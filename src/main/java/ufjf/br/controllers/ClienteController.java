package ufjf.br.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ufjf.br.models.Cidade;
import ufjf.br.models.Cliente;
import ufjf.br.models.Endereco;
import ufjf.br.service.CidadeService;
import ufjf.br.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import ufjf.br.service.EnderecoService;
import ufjf.br.service.EstadoService;

import javax.swing.*;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = {"/cliente"})
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private CidadeService cidadeService;

    @RequestMapping(value = {"index"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/cliente/index");
        mv.addObject("clientes", clienteService.findAll());

        return mv;
    }

    @GetMapping(value = "view/{id}")
    public ModelAndView view(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("/cliente/view");
        mv.addObject("cliente", clienteService.findOne(id));
        mv.addObject("estados", estadoService.findAllByOrderByNome());
        mv.addObject("cidades", cidadeService.findAllByOrderByNome());

        return mv;
    }

    @GetMapping(value = "create")
    public ModelAndView create() {
        Cliente model = new Cliente();
        Endereco endereco = new Endereco();
        ModelAndView mv = new ModelAndView("/cliente/create");
        mv.addObject("cliente", model);
        mv.addObject("estados", estadoService.findAllByOrderByNome());
        mv.addObject("cidades", cidadeService.findAllByOrderByNome());

        return mv;
    }

    @PostMapping(value = "create")
    //@Transactional
    public ModelAndView create(@ModelAttribute @Valid Cliente cliente, BindingResult result, RedirectAttributes redirectAttributes) {
        try {
            if (result.hasErrors()) {
                ModelAndView mv = new ModelAndView("/cliente/create", "erros", result.getAllErrors());
                mv.addObject("cliente", cliente);
                mv.addObject("estados", estadoService.findAllByOrderByNome());
                mv.addObject("cidades", cidadeService.findAllByOrderByNome());

                return mv;
            }

            Cliente cliente1 = clienteService.findOneByCpf(cliente.getCpf());

            if(cliente1 == null) {


                    enderecoService.save(cliente.getEndereco());
                    cliente.setAtivo(true);
                    cliente.setSituacao("HABILITADO");
                    clienteService.save(cliente);
                    return new ModelAndView("redirect:/cliente/index");

            }else{
                redirectAttributes.addFlashAttribute("messages", "CPF JÁ EXISTENTE.");
                return new ModelAndView("redirect:/cliente/index");}
        } catch(Exception ex) {
            return new ModelAndView("redirect:/cliente/index");
        }
    }

    @GetMapping(value = "update/{id}")
    public ModelAndView update(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("/cliente/update");
        mv.addObject("cliente", clienteService.findOne(id));
        mv.addObject("estados", estadoService.findAllByOrderByNome());
        mv.addObject("cidades", cidadeService.findAllByOrderByNome());

        return mv;
    }

    @PostMapping("/update/{id}")
    @Transactional
    public ModelAndView save(@PathVariable("id") Integer id, @ModelAttribute @Valid Cliente model, BindingResult result,RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("/cliente/update");
            mv.addObject("cliente", model);
            mv.addObject("estados", estadoService.findAllByOrderByNome());
            //mv.addObject("cidades", cidadeService.findAllByOrderByNome());

            return mv;
        }

        Cliente cliente1 = clienteService.findOneByCpf(model.getCpf());
        Cliente cliente2 = clienteService.findOne(model.getId());

        if( cliente1 == null || cliente2.getCpf().equals(model.getCpf())) {

            if(clienteService.findOne(id).getAtivo() == true){
                model.setAtivo(true);
                model.setSituacao("HABILITADO");
            }
            else{
                model.setAtivo(false);
                model.setSituacao("DESABILITADO");
            }

            enderecoService.save(model.getEndereco());
            clienteService.save(model);
            return index();


        }else{
            redirectAttributes.addFlashAttribute("messages", "CPF JÁ EXISTENTE.");
            return new ModelAndView("redirect:/cliente/index");}
       /* if(clienteService.findOne(id).getAtivo() == true){
            model.setAtivo(true);
            model.setSituacao("HABILITADO");
        }
        else{
            model.setAtivo(false);
            model.setSituacao("DESABILITADO");
        }*/
        /*enderecoService.save(model.getEndereco());
        clienteService.save(model);
        return index();*/
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        /*
        Integer endereco_id = clienteService.findOne(id).getEndereco().getId();
        enderecoService.delete(endereco_id);
        clienteService.delete(id);
        JOptionPane.showMessageDialog(null, "Cliente removido!");
        */
        Cliente deletando = clienteService.findOne(id);
        if(deletando != null && deletando.getAtivo() == true){
            deletando.setAtivo(false);
            deletando.setSituacao("DESABILITADO");
            redirectAttributes.addFlashAttribute("messages", "Cliente "+ deletando.getNome() +" desabilitado com sucesso.");
        }
        else if(deletando != null && deletando.getAtivo() == false){
            deletando.setAtivo(true);
            deletando.setSituacao("HABILITADO");
            redirectAttributes.addFlashAttribute("messages", "Cliente "+ deletando.getNome() +" reabilitado com sucesso.");
        }
        else{
            redirectAttributes.addFlashAttribute("messages", "Cliente já está desabilitado.");
            return new ModelAndView("redirect:/cliente/index");}
        clienteService.save(clienteService.findOne(id));
        return new ModelAndView("redirect:/cliente/index");
    }

    @GetMapping("/reabilitar/{id}")
    public ModelAndView reabilitar(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
        if(clienteService.findOne(id) != null && clienteService.findOne(id).getAtivo() == false){
            clienteService.findOne(id).setAtivo(true);
            clienteService.findOne(id).setSituacao("HABILITADO");
            clienteService.save(clienteService.findOne(id));
            redirectAttributes.addFlashAttribute("messages", "Cliente reabilitado com sucesso.");
            return new ModelAndView("redirect:/cliente/index");
        }
        else{
            redirectAttributes.addFlashAttribute("messages", "Cliente já habilitado.");
            return new ModelAndView("redirect:/cliente/index");
        }
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
