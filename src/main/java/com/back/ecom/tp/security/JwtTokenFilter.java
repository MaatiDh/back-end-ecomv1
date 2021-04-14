package com.back.ecom.tp.security;

import com.back.ecom.tp.dto.error.RestError;
import com.back.ecom.tp.exception.FunctionalErrorCode;
import com.back.ecom.tp.exception.InvalidJwtAuthenticationException;
import com.back.ecom.tp.service.UtilisateurService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.back.ecom.tp.util.MapperUtils.mapToJson;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

public class JwtTokenFilter extends GenericFilterBean {
    private JwtTokenProvider jwtTokenProvider;
    private UtilisateurService utilisateurService;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider, UtilisateurService utilisateurService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);


            }
        } catch (InvalidJwtAuthenticationException e) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) res;

            httpServletResponse.setStatus(SC_UNAUTHORIZED);
            httpServletResponse.setHeader("content-type", MediaType.APPLICATION_JSON_VALUE);
            httpServletResponse.getWriter().print(mapToJson(RestError.builder()
                    .code(FunctionalErrorCode.INVALID_JWT_TOKEN.getCode())
                    .message(e.getMessage())
                    .build()));

            return;
        }
        filterChain.doFilter(req, res);
    }
}
