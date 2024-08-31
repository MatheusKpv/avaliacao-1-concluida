package jv.avaliacao_1_correcao.dto;

import jakarta.validation.constraints.NotBlank;

public record ClienteAlteracaoDTO(
        String nome,
        String email
) {
}
