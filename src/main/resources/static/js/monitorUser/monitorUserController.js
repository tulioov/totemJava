
const MonitorUserController = {
		
	reproduzirListaBarcos(barcosList){
		
		$(barcosList).each(function(index, data) {
			
			setTimeout(function () {
				$('#contentId').html(MonitorUserTemplate.monitoracao(data));
				$('#tableMonitorUser').DataTable().destroy();
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
	        url: "monitoracao/listar",
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
	        url: "/barco/listar",
	        success: function(retorno) {
	        	$(retorno.response).each(function(index, data) {
	        		$('#imgEscolhaBarco').append(
        				`<div class="col-md-4 mt15">
        					<img 
        						style='display:block; width:16em;height:9em;cursor:pointer;' 
        						id='base64image-${data.id}' 
        						src='${data.imagem}'
        						onClick=MonitorUserController.abrirEscolhaEtapa()
        						class="btn btn-primary" data-toggle="modal" data-target="#myModal"
    						/>
        				</div>`);
        		});
	        }
	    });
		
		setTimeout(function() { 
			$('#contentIdBarco').html("");
	    }, tempoEscolhaBarco);
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
	
	abrirEscolhaEtapa(){
		$('#myModal').html(MonitorUserTemplate.abrirEscolhaEtapa());
		
		
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
	        		$('#contentAtividadeId'+etapa.id).html(MonitorUserTemplate.contentAtividade(index,etapa));
        		});
	        },
	        error: function (data) {  
	        	CadastroAtividadeController.erro(data,'alertMsgIdTable');
	        },
	        complete: function(data) { 
	        	$('#tableAtividade').DataTable( {
	        	    language: {
	        	        url: '//cdn.datatables.net/plug-ins/1.11.3/i18n/pt_br.json'
	        	    }
	        	});
	        }
	    });
		
		
//		MonitorUserController.tempoProgressbar();
	}
};

$( document ).ready(function() {
	MonitorUserController.abrirMonitoracao();
});
