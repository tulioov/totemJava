
const MonitorUserController = {
		
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
    	retorno = data.responseJSON.response;
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
				
				$(data.monitoracao).each(function(index, data) {
					$("#tableMonitorUser").find('tbody').append(MonitorUserTemplate.itemLinha(data));
				});
				
				$('#tableMonitorUser').DataTable( {
	    		    language: {
	    		        url: '//cdn.datatables.net/plug-ins/1.11.3/i18n/pt_br.json'
	    		    }
	    		});
				if(barcosList.length == index+1){
					setTimeout(function () {MonitorUserController.reproduzirListaBarcos(barcosList);}, 5000);
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
		
		var tempoEscolhaBarco = 5000;
		$('#contentIdBarco').html(MonitorUserTemplate.modalEscolhaBarco());
		
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "GET",
	        contentType: "application/json",
	        url: "/barco/escolhaBarco/"+$('#nfcId').val(),
	        success: function(retorno) {
        		for (const barco of retorno.response){	
	        		$('#imgEscolhaBarco').append(
        				`<div class="col-md-4 mt15">
        					<img 
        						style='display:block; width:16em;height:9em;cursor:pointer;' 
        						id='base64image-${barco.id}' 
        						src='${barco.imagem}'
        						onClick=MonitorUserController.abrirEscolhaEtapa(${barco.id})
        						class="btn btn-primary" data-toggle="modal" data-target="#myModal"
    						/>
        				</div>`);
        		}
	        },error: function (data) {   
	        	$('#imgEscolhaBarco').append(
					`<div class="col-md-4 mt15">
						<h4>Voc&ecirc; j&aacute; est&aacute; contando hora no barco</h4>
					</div>`);
	        }
	    });
		
		setTimeout(function() { 
			$('#contentIdBarco').html("");
	    }, tempoEscolhaBarco);
	},
	
	salvarSubAtividadeEscolhida(idSubAtividade){
		
		let formControl = new Object();
		formControl  = $('#formId').serializeJSON();
		formControl.idBarco = $('#barcoId').val();
		formControl.idSubAtividade = idSubAtividade;
		formControl.nfcId = $('#nfcId').val();
		
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
	        },
	        error: function (data) {   
	        	
	        },
	    });
	},
	
	
	tempoProgressbar(){
		
		var valorBar = $('#progressBarEtapaId').attr("value");
		
		if(valorBar <= 0){
			$("#modalCloseId").click();
			return;
		}
		
		$('#progressBarEtapaId').attr("value",valorBar-10);
		$('#progressBarEtapaId').css({"width": (valorBar-10)+"%"});
		
		setTimeout(function() { 
			MonitorUserController.tempoProgressbar();
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
	        url: "/usuario/findByNFC/"+$('#nfcId').val(),
	        success: function(retorno) {
	        	$(retorno.response).each(function(index, usuario) {
	        		$(usuario.etapa).each(function(index, etapa) {
	        			$(etapa.atividadeList).each(function(index, atividade) {
	        				let atividadeHTML = MonitorUserTemplate.contentAtividade(index,atividade);
	        				$('#contentAtividadeId'+etapa.id).html(atividadeHTML);
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
	
	abrirEscolhaEtapa(idBarco){
		
		$('#myModal').html(MonitorUserTemplate.abrirEscolhaEtapa(idBarco));
		
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "GET",
	        contentType: "application/json",
	        url: "/etapa/listar",
	        success: function(retorno) {
	        	$(retorno.response).each(function(index, etapa) {
	        		$('#rowEtapasId').append(MonitorUserTemplate.rowEtapas(index,etapa));
	        		$('#contentEtapasId').append(MonitorUserTemplate.contentEtapas(index,etapa));
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
		
		
		MonitorUserController.tempoProgressbar();
	}
};

$( document ).ready(function() {
	MonitorUserController.abrirMonitoracao();
	
	$("#formNCId").on("submit", function(e){
		e.preventDefault();
	});
	
});
