package ufjf.br.controllers;

import org.hibernate.annotations.CascadeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.Validate;
import ufjf.br.models.*;
import ufjf.br.service.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = {"/usuario"})
public class UsuarioController {

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

    @Autowired
    private PerfilUsuarioService perfilUsuarioService;

    @RequestMapping(value = {"index"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/usuario/index");
        mv.addObject("usuarios",usuarioService.findAll());
        return mv;
    }


    @GetMapping(value = "create")
    public ModelAndView create() {
        Usuario usuario = new Usuario();
        usuario.setAtivo(true);
        Colaborador colaborador = new Colaborador();
        //usuario.setColaborador(colaborador);
        Endereco endereco = new Endereco();
        usuario.setEndereco(endereco);
        ModelAndView mv = new ModelAndView("/usuario/create");
        mv.addObject("usuario", usuario);
        mv.addObject("estados", estadoService.findAllByOrderByNome());
        mv.addObject("setors", setorService.findAll());
        mv.addObject("tipoColaboradors", tipoColaboradorService.findAll());
        mv.addObject("perfil_usuario", perfilUsuarioService.findAll());
        mv.addObject("colaborador",colaborador);
        return mv;
    }


    @PostMapping(value = "create")
    @Transactional
    public ModelAndView create(@ModelAttribute @Valid Usuario usuario,@ModelAttribute @Valid Colaborador colaborador, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            usuario.setAtivo(true);
            ModelAndView mv = new ModelAndView("/usuario/create", "erros", result.getAllErrors());
            mv.addObject("usuario", usuario);
            mv.addObject("estados", estadoService.findAllByOrderByNome());
            mv.addObject("setors", setorService.findAll());
            mv.addObject("tipoColaboradors", tipoColaboradorService.findAll());

            return mv;
        }
         Endereco novoEndereco = usuario.getEndereco();
             if(colaborador.getTipoColaborador()!= null){
                 colaborador.setUsuario_id(usuario.getId());
                 colaboradorService.save(colaborador);
             }
        else{    usuario.setId_Colaborador(null);
                 colaborador=null;
             }
        perfilUsuarioService.save(usuario.getPerfil_usuario());
        enderecoService.save(novoEndereco);
        usuario.setAtivo(true);
        usuario.setDt_exclusao(null);
        usuarioService.save(usuario);

        // Recuperar id's gravados no bd
        if(colaborador != null) {
            colaborador.setUsuario_id(usuario.getId());
            colaboradorService.save(colaborador);
            usuario.setId_Colaborador(colaborador.getId());
            usuarioService.save(usuario);
        }
 redirectAttributes.addFlashAttribute("messages", "Usuário criado com sucesso.");
        return new ModelAndView("redirect:/usuario/index");
    }

    @GetMapping(value = "update/{id}")
    public ModelAndView update(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("/usuario/update");

        mv.addObject("usuario",usuarioService.findOne(id));
        mv.addObject("estados", estadoService.findAllByOrderByNome());
        mv.addObject("setors", setorService.findAll());
        mv.addObject("tipoColaboradors", tipoColaboradorService.findAll());
        mv.addObject("perfil_usuario", perfilUsuarioService.findAll());
        if(usuarioService.findOne(id).getId_Colaborador().toString() != ""){
            Colaborador colaborador = colaboradorService.findOne(usuarioService.findOne(id).getId_Colaborador());
            mv.addObject("colaborador",colaborador);
        }
        return mv;
    }

    @PostMapping("/update/{id}")
    @Transactional
    public ModelAndView update(@PathVariable("id") Integer id, @ModelAttribute @Valid Usuario usuario, @ModelAttribute Colaborador colaborador, BindingResult result, RedirectAttributes redirectAttributes) {
        usuario.setAtivo(true);
        if (result.hasErrors()){
            ModelAndView mv = new ModelAndView("/usuario/update", "erros", result.getAllErrors());
            mv.addObject("usuario", usuario);
            mv.addObject("estados", estadoService.findAllByOrderByNome());
            mv.addObject("setors", setorService.findAll());
            mv.addObject("tipoColaboradors", tipoColaboradorService.findAll());
            mv.addObject("perfil_usuario", perfilUsuarioService.findAll());
            mv.addObject("colaborador",colaborador);
             return mv;
        }

         // salvo os dados para que o Hibernate re-atribua os id's originais aos objetos
        enderecoService.save(usuario.getEndereco());
        perfilUsuarioService.save(usuario.getPerfil_usuario());
        enderecoService.save(usuario.getEndereco());
        perfilUsuarioService.save(usuario.getPerfil_usuario());
        usuarioService.save(usuario);

        // recupero os id's salvos no banco de dados
         // mas eu só salvo se ela preencheu o tipo de colaborador
        if(colaborador.getTipoColaborador()!=null) {
            colaborador.setUsuario_id(usuario.getId());
            colaboradorService.save(colaborador);
            usuario.setId_Colaborador(colaborador.getId());
            usuarioService.save(usuario);
        }
        redirectAttributes.addFlashAttribute("messages", "Usuario editado com sucesso.");
        return new ModelAndView("redirect:/usuario/index");
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

        if(!usuarioService.findOne(id).getAtivo()) {
            redirectAttributes.addFlashAttribute("messages", "O usuário já está desabilitado.");
            return new ModelAndView("redirect:/usuario/index");
        }
        else {
            usuarioService.findOne(id).setAtivo(false);
            Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
            usuarioService.findOne(id).setDt_exclusao(dataDeHoje);
            usuarioService.save(usuarioService.findOne(id));
            redirectAttributes.addFlashAttribute("messages", "Usuario desabilitado com sucesso.");
            return new ModelAndView("redirect:/usuario/index");
        }
    }

    @GetMapping("/reabilitar/{id}")
    public ModelAndView reabilitar(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes) {

        usuarioService.findOne(id).setAtivo(true); // mudo o atributo de habilitado para true
        usuarioService.findOne(id).setDt_exclusao(null);// Retiro a data de exclusao pois o usuario foi reabilitado
        usuarioService.save(usuarioService.findOne(id)); // salvo o usuário
        redirectAttributes.addFlashAttribute("messages", "Usuario reabilitado com sucesso."); // adiciono mensagem de sucesso
        return new ModelAndView("redirect:/usuario/index"); // retorno a view
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

