package com.cny.config;

import com.cny.common.code.detail.source.ImageCodeAuthenticationDetailsSource;
import com.cny.common.code.provider.ImageCodeAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author : chennengyuan
 */
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private ImageCodeAuthenticationProvider imageCodeAuthenticationProvider;
    @Autowired
    private ImageCodeAuthenticationDetailsSource imageCodeAuthenticationDetailsSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //核心配置方法
        http
                .authorizeRequests()
                //给登录页开权限
                .antMatchers("/mylogin.html","/code/image").permitAll()
                //设置所有的请求都需要认证
                .anyRequest().authenticated()
                .and()
                //表单登录的方式
                .formLogin()
                .authenticationDetailsSource(imageCodeAuthenticationDetailsSource)
                //配置自定义的登录页面
                .loginPage("/mylogin.html")
                .loginProcessingUrl("/login")
                .and()
                //支持HttpBasic的方式
                .httpBasic()
                .and()
                //关闭csrf
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        //增加自定义的配置信息
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider);
        auth.authenticationProvider(imageCodeAuthenticationProvider);
    }
}
