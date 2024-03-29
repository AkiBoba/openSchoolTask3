package com.example.openschooltask3.api.v1;

import com.example.openschooltask3.dto.OrderDTO;
import com.example.openschooltask3.entity.Order;
import com.example.openschooltask3.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/order")
public class OrderController {

    OrderService service;

    @Operation(description = "Контроллер принимает запрос на получение списка всех заказов")
    @GetMapping
    public List<Order> getAll() {
        return service.getAll();
    }

    @Operation(description = "Контроллер принимает запрос на получение заказа по id")
    @GetMapping("/{id}")
    public Order get(@PathVariable Long id) {
        return service.get(id).orElseThrow();
    }

    @Operation(description = "Контроллер принимает запрос на создание заказа")
    @PostMapping
    public void create(@Valid @RequestBody OrderDTO orderDTO) {
        service.create(orderDTO);
    }

    @Operation(description = "Контроллер принимает запрос на удаление заказа")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @Operation(description = "Контроллер принимает запрос на изменение заказа")
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        service.get(id).orElseThrow();
        service.update(id, orderDTO);
    }

    @Operation(description = "Контроллер принимает запрос на получение заказов пользователя по id")
    @GetMapping("/user/{userId}")
    public List<Order> getOrders(@PathVariable Long userId) {
        return service.getAllByUser(userId);
    }

}
