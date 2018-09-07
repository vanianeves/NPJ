package ufjf.br.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ufjf.br.models.Usuario;
import ufjf.br.repository.UsuarioRepository;
import ufjf.br.models.Colaborador;
import ufjf.br.service.ColaboradorService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.time.LocalTime;
import java.util.List;

@Service
@PersistenceContext(type = PersistenceContextType.EXTENDED)
public class UsuarioService {
    private EntityManager entityManager;


    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UsuarioRepository usuarioRepository;
    private ColaboradorService colaboradorService;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findOne(Integer id) {

        return usuarioRepository.findOne(id);
    }

    public Usuario save(Usuario usuario) {
        //if (!usuario.getPassword().isEmpty())
          //  usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.saveAndFlush(usuario);
    }

    public Usuario update(Usuario usuario){
        //entityManager.flush();
        return  entityManager.merge(usuario);

     }

    public void delete(Integer id) {
        usuarioRepository.delete(id);
    }
    }

