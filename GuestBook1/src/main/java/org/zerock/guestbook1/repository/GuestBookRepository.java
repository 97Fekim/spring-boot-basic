package org.zerock.guestbook1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.zerock.guestbook1.entity.GuestBook;

import java.util.List;

public interface GuestBookRepository extends JpaRepository<GuestBook, Long>,
        QuerydslPredicateExecutor<GuestBook> {
    List<GuestBook> findByGnoBetweenOrderByGnoDesc(Long form, Long to);
}
