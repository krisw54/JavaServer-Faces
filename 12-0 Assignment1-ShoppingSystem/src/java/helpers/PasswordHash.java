package helpers;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

/**
 *
 * @author Kris
 */
public class PasswordHash implements Serializable {

    public String hashThePassword(String pass) {
        String password = "";
        try {
            byte[] hash = MessageDigest.getInstance("SHA-256").digest(pass.getBytes(StandardCharsets.UTF_8));
            password = Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {

        }
        return password;
    }

}
