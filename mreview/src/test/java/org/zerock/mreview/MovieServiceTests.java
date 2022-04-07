package org.zerock.mreview;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mreview.dto.MovieDTO;
import org.zerock.mreview.dto.PageRequestDTO;
import org.zerock.mreview.dto.PageResultDTO;
import org.zerock.mreview.service.MovieService;

import javax.transaction.Transactional;

@SpringBootTest
public class MovieServiceTests {

    @Autowired
    private MovieService movieService;

    @Test
    public void testGestList(){
        PageResultDTO<MovieDTO, Object[]> list = movieService.getList(new PageRequestDTO());
        System.out.println(list);
    }

}
