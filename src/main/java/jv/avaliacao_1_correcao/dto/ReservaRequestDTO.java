package jv.avaliacao_1_correcao.dto;

import jakarta.validation.constraints.NotNull;
import jv.avaliacao_1_correcao.enuns.StatusEnum;

import java.time.LocalDate;

public record ReservaRequestDTO(
        @NotNull(message = "data reserva nao pode ser nulo")
        LocalDate dataReserva,
        @NotNull(message = "numero pessoa nao pode ser nulo")
        Integer numeroPessoas,
        @NotNull(message = "numero mesa nao pode ser nulo")
        Integer numeroMesa,

        StatusEnum status,

        @NotNull(message = "id cliente nao pode ser nulo")
        Long id_cliente
) {
}
