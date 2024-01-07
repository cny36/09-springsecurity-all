package com.cny.config;

import com.cny.convert.MyAccessTokenConvert;
import com.cny.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;

/**
 * @author : chennengyuan
 */
@Configuration
@EnableAuthorizationServer //该注解表示当前应用作为一个认证授权服务器
public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    DataSource dataSource;
    @Autowired
    UserDetailServiceImpl userDetailService;
    @Autowired
    MyAccessTokenConvert accessTokenConvert;

    /**
     * 让认证服务器指定哪些客户端会找它要令牌
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 将客户端应用的信息保存在数据库中
        clients.jdbc(dataSource);

        // 将客户端应用的信息保存在内存中
        /*clients.inMemory()
                //设置客户端appid
                .withClient("orderClient")
                //设置客户端密码
                .secret(passwordEncoder.encode("6666"))
                //设置客户端的资源访问权限
                .scopes("read", "write")
                //设置令牌有效期
                .accessTokenValiditySeconds(3600)
                //设置能够访问的资源
                .resourceIds("order-service")
                //设置采用的认证模式
                .authorizedGrantTypes("password")

                .and()

                .withClient("order-service")
                .secret(passwordEncoder.encode("6666"))
                .accessTokenValiditySeconds(3600)
                .resourceIds("order-service")
                .authorizedGrantTypes("password");*/
    }

    /**
     * 核对用户信息是否合法
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(getTokenStore())
                .accessTokenConverter(getJwtAccessTokenConverter())
                .userDetailsService(userDetailService)
                .authenticationManager(authenticationManager)
                //设置访问token支持的方法
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

    private TokenStore getTokenStore() {
        //return new JdbcTokenStore(dataSource);
        return new JwtTokenStore(getJwtAccessTokenConverter());
    }

    private JwtAccessTokenConverter getJwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        //设置签名密钥
        jwtAccessTokenConverter.setSigningKey("666888");
        //设置验证令牌时的密钥
        jwtAccessTokenConverter.setVerifier(new MacSigner("666888"));
        //jwt设置自定义信息
        jwtAccessTokenConverter.setAccessTokenConverter(accessTokenConvert);
        return jwtAccessTokenConverter;
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //表示要验证令牌的合法性，必须是认证通过，比如携带账号密码认证通过后，才会检查令牌是否有效
        security.checkTokenAccess("isAuthenticated()");
    }
}
