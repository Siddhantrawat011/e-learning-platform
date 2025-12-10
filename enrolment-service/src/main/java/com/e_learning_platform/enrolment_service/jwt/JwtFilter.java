package com.e_learning_platform.enrolment_service.jwt;

import com.e_learning_platform.enrolment_service.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        String username;
        String jwtToken;

        // 1. check jwt existence and its format correctness
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        // 2. extract token and username
        jwtToken = authHeader.substring(7);
        username = jwtService.extractUsernameFromToken(jwtToken);

        // 3. validate jwt and set it in security context
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            List<String> roles = jwtService.extractRoles(jwtToken);
            List<SimpleGrantedAuthority> authorities = roles.stream()
                    // ensure roles are prefixed with ROLE_ if your app uses hasRole("ADMIN") checks
                    .map(r -> r.startsWith("ROLE_") ? new SimpleGrantedAuthority(r) : new SimpleGrantedAuthority("ROLE_" + r))
                    .toList();

            if(jwtService.isTokenValid(jwtToken)){
                UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                        username,
                        "", // no password required
                        authorities
                );
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // update the security context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
