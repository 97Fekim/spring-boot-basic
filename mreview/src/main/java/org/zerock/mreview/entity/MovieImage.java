package org.zerock.mreview.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "movie")
public class MovieImage {       // 영화에 사용할 이미지에 대한 정보를 기록하는 클래스입니다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inum;

    private String uuid;

    private String imgName; // 이미지의 이름

    private String path;    // 이미지의 저장 경로 (년/월/일 폴더 구조)

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;
}
