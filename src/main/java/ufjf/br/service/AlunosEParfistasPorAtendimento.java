package ufjf.br.service;


import org.springframework.stereotype.Service;
import ufjf.br.models.AlunosParfistasPorAtendimento;
import ufjf.br.repository.AlunosEParfistasRepository;

import java.util.List;

@Service
public class AlunosEParfistasPorAtendimento {

    private AlunosEParfistasRepository servico;

   public AlunosParfistasPorAtendimento findOne(Integer id){
       return servico.findOne(id);
    }
    public List<AlunosParfistasPorAtendimento>findAll(){
       return servico.findAll();
    }
    public void delete(Integer id){
       servico.delete(id);
    }




}
