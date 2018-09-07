package ufjf.br.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufjf.br.Utils.InvalidDateException;
import ufjf.br.Utils.PreAtendimentoPost;
import ufjf.br.models.PreAtendimento;
import ufjf.br.repository.PreAtendimentoRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PreAtendimentoService {

    @Autowired
    private PreAtendimentoRepository preAtendimentoRepository;

    public List<PreAtendimento> horarioExtraordinario = new ArrayList<>();
    public List<PreAtendimento> datasAgendados = new ArrayList<>();

    public List<PreAtendimento> findAll() {
        return preAtendimentoRepository.findAll();
    }
    public List<PreAtendimento> findAllByOrderById(){ return preAtendimentoRepository.findAllByOrderById();}

    public List<PreAtendimento>  findAllScheduled(){return preAtendimentoRepository. findAllScheduled();}

    public PreAtendimento findOne(Integer id){return preAtendimentoRepository.findOne(id);}

    public void delete(Integer id){
        preAtendimentoRepository.delete(id);
    }

    public String [] diasSemana(){
        return new String[] {
                " Segunda-feira", " Terça-feira", " Quarta-feira",
                " Quinta-feira", " Sexta-feira"
        };
    }


    public void agendamentos()
    {
        List<PreAtendimento> preAtendimentos = preAtendimentoRepository. findAllScheduled();

        datasAgendados.clear();
        horarioExtraordinario.clear();

        if (preAtendimentos.size() == 0)
        {
            preAtendimentos = preAtendimentoRepository.findAllByOrderById();
        }
        for (PreAtendimento aux: preAtendimentos
             ) {


            if (aux.getCliente() == null){
               this.horarioExtraordinario.add(aux);
            }
            else
            {
                this.datasAgendados.add(aux);
            }

        }


    }






    public PreAtendimento save(PreAtendimento model) {
        return preAtendimentoRepository.saveAndFlush(model);
    }

    public String getData(PreAtendimento preAtendimento){
        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String data = String.valueOf(preAtendimento.getData_horario());
        data = formatar.format(data);

        System.out.println(data);
        return data;

    }


    public List<PreAtendimento> geraDatas(PreAtendimentoPost atendimentoPost) {


            Calendar calendarDataIni = Calendar.getInstance();
            Calendar calendaDataFim = Calendar.getInstance();


            List<PreAtendimento> datas = new ArrayList<>();

            String[] arrayDataIni = atendimentoPost.getDataInicio().split("-");
            String[] arrayDataFim = atendimentoPost.getDataFim().split("-");

            String[] arrayHoraIni = atendimentoPost.getHorarioInicio().split(":");
            String[] arrayHoraFim = atendimentoPost.getHorarioFim().split(":");

            String[] arrayTempAtend = atendimentoPost.getIntervalo().split(":");

            int intervalo = Integer.parseInt(arrayTempAtend[0]) * 60 + Integer.parseInt(arrayTempAtend[1]); //Transforma para minutos

            calendarDataIni.set(Calendar.DAY_OF_MONTH, Integer.parseInt(arrayDataIni[2]));
            calendarDataIni.set(Calendar.MONTH, Integer.parseInt(arrayDataIni[1]) - 1);
            calendarDataIni.set(Calendar.YEAR, Integer.parseInt(arrayDataIni[0]));
            calendarDataIni.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arrayHoraIni[0]));
            calendarDataIni.set(Calendar.MINUTE, Integer.parseInt(arrayHoraIni[1]));
            calendarDataIni.set(Calendar.SECOND, 00);

            calendaDataFim.set(Calendar.DAY_OF_MONTH, Integer.parseInt(arrayDataFim[2]));
            calendaDataFim.set(Calendar.MONTH, Integer.parseInt(arrayDataFim[1]) - 1);
            calendaDataFim.set(Calendar.YEAR, Integer.parseInt(arrayDataFim[0]));
            calendaDataFim.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arrayHoraFim[0]));
            calendaDataFim.set(Calendar.MINUTE, Integer.parseInt(arrayHoraFim[1]) + intervalo);
            calendaDataFim.set(Calendar.SECOND, 00);


        String [] diasSemana = atendimentoPost.getCheckBoxValues();


        List<Integer> listaDiasSemana = new ArrayList<>();
        int pos;
        for(int j =0; j< diasSemana.length; j++)
        {  try {
                pos = Integer.parseInt(diasSemana[j]);
            }  catch (NumberFormatException nFE){
                continue;

        }
            if(pos <= 7)
                listaDiasSemana.add(pos) ;
        }



            Date dataAux;
            int numAtenDia = 0;
            int aux = calendarDataIni.get(Calendar.DAY_OF_WEEK);

            while (calendarDataIni.getTime().before(calendaDataFim.getTime()))
            {
                if (calendarDataIni.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && calendarDataIni.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
                {
                   if(aux != calendarDataIni.get(Calendar.DAY_OF_WEEK)){
                        numAtenDia = 0;
                       aux = calendarDataIni.get(Calendar.DAY_OF_WEEK);
                   }


                    if (calendarDataIni.get(Calendar.HOUR_OF_DAY) >= Integer.parseInt(arrayHoraIni[0]) && calendarDataIni.get(Calendar.HOUR_OF_DAY) <= Integer.parseInt(arrayHoraFim[0]) && numAtenDia < atendimentoPost.getNumAtendDia() )
                    {
                        int i = 0;

                        if(listaDiasSemana.contains(calendarDataIni.get(Calendar.DAY_OF_WEEK)))
                        {
                            while (i < atendimentoPost.getNumAtend()) {

                                PreAtendimento preAtendimentoAux = new PreAtendimento();
                                dataAux = calendarDataIni.getTime();
                                preAtendimentoAux.setData_horario(dataAux);
                                preAtendimentoAux.setDuracaoAtendimento(intervalo);
                                preAtendimentoAux.setSemestre(atendimentoPost.getSemestre());


                                //                            System.out.println("Datas geradas: --->" + preAtendimentoAux.getData_horario());

                                datas.add(preAtendimentoAux);
                                i++;
                                numAtenDia++;
                            }
                        }

                        calendarDataIni.set(Calendar.MINUTE, calendarDataIni.get(Calendar.MINUTE)+intervalo);


                    } else {

                            if (calendarDataIni.get(Calendar.HOUR_OF_DAY) != Integer.parseInt(arrayHoraIni[0]) && calendarDataIni.getTime().before(calendaDataFim.getTime()))
                            {
                                calendarDataIni.set(Calendar.DAY_OF_MONTH, calendarDataIni.get(Calendar.DAY_OF_MONTH)+1);
                                calendarDataIni.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arrayHoraIni[0]));
                                calendarDataIni.set(Calendar.MINUTE, Integer.parseInt(arrayHoraIni[1]));


                            }

                    }

                }else
                {
                    calendarDataIni.set(Calendar.DAY_OF_MONTH, calendarDataIni.get(Calendar.DAY_OF_MONTH) + 1);
                }


            }


            return datas;



    }

    public List<PreAtendimento> gerarHorarioExtraordinario(PreAtendimentoPost atendimentoPost)  {

        Calendar calendarDataIni = Calendar.getInstance();

        List<PreAtendimento> horarioExtraordinario = new ArrayList<>();

        String[] arrayDataExtraordinaria = atendimentoPost.getDataInicio().split("-");
        String[] arrayHora = atendimentoPost.getHorarioInicio().split(":");

        String[] arrayTempAtend = atendimentoPost.getIntervalo().split(":");

        int intervalo = Integer.parseInt(arrayTempAtend[0]) * 60 + Integer.parseInt(arrayTempAtend[1]);

        calendarDataIni.set(Calendar.DAY_OF_MONTH, Integer.parseInt(arrayDataExtraordinaria[2]));
        calendarDataIni.set(Calendar.MONTH, Integer.parseInt(arrayDataExtraordinaria[1]) - 1);
        calendarDataIni.set(Calendar.YEAR, Integer.parseInt(arrayDataExtraordinaria[0]));
        calendarDataIni.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arrayHora[0]));
        calendarDataIni.set(Calendar.MINUTE, Integer.parseInt(arrayHora[1]));
        calendarDataIni.set(Calendar.SECOND, 00);

        Date dataAux;
        if (calendarDataIni.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && calendarDataIni.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
        {

                int i = 0;

                while (i < atendimentoPost.getNumAtend()) {

                    PreAtendimento preAtendimentoAux = new PreAtendimento();
                    dataAux = calendarDataIni.getTime();
                    preAtendimentoAux.setData_horario(dataAux);
                    preAtendimentoAux.setDuracaoAtendimento(intervalo);
                    preAtendimentoAux.setSemestre(atendimentoPost.getSemestre());


//                            System.out.println("Datas geradas: --->" + preAtendimentoAux.getData_horario());

                    horarioExtraordinario.add(preAtendimentoAux);
                    i++;
                }

        }/*else{
            throw new InvalidDateException("Não é possivel gerar datas durante o fim de semana");
        }*/

        return horarioExtraordinario;
    }

}