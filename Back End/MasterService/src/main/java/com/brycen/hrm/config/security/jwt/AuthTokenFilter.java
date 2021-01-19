package com.brycen.hrm.config.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.brycen.hrm.config.user.UserDetailsServiceImpl;

import io.jsonwebtoken.Claims;

public class AuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @SuppressWarnings({ "unused" })
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null) {
                Claims claims = jwtUtils.getClaimsToken(jwt);
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                // List<String> role = (List<String>) claims.get("roles");
                String role = (String) claims.get("roles");
                int companyID = (int) claims.get("companyid");
                int userID = (int) claims.get("userid");
                UserDetails userDetails = userDetailsService.loadUserByUsername(username + "---" + companyID);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                request.setAttribute("companyID", companyID);
                request.setAttribute("userID", userID);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            
            logger.error("Cannot set user authentication: {}", e);
        }

        filterChain.doFilter(request, response);

    }

    /**
     * [Description]: Get Token from header<br/>
     * [ Remarks ]:<br/>
     *
     * @param request HttpServletRequest
     * @return String token
     */
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }
}
