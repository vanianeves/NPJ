package ufjf.br.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ufjf.br.models.*;
import ufjf.br.service.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = {"/agendamento-usuario", "/"})
public class AgendamentoUsuarioController {

    @Autowired
    private AgendamentoUsusarioService agendamentoUsusarioService;

    @Autowired
    ColaboradorService colaboradorService;

    @Autowired
    PreAtendimentoService preAtendimentoService;

    @Autowired
    private UsuarioService usuarioService;



    @RequestMapping(value = {"index"})
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("/agendamento-usuario/index");
        preAtendimentoService.agendamentos();
        mv.addObject("agendamentos", agendamentoUsusarioService.findAllByOrderById());



        mv.addObject("preAtendimento",preAtendimentoService.datasAgendados); //Pode ser que aqui de problema na hora dos teste
        return mv;
    }

    @GetMapping(value = {"create/{id}"})
    public ModelAndView create(@PathVariable("id") Integer id){

        PreAtendimento preAtendimento = preAtendimentoService.findOne(id);
        AgendamentoUsuario agendamentoUsuario= new AgendamentoUsuario();
        agendamentoUsuario.setAluno1(new Colaborador());
        agendamentoUsuario.setAluno2(new Colaborador());

        ModelAndView mv = new ModelAndView("/agendamento-usuario/create");
        mv.addObject("agendamento",agendamentoUsuario);


        mv.addObject("alunos", colaboradorService.findAlunos());
        mv.addObject("parfistas",colaboradorService.findParfistas());
        mv.addObject("atendimento", preAtendimento);

        return mv;
    }



    @PostMapping(value = {"create/{id}"})
    @Transactional
    public ModelAndView create(@PathVariable("id") Integer id, @ModelAttribute @Valid AgendamentoUsuario agendamentoUsuario, BindingResult result, RedirectAttributes redirectAttributes){

        if(result.hasErrors()){

            PreAtendimento atendimento = preAtendimentoService.findOne( agendamentoUsuario.getId());
            ModelAndView mv = new ModelAndView("/agendamento-usuario/create");
            mv.addObject("agendamento",agendamentoUsuario);


            mv.addObject("alunos", colaboradorService.findAlunos());
            mv.addObject("parfistas",colaboradorService.findParfistas());
            mv.addObject("atendimento", atendimento);

            return mv;
        }
        if(preAtendimentoService.findOne(id).getConfirmacao().equals("Confirmado"))
        {
            agendamentoUsuario.setPre_atendimento(preAtendimentoService.findOne(id));

            if(agendamentoUsuario.getAluno2().getId() == null){
                agendamentoUsuario.setAluno2(null);
            }
            if(agendamentoUsuario.getParfista2().getId() == null){
                agendamentoUsuario.setParfista2(null);
            }

            agendamentoUsusarioService.save(agendamentoUsuario);
            redirectAttributes.addFlashAttribute("messages", "Agendamento criado com sucesso.");
            return new ModelAndView("redirect:/agendamento-usuario/index");

        }else{

            redirectAttributes.addFlashAttribute("messages", "Operação Inválida. Pré-agendamento não foi confirmado.");
            return new ModelAndView("redirect:/agendamento-usuario/index");
        }

    }

    @GetMapping(value = "update/{id}")
    public ModelAndView update(@PathVariable("id") Integer id){


        AgendamentoUsuario agendamentoUsuario = agendamentoUsusarioService.findOne(id);


        ModelAndView mv = new ModelAndView("/agendamento-usuario/update");


        mv.addObject("agendamento",agendamentoUsuario);
        mv.addObject("usuarios",colaboradorService.findAll());





        return mv;
    }

    @PostMapping("/update/{id}")
    @Transactional
    public ModelAndView save( @PathVariable("id") Integer id, @ModelAttribute @Valid AgendamentoUsuario agendamentoUsuario, BindingResult result, RedirectAttributes redirectAttributes){


        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("/listaEspera/update");
            mv.addObject("agendamento", agendamentoUsuario);
            mv.addObject("usuarios",colaboradorService.findAll());


            return mv;
        }


        agendamentoUsusarioService.save(agendamentoUsuario);

        redirectAttributes.addFlashAttribute("messages", "Agendamento editado com sucesso.");
        return index();
    }


    @GetMapping(value = {"/delete/{id}"})
    public ModelAndView delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
        agendamentoUsusarioService.delete(id);
        redirectAttributes.addFlashAttribute("messages", "Agendamento excluído com sucesso.");
        return new ModelAndView("redirect:/agendamento-usuario/index");
    }


}
