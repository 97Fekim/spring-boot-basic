package org.zerock.club.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.club.entity.ClubMember;
import org.zerock.club.entity.ClubMemberRole;
import org.zerock.club.repository.ClubMemberRepository;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ClubMemberTests {

    @Autowired
    private ClubMemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies() {

        // 1 ~ 80 까지는 USER만 지정
        // 81 ~ 90 까지는 USER, MANAGER
        // 90 ~ 100 까지는 USER, MANAGER, ADMIN

        IntStream.rangeClosed(1,100).forEach(i -> {
            ClubMember clubMember = ClubMember.builder()
                    .email("user"+i+"@gmail.com")
                    .name("사용자"+i)
                    .fromSocial(false)
                    .password(passwordEncoder.encode("1111"))
                    .build();
            clubMember.addMemberRole(ClubMemberRole.USER);
            if(i > 80){
                clubMember.addMemberRole(ClubMemberRole.MANAGER);
            }
            if(i > 90){
                clubMember.addMemberRole(ClubMemberRole.ADMIN);
            }
            repository.save(clubMember);
        });

    }

    @Test
    public void insertFekim(){
        ClubMember clubMember = ClubMember.builder()
                .email("16fekim@gmail.com")
                .name("Fekim")
                .fromSocial(true)
                .password(passwordEncoder.encode("Fekim179546a!"))
                .build();
        clubMember.addMemberRole(ClubMemberRole.USER);
        clubMember.addMemberRole(ClubMemberRole.MANAGER);
        clubMember.addMemberRole(ClubMemberRole.ADMIN);

        repository.save(clubMember);

    }

    @Test
    public void testRead(){
        Optional<ClubMember> result = repository.findByEmail("user40@gmail.com", false);

        if(result.isPresent()){
            ClubMember clubMember = result.get();
            System.out.println(clubMember);
        }

    }

}
