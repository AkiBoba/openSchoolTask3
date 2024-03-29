package com.example.openschooltask3.util;

import com.example.openschooltask3.dto.OrderDTO;
import com.example.openschooltask3.entity.Order;
import com.example.openschooltask3.entity.User;
import com.example.openschooltask3.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserAndOrderUtil {

    UserService userService;

    public void getAndSetUser(OrderDTO orderDTO) {
        User user = userService.get(orderDTO.getUserId()).orElseThrow();
        Order order = Order.builder()
                .description(orderDTO.getDescription())
                .status(orderDTO.getStatus())
                .build();
        user.getOrders().add(order);
        userService.update(user);
    }

}
