package org.zerock.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.board.entity.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // 게시글 정보 + 작성자 정보까지 함께 얻고 싶을때(Board가 Member를 참조하고 있으므로 on을 사용하지 않아도 됨)
    @Query("select b,w from Board b left join b.writer w where b.bno = :bno")
    Object getBoardWithWriter(@Param("bno") Long bno);

    // 게시글 정보와 작성자 이메일 + 댓글의 정보까지 함께 얻고 싶을때
    @Query("SELECT b,r from Board b LEFT JOIN Reply r ON r.board = b WHERE b.bno = :bno")
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);

    // 목록 화면에 필요한 데이터. (Board + Member + count(Reply))
    @Query(value="SELECT b,w,count(r) "+
            " FROM Board b "+
            " LEFT JOIN b.writer w "+
            " LEFT JOIN Reply r ON r.board = b "+
            " GROUP BY b",
            countQuery = "SELECT count(b) FROM Board b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);
}
