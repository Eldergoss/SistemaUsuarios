// Clase Main
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // ==========================
        // Inicializar sistema
        // ==========================
        Scanner sn = new Scanner(System.in);

        UserRepository repositorio = new UserRepository();

        AuthenticationService auth = new AuthenticationService();

        boolean salir = false;

        while (!salir) {

            System.out.println("====================================");
            System.out.println("         SISTEMA DE LOGIN");
            System.out.println("====================================");

            System.out.print("Correo      : ");
            String correo = sn.nextLine();

            System.out.print("Contraseña  : ");
            String contraseña = sn.nextLine();

            System.out.println();

            // Intentar autenticación
            boolean autenticado = auth.autenticar(
                repositorio,
                correo,
                contraseña
            );

            if (autenticado) {

                System.out.println("Inicio de sesión correcto.");

                User usuario = auth.obtenerUsuarioActual();

                System.out.println("Bienvenido " + usuario.nombre);

                salir = true;

            } else {

                System.out.println("Correo o contraseña incorrectos.\n");

            }

        }

        sn.close();

    }

}
