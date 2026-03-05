package com.capgemini.test.code.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Usuario para peticiones")
@AllArgsConstructor
@Getter
public class User {

    @Schema(example = "3")
    private Long id;

    @Schema(example = "user1")
    private final String name;

    @Schema(example = "mail@example.com")
    private final String email;

    @Schema(example = "123456789")
    private final String phone;

    @Schema(example = "admin")
    private final Role role;

    @Schema(example = "33333333P")
    private final String dni;

    @Schema(example = "1")
    @Setter
    private Long roomId;


    public static User create(String name, String email, String phone, Role rol, String dni, Long roomId) {
        return new User(null, name, email, phone, rol, dni, roomId);
    }

}
