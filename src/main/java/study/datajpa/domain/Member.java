package study.datajpa.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of={"id","username","age","team"})
@NamedQuery(
        name="Member.findByUserName",
        query="select m from Member m where m.username = :username")
public class Member {
    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String username;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }

    public Member(String username,int age) {
        this.username = username;
        this.age = age;
    }


}
