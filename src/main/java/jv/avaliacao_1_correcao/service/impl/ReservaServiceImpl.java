package jv.avaliacao_1_correcao.service.impl;

import jv.avaliacao_1_correcao.dto.ReservaRequestDTO;
import jv.avaliacao_1_correcao.dto.ReservaResponseDTO;
import jv.avaliacao_1_correcao.entity.Cliente;
import jv.avaliacao_1_correcao.entity.Reserva;
import jv.avaliacao_1_correcao.enuns.StatusEnum;
import jv.avaliacao_1_correcao.repository.ClienteRepository;
import jv.avaliacao_1_correcao.repository.ReservaRepository;
import jv.avaliacao_1_correcao.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class ReservaServiceImpl implements ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public ReservaResponseDTO cadastraReserva(ReservaRequestDTO reservaRequestDTO) {
        var cliente = clienteRepository.findById(reservaRequestDTO.id_cliente()).orElseThrow(() -> new RuntimeException("cliente nao existe"));
        List<Reserva> reservas = reservaRepository.findByNumeroMesaAndDataReserva(reservaRequestDTO.numeroMesa(), reservaRequestDTO.dataReserva());
        if (!reservas.isEmpty()) {
            throw new RuntimeException("mesa nao disponivel");
        }
        if (reservaRequestDTO.dataReserva().isBefore(LocalDate.now())) {
            throw new RuntimeException("reserve para uma data valida");
        }
        if (reservaRequestDTO.numeroPessoas() > 10) {
            throw new RuntimeException("numero maximo de pessoas por mesa é 10");
        }
        if (reservaRequestDTO.numeroMesa() < 1 || reservaRequestDTO.numeroMesa() > 20) {
            throw new RuntimeException("escolha uma mesa entre 1 e 20");
        }
        var reserva = new Reserva(reservaRequestDTO, cliente);
        reservaRepository.save(reserva);
        return new ReservaResponseDTO(reserva);
    }

    @Override
    public Boolean verificaDisponibilidadeMesa(Integer numeroMesa, LocalDate data) {
        List<Reserva> reservas = reservaRepository.findByNumeroMesaAndDataReserva(numeroMesa, data);
        return reservas.isEmpty();
    }

    @Override
    public ReservaResponseDTO alteraStatus(Long id, StatusEnum status) {
        var reserva = reservaRepository.findById(id).orElseThrow(() -> new RuntimeException("id nao encontrado"));
        if (Objects.equals(status.toString(), "CONCLUIDA") && reserva.getDataReserva().isBefore(LocalDate.now())) {
            throw new RuntimeException("reserva só pode ser concluida caso data seja maior ou igual a hoje");
        }
        if (Objects.equals(status.toString(), "CANCELADA")) {
            if (reserva.getDataReserva().equals(LocalDate.now())) {
                throw new RuntimeException("reserva só pode ser cancelada com um dia de antecedencia");
            }
        }
        reserva.setStatus(status);
        reservaRepository.save(reserva);
        return new ReservaResponseDTO(reserva);
    }
}
