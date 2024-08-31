package jv.avaliacao_1_correcao.dto;

import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDTO(
        @NotBlank(message = "nome nao pode ser nulo")
        String nome,
        @NotBlank(message = "email nao pode ser nulo")
        String email) {
}
