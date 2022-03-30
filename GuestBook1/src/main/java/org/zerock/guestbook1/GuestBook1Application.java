package org.zerock.guestbook1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GuestBook1Application {

    public static void main(String[] args) {
        SpringApplication.run(GuestBook1Application.class, args);
    }

}
