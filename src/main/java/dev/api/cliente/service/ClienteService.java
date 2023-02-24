package dev.api.cliente.service;

import dev.api.cliente.model.Cliente;
import dev.api.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
                .orElseThrow(RuntimeException::new);

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
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Cliente> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public void excluirPorId(Long id) {
        repository.deleteById(id);
    }
}
