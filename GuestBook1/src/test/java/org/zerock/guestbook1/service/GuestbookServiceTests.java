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

    @Test
    public void testGetList(){
        service.getList(new PageRequestDTO(2, 10)).getDtoList().forEach(dto -> {
            System.out.println(dto);
        });
    }

    @Test
    public void testGetPageList() {
        PageResultDTO<GuestbookDTO, GuestBook> dto = service.getList(new PageRequestDTO(1,10));
        System.out.println(dto);
    }

    @Test
    public void testGetOne(){
        System.out.println(service.read(50L));
    }
}
