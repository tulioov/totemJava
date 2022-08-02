
const MonitorUserController = {
		
	erro(data, alertComponent){
		
		setTimeout(function () {
			$("#"+alertComponent).removeClass("alert-danger").addClass("oculta").find('div').html("");
		}, 5000);
		
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
    	if(data.responseJSON.statusCode === 400){
    		$("#"+alertComponent).removeClass("oculta").addClass("alert-danger").find('div').append(data.responseJSON.response.message+"<br>");
    		return;
    	}
    	if(data.responseJSON.statusCode === 500){
    		$("#"+alertComponent).removeClass("oculta").addClass("alert-danger").find('div').append("Erro Interno"+"<br>");
    		return;
    	}
    	let retorno = data.responseJSON.response;
    	for (const property in retorno) {
    		if(property == 'stackTrace'){
    			return;
    		}
    		$("#"+property+"Id").addClass("errorInput");
    		$("#alertMsgId").removeClass("oculta").addClass("alert-danger").find('div').append(retorno[property]+"<br>");
		}
	},
		
	reproduzirListaBarcos(barcosList){
		
		$(barcosList).each(function(index, data) {
			
			setTimeout(function () {
				$('#contentId').html(MonitorUserTemplate.monitoracao(data));
				$('#tableMonitorUser').DataTable().destroy();
				
				$(data.monitoracao).each(function(inde, dat) {
					if(dat.status !== 'FINALIZADO' && dat.dtFimAtividadeTotal == null){
						$("#tableMonitorUser").find('tbody').append(MonitorUserTemplate.itemLinha(dat));
					}
				});
				
				$('#tableMonitorUser').DataTable( {
					"searching": false,
	    		    language: {
	    		        url: '//cdn.datatables.net/plug-ins/1.11.3/i18n/pt_br.json'
	    		    }
	    		});
				if(barcosList.length == index+1){
					setTimeout(function () {
						MonitorUserController.abrirMonitoracao();
					}, 5000);
				}	
            }, 5000 * index);
		});
	},
		
	abrirMonitoracao(){
		
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "GET",
	        contentType: "application/json",
	        url: "barco/listar",
	        success: function(retorno) {
	        	MonitorUserController.reproduzirListaBarcos(retorno.response);
	        }
	    });
	},
		
	abrirEscolhaBarco(){
		
		if($('#nfcId').val()===''){
			return;
		}
		$('#nfcIdCache').val($('#nfcId').val());
		$('#nfcId').val('');
		
		
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "GET",
	        contentType: "application/json",
	        url: "/barco/escolhaBarco/"+$('#nfcIdCache').val(),
	        success: function(retorno) {
	        	
	        	$('#contentId').hide();
	        	$('#contentIdBarco').html(MonitorUserTemplate.modalEscolhaBarco());
	        	
	        	if(retorno.response.status == "TRABALHANDO"){
	        		$('#imgEscolhaBarco').append(
	    					`<div class="col-md-12 mt15">
	    						<button type="button" class="btn-lg btn-warning col-md-12 mt15" onclick="MonitorUserController.continuarPausarFinalizar('PAUSA');">Pausar Atividade</button>
	    						<button type="button" class="btn-lg btn-danger col-md-12 mt15" onclick="MonitorUserController.continuarPausarFinalizar('FINALIZADO');">Finalizar Atividade</button>
	    					</div>`);
	        		return;
	        	}
	        	
        		if(retorno.response.status == "PAUSA"){
        			$('#imgEscolhaBarco').append(
	    					`
	    					<div class="col-md-12 mt15">
	    						<button type="button" class="btn-lg btn-success col-md-12 mt15" onclick="MonitorUserController.continuarPausarFinalizar();">Continuar Trabalhando na ${retorno.response.barco.nome} na Atividade: ${retorno.response.monitoracao.atividade.nome}</button>
	    						<button type="button" class="btn-lg btn-danger col-md-12 mt15" onclick="MonitorUserController.continuarPausarFinalizar('FINALIZADO');">Finalizar Atividade</button>
	    					</div>`);
	        		return;
	        	}
	        	
        		for (const barco of retorno.response){	
	        		$('#imgEscolhaBarco').append(
        				`
        				<div class="col-md-4 mt15">
        					<span class="btnBarco" data-toggle="modal" data-target="#myModal" onclick="javascript:MonitorUserController.abrirEscolhaFase(${barco.id});">${barco.nome}</span>
        				</div>`
    				);
        		}
	        },
	        error: function (data) {  
	        	MonitorUserController.erro(data,'alertMsgIdTable');
	        }
	    });
		MonitorUserController.tempoProgressbar("progressBarEscolhaBarcoId", "escolhaBarco");
	},
	
	continuarPausarFinalizar(acao){
		
		let formControl = new Object();
		formControl  = $('#formId').serializeJSON();
		formControl.acao = acao;
		formControl.nfcId = $('#nfcIdCache').val();
		
		let myJsonData = JSON.stringify(formControl);
		
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "POST",
	        url: "/monitoracao/continuarPausarFinalizar",
	        dataType: "json",
	        cache: false,
	        data : myJsonData,
	        success: function(retorno) {
	        	window.location.reload(true)
	        }
	    });
		
	},
	
	salvarAtividadeEscolhida(idAtividade){
		
		let formControl = new Object();
		formControl  = $('#formId').serializeJSON();
		formControl.idBarco = $('#barcoId').val();
		formControl.idAtividade = idAtividade;
		formControl.nfcId = $('#nfcIdCache').val();
		
		let myJsonData = JSON.stringify(formControl);
		
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "POST",
	        url: "/monitoracao/salvarBarcoMonitoracao",
	        dataType: "json",
	        cache: false,
	        data : myJsonData,
	        success: function(retorno) {
	        	window.location.reload(true)
	        	$("#myModal").scrollTop(0);
	        	$("#alertMsgId").removeClass("oculta").addClass("alert-success").find('div').append("Salvo com sucesso!");
	        	setTimeout(function(){
	        		$("#alertMsgId").addClass("oculta").find('div').removeClass("alert-success").html("");
	        		$('#myModal').modal('hide');
	        		CadastroUsuarioController.listar();
        		},2000); 
	        }
	    });
	},
	
	
	tempoProgressbar(idProgress, acao){
		
		var valorBar = $('#'+idProgress).attr("value");
		
		if(valorBar <= 0){
			if(acao == "escolhaFase"){
				$("#modalCloseId").click();
			}
			if(acao == "escolhaBarco"){
				$('#contentIdBarco').html("");
				$('#contentId').show();
			}
			return;
		}
		
		$('#'+idProgress).attr("value",valorBar-10);
		$('#'+idProgress).css({"width": (valorBar-10)+"%"});
		
		setTimeout(function() { 
			MonitorUserController.tempoProgressbar(idProgress, acao);
	    }, 1000);
		
	},
	
	carregarAtividadesUsuario(){
		
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "GET",
	        contentType: "application/json",
	        url: "/usuario/findByNFC/"+$('#nfcIdCache').val(),
	        success: function(retorno) {
	        	$(retorno.response).each(function(index, usuario) {
	        		$(usuario.fase).each(function(index, fase) {
	        			$(fase.localList).each(function(index, local) {
	        				let atividadeHTML = MonitorUserTemplate.contentLocal(index,local);
	        				$('#contentLocalId'+fase.id).html(atividadeHTML);
	        			});
	        		});
        		});
	        },
	        error: function (data) {  
	        	MonitorUserController.erro(data,'alertMsgIdTable');
	        },
	        complete: function(data) { 
	        	$('#tableAtividade').DataTable( {
	        	    language: {
	        	        url: '//cdn.datatables.net/plug-ins/1.11.3/i18n/pt_br.json'
	        	    }
	        	});
	        }
	    });
	},
	
	abrirEscolhaFase(idBarco){
		
		$('#myModal').html(MonitorUserTemplate.abrirEscolhaFase(idBarco));
		
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "GET",
	        contentType: "application/json",
	        url: "/fase/listar",
	        success: function(retorno) {
	        	$(retorno.response).each(function(index, fase) {
	        		$('#rowFasesId').append(MonitorUserTemplate.rowFases(index,fase));
	        		$('#contentFasesId').append(MonitorUserTemplate.contentFases(index,fase));
        		});
	        	MonitorUserController.carregarAtividadesUsuario();
	        },
	        error: function (data) {  
	        	MonitorUserController.erro(data,'alertMsgIdTable');
	        },
	        complete: function(data) { 
	        	$('#tableAtividade').DataTable( {
	        	    language: {
	        	        url: '//cdn.datatables.net/plug-ins/1.11.3/i18n/pt_br.json'
	        	    }
	        	});
	        }
	    });
		MonitorUserController.tempoProgressbar("progressBarFaseId","escolhaFase")
	}
};

$( document ).ready(function() {
	MonitorUserController.abrirMonitoracao();
	
	$("#formNCId").on("submit", function(e){
		e.preventDefault();
	});
	
});
