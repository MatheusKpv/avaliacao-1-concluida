package jv.avaliacao_1_correcao.service;

import jv.avaliacao_1_correcao.dto.ClienteAlteracaoDTO;
import jv.avaliacao_1_correcao.dto.ClienteRequestDTO;
import jv.avaliacao_1_correcao.dto.ClienteResponseDTO;
import jv.avaliacao_1_correcao.dto.ReservaResponseDTO;
import jv.avaliacao_1_correcao.entity.Cliente;

import java.util.List;

public interface ClienteService {
    ClienteResponseDTO cadastraCliente(ClienteRequestDTO clienteRequestDTO);

    ClienteResponseDTO alteraCliente(Long id, ClienteAlteracaoDTO clienteDTO);

    List<ReservaResponseDTO> listaReservasCLiente(Long id);

    Cliente getCliente(Long id);
}
