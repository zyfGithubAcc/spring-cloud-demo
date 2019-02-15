package com.fufu.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class TokenAspect {

    @Pointcut("execution(* org.springframework.security.oauth2.provider.endpoint.TokenEndpoint.postAccessToken(..))")
    private void token() {
    }

    @AfterReturning(returning = "obj", pointcut = "token()")
    public void doAfterReturning(ResponseEntity<OAuth2AccessToken> obj) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 打印用户名
        System.out.println(request.getParameter("username"));
        // 打印token
        System.out.println(obj.getBody().getValue());
    }
}
