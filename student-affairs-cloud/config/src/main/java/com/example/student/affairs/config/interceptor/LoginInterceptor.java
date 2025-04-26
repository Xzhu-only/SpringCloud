package com.example.student.affairs.config.interceptor;

import cn.hutool.core.util.StrUtil;
import com.example.student.affairs.common.utils.ThreadLocalUtil;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    private static final List<String> excludePaths;

    static {
        excludePaths = new ArrayList<>();
        excludePaths.add("/user/login");
        excludePaths.add("/user/register");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取网关标识
        String gatewayFlag = request.getHeader("custom-header");
        if (!"gateway-service".equals(gatewayFlag)) {
            response.setStatus(401);
            response.getWriter().write("Illegal request！");
            return false;
        }
        // 获取请求的路径
        String requestURI = request.getRequestURI();
        if(isExclude(requestURI)){
            return true;
        }
        //获取登录信息
        String userIdStr = request.getHeader("user-info");
        if (StrUtil.isBlank(userIdStr)) {
            response.setStatus(401);
            response.getWriter().write("Token missing or malformed！");
            return false;
        }
        int userId = Integer.parseInt(userIdStr);
        // 将 userId 存入 ThreadLocal
        ThreadLocalUtil.set(userId);
        // 放行请求
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.remove();;
    }

    private boolean isExclude(String antPath) {
        for (String pathPattern : excludePaths) {
            if(antPathMatcher.match(pathPattern, antPath)){
                return true;
            }
        }
        return false;
    }
}
