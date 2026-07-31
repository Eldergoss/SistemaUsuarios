import java.sql.*;
import java.util.ArrayList;

public class UserRepository {

    // ==========================
    // Guardar usuario
    // ==========================
    public void agregar(User usuario) {

        String sql = "INSERT INTO users(nombre, correo, contraseña, rol) VALUES(?, ?, ?, ?)";

        try (
            //creamos el objeto conexion que contiene database clase y conectar
            //como metodo.
            Connection conexion = Database.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql)
        ) {

            sentencia.setString(1, usuario.nombre);
            sentencia.setString(2, usuario.correo);
            sentencia.setString(3, usuario.contraseña);
            sentencia.setInt(4, usuario.rol);

            sentencia.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    // ==========================
    // Eliminar usuario
    // ==========================
    public void eliminar(User usuario) {

        String sql = "DELETE FROM users WHERE id = ?";

        try (
            Connection conexion = Database.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql)
        ) {

            sentencia.setInt(1, usuario.id);

            sentencia.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    // ==========================
    // Actualizar usuario
    // ==========================
    public void actualizar(User usuario) {

        String sql = """
            UPDATE users
            SET nombre = ?, correo = ?, contraseña = ?, rol = ?
            WHERE id = ?
            """;

        try (
            Connection conexion = Database.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql)
        ) {

            sentencia.setString(1, usuario.nombre);
            sentencia.setString(2, usuario.correo);
            sentencia.setString(3, usuario.contraseña);
            sentencia.setInt(4, usuario.rol);
            sentencia.setInt(5, usuario.id);

            sentencia.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    // ==========================
    // Buscar por ID
    // ==========================
    public User buscarPorId(int id) {

        String sql = "SELECT * FROM users WHERE id = ?";

        try (
            Connection conexion = Database.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql)
        ) {

            sentencia.setInt(1, id);

            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {

                return new User(

                    resultado.getInt("id"),
                    resultado.getString("nombre"),
                    resultado.getString("correo"),
                    resultado.getString("contraseña"),
                    resultado.getInt("rol")

                );

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;

    }

    // ==========================
    // Buscar por correo
    // ==========================
    public User buscarPorCorreo(String correo) {

        String sql = "SELECT * FROM users WHERE correo = ?";

        try (
            Connection conexion = Database.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql)
        ) {

            sentencia.setString(1, correo);

            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {

                return new User(

                    resultado.getInt("id"),
                    resultado.getString("nombre"),
                    resultado.getString("correo"),
                    resultado.getString("contraseña"),
                    resultado.getInt("rol")

                );

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;

    }

    // ==========================
    // Obtener todos los usuarios
    // ==========================
    public ArrayList<User> obtenerTodos() {

        ArrayList<User> usuarios = new ArrayList<>();

        String sql = "SELECT * FROM users";

        try (
            Connection conexion = Database.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery()
        ) {

            while (resultado.next()) {

                User usuario = new User(

                    resultado.getInt("id"),
                    resultado.getString("nombre"),
                    resultado.getString("correo"),
                    resultado.getString("contraseña"),
                    resultado.getInt("rol")

                );

                usuarios.add(usuario);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return usuarios;

    }

    // ==========================
    // Obtener solo administradores
    // ==========================
    public ArrayList<User> obtenerAdmins() {

        ArrayList<User> admins = new ArrayList<>();

        String sql = "SELECT * FROM users WHERE rol = 1";

        try (
            Connection conexion = Database.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery()
        ) {

            while (resultado.next()) {

                User usuario = new User(

                    resultado.getInt("id"),
                    resultado.getString("nombre"),
                    resultado.getString("correo"),
                    resultado.getString("contraseña"),
                    resultado.getInt("rol")

                );

                admins.add(usuario);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return admins;

    }

}
