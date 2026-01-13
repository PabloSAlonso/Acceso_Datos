package ejem1;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/persona")
public class GestionaPersona {
    public static Persona p;

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Persona getPersona() {
        p = new Persona();
        p.setId(1);
        p.setNombre("Pinga");
        p.setCasado(true);
        p.setSexo("poco");
        return p;
    }

    
}
