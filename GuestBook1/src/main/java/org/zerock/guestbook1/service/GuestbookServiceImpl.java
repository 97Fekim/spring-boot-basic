package org.zerock.guestbook1.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
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
import org.zerock.guestbook1.entity.QGuestBook;
import org.zerock.guestbook1.repository.GuestBookRepository;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService{

    private final GuestBookRepository repository;

    @Override   // 등록(Create)
    public Long register(GuestbookDTO dto){
        log.info("DTO.............");
        log.info(dto);
        GuestBook entity = dtoToEntity(dto);
        log.info(entity);
        repository.save(entity);

        return entity.getGno();
    }

    @Override   // 조회(Read)
    public GuestbookDTO read(Long gno) {
        Optional<GuestBook> result = repository.findById(gno);

        return result.isPresent() ? entityToDto(result.get()) : null;
    }

    @Override   // 삭제(Delete)
    public void remove(Long gno) {
        repository.deleteById(gno);
    }

    @Override   // 수정(Update)
    public void modify(GuestbookDTO dto) {
        Optional<GuestBook> result = repository.findById(dto.getGno());

        if(result.isPresent()){
            GuestBook entity = result.get();
            entity.changeTitle(dto.getTitle());
            entity.changeContent(dto.getContent());
            repository.save(entity);
        }
    }

    @Override   // 한 페이지 가져오기(Read)
    public PageResultDTO<GuestbookDTO, GuestBook> getList(PageRequestDTO requestDTO){
        Pageable pageable = requestDTO.getPage(Sort.by("gno").descending());

        BooleanBuilder booleanBuilder = getSearch(requestDTO);  // 검색을 위한 BooleanBuilder 구하기

        Page<GuestBook> result = repository.findAll(booleanBuilder,pageable);   // findAll에 BooleanBuilder 추가

        Function<GuestBook, GuestbookDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }
    
    // 검색 expression 만들기
    private BooleanBuilder getSearch(PageRequestDTO requestDTO){    // Querydsl 처리
        String type = requestDTO.getType();
        String keyword = requestDTO.getKeyword();
        
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QGuestBook qGuestBook = QGuestBook.guestBook;

        BooleanExpression expression = qGuestBook.gno.gt(0);    // gno > 0 조건 생성
        booleanBuilder.and(expression);

        if(type == null || type.trim().length()==0){    // 검색 조건이 없는 경우
            return booleanBuilder;
        }

        // 검색 조건 작성하기
        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if(type.contains("t")){
            conditionBuilder.or(qGuestBook.title.contains(keyword));
        }
        if(type.contains("c")){
            conditionBuilder.or(qGuestBook.content.contains(keyword));
        }
        if(type.contains("w")){
            conditionBuilder.or(qGuestBook.writer.contains(keyword));
        }

        // 모든 조건 통합
        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }
}
