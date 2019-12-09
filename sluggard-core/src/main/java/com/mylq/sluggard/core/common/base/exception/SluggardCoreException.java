package com.mylq.sluggard.core.common.base.exception;

import java.text.MessageFormat;

/**
 * Sluggard应用异常
 *
 * @author WangRunQian
 * @date 2019/11/28
 * @since 1.0.0
 */
public class SluggardCoreException extends RuntimeException {

    public SluggardCoreException(String message) {
        super(message);
    }

    public SluggardCoreException(String pattern, Object ... arguments) {
        this(MessageFormat.format(pattern, arguments));
    }

    public SluggardCoreException(Throwable e) {
        super(e);
    }

    public SluggardCoreException(String message, Throwable e) {
        super(message, e);
    }
}
