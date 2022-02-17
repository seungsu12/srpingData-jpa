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

    public List<Member> findByPage(int age, int offset, int limit) {
        return em.createQuery("select m from Member m where m.age = :age order by m.username desc")
                .setParameter("age",age)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public Long totalCount(int age) {
        return em.createQuery("select count(m) from Member m where m.age =:age",Long.class)
                .setParameter("age",age)
                .getSingleResult();

    }

    public int bulkAgePlus(int age) {
        int resultCount = em.createQuery("update Member m set m.age= m.age +1"
                        + "where m.age >=:age")
                .setParameter("age", age)
                .executeUpdate();
        return resultCount;
    }

}
