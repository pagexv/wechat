package com.wechat.controller;


import com.google.gson.Gson;
import com.wechat.RobotUtil;
import com.wechat.controller.dto.WebHookPayload;
import com.wechat.service.SendWeChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@Slf4j
public class WebHookController {

    @PostMapping(value = "/notification")
    public String receiveWebHook(@RequestBody(required = false) String requestBody){
        if (requestBody != null)
            log.info(requestBody);
        Gson gson = new Gson();
        WebHookPayload payload = gson.fromJson(requestBody,WebHookPayload.class);
        SendWeChatMessage weChat = new SendWeChatMessage();
        weChat.sendWeChatMsgText("@all", "1", "", payload.toString(), "0");
        return "success";
    }
}
