package com.example.authapijava.interceptor;

import com.example.authapijava.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        logger.info("Processing request: {}", request.getRequestURI());

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(token);
                logger.debug("Extracted token: {}, username: {}", token, username);
            } catch (Exception e) {
                logger.error("Error extracting username from token: {}", e.getMessage());
            }
        } else {
            logger.warn("Authorization header is missing or does not contain a Bearer token");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            logger.info("Authenticating user: {}", username);

            try {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtUtil.validateToken(token, userDetails)) {
                    logger.debug("Token validated successfully for user: {}", username);

                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else {
                    logger.warn("Invalid token for user: {}", username);
                }
            } catch (Exception e) {
                logger.error("Authentication failed for user: {}", username, e);
            }
        } else if (username != null) {
            logger.debug("User: {} is already authenticated", username);
        }

        filterChain.doFilter(request, response);

        logger.info("Request processing completed for: {}", request.getRequestURI());
    }
}