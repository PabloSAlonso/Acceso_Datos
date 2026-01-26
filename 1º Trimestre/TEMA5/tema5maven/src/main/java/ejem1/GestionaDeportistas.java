package ejem1;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jakarta.annotation.Generated;
import jakarta.validation.metadata.ReturnValueDescriptor;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
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

    // // Ejercicio 4.9
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
}
