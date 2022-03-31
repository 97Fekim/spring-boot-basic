package org.zerock.guestbook1.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.guestbook1.dto.GuestbookDTO;

@SpringBootTest
public class GuestbookServiceTests {
    @Autowired
    private GuestbookService service;

    @Test
    public void testRegister(){
        GuestbookDTO dto = new GuestbookDTO();
        dto.setTitle("registerd title...");
        dto.setContent("registerd content...");
        dto.setWriter("registerd writer...");

        System.out.println(service.register(dto));
    }
}
