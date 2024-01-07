package com.cny.common.code.provider;

import com.cny.common.code.detail.ImageCodeWebAuthenticationDetails;
import com.cny.common.code.exception.ImageCodeAuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author : chennengyuan
 */
@Component
@Slf4j
public class ImageCodeAuthenticationProvider extends DaoAuthenticationProvider {

    public ImageCodeAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder){
        this.setUserDetailsService(userDetailsService);
        this.setPasswordEncoder(passwordEncoder);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        //增加验证码的验证逻辑
        ImageCodeWebAuthenticationDetails details = (ImageCodeWebAuthenticationDetails) authentication.getDetails();
        if (!details.getImageCodeIsCorrect()) {
            throw new ImageCodeAuthenticationException("验证码不正确");
        }

        super.additionalAuthenticationChecks(userDetails, authentication);
    }
}
