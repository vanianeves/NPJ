package ufjf.br.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufjf.br.models.Historico;
import ufjf.br.models.Processos;
import ufjf.br.repository.HistoricoRepository;
import ufjf.br.repository.ProcessoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessosService {

    @Autowired
    private ProcessoRepository service;

    public List<Processos> findAll() {
        return service.findAll();
    }

    public Processos findOne(Integer id) {
        return service.findOne(id);
    }

    public Processos save(Processos entity) {
        return service.saveAndFlush(entity);
    }

    public void delete(Integer id) {
        service.delete(id);
}


    public List<Processos> listarProcessos(Integer id){
        List<Processos> processos;
        List<Processos> ProcessosARetornar= new ArrayList<>();
        processos= service.findAll();
        for (Processos processo: processos) {
            if(processo.getIdAtendimento().equals(id)){
                ProcessosARetornar.add(processo);
            }
        }
        processos=null;
        return  ProcessosARetornar;
    }



}
