package org.zerock.guestbook1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.guestbook1.dto.GuestbookDTO;
import org.zerock.guestbook1.dto.PageRequestDTO;
import org.zerock.guestbook1.dto.PageResultDTO;
import org.zerock.guestbook1.entity.GuestBook;
import org.zerock.guestbook1.repository.GuestBookRepository;

import java.util.function.Function;

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

   /* @Override
    public PageResultDTO<GuestbookDTO, GuestBook> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());

        Page<GuestBook> result = repository.findAll(pageable);

        Function<GuestBook, GuestbookDTO> fn = (entity ->
                entityToDto(entity));
        return new PageResultDTO<>(result, fn);
    }*/
}
