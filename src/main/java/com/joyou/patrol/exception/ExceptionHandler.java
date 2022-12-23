package com.joyou.patrol.exception;

import com.alibaba.fastjson.JSONObject;
import com.joyou.integrationOpsDevice.model.ApiResult;
import com.joyou.integrationOpsDevice.model.ServerResponse;
import com.joyou.integrationOpsDevice.util.SpringContextUtil;
import com.zhgs.interceptor.EncryptedInterceptor;
import com.zhgs.utils.CipherUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yangfei
 * @since 2019-05-18
 */
@ControllerAdvice
@Slf4j
@ResponseBody
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public ServerResponse<List<String>> handleMethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<String> list = new ArrayList<>();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            list.add(fieldError.getDefaultMessage());
        }
        Collections.sort(list);
        return ServerResponse.createByErrorCodeMessage(501, list);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.OK)
    public ServerResponse<String> httpBindExceptionHandler(BindException ex) {
        StringBuilder sb = new StringBuilder();
        BindingResult bindingResult = ex.getBindingResult();
        List<String> list = new ArrayList<>();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            sb.append(fieldError.getDefaultMessage());
            sb.append(",");
        }
        String msg = sb.substring(0, sb.length() - 1);
        return ServerResponse.createByErrorCodeMessage(501, msg);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    @ResponseBody
    Object handleException(Exception ex) throws BadPaddingException, IllegalBlockSizeException {
        if (ex instanceof MobileServiceException) {
            MobileServiceException mse = (MobileServiceException) ex;
            ApiResult<Object> fail = ApiResult.builder()
                    .code(mse.getCode())
                    .message(mse.getMessage())
                    .build();
            if (SpringContextUtil.getApplicationContext().containsBean("encryptedInterceptor")) {
                EncryptedInterceptor encryptedInterceptor = SpringContextUtil.getBean("encryptedInterceptor", EncryptedInterceptor.class);
                String aesKey = encryptedInterceptor.getAesKey();
                Cipher aesEncryptCipher = CipherUtils.getAESEncryptCipher(aesKey);
                byte[] objectBytes = JSONObject.toJSONBytes(fail);
                byte[] aesCipher = aesEncryptCipher.doFinal(objectBytes);
                return Base64.getEncoder().encode(aesCipher);
            } else {
                return fail;
            }
        }
        if (ex instanceof com.joyou.integrationOpsDevice.config.exception.ServiceException) {
            com.joyou.integrationOpsDevice.config.exception.ServiceException se = (com.joyou.integrationOpsDevice.config.exception.ServiceException) ex;
            return ServerResponse.createByErrorCodeMessage(se.getCode(), se.getMsg());
        }
        log.error("系统错误", ex);
        return ServerResponse.createByErrorMessage(ex.toString());
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(value = BusinessRuntimeException.class)
    @ResponseBody
    Object handleException(BusinessRuntimeException ex) {
        log.error("系统错误", ex);
        return ServerResponse.createByErrorCodeMessage(ex.getCode(), ex.getMsg());
    }
}
