package com.kbm.instagram.security;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.security.GeneralSecurityException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
class JwtAuthenticationFilter extends GenericFilterBean {

    private JwtTokenProvider jwtTokenProvider;

    //request시 들어오는 jwt 필터링 필터체이닝.
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
            System.out.println(token);
            if (token != null) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            filterChain.doFilter(request, response);
        } catch (IllegalStateException e){
            e.printStackTrace();
            ((HttpServletResponse) response).sendError(401, "Token is null");
        } catch (GeneralSecurityException e) {
            ((HttpServletResponse) response).sendError(401, "Token is not valid");
            e.printStackTrace();
        } catch (NullPointerException e) {
            ((HttpServletResponse) response).sendError(401, "User not found");
            e.printStackTrace();
        }
    }
}