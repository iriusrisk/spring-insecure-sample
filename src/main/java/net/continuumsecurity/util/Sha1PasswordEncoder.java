package net.continuumsecurity.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Sha1PasswordEncoder implements PasswordEncoder {
    protected final Log log = LogFactory.getLog(Sha1PasswordEncoder.class);

    private final MyDigester digester;

    /**
     * Constructs a standard password encoder with no additional secret value.
     */
    public Sha1PasswordEncoder() {
        this("SHA-1");
    }

    public String encode(CharSequence rawPassword) {
        return encode(rawPassword);
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        byte[] digested = decode(encodedPassword);
        return matches(digested, digest(rawPassword));
    }

    // internal helpers

    private Sha1PasswordEncoder(String algorithm) {
        this.digester = new MyDigester(algorithm);
    }

    private byte[] digest(CharSequence rawPassword) {
        byte[] digest = digester.digest(Utf8.encode(rawPassword));
        return digest;
    }

    private byte[] decode(CharSequence encodedPassword) {
        return Hex.decode(encodedPassword);
    }

    /**
     * Constant time comparison to prevent against timing attacks.
     */
    private boolean matches(byte[] expected, byte[] actual) {
        if (expected.length != actual.length) {
            return false;
        }

        int result = 0;
        for (int i = 0; i < expected.length; i++) {
            result |= expected[i] ^ actual[i];
        }
        return result == 0;
    }

    private static final int DEFAULT_ITERATIONS = 1;
}
