package org.zerock.guestbook1.service;

import org.zerock.guestbook1.dto.GuestbookDTO;
import org.zerock.guestbook1.entity.GuestBook;

public interface GuestbookService {

    Long register(GuestbookDTO dto);

    default GuestBook dtoToEntity(GuestbookDTO dto){
        GuestBook entity = GuestBook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }
}
