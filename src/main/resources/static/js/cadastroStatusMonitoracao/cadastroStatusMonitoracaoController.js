
const CadastroStatusMonitoracaoController = {
		
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
    		CadastroStatusMonitoracaoController.tempoEspera(alertComponent);
    		return;
    	}
    	if(data.responseJSON.statusCode === 401){
    		$("#"+alertComponent).removeClass("oculta").addClass("alert-danger").find('div').append(data.responseJSON.response.message+"<br>");
    		CadastroStatusMonitoracaoController.tempoEspera(alertComponent);
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
    		$("#"+alertComponent).removeClass("oculta").addClass("alert-danger").find('div').append(retorno[property]+"<br>");
    		CadastroStatusMonitoracaoController.tempoEspera(alertComponent);
		}
	},
	
	salvar(){
		
		let formControl  = $('#formId').serializeJSON();
		let myJsonData = JSON.stringify(formControl);
		
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "POST",
	        url: "/statusMonitoracao/salvar",
	        dataType: "json",
	        cache: false,
	        data : myJsonData,
	        success: function(retorno) {
	        	$("#myModal").scrollTop(0);
	        	$("#alertMsgId").removeClass("oculta").addClass("alert-success").find('div').append("Salvo com sucesso!");
	        	setTimeout(function(){
	        		$("#alertMsgId").addClass("oculta").find('div').removeClass("alert-success").html("");
	        		$('#myModal').modal('hide');
	        		CadastroStatusMonitoracaoController.listar();
        		},2000); 
	        },
	        error: function (data) {   
	        	CadastroStatusMonitoracaoController.erro(data,"alertMsgId");
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
	        url: "/statusMonitoracao/findById/"+id,
	        success: function(retorno) {
	        	CadastroStatusMonitoracaoController.addUser(retorno.response)
	        }, error: function (data) {   
	        	CadastroStatusMonitoracaoController.erro(data,"alertMsgId");
	        }
	    });
	},
	
	deletar(id){
		alert('fazendo');
//		$.ajax({
//			headers: {
//	            'Authorization': email,
//	            'Content-Type':'application/json'
//	        },
//	        type: "DELETE",
//	        contentType: "application/json",
//	        url: "/statusMonitoracao/deletar/"+id,
//	        success: function(retorno) {
//	        	$("#myModal").scrollTop(0);
//	        	$("#alertMsgIdTable").removeClass("oculta").addClass("alert-success").find('div').append("Deletado com sucesso!");
//	        	setTimeout(function(){
//	        		$("#alertMsgIdTable").addClass("oculta").removeClass("alert-success").find('div').html("");
//	        		CadastroStatusMonitoracaoController.listar();
//        		},2000); 
//	        }, error: function (data) {  
//	        	CadastroStatusMonitoracaoController.erro(data,"alertMsgIdTable");
//	        }
//	    });
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
	        url: "/statusMonitoracao/listar",
	        success: function(retorno) {
	        	$(retorno.response).each(function(index, data) {
	        		$("#tableFeriado").find('tbody').append(CadastroStatusMonitoracaoTemplate.itemLinha(data));
        		});
	        },error: function (data) {   
	        	CadastroStatusMonitoracaoController.erro(data,"alertMsgIdTable");
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
	
	addUser(statusMonitoracao){
		
		$('#myModal').html(CadastroStatusMonitoracaoTemplate.add()).show();
		
		$("#nomeId").keyup(function(){
			const reg = /[^a-zA-Z0-9 ]+/g;
			let texto = $("#nomeId").val().replace(reg,'');
			$("#constanteCampoId").val(texto.replaceAll(' ','_'));
		});
		
		$("#duallistboxId").bootstrapDualListbox({
			nonSelectedListLabel: 'N\u00e3o Selecionadas',
			selectedListLabel: 'Selecionadas'
		});
		
		if(statusMonitoracao != undefined){
			$('#campoId').val(statusMonitoracao.id);
			$('#nomeId').val(statusMonitoracao.nome);
			$('#constanteCampoId').val(statusMonitoracao.constanteCampo.substring(6));
		}
	}
};

$( document ).ready(function() {
	CadastroStatusMonitoracaoController.listar();
});



