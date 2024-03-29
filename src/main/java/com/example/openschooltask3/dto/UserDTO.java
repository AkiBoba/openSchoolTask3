package com.example.openschooltask3.dto;

import com.example.openschooltask3.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotBlank(message = "Поле имя не заполнено")
    @Size(min = 3, message = "Имя должно быть не короче 3х букв")
    private String name;
    @NotBlank(message = "Поле email не заполнено")
    @Size(min = 5, message = "Email должен быть не короче 5 знаков")
    private String email;

    public User toUser() {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .build();
    }
}
