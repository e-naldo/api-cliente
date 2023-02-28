package dev.api.cliente.exception;

import java.time.LocalDateTime;

public record DadosErroBascio(LocalDateTime timestamp, Integer status, String message, String path) {
}