package com.kisonich.learntest.magaz;

import com.kisonich.learntest.magaz.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;

@Component
public class KafkaConsumer {
    @KafkaListener(topics = "orders-topic", groupId = "group_id")
    public void consume(Order order) {
        LocalDate currentDate = LocalDate.now();
        LocalDate monthAgo = currentDate.minusMonths(1);
        if (order.getOrderDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(monthAgo)) {
            // генерирование отчета о покупках пользователя
//            System.out.println("Report generated for user " + order.getUsername());
            System.out.println("Book: " + order.getBook().getName());
            System.out.println("Quantity: " + order.getQuantity());
            System.out.println("Total price: " + order.getTotalPrice());
        }
    }


}