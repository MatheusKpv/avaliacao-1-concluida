package jv.avaliacao_1_correcao.controller;

import jakarta.validation.Valid;
import jv.avaliacao_1_correcao.dto.*;
import jv.avaliacao_1_correcao.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<?> cadastraCliente(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        try {
            ClienteResponseDTO clienteResponseDTO = clienteService.cadastraCliente(clienteRequestDTO);
            return ResponseEntity.ok(clienteResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessageDTO(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alteraCliente(@PathVariable Long id, @RequestBody ClienteAlteracaoDTO clienteDTO) {
        try {
            ClienteResponseDTO clienteResponseDTO = clienteService.alteraCliente(id, clienteDTO);
            return ResponseEntity.ok(clienteResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessageDTO(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listaReservasCLiente(@PathVariable Long id) {
        try {
            List<ReservaResponseDTO> reservas = clienteService.listaReservasCLiente(id);
            return ResponseEntity.ok(reservas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessageDTO(e.getMessage()));
        }
    }
}
