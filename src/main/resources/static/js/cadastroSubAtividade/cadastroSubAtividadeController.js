
const CadastroSubAtividadeController = {
		
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
    		CadastroSubAtividadeController.tempoEspera(alertComponent);
    		return;
    	}
    	if(data.responseJSON.statusCode === 401){
    		$("#"+alertComponent).removeClass("oculta").addClass("alert-danger").find('div').append(data.responseJSON.response.message+"<br>");
    		CadastroSubAtividadeController.tempoEspera(alertComponent);
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
    		CadastroSubAtividadeController.tempoEspera(alertComponent);
		}
	},
		
	salvar(){
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "POST",
	        url: "/subAtividade/salvar",
	        dataType: "json",
	        cache: false,
	        data : JSON.stringify($('#formId').serializeJSON()),
	        success: function(retorno) {
	        	$("#myModal").scrollTop(0);
	        	$("#alertMsgId").removeClass("oculta").addClass("alert-success").find('div').append("Salvo com sucesso!");
	        	setTimeout(function(){
	        		$("#alertMsgId").addClass("oculta").find('div').removeClass("alert-success").html("");
	        		$('#myModal').modal('hide');
	        		CadastroSubAtividadeController.listar();
        		},2000); 
	        },
	        error: function (data) {   
	        	CadastroSubAtividadeController.erro(data,"alertMsgId");
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
	        url: "/subAtividade/findById/"+id,
	        success: function(retorno) {
	        	CadastroSubAtividadeController.addUser(retorno.response)
	        }, error: function (data) {   
	        	CadastroSubAtividadeController.erro(data,"alertMsgId");
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
	        url: "/subAtividade/deletar/"+id,
	        success: function(retorno) {
	        	$("#myModal").scrollTop(0);
	        	$("#alertMsgIdTable").removeClass("oculta").addClass("alert-success").find('div').append("Deletado com sucesso!");
	        	setTimeout(function(){
	        		$("#alertMsgIdTable").addClass("oculta").removeClass("alert-success").find('div').html("");
	        		CadastroSubAtividadeController.listar();
        		},2000); 
	        }, error: function (data) {  
	        	CadastroSubAtividadeController.erro(data,"alertMsgIdTable");
	        }
	    });
	},
		
	listar(){
		$('#tableSubAtividade').dataTable().fnClearTable();
	    $('#tableSubAtividade').dataTable().fnDestroy();
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "GET",
	        contentType: "application/json",
	        url: "/subAtividade/listar",
	        success: function(retorno) {
	        	$(retorno.response).each(function(index, data) {
	        		$("#tableSubAtividade").find('tbody').append(CadastroSubAtividadeTemplate.itemLinha(data));
        		});
	        }, 
	        error: function (data) {  
	        	CadastroSubAtividadeController.erro(data,"alertMsgIdTable");
	        },
	        complete: function(data) { 
	        	$('#tableSubAtividade').DataTable( {
	        	    language: {
	        	        url: '//cdn.datatables.net/plug-ins/1.11.3/i18n/pt_br.json'
	        	    }
	        	});
	        }
	    });
	},
	
	addUser(subAtibidade){
		
		$('#myModal').html(CadastroSubAtividadeTemplate.add()).show();
		$('[name=duallistbox]').bootstrapDualListbox({
			nonSelectedListLabel: 'N\u00e3o Selecionadas',
			selectedListLabel: 'Selecionadas'
		});
		
		if(subAtibidade != undefined){
			$('#campoId').val(subAtibidade.id);
			$('#nomeId').val(subAtibidade.nome);
			$('#descricaoId').val(subAtibidade.descricao);
		}
	}
};

$( document ).ready(function() {
	CadastroSubAtividadeController.listar();
});



