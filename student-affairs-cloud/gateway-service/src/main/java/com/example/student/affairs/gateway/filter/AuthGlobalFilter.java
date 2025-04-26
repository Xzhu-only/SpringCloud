package com.example.student.affairs.gateway.filter;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.student.affairs.common.utils.JwtUtil;
import com.example.student.affairs.gateway.auth.AuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
@EnableConfigurationProperties(AuthProperties.class)
@RequiredArgsConstructor
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private final AuthProperties authProperties;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.获取Request
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 2.判断是否不需要拦截
        if(isExclude(request.getPath().toString())){
            // 无需拦截，直接放行
            exchange.mutate().request(builder -> builder.header("custom-header", "gateway-service")).build();
            return chain.filter(exchange);
        }
        // 3.获取请求头中的token
        String token=null;
        List<String> authorization = request.getHeaders().get("Authorization");
        if(authorization != null && !authorization.isEmpty()){
            token = authorization.get(0);
        }
        // 4.校验并解析token
        if (token == null) {
            response.setRawStatusCode(401);
            return response.setComplete();
        }
        Integer userId = null;
        try {
            // 解析 token，检查是否有效
            DecodedJWT decodedJWT = JwtUtil.decodeToken(token);

            // 获取 JWT 负载中的信息
            Map<String, Claim> claims = decodedJWT.getClaims();
            userId = claims.get("userId").asInt();  // 获取 userId

            // 判断 token 是否过期
            if (JwtUtil.isTokenExpired(token)) {
                response.setRawStatusCode(401);
                return response.setComplete();
            }
        } catch (Exception e) {
            //http响应状态码为401
            //如果无效，拦截
            response.setRawStatusCode(401);
            return response.setComplete();
        }

        // 5.如果有效，向service传递userId
        String userIdStr = userId.toString();
        exchange.mutate().request(builder -> builder.header("user-info", userIdStr)
                                .header("custom-header", "gateway-service")).build();
        // 6.放行
        return chain.filter(exchange);
    }

    private boolean isExclude(String antPath) {
        for (String pathPattern : authProperties.getExcludePaths()) {
            if(antPathMatcher.match(pathPattern, antPath)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}