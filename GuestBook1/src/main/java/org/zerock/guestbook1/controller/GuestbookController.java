package org.zerock.guestbook1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.guestbook1.dto.GuestbookDTO;
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
    public void list(@ModelAttribute("requestDTO") PageRequestDTO requestDTO
            , Model model){
        log.info("==========(Controller-get) list=========");
        model.addAttribute("result", service.getList(requestDTO));
    }

    @GetMapping("/register")
    public void register(){
        log.info("========(Controller-get) register========");
    }

    @PostMapping("/register")
    public String registerPost(GuestbookDTO dto, RedirectAttributes redirectAttributes){
        log.info("========(Controller-post) register========");
        Long gno = service.register(dto);
        redirectAttributes.addFlashAttribute("msg", gno);
        return "redirect:/guestbook/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(Long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model){
        log.info("=========(Controller-get) read=========");
        model.addAttribute("dto", service.read(gno));
    }

    @PostMapping("/remove")
    public String remove(Long gno, RedirectAttributes redirectAttributes){
        log.info("==========(Controller-post) remove==========");
        service.remove(gno);
        redirectAttributes.addFlashAttribute("msg", gno);
        return "redirect:/guestbook/list";
    }

    @PostMapping("/modify")
    public String modify(GuestbookDTO dto,
                         @ModelAttribute("requestDTO")PageRequestDTO requestDTO,
                         RedirectAttributes redirectAttributes){
        log.info("==========(Controller-post) modify==========");
        service.modify(dto);
        redirectAttributes.addAttribute("gno", dto.getGno());
        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("type", requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());

        return "redirect:/guestbook/read";
    }

}
