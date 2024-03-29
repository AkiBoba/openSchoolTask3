package com.example.openschooltask3.service;

import com.example.openschooltask3.dto.OrderDTO;
import com.example.openschooltask3.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    /**
     * Создает новый заказ
     * @param orderDTO объект класса OrderDTO
     */
    void create(OrderDTO orderDTO);

    /**
     * Получает список всех заказов
     * @return список всех заказов
     */
    List<Order> getAll();

    /**
     * Получает список всех заказов пользователя
     * @return список всех заказов пользователя
     */
    List<Order> getAllByUser(Long userId);

    /**
     * Обновляет информацию о заказе
     * @param id заказа
     * @param orderDTO объект класса Order
     */
    void update(Long id, OrderDTO orderDTO);

    /**
     * Получает заказ
     * @param id заказа
     * @return объект класса Order
     */
    Optional<Order> get(Long id);

    /**
     * Удаляет заказ
     * @param id заказа
     */
    void delete(Long id);
}
