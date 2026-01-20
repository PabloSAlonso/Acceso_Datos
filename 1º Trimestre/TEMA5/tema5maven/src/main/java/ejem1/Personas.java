package ejem1;

import java.util.ArrayList;

import javax.print.attribute.standard.Media;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/personas")
public class Personas {
    static ArrayList<Persona> personas;

    // Ejercicio 1
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void guardar(Persona p) {
        personas.add(p);
    }

    // Ejercicio 2
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public ArrayList<Persona> listar() {
        return personas;
    }

    // Ejercicio 3
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

    // Ejercicio 4
    @GET
    @Path("/buscar")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String ver_2(@QueryParam("patron") String patron) {
        ArrayList<String> nombres = new ArrayList<>();
        for (Persona persona : personas) {
            if (persona.getNombre().contains(patron)) {
                nombres.add(persona.getNombre());
            }
        }
        return nombres.toString();
    }

    // Ejercicio 6
    @POST
    @Path("/form")
    @Consumes("application/x-www-form-urlencoded")
    public Response insertarPersonas(@FormParam("id") int id, @FormParam("nombre") String nombre,
            @FormParam("casado") boolean casado, @FormParam("sexo") String sexo) {
        Persona p = new Persona();
        p.setId(id);
        p.setNombre(nombre);
        p.setCasado(casado);
        p.setSexo(sexo);
        personas.add(p);
        return Response.ok(personas).build();
    }

    // Ejercicio 7
    @POST
    @Path("/add")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response insertarVariasPersonas() {
        for (Persona persona : personas) {
            personas.add(persona);
        }
        return Response.ok(personas).build();
    }

    // Ejercicio 8
    @DELETE
    @Path("/{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response borrarPersonas(@PathParam("id") int id) {
        for (int i = personas.size(); i >= 0; i--) {
            if (personas.get(i).getId() == id) {
                personas.remove(personas.get(i));
            }
        }
        return Response.ok(personas).build();
    }

    // Ejercicio 9
    @GET
    @Path("/buscar")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String ver_3(@DefaultValue("a") @QueryParam("patron") String patron) {
        ArrayList<String> nombres = new ArrayList<>();
        for (Persona persona : personas) {
            if (persona.getNombre().contains(patron)) {
                nombres.add(persona.getNombre());
            }
        }
        return nombres.toString();
    }

}
