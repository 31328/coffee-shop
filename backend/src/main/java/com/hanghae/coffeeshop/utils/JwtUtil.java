package com.hanghae.coffeeshop.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Service
public class JwtUtil {

    // Step 1: Externalize configuration using @Value annotation
    // This allows changing secret key without recompiling code
    // The value comes from application.properties: jwt.secret-key=...
    @Value("${jwt.secret-key}")
    private String secretKey;

    // Step 2: Make expiration time configurable (default: 10 hours)
    // This allows different expiration times for different environments
    @Value("${jwt.expiration.hours:2}")
    private int expirationHours;

    // Step 3: Cache the signing key for better performance
    // Lazy initialization - create once, reuse many times
    private SecretKey cachedSigningKey;

    // Step 4: Extract the username (subject) from the JWT token
    // The subject claim typically contains the username/identifier
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Step 5: Extract the expiration date from the JWT token
    // Returns the "exp" claim as a Date object
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Step 6: Generic method to extract any claim from the JWT token
    // Uses Java Function interface for flexible claim extraction
    // T is generic type (String, Date, Boolean, etc.)
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        // Get all claims from the token
        final Claims claims = extractAllClaims(token);
        // Apply the resolver function to extract specific claim
        return claimsResolver.apply(claims);
    }

    // Step 7: Parse the JWT token and retrieve all claims (payload)
    // Private method for internal use - parses and validates token
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                // Verify token signature using our secret key
                .verifyWith(getSigningKey())
                .build()
                // Parse and verify the signed token
                .parseSignedClaims(token)
                // Extract the payload (claims) from verified token
                .getPayload();
    }

    // Step 8: Convert Base64 encoded secret key into SecretKey object
    // Uses double-check locking for thread-safe lazy initialization
    private SecretKey getSigningKey() {
        // First check (no synchronization)
        if (cachedSigningKey == null) {
            synchronized (this) {
                // Second check (with synchronization)
                if (cachedSigningKey == null) {
                    // Decode Base64 string to byte array
                    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
                    // Create HMAC SHA key for JWT signing
                    cachedSigningKey = Keys.hmacShaKeyFor(keyBytes);
                }
            }
        }
        return cachedSigningKey;
    }

    // Step 9: Check if the JWT token is expired
    // Private method - used internally for validation
    private Boolean isTokenExpired(String token) {
        // Compare token expiration with current date
        return extractExpiration(token).before(new Date());
    }

    // Step 10: Generate a new JWT token for a given user
    // Uses default expiration time from configuration
    public String generateToken(UserDetails userDetails) {
        // Create empty claims map (can be extended with custom claims)
        Map<String, Object> claims = new HashMap<>();
        // Generate token with username as subject
        return createToken(claims, userDetails.getUsername());
    }

    // Step 11: Generate token with custom claims
    // Allows adding additional information to JWT payload
    public String generateTokenWithClaims(UserDetails userDetails, Map<String, Object> claims) {
        // Generate token with custom claims and username
        return createToken(claims, userDetails.getUsername());
    }

    // Step 12: Core method to create JWT token
    // Private method that handles actual token creation
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                // Set custom claims if provided
                .claims(claims)
                // Set subject (username/identifier)
                .subject(subject)
                // Set issue time (current time)
                .issuedAt(new Date())
                // Set expiration time (current time + configured hours)
                .expiration(calculateExpirationDate())
                // Sign token with our secret key
                .signWith(getSigningKey())
                // Compact to final JWT string
                .compact();
    }

    // Step 13: Calculate expiration date based on configuration
    // Converts hours to milliseconds and adds to current time
    private Date calculateExpirationDate() {
        // System.currentTimeMillis() returns current time in milliseconds
        // Add expiration hours converted to milliseconds
        long expirationMillis = TimeUnit.HOURS.toMillis(expirationHours);
        return new Date(System.currentTimeMillis() + expirationMillis);
    }

    // Step 14: Validate the JWT token
    // Checks if username matches and token is not expired
    public Boolean validateToken(String token, UserDetails userDetails) {
        // Extract username from token
        final String username = extractUsername(token);
        // Return true if username matches AND token is not expired
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Step 15: Add method to validate token without UserDetails
    // Useful for scenarios where we only have the token
    public Boolean isValidToken(String token) {
        try {
            // Try to extract claims - will fail if token is invalid
            Claims claims = extractAllClaims(token);
            // Check if token has expired
            boolean expired = isTokenExpired(token);
            // Check if token has a subject (username)
            boolean hasSubject = claims.getSubject() != null && !claims.getSubject().isEmpty();
            // Token is valid if not expired and has subject
            return !expired && hasSubject;
        } catch (Exception e) {
            // Any exception means token is invalid
            return false;
        }
    }

    // Step 16: Add method to get token expiration in readable format
    // Returns remaining time until expiration in milliseconds
    public long getRemainingTimeMillis(String token) {
        try {
            Date expiration = extractExpiration(token);
            Date now = new Date();
            return expiration.getTime() - now.getTime();
        } catch (Exception e) {
            // Return 0 if token is invalid or already expired
            return 0;
        }
    }

    // Step 17: Add method to check if token will expire soon
    // Useful for token refresh logic
    public boolean willExpireSoon(String token, long minutes) {
        try {
            long remainingMillis = getRemainingTimeMillis(token);
            long warningMillis = TimeUnit.MINUTES.toMillis(minutes);
            // Return true if less than 'minutes' minutes remaining
            return remainingMillis > 0 && remainingMillis <= warningMillis;
        } catch (Exception e) {
            return false;
        }
    }
}

