package com.cny.controller;

import com.cny.entity.MyUser;
import com.cny.entity.OrderInfo;
import com.cny.entity.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

/**
 * @author : chennengyuan
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @PostMapping
    public ResultBean create(@RequestBody OrderInfo orderInfo/*, @AuthenticationPrincipal MyUser myUser*/) {
        log.info("create order");
        //log.info("username:{}, password:{}, phone:{}", myUser.getUsername(), myUser.getPassword(), myUser.getPhone());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        LinkedHashMap<String,Object> decodedDetails = (LinkedHashMap<String, Object>) details.getDecodedDetails();
        return new ResultBean(200, orderInfo);
    }

    @GetMapping("/{orderNo}")
    public ResultBean getByOrderNo(@PathVariable String orderNo) {
        log.info("getByOrderNo.....");
        return new ResultBean(200, new OrderInfo(orderNo));
    }

}
