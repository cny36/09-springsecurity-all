package com.cny.common;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author : chennengyuan
 */
@Configuration
@EnableConfigurationProperties(SecuritySystemProperties.class)
public class SecuritySystemConfig {
}
