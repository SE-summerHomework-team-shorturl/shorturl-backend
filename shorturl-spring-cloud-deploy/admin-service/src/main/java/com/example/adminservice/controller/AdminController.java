package com.example.adminservice.controller;

import com.example.adminservice.service.AdminService;
import com.example.sharedentity.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/admin/finduser")
    public Message findAllUsers(){
        return adminService.findAllUsers();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/admin/findurl")
    public Message findAllShortUrls(){
        return adminService.findAllShortUrls();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/admin/deleteurl")
    public Message deleteShortUrlById(@RequestParam Integer id){
        return adminService.deleteShortUrlById(id);
    }
}
