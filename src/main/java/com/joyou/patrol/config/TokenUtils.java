package com.joyou.patrol.config;

import com.alibaba.fastjson.JSONObject;
import com.joyou.patrol.entity.BisUser;
import com.joyou.patrol.exception.MobileServiceException;
import com.joyou.patrol.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * <p>
 *
 * </p>
 *
 * @author yangfei
 * @since 2019-05-18
 */
@Slf4j
@Component
public class TokenUtils {
    public static TokenUtils tokenUtils;
    @Autowired
    public RedisTemplate<String, String> redisTemplate;
    @Value("${spring.application.token-name}")
    private String tokenName;

    public static String getToken() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getRequest();
        if (request == null) {
            throw new ServiceException("request不能为空");
        }
        // 从请求头中获取token
        String token = request.getHeader(TokenUtils.tokenUtils.tokenName);
        return token;
    }

    public static BisUser getLoginer() {
        String oldToken = getToken();
        String errorToken = TokenUtils.tokenUtils.redisTemplate.opsForValue().get(getToken());
        if (errorToken == null) {
            if (oldToken.endsWith("mobile")) {
                throw new MobileServiceException(202, "token 已过期，请重新登录");
            } else {
                throw new ServiceException(202, "token 已过期，请重新登录");
            }
        }
        String substring = errorToken.substring(1, errorToken.length() - 1);//去除双引号""
        String token = StringEscapeUtils.unescapeJava(substring);
        BisUser bisUser = JSONObject.toJavaObject(JSONObject.parseObject(token), BisUser.class);
        if (bisUser == null) {
            if (oldToken.endsWith("mobile")) {
                throw new MobileServiceException(202, "token 已过期，请重新登录");
            } else {
                throw new ServiceException(202, "token 已过期，请重新登录");
            }
        }
        return bisUser;
    }

    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }


    @PostConstruct
    public void init() {
        tokenUtils = this;
    }
}
