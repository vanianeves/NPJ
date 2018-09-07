package ufjf.br.service;

import ufjf.br.models.Cliente;
import ufjf.br.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() { return clienteRepository.findAll(); }

    public Cliente findOneByCpf (String cpf){ return clienteRepository.findOneByCpf(cpf);}

    public Cliente findOne(Integer id) {
        return clienteRepository.findOne(id);
    }

    public Cliente save(Cliente person) {
        return clienteRepository.saveAndFlush(person);
    }

    public void delete(Integer id) {
        clienteRepository.delete(id);
    }

    public Boolean checkCpfUnique(Cliente cliente) {
        boolean result = true;

        List<Cliente> clientes = clienteRepository.findAll();

        for (Cliente c : clientes){
             if(cliente.getCpf() == c.getCpf()) {
                return false;
             }
        }

        return result;
    }
}
