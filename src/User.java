

public class User {

    protected int id;
    protected String nombre;
    protected String correo;
    protected String contraseña;
    protected int rol;

    public User(int id, String nombre, String correo,
                String contraseña, int rol) {

        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.rol = rol;

    }

}
