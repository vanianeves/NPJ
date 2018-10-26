package ufjf.br.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ufjf.br.models.*;
import ufjf.br.service.ClienteService;

import ufjf.br.service.DemandaJuridicaService;
import ufjf.br.service.ListaEsperaService;
import ufjf.br.service.PreAtendimentoService;
import javax.transaction.Transactional;
import javax.validation.Valid;



@Controller
@RequestMapping(value = {"/controleHorario/"})
public class controleHorariosController {

    private  Logger logger = Logger.getLogger(controleHorariosController.class);

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PreAtendimentoService preAtendimentoService;

    @Autowired
    private ListaEsperaService listaEsperaService;

    @Autowired
    private DemandaJuridicaService demandaJuridicaService;


    @RequestMapping(value = {"index"})
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("/controleHorario/index");
        preAtendimentoService.agendamentos();
        mv.addObject("preAtendimeto",preAtendimentoService.horarioExtraordinario);
        mv.addObject("atendimentos", preAtendimentoService.datasAgendados);
        return mv;
    }


    @GetMapping(value = "create/{id}")
    public ModelAndView create(@PathVariable("id") Integer id){

        PreAtendimento preAtendimento = preAtendimentoService.findOne(id);

        ModelAndView mv = new ModelAndView("/controleHorario/create");

        mv.addObject("atendimento",  preAtendimento);
        mv.addObject("clientes", this.clienteService.findAll());
        mv.addObject("demanda",demandaJuridicaService.findAll());

        return mv;


    }

    @PostMapping("/create/{id}")
    @Transactional
    public ModelAndView create( @PathVariable("id") Integer id, @ModelAttribute @Valid PreAtendimento preAtendimento, BindingResult result){

        logger.info(preAtendimento.hashCode());
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("/controleHorario/create");
            mv.addObject("atendimento", preAtendimento);
            mv.addObject("clientes", this.clienteService.findAll());
            mv.addObject("demanda",demandaJuridicaService.findAll());

            return mv;
        }




        PreAtendimento atendimento = preAtendimentoService.findOne(id);
        atendimento.setCliente(preAtendimento.getCliente());
        atendimento.setConfirmacao("Não Confirmado");
        atendimento.setDemandaJuridica(preAtendimento.getDemandaJuridica());
        preAtendimentoService.save(atendimento);

        return new ModelAndView("redirect:/controleHorario/index");

    }



    @GetMapping(value = "update/{id}")
    public ModelAndView update(@PathVariable("id") Integer id){

        PreAtendimento preAtendimento = preAtendimentoService.findOne(id);
        ModelAndView mv = new ModelAndView("/controleHorario/update");


        mv.addObject("atendimento",  preAtendimento);
        mv.addObject("clientes", this.clienteService.findAll());
        mv.addObject("demanda",demandaJuridicaService.findAll());



        return mv;
    }

    @PostMapping("/update/{id}")
    @Transactional
    public ModelAndView save( @PathVariable("id") Integer id, @ModelAttribute @Valid PreAtendimento preAtendimento, BindingResult result){

        logger.info(preAtendimento.hashCode());
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("/controleHorario/update ");
            mv.addObject("atendimento", preAtendimento);
            mv.addObject("clientes", this.clienteService.findAll());
            mv.addObject("demanda",demandaJuridicaService.findAll());

            return mv;
        }


        PreAtendimento atendimento = preAtendimentoService.findOne(id);
        atendimento.setCliente(preAtendimento.getCliente());
        atendimento.setDemandaJuridica(preAtendimento.getDemandaJuridica());
        preAtendimentoService.save(atendimento);

        return new ModelAndView("redirect:/controleHorario/index");
    }


    @GetMapping(value = {"/delete/{id}"})
    public ModelAndView delete(@PathVariable("id") Integer id){

        PreAtendimento atendimento = preAtendimentoService.findOne(id);
        atendimento.setCliente(null);
        atendimento.setDemandaJuridica(null);
        preAtendimentoService.save(atendimento);
        return new ModelAndView("redirect:/controleHorario/index");
    }

    @GetMapping("/recadastrar/{id}")
    public ModelAndView recadastrar(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes){

        PreAtendimento atendimento = preAtendimentoService.findOne(id);

        if(atendimento.getConfirmacao().equals("Não Confirmado")) {

            ListaEspera listaEspera = new ListaEspera();
            listaEspera.setCliente(atendimento.getCliente());
            listaEspera.setDemandaJuridica(atendimento.getDemandaJuridica());

            // System.out.println("Lista de Espera: "+listaEspera.getCliente().getNome() + " "+ listaEspera.getDemandaJuridica() + " "+ listaEspera.getId());
            listaEsperaService.save(listaEspera);


            if (listaEsperaService.findAll() != null ) {

                ListaEspera espera = listaEsperaService.findFirstByOrderByIdAsc();

                Cliente cliente = espera.getCliente();
                System.out.println( espera.getCliente().getId() != atendimento.getCliente().getId());

                if (espera.getCliente().getId() != atendimento.getCliente().getId())
                {
                    atendimento.setCliente(cliente);
                    atendimento.setDemandaJuridica(espera.getDemandaJuridica());
                    atendimento.setConfirmacao("Não Confirmado");

                    listaEsperaService.delete(espera.getId());

                }else if (!espera.getDemandaJuridica().getDemanda().equals(atendimento.getDemandaJuridica().getDemanda()))
                {
                    atendimento.setCliente(cliente);
                    atendimento.setDemandaJuridica(espera.getDemandaJuridica());
                    atendimento.setConfirmacao("Não Confirmado");

                    listaEsperaService.delete(espera.getId());

                }else
                {
                    atendimento.setCliente(null);
                    atendimento.setDemandaJuridica(null);
                }

                preAtendimentoService.save(atendimento);
            }

            redirectAttributes.addFlashAttribute("messages", "Operação realizada. Cliente enviado para a Lista de Espera.");
            return new ModelAndView("redirect:/controleHorario/index");
        }else{
            redirectAttributes.addFlashAttribute("messages", "Operação Inválida. Pré-agendamento já foi confirmado.");
            return new ModelAndView("redirect:/controleHorario/index");
        }


    }

    @GetMapping("/confirmar/{id}")
    public ModelAndView confirmar(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){

        PreAtendimento preAtendimento = preAtendimentoService.findOne(id);
        if(preAtendimento !=null && preAtendimento.getConfirmacao().equals("Não Confirmado"))
        {
           preAtendimento.setConfirmacao("Confirmado");
           preAtendimentoService.save(preAtendimento);

            redirectAttributes.addFlashAttribute("messages", "Pré-Agendamento confirmado");
            return new ModelAndView("redirect:/controleHorario/index");
        }
        else{
            redirectAttributes.addFlashAttribute("messages", "Pré-Agendamento já confirmado");
            return new ModelAndView("redirect:/controleHorario/index");
        }
    }

}


