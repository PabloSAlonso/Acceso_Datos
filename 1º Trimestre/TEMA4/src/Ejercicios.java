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
            System.out.println(rs.getInt(1) + "\n");
        }
    }

    public static void consultarCadena(String cadena) {
        try (Statement st = conexion.createStatement()) {
            int cont = 0;
            String consulta = String.format("SELECT * FROM alumnos WHERE nombre LIKE '%%%s%%'", cadena);
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                System.out.printf("Codigo: %d, Nombre: %s, ", rs.getInt("Codigo"), rs.getString("Nombre"));
                cont++;
            }
            System.out.println("Numero Resultados:" + cont);
        } catch (SQLException e) {
            System.out.println("Error de consulta");
        }
    }

    public static void altaAlumnos(String nombre, String apellidos, int altura, int aula) {
        try (Statement st = conexion.createStatement()) {
            String consulta = String.format(
                    "INSERT INTO alumnos (nombre, apellidos, altura, aula) VALUES ('%s', '%s', %d, %d)", nombre,
                    apellidos,
                    altura, aula);
            int resultado = st.executeUpdate(consulta);
            System.out.println("Filas afectadas:" + resultado);
        } catch (SQLException e) {
            System.out.println("Error de consulta");
        }
    }

    public static void altaAsignaturas(String nombre) {
        try (Statement st = conexion.createStatement()) {
            String consulta = String.format("INSERT INTO asignaturas (nombre) VALUES ('%s')", nombre);
            int resultado = st.executeUpdate(consulta);
            System.out.println("Filas afectadas:" + resultado);
        } catch (SQLException e) {
            System.out.println("Error de consulta");
        }
    }

    public static void bajaAlumnos(int codigo) {
        try (Statement st = conexion.createStatement()) {
            String consulta = String.format("DELETE FROM alumnos WHERE codigo = %d", codigo);
            int resultado = st.executeUpdate(consulta);
            System.out.println("Filas afectadas:" + resultado);
        } catch (SQLException e) {
            System.out.println("Error de consulta");
        }
    }

    public static void bajaAsignaturas(int codigo) {
        try (Statement st = conexion.createStatement()) {
            String consulta = String.format("DELETE FROM asignaturas WHERE codigo = %d", codigo);
            int resultado = st.executeUpdate(consulta);
            System.out.println("Filas afectadas:" + resultado);
        } catch (SQLException e) {
            System.out.println("Error de consulta");
        }
    }

    public static void modificaAlumnos(int codigo, String nombreNuevo) {
        try (Statement st = conexion.createStatement()) {
            String consulta = String.format("UPDATE alumnos SET nombre = '%s' WHERE codigo = %d", nombreNuevo, codigo);
            int resultado = st.executeUpdate(consulta);
            System.out.println("Filas afectadas:" + resultado);
        } catch (SQLException e) {
            System.out.println("Error de consulta");
        }
    }

    public static void modificaConsultas(int codigo) {
        try (Statement st = conexion.createStatement()) {

        } catch (SQLException e) {
            System.out.println("Error de consulta");
        }
    }

    public static void realizaConsultas() {
        try (Statement st = conexion.createStatement()) {

        } catch (SQLException e) {
            System.out.println("Error de consulta");
        }
    }

    public static void consultarConPatron() {
        try (Statement st = conexion.createStatement()) {

        } catch (SQLException e) {
            System.out.println("Error de consulta");
        }
    }

    public static void main(String[] args) {
        abrirConexion("add", "localhost", "root", "");

        // consultarJugadores();
        // borrarJugador(1);
        // muestraMayorTreintaAños();
        // insertarManuel();
        // cambiarNombre();

        // EJERCICIO 1
        // consultarCadena("a");
        // EJERCICIO 2
        altaAlumnos("Diego", "C.Pereira", 172, 33);
        // EJERCICIO 3

        // EJERCICIO 4

        // EJERCICIO 5

        // EJERCICIO 6

        // EJERCICIO 7

        cerrarConexion();
    }
}