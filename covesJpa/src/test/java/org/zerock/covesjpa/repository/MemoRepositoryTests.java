package org.zerock.covesjpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.covesjpa.Repository.MemoRepository;
import org.zerock.covesjpa.entity.Memo;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTests {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testInsert(){
        System.out.println("-----------------------------");
        System.out.println(memoRepository.getClass().getName());
    }

    @Test
    public void testInsertDummies() {
        IntStream.rangeClosed(1,100).forEach(i->{
            Memo memo = Memo.builder().memoText("Memo....." + i).build();
            memoRepository.save(memo);
        });
    }

    @Test
    public void testSelect() {
        Long mno = 100L;

        Optional<Memo> result = memoRepository.findById(mno);

        if(result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
        }
    }

    @Transactional
    @Test
    public void testSelect2() {
        Long mno = 100L;

        Memo memo = memoRepository.getOne(mno);
        System.out.println("-------------------");
        System.out.println(memo);
    }

    @Test
    public void testUpdate() {
        Memo memo = Memo.builder().mno(100L).memoText("Update Text").build();
        System.out.println(memoRepository.save(memo));
    }

    @Test
    public void testDelete() {
        Long mno = 100L;
        memoRepository.deleteById(mno);
    }

    @Test
    public void testPageDefault() {
        // 한 페이지에 10개씩
        // 페이지 번호가 항상 0부터 시작함을 주의!!
        Pageable pageable = PageRequest.of(6,10);
        Page<Memo> result = memoRepository.findAll(pageable);
        System.out.println("=================");
        System.out.println(result);
    }

    @Test
    public void testPageDefault2() {

        Sort sort = Sort.by("mno").ascending();

        Pageable pageable = PageRequest.of(0,10, sort);

        Page<Memo> result = memoRepository.findAll(pageable);

        System.out.println("==============================");
        System.out.println(result);
        System.out.println("total pages : " + result.getTotalPages());      // 총 몇 페이지
        System.out.println("total count : " + result.getTotalElements());   // 전체 개수
        System.out.println("page number : " + result.getNumber());          // 현재 페이지 번호 0부터 시작
        System.out.println("page size : " + result.getSize());              // 페이지당 데이터 개수
        System.out.println("has next page ? : " + result.hasNext());        // 다음 페이지 존재 여부
        System.out.println("has previous page? : "+result.hasPrevious());   // 이전 페이지 존재 여부
        System.out.println("fist page ? : " + result.isFirst());            // 시작 페이지인지 여부
        System.out.println("==============================");
    }

    public void testSort() {


    }

}
