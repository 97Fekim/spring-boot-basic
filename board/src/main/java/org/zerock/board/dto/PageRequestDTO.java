package org.zerock.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
    private int page;    // 몇번째 페이지를 가져올건지
    private int size;    // 한번에 몇 개의 페이지를 가져올건지
    private String type;
    private String keyword;

    public PageRequestDTO(){
        this.page = 1;
        this.size = 10;
    }

    // PageRequestDTO 는 가져올 페이지에 대한 설정정보를 담고 있다.
    // 이 getPage 메소드는, 그 설정정보를 바탕으로 페이지를 가져오는 기준 Pageable을 설정해서 가져온다.
    public Pageable getPageable(Sort sort){
        return PageRequest.of(page - 1 , size, sort);
    }
}
