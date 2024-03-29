package com.example.openschooltask3.config;

import com.example.openschooltask3.entity.Order;
import com.example.openschooltask3.entity.User;
import com.example.openschooltask3.model.Status;
import com.example.openschooltask3.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TestConfig {

    UserRepository repository;

    /**
     * Метод для предварительного заполнения БД тестовыми данными
     */
    @PostConstruct
    private void initTestValue() {
        List<User> users = new ArrayList<>();

        for (int userI = 1; userI < 6; userI++) {
            List<Order> orders = new ArrayList<>();
            for (int orderI = 1; orderI < 4; orderI++) {
                orders.add(Order.builder()
                        .description("order" + orderI)
                        .status(Status.OPEN)
                        .build());
            }

            users.add(User.builder()
                    .name("user" + userI)
                    .email("email" + userI)
                    .orders(orders)
                    .build());
        }

        repository.saveAll(users);
    }

}
