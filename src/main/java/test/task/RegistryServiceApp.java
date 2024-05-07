package test.task;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "This is region registration service",
                version = "1.0.0")
)
public class RegistryServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(RegistryServiceApp.class, args);
    }

}