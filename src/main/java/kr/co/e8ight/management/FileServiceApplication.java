package kr.co.e8ight.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FileServiceApplication {
    public FileServiceApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(FileServiceApplication.class, args);
    }
}
