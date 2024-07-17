package vn.com.ltdt.Coffee_Shop.auth;


import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import vn.com.ltdt.Coffee_Shop.user.User;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        JWTClaimsSet claimSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issueTime(new Date())
                .expirationTime(new Date(new Date().getTime() + 1000 * 60 * 60 * 24))
                .claim("userId",user.getId())
                .claim("role",user.getAuthorities())
                .build();

        JWSObject jwsObject = new JWSObject(header, new Payload(claimSet.toJSONObject()));
        try {
            jwsObject.sign(new MACSigner(secretKey));
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        return jwsObject.serialize();
    }

    public LocalDateTime getExpiredDate(String token) throws ParseException {
        JWSObject jwsObject = JWSObject.parse(token);
        Payload payload = jwsObject.getPayload();
        Map<String, Object> jsonObject = payload.toJSONObject();
        long exp = ((Number) jsonObject.get("exp")).longValue();
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(exp), ZoneId.systemDefault());
    }

    public String getUsername(String token) throws ParseException, JOSEException {
        JWSObject jwsObject = JWSObject.parse(token);
        Payload payload = jwsObject.getPayload();
        Map<String, Object> jsonObject = payload.toJSONObject();
        return jsonObject.get("sub").toString();
    }

}
