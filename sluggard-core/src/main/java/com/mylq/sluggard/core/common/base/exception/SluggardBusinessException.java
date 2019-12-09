package com.mylq.sluggard.core.common.base.exception;

import java.text.MessageFormat;

/**
 * Sluggard业务异常
 *
 * @author WangRunQian
 * @date 2019/12/7
 * @since 1.0.0
 */
public class SluggardBusinessException extends RuntimeException {

    public SluggardBusinessException(String message) {
        super(message);
    }

    public SluggardBusinessException(String pattern, Object ... arguments) {
        this(MessageFormat.format(pattern, arguments));
    }

    public SluggardBusinessException(Throwable e) {
        super(e);
    }

    public SluggardBusinessException(String message, Throwable e) {
        super(message, e);
    }
}
