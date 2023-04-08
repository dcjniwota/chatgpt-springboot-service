package com.cyl.ctrbt.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cyl.ctrbt.openai.ChatGPTUtil;
import com.cyl.ctrbt.openai.entity.chat.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestOneStudioController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ChatGPTUtil chatGPTUtil;

    @RequestMapping("/receive")
    public String chatMsg(@RequestBody(required = false) JSONObject json) {
        logger.info("chatMsg params is " + JSONUtil.toJsonStr(json));
        if (json != null) {
            String content = json.getJSONObject("text").get("content").toString().replaceAll(" ", "");
            return text(content);
        }
        return null;
    }

    private String text(String content) {
        Message message = chatGPTUtil.chat(content, "testonestudio");
        String result = message.getContent();
        logger.info("result is " + result);
        return result;
    }
}
