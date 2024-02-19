package net.saravana.config;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final UserAuthProvider userAuthProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
    String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        String requestUri = request.getRequestURI();

        // Skip the filter for login and signup APIs
        if (requestUri.equals("/api/v1/login") || requestUri.equals("/api/v1/signup")) {
            filterChain.doFilter(request, response);
            return;
        }

    if(header != null){
        String[] elements = header.split(" ");

        if (elements.length == 2 && "Bearer".equals(elements[0])){
            try {
                SecurityContextHolder.getContext().setAuthentication(
                        userAuthProvider.validateToken(elements[1])
                );
            }catch (RuntimeException e){
                SecurityContextHolder.clearContext();
                throw e;
            }
        }
    }
    // Add logging statements
    System.out.println("Request URI: " + request.getRequestURI());
    System.out.println("Authentication: " + SecurityContextHolder.getContext().getAuthentication());
    filterChain.doFilter(request, response);
    }
}
