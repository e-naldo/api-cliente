package dev.api.cliente.mapper;

import dev.api.cliente.dto.ClienteAtualizacaoDto;
import dev.api.cliente.dto.ClienteCadastroDto;
import dev.api.cliente.dto.ClienteDetalhesDto;
import dev.api.cliente.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteMapper {

    public Cliente paraEntidade(ClienteCadastroDto dto) {
        return new Cliente(
                dto.nome(),
                dto.documento(),
                dto.email(),
                dto.telefone());
    }

    public Cliente paraEntidade(ClienteAtualizacaoDto dto) {
        return new Cliente(
                dto.id(),
                dto.nome(),
                dto.documento(),
                dto.email(),
                dto.telefone());
    }

    public ClienteDetalhesDto paraDetalhesDto(Cliente cliente) {
        return new ClienteDetalhesDto(
                cliente.getId(),
                cliente.getNome(),
                cliente.getDocumento(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getDataCadastro(),
                cliente.getDataAtualizacao()
        );
    }

    public List<ClienteDetalhesDto> paraListagemDto(List<Cliente> clientes) {
        return clientes.stream().map(this::paraDetalhesDto).collect(Collectors.toList());
    }

    public Page<ClienteDetalhesDto> paraListagemDto(Page<Cliente> clientes) {
        return clientes.map(this::paraDetalhesDto);
    }
}
