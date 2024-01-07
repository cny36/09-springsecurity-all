package com.cny.config;

import com.cny.convert.MyAccessTokenConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author : chennengyuan
 */
@Configuration
//表明资源服务器的角色，声明了该服务的所有资源都是受保护的
//会对所有的HTTP请求进行验证以确定Header部分中是否包含Token信息
@EnableResourceServer
public class OAuth2ResourceServereConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private MyAccessTokenConverter accessTokenConverter;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //标注该资源的ID
        resources.resourceId("order-service").tokenStore(getTokenStore()).stateless(true);
    }

    private TokenStore getTokenStore() {
        //return new JdbcTokenStore(dataSource);
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    private JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        //设置签名密钥
        jwtAccessTokenConverter.setSigningKey("666888");
        //设置验证令牌时的密钥
        jwtAccessTokenConverter.setVerifier(new MacSigner("666888"));
        jwtAccessTokenConverter.setAccessTokenConverter(accessTokenConverter);
        return jwtAccessTokenConverter;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET).access("#oauth2.hasScope('read')")
                .antMatchers(HttpMethod.POST).access("#oauth2.hasScope('write')");
    }
}
