package com.capgemini.test.code;

import com.capgemini.test.code.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "Operaciones sobre usuarios")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }


    @Operation(summary = "Crear usuario", description = "Guarda un usuario en la sala 1 y retorna su ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Usuario creado"),
                           @ApiResponse(responseCode = "409", description = "Error de validación")})
    @PostMapping
    public ResponseEntity<Map<String, Long>> create( @RequestBody User request) {
        Long id = service.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id", id));
    }

    @Operation(summary = "Obtener usuario por ID")
    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return service.getUser(id);
    }
}