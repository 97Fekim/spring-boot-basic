package org.zerock.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Reply;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertDummiesTest(){
        IntStream.rangeClosed(1,300).forEach(i -> {
            Long bno = (long)(Math.random() * 100 + 1);

            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder()
                    .text("Reply...."+i)
                    .board(board)
                    .replyer("guest")
                    .build();

            replyRepository.save(reply);
        });
    }

    @Transactional
    @Test
    public void readReply1(){
        Optional<Reply> result = replyRepository.findById(1L);

        if(result.isPresent()){
            Reply reply = result.get();

            System.out.println(reply);
            System.out.println(reply.getBoard());
        }
    }


}
