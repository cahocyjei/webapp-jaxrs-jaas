package org.troyka.webaap.jaxrs.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.troyka.webaap.jaxrs.models.Curso;
import org.troyka.webaap.jaxrs.services.ServiceCursoWs;

import java.util.List;
import java.util.Optional;

@RequestScoped
@Path("/cursos")
@Produces(MediaType.APPLICATION_JSON)
public class CursoControllers {

    @Inject
    private ServiceCursoWs service;

    @GET
    public List<Curso> listar(){
        return service.lista();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response byId(@PathParam("id") Long id){
        Optional<Curso> curso = service.byId(id);
        if (curso.isPresent()){
            return Response.ok(curso.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Curso curso){
        try{
            return Response.ok(service.guardar(curso)).build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(@PathParam("id") Long id,Curso curso){
        Optional<Curso> optionalCurso = service.byId(id);
        if (optionalCurso.isPresent()){
            Curso nuevoCurso = optionalCurso.get();
            nuevoCurso.setName(curso.getName());
            nuevoCurso.setDescripcion(curso.getDescripcion());
            nuevoCurso.setInstructor(curso.getInstructor());
            nuevoCurso.setDuracion(curso.getDuracion());

            try{
                System.out.println("Curso editado, id = " + nuevoCurso.getId());
                return Response.ok(service.guardar(nuevoCurso)).build();
            }catch (Exception e){
                e.printStackTrace();
                return Response.serverError().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id){
        Optional<Curso> curso = service.byId(id);
        if (curso.isPresent()){
            try{
                service.eliminar(curso.get().getId());
                return Response.status(Response.Status.NO_CONTENT).build();
            }catch (Exception e){
                e.printStackTrace();
                Response.serverError().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
