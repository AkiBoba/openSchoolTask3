package com.example.openschooltask3.dto;

import com.example.openschooltask3.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    @NotBlank(message = "Поле описание не заполнено")
    @Size(min = 5, message = "Описание должно быть не короче 5 букв")
    private String description;
    private Status status = Status.OPEN;
    @NotNull(message = "Поле пользователь не заполнено")
    private Long userId;

}
