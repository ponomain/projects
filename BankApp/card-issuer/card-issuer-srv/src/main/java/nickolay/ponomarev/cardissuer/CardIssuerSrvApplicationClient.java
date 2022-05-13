package nickolay.ponomarev.cardissuer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(info = @Info(title = "Card Issuer API", version = "1.0", description = "All information about card issuer"))
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CardIssuerSrvApplicationClient {
    public static void main(String[] args) {
        SpringApplication.run(CardIssuerSrvApplicationClient.class, args);
    }
}
