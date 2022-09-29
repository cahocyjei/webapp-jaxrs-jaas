package org.troyka.webaap.jaxrs.services;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.troyka.webaap.jaxrs.models.Curso;
import org.troyka.webaap.jaxrs.repositories.CursoRepository;

import java.util.List;
import java.util.Optional;

@Stateless
@DeclareRoles({"USER","ADMIN"})
public class ServiceCursoWsImpl implements ServiceCursoWs {

    @Inject
    private CursoRepository repo;

    @Override
    @RolesAllowed({"USER","ADMIN"})
    public List<Curso> lista() {
        return repo.listar() ;
    }

    @Override
    @RolesAllowed({"ADMIN"})
    public Curso guardar(Curso curso) {
        return repo.crear(curso);
    }

    @Override
    @RolesAllowed({"USER","ADMIN"})
    public Optional<Curso> byId(Long id) {
        return Optional.ofNullable(repo.byId(id));
    }

    @Override
    @RolesAllowed({"ADMIN"})
    public void eliminar(Long id) {
        repo.eliminar(id);
    }
}
