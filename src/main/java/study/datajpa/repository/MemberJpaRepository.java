package study.datajpa.repository;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
public class MemberJpaRepository {

    @PersistenceContext
    private EntityManager em;


    public Member save(Member member){
        em.persist(member);
        return member;
    }

    public Member find(Long id) {
        return em.find(Member.class,id);
    }

    public void delete(Long id){
        em.remove(id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m",Member.class)
                .getResultList();
    }

    public Long count() {
        return em.createQuery("select count(m) from Member m",Long.class)
                .getSingleResult();
    }

}
