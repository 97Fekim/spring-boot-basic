package org.zerock.guestbook1.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.guestbook1.dto.GuestbookDTO;
import org.zerock.guestbook1.dto.PageRequestDTO;
import org.zerock.guestbook1.dto.PageResultDTO;
import org.zerock.guestbook1.entity.GuestBook;

@SpringBootTest
public class GuestbookServiceTests {
    @Autowired
    private GuestbookService service;

    @Test
    public void testRegister(){
        GuestbookDTO dto = new GuestbookDTO();
        dto.setTitle("registered title...");
        dto.setContent("registered content...");
        dto.setWriter("registered writer...");

        System.out.println(service.register(dto));
    }

    /*@Test
    public void testGetList(){
        service.getList(new PageRequestDTO(2, 10)).getDtoList().forEach(dto -> {
            System.out.println(dto);
        });
    }*/

    /*@Test
    public void testGetPageList() {
        PageResultDTO<GuestbookDTO, GuestBook> dto = service.getList(new PageRequestDTO(1,10));
        System.out.println(dto);
    }*/

    @Test
    public void testGetOne(){
        System.out.println(service.read(50L));
    }

    @Test
    public void searchTest(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .type("t")
                .keyword("title")
                .build();
        PageResultDTO<GuestbookDTO, GuestBook> resultDTO = service.getList(pageRequestDTO);

        System.out.println("PREV : "+resultDTO.isPrev());
        System.out.println("NEXT : "+resultDTO.isNext());
        System.out.println("TOTAL : "+resultDTO.getTotalPage());

        System.out.println("-----------------------------");

        for(GuestbookDTO guestbookDTO : resultDTO.getDtoList()){
            System.out.println(guestbookDTO);
        }

        System.out.println("==============================");
        resultDTO.getPageList().forEach(i -> System.out.println(i));
    }
}
