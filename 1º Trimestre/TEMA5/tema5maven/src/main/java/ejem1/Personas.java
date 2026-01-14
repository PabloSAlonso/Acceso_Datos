package ejem1;

import java.util.ArrayList;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/personas")
public class Personas {
    static ArrayList<Persona> personas;

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void guardar(Persona p) {
        personas.add(p);
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<Persona> listar() {
        return personas;
    }

    @GET
    @Path("/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public String ver(@PathParam("nombre") String name) {
        for (Persona persona : personas) {
            if (persona.getNombre().equals(name)) {
                return String.format("Id:%d, Nombre:%s,  Casado:%s, Sexo:%s", persona.getId(), persona.getNombre(),
                        persona.getCasado(), persona.getSexo());
            }
        }
        return "No existe la persona: " + name;
    }

    @GET
    @Path("/buscar")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String ver_2(@DefaultValue("a") @QueryParam("patron") String patron){
        ArrayList<String> nombres = new ArrayList<>();
        for (Persona persona : personas) {
            if (persona.getNombre().contains(patron)){
                nombres.add(persona.getNombre());
            }
        }
        return nombres.toString();
    }


}
