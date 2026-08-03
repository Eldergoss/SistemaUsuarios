

public class SuperAdmin extends User {

    public SuperAdmin(int id, String nombre, String correo,
                      String contraseña) {

        super(id, nombre, correo, contraseña, 2);

    }

    // Promover usuario a administrador
    public void promoverAdmin(UserRepository repositorio,
                              User usuario) {

        usuario.rol = 1;

        repositorio.actualizar(usuario);

        System.out.println(usuario.nombre +
                " fue promovido a Administrador.");

    }

    // Degradar administrador a usuario
    public void degradarAdmin(UserRepository repositorio,
                              User usuario) {

        usuario.rol = 0;

        repositorio.actualizar(usuario);

        System.out.println(usuario.nombre +
                " ahora es Usuario.");

    }

    // Ver administradores
    public void verAdmins(UserRepository repositorio) {

        System.out.println("=== Lista de Administradores ===");

        for (User usuario : repositorio.obtenerAdmins()) {

            System.out.println(usuario.nombre);

        }

    }

}
