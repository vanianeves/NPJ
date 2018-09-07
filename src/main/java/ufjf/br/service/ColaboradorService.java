package ufjf.br.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufjf.br.models.Colaborador;
import ufjf.br.repository.ColaboradorRepository;

import java.util.List;

@Service
public class ColaboradorService {
    @Autowired
    private ColaboradorRepository service;

    public List<Colaborador> findAll() {
        return service.findAll();
    }

    public Colaborador findOne(Integer id) {
        return service.findOne(id);
    }

    public Colaborador save(Colaborador entity) {
        return service.saveAndFlush(entity);
    }


    public void delete(Integer id) {
        service.delete(id);
}

    public Colaborador  findColaboradorByOab(String OAB){
        return service.findColaboradorByOab(OAB);
    }

    public List<Colaborador> findAlunos(){ return service.findAlunos();}

    public List<Colaborador> findParfistas(){return service.findParfistas();}

    public Colaborador findColaboradorByMatricula(String matricula){
        return service.findColaboradorByMatricula(matricula);
    }

    public Colaborador findColaboradorByNome(String nome){
      return service.findColaboradorByNome(nome);
    }



}
