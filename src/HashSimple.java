import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class HashSimple {

    public static String hash(String texto) {

        try {

            MessageDigest md =
                    MessageDigest.getInstance("SHA-256");

            byte[] hashBytes =
                    md.digest(texto.getBytes(StandardCharsets.UTF_8));

            return HexFormat
                    .of()
                    .formatHex(hashBytes);

        } catch (NoSuchAlgorithmException e) {

            throw new RuntimeException(
                    "SHA-256 no disponible",
                    e
            );

        }

    }

}
