package nickolay.ponomarev.clientservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 30.03.2022
 * client-service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ClientServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class); 
    }
}
