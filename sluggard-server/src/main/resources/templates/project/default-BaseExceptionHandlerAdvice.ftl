package ${project.basePackage}.core.base.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ${project.basePackage}.core.base.enums.BaseErrorEnum;
import ${project.basePackage}.core.base.result.JsonResult;

@RestControllerAdvice
public class BaseExceptionHandlerAdvice {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult<Void> handlerDuplicateKeyException(DuplicateKeyException e) {
        logger.error("违反唯一索引约束。", e);
        return JsonResult.error(BaseErrorEnum.SYSTEM_DUPLICATE_KEY);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult<Void> handlerException(Exception e) {
        logger.error("未知异常。", e);
        return JsonResult.error(BaseErrorEnum.SYSTEM_MAINTENANCE);
    }
}
