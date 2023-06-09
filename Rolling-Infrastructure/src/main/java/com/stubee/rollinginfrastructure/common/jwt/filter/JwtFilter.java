package com.stubee.rollinginfrastructure.common.jwt.filter;

import com.stubee.rollingapplication.domain.auth.port.spi.ParseJwtPort;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final ParseJwtPort parseJwtPort;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                 FilterChain chain) throws IOException, ServletException {
        final String token = parseJwtPort.extractTokenFromRequest(request);

        if(token != null) {
            SecurityContextHolder.getContext().setAuthentication(parseJwtPort.getAuthentication(token));
        }

        chain.doFilter(request, response);
    }

}