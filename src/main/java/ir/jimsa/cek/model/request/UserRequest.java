package ir.jimsa.cek.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserRequest implements Serializable {

    @NotBlank(message = "username can not be blank")
    @NotEmpty(message = "username can not be empty")
    @NotNull(message = "username can not be null")
    private String username;

    @NotBlank(message = "password can not be blank")
    @NotEmpty(message = "password can not be empty")
    @NotNull(message = "password can not be null")
    private String password;

    @NotBlank(message = "role can not be blank")
    @NotEmpty(message = "role can not be empty")
    @NotNull(message = "role can not be null")
    private String role;
}
