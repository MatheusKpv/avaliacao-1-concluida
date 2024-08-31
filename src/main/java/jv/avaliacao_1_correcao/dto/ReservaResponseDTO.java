package jv.avaliacao_1_correcao.dto;

import jakarta.validation.constraints.NotNull;
import jv.avaliacao_1_correcao.entity.Cliente;
import jv.avaliacao_1_correcao.entity.Reserva;
import jv.avaliacao_1_correcao.enuns.StatusEnum;

import java.time.LocalDate;

public record ReservaResponseDTO(
        Long id,
        LocalDate dataReserva,
        Integer numeroPessoas,
        Integer numeroMesa,
        StatusEnum status,
        ClienteResponseDTO cliente
) {
        public ReservaResponseDTO(Reserva reserva) {
                this(reserva.getId(), reserva.getDataReserva(), reserva.getNumeroPessoas(),
                        reserva.getNumeroMesa(), reserva.getStatus(), new ClienteResponseDTO(reserva.getCliente()));
        }
}
