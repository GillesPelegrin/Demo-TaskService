package com.example.demo;

import com.example.demo.exception.InternalErrorException;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.util.DateTimeWrapper;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import static com.example.demo.testconstant.SecurityDtoTestConstant.getSecurityDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SecurityApplicationServiceTest {


    /*
        This test is used to create a new RSA jwk
        Is only used for generating a new jwk
     */
    @Test
    @Disabled
    void generateJwk() throws NoSuchAlgorithmException {

        // Generate the RSA key pair
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(2048);
        KeyPair keyPair = gen.generateKeyPair();

        // Convert to JWK format
        JWK jwk = new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
                .privateKey((RSAPrivateKey) keyPair.getPrivate())
                .keyUse(KeyUse.SIGNATURE)
                .keyID(UUID.randomUUID().toString())
                .issueTime(new Date())
                .build();

        System.out.println(jwk.toString());
    }

    @AfterEach
    void tearDown() {
        DateTimeWrapper.reset();
    }

    @Test
    void getAccessToken() {
        DateTimeWrapper.setFixed(LocalDate.of(2020, 2, 2));

        SecurityApplicationService securityApplicationService = new SecurityApplicationService();
        ReflectionTestUtils.setField(securityApplicationService, "jwksPath", "../local-dev/signature/jwk.json");

        assertThat(securityApplicationService.getAccessToken("admin", "admin"))
                .isEqualTo(getSecurityDTO("eyJraWQiOiI0NjZlZWQ4MS0yYmE4LTQ2OTMtODYxYi1lZDA2NGViZGU1MzQiLCJhbGciOiJSUzI1NiJ9.eyJuYW1lIjoiYWRtaW4iLCJleHAiOiJGZWIgMiwgMjAyMCwgMTI6MTA6MDAgQU0iLCJzY29wZSI6ImFwaSJ9.sWXAq7U9HkcRv9Y9hPtzU_Qbilf1nijQKiDZyW6C2cAbMyZPnuYndN9UuX3iBtu93lyZpGuHwZvyICBWnCucwhZUwDg5xkZSe0gREfrbtr9So7gnfBmsUA-_Ial0i2P_hFhsVzLywhNOATDu7k34aQiNmmv3yrA4_UrdhIcMKMA7IPoxjWrrUMscYuEEKDhWoC_-vHE3HkYX2M2zpDjRREq2dX7Mz1ulC4tF6xHW-czYs4NawHlrvPnqv7DuojDTpYwm8Ov6s_er4pH4QFHuGoynzSx9gpJfQJnV9PUT5Ldz8a7b282Ku_uopd42RR9C__DLhX4eUalyAOg2I3e09w"));
    }

    @Test
    void getAccessToken_unauthorised() {
        SecurityApplicationService securityApplicationService = new SecurityApplicationService();

        assertThatThrownBy(() -> securityApplicationService.getAccessToken("admin", "wrongPassword"))
                .isInstanceOf(UnauthorizedException.class)
                .hasMessage("Username and password combo is not authorized");
    }

    @Test
    void getAccessToken_InternalException() {
        SecurityApplicationService securityApplicationService = new SecurityApplicationService();
        ReflectionTestUtils.setField(securityApplicationService, "jwksPath", "../wrong-path");

        assertThatThrownBy(() -> securityApplicationService.getAccessToken("admin", "admin"))
                .isInstanceOf(InternalErrorException.class)
                .hasMessage("Could not sign jwt token");

    }

    @Test
    void name() throws JOSEException, ParseException {
        // RSA signatures require a public and private RSA key pair,
// the public key must be made known to the JWS recipient to
// allow the signatures to be verified
        RSAKey rsaJWK = new RSAKeyGenerator(2048)
                .keyID("123")
                .generate();
        RSAKey rsaPublicJWK = rsaJWK.toPublicJWK();

// Create RSA-signer with the private key
        JWSSigner signer = new RSASSASigner(rsaJWK);

// Prepare JWS object with simple string as payload
        JWSObject jwsObject = new JWSObject(
                new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(rsaJWK.getKeyID()).build(),
                new Payload("In RSA we trust!"));

// Compute the RSA signature
        jwsObject.sign(signer);

// To serialize to compact form, produces something like
// eyJhbGciOiJSUzI1NiJ9.SW4gUlNBIHdlIHRydXN0IQ.IRMQENi4nJyp4er2L
// mZq3ivwoAjqa1uUkSBKFIX7ATndFF5ivnt-m8uApHO4kfIFOrW7w2Ezmlg3Qd
// maXlS9DhN0nUk_hGI3amEjkKd0BWYCB8vfUbUv0XGjQip78AI4z1PrFRNidm7
// -jPDm5Iq0SZnjKjCNS5Q15fokXZc8u0A
        String s = jwsObject.serialize();

// To parse the JWS and verify it, e.g. on client-side
        jwsObject = JWSObject.parse(s);

        JWSVerifier verifier = new RSASSAVerifier(rsaPublicJWK);

        assertTrue(jwsObject.verify(verifier));

        assertEquals("In RSA we trust!", jwsObject.getPayload().toString());

    }


    @Test
    void name2() throws IOException, ParseException {

        JWKSet localKeys = JWKSet.load(new File("../local-dev/signature/jwk.json"));
//        int a = 5;

//        File file = new File("../local-dev/signature/jwk.json");
//        for(String fileNames : file.list()) System.out.println(fileNames);
    }


    @Test
    void name3() throws IOException, ParseException, JOSEException {

        JWKSet localKeys = JWKSet.load(new File("../local-dev/signature/jwk.json"));
        JWK jwk1 = localKeys.getKeys().get(0);
        RSAKey rsaKey = jwk1.toRSAKey();
        RSAPublicKey publicKey = (RSAPublicKey) rsaKey.toPublicKey();
        PrivateKey privateKey = rsaKey.toPrivateKey();

        JWSSigner signer = new RSASSASigner(privateKey);

        JWSObject jwsObject = new JWSObject(
                new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(rsaKey.getKeyID()).build(),
                new Payload("In RSA we trust!"));

        jwsObject.sign(signer);

// To serialize to compact form, produces something like
// eyJhbGciOiJSUzI1NiJ9.SW4gUlNBIHdlIHRydXN0IQ.IRMQENi4nJyp4er2L
// mZq3ivwoAjqa1uUkSBKFIX7ATndFF5ivnt-m8uApHO4kfIFOrW7w2Ezmlg3Qd
// maXlS9DhN0nUk_hGI3amEjkKd0BWYCB8vfUbUv0XGjQip78AI4z1PrFRNidm7
// -jPDm5Iq0SZnjKjCNS5Q15fokXZc8u0A
        String s = jwsObject.serialize();

// To parse the JWS and verify it, e.g. on client-side
        jwsObject = JWSObject.parse(s);

        JWSVerifier verifier = new RSASSAVerifier(publicKey);

        assertTrue(jwsObject.verify(verifier));

        assertEquals("In RSA we trust!", jwsObject.getPayload().toString());
    }


}