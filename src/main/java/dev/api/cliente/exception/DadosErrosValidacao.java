package dev.api.cliente.exception;

import org.springframework.validation.FieldError;

public record DadosErrosValidacao(String campo, String mnesagem) {
    public DadosErrosValidacao(FieldError erro) {
        this(erro.getField(), erro.getDefaultMessage());
    }
}