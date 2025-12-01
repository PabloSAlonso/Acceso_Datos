import java.awt.Taskbar.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicios {
    private static Connection conexion;

    public static void abrirConexion(String bd, String servidor, String usuario,
            String password) {
        try {
            String url = String.format("jdbc:mariadb://%s:3306/%s", servidor, bd);
            // Establecemos la conexión con la BD
            conexion = DriverManager.getConnection(url, usuario, password);
            if (conexion != null) {
                System.out.println("Conectado a " + bd + " en " + servidor);
            } else {
                System.out.println("No conectado a " + bd + " en " + servidor);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getLocalizedMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Código error: " + e.getErrorCode());
        }
    }

    public static void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getLocalizedMessage());
        }
    }

    public static void consultarJugadores() {
        try (Statement st = conexion.createStatement()) {
            String consulta = "SELECT * FROM jugadores_celta;";
            ResultSet resultadoConsulta = st.executeQuery(consulta);
            while (resultadoConsulta.next()) {
                System.out.println(resultadoConsulta.getInt("Dorsal") + "//" + resultadoConsulta.getString(2));
            }

        } catch (SQLException e) {
            System.out.println("ERROR DE CONSULTA");
        }
    }

    public static void borrarJugador(int dorsal) {
        try (Statement st = conexion.createStatement()) {
            String consulta = "DELETE FROM jugadores_celta WHERE dorsal=" + dorsal;
            int resultado = st.executeUpdate(consulta);
            System.out.println("Numero de filas afectadas: " + resultado);
        } catch (SQLException e) {
            System.out.println("ERROR DE CONSULTA");
        }
    }

    public static void muestraMayorTreintaAños() {
        try (Statement st = conexion.createStatement()) {
            String consulta = "SELECT nombre, edad FROM jugadores_celta WHERE edad >= 30";
            ResultSet resultado = st.executeQuery(consulta);
            while (resultado.next()) {
                System.out.printf("Nombre: %s, Edad: %d\n", resultado.getString("Nombre"), resultado.getInt("Edad"));
            }
        } catch (SQLException e) {
            System.out.println("ERROR DE CONSULTA");
        }
    }

    public static void insertarManuel() {
        try (Statement st = conexion.createStatement()) {
            String consulta = "INSERT INTO jugadores_celta (dorsal, nombre, posicion, edad, nacionalidad, convocado, partidos_jugados, goles, minutos_jugados) VALUES (69, 'Manuel', 'Delantero_killer', 18, 'ESPAÑOL', 13, 13, 5, 300)";
            int resultado = st.executeUpdate(consulta);
            System.out.println("Numero de filas afectadas: " + resultado);
        } catch (SQLException e) {
            System.out.println("Erro de consulta");
        }
    }

    public static void cambiarNombre() {
        try (Statement st = conexion.createStatement()) {
            String consulta = "UPDATE jugadores_celta SET nombre = 'diego_costa' WHERE dorsal=69";
            int resultado = st.executeUpdate(consulta);
            System.out.println("Numero de filas afectadas" + resultado);
        } catch (SQLException e) {
            System.out.println("errorrrrrrrrrrrrrr");
        }
    }

    private static PreparedStatement ps = null;

    public static void consultarPS(int dorsal, int edad) throws SQLException {
        String consulta = "SELECT * FROM jugadores_celta WHERE dorsal = ? AND edad = ?" + dorsal;
        ps = conexion.prepareStatement(consulta);
        ps.setInt(1, dorsal);
        ps.setInt(2, edad);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getInt(1)+"\n");
        }
    }

    public static void main(String[] args) {
        abrirConexion("celta", "localhost", "root", "");

        consultarJugadores();
        // borrarJugador(1);
        // muestraMayorTreintaAños();
        // insertarManuel();
        cambiarNombre();
        cerrarConexion();
    }
}