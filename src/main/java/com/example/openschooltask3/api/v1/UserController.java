package com.example.openschooltask3.api.v1;

import com.example.openschooltask3.dto.UserDTO;
import com.example.openschooltask3.entity.User;
import com.example.openschooltask3.service.UserService;
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
@RequestMapping("/user")
public class UserController {

    UserService service;

    @Operation(description = "Контроллер принимает запрос на получение списка всех пользователей")
    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }

    @Operation(description = "Контроллер принимает запрос на получение пользователя по id")
    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return service.get(id).orElseThrow();
    }

    @Operation(description = "Контроллер принимает запрос на создание пользователя")
    @PostMapping
    public User create(@Valid @RequestBody UserDTO userDTO) {
        return service.create(userDTO);
    }

    @Operation(description = "Контроллер принимает запрос на удаление пользователя")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @Operation(description = "Контроллер принимает запрос на изменение пользователя")
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        service.get(id).orElseThrow();
        return service.update(User.builder()
                .id(id)
                        .name(userDTO.getName())
                        .email(userDTO.getEmail())
                .build());
    }

}
