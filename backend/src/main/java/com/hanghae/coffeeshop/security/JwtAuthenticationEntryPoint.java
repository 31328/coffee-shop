package com.hanghae.coffeeshop.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT Authentication Entry Point - handles unauthorized access attempts
 *
 * This component is called when:
 * 1. No authentication token is provided
 * 2. Invalid/malformed token is provided
 * 3. Expired token is provided
 * 4. User tries to access secured endpoint without proper credentials
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // Logger for tracking unauthorized access attempts
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    // ObjectMapper for converting Java objects to JSON (injected by Spring)
    private final ObjectMapper objectMapper;

    /**
     * Constructor with dependency injection
     * Spring will automatically inject the ObjectMapper bean
     */
    public JwtAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        // Step 1: Log the unauthorized access attempt for monitoring/auditing
        logUnauthorizedAccess(request, authException);

        // Step 2: Set HTTP response status to 401 Unauthorized
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // Step 3: Set response content type to JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // Step 4: Set character encoding to UTF-8
        response.setCharacterEncoding("UTF-8");

        // Step 5: Create a structured error response
        Map<String, Object> errorResponse = createErrorResponse(request, authException);

        // Step 6: Write the JSON error response to the client
        objectMapper.writeValue(response.getWriter(), errorResponse);
    }

    /**
     * Logs details about the unauthorized access attempt
     */
    private void logUnauthorizedAccess(HttpServletRequest request, AuthenticationException exception) {
        logger.warn("Unauthorized access attempt detected:");
        logger.warn("  Method: {}", request.getMethod());
        logger.warn("  URI: {}", request.getRequestURI());
        logger.warn("  Remote IP: {}", request.getRemoteAddr());
        logger.warn("  User-Agent: {}", request.getHeader("User-Agent"));
        logger.warn("  Error: {}", exception.getMessage());

        // Log additional headers for debugging if needed
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            logger.debug("  Authorization Header: {}",
                    authHeader.substring(0, Math.min(20, authHeader.length())) + "...");
        }
    }

    /**
     * Creates a structured error response with helpful information
     */
    private Map<String, Object> createErrorResponse(HttpServletRequest request,
                                                    AuthenticationException exception) {
        Map<String, Object> errorResponse = new HashMap<>();

        // Basic error information
        errorResponse.put("timestamp", LocalDateTime.now().toString());
        errorResponse.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        errorResponse.put("error", HttpStatus.UNAUTHORIZED.getReasonPhrase());

        // More helpful error message based on exception type
        errorResponse.put("message", getFriendlyErrorMessage(exception));

        // Path that was attempted to access
        errorResponse.put("path", request.getRequestURI());

        // Method used (GET, POST, etc.)
        errorResponse.put("method", request.getMethod());

        // Optional: Add documentation link or error code
        errorResponse.put("documentation", "https://api.yourdomain.com/docs/errors/401");

        return errorResponse;
    }

    /**
     * Provides user-friendly error messages based on exception type
     */
    private String getFriendlyErrorMessage(AuthenticationException exception) {
        String exceptionMessage = exception.getMessage();

        // Check common JWT-related errors
        if (exceptionMessage == null) {
            return "Authentication required. Please provide valid credentials.";
        }

        // Analyze exception message to provide more specific guidance
        if (exceptionMessage.contains("JWT") || exceptionMessage.contains("token")) {
            if (exceptionMessage.contains("expired")) {
                return "Your session has expired. Please login again.";
            } else if (exceptionMessage.contains("malformed") || exceptionMessage.contains("invalid")) {
                return "Invalid authentication token format. Please provide a valid token.";
            } else if (exceptionMessage.contains("signature")) {
                return "Token verification failed. The token may have been tampered with.";
            }
        } else if (exceptionMessage.contains("credentials")) {
            return "Invalid credentials provided. Please check your username and password.";
        } else if (exceptionMessage.contains("access denied")) {
            return "Access denied. You do not have permission to access this resource.";
        }

        // Default generic message
        return "Authentication failed. Please provide valid authentication credentials.";
    }
}
