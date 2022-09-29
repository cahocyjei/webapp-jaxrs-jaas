package org.troyka.webaap.jaxrs.repositories;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.troyka.webaap.jaxrs.models.Curso;

import java.util.List;
import java.util.Optional;

@RequestScoped
public class CursoRepositoryImpl implements CursoRepository{
    @Inject
    private EntityManager em;

    @Override
    public List<Curso> listar() {
        return em.createQuery("select c from Curso c left outer join fetch c.instructor ",Curso.class)
                .getResultList();
    }

    @Override
    public Curso crear(Curso curso) {
        if(curso.getId() != null && curso.getId() > 0){
            em.merge(curso);
        }else{
            em.persist(curso);
        }
        return curso;
    }

    @Override
    public Curso byId(Long id) {
        return em.createQuery("select c from Curso c left outer join fetch c.instructor where c.id=:id",Curso.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void eliminar(Long id) {
        Curso c = byId(id);
        em.remove(c);
    }
}
