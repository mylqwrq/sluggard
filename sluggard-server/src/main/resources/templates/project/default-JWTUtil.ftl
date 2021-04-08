package ${project.basePackage}.core.util;

import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

public class JWTUtil {

    private JWTUtil() {
    }

    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public static String createToken(Map<String, Object> claims, String secretKey, int expiration) {
        long nowMillis = System.currentTimeMillis();
        return Jwts.builder()
                .setExpiration(new Date(nowMillis + expiration))
                .setClaims(claims)
                .signWith(SIGNATURE_ALGORITHM, secretKey)
                .compact();
    }

    public static Map<String, Object> getClaims(String token, String secretKey) {
        if (token == null || token.isEmpty()) {
            return null;
        }
        try {
            return parseToken(token, secretKey);
        } catch (ExpiredJwtException | SignatureException | MalformedJwtException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Map<String, Object> parseToken(String token, String secretKey) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
