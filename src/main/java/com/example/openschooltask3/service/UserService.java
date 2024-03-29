package com.example.openschooltask3.service;

import com.example.openschooltask3.dto.UserDTO;
import com.example.openschooltask3.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    /**
     *  Создать нового юзера
     * @param userDTO юзер
     * @return сохраненный объект класса юзер
     */
    User create(UserDTO userDTO);

    /**
     * Изменяет юзера
     * @param user пользователь
     * @return измененный юзер
     */
    User update(User user);

    /**
     * получает пользователя по его id
     * @param id юзера
     * @return объект юзера в обертке Optional
     */
    Optional<User> get(Long id);

    /**
     * получает список всех юзеров
     * @return список юзеров
     */
    List<User> getAll();

    /**
     * Удаляет пользователя по его id
     * @param id юзера
     */
    void delete(Long id);
}
