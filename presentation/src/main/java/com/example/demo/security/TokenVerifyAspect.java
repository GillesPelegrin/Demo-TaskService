package com.example.demo.security;

import com.example.demo.exception.UnauthorizedException;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.File;
import java.security.interfaces.RSAPublicKey;

@Aspect
@Component
public class TokenVerifyAspect {

    @Value("${security.jwks.path}")
    private String jwksPath;

    @Before("@annotation(TokenVerify)")
    public void tokenVerify(JoinPoint joinPoint)  {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String header = request.getHeader("Authorization");

        if (header != null && (header.contains("Bearer") || header.contains("bearer"))) {

            String token = header.replace("Bearer", "");
            token = token.replace("bearer", "");
            token = token.trim();

            JWSObject jwsObject;
            try {
                jwsObject = JWSObject.parse(token);
            } catch (Exception e) {
                throw new UnauthorizedException("Could not parse jwt token");
            }

            boolean verify;
            try {
                JWKSet jwkSet = JWKSet.load(new File(jwksPath));
                RSAKey rsaKey = jwkSet.getKeys().get(0).toRSAKey();

                RSAPublicKey publicKey = (RSAPublicKey) rsaKey.toPublicKey();

                JWSVerifier verifier = new RSASSAVerifier(publicKey);

                verify = jwsObject.verify(verifier);

            } catch (Exception e) {
                verify = false;
            }

            if (!verify) {
                throw new UnauthorizedException("Jwt is not valid");
            }
            return;
        }

        throw new UnauthorizedException("Authentication token is empty or no Bearer string found in the value");
    }
}
