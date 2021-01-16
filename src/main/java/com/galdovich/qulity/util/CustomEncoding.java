package com.galdovich.qulity.util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The class represents custom encoding.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class CustomEncoding {
    private static final Logger logger = Logger.getLogger(CustomEncoding.class);
    private static final String HASH_METHOD = "MD5";

    private CustomEncoding() {
    }

    /**
     * Hashes a password and represents it as a string object.
     * <p>
     * The hash contains 128 bits (16 bytes) so we specified 16 bytes in line 31, 32 were specified in line 19,
     * since usually a hash of 16 bytes is represented as a sequence of 32 hexadecimal digits.
     *
     * @param password the password
     * @return the string
     */
    public static String encodePassword(String password) {
        MessageDigest md = null;
        byte[] digest = new byte[0];

        try {
            md = MessageDigest.getInstance(HASH_METHOD);
            md.reset();
            md.update(password.getBytes());
            digest = md.digest();
        } catch (NoSuchAlgorithmException exc) {
            logger.log(Level.ERROR, "An error occurred while encrypting the password", exc);
        }
        // signum possible values: –1 - for negative numbers, 1 - for positive and 0 for an empty array
        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while (md5Hex.length() < 32) {
            md5Hex = "0" + md5Hex;
        }
        return md5Hex;
    }
}
