package com.capgemini.test.code;

import com.capgemini.test.code.errors.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckDniRequest {
    private String dni;

}
