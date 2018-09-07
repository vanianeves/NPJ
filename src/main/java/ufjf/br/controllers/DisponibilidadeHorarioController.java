package ufjf.br.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ufjf.br.models.*;
import ufjf.br.service.ColaboradorService;
import ufjf.br.service.DisponibilidadeHorarioService;
import ufjf.br.service.UsuarioService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = {"/disponibilidadeHorario"})
public class DisponibilidadeHorarioController {

    @Autowired
    private DisponibilidadeHorarioService disponibildadeHorarioService;
    @Autowired
    private ColaboradorService colaboradorService;

    @RequestMapping(value = {"index"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/disponibilidadeHorario/index");
        List<DisponibilidadeHorario> disponibilidades = disponibildadeHorarioService.findAll();
        mv.addObject("disponibilidades", disponibildadeHorarioService.findAll());

        return mv;
    }

    @GetMapping(value = "create")
    public ModelAndView create() {
        Colaborador usuario = new Colaborador();

        DisponibilidadeHorario disponibilidade = new DisponibilidadeHorario();
        List<Colaborador> usuarios = colaboradorService.findAll();
        disponibilidade.setColaborador(usuario);
        ModelAndView mv = new ModelAndView("/disponibilidadeHorario/create");
        mv.addObject("disponibilidadeHorario", disponibilidade);
        mv.addObject("usuarios",usuarios);
        return mv;
    }
    @PostMapping(value = "salvarListaDisponibilidades", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseReference create(@RequestBody ListDisponibilidades disponibilidadeHorarios) {
        List<DisponibilidadeHorario> duplicidades = new ArrayList<>();
        boolean houveCadastro = false;
        for (DisponibilidadeHorario disponibilidade:disponibilidadeHorarios.getDisponibilidades()){
            if(!disponibildadeHorarioService.existeDuplicidade(disponibilidade)) {
                disponibildadeHorarioService.save(disponibilidade);
                houveCadastro = true;
            }
            else{
                Colaborador usuario = colaboradorService.findOne(disponibilidade.getColaborador().getId());
                disponibilidade.setColaborador(usuario);
                duplicidades.add(disponibilidade);
            }
        }
        ResponseReference response;
        if (duplicidades.isEmpty()) {
            response = new ResponseReference("/disponibilidadeHorario/index");
        } else {
            response = new ResponseReference("",duplicidades,houveCadastro);
        }
        return response;
    }


    @GetMapping(value = "update/{id}")
    public ModelAndView update(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("/disponibilidadeHorario/update");
        mv.addObject("disponibilidadeHorario", disponibildadeHorarioService.findOne(id));
        List<Colaborador> usuarios = colaboradorService.findAll();
        mv.addObject("usuarios",usuarios);
        return mv;
    }

    @PostMapping("/update/{id}")
    public ModelAndView save(@PathVariable("id") Integer id, @Valid DisponibilidadeHorario disponibilidade, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("/disponibilidadeHorario/update");
            mv.addObject("disponibilidadeHorario", disponibilidade);

            return mv;
        }

        disponibildadeHorarioService.save(disponibilidade);
        return new ModelAndView("redirect:/disponibilidadeHorario/index");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id) {
        disponibildadeHorarioService.delete(id);
        return new ModelAndView("redirect:/disponibilidadeHorario/index");
    }
/*
    @PostMapping(value = "/getCarga", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public LocalTime getCargaDisponivel(@RequestBody Integer id){
        Usuario usuario = usuarioService.findOne(id);
        LocalTime totalCadastrado = disponibildadeHorarioService.getCargaTotal(id);
        //return usuario.getColaborador().getCarga_horaria() - totalCadastrado;
    }
*/
}