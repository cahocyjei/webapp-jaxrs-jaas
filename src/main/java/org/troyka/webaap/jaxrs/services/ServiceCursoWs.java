package org.troyka.webaap.jaxrs.services;

import jakarta.ejb.Local;
import jakarta.jws.WebService;
import org.troyka.webaap.jaxrs.models.Curso;

import java.util.List;
import java.util.Optional;

@Local
public interface ServiceCursoWs {
    List<Curso> lista();
    Curso guardar(Curso curso);
    Optional<Curso> byId(Long id);
    void eliminar(Long id);
}
