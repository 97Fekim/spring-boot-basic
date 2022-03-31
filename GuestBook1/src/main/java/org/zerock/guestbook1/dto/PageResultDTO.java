package org.zerock.guestbook1.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

// 제네릭이 의미하는 바는
// DTO : 뿌려질 개체
// EN : Entity를 뜻한다. (repository에서 반환하는게 Entity 타입이므로)
@Data
public class PageResultDTO<DTO, EN> {
    private List<DTO> dtoList;   // 뿌려질 DTO를 담고있는 리스트

    // 첫번째 인자 Page<EN>은 repository에서 가져온 Entity들의 페이지들이다.
    // 두번째 인자 fn은 Entity가 DTO로 어떻게 변환되는지를 정의하는 함수이다.
    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn){
        dtoList = result.stream().map(fn).collect(Collectors.toList());
    }
}
