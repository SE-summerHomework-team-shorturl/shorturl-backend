package com.example.consumer.controller;

import com.example.sharedentity.dto.Message;
import com.example.sharedentity.entity.User;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class ConsumerController {
    private static final String redirectServiceUrl = "http://REDIRECT-SERVICE";
    private static final String registerServiceUrl = "http://REGISTER-SERVICE";
    private static final String addUrlServiceUrl = "http://ADDURL-SERVICE";
    private static final String userUrlServiceUrl = "http://USERURL-SERVICE";
    private static final String loginServiceUrl = "http://LOGIN-SERVICE";
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/print")
    public String print()  {
        return "prints";
    }

    @RequestMapping(value = "/r/{token}")
    public void redirect(HttpServletResponse response, @PathVariable String token) throws Exception {
        String url = restTemplate.getForObject(redirectServiceUrl+ "/r/"+token, String.class);
        response.sendRedirect(url);
    }

    @PostMapping(value = "/user/register")
    public Message register(@RequestBody User user) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String,String>();
        map.add("username",user.getUsername());
        map.add("password",user.getPassword());
        map.add("email",user.getEmail());
        return restTemplate.postForObject(registerServiceUrl+ "/user/register", map, Message.class);
    }

    @GetMapping(value = "/urlmanage/addurl")
    public Message addToMyShortUrls(@RequestParam(value = "url") String url,@RequestParam("userId") Integer id) {
        return restTemplate.getForObject(addUrlServiceUrl+ "/urlmanage/addurl?url="+url+"&userId="+id,Message.class);
    }

    @GetMapping(value = "/urlmanage/findurl")
    public Message findAllMyShortUrls(@RequestParam(value = "page") int page,
                                      @RequestParam(value = "size") int size,
                                      @RequestParam(value = "userId") int userId) {
        return restTemplate.getForObject(userUrlServiceUrl+ "/urlmanage/findurl?page="+page+"&size="+size+"&userId="+userId,Message.class);
    }

    @GetMapping(value = "/urlmanage/deleteurl")
    public Message deleteMyShortUrlById(@RequestParam(value = "id") int id,
                                        @RequestParam(value = "userId") int userId) {
        return restTemplate.getForObject(userUrlServiceUrl+ "/urlmanage/deleteurl?id="+id+"&userId="+userId,Message.class);
    }

    @GetMapping(value = "/login")
    public Message login() {
        return restTemplate.getForObject(loginServiceUrl+ "/login",Message.class);
    }
}
