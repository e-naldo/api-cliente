package dev.api.cliente.dto;

import java.time.LocalDateTime;

public record ClienteDetalhesDto(
        Long id,
        String nome,
        String documento,
        String email,
        String telefone,
        LocalDateTime dataCadastro,
        LocalDateTime dataAtualizacao
) {
}
