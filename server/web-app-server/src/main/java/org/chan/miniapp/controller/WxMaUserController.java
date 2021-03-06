package org.chan.miniapp.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import org.chan.miniapp.config.WxMaConfiguration;
import org.chan.miniapp.utils.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小程序用户接口
 */
@RestController
@RequestMapping("/wx/user/{appid}")
public class WxMaUserController {



    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 登陆接口
     */
    @GetMapping("/login")
    public String login(@PathVariable String appid, String code) {
        if (StringUtils.isBlank(code)) {
            return "empty jscode";
        }

        final WxMaService wxService = WxMaConfiguration.getMaServices().get(appid);
        if (wxService == null) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%d]的配置，请核实！", appid));
        }

        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            this.logger.info(session.getSessionKey());
            this.logger.info(session.getOpenid());
            //TODO 可以增加自己的逻辑，关联业务相关数据
            return JsonUtils.toJson(session);
        } catch (WxErrorException e) {
            this.logger.error(e.getMessage(), e);
            return e.toString();
        }
    }

    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @GetMapping("/info")
    public String info(@PathVariable String appid, String sessionKey,
                       String signature, String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaServices().get(appid);
        if (wxService == null) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%d]的配置，请核实！", appid));
        }

        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        // 解密用户信息
        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
        //微信号
        String openId =  userInfo.getOpenId();
        //昵称
        String nickName = userInfo.getNickName();
        //性别
        String gender = userInfo.getGender();
        //头像URL
        String avatarUrl = userInfo.getAvatarUrl();
        //省份
        String province = userInfo.getProvince();
        //国家(地区)
        String country = userInfo.getCountry();
        //微信版本语言
        String language = userInfo.getLanguage();
        //城市
        String city = userInfo.getCity();
        //联合ID
        String unionId = userInfo.getUnionId();



        String querySql = "SELECT COUNT(1) FROM wechat_user WHERE openId = ?";

        Integer count = this.jdbcTemplate.queryForObject(querySql, new Object[] { openId }, Integer.class);
        if (count != null && count == 0) {
            String insertSql = "INSERT INTO " +
                    "wechat_user(`openId`,`nickName`,`gender`,`avatarUrl`,`province`,`country`,`language`,`city`,`unionId`) " +
                    "VALUES (?,?,?,?,?,?,?,?,?)";
            this.jdbcTemplate.update(insertSql,
                    new Object[] { openId,nickName,gender,avatarUrl,province,country,language,city,unionId });
        }


        return JsonUtils.toJson(userInfo);
    }

    /**
     * <pre>
     * 获取用户绑定手机号信息
     * </pre>
     */
    @GetMapping("/phone")
    public String phone(@PathVariable String appid, String sessionKey, String signature,
                        String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaServices().get(appid);
        if (wxService == null) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%d]的配置，请核实！", appid));
        }

        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);

        return JsonUtils.toJson(phoneNoInfo);
    }

}
