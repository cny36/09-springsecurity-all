package com.cny.common;

import com.cny.common.code.ValidateCodeProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author : chennengyuan
 * 属性配置类
 */
@Data
@ConfigurationProperties(prefix = "security.system")
public class SecuritySystemProperties {

    /**
     * 验证码
     */
    private ValidateCodeProperties code = new ValidateCodeProperties();

    //此处可以配置其他属性
}
