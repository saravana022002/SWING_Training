package net.saravana.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class IdentityDto {
    private String userName;
    private String phone;

    private String email;
    private char[] password;

}
