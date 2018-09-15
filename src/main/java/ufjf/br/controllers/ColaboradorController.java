package ufjf.br.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ufjf.br.models.Cidade;
import ufjf.br.models.Colaborador;
import ufjf.br.models.Endereco;
import ufjf.br.models.Usuario;
import ufjf.br.service.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = {"/colaborador"})
public class ColaboradorController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private TipoColaboradorService tipoColaboradorService;

    @Autowired
    private SetorService setorService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ColaboradorService colaboradorService;

    //@Autowired
    //private PerfilUsuarioService perfilUsuarioService;

    @RequestMapping(value = {"index"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/colaborador/index");
        mv.addObject("colaboradores",colaboradorService.findAll());
        return mv;
    }


    @GetMapping(value = "create")
    public ModelAndView create() {
        Colaborador colaborador = new Colaborador();
        colaborador.setAtivo(true);
        Endereco endereco = new Endereco();
        colaborador.setEndereco(endereco);
        ModelAndView mv = new ModelAndView("/colaborador/create");
        mv.addObject("colaborador", colaborador);
        mv.addObject("estados", estadoService.findAllByOrderByNome());
        mv.addObject("setors", setorService.findAll());
        mv.addObject("tipoColaboradors", tipoColaboradorService.findAll());
        return mv;
    }


    @PostMapping(value = "create")
    @Transactional
    public ModelAndView create(@ModelAttribute @Valid Colaborador colaborador, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            //usuario.setAtivo(true);
            ModelAndView mv = new ModelAndView("/colaborador/create", "erros", result.getAllErrors());
            mv.addObject("colaborador", colaborador);
            mv.addObject("estados", estadoService.findAllByOrderByNome());
            mv.addObject("setors", setorService.findAll());
            mv.addObject("tipoColaboradors", tipoColaboradorService.findAll());
            mv.addObject("cidade",cidadeService.findAll());

            return mv;
        }

        if (colaboradorService.findColaboradorByMatricula(colaborador.getMatricula_ufjf())!=null){
            redirectAttributes.addFlashAttribute("messages", "Esta matrícula-ufjf já está em uso !");
            return new ModelAndView("redirect:/colaborador/index");
        }
        if (colaboradorService.findColaboradorByOab(colaborador.getOab())!=null){
            redirectAttributes.addFlashAttribute("messages", "Esta matrícula-OAB já está em uso !");
            return new ModelAndView("redirect:/colaborador/index");
        }


        enderecoService.save(colaborador.getEndereco());
        colaborador.setAtivo(true);
        colaborador.setDt_exclusao(null);
        colaboradorService.save(colaborador);

        redirectAttributes.addFlashAttribute("messages", "Colaborador criado com sucesso.");
        return new ModelAndView("redirect:/colaborador/index");
    }

    @GetMapping(value = "update/{id}")
    public ModelAndView update(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("/colaborador/update");
        Colaborador colaborador = colaboradorService.findOne(id);
        mv.addObject("estados", estadoService.findAllByOrderByNome());
        mv.addObject("setors", setorService.findAll());
        mv.addObject("tipoColaboradors", tipoColaboradorService.findAll());
        mv.addObject("colaborador",colaborador);
        return mv;
    }

    @PostMapping("/update/{id}")
    @Transactional
    public ModelAndView update(@PathVariable("id") Integer id,@ModelAttribute @Valid Colaborador colaborador, BindingResult result, RedirectAttributes redirectAttributes) {
        colaborador.setAtivo(true);
        if (result.hasErrors()){
            ModelAndView mv = new ModelAndView("/colaborador/update", "erros", result.getAllErrors());
            mv.addObject("estados", estadoService.findAllByOrderByNome());
            mv.addObject("setors", setorService.findAll());
            mv.addObject("tipoColaboradors", tipoColaboradorService.findAll());
            mv.addObject("colaborador",colaborador);
             return mv;
        }

        // Restrições de AOB e Matricula ufjf repitidas


        ArrayList<Colaborador> arrayColaboradores = (ArrayList<Colaborador>) colaboradorService.findAll();
        int numColaboradoresOAB =1; // inicia em 1 pois a pessoa que estamos editando está no banco,
        int numColaboradoresMat =1;// ou seja, ela já vai contar.

        //itero sobre os colaboradores procurando outras pessoas que possuam os mesmos numeros oab e matricula ufjf
        for (Colaborador c :arrayColaboradores) {
           if(((!c.getId().equals(colaborador.getId())) && c.getOab().equals(colaborador.getOab())))
             numColaboradoresOAB= numColaboradoresOAB+1;
           if((!c.getId().equals(colaborador.getId())) && c.getMatricula_ufjf().equals(colaborador.getMatricula_ufjf()))
            numColaboradoresMat = numColaboradoresMat+1;
        }

        if (numColaboradoresMat > 1 && numColaboradoresOAB ==1){ // significa que só tem matricula repedita
            redirectAttributes.addFlashAttribute("messages", "Esta matrícula-ufjf já está associada a outro colaborador");
            return new ModelAndView("redirect:/colaborador/index");
        }
        if (numColaboradoresMat == 1 && numColaboradoresOAB > 1){ // significa que só tem oab repetida
            redirectAttributes.addFlashAttribute("messages", "Número OAB informado já está associado a outro colaborador");
            return new ModelAndView("redirect:/colaborador/index");
        }
        if (numColaboradoresMat >1 && numColaboradoresOAB >1){ // significa que ambos os numeros informados já se encontram no banco de dados
            redirectAttributes.addFlashAttribute("messages", "Ambos os números OAB e Matrícula_UFJF já estão asscociados a outros colaboradores");
            return new ModelAndView("redirect:/colaborador/index");

        }
        // salvo os dados para que o Hibernate re-atribua os id's originais aos objetos
        enderecoService.save(colaborador.getEndereco());
        enderecoService.save(colaborador.getEndereco());
        colaboradorService.save(colaborador);

        redirectAttributes.addFlashAttribute("messages", "Colaborador editado com sucesso.");
        return new ModelAndView("redirect:/colaborador/index");
    }



    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes) {
       // Integer enderecoId = usuarioService.findOne(id).getEndereco().getId();
        //Integer  colaboradorId = usuarioService.findOne(id).getColaborador().getId();
       // Integer cidadeId = usuarioService.findOne(id).getEndereco().getCidade().getId();
       // cidadeService.delete(cidadeId);
       // enderecoService.delete(enderecoId);
        //usuarioService.delete(id);
       // colaboradorService.delete(colaboradorId);

        if(!colaboradorService.findOne(id).getAtivo()) {
            redirectAttributes.addFlashAttribute("messages", "O colaborador já está desabilitado.");
            return new ModelAndView("redirect:/colaborador/index");
        }
        else {
            colaboradorService.findOne(id).setAtivo(false);
            Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
            colaboradorService.findOne(id).setDt_exclusao(dataDeHoje);
            colaboradorService.save(colaboradorService.findOne(id));
            redirectAttributes.addFlashAttribute("messages", "colaborador desabilitado com sucesso.");
            return new ModelAndView("redirect:/colaborador/index");
        }
    }

    @GetMapping("/reabilitar/{id}")
    public ModelAndView reabilitar(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes) {
            Colaborador colaborador =colaboradorService.findOne(id);
            colaborador.setAtivo(true); // mudo o atributo de habilitado para true
            colaborador.setDt_exclusao(null);// Retiro a data de exclusao pois o usuario foi reabilitado
            colaboradorService.save(colaborador); // salvo o usuário
            redirectAttributes.addFlashAttribute("messages", "Usuario reabilitado com sucesso."); // adiciono mensagem de sucesso
            return new ModelAndView("redirect:/colaborador/index"); // retorno a view

        }



    @RequestMapping(value = "/get-cidade/{id}")
    public @ResponseBody
    String GetCidade(@PathVariable("id") Integer id) {
        List<Cidade> cidades = cidadeService.findAllByEstado_Id(id);
        StringBuilder retorno = new StringBuilder("<option value=\"\">Selecione uma Cidade</option>");
        for (Cidade cidade : cidades) {

            retorno.append(String.format("<option value=\"%d\">%s</option>", cidade.getId(), cidade));
        }
        return retorno.toString();
    }
}

