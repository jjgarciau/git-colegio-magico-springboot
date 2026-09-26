package com.quipux.colegio.services;

import com.quipux.colegio.manager.HechizoManager;
import com.quipux.colegio.models.HechizoEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

// RETO 4: Expón las "Puertas del Colegio" (APIs REST) usando anotaciones de Jakarta
// Agrega la anotación para la ruta principal: "/hechizos"
// PISTA: @P...

@Path("/hechizos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component
@Tag(name = "Hechizos", description = "Servicios/Endpoints para la gestión de hechizos")
public class HechizoService {

    @Autowired
    private HechizoManager hechizoManager;

    // RETO 4.1: Endpoint para crear un hechizo.
    // Usa la anotación HTTP correcta para "crear". (PISTA: @P...)
    @Operation(summary = "Crear un nuevo hechizo")
    @POST
    public Response crearHechizo(HechizoEntity hechizo) {
        // RETO 4.1.2: Usa try/catch. Si el manager lanza Exception, devuelve un Response con Status.BAD_REQUEST (400)
        // Si todo sale bien, devuelve un Response con Status.CREATED (201) y la entidad.
        // Escribe tu código aquí:
        try {
            hechizoManager.registrarHechizo(hechizo);
            return Response.status(Response.Status.CREATED).entity("Hechizo creado correctamente").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // RETO 4.2: Endpoint para buscar hechizos por tipo, ej: /hechizos?tipo=Fuego
    // Usa la anotación HTTP correcta para "leer". (PISTA: @G...)
    @Operation(summary = "Buscar hechizos por tipo de magia")
    @GET
    public Response buscarPorTipo(@QueryParam("tipo") String tipo) {
        // RETO 4.3: Usa la anotación correcta dentro de los parámetros de arriba (PISTA: @Q...)
        List<HechizoEntity> lista = hechizoManager.buscarMagia(tipo);
        return Response.ok(lista).build();
    }
}
