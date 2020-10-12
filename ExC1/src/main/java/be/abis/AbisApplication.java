package be.abis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AbisApplication {

    public static void main(String[] args) {
        SpringApplication.run(AbisApplication.class);
        System.out.println(AbisApplication.class.getName()+ " of ExC1 is running !");
    }
}
