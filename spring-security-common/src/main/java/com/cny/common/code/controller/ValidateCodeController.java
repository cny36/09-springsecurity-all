package com.cny.common.code.controller;

import com.cny.common.code.ValidateCodeGenerator;
import com.cny.common.code.image.ImageValidateCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author : chennengyuan
 */
@Slf4j
@RestController
@RequestMapping("/code")
public class ValidateCodeController {


    @Autowired
    private ValidateCodeGenerator validateCodeGenerator;

    @RequestMapping("/image")
    public void generateImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.生成验证码对象
        ImageValidateCode imageValidateCode = validateCodeGenerator.generate(new ServletWebRequest(request, response));
        log.info("生成的验证为：{}", imageValidateCode.getCode());

        //2.将验证码保存到session中
        HttpSession session = request.getSession();
        session.setAttribute("SESSION_KEY_IMAGE_CODE", imageValidateCode);

        //3.将图形验证码的图片流写到客户端
        ImageIO.write(imageValidateCode.getBufferedImage(), "JPEG", response.getOutputStream());
    }
}
