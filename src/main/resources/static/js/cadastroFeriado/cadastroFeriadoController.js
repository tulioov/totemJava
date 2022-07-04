
const CadastroFeriadoController = {
		
	tempoEspera(divId){
		setTimeout(function () {
			$('#'+divId).hide(); 
		}, 1500); 
	},
		
	erro(data, alertComponent){
		$("#myModal").scrollTop(0);
    	$("#"+alertComponent).find('div').html("");
    	if(data.responseJSON.statusCode === 404){
    		$("#"+alertComponent).removeClass("oculta").addClass("alert-danger").find('div').append(data.responseJSON.response+"<br>");
    		CadastroFeriadoController.tempoEspera(alertComponent);
    		return;
    	}
    	if(data.responseJSON.statusCode === 401){
    		$("#"+alertComponent).removeClass("oculta").addClass("alert-danger").find('div').append(data.responseJSON.response.message+"<br>");
    		CadastroFeriadoController.tempoEspera(alertComponent);
    		return;
    	}
    	let retorno = data.responseJSON.response;
    	for (const property in retorno) {
    		if(property == 'stackTrace'){
    			return;
    		}
    		$("#"+property+"Id").addClass("errorInput");
    		$("#"+alertComponent).removeClass("oculta").addClass("alert-danger").find('div').append(retorno[property]+"<br>");
    		CadastroFeriadoController.tempoEspera(alertComponent);
		}
	},
	
	salvar(){
		
		let formControl  = $('#formId').serializeJSON();
		formControl.dtFeriado = $("#dtFeriadoId").val().split('-').reverse().join('/');
		let myJsonData = JSON.stringify(formControl);
		
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "POST",
	        url: "/feriado/salvar",
	        dataType: "json",
	        cache: false,
	        data : myJsonData,
	        success: function(retorno) {
	        	$("#myModal").scrollTop(0);
	        	$("#alertMsgId").removeClass("oculta").addClass("alert-success").find('div').append("Salvo com sucesso!");
	        	setTimeout(function(){
	        		$("#alertMsgId").addClass("oculta").find('div').removeClass("alert-success").html("");
	        		$('#myModal').modal('hide');
	        		CadastroFeriadoController.listar();
        		},2000); 
	        },
	        error: function (data) {   
	        	CadastroFeriadoController.erro(data,"alertMsgId");
	        },
	    });
	},
	
	editar(id){
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "GET",
	        contentType: "application/json",
	        url: "/feriado/findById/"+id,
	        success: function(retorno) {
	        	CadastroFeriadoController.addUser(retorno.response)
	        }, error: function (data) {   
	        	CadastroFeriadoController.erro(data,"alertMsgId");
	        }
	    });
	},
	
	deletar(id){
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "DELETE",
	        contentType: "application/json",
	        url: "/feriado/deletar/"+id,
	        success: function(retorno) {
	        	$("#myModal").scrollTop(0);
	        	$("#alertMsgIdTable").removeClass("oculta").addClass("alert-success").find('div').append("Deletado com sucesso!");
	        	setTimeout(function(){
	        		$("#alertMsgIdTable").addClass("oculta").removeClass("alert-success").find('div').html("");
	        		CadastroFeriadoController.listar();
        		},2000); 
	        }, error: function (data) {  
	        	CadastroFeriadoController.erro(data,"alertMsgIdTable");
	        }
	    });
	},
		
	listar(){
		$('#tableFeriado').dataTable().fnClearTable();
	    $('#tableFeriado').dataTable().fnDestroy();
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "GET",
	        contentType: "application/json",
	        url: "/feriado/listar",
	        success: function(retorno) {
	        	$(retorno.response).each(function(index, data) {
	        		$("#tableFeriado").find('tbody').append(CadastroFeriadoTemplate.itemLinha(data));
        		});
	        },error: function (data) {   
	        	CadastroFeriadoController.erro(data,"alertMsgIdTable");
	        },
	        complete: function(data) { 
	        	$('#tableFeriado').DataTable( {
	        	    language: {
	        	        url: '//cdn.datatables.net/plug-ins/1.11.3/i18n/pt_br.json'
	        	    }
	        	});
	        }
	    });
	},
	
	addUser(Feriado){
		
		$('#myModal').html(CadastroFeriadoTemplate.add()).show();
		
		$("#duallistboxId").bootstrapDualListbox({
			nonSelectedListLabel: 'N\u00e3o Selecionadas',
			selectedListLabel: 'Selecionadas'
		});
		
		if(Feriado != undefined){
			$('#campoId').val(Feriado.id);
			$('#nomeId').val(Feriado.nome);
			$('#dtFeriadoId').val(Feriado.dtFeriado.split('/').reverse().join('-'));
			$('#descricaoId').val(Feriado.descricao);
			$('#constanteCampoId').val(Feriado.constanteCampo);
		}
	}
};

$( document ).ready(function() {
	CadastroFeriadoController.listar();
});



