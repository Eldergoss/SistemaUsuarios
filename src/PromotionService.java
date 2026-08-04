public class PromotionService {

    private UserRepository repositorio;

    public PromotionService(UserRepository repositorio) {

        this.repositorio = repositorio;

    }

    public void promoverAdministrador(String correo) {

        User usuario = repositorio.buscarPorCorreo(correo);

        if (usuario == null) {

            System.out.println("El usuario no existe.");
            return;

        }

        if (usuario.getRol() >= 10) {

            System.out.println("El usuario ya es administrador.");
            return;

        }

        usuario.setRol(10);

        repositorio.actualizar(usuario);

        System.out.println("Usuario promovido a administrador correctamente.");

    }

}
