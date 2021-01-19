package com.brycen.hrm.config.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.brycen.hrm.config.user.UserDetailsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JWTUtils {
    private static final Logger logger = LoggerFactory.getLogger(JWTUtils.class);

    @Value("${brycen.app.jwtSecret}")
    private String jwtSecret;

    @Value("${brycen.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    /**
     * [Description]: Generate JWT Token for user<br/>
     * [ Remarks ]:<br/>
     *
     * @param authentication Authentication
     * @return
     */
    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
//        List<String> roles = userPrincipal.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
        String roleCode = userPrincipal.getRoleCode();
        int companyID = userPrincipal.getCompanyID();
        Long userID = userPrincipal.getId();
        return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).claim("roles", roleCode).claim("companyid", companyID)
                .claim("userid", userID).compact();
    }

    /**
     * [Description]:<br/>
     * [ Remarks ]:<br/>
     *
     * @param token String
     * @return
     */
    /**
     * [Description]: Get Username from JWT Token<br/>
     * [ Remarks ]:<br/>
     *
     * @param token String
     * @return
     */
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * [Description]: Get Claim from JWT Token<br/>
     * [ Remarks ]:<br/>
     *
     * @param token String
     * @return
     */
    public Claims getClaimsToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    /**
     * [Description]:Check validate JWT Token<br/>
     * [ Remarks ]:<br/>
     *
     * @param authToken String
     * @return
     */
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }    
}
