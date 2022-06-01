package study.querydsl.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"}) // team 은 제외
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String username;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID") // 외래키 컬럼명이 된다.
    private Team team;

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        this.team = team;

        if (team != null) { //팀이 이미 있다면
            changeTeam(team);
        }
    }

    public Member(String username, int age) {
        this(username, age, null);
    }

    public Member(String username) {
        this(username, 0);
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}