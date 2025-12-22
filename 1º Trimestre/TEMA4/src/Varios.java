import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;  

public class Varios {
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

    // 1
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

    // 2.1
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

    // 2.2
    public static void altaAsignaturas(String nombre) {
        try (Statement st = conexion.createStatement()) {
            String consulta = String.format("INSERT INTO asignaturas (nombre) VALUES ('%s')", nombre);
            int resultado = st.executeUpdate(consulta);
            System.out.println("Filas afectadas:" + resultado);
        } catch (SQLException e) {
            System.out.println("Error de consulta");
        }
    }

    // 3.1
    public static void bajaAlumnos(int codigo) {
        try (Statement st = conexion.createStatement()) {
            String consulta = String.format("DELETE FROM alumnos WHERE codigo = %d", codigo);
            int resultado = st.executeUpdate(consulta);
            System.out.println("Filas afectadas:" + resultado);
        } catch (SQLException e) {
            System.out.println("Error de consulta");
        }
    }

    // 3.2
    public static void bajaAsignaturas(int codigo) {
        try (Statement st = conexion.createStatement()) {
            String consulta = String.format("DELETE FROM asignaturas WHERE codigo = %d", codigo);
            int resultado = st.executeUpdate(consulta);
            System.out.println("Filas afectadas:" + resultado);
        } catch (SQLException e) {
            System.out.println("Error de consulta");
        }
    }

    // 4.1
    public static void modificaAlumnos(int codigo, String nombreNuevo) {
        try (Statement st = conexion.createStatement()) {
            String consulta = String.format("UPDATE alumnos SET nombre = '%s' WHERE codigo = %d", nombreNuevo, codigo);
            int resultado = st.executeUpdate(consulta);
            System.out.println("Filas afectadas:" + resultado);
        } catch (SQLException e) {
            System.out.println("Error de consulta");
        }
    }

    // 4.2
    public static void modificaAsignaturas(int codigo, String nombreNuevo) {
        try (Statement st = conexion.createStatement()) {
            String consulta = String.format("UPDATE asignaturas SET nombre = '%s' WHERE codigo = %d", nombreNuevo,
                    codigo);
            int resultado = st.executeUpdate(consulta);
            System.out.println("Filas afectadas:" + resultado);
        } catch (SQLException e) {
            System.out.println("Error de consulta");
        }
    }

    // 5.1
    public static void aulasConAlumnos() {
        try (Statement st = conexion.createStatement()) {
            String consulta = "SELECT * FROM alumnos JOIN aulas ON alumnos.aula = aulas.numero WHERE aulas.nombreAula IS NOT NULL";
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                System.out.println(rs.getString("nombreAula"));
            }
        } catch (SQLException e) {
            System.out.println("Error de consulta");
        }
    }

    // 5.2
    public static void alumnosAsignaturasAprobados() {
        try (Statement st = conexion.createStatement()) {
            String consulta = "SELECT * FROM alumnos JOIN asignaturas JOIN notas ON asignaturas.COD = notas.asignatura AND alumnos.codigo = notas.alumno WHERE NOTA >= 5";
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                System.out.printf("Nombre alumno: %s, Nombre Asignatura: %s\n", rs.getString(2), rs.getString(7));
            }
        } catch (SQLException e) {
            System.out.println("Error de consulta");
        }
    }

    // 5.3
    public static void asignaturaSinAlumnos() {
        try (Statement st = conexion.createStatement()) {
            String consulta = "SELECT asignaturas.NOMBRE FROM asignaturas WHERE NOT EXISTS (SELECT asignatura FROM notas WHERE notas.asignatura = asignaturas.COD)";
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                System.out.printf("Asignatura sin alumnos: %s\n", rs.getString("NOMBRE"));
            }
        } catch (SQLException e) {
            System.out.println("Error de consulta");
        }
    }

    // 6.1
    public static void consultarConPatron(String patron, int altura) {
        try (Statement st = conexion.createStatement()) {
            String consulta = String.format("SELECT nombre FROM alumnos WHERE nombre LIKE \"%%%s%%\" AND altura > %d",
                    patron, altura);
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                // System.out.println(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("Error de consulta");
        }
    }

    // 6.2
    public static void consultarConPatronPreparada(String patron, int altura) throws SQLException {
        String consultaPreparada = "SELECT nombre FROM alumnos WHERE nombre like ? AND altura > ?";// Con preparada no
                                                                                                   // se ponen comillas
                                                                                                   // ni %
        ps = conexion.prepareStatement(consultaPreparada);
        ps.setString(1, patron);
        ps.setInt(2, altura);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            // System.out.println(rs.getString("nombre"));
        }
    }

    // 8
    public static void añadirColumna(String tabla, String columna, String tipoDato, String propiedad) {
        try (Statement st = conexion.createStatement()) {
            String consulta = String.format("ALTER TABLE %s ADD %s %s %s", tabla, columna, tipoDato, propiedad);
            int resultado = st.executeUpdate(consulta);
            System.out.println("Numero de columnas añadidas:" + resultado);
        } catch (SQLException e) {
            System.out.println("Error de consulta");
        }
    }

    // 9 a)
    public static void ejercicio_nueve_a() {
        String nombre_driver, version_driver, url_conexion, nombre_sgbd, version_sgbd, palabras_sgbd;
        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            nombre_driver = dbmd.getDriverName();
            version_driver = dbmd.getDriverVersion();
            url_conexion = dbmd.getURL();
            nombre_sgbd = dbmd.getDatabaseProductName();
            version_sgbd = dbmd.getDatabaseProductVersion();
            palabras_sgbd = dbmd.getSQLKeywords();
            System.out.printf(
                    "Nombre Driver:%s\nVersion Driver:%s\nURL:%s\nNombre del gestor:%s\nVersion del gestor:%s\nPalabras reservadas:%s",
                    nombre_driver, version_driver, url_conexion, nombre_sgbd, version_sgbd, palabras_sgbd);
        } catch (SQLException e) {
            System.out.println("Error de SQL");
        }
    }

    // 9 b)
    public static void ejercicio_nueve_b() {
        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet rs = dbmd.getCatalogs();
            while (rs.next()) {
                System.out.println(rs.getString("TABLE_CAT"));
            }
        } catch (SQLException e) {
            // TODO: handle exception
        }
    }

    // 9 c)
    public static void ejercicio_nueve_c() {
        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet rs = dbmd.getTables("add", null, null, null);
            while (rs.next()) {
                System.out.printf("Nombre tabla:%s, Tipo de tabla:%s\n", rs.getString("TABLE_NAME"),
                        rs.getString("TABLE_TYPE"));
            }
        } catch (SQLException e) {
            // TODO: handle exception
        }
    }

    // 9 d)
    public static void ejercicio_nueve_d() {
        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet rs = dbmd.getTables("add", null, null, null);
            while (rs.next()) {
                if (rs.getString("TABLE_TYPE").equals("VIEW")) {
                    System.out.printf("Nombre tabla:%s, Tipo de tabla:%s\n", rs.getString("TABLE_NAME"),
                            rs.getString("TABLE_TYPE"));
                }
            }
        } catch (SQLException e) {
            // TODO: handle exception
        }
    }

    // 9 e)
    public static void ejercicio_nueve_e() {
        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet rs = dbmd.getCatalogs();
            ResultSet rsTablas = dbmd.getTables("add", null, null, null);
            while (rs.next()) {
                System.out.println(rs.getString("TABLE_CAT"));
            }
            while (rsTablas.next()) {
                System.out.printf("Nombre tabla:%s, Tipo de tabla:%s\n", rsTablas.getString("TABLE_NAME"),
                        rsTablas.getString("TABLE_TYPE"));
            }
        } catch (SQLException e) {
            // TODO: handle exception
        }
    }

    // 9 f)
    public static void ejercicio_nueve_f() {
        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet rs = dbmd.getProcedures("add", null, null);
            while (rs.next()) {
                System.out.println(rs.getString("PROCEDURE_NAME"));
            }
        } catch (SQLException e) {
            // TODO: handle exception
        }
    }

    // 9 g)
    public static void ejercicio_nueve_g() {

        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet rs = dbmd.getColumns("add", null, "a%", null);
            while (rs.next()) {
                System.out.printf(
                        "Posicion:%s - Tabla:%s - Nombre Columna:%s - TipoDato:%s - TamañoCol:%s - Nulos:%s - Autoincrementado: %s\n",
                        rs.getString("ORDINAL_POSITION"), rs.getString("TABLE_NAME"),
                        rs.getString("COLUMN_NAME"), rs.getString("TYPE_NAME"), rs.getString("COLUMN_SIZE"),
                        rs.getString("IS_NULLABLE"), rs.getString("IS_AUTOINCREMENT"));
            }

        } catch (SQLException e) {
            // TODO: handle exception
        }
    }

    // 9 h)
    public static void ejercicio_nueve_h() {
        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet rs = dbmd.getPrimaryKeys("add", null, null);
            System.out.println("Claves Primarias:");
            while (rs.next()) {
                System.out.println(rs.getString("COLUMN_NAME"));
            }
        } catch (SQLException e) {
            System.out.println("Error SQL");
        }
    }

    public static void ejercicio_nueve_h2(){
        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet rs = dbmd.getExportedKeys("add", null, null);
            System.out.println("Claves Foraneas");
            while (rs.next()) {
                System.out.println(rs.getString("FKCOLUMN_NAME"));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    // 10
    public static void obtenerDatosCol() {
        try (Statement st = conexion.createStatement()) {
            String consulta = "select *, nombre as non from alumnos";
            ResultSet rs = st.executeQuery(consulta);
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println("NOMBRE_COL | ALIAS_COL | NOMBRE_TIPODATO | AUTOINCREMENTADO | NULLABLE");
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.printf("%10s %10s %15s %15s %10s\n", rsmd.getColumnName(i), rsmd.getColumnLabel(i),
                        rsmd.getColumnTypeName(i), rsmd.isAutoIncrement(i), rsmd.isNullable(i));
            }
        } catch (SQLException e) {
            System.out.println("Error");
        }
    }

    // 12

    // 13

    // 15

    // 16

    public static void getInfo(String databaseName) {
        try {
            DatabaseMetaData dbmd = conexion.getMetaData(); // coge informacion de la base de datos
            ResultSet tablas = dbmd.getTables(databaseName, null, null, null);
            while (tablas.next()) {
                System.out.println(tablas.getString("TABLE_NAME") + " - " + tablas.getString("TABLE_TYPE"));
                ResultSet columnas = dbmd.getColumns(databaseName, null, tablas.getString("TABLE_NAME"), null);
                System.out.println("COLUMNAS:");
                while (columnas.next()) {
                    System.out.printf("Nombre:%s, Tipo:%s, Tamaño:%d, Nullable:%s, Autoincrementado:%s\n",
                            columnas.getString("COLUMN_NAME"), columnas.getString("TYPE_NAME"),
                            columnas.getInt("COLUMN_SIZE"), columnas.getString("IS_NULLABLE"),
                            columnas.getString("IS_AUTOINCREMENT"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error");
        }
    }

    public static void getInfoConsultas() {
        try (Statement st = conexion.createStatement()) {
            String consulta = "SELECT * FROM alumnos";
            ResultSet rs = st.executeQuery(consulta);
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println("NUM NAME TYPE");
            for (int i = 1; i < rsmd.getColumnCount(); i++) {
                System.out.printf("Indice:%d, NombreCol:%s, Tipo:%s\n", i, rsmd.getColumnName(i),
                        rsmd.getColumnTypeName(i));
            }

        } catch (SQLException e) {
            System.out.println("Error");
        }
    }

    public static void main(String[] args) throws SQLException {
        abrirConexion("add", "localhost", "root", "");

        // consultarJugadores();
        // borrarJugador(1);
        // muestraMayorTreintaAños();
        // insertarManuel();
        // cambiarNombre();

        // EJERCICIO 1
        // consultarCadena("a");
        // EJERCICIO 2
        // altaAlumnos("Diego", "C.Pereira", 172, 33);
        // altaAsignaturas(null);
        // // EJERCICIO 3
        // bajaAlumnos(0);
        // bajaAsignaturas(0);
        // // EJERCICIO 4
        // modificaAlumnos(0, null);
        // modificaAsignaturas(0, null);
        // EJERCICIO 5
        // aulasConAlumnos();
        // alumnosAsignaturasAprobados();
        // asignaturaSinAlumnos();
        // EJERCICIO 6
        // consultarConPatron("a", 10);
        // consultarConPatronPreparada("%a%", 10);
        // EJERCICIO 7
        // long inicio = System.nanoTime();
        // for (int i = 0; i < 1000; i++) {
        // consultarConPatron("a", 1); //Tarda mas la no preparada
        // }
        // long fin = System.nanoTime();
        // System.err.println(fin - inicio);
        // EJERCICIO 8
        // añadirColumna("alumnos", "Curso", "TINYINT", "");
        // EJERCICIO 9
        // ejercicio_nueve_a();
        // ejercicio_nueve_b();
        // ejercicio_nueve_c();
        // ejercicio_nueve_d();
        // ejercicio_nueve_e();
        // ejercicio_nueve_f();
        // ejercicio_nueve_g();
        // ejercicio_nueve_h();
        ejercicio_nueve_h2(); //Da error, hay que ver ahi
        // EJERCICIO 10
        // obtenerDatosCol();
        // INFORMACION DE LA BD
        // getInfo("add");
        // getInfoConsultas();
        cerrarConexion();
    }
}