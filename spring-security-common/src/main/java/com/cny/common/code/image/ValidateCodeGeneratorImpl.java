package com.cny.common.code.image;

import com.cny.common.SecuritySystemProperties;
import com.cny.common.code.ValidateCodeGenerator;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author : chennengyuan
 * 图像验证码生成器
 */
@Slf4j
@Data
public class ValidateCodeGeneratorImpl implements ValidateCodeGenerator {

    private SecuritySystemProperties securitySystemProperties;

    @Override
    public ImageValidateCode generate(ServletWebRequest request) {
        //读取配置的内容设置宽度和高度
        int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width",
                securitySystemProperties.getCode().getImage().getWidth());
        int height = ServletRequestUtils.getIntParameter(request.getRequest(),
                "height",
                securitySystemProperties.getCode().getImage().getHeight());
        //构建图片对象
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();
        //设置颜色效果
        g.setColor(getRandColor(80, 240));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(230, 240));
        for (int i = 0; i < 166; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        String code = "";
        for (int i = 0; i < securitySystemProperties.getCode().getImage().getLength();
             i++) {
            String rand = String.valueOf(random.nextInt(10));
            code += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110),
                    20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }
        g.dispose();
        return new ImageValidateCode(code, image, 60);
    }

    /**
     * 生成随机背景条纹
     *
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
