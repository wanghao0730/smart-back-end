package com.ruoyi.smart.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.util.WxMaConfigHolder;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.service.WxLoginService;
import com.ruoyi.framework.web.service.WxLoginUser;
import com.ruoyi.smart.domain.WxUser;
import com.ruoyi.smart.param.UserLoginParam;
import com.ruoyi.smart.service.IWxUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;

@RestController
//@AllArgsConstructor
@Slf4j
@Api(tags = "微信用户接口")
@RequestMapping("/wx/user/{appid}") //用户登录接口
public class WxMaUserController {
    private final WxMaService wxMaService; //这个service用于对接微信

    @Autowired
    private IWxUserService wxUserService;

    @Autowired
    private WxLoginService wxLoginService; //自己封装的登录逻辑 在framework

    //从容器中注入数据到wxMaService
    public WxMaUserController(WxMaService wxMaService) {
        this.wxMaService = wxMaService;
    }

    /**
     * 登陆接口
     */

    @ApiOperation("微信用户登录")
    @PostMapping("/login")
    public AjaxResult login(@PathVariable String appid, @Validated @RequestBody UserLoginParam userLoginParam) {
        if (StringUtils.isEmpty(userLoginParam.getCode())) {
            return AjaxResult.error("用户code不能为空");
        }

        //判断应用的id
        if (!wxMaService.switchover(appid)) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
        }

        try {
            //判断是否以教务系统账户或密码方式登录 匹配数据库(这一步通过表示用户已经在数据库中有数据包含了openid)
            //TODO 没有教务系统的验证,这里还没想好怎么设计
//            if (StringUtils.isNotEmpty(userLoginParam.getUsername()) && StringUtils.isNotEmpty(userLoginParam.getPassword())) {
//                //调用查询用户数据
//            }

            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(userLoginParam.getCode());
            String sessionKey = session.getSessionKey();
            String openid = session.getOpenid(); //用户openId
            //查找数据库中的用户的openid是否存在
//            WxUser wxUser = wxUserService.selectWxUserByOpenId(openid);
//            if (StringUtils.isNull(wxUser)) { //如果为空则调用新增接口
//                log.info("用户openid:{},以及sessionkey:{}",openid,sessionKey);
//            }
            //查询用户是否存在
            WxUser wxUser  = wxUserService.selectWxUserByOpenId(openid);
            if (StringUtils.isNull(wxUser)) { //用户不存在的话调用新增方法
                WxUser inserUser = new WxUser();
                inserUser.setOpenId(openid);
                inserUser.setNickname(userLoginParam.getNickName());
                inserUser.setSex(Integer.toString(userLoginParam.getGender()));
                inserUser.setPhoto(userLoginParam.getAvatarUrl());
                inserUser.setProvince(userLoginParam.getProvince());
                inserUser.setCity(userLoginParam.getCity());
                int i = wxUserService.insertWxUser(inserUser);
                //查询用户信息 根据新增用户
                WxUser newUser = wxUserService.selectWxUserById(inserUser.getId());
                newUser.setNull(); //清除隐私数据
                //返回用户
                //封装wxLoginuser对象
                WxLoginUser wxLoginUser = new WxLoginUser();//该对象存储redis
                wxLoginUser.setOpenid(openid);
                wxLoginUser.setNickname(userLoginParam.getNickName());
                String token = wxLoginService.createToken(wxLoginUser); //走完这一步redis中就存储了登录对象

                HashMap<String, Object> resMap = new HashMap<>();
                resMap.put("token",token);
                resMap.put("user",newUser);
                return AjaxResult.success("请求成功",resMap);
            }
            else {
                WxLoginUser wxLoginUser = new WxLoginUser();//该对象存储redis
                wxLoginUser.setOpenid(openid);
                wxLoginUser.setNickname(userLoginParam.getNickName());
                String token = wxLoginService.createToken(wxLoginUser);
                wxUser.setNull();//清除隐私数据
                HashMap<String, Object> resultMap = new HashMap<>();
                resultMap.put("token",token);
                resultMap.put("user",wxUser);
                return AjaxResult.success("请求成功",resultMap);
            }
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            return AjaxResult.error("获取用户openId错误");
        } finally {
            WxMaConfigHolder.remove();//清理ThreadLocal
        }
    }

    /**
     * 根据用户主键id查询数据
     * @param userId
     * @return
     */
    @ApiOperation("用户信息查询")
    @GetMapping("/info/{id}")
    public AjaxResult userInfo(@NotNull(message = "用户ID主键") @PathVariable("id") Long userId) {
        WxUser wxUser = wxUserService.selectWxUserById(userId);
        wxUser.setNull();
        return AjaxResult.success(wxUser);
    }

    @ApiOperation("更新用户数据")
    @PostMapping("/update")
    public AjaxResult updateInfo(@RequestBody  WxUser wxUser) {
        int rows = wxUserService.updateWxUser(wxUser);
        return rows > 0 ? AjaxResult.success("修改成功") : AjaxResult.error("修改失败");
    }
}
