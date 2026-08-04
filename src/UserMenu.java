import java.util.Scanner;

public class UserMenu {

    private Scanner sn;

    private AuthenticationService auth;
    private RegistrationService registro;
    private PromotionService promotion;
    private UserRepository repositorio;

    public UserMenu(
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

        boolean salir = false;

        while (!salir) {

            User usuario = auth.obtenerUsuarioActual();

            System.out.println("\n==================================");
            System.out.println(" MENÚ PRINCIPAL");
            System.out.println("==================================");
            System.out.println("Usuario : " + usuario.getNombre());
            System.out.println("Rol     : " + RolUtils.nombreRol(usuario.getRol()));
            System.out.println("==================================");
            System.out.println("1. Mi perfil");
            System.out.println("2. Administración");
            System.out.println("3. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            int opcion = Integer.parseInt(sn.nextLine());

            switch (opcion) {

                case 1:

                    System.out.println();
                    System.out.println(usuario);

                    break;

                case 2:

                    AdminMenu admin = new AdminMenu(
                            sn,
                            auth,
                            registro,
                            promotion,
                            repositorio
                    );

                    admin.iniciar();

                    break;

                case 3:

                    auth.cerrarSesion();

                    salir = true;

                    System.out.println("\nSesión cerrada.");

                    break;

                default:

                    System.out.println("\nOpción inválida.");

            }

        }

    }



}
