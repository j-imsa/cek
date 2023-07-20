package ir.jimsa.cek.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDto implements Serializable {
    private long id;
    private String username;
    private String password;
    private String encodedPassword;
    private String role;
}
