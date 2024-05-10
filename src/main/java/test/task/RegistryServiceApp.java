package test.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Kirill",
                        email = "gutorovvvvv.k@gmail.com"
                ),
                title = "OpenApi Specification - Kirill",
                description = "OpenApi Specification for Registry",
                version = "1.0.0")
)
public class RegistryServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(RegistryServiceApp.class, args);
    }

}
