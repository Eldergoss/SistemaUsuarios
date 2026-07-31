import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    // Ruta de la base de datos
    private static final String URL = "jdbc:sqlite:roles.db";

    // Constructor privado para evitar crear objetos Database
    private Database() {
    }

    // Obtener conexión
    public static Connection conectar() {

        try {

            return DriverManager.getConnection(URL);

        } catch (SQLException e) {

            throw new RuntimeException("Error al conectar con la base de datos.", e);

        }

    }

}
