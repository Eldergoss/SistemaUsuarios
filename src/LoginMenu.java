import java.util.Scanner;

public class LoginMenu {

    private Scanner sn;

    private UserRepository repositorio;
    private AuthenticationService auth;
    private RegistrationService registro;
    private PromotionService promotion;

    public LoginMenu() {

        sn = new Scanner(System.in);

        repositorio = new UserRepository();

        auth = new AuthenticationService();

        registro = new RegistrationService(repositorio);

        promotion = new PromotionService(repositorio);

    }

    public void iniciar() {

        boolean salir = false;

        while (!salir) {

            System.out.println("==================================");
            System.out.println(" SISTEMA DE GESTIÓN DE USUARIOS");
            System.out.println("==================================");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            System.out.println("==================================");
            System.out.print("Seleccione una opción: ");

            int opcion = Integer.parseInt(sn.nextLine());

            switch (opcion) {

                case 1:

                    iniciarSesion();

                    break;

                case 2:

                    registrarUsuario();

                    break;

                case 3:

                    salir = true;

                    System.out.println("\nHasta luego.");

                    break;

                default:

                    System.out.println("\nOpción inválida.");

            }

            System.out.println();

        }

        sn.close();

    }

    private void iniciarSesion() {

        System.out.println("\n======= LOGIN =======");

        System.out.print("Correo: ");
        String correo = sn.nextLine();

        System.out.print("Contraseña: ");
        String contraseña = sn.nextLine();

        auth.autenticar(
                repositorio,
                correo,
                contraseña
        );

        User usuario = auth.obtenerUsuarioActual();

        if (usuario == null) {

            System.out.println("\nCorreo o contraseña incorrectos.");
            return;

        }

        System.out.println("\nBienvenido " + usuario.getNombre());

        UserMenu menu = new UserMenu(
                sn,
                auth,
                registro,
                promotion,
                repositorio
        );

        menu.iniciar();

    }

    private void registrarUsuario() {

        System.out.println("\n======= REGISTRO =======");

        System.out.print("Nombre: ");
        String nombre = sn.nextLine();

        System.out.print("Correo: ");
        String correo = sn.nextLine();

        System.out.print("Contraseña: ");
        String contraseña = sn.nextLine();

        registro.registrarUsuario(
                nombre,
                correo,
                contraseña
        );

    }

}
