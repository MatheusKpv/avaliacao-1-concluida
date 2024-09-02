package jv.avaliacao_1_correcao.repository;

import jv.avaliacao_1_correcao.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    Boolean existsByNumeroMesaAndDataReserva(Integer numeroMesa, LocalDate data);
}
