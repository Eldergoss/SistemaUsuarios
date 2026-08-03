import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sn = new Scanner(System.in);

        UserRepository repositorio = new UserRepository();
        AuthenticationService auth = new AuthenticationService();
        RegistrationService registro =
                new RegistrationService(repositorio);

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

                    if (usuario != null) {

                        System.out.println(
                                "\nBienvenido "
                                        + usuario.getNombre()
                        );

                        menuPrincipalUsuario(
                                sn,
                                auth,
                                registro,
                                repositorio
                        );

                    } else {

                        System.out.println(
                                "\nCorreo o contraseña incorrectos."
                        );

                    }

                    break;

                case 2:

                    System.out.println("\n======= REGISTRO =======");

                    System.out.print("Nombre: ");
                    String nombre = sn.nextLine();

                    System.out.print("Correo: ");
                    String nuevoCorreo = sn.nextLine();

                    System.out.print("Contraseña: ");
                    String nuevaContraseña = sn.nextLine();

                    registro.registrarUsuario(
                            nombre,
                            nuevoCorreo,
                            nuevaContraseña
                    );

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

    //=========================================
    // MENÚ PRINCIPAL
    //=========================================

    public static void menuPrincipalUsuario(
            Scanner sn,
            AuthenticationService auth,
            RegistrationService registro,
            UserRepository repositorio) {

        boolean salir = false;

        while (!salir) {

            User usuario = auth.obtenerUsuarioActual();

            System.out.println("\n==================================");
            System.out.println(" MENÚ PRINCIPAL");
            System.out.println("==================================");
            System.out.println("Usuario : " + usuario.getNombre());
            System.out.println("Rol     : " + nombreRol(usuario.getRol()));
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

                    menuAdministrativo(
                            sn,
                            auth,
                            registro,
                            repositorio
                    );

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

    //=========================================
    // PANEL ADMINISTRATIVO
    //=========================================

    public static void menuAdministrativo(
            Scanner sn,
            AuthenticationService auth,
            RegistrationService registro,
            UserRepository repositorio) {

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

                    System.out.println("\n[Registrar usuario]");
                    // TODO:
                    // registro.registrarUsuario(...);

                    break;

                case 2:


                    System.out.println("\n===== LISTA DE USUARIOS =====");

                    for (User u : repositorio.obtenerTodos()) {

                        System.out.println("----------------------------");
                        System.out.println("ID: " + u.getId());
                        System.out.println("Nombre: " + u.getNombre());
                        System.out.println("Correo: " + u.getCorreo());
                        System.out.println("Rol: " + u.getRol());

                    }



                    break;

                case 3:

                    if (usuario.getRol() >= 20) {

                        System.out.println("\n[Promover administrador]");
                        // TODO:
                        // PromotionService.promoverAdministrador(...);

                    } else {

                        volver = true;

                    }

                    break;

                case 4:

                    if (usuario.getRol() >= 20) {

                        System.out.println("\n[Ver administradores]");
                        // TODO:
                        // repositorio.obtenerAdmins();

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

    //=========================================
    // NOMBRE DEL ROL
    //=========================================

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
