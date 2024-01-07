package com.cny.config;

import com.cny.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.*;

/**
 * @author : chennengyuan
 */
//@Configuration
//@EnableWebSecurity
public class OAuth2ResourceServerWebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private UserDetailServiceImpl userDetailService;
//
//    //声明一个Bean，来负责验证令牌的合法性
//    @Bean
//    public ResourceServerTokenServices tokenServices() {
//        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
//        remoteTokenServices.setClientId("order-service");
//        remoteTokenServices.setClientSecret("6666");
//        remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:9999/oauth/check_token");
//        remoteTokenServices.setAccessTokenConverter(getAccessTokenConverter());
//        return remoteTokenServices;
//    }
//
//    private AccessTokenConverter getAccessTokenConverter() {
//        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
//
//        DefaultUserAuthenticationConverter userAuthenticationConverter = new DefaultUserAuthenticationConverter();
//        userAuthenticationConverter.setUserDetailsService(userDetailService);
//
//        accessTokenConverter.setUserTokenConverter(userAuthenticationConverter);
//        return accessTokenConverter;
//    }
//
//    //覆盖掉原先的配置
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        OAuth2AuthenticationManager oAuth2AuthenticationManager = new OAuth2AuthenticationManager();
//        oAuth2AuthenticationManager.setTokenServices(tokenServices());
//        return oAuth2AuthenticationManager;
//    }
}
