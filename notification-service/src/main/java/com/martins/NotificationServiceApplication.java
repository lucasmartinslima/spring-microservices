package com.martins;

import com.martins.notificationservice.event.CompraEfetuadaEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
@EnableDiscoveryClient
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(CompraEfetuadaEvent compraEfetuadaEvent){
        //send email notification
        log.info("Noticação recebida para ordem - {}", compraEfetuadaEvent.getNumeroCompra());
    }

}
