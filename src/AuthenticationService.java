public class AuthenticationService {

    // Usuario que tiene la sesión iniciada
    private User usuarioActual;

    public boolean autenticar(
            UserRepository repositorio,
            String correo,
            String contraseña) {

        // Buscar el usuario por correo
        User usuario = repositorio.buscarPorCorreo(correo);

        // ¿Existe el usuario?
        if (usuario == null) {
            return false;
        }

        // Hashear la contraseña introducida
        String contraseñaHash =
                PasswordUtils.hashContraseña(contraseña);

        // ¿El hash coincide con el almacenado?
        if (!usuario.contraseña.equals(contraseñaHash)) {
            return false;
        }

        // Autenticación correcta
        usuarioActual = usuario;

        return true;
    }

    public User obtenerUsuarioActual() {

        return usuarioActual;
    }

    public void cerrarSesion() {

        usuarioActual = null;
    }

    public boolean haySesion() {

        return usuarioActual != null;
    }

}
