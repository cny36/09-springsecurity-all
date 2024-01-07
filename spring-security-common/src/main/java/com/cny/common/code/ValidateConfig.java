package com.cny.common.code;

import com.cny.common.SecuritySystemProperties;
import com.cny.common.code.image.ValidateCodeGeneratorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : chennengyuan
 */
@Configuration
public class ValidateConfig {

    @Autowired
    private SecuritySystemProperties securitySystemProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ValidateCodeGeneratorImpl imageValidateCodeGenerator = new ValidateCodeGeneratorImpl();
        imageValidateCodeGenerator.setSecuritySystemProperties(securitySystemProperties);
        return imageValidateCodeGenerator;
    }
}
