package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("123");
        return jwtAccessTokenConverter;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory()

                .withClient("userClient")

                .authorizedGrantTypes("password", "refresh_token")

                .scopes("select")

                .secret("$2a$10$r1ku0ThuoxaGEWk7lezlzuKonlp8o0sNA0RdT8n1NL1bmsVh.ppH2")

                .and()

                .withClient("client_1")

                .authorizedGrantTypes("client_credentials", "refresh_token")

                .scopes("select")

                .authorities("client")

                .secret("$2a$10$r1ku0ThuoxaGEWk7lezlzuKonlp8o0sNA0RdT8n1NL1bmsVh.ppH2");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints
                // Grant type 為 password 需指定 authenticationManager
                .authenticationManager(authenticationManager)
                // 指定 token 轉換器
                .accessTokenConverter(accessTokenConverter());
    }
}
