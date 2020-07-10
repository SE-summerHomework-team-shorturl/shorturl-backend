package com.example.configclient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
/**
 * @className: ConfigClientController
 * @description: 应用程序控制层
 * @author: niaonao
 **/
@RestController
@RequestMapping(value = "/config/client")
public class ConfigClientController {
    /** 外部属性 account */
    @Value("${data.env}")
    private String username;


    @GetMapping("/getRepositoryUrl")
    public String getRepositoryUrl() {
        StringBuilder resultUrl = new StringBuilder("Account：");
        resultUrl.append(username)
                .append("<br/>");
        return resultUrl.toString();
    }
}
