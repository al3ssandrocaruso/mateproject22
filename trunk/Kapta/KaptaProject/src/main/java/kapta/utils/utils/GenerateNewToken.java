package kapta.utils.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class GenerateNewToken {
    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe
    public static String generateToken() {
        byte[] randomBytes = new byte[8];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes).substring(0,4);
    }
}
