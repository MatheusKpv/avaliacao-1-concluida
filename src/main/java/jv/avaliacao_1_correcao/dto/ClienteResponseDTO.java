package jv.avaliacao_1_correcao.dto;

import jv.avaliacao_1_correcao.entity.Cliente;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String email
) {
        public ClienteResponseDTO(Cliente cliente) {
                this(cliente.getId(), cliente.getNome(), cliente.getEmail());
        }
}
