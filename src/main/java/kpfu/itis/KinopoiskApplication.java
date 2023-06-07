package kpfu.itis;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class KinopoiskApplication {


    public static void main(String[] args) {
        SpringApplication.run(KinopoiskApplication.class, args);

    }
}
