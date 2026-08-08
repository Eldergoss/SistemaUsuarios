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

            System.out.println(
                    "El correo ya está registrado."
            );

            return null;
        }

        // ==========================
        // Hashear contraseña
        // ==========================

        String contraseñaHash =
                PasswordUtils.hashContraseña(contraseña);

        // ==========================
        // Crear usuario
        // ==========================

        User usuario = new User(
                0,
                nombre,
                correo,
                contraseñaHash,
                0
        );

        // ==========================
        // Guardar en base de datos
        // ==========================

        repositorio.agregar(usuario);

        // Devolver usuario creado
        return usuario;

    }

}
