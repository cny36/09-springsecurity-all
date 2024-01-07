package com.cny.common.code.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author : chennengyuan
 */
public class ImageCodeAuthenticationException extends AuthenticationException {

    public ImageCodeAuthenticationException(String msg) {
        super(msg);
    }
}
