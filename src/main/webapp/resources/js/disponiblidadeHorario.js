var lista = [];

/*$("#colaborador").change(function () {
    var usuarioID = $("#colaborador option:selected").val();
    $.ajax({
        url: "/disponibilidadeHorario/getUsuario",
        type: 'POST',
        contentType: 'application/json',
        mimeType: 'application/json',
        data: usuarioID,
        success: function (response) {
})*/
$("#btn-carga").click(function () {
    if ((!$.trim($("#hora_ini").val()).length)) {
        buildMessage("Informe o horário de início", "danger", false);
    }
    else if ((!$.trim($("#hora_fim").val()).length)) {
        buildMessage("Informe o horário de término", "danger", false);
    }
    else if (!isHoraInicialMenorHoraFinal($("#hora_ini").val(), $("#hora_fim").val())) {
        buildMessage("A hora de início é maior que a de término", "danger", false);
    }
    else {
        $("#carga_horaria").val(diferencaHoras($("#hora_ini").val(), $("#hora_fim").val()));
    }
});


$("#form_disponibilidade").submit(function (e) {

    $("#carga_horaria").val(diferencaHoras($("#hora_ini").val(), $("#hora_fim").val()));
    e.preventDefault();
    if ($("#colaborador option:selected").val() !== '') {
        adicionarDisponibilidade();
    }
    else {
        buildMessage("Selecione um colaborador. Por favor.", "danger", false)
    }
});

$("#btnSalvar").click(function (event) {
    event.preventDefault();
    if (lista.length == 0) {
        buildMessage("Informe disponibilidades para salvar os dados. Por favor.", "danger", false)
        return false;
    }

    lista = JSON.stringify({
        'disponibilidades': lista
    });

    $.ajax({
        url: "/disponibilidadeHorario/salvarListaDisponibilidades",
        type: 'POST',
        contentType: 'application/json',
        mimeType: 'application/json',
        data: lista,
        success: function (response) {
            if (response.referencia !== "") {
                window.location.href = response.referencia;
            }
            else {

                lista = [];
                $(".disponibilidade").remove();

                if (response.duplicidades.length > 0) {
                    if (response.houveCadastro == true) {
                        buildMessage("Cadastro Realizado com Sucesso, exceto dados concorrentes", "success", true);
                    }
                    buildMessage("As disponibilidades demonstradas são concorrentes com horários já cadastrados. Exclua-as e insira disponibilidades não-concorrentes", "danger", true);
                    for (var i = 0; i < response.duplicidades.length; i++) {
                        adicaoDuplicadas(response.duplicidades[i]);
                    }
                }
            }
        },

        complete: function () {
        }
    });

});

function diferencaHoras(horaInicial, horaFinal) {
    hIni = horaInicial.split(':');
    hFim = horaFinal.split(':');
    horasTotal = parseInt(hFim[0], 10) - parseInt(hIni[0], 10);
    minutosTotal = parseInt(hFim[1], 10) - parseInt(hIni[1], 10);
    if (minutosTotal < 0) {
        minutosTotal += 60;
        horasTotal -= 1;
    }
    horaFinal = completaZeroEsquerda(horasTotal) + ":" + completaZeroEsquerda(minutosTotal);
    return horaFinal;
}

function completaZeroEsquerda(total) {
    total = ("00" + total).slice(-2);
    return total;
}

/** * Verifica se a hora inicial é menor que a final. */
function isHoraInicialMenorHoraFinal(horaInicial, horaFinal) {
    horaIni = horaInicial.split(':');
    horaFim = horaFinal.split(':');

    // Verifica as horas. Se forem diferentes, é só ver se a inicial
    // é menor que a final.
    hIni = parseInt(horaIni[0], 10);
    hFim = parseInt(horaFim[0], 10);
    if (hIni !== hFim)
        return hIni < hFim;

    // Se as horas são iguais, verifica os minutos então.
    mIni = parseInt(horaIni[1], 10);
    mFim = parseInt(horaFim[1], 10);
    if (mIni !== mFim)
        return mIni < mFim;
}

function adicionarDisponibilidade() {

    var corpoTabela = $("#table-temp").find("tbody");

    var usuarioID = $("#colaborador option:selected").val();
    var usuarioNome = $("#colaborador option:selected").text();
    var diaVal = $("#dia_semana option:selected").val();
    var dia = $("#dia_semana option:selected").text();
    var hora_inicio = $("#hora_ini").val();
    var hora_fim = $("#hora_fim").val();
    var carga = $("#carga_horaria").val();

    var obj = buildObject(diaVal, hora_inicio, hora_fim, carga, usuarioID);
    lista.push(obj);
    console.log(lista);
    var linha = novaLinha(dia, diaVal, hora_inicio, hora_fim, usuarioID, usuarioNome, carga);
    corpoTabela.append(linha);
    $('#form-disponibilidade').each(function () {
        this.reset();
    });

}

function novaLinha(dia, diaVal, hora_inicio, hora_fim, usuarioID, usuarioNome, carga) {

    var linha = $("<tr class='disponibilidade'>");
    linha.attr('id', usuarioID);
    var botao = $("<button type='button' class='btn btn-danger' aria-label='Excluir' onClick=\"remover($(this));\"> ");
    botao.append("<span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span>")
    var colunaUsuario = $("<td class='usuario'>").text(usuarioNome);
    var colunaDia = $("<td class='dia'>").text(dia);
    colunaDia.attr('diaval', diaVal);
    var colunaHoraInicio = $("<td class='horainicio'>").text(hora_inicio);
    var colunaHoraFim = $("<td class='horafim'>").text(hora_fim);
    var colunaCarga = $("<td class='carga'>").text(carga);
    var colunaExcluir = $("<td>").append(botao);
    linha.append(colunaUsuario);
    linha.append(colunaDia);
    linha.append(colunaHoraInicio);
    linha.append(colunaHoraFim);
    linha.append(colunaCarga);
    linha.append(colunaExcluir);


    return linha;
}

function adicaoDuplicadas(item) {
    var corpoTabela = $("#table-temp").find("tbody");
    var usuarioID = item.usuario.id;
    var usuarioNome = item.usuario.nome;
    var diaVal = item.dia_semana;
    var dia = DiaSemana(item.dia_semana);
    var hora_inicio = toTime(item.horarioinicio);
    var hora_fim = toTime(item.horariofim);
    var carga = toTime(item.cargahoraria);
    var linha = novaLinha(dia, diaVal, hora_inicio, hora_fim, usuarioID, usuarioNome, carga);
    corpoTabela.append(linha);


    function DiaSemana(dia) {
        switch (dia) {
            case "SEGUNDA":
                return "Segunda-Feira";

            case "TERCA":
                return "Terça-Feira";

            case "QUARTA":
                return "Quarta-Feira";

            case "QUINTA":
                return "Quinta-Feira";

            case "SEXTA":
                return "Sexta-Feira";
        }
    }

    function toTime(horario) {
        return completaZeroEsquerda(horario.hour) + ":" + completaZeroEsquerda(horario.minute);
    }
}

function remover(botao) {
    var usuarioID = botao.closest('tr').attr('id');
    var diaVal = botao.closest('tr td .dia').attr('diaval');
    var dia = botao.closest('tr td .horainicio').text();
    var hora_inicio = botao.closest('tr td .horafim').val();
    var hora_fim = botao.closest('tr td .carga').val();
    var carga = botao.closest('tr td .usuario').val();

    var obj = buildObject(diaVal, hora_inicio, hora_fim, carga, usuarioID);
    lista.splice(lista.indexOf(obj), 1);
    botao.closest('tr').remove();

}

function buildObject(diaVal, hora_inicio, hora_fim, carga, usuarioID) {
    var obj = {
        "dia_semana": diaVal,
        "horarioinicio": hora_inicio,
        "horariofim": hora_fim,
        "cargahoraria": carga,
        "usuario": {
            "id": usuarioID
        }
    };
    return obj;
}

function buildMessage(mensagem, status, append) {
    if (append) {
        $("#aviso").append(
            "<div class=\"alert alert-" + status + " alert-dismissable\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>" + mensagem + "</div>"
        );
    } else {
        $("#aviso").html(
            "<div class=\"alert alert-" + status + " alert-dismissable\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>" + mensagem + "</div>"
        );
    }
}