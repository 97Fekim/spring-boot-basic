package org.zerock.guestbook1.service;

import org.zerock.guestbook1.dto.GuestbookDTO;
import org.zerock.guestbook1.dto.PageRequestDTO;
import org.zerock.guestbook1.dto.PageResultDTO;
import org.zerock.guestbook1.entity.GuestBook;

public interface GuestbookService {

    Long register(GuestbookDTO dto);
    GuestbookDTO read(Long gno);
    void remove(Long gno);
    void modify(GuestbookDTO dto);

    PageResultDTO<GuestbookDTO, GuestBook> getList(PageRequestDTO requestDTO);

    default GuestBook dtoToEntity(GuestbookDTO dto){
        GuestBook entity = GuestBook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }

    default GuestbookDTO entityToDto(GuestBook guestBook){
        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .gno(guestBook.getGno())
                .title(guestBook.getTitle())
                .content(guestBook.getContent())
                .writer(guestBook.getWriter())
                .regDate(guestBook.getRegDate())
                .modDate(guestBook.getModDate())
                .build();
        return guestbookDTO;
    }
}
