package org.troyka.webaap.jaxrs.repositories;


import org.troyka.webaap.jaxrs.models.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoRepository {
    List<Curso> listar();
    Curso crear(Curso curso);
    Curso byId(Long id);
    void eliminar(Long id);
}
