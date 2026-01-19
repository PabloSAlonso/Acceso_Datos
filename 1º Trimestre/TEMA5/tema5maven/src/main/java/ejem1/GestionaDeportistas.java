package ejem1;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/deportistas")
public class GestionaDeportistas {

    private static final String URL = "jdbc:mariadb://localhost:3306/ad_tema6";
    private static final String USER = "root";
    private static final String PASS = "";

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response obtenerTodos() {
        try {
            ArrayList<Deportista> lDeportistas = new ArrayList<>();
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conexion = DriverManager.getConnection(URL, USER, PASS);
                    Statement st = conexion.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM deportistas")) {
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

}
