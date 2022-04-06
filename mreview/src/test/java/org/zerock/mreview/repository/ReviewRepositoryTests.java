package org.zerock.mreview.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.zerock.mreview.entity.Member;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.Review;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testInsertMovieReviews(){

        IntStream.rangeClosed(1,200).forEach(i -> {

            // 랜덤 영화
            Long mno = (long)(Math.random()*100) + 1;
            Movie movie = Movie.builder().mno(mno).build();

            // 랜덤 리뷰어
            Long mid = (long)(Math.random()*100) + 1;
            Member member = Member.builder().mid(mid).build();

            Review review = Review.builder()
                    .movie(movie)
                    .member(member)
                    .text("이 영화에 대한 느낌..."+i)
                    .grade((int)(Math.random()*5)+1)
                    .build();

            reviewRepository.save(review);
        });
    }

    @Test
    public void testGetReviewsByMovie(){
        Movie movie = Movie.builder().mno(7L).build();

        List<Review> result = reviewRepository.findByMovie(movie);

        result.forEach(review -> {
            System.out.println(review.getReviewnum());
            System.out.println("\t"+review.getGrade());
            System.out.println("\t"+review.getText());
            System.out.println("\t"+review.getMember().getEmail());
        });
    }

    @Commit
    @Transactional
    @Test
    public void testDeleteMember(){
        Long mid = 2L;

        Member member = Member.builder().mid(mid).build();

        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(mid);

    }
}
