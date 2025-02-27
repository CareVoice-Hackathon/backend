package com.team7.carevoice.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filter to log details of incoming requests.
 * Logs the HTTP method, URI, and remote address for each request.
 * Handles errors during filtering and logs them for debugging purposes.
 */
@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    /**
     * Filters incoming requests and logs details such as HTTP method, URI, and remote address.
     * It also handles potential exceptions that could arise during filtering.
     *
     * @param request     the HTTP request to be processed
     * @param response    the HTTP response to be processed
     * @param filterChain the filter chain that further processes the request and response
     * @throws ServletException if a servlet error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String method = request.getMethod();
        String uri = request.getRequestURI();
        String remoteAddr = request.getRemoteAddr();

        try {
            // Log request details
            logger.info("Incoming request - Method: {}, URI: {}, Remote address: {}", method, uri, remoteAddr);

            // Continue the filter chain
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            // Log error details for debugging
            logger.error("Error while processing request - Method: {}, URI: {}, Remote address: {}, Error: {}",
                    method, uri, remoteAddr, e.getMessage(), e);

            // Optionally handle specific exceptions or set custom error responses
            if (!response.isCommitted()) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 Internal Server Error
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"An error occurred while processing the request.\"}");
            }
        }
    }
}
