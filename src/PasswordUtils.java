public class PasswordUtils {

    private static final String PEPPER =
            "MiPepperSecreto_2026";

    private PasswordUtils() {
    }

    public static String prepararContraseña(String contraseña) {

        return contraseña + PEPPER;

    }

    public static String hashContraseña(String contraseña) {

        String contraseñaConPepper =
                prepararContraseña(contraseña);

        return HashSimple.hash(contraseñaConPepper);

    }

}
