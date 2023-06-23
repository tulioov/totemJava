
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
					if(dat.statusMonitoracao.constanteCampo !== 'FINALIZADO' && dat.dtFimAtividade == null){
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
	        url: "barco/findByDtDeleteIsNull",
	        success: function(retorno) {
	        	MonitorUserController.reproduzirListaBarcos(retorno.response);
	        }
	    });
	},
	
	listMenuPausa(){
		
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "GET",
	        async: false,
	        contentType: "application/json",
	        url: "/statusMonitoracao/listar",
	        success: function(retorno) {
	        	
	        	
	        	let html = `
	        		<div class="col-md-12 mt15">
	        		`;
	        	
        		for (const menuPausa of retorno.response){	
        			html += MonitorUserTemplate.menuPausa(menuPausa)
        		}
        		
        		html += `
        				</div>
        				`;		
        		
        		$('#imgEscolhaBarco').append(html);
        		return ;
	        },
	        error: function (data) {  
	        	MonitorUserController.erro(data,'alertMsgIdTable');
	        }
	    });
	},
		
	abrirEscolhaBarco(idUsuario, codRfid){
		
		if($('#nfcId').val()==='' || id==null){
			$('#nfcId').val(codRfid);
		}
		let urlService = "/barco/escolhaBarcoByNFC/"+$('#nfcIdCache').val();
		
		$('#nfcIdCache').val($('#nfcId').val());
		$('#nfcId').val('');

		if(idUsuario != null){
			urlService = "/barco/escolhaBarcoByIdUsuario/" + idUsuario;
			$('#usuarioIdCache').val(idUsuario);
		}		
		
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "GET",
	        async: false,
	        contentType: "application/json",
	        url: urlService,
	        
	        success: function(retorno) {
	        	
	        	//$('#contentId').hide();
	        	$('#myModal').html(MonitorUserTemplate.modalEscolhaBarco());
	        	
	        	if(retorno.response.status != undefined && retorno.response.status == "TRABALHANDO"){
	        		MonitorUserController.listMenuPausa();
	        		return;
	        	}
	        	
        		if(retorno.response.status != undefined && retorno.response.status.includes("PAUSA")){
        			$('#imgEscolhaBarco').append(
	    					`
	    					<div class="col-md-12 mt15">
	    						<button type="button" class="btn-lg btn-success col-md-12 mt15" onclick="MonitorUserController.continuarPausarFinalizar('CONTINUAR');">Continuar Trabalhando na ${retorno.response.barco.nome} na Atividade: ${retorno.response.monitoracao.atividade.nome}</button>
	    					</div>`);
	        		return;
	        	}
	        	
        		for (const barco of retorno.response){	
	        		$('#imgEscolhaBarco').append(
        				`
        				<div class="col-md-4 mt15">
        					<span class="btnBarco" onclick="MonitorUserController.abrirEscolhaFase(${barco.id},${barco.barcoTemplate!=null?barco.barcoTemplate.id:null},${idUsuario});">${barco.nome}</span>
        				</div>`
    				);
        		}
	        },
	        error: function (data) {  
	        	MonitorUserController.erro(data,'alertMsgIdTable');
	        }
	    });
		//MonitorUserController.tempoProgressbar("progressBarEscolhaBarcoId", "escolhaBarco");
	},
	
	continuarPausarFinalizar(acao){
		
		let formControl = new Object();
		formControl  = $('#formId').serializeJSON();
		formControl.acao = acao;
		formControl.nfcId = $('#nfcIdCache').val();
		formControl.idUsuario = $('#usuarioIdCache').val();
		
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
	
	salvarAtividadeEscolhida(idAtividade, idLocal){
		
		let formControl = new Object();
		formControl  = $('#formId').serializeJSON();
		formControl.idBarco = $('#barcoId').val();
		formControl.idAtividade = idAtividade;
		formControl.idLocal = idLocal;
		formControl.nfcId = $('#nfcIdCache').val();
		formControl.idUsuario = $('#usuarioIdCache').val();
		
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
	
	
	/*tempoProgressbar(idProgress, acao){
		
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
		
	},*/
	
	carregarLocal(idUsuario,idBarcoTemplate){
		
		let urlService = "/usuario/findByNFC/"+$('#nfcIdCache').val();
		
		if(idUsuario != null){
			urlService = "/usuario/findById/"+idUsuario;
		}
		
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "GET",
	        contentType: "application/json",
	        url: urlService,
	        success: function(retorno) {
	        	$(retorno.response).each(function(index, usuario) {
	        		$(usuario.faseList).each(function(index, fase) {
	        			$(fase.localList).each(function(index, local) {
	        				let localBtn = MonitorUserTemplate.contentLocal(index,local,idUsuario,idBarcoTemplate);
	        				$('#contentLocalId'+fase.id).html(localBtn);
	        			});
	        		});
        		});
	        },
	        error: function (data) {  
	        	MonitorUserController.erro(data,'alertMsgIdTable');
	        }
	    });
	},
	
	htmlAtividade(object, idLocal,idUsuario,idBarcoTemplate){
		
		console.log(object);
		
		
//		let isBtnFechado = document.getElementById(object).querySelector('.collapse') != null;
		let isPossuiBtn = document.getElementById(object).querySelector('.btnAtividade') != null;
		
		if(!isPossuiBtn){
			$.ajax({
				headers: {
		            'Authorization': email,
		            'Content-Type':'application/json'
		        },
		        type: "GET",
		        contentType: "application/json",
		        url: "/atividade/listarAtividadeByLocalIdEspecIdBarcoTemplateId/"+idLocal+"/"+idUsuario+"/"+idBarcoTemplate,
		        success: function(retorno) {
		        	$(retorno.response).each(function(index, atividade) {
		        		let atividadeBtn = MonitorUserTemplate.htmlAtividade(idLocal, atividade);
		        		$('#'+object).find('.form-group').append(atividadeBtn);
	        		});
		        },
		        error: function (data) {  
		        	MonitorUserController.erro(data,'alertMsgIdTable');
		        }
		    });
		}
	},
	
	abrirEscolhaFase(idBarco,idBarcoTemplate, idUsuario){
		
		$('#myModal').html("");
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
	        	MonitorUserController.carregarLocal(idUsuario,idBarcoTemplate);
	        },
	        error: function (data) {  
	        	MonitorUserController.erro(data,'alertMsgIdTable');
	        },
	        complete: function() { 
	        	$('#tableAtividade').DataTable( {
	        	    language: {
	        	        url: '//cdn.datatables.net/plug-ins/1.11.3/i18n/pt_br.json'
	        	    }
	        	});
	        }
	    });
		//MonitorUserController.tempoProgressbar("progressBarFaseId","escolhaFase")
	},
	
	addFuncionario(){
		
		$('#myModal').html(MonitorUserTemplate.modalFuncionario());
		
		$('#tableUsuario').dataTable().fnClearTable();
	    $('#tableUsuario').dataTable().fnDestroy();
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "GET",
	        contentType: "application/json",
	        url: "/usuario/listar",
	        success: function(retorno) {
	        	$(retorno.response).each(function(index, data) {
	        		$("#tableUsuario").find('tbody').append(MonitorUserTemplate.itemLinhaUsuario(data));
        		});
	        },
	        complete: function(data) { 
	        	$('#tableUsuario').DataTable( {
	        	    language: {
	        	        url: '//cdn.datatables.net/plug-ins/1.11.3/i18n/pt_br.json'
	        	    }
	        	});
	        }
	    });
		
	}
};

$( document ).ready(function() {
	MonitorUserController.abrirMonitoracao();
	
	$("#formNCId").on("submit", function(e){
		e.preventDefault();
	});
	
});
