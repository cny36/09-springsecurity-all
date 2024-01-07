package com.cny.common.code.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : chennengyuan
 * 图形验证码属性
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageValidateCodeProperties {

    //验证码图片的默认的宽度和高度
    private int height = 24;
    private int width = 66;
    //验证码的默认长度
    private int length = 6;
    //验证码默认的过期时间
    private int expireSecond = 60;
}
