package ufjf.br.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufjf.br.models.PerfilUsuario;
import ufjf.br.repository.PerfilUsuarioRepository;

import java.util.List;

@Service
public class PerfilUsuarioService {
    @Autowired
    PerfilUsuarioRepository perfilUsuarioRepository;
    public List<PerfilUsuario> findAll() {return perfilUsuarioRepository.findAll();}
    public PerfilUsuario findOne(Integer id) {return perfilUsuarioRepository.findOne(id);}
    public PerfilUsuario save(PerfilUsuario perfilUsuario) {return perfilUsuarioRepository.saveAndFlush(perfilUsuario);}
    public void delete(Integer id) {perfilUsuarioRepository.delete(id);}
}
