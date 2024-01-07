package com.cny.common.code;

import com.cny.common.code.image.ImageValidateCodeProperties;
import lombok.Data;

/**
 * @author : chennengyuan
 * 验证码属性 比如图像验证码、短信验证码
 */
@Data
public class ValidateCodeProperties {

    /**
     * 图像验证码
     */
    private ImageValidateCodeProperties image = new ImageValidateCodeProperties();

    //此处可以配置其他验证码 比如短信验证码
}
