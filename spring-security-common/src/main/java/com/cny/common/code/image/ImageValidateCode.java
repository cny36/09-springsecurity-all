package com.cny.common.code.image;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图形验证码
 *
 * @author : chennengyuan
 */
@Data
public class ImageValidateCode {

    private String code;
    private BufferedImage bufferedImage;
    private LocalDateTime expireTime;

    public ImageValidateCode(String code, BufferedImage bufferedImage, LocalDateTime expireTime) {
        this.code = code;
        this.bufferedImage = bufferedImage;
        this.expireTime = expireTime;
    }

    public ImageValidateCode(String code, BufferedImage bufferedImage, int expireSecond) {
        this.code = code;
        this.bufferedImage = bufferedImage;
        this.expireTime = LocalDateTime.now().plusSeconds(expireSecond);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
