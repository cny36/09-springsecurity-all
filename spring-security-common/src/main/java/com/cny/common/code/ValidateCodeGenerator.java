package com.cny.common.code;

import com.cny.common.code.image.ImageValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author : chennengyuan
 */
public interface ValidateCodeGenerator {

    ImageValidateCode generate(ServletWebRequest request);

}
