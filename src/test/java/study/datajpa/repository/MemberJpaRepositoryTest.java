package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.domain.Member;
import study.datajpa.domain.Team;
import study.datajpa.dto.MemberDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired MemberJpaRepository memberJpaRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired TeamJpaRepository teamJpaRepository;
    @PersistenceContext
    EntityManager em;
    @Test
    void save() {
        Member member = new Member("memberA",11);

        Member id = memberJpaRepository.save(member);
        Member findMember = memberJpaRepository.find(id.getId());

        Assertions.assertThat(id).isEqualTo(findMember);

    }


    @Test
    void tt() {
        Member member1 = new Member("memberA",11);
        Member member2 = new Member("memberB",22);

        memberRepository.save(member1);
        memberRepository.save(member2);

        Team team = new Team("댄스동아리");
        teamJpaRepository.save(team);
        member1.setTeam(team);
        member2.setTeam(team);


        List<MemberDto> byDto = memberRepository.findByDto();
        System.out.println(byDto);
    }

    @Test
    void bulk() {
        memberRepository.save(new Member("member1",10));
        memberRepository.save(new Member("member2",15));
        memberRepository.save(new Member("member3",11));
        memberRepository.save(new Member("member4", 17));
        memberRepository.save(new Member("member5",19));

        int resultCount = memberJpaRepository.bulkAgePlus(15);
        em.flush();
        em.clear();

        System.out.println("resultCount = " + resultCount);

    }

}