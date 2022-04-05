package org.zerock.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.board.dto.ReplyDTO;
import org.zerock.board.service.ReplyService;

import java.util.List;

@RestController
@RequestMapping("/replies/")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;    // 자동 주입을 위해 final

    // 경로 요청을 받는 컨트롤러이다.
    // 데이터를 JSON으로 공급한다.
    @GetMapping(value = "/board/{bno}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReplyDTO>> getListByBoard(@PathVariable("bno")Long bno){
        log.info("==========(Controller)-getListByBoard========");
        return new ResponseEntity<>(replyService.getList(bno), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Long> register(@RequestBody ReplyDTO replyDTO){
        log.info("==========(Controller)-register========");
        Long rno = replyService.register(replyDTO);
        log.info("========rno : "+ rno);
        return new ResponseEntity<>(rno, HttpStatus.OK);
    }

}
