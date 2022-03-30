package org.zerock.guestbook1.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.guestbook1.entity.GuestBook;
import org.zerock.guestbook1.entity.QGuestBook;

import java.util.stream.IntStream;

@SpringBootTest
public class GuestBookRepositoryTests {
    @Autowired
    private GuestBookRepository guestBookRepository;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1,100).forEach(i -> {
            GuestBook guestBook = GuestBook.builder()
                    .title("title..."+ i)
                    .content("content..."+i)
                    .writer("writer..."+i)
                    .build();
            System.out.println(guestBookRepository.save(guestBook));
        });
    }

    @Test
    public void testQuery1(){

        Pageable pageable = PageRequest.of(0,10, Sort.by("gno").descending());

        QGuestBook qGuestBook = QGuestBook.guestBook;

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression expression = qGuestBook.title.contains(keyword);

        builder.and(expression);

        Page<GuestBook> result = guestBookRepository.findAll(builder, pageable);

        result.stream().forEach(guestBook -> {
            System.out.println(guestBook);
        });
    }

    @Test
    public void testQuery2(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("gno").descending());

        QGuestBook qGuestBook = QGuestBook.guestBook;

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression exTitle = qGuestBook.title.contains(keyword);
        BooleanExpression exContent = qGuestBook.content.contains(keyword);
        BooleanExpression exAll = exTitle.or(exContent);

        builder.and(exAll);
        builder.and(qGuestBook.gno.gt(0L));

        Page<GuestBook> result = guestBookRepository.findAll(builder, pageable);

        result.stream().forEach(guestBook -> {
            System.out.println(guestBook);
        });
    }

}