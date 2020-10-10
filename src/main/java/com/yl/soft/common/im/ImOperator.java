package com.yl.soft.common.im;

import com.alibaba.fastjson.JSONObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author Like
 */
@Component
@Slf4j
public class ImOperator {

    @Value("${tencent_yun.im.sdk_app_id}")
    private long sdkappid;
    @Value("${tencent_yun.im.secret_key}")
    private String key;
    @Value("${tencent_yun.im.expire_time}")
    private Long expireTime;
    @Value("${tencent_yun.im.default_im_admin_account}")
    private String adminAccount;
    private final TLSSigAPIv2 tlsSigAPIv2;

    public ImOperator(TLSSigAPIv2 tlsSigAPIv2) {
        this.tlsSigAPIv2 = tlsSigAPIv2;
    }

    /**
     * 获取用户sig
     *
     * @param userId 用户id
     * @return
     */
    public String getUserSig(String userId) {
        return tlsSigAPIv2.genUserSig(userId, 259200);
    }

    /**
     * 创建账号
     */
    public String createLiveGrop(String gropName) {
        String userSig = tlsSigAPIv2.genUserSig();
        JSONObject data = new JSONObject();
        data.put("Type", "AVChatRoom");
        data.put("Name", gropName);
        try {
            HttpResponse<String> response = Unirest.post("https://console.tim.qq.com/v4/group_open_http_svc/create_group")
                    .header("accept", "application/json")
                    .queryString("sdkappid", sdkappid)
                    .queryString("identifier", adminAccount)
                    .queryString("usersig", userSig)
                    .queryString("random", new Random().nextInt(999999999))
                    .queryString("contenttype", "json")
                    .body(data.toJSONString())
                    .asString();
            String respStr = response.getBody();
            log.info(respStr);
            return respStr;
        } catch (UnirestException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
