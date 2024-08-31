package jv.avaliacao_1_correcao.service;

import jv.avaliacao_1_correcao.dto.ReservaRequestDTO;
import jv.avaliacao_1_correcao.dto.ReservaResponseDTO;
import jv.avaliacao_1_correcao.enuns.StatusEnum;

import java.time.LocalDate;

public interface ReservaService {
    ReservaResponseDTO cadastraReserva(ReservaRequestDTO reservaRequestDTO);

    Boolean verificaDisponibilidadeMesa(Integer numeroMesa, LocalDate data);

    ReservaResponseDTO alteraStatus(Long id, StatusEnum status);
}
