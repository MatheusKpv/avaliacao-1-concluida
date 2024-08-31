package jv.avaliacao_1_correcao.entity;

import jakarta.persistence.*;
import jv.avaliacao_1_correcao.dto.ReservaRequestDTO;
import jv.avaliacao_1_correcao.enuns.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "reserva")
//@Table(name = "reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserva_seq")
    @SequenceGenerator(name = "reserva_seq", sequenceName = "reserva_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "data_reserva")
    private LocalDate dataReserva;

    @Column(name = "numero_pessoas")
    private Integer numeroPessoas;

    @Column(name = "numero_mesa")
    private Integer numeroMesa;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public Reserva(ReservaRequestDTO reservaRequestDTO, Cliente cliente) {
        this.dataReserva = reservaRequestDTO.dataReserva();
        this.numeroPessoas = reservaRequestDTO.numeroPessoas();
        this.numeroMesa = reservaRequestDTO.numeroMesa();
        this.status = Optional.ofNullable(reservaRequestDTO.status()).orElse(StatusEnum.FEITA);
        this.cliente = cliente;
    }
}
