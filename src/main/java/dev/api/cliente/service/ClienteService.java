package dev.api.cliente.service;

import dev.api.cliente.model.Cliente;
import dev.api.cliente.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClienteService implements GenericService<Cliente> {

    @Autowired
    private ClienteRepository repository;

    @Override
    public Cliente cadastrar(Cliente cliente) {
        cliente.setDataCadastro(LocalDateTime.now());

        return repository.save(cliente);
    }

    @Override
    public Cliente atualizar(Cliente cliente) {
        Cliente clienteAtual = repository.findById(cliente.getId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        clienteAtual.setNome(cliente.getNome());
        clienteAtual.setEmail(cliente.getEmail());
        clienteAtual.setDocumento(cliente.getDocumento());
        clienteAtual.setTelefone(cliente.getTelefone());
        clienteAtual.setDataAtualizacao(LocalDateTime.now());

        return repository.save(clienteAtual);
    }

    @Override
    public Cliente buscarPorId(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
    }

    @Override
    public List<Cliente> buscarTodos() {
        return repository.findAll();
    }

    public Page<Cliente> buscarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void excluirPorId(Long id) {
        repository.deleteById(id);
    }
}
