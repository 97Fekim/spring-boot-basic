package org.zerock.board.service;

import org.zerock.board.dto.ReplyDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Reply;

import java.util.List;

public interface ReplyService {

    Long register(ReplyDTO replyDTO);

    List<ReplyDTO> getList(Long bno);

    void modify(ReplyDTO replyDTO);

    void remove(Long rno);

    default Reply dtoToEntity(ReplyDTO replyDTO){
        Board board = Board.builder().bno(replyDTO.getBno()).build();

        Reply reply = Reply.builder()
                .rno(replyDTO.getRno())
                .text(replyDTO.getText())
                .replyer(replyDTO.getReplyer())
                .board(board)
                .build();

        return reply;
    }

    default ReplyDTO entityToDto(Reply reply){
        
        ReplyDTO dto = ReplyDTO.builder()
                //.bno(reply.getBoard().getBno()) // DTO는 Board객체(bno)가 필요 없다
                .rno(reply.getRno())
                .text(reply.getText())
                .replyer(reply.getReplyer())
                .regDate(reply.getRegDate())    // DTO는 날짜가 자동 생성이 안되므로, 반드시 설정
                .modDate(reply.getModDate())
                .build();
        
        return dto;
    }


}
