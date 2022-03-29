package org.zerock.covesjpa.entity;

import lombok.*;

import javax.persistence.*;

// 1) 엔티티 객체에는 @Entity를 붙인다
@Entity
@Table(name="tbl_memo")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Memo {
    // 2) 엔티티 객체는 반드시 pk가 있어야 하는데,
    // @Id 를 붙여서 pk 멤버를 정의한다.
    // * 그냥 Entity - Id 를 쌍으로 외워두자
    // @GeneratedValue가 붙어있다면 시퀀스가 부여된
    // 멤버라고 생각하면 된다.
    // IDENTITY는 auto-increment 라고 생각하면 된다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    private String memoText;
}