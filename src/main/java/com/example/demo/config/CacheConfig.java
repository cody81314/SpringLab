package com.example.demo.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching  // 只要宣告 @EnableCaching 啟用了緩存支援，Spring Boot 就會自動配置緩存基礎結構。
public class CacheConfig {
}
