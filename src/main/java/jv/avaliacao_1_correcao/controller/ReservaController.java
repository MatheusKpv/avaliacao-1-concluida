package jv.avaliacao_1_correcao.controller;

import jakarta.validation.Valid;
import jv.avaliacao_1_correcao.dto.ErrorMessageDTO;
import jv.avaliacao_1_correcao.dto.ReservaRequestDTO;
import jv.avaliacao_1_correcao.dto.ReservaResponseDTO;
import jv.avaliacao_1_correcao.enuns.StatusEnum;
import jv.avaliacao_1_correcao.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<?> cadastraReserva(@Valid @RequestBody ReservaRequestDTO reservaRequestDTO) {
        try {
            ReservaResponseDTO reservaResponseDTO = reservaService.cadastraReserva(reservaRequestDTO);
            return ResponseEntity.ok(reservaResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessageDTO(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> verificaDisponibilidadeMesa(@RequestParam Integer numeroMesa, @RequestParam LocalDate data) {
        try {
            Boolean indisponivel = reservaService.verificaDisponibilidadeMesa(numeroMesa, data);
            return ResponseEntity.ok("Mesa disponvel");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessageDTO(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alteraStatus(@PathVariable Long id, @RequestParam StatusEnum status) {
        try {
            ReservaResponseDTO reservaResponseDTO = reservaService.alteraStatus(id, status);
            return ResponseEntity.ok(reservaResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessageDTO(e.getMessage()));
        }
    }
}
