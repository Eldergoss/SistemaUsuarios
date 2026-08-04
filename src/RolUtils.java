public class RolUtils {

    private RolUtils() {
        // Evita que se instancie esta clase.
    }

    public static String nombreRol(int rol) {

        switch (rol) {

            case 0:
                return "Usuario";

            case 10:
                return "Administrador";

            case 20:
                return "Super Administrador";

            default:
                return "Desconocido";

        }

    }

}
