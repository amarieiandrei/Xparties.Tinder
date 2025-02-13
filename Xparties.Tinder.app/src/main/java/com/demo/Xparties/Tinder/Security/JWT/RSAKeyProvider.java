package com.demo.Xparties.Tinder.Security.JWT;

import com.demo.Xparties.Tinder.Exception.JwtException.JwtPrivateKeyCanNotLoad;
import com.demo.Xparties.Tinder.Exception.JwtException.JwtPrivateKeyNotFound;
import com.demo.Xparties.Tinder.Exception.JwtException.JwtPublicKeyCanNotLoad;
import com.demo.Xparties.Tinder.Exception.JwtException.JwtPublicKeyNotFound;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAKeyProvider {
    private static final String PRIVATE_KEY_ENV = "JWT_PRIVATE_KEY";
    private static final String PUBLIC_KEY_ENV = "JWT_PUBLIC_KEY";

    public static PrivateKey getPrivateKey() {
        try {

            String privateKeyPEM = System.getenv(PRIVATE_KEY_ENV);
            if (privateKeyPEM == null) {
                throw new JwtPrivateKeyNotFound("JWT_PRIVATE_KEY environment variable is missing");
            }

            privateKeyPEM = privateKeyPEM
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s", "");

            byte[] keyBytes = Base64.getDecoder().decode(privateKeyPEM);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);

        } catch (Exception e) {
            throw new JwtPrivateKeyCanNotLoad("Could not load private key from environment");
        }
    }

    public static PublicKey getPublicKey() {
        try {

            String publicKeyPEM = System.getenv(PUBLIC_KEY_ENV);
            if (publicKeyPEM == null) {
                throw new JwtPublicKeyNotFound("JWT_PUBLIC_KEY environment variable is missing");
            }

            publicKeyPEM = publicKeyPEM.replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s", "");

            byte[] keyBytes = Base64.getDecoder().decode(publicKeyPEM);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);

        } catch (Exception e) {
            throw new JwtPublicKeyCanNotLoad("Could not load public key from environment");
        }
    }
}