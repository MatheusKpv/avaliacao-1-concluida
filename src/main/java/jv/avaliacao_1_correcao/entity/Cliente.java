package jv.avaliacao_1_correcao.entity;

import jakarta.persistence.*;
import jv.avaliacao_1_correcao.dto.ClienteRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_seq")
    @SequenceGenerator(name = "cliente_seq", sequenceName = "cliente_sequence", allocationSize = 1)
    private Long id;

    private String nome;

    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private List<Reserva> reservas;

    public Cliente(ClienteRequestDTO clienteRequestDTO) {
        this.nome = clienteRequestDTO.nome();
        this.email = clienteRequestDTO.email();
    }
}
