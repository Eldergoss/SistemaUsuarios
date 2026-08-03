
public class AuthenticationService {

    // Usuario que tiene la sesión iniciada
    private User usuarioActual;

    public boolean autenticar(UserRepository repositorio,
                              String correo,
                              String contraseña) {

        // Buscar el usuario por correo
        User usuario = repositorio.buscarPorCorreo(correo);

        // ¿Existe el usuario?
        if (usuario == null) {
            return false;
        }

        // ¿La contraseña coincide?
        if (!usuario.contraseña.equals(contraseña)) {
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
