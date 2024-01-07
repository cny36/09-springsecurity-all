package com.cny.common.code.detail;

import com.cny.common.code.image.ImageValidateCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author : chennengyuan
 */
@Slf4j
public class ImageCodeWebAuthenticationDetails extends WebAuthenticationDetails {

    //存储验证码校验结果
    private boolean imageCodeIsCorrect;

    public boolean getImageCodeIsCorrect(){
        return imageCodeIsCorrect;
    }

    /**
     * Records the remote address and will also set the session Id if a session already
     * exists (it won't create one).
     *
     * @param request that the authentication request was received from
     */
    public ImageCodeWebAuthenticationDetails(HttpServletRequest request) {
        super(request);

        //判断验证码是否正确
        HttpSession session = request.getSession();
        ImageValidateCode codeInSession = (ImageValidateCode) session.getAttribute("SESSION_KEY_IMAGE_CODE");
        String codeInRequest = request.getParameter("imageValidateCode");
        log.info("codeInSession - 是否过期：{} - 验证码：{}", codeInSession.isExpired(), codeInSession.getCode());
        log.info("codeInRequest - 验证码：{}", codeInRequest);

        if(!StringUtils.isEmpty(codeInRequest) && !codeInSession.isExpired() && codeInRequest.equals(codeInSession.getCode())){
            this.imageCodeIsCorrect = true;
            session.removeAttribute("SESSION_KEY_IMAGE_CODE");
        }
    }
}
