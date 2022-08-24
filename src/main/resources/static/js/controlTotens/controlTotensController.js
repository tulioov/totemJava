
const ControlTotensController = {
		
	erro(data, alertComponent){
		$("#myModal").scrollTop(0);
    	$("#"+alertComponent).find('div').html("");
    	if(data.responseJSON.statusCode === 404){
    		$("#"+alertComponent).removeClass("oculta").addClass("alert-danger").find('div').append(data.responseJSON.response+"<br>");
    		return;
    	}
    	if(data.responseJSON.statusCode === 401){
    		$("#"+alertComponent).removeClass("oculta").addClass("alert-danger").find('div').append(data.responseJSON.response.message+"<br>");
    		return;
    	}
    	if(data.responseJSON.statusCode === 500){
    		$("#"+alertComponent).removeClass("oculta").addClass("alert-danger").find('div').append("Erro Interno"+"<br>");
    		return;
    	}
    	retorno = data.responseJSON.response;
    	for (const property in retorno) {
    		if(property == 'stackTrace'){
    			return;
    		}
    		$("#"+property+"Id").addClass("errorInput");
    		$("#"+alertComponent).removeClass("oculta").addClass("alert-danger").find('div').append(retorno[property]+"<br>");
		}
	},
		
	init(){
		
		$('#painelId').html("");
		
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "GET",
	        contentType: "application/json",
	        url: "barco/listar",
	        success: function(retorno) {
	        	barcos = retorno.response;
	        	$(retorno.response).each(function(index, barco) {
	        		$('#painelId').append(ControlTotensTemplate.carregarPainel(barco));
	        	});
	        }
	    });
	},
		
	abrirModal(nome){
		
		let barco = barcos.find(b => b.nome === nome);
		
		$('#myModal').html(ControlTotensTemplate.modalToten(barco));
		$('#tableMonitorUser').DataTable().destroy();
		
		$(barco.monitoracao).each(function(inde, dat) {
			$("#tableMonitorUser").find('tbody').append(ControlTotensTemplate.itemLinha(dat));
		});
		
		$('#tableMonitorUser').DataTable({
		    language: {
		        url: '//cdn.datatables.net/plug-ins/1.11.3/i18n/pt_br.json'
		    }
		});
	},
	
	abrirModalAddHoraAvulsa(idBarco,nomeBarco){
		
		$('#modalAddHoraAvulsa').html(ControlTotensTemplate.modalAddHoraAvulsa(idBarco,nomeBarco));
		SelectUtil.carregarSelect("usuario/listar","selectUsuarioId");
		
		let arraySelect1 = ["selectFaseId","selectLocalId", "selectAtividadeId"];
		SelectUtil.resetListSelectChange("selectUsuarioId", arraySelect1, true)
		let arraySelect2 = ["selectLocalId", "selectAtividadeId"];
		SelectUtil.resetListSelectChange("selectFaseId", arraySelect2, true)
		let arraySelect3 = ["selectAtividadeId"];
		SelectUtil.resetListSelectChange("selectLocalId", arraySelect3, true)
		
		SelectUtil.carregarSelectChange("fase/listarFaseByUsuarioId/","selectFaseId", "selectUsuarioId");
		SelectUtil.carregarSelectChange("local/listarLocalByFaseId/","selectLocalId", "selectFaseId");
		SelectUtil.carregarSelectChange("atividade/listarAtividadeByLocalId/","selectAtividadeId", "selectLocalId");
		ControlTotensController.listar(idBarco);
	},
	
	salvarMonitoracaoAvulsa(){
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "POST",
	        url: "/monitoracao/salvarMonitoracaoAvulsa",
	        dataType: "json",
	        cache: false,
	        data : JSON.stringify($('#formId').serializeJSON()),
	        success: function(retorno) {
	        	$("#modalAddHoraAvulsa").scrollTop(0);
	        	$("#alertMsgId").removeClass("oculta").addClass("alert-success").find('div').append("Salvo com sucesso!");
	        	ControlTotensController.init();
	        	setTimeout(function(){
	        		$("#alertMsgId").addClass("oculta").find('div').removeClass("alert-success").html("");
	        		$('#modalAddHoraAvulsa').modal('hide');
        		},2000); 
	        },
	        error: function (data) {   
	        	console.log(data);
	        },
	    });
	},
	
	editarHrAvulsa(id){
		
		let hrAvulsa = {};
		let linhas = $("#tableHoraAvulsa").find('tbody').find('tr').find('td');
		$('#monitoracaoId').val(id);
		
		for(let i = 0;i<=linhas.length;i++){
			if(linhas[i].textContent == id){
				hrAvulsa.id = linhas[i++].textContent;
				hrAvulsa.nome = linhas[i++].textContent;
				hrAvulsa.fase = linhas[i++].textContent;
				hrAvulsa.local = linhas[i++].textContent;
				hrAvulsa.especialidade = linhas[i++].textContent;
				hrAvulsa.hrInicio = linhas[i++].textContent;
				hrAvulsa.hrFim = linhas[i++].textContent;
				break;
			}
		}
		$('#selectUsuarioId').val( $('option:contains("'+hrAvulsa.nome+'")').val()).change().selectpicker('refresh');
		$('#selectFaseId').val( $('option:contains("'+hrAvulsa.fase+'")').val()).change().selectpicker('refresh');
		$('#selectLocalId').val( $('option:contains("'+hrAvulsa.local+'")').val()).change().selectpicker('refresh');
		$('#selectAtividadeId').val( $('option:contains("'+hrAvulsa.especialidade+'")').val()).change().selectpicker('refresh');
		
		
//		"01/08/2022 00:00"
//		'2022-08-31T13:53'
		let arrayDate = hrAvulsa.hrInicio.split(' ');
		let dtFormatado = arrayDate[0].split('/').reverse().join('-')+"T"+arrayDate[1];
		$('#dtInicioAtividadeId').val(dtFormatado);
		arrayDate = hrAvulsa.hrFim.split(' ');
		dtFormatado = arrayDate[0].split('/').reverse().join('-')+"T"+arrayDate[1];
		$('#dtFimAtividadeId').val(dtFormatado);
		
		console.log(hrAvulsa);
	},
	
	deletarHrAvulsa(id){
		alert('fazendo');
	},
	
	listar(idBarco){
		$('#tableHoraAvulsa').dataTable().fnClearTable();
	    $('#tableHoraAvulsa').dataTable().fnDestroy();
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "GET",
	        contentType: "application/json",
	        url: "/monitoracao/listarHoraAvulsaByBarcoId/"+idBarco,
	        success: function(retorno) {
	        	$(retorno.response).each(function(index, data) {
	        		$("#tableHoraAvulsa").find('tbody').append(ControlTotensTemplate.itemLinhaAvulsa(data));
        		});
	        },
	        error: function (data) {  
	        	ControlTotensController.erro(data,"alertMsgIdTable");
	        },
	        complete: function(data) { 
	        	$('#tableHoraAvulsa').DataTable( {
	        	    language: {
	        	        url: '//cdn.datatables.net/plug-ins/1.11.3/i18n/pt_br.json'
	        	    }
	        	});
	        }
	    });
	},
};

$(document).ready(function() {
	ControlTotensController.init();
});