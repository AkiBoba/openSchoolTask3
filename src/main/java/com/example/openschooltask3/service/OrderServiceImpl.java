package com.example.openschooltask3.service;

import com.example.openschooltask3.dto.OrderDTO;
import com.example.openschooltask3.entity.Order;
import com.example.openschooltask3.entity.User;
import com.example.openschooltask3.repository.OrderRepository;
import com.example.openschooltask3.util.UserAndOrderUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;

    UserAndOrderUtil util;

    @Override
    public void create(OrderDTO orderDTO) {
        util.getAndSetUser(orderDTO);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getAllByUser(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    @Override
    public void update(Long id, OrderDTO orderDTO) {
        Order order = toOrder(orderDTO);
        order.setId(id);
        orderRepository.save(order);
    }

    @Override
    public Optional<Order> get(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        orderRepository.delete(Order.builder()
                        .id(id)
                .build());

    }

    private Order toOrder(OrderDTO orderDTO) {
        return Order.builder()
                .description(orderDTO.getDescription())
                .status(orderDTO.getStatus())
                .user(User.builder()
                        .id(orderDTO.getUserId())
                        .build())
                .build();
    }
}
