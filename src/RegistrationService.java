public class RegistrationService {

    // ==========================
    // Atributos
    // ==========================
    private UserRepository repositorio;

    // ==========================
    // Constructor
    // ==========================
    public RegistrationService(UserRepository repositorio) {
        this.repositorio = repositorio;
    }

    // ==========================
    // Registrar usuario
    // ==========================
    public User registrarUsuario(
            String nombre,
            String correo,
            String contraseña) {

        // Verificar si el correo ya existe
        User existente = repositorio.buscarPorCorreo(correo);

        if (existente != null) {
            System.out.println("El correo ya está registrado.");
            return null;
        }

        // Crear el objeto User
        User usuario = new User(
                0,
                nombre,
                correo,
                contraseña,
                0 // Rol de usuario
        );

        // Guardarlo en la base de datos
        repositorio.agregar(usuario);

        // Devolver el usuario creado
        return usuario;
    }

}
