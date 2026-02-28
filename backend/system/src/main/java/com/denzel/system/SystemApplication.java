package com.denzel.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.denzel"   // ← couvre tout : com.denzel.entity, com.denzel.system, etc.
})
@EntityScan(basePackages = {
        "com.denzel.entity"    // ← package exact visible dans l'arborescence
})
@EnableJpaRepositories(basePackages = {
        "com.denzel.system",
        "com.denzel.entity"
})
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }

}
