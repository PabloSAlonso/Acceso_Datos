package ejem1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.checkerframework.checker.units.qual.s;

import jakarta.annotation.Generated;
import jakarta.validation.metadata.ReturnValueDescriptor;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/deportistas")
public class GestionaDeportistas {

    private static final String URL = "jdbc:mariadb://localhost:3306/ad_tema6";
    private static final String USER = "root";
    private static final String PASS = "";
    ArrayList<Deportista> lDeportistas = new ArrayList<>();
    Deportista deportista;

    String ruta_driver = "org.mariadb.jdbc.Driver";

    public void llamadaDriver(String ruta) throws ClassNotFoundException {
        Class.forName(ruta);
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response obtenerTodos() {
        try {
            llamadaDriver(ruta_driver);
            try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM deportistas");
                while (rs.next()) {
                    lDeportistas.add(new Deportista(rs.getInt("id"), rs.getString("nombre"), rs.getBoolean("activo"),
                            rs.getString("deporte"), rs.getString("genero")));
                }
                return Response.ok(lDeportistas).build();

            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
            }

        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
        }
    }

    @Path("/android")
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response subirDeportistaAndroid(Deportista d) throws ClassNotFoundException {
        try {
            llamadaDriver(ruta_driver);
            Connection conexion = DriverManager.getConnection(URL, USER, PASS);
            Statement st = conexion.createStatement();
            st.executeUpdate(
                    String.format("INSERT INTO deportistas (nombre, deporte) VALUES ('%s', '%s')", d.getNombre(),
                            d.getDeporte()));

            return Response.ok("subido correctamente").build();

        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
        }
    }

    // Ejercicio 4.3
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarJugador(@PathParam("id") int id) {
        try {
            llamadaDriver(ruta_driver);
            try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM deportistas WHERE id = " + id);
                while (rs.next()) {
                    deportista = new Deportista(rs.getInt("id"), rs.getString("nombre"), rs.getBoolean("activo"),
                            rs.getString("genero"), rs.getString("deporte"));
                }
                return Response.ok(deportista).build();

            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
            }

        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
        }
    }

    // Ejercicio 4.4
    @Path("/deporte/{nombreDeporte}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response porDeporte(@PathParam("nombreDeporte") String nombre_deporte) {
        try {
            llamadaDriver(nombre_deporte);
            try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM deportistas WHERE deporte = " + nombre_deporte);
                while (rs.next()) {
                    lDeportistas.add(new Deportista(rs.getInt("id"), rs.getString("nombre"), rs.getBoolean("activo"),
                            rs.getString("genero"), rs.getString("deporte")));
                }
                return Response.ok(lDeportistas).build();
            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
        }
    }

    // Ejercicio 4.5
    @Path("/activos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarActivos() {
        try {
            llamadaDriver(ruta_driver);
            try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM deportistas WHERE activo = " + 1);
                while (rs.next()) {
                    lDeportistas.add(new Deportista(rs.getInt("id"), rs.getString("nombre"), rs.getBoolean("activo"),
                            rs.getString("genero"), rs.getString("deporte")));
                }
                return Response.ok(lDeportistas).build();
            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
        }
    }

    // Ejercicio 4.6
    @Path("/retirados")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarRetirados() {
        try {
            llamadaDriver(ruta_driver);
            try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM deportistas WHERE activo = " + 0);
                while (rs.next()) {
                    lDeportistas.add(new Deportista(rs.getInt("id"), rs.getString("nombre"), rs.getBoolean("activo"),
                            rs.getString("genero"), rs.getString("deporte")));
                }
                return Response.ok(lDeportistas).build();
            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
        }
    }

    // Ejercicio 4.7
    @Path("/masculinos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarMasculinos() {
        try {
            llamadaDriver(ruta_driver);
            try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM deportistas WHERE genero = " + "Masculino");
                while (rs.next()) {
                    lDeportistas.add(new Deportista(rs.getInt("id"), rs.getString("nombre"), rs.getBoolean("activo"),
                            rs.getString("genero"), rs.getString("deporte")));
                }
                return Response.ok(lDeportistas).build();
            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
        }
    }

    // Ejercicio 4.8
    @Path("/femeninos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarFemeninos() {
        try {
            llamadaDriver(ruta_driver);
            try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM deportistas WHERE genero = " + "Femenino");
                while (rs.next()) {
                    lDeportistas.add(new Deportista(rs.getInt("id"), rs.getString("nombre"), rs.getBoolean("activo"),
                            rs.getString("genero"), rs.getString("deporte")));
                }
                return Response.ok(lDeportistas).build();
            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
        }
    }

    // Ejercicio 4.9
    @Path("/xg")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPorGenero() {
        ArrayList<Deportista> listadoGenero = new ArrayList<>();
        try {
            llamadaDriver(ruta_driver);
            try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM deportistas");
                while (rs.next()) {
                    lDeportistas.add(new Deportista(rs.getInt("id"), rs.getString("nombre"),
                            rs.getBoolean("activo"),
                            rs.getString("genero"), rs.getString("deporte")));
                }
                for (Deportista deportista : lDeportistas) {
                    if (deportista.getGenero().equals("Masculino")) {
                        listadoGenero.add(deportista);
                    }
                }
                for (Deportista deportista : lDeportistas) {
                    if (deportista.getGenero().equals("Femenino")) {
                        listadoGenero.add(deportista);
                    }
                }
                return Response.ok(listadoGenero).build();

            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encontrado el driver").build();
        }
    }

    // Ejercicio 4.10
    @Path("/deporte/{nombreDeporte}/activos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response activosPorDeporte(@PathParam("nombreDeporte") String nombreDeporte) {
        try {
            llamadaDriver(nombreDeporte);
            try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery(
                        String.format("SELECT * FROM deportistas WHERE deporte = %s AND activo = 1", nombreDeporte));
                while (rs.next()) {
                    lDeportistas.add(new Deportista(rs.getInt("id"), rs.getString("nombre"),
                            rs.getBoolean("activo"),
                            rs.getString("genero"), rs.getString("deporte")));
                }
                return Response.ok(lDeportistas).build();
            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error sql").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encontrado el driver").build();
        }
    }

    // Ejercicio 4.11
    @Path("/sdepor")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response numeroDistintos() {
        try {
            llamadaDriver(ruta_driver);
            try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery("SELECT COUNT(DISTINCT(id)) FROM deportistas");
                int contador = 0;
                while (rs.next()) {
                    contador++;
                }
                return Response.ok(contador).build();
            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encontrado el driver").build();
        }
    }

    ArrayList<String> deportes = new ArrayList<>();

    // Ejercicio 4.12
    @Path("/deportes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarDeportes() {
        try {
            llamadaDriver(ruta_driver);
            try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery("SELECT Distinct(deporte) FROM deportistas");
                while (rs.next()) {
                    deportes.add(rs.getString("deporte"));
                }
                return Response.ok(deportes).build();
            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encontrado el driver").build();
        }
    }

    // Ejercicio 4.13
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearDeportista(Deportista d) {
        try {
            llamadaDriver(ruta_driver);
            try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
                Statement st = connection.createStatement();
                st.executeUpdate(String.format(
                        "INSERT INTO deportistas(nombre, activo, genero, deporte) VALUES (%s, %s, %s, %s)",
                        d.getNombre(), d.isActivo(), d.getGenero(), d.getDeporte()));
                return Response.ok("Subido bien :/").build();
            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encontrado el driver").build();
        }
    }

    // Ejercicio 4.14
    @Path("/form")
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response crearConFormulario(@FormParam("nombre") String nombre, @FormParam("activo") boolean activo,
            @FormParam("genero") String genero, @FormParam("deporte") String deporte) {
        try {
            llamadaDriver(ruta_driver);
            try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
                Statement st = connection.createStatement();
                st.executeUpdate(String.format(
                        "INSERT INTO deportistas(nombre, activo, genero, deporte) VALUES (%s, %s, %s, %s)",
                        nombre, activo, genero, deporte));
                return Response.ok("Insertado con exito").build();

            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encontrado el driver").build();
        }
    }

    // Ejercicio 4.15
    @Path("/adds")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearDeportistas(ArrayList<Deportista> deportistas) {
        try {
            llamadaDriver(ruta_driver);
            try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
                Statement st = connection.createStatement();
                for (Deportista d : deportistas) {
                    st.executeUpdate(String.format(
                            "INSERT INTO deportistas(nombre, activo, genero, deporte) VALUES (%s, %s, %s, %s)",
                            d.getNombre(), d.isActivo(), d.getGenero(), d.getDeporte()));
                }
                return Response.ok("Subido bien :/").build();
            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encontrado el driver").build();
        }
    }

    // Ejercicio 4.16
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarDeporte(Deportista d) {
        try {
            llamadaDriver(ruta_driver);
            try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
                Statement st = connection.createStatement();
                st.executeUpdate(String.format(
                        "UPDATE deportistas set nombre = '%s' , activo = '%s', deporte = '%s', genero = '%s' WHERE id = %d",
                        d.getNombre(), d.isActivo(), d.getDeporte(), d.getGenero(), d.getId()));
                return Response.ok("Subido bien :/").build();
            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encontrado el driver").build();
        }
    }

    // Ejercicio 4.17
    @Path("/del/{id}")
    @DELETE
    public Response eliminarDeportista(@PathParam("id") int id) {
        try {
            llamadaDriver(ruta_driver);
            try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
                Statement st = connection.createStatement();
                st.executeUpdate(String.format(
                        "DELETE FROM deportistas WHERE id = %d", id));
                return Response.ok("Subido bien :/").build();
            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encontrado el driver").build();
        }
    }

    // Ejercicio 4.18
    @GET
    @Path("/img/{id}/{num}")
    @Produces("image/jpg")
    public Response imagenDeportista(@PathParam("id") int id, @PathParam("num") int num) throws FileNotFoundException {
        try {
            llamadaDriver(ruta_driver);
            try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
                String ruta = "";
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery(
                        "SELECT nombre FROM imagenes WHERE id = " + id + "AND nombre LIKE '" + id + "_" + num + "_%'");
                while (rs.next()) {
                    ruta = "C:\\imagenes\\imagenes\\" + rs.getString("nombre");
                }
                FileInputStream fis = new FileInputStream(new File(ruta));
                return Response.ok(fis).build();
            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encontrado el driver").build();
        }
    }

    // Ejercicio 4.19
    @GET
    @Path("/img/{id}")
    @Produces("image/jpg")
    public Response imagenesDeportistas(@PathParam("id") int id) throws FileNotFoundException {
        try {
            llamadaDriver(ruta_driver);
            try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
                String ruta = "";
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery(String.format("SELECT deportistas.nombre, imagenes.nombre FROM deportistas JOIN imagenes USING (id) WHERE id = %d", id));
                while (rs.next()) {
                    ruta = "C:\\imagenes\\imagenes\\" + rs.getString("imagenes.nombre");
                }
                FileInputStream fis = new FileInputStream(new File(ruta));
                return Response.ok(fis).build();
            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encontrado el driver").build();
        }
    }
}
