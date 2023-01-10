package com.uevitondev.springweb.services;

import com.uevitondev.springweb.domain.Cidade;
import com.uevitondev.springweb.domain.Cliente;
import com.uevitondev.springweb.domain.Endereco;
import com.uevitondev.springweb.domain.enums.Perfil;
import com.uevitondev.springweb.domain.enums.TipoCliente;
import com.uevitondev.springweb.dto.ClienteDTO;
import com.uevitondev.springweb.dto.ClienteNewDTO;
import com.uevitondev.springweb.exceptions.AuthorizationException;
import com.uevitondev.springweb.exceptions.DataintegrityViolationException;
import com.uevitondev.springweb.exceptions.ObjectNotFoundException;
import com.uevitondev.springweb.repositories.ClienteRepository;
import com.uevitondev.springweb.repositories.EnderecoRepository;
import com.uevitondev.springweb.repositories.EstadoRepository;
import com.uevitondev.springweb.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private S3Services s3Services;


    public Cliente findClienteById(Integer id) {
        UserSS userSS = UserService.authenticated();
        if (userSS == null || !userSS.hasRole(Perfil.ADMIN) && !id.equals(userSS.getId())) {
            throw new AuthorizationException("Acesso Negado!");
        }

        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Entidade não encontrada! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    @Transactional(readOnly = true)
    public Cliente insertCliente(Cliente cliente) {
        cliente.setId(null);
        cliente = clienteRepository.save(cliente);
        enderecoRepository.saveAll(cliente.getEnderecos());
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Cliente cliente) {
        Cliente newCliente = findClienteById(cliente.getId());
        updateData(newCliente, cliente);
        return clienteRepository.save(newCliente);
    }

    public void deleteClienteById(Integer id) {
        findClienteById(id);
        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataintegrityViolationException("Não é possível excluir, porque há 'Pedidos' relaci   onadas!");
        }
    }

    public List<Cliente> findAllClientes() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPages, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPages, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDto(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null, null);
    }

    public Cliente fromDto(ClienteNewDTO clienteNewDTO) {
        Cliente cliente = new Cliente(null, clienteNewDTO.getNome(), clienteNewDTO.getEmail(),
                clienteNewDTO.getCpfOuCnpj(), TipoCliente.toEnum(clienteNewDTO.getTipoCliente()),
                bCryptPasswordEncoder.encode(clienteNewDTO.getSenha()));
        Cidade cidade = new Cidade(clienteNewDTO.getCidadeId(), null, null);
        Endereco endereco = new Endereco(null, clienteNewDTO.getLogradouro(), clienteNewDTO.getNumero(), clienteNewDTO.getComplemento(), clienteNewDTO.getBairro(), clienteNewDTO.getCep(), cliente, cidade);
        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(clienteNewDTO.getTelefone1());
        if (clienteNewDTO.getTelefone2() != null) {
            cliente.getTelefones().add(clienteNewDTO.getTelefone2());
        }
        if (clienteNewDTO.getTelefone3() != null) {
            cliente.getTelefones().add(clienteNewDTO.getTelefone3());
        }
        return cliente;
    }

    private void updateData(Cliente newCliente, Cliente cliente) {
        newCliente.setNome(cliente.getNome());
        newCliente.setEmail(cliente.getEmail());
    }

    public URI uploadProfilePicture(MultipartFile multipartFile) {
        return s3Services.uploadFile(multipartFile);
    }


}
