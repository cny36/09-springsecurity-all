package com.cny.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author : chennengyuan
 */
@Slf4j
@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 此处省略从数据库获取用户信息
        // 用户名：张三，密码：6666才可以授权成功
        String dbUsername = "张三";
        String dbPassword = passwordEncoder.encode("6666");
        log.info("获取到数据库用户：{}, 传入用户：{}", dbUsername, username);

        if (dbUsername.equals(username)) {
            return new User(dbUsername, dbPassword, AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN"));
        }

        throw new UsernameNotFoundException(username + " not found");
    }
}
