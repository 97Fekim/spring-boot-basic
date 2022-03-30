package org.zerock.guestbook1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.guestbook1.dto.GuestbookDTO;
import org.zerock.guestbook1.entity.GuestBook;
import org.zerock.guestbook1.repository.GuestBookRepository;

@Service
@Log4j2
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService{

    private final GuestBookRepository repository;

    @Override
    public Long register(GuestbookDTO dto){
        log.info("DTO.............");
        log.info(dto);
        GuestBook entity = dtoToEntity(dto);
        log.info(entity);
        repository.save(entity);
        return entity.getGno();
    }
}
