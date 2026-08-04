import java.util.ArrayList;
import java.util.Scanner;

public class AdminMenu {

    private Scanner sn;

    private AuthenticationService auth;
    private RegistrationService registro;
    private PromotionService promotion;
    private UserRepository repositorio;

    public AdminMenu(
            Scanner sn,
            AuthenticationService auth,
            RegistrationService registro,
            PromotionService promotion,
            UserRepository repositorio) {

        this.sn = sn;
        this.auth = auth;
        this.registro = registro;
        this.promotion = promotion;
        this.repositorio = repositorio;

    }

    public void iniciar() {

        User usuario = auth.obtenerUsuarioActual();

        if (usuario.getRol() < 10) {

            System.out.println("\nAcceso denegado.");
            return;

        }

        boolean volver = false;

        while (!volver) {

            System.out.println("\n==================================");
            System.out.println(" PANEL ADMINISTRATIVO");
            System.out.println("==================================");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Ver usuarios");

            if (usuario.getRol() >= 20) {

                System.out.println("3. Promover administrador");
                System.out.println("4. Ver administradores");
                System.out.println("5. Volver");

            } else {

                System.out.println("3. Volver");

            }

            System.out.print("Seleccione una opción: ");

            int opcion = Integer.parseInt(sn.nextLine());

            switch (opcion) {

                case 1:

                    registrarUsuario();

                    break;

                case 2:

                    verUsuarios();

                    break;

                case 3:

                    if (usuario.getRol() >= 20) {

                        promoverAdministrador();

                    } else {

                        volver = true;

                    }

                    break;

                case 4:

                    if (usuario.getRol() >= 20) {

                        verAdministradores();

                    } else {

                        System.out.println("\nOpción inválida.");

                    }

                    break;

                case 5:

                    if (usuario.getRol() >= 20) {

                        volver = true;

                    } else {

                        System.out.println("\nOpción inválida.");

                    }

                    break;

                default:

                    System.out.println("\nOpción inválida.");

            }

        }

    }

    private void registrarUsuario() {

        System.out.println("\n===== REGISTRAR USUARIO =====");

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

    private void verUsuarios() {

        System.out.println("\n===== LISTA DE USUARIOS =====");

        ArrayList<User> usuarios = repositorio.obtenerTodos();

        if (usuarios.isEmpty()) {

            System.out.println("No hay usuarios registrados.");
            return;

        }

        for (User u : usuarios) {

            System.out.println("----------------------------");
            System.out.println("ID: " + u.getId());
            System.out.println("Nombre: " + u.getNombre());
            System.out.println("Correo: " + u.getCorreo());
            System.out.println("Rol: " + RolUtils.nombreRol(u.getRol()));

        }

    }

    private void promoverAdministrador() {

        System.out.println("\n===== PROMOVER ADMINISTRADOR =====");

        System.out.print("Correo del usuario: ");
        String correo = sn.nextLine();

        promotion.promoverAdministrador(correo);

    }

    private void verAdministradores() {

        System.out.println("\n===== LISTA DE ADMINISTRADORES =====");

        ArrayList<User> admins = repositorio.obtenerAdmins();

        if (admins.isEmpty()) {

            System.out.println("No hay administradores registrados.");
            return;

        }

        for (User u : admins) {

            System.out.println("----------------------------");
            System.out.println("ID: " + u.getId());
            System.out.println("Nombre: " + u.getNombre());
            System.out.println("Correo: " + u.getCorreo());
            System.out.println("Rol: " + RolUtils.nombreRol(u.getRol()));

        }

    }



}
