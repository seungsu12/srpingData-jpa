package study.datajpa.repository;


import org.springframework.stereotype.Repository;
import study.datajpa.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class TeamJpaRepository {

    @PersistenceContext
    private EntityManager em;


    private Team save(Team team) {
        em.persist(team);
        return team;
    }

    public void delete(Long id) {
        em.remove(id);
    }

    public List<Team> findAll() {
        return em.createQuery("select t from Team t ",Team.class)
                .getResultList();
    }

    public Optional<Team> findById(Long id) {
         Team team =em.find(Team.class,id);
         return Optional.ofNullable(team);
    }

    public Long count() {
        return em.createQuery("select count(t) from Team t",Long.class)
                .getSingleResult();
    }

}
