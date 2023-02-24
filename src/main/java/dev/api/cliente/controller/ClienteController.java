package dev.api.cliente.controller;

import dev.api.cliente.dto.ClienteAtualizacaoDto;
import dev.api.cliente.dto.ClienteCadastroDto;
import dev.api.cliente.dto.ClienteDetalhesDto;
import dev.api.cliente.mapper.ClienteMapper;
import dev.api.cliente.model.Cliente;
import dev.api.cliente.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @Autowired
    private ClienteMapper mapper;

    @PostMapping
    public ResponseEntity<ClienteDetalhesDto> cadastrar(@RequestBody @Valid ClienteCadastroDto dto) {
        Cliente cliente = service.cadastrar(mapper.paraEntidade(dto));

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
        URI uri = uriComponentsBuilder.path("api/v1/clientes/{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body(mapper.paraDetalhesDto(cliente));
    }

    @PutMapping
    public ResponseEntity<ClienteDetalhesDto> atualizar(@RequestBody @Valid ClienteAtualizacaoDto dto) {
        Cliente cliente = service.atualizar(mapper.paraEntidade(dto));

        return ResponseEntity.ok(mapper.paraDetalhesDto(cliente));
    }

    @GetMapping("{id}")
    public ResponseEntity<ClienteDetalhesDto> buscarPorId(@PathVariable Long id) {
        Cliente cliente = service.buscarPorId(id);

        return ResponseEntity.ok(mapper.paraDetalhesDto(cliente));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> excluirPorId(@PathVariable Long id) {
        service.excluirPorId(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ClienteDetalhesDto>> buscarTodos() {
        List<Cliente> clientes = service.buscarTodos();

        return ResponseEntity.ok(mapper.paraListagemDto(clientes));
    }
}
