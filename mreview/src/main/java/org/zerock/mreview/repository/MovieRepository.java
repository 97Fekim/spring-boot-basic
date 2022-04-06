package org.zerock.mreview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.mreview.entity.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    // 1)'영화', 2) '영화이미지'에 가장 먼저 등록된 이미지 3)'리뷰'의 평균, 4)'리뷰'의 갯수 를 가져온다
    // 영화는 리뷰와 이미지를 참조하고 있지 않으므로, on 을 사용한다.
    // 영화 정보에서 멤버의 정보는 필요 없다
    @Query("select m, mi, avg(coalesce(r.grade, 0)), count(distinct r) " +
            "from Movie m "
            + "left outer join MovieImage mi on mi.movie = m "
            + "left outer join Review r on r.movie = m group by m")
    Page<Object[]> getListPage(Pageable pageable);
    
    @Query("select m, mi, avg(coalesce(r.grade, 0)), count(r) " +
            " from Movie m " +
            " left outer join MovieImage mi on mi.movie = m " +
            " left outer join Review r on r.movie = m " +
            " where m.mno = :mno group by mi")
    List<Object[]> getMovieWithAll(@Param("mno") Long mno);
}
