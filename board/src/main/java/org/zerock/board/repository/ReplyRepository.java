package org.zerock.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Reply;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    
    // 게시글 삭제 시, 댓글도 함께 삭제하기 위한 메소드
    @Modifying
    @Query("delete from Reply r where r.board.bno = :bno")
    void deleteByBno(@Param("bno") Long bno);

    // Query Method!!!
    // getReplies 대신 get, find 를 써도 마찬가지의 결과를 가져온다
    List<Reply> getRepliesByBoardOrderByRno(Board board);

}
