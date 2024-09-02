package jv.avaliacao_1_correcao.service.impl;

import jv.avaliacao_1_correcao.dto.ClienteAlteracaoDTO;
import jv.avaliacao_1_correcao.dto.ClienteRequestDTO;
import jv.avaliacao_1_correcao.dto.ClienteResponseDTO;
import jv.avaliacao_1_correcao.dto.ReservaResponseDTO;
import jv.avaliacao_1_correcao.entity.Cliente;
import jv.avaliacao_1_correcao.repository.ClienteRepository;
import jv.avaliacao_1_correcao.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public ClienteResponseDTO cadastraCliente(ClienteRequestDTO clienteRequestDTO) {
        var cliente = new Cliente(clienteRequestDTO);
        clienteRepository.save(cliente);
        return new ClienteResponseDTO(cliente);
    }

    @Override
    public ClienteResponseDTO alteraCliente(Long id, ClienteAlteracaoDTO clienteDTO) {
        //var cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("id nao encontrado"));
        var cliente = getCliente(id);
        if (clienteDTO.nome() != null) {
            cliente.setNome(clienteDTO.nome());
        }
        if (clienteDTO.email() != null) {
            cliente.setEmail(clienteDTO.email());
        }
        clienteRepository.save(cliente);
        return new ClienteResponseDTO(cliente);
    }

    @Override
    public List<ReservaResponseDTO> listaReservasCLiente(Long id) {
        //var cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("id nao encontrado"));
        var cliente = getCliente(id);
        var reservas = cliente.getReservas().stream().map(ReservaResponseDTO::new).toList();
        if (reservas.isEmpty()) {
            throw new RuntimeException("cliente nao tem reservas");
        }
        return reservas;
    }

    @Override
    public Cliente getCliente(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("id do cliente nao encontrado"));
    }
}
