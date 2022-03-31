package org.zerock.guestbook1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.guestbook1.dto.PageRequestDTO;
import org.zerock.guestbook1.service.GuestbookService;

@Controller
@Log4j2
@RequestMapping("/guestbook")
@RequiredArgsConstructor    // 자동 주입을 위한 Annotation
public class GuestbookController {

    private final GuestbookService service;

    @GetMapping("/")
    public String index() {
        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")    // 자동으로 modelAttribute가 되긴 하지만, 적어주자
    public void list(@ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO
            , Model model){
        log.info("list........" + pageRequestDTO);
        model.addAttribute("result", service.getList(pageRequestDTO));
    }
}
