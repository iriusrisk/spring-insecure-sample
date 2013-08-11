package net.continuumsecurity.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

final class MyDigester {

    private final MessageDigest messageDigest;


    /**
     * Create a new Digester.
     * @param algorithm the digest algorithm; for example, "SHA-1" or "SHA-256".
     * @param iterations the number of times to apply the digest algorithm to the input
     */
    public MyDigester(String algorithm) {
        try {
            messageDigest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No such hashing algorithm", e);
        }
    }

    public byte[] digest(byte[] value) {
        return messageDigest.digest(value);
    }
}
