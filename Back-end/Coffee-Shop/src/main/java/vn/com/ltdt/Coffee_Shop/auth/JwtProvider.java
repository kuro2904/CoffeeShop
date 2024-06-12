package vn.com.ltdt.Coffee_Shop.auth;


import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vn.com.ltdt.Coffee_Shop.user.User;

import java.text.ParseException;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secretKey;


    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        JWTClaimsSet claimSet = new JWTClaimsSet.Builder()
                .subject(authentication.getName())
                .issueTime(new Date())
                .expirationTime(new Date(new Date().getTime() + 1000 * 60 * 60 * 24))
                .claim("userId",user.getId())
                .build();

        JWSObject jwsObject = new JWSObject(header, new Payload(claimSet.toJSONObject()));
        try {
            jwsObject.sign(new MACSigner(secretKey));
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        return jwsObject.serialize();
    }

    public String getUsername(String token) throws ParseException, JOSEException {
        JWSObject jwsObject = JWSObject.parse(token);
        return jwsObject.getPayload().toString();
    }

}
