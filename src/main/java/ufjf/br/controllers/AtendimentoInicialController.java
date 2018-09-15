package ufjf.br.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ufjf.br.models.*;
import ufjf.br.service.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

import java.util.*;

@Controller
@RequestMapping(value = {"/atendimento"})
public class AtendimentoInicialController {


    @Autowired
    private AtendimentoInicialService atendimentoInicialService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ColaboradorService colaboradorService;

    @Autowired
    private DesdobramentosService desdobramentosService;

    @Autowired
    private HistoricoService historicoService;

    @Autowired
    private ProcessosService processosService;


    @RequestMapping(value = {"index"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/atendimento/index");
        mv.addObject("atendimentos", atendimentoInicialService.findAll());
        return mv;
    }

    @GetMapping(value = "create")
    public ModelAndView create(){
        AtendimentoInicial atendimento = new AtendimentoInicial();
        Date data = new Date();
        data.getTime();
        String ajusta = data.toString();
        String diaSemana = ajusta.substring(0, 3);
        String dia = ajusta.substring(8, 10);
        String mes = ajusta.substring(4, 7);
        String ano = ajusta.substring(24, 28);
        ajusta = diaSemana + " " + dia + "/" + mes + "/" + ano;
        atendimento.setDtAtendimento(ajusta);
        List<Cliente> clientes = clienteService.findAll();
        List<Colaborador> colaboradores = colaboradorService.findAll();
        List<Colaborador> parfistas = new ArrayList<>();
        List<Colaborador> professores = new ArrayList<>();
        List<Colaborador> alunos = new ArrayList<>();
        List<Colaborador> bolsistasTP = new ArrayList<>();
        List<Desdobramentos> desdobramentos = desdobramentosService.findAll();


        for (Colaborador colaborador:colaboradores)
             {
                 if(colaborador.getTipoColaborador().getTipo().equals("PROFESSOR")&& colaborador.getAtivo())
                     professores.add(colaborador);
                 if(colaborador.getTipoColaborador().getTipo().equals("ALUNO")&& colaborador.getAtivo())
                     alunos.add(colaborador);
                 if(colaborador.getTipoColaborador().getTipo().equals("BOLSISTA_TP")&& colaborador.getAtivo())
                     bolsistasTP.add(colaborador);
                 if(colaborador.getTipoColaborador().getTipo().equals("PARFISTA")&& colaborador.getAtivo())
                     parfistas.add(colaborador);
             }

        ModelAndView mv = new ModelAndView("/atendimento/create");
        mv.addObject("atendimento",atendimento);
        mv.addObject("clientes",clientes);
        mv.addObject("alunos",alunos);
        mv.addObject("parfistas",parfistas);
        mv.addObject("professores",professores);
        mv.addObject("bolsistas",bolsistasTP);
        mv.addObject("colaboradores",colaboradores);
        mv.addObject("desdobramentos",desdobramentos);

        return mv;
    }







   @PostMapping(value="create")
    public ModelAndView create(@Valid AtendimentoInicial atendimento, BindingResult resultado,RedirectAttributes redirectAttributes){
        if(resultado.hasErrors()){

            List<Cliente> clientes = clienteService.findAll();
            List<Colaborador> colaboradores = colaboradorService.findAll();
            List<Colaborador> parfistas = new ArrayList<>();
            List<Colaborador> professores = new ArrayList<>();
            List<Colaborador> alunos = new ArrayList<>();
            List<Colaborador> bolsistasTP = new ArrayList<>();
            List<Desdobramentos> desdobramentos = desdobramentosService.findAll();

            for (Colaborador colaborador:colaboradores)
            {
                if(colaborador.getTipoColaborador().getTipo().equals("PROFESSOR")&& colaborador.getAtivo())
                    professores.add(colaborador);
                if(colaborador.getTipoColaborador().getTipo().equals("ALUNO")&& colaborador.getAtivo())
                    alunos.add(colaborador);
                if(colaborador.getTipoColaborador().getTipo().equals("BOLSISTA_TP")&& colaborador.getAtivo())
                    bolsistasTP.add(colaborador);
                if(colaborador.getTipoColaborador().getTipo().equals("PARFISTA")&& colaborador.getAtivo())
                    parfistas.add(colaborador);
            }
            ModelAndView mv = new ModelAndView("/atendimento/create");
            mv.addObject("atendimento",atendimento);
            mv.addObject("clientes",clientes);
            mv.addObject("alunos",alunos);
            mv.addObject("parfistas",parfistas);
            mv.addObject("professores",professores);
            mv.addObject("bolsistas",bolsistasTP);
            mv.addObject("colaboradores",colaboradores);
            mv.addObject("desdobramentos",desdobramentos);

            return mv;
        }
        atendimentoInicialService.save(atendimento);

       ModelAndView mv = new ModelAndView("/atendimento/index");
       return mv;
   }


   @GetMapping(value ="update/{id}")
   public ModelAndView update(@PathVariable ("id") Integer id ){
       ModelAndView mv = new ModelAndView("/atendimento/update");
       AtendimentoInicial atendimento = atendimentoInicialService.findOne(id);
       List<Cliente> clientes = clienteService.findAll();
       List<Colaborador> colaboradores = colaboradorService.findAll();
       List<Colaborador> parfistas = new ArrayList<>();
       List<Colaborador> professores = new ArrayList<>();
       List<Colaborador> alunos = new ArrayList<>();
       List<Colaborador> bolsistasTP = new ArrayList<>();
       List<Desdobramentos> desdobramentos = desdobramentosService.findAll();

       for (Colaborador colaborador:colaboradores)
       {
           if((colaborador.getTipoColaborador().getTipo().equals("PROFESSOR")) && (colaborador.getAtivo()))
               professores.add(colaborador);
           if(colaborador.getTipoColaborador().getTipo().equals("ALUNO") && colaborador.getAtivo())
               alunos.add(colaborador);
           if(colaborador.getTipoColaborador().getTipo().equals("BOLSISTA_TP")&& colaborador.getAtivo())
               bolsistasTP.add(colaborador);
           if(colaborador.getTipoColaborador().getTipo().equals("PARFISTA")&& colaborador.getAtivo())
               parfistas.add(colaborador);
       }
       mv.addObject("atendimento",atendimento);
       mv.addObject("clientes",clientes);
       mv.addObject("alunos",alunos);
       mv.addObject("parfistas",parfistas);
       mv.addObject("professores",professores);
       mv.addObject("bolsistas",bolsistasTP);
       mv.addObject("colaboradores",colaboradores);
       mv.addObject("desdobramentos",desdobramentos);
      return mv;
   }

    @GetMapping(value ="view/{id}")
    public ModelAndView view(@PathVariable ("id") Integer id ){
        ModelAndView mv = new ModelAndView("/atendimento/view");
        AtendimentoInicial atendimento = atendimentoInicialService.findOne(id);
        List<Cliente> clientes = clienteService.findAll();
        List<Colaborador> colaboradores = colaboradorService.findAll();
        List<Colaborador> parfistas = new ArrayList<>();
        List<Colaborador> professores = new ArrayList<>();
        List<Colaborador> alunos = new ArrayList<>();
        List<Colaborador> bolsistasTP = new ArrayList<>();
        List<Desdobramentos> desdobramentos = desdobramentosService.findAll();

        List<Historico> historicos = historicoService.listarHistoricos(id);
        List<Processos> processos = processosService.listarProcessos(id);


        for (Colaborador colaborador:colaboradores)
        {
            if((colaborador.getTipoColaborador().getTipo().equals("PROFESSOR")) && (colaborador.getAtivo()))
                professores.add(colaborador);
            if(colaborador.getTipoColaborador().getTipo().equals("ALUNO") && colaborador.getAtivo())
                alunos.add(colaborador);
            if(colaborador.getTipoColaborador().getTipo().equals("BOLSISTA_TP")&& colaborador.getAtivo())
                bolsistasTP.add(colaborador);
            if(colaborador.getTipoColaborador().getTipo().equals("PARFISTA")&& colaborador.getAtivo())
                parfistas.add(colaborador);
        }
        mv.addObject("atendimento",atendimento);
        mv.addObject("clientes",clientes);
        mv.addObject("alunos",alunos);
        mv.addObject("parfistas",parfistas);
        mv.addObject("professores",professores);
        mv.addObject("bolsistas",bolsistasTP);
        mv.addObject("colaboradores",colaboradores);
        mv.addObject("desdobramentos",desdobramentos);
        mv.addObject("processos",processos);
        mv.addObject("historicos",historicos);
        return mv;
    }





   @PostMapping(value ="update/{id}")
   public ModelAndView update(@ModelAttribute @Valid AtendimentoInicial atendimentoInicial, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            ModelAndView mv = new ModelAndView("/atendimento/update");
            List<Cliente> clientes = clienteService.findAll();
            List<Colaborador> colaboradores = colaboradorService.findAll();
            List<Colaborador> parfistas = new ArrayList<>();
            List<Colaborador> professores = new ArrayList<>();
            List<Colaborador> alunos = new ArrayList<>();
            List<Colaborador> bolsistasTP = new ArrayList<>();
            List<Desdobramentos> desdobramentos = desdobramentosService.findAll();

            for (Colaborador colaborador:colaboradores)
            {
                if(colaborador.getTipoColaborador().getTipo().equals("PROFESSOR")&& colaborador.getAtivo())
                    professores.add(colaborador);
                if(colaborador.getTipoColaborador().getTipo().equals("ALUNO")&& colaborador.getAtivo())
                    alunos.add(colaborador);
                if(colaborador.getTipoColaborador().getTipo().equals("BOLSISTA_TP")&& colaborador.getAtivo())
                    bolsistasTP.add(colaborador);
                if(colaborador.getTipoColaborador().getTipo().equals("PARFISTA")&& colaborador.getAtivo())
                    parfistas.add(colaborador);
            }
            mv.addObject("atendimento",atendimentoInicial);
            mv.addObject("clientes",clientes);
            mv.addObject("alunos",alunos);
            mv.addObject("parfistas",parfistas);
            mv.addObject("professores",professores);
            mv.addObject("bolsistas",bolsistasTP);
            mv.addObject("colaboradores",colaboradores);
            mv.addObject("desdobramentos",desdobramentos);
            return  mv;
        }
         //implementar as validações

         atendimentoInicialService.save(atendimentoInicial);

       redirectAttributes.addFlashAttribute("messages", "Atendimento editado com sucesso");
       return new ModelAndView("redirect:/atendimento/index");


   }









@RequestMapping(value ="delete/{id}")
    public ModelAndView delete(@PathVariable ("id")Integer id, RedirectAttributes redirect){
       atendimentoInicialService.delete(id);
    redirect.addFlashAttribute("messages", "Atendimento excluído!");
    return new ModelAndView("redirect:/atendimento/index");
}


}
