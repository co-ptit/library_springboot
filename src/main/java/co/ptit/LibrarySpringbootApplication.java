package co.ptit;

import co.ptit.utils.Constant;
import com.tinify.Tinify;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibrarySpringbootApplication {

    public static void main(String[] args) {
        Tinify.setKey(Constant.KEY_TINY);
        SpringApplication.run(LibrarySpringbootApplication.class, args);
    }

}
