package com.ruoyi.framework.web.service;

import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.ip.AddressUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 该类用于自定义的用户验证
 */
@Component
public class WxLoginService
{
    // 令牌自定义标识 用户header的keyzhi1
    private String header = "Authorization";

    // 令牌秘钥 加密密钥
    private String secret = "touchsmart";
    //自定义用户的token前缀 与后台的bearer进行区分
    private  final String TOKEN_PREFIX = "wx ";
    //存储在redis中的key值
    private  final String TOKEN_KEY = "wx_user_key";
    //redis中的token key值
    private final String REDIES_TOKEN_KEY = "wx_tokens:";
    // 令牌有效期（默认30）
    private static final int expireTime = 1440 * 30;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    @Autowired
    private RedisCache redisCache;

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public WxLoginUser getLoginUser(HttpServletRequest request)
    {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token))
        {
            try
            {
                //解析token
                Claims claims = parseToken(token);
                // 解析对应的权限以及用户信息 拿到claims后根据令牌前缀获取uuid 也就是token的值
                String uuid = (String) claims.get(TOKEN_KEY);
                //redis前缀拼接REDIES_TOKEN_KEY + uuid 拿到redis的数据
                String userKey = getTokenKey(uuid);
                WxLoginUser user = redisCache.getCacheObject(userKey);
                return user;
            }
            catch (Exception e)
            {
            }
        }
        return null;
    }

    /**
     * 设置用户身份信息
     */
    public void setLoginUser(WxLoginUser loginUser)
    {
        if (StringUtils.isNotNull(loginUser) && StringUtils.isNotEmpty(loginUser.getUuid()))
        {
            refreshToken(loginUser);
        }
    }

    /**
     * 删除用户身份信息
     */
    public void delLoginUser(String token)
    {
        if (StringUtils.isNotEmpty(token))
        {
            Claims claims = parseToken(token);
            //解析token根据令牌前缀获取token数据
            String uuiid = (String)claims.get(TOKEN_KEY);
            String userKey = getTokenKey(uuiid);
            redisCache.deleteObject(userKey);
        }
    }

    /**
     * 创建令牌
     *
     * @param loginUser 用户信息
     * @return 令牌
     */
    public String createToken(WxLoginUser loginUser)
    {
        String token = IdUtils.fastUUID();
        //设置loginUser的token值
        loginUser.setUuid(token);
        //设置loginUser的ip地址 os浏览器等等信息
        setUserAgent(loginUser);
        refreshToken(loginUser);

        Map<String, Object> claims = new HashMap<>();
        claims.put(TOKEN_KEY, token);
        return createToken(claims);
    }

    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
     *
     * @param loginUser
     * @return 令牌
     */
    public void verifyToken(WxLoginUser loginUser)
    {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN)
        {
            //如果redis中设置的超时时间-当前时间 <=二十分钟 则刷新token 否则不做处理
            refreshToken(loginUser);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public void refreshToken(WxLoginUser loginUser)
    {
        //设置用户的登录时间为 当前系统时间
        loginUser.setLoginTime(System.currentTimeMillis());
        //设置用户的超时时间为 用户登录时间加上 (用户token过期时间-xml中配置的默认30分钟 * 60000毫秒) 1分钟=60000毫秒
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUser.getUuid());
        //将登录的用户数据存储到redis中
        redisCache.setCacheObject(userKey, loginUser, expireTime, TimeUnit.MINUTES);
    }

    /**
     * 设置用户代理信息
     *
     * @param loginUser 登录信息
     */
    public void setUserAgent(WxLoginUser loginUser)
    {
        //loginUser是引用传递 这里去设置用户的登录信息
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        loginUser.setIpaddr(ip);
        loginUser.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
        loginUser.setBrowser(userAgent.getBrowser().getName());
        loginUser.setOs(userAgent.getOperatingSystem().getName());
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String createToken(Map<String, Object> claims)
    {
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
        return token;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token)
    {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token)
    {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    public String getToken(HttpServletRequest request)
    {
        String token = request.getHeader(header);
        //这里改造成了判断用户前缀是否为wx
        if (StringUtils.isNotEmpty(token) && token.startsWith(TOKEN_PREFIX))
        {
            token = token.replace(TOKEN_PREFIX, "");
            return token;
        }
        else { //如果不是前台用户登录以wx开头 则走后台登录的那套 外界会判断是否为空
            return null;
        }

    }

    //拼接token的值和uuid
    private String getTokenKey(String uuid)
    {
        return REDIES_TOKEN_KEY + uuid;
    }
}
