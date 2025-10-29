package ci.trabrouss.authservice.dto;

public record UserCreateDTO(
        String username,
        String password,

        String firstname,
        String lastname,

        String role
) {}
