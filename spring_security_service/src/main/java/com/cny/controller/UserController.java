package com.cny.controller;

import com.cny.entity.UserInfo;
import com.cny.pojo.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : chennengyuan
 */
@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {

    @GetMapping("/{id}")
    public ResultBean<UserInfo> selectUserInfo(@PathVariable int id) {
        return new ResultBean<>(200, new UserInfo(id, "andy", 25));
    }

    @PostMapping
    public ResultBean<String> createUser(@RequestBody UserInfo userInfo) {
        log.info("新建用户：{}", userInfo.toString());
        return new ResultBean<>(200, "创建成功");
    }

    @GetMapping("/getAuthenticationFromSecurityContextHolder")
    public Authentication getAuthenticationFromSecurityContextHolder() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/getAuthenticationFromSession")
    public Authentication getAuthenticationFromSession(HttpServletRequest request) {
        SecurityContext securityContext = (SecurityContext) request.getSession().getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
        return securityContext.getAuthentication();
    }
}
