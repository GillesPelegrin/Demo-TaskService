package com.example.demo;

import com.example.demo.exception.InternalErrorException;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.gen.springbootserver.model.SecurityDto;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;
import java.util.Objects;

import static com.example.demo.util.DateTimeWrapper.currentDateTime;
import static java.sql.Timestamp.valueOf;

@Component
public class SecurityApplicationService {

    @Value("${security.jwks.path}")
    private String jwksPath;

    public SecurityDto getAccessToken(String username, String password) {
        if (!Objects.equals(username, "admin") || !Objects.equals(password, "admin")) {
            throw new UnauthorizedException("Username and password combo is not authorized");
        }

        Map<String, Object> claims = new JWTClaimsSet.Builder()
                .claim("name", username)
                .claim("scope", "api")
                .expirationTime(valueOf(currentDateTime().plusMinutes(10)))
                .build().getClaims();

        SecurityDto securityDto = new SecurityDto();
        securityDto.setAccessToken(signJwt(new Payload(claims)));

        return securityDto;
    }

    private String signJwt(Payload payload) {
        try {
            JWKSet jwkSet = JWKSet.load(new File(jwksPath));
            RSAKey rsaKey = jwkSet.getKeys().get(0).toRSAKey();

            RSAPublicKey publicKey = (RSAPublicKey) rsaKey.toPublicKey();
            PrivateKey privateKey = rsaKey.toPrivateKey();

            JWSObject jwsObject = new JWSObject(
                    new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(rsaKey.getKeyID()).build(),
                    payload);

            jwsObject.sign(new RSASSASigner(privateKey));
            return jwsObject.serialize();

        } catch (Exception e) {
            throw new InternalErrorException("Could not sign jwt token", e);
        }
    }
}
