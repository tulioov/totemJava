
const CadastroAtividadeController = {
		
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
    		CadastroAtividadeController.tempoEspera(alertComponent);
    		return;
    	}
    	if(data.responseJSON.statusCode === 401){
    		$("#"+alertComponent).removeClass("oculta").addClass("alert-danger").find('div').append(data.responseJSON.response.message+"<br>");
    		CadastroAtividadeController.tempoEspera(alertComponent);
    		return;
    	}
    	retorno = data.responseJSON.response;
    	for (const property in retorno) {
    		if(property == 'stackTrace'){
    			return;
    		}
    		$("#"+property+"Id").addClass("errorInput");
    		$("#alertMsgId").removeClass("oculta").addClass("alert-danger").find('div').append(retorno[property]+"<br>");
    		CadastroAtividadeController.tempoEspera(alertComponent);
		}
	},
		
	carregarDualList(subAtividadeList){
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
	        		if (subAtividadeList != undefined){
        				if(subAtividadeList.some(atividade => atividade.id === data.id)){
	        				$("#duallistboxId").append(`<option value="${data.id}" selected="selected" >${data.descricao}</option>`);
        				}else{
        					$("#duallistboxId").append(`<option value="${data.id}">${data.descricao}</option>`);
        				}
	        		}else{
	        			$("#duallistboxId").append(`<option value="${data.id}">${data.descricao}</option>`);
	        		}
        		});
	        },complete: function(data) { 
	        	$("#duallistboxId").bootstrapDualListbox('refresh');
	        }
	    });
	},
	salvar(){
		
		let formControl = new Object();
		formControl  = $('#formId').serializeJSON();
		formControl.subAtividadeList = $('#duallistboxId').val();
		let myJsonData = JSON.stringify(formControl);
		
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "POST",
	        url: "/atividade/salvar",
	        dataType: "json",
	        cache: false,
	        data : myJsonData,
	        success: function(retorno) {
	        	$("#myModal").scrollTop(0);
	        	$("#alertMsgId").removeClass("oculta").addClass("alert-success").find('div').append("Salvo com sucesso!");
	        	setTimeout(function(){
	        		$("#alertMsgId").addClass("oculta").find('div').removeClass("alert-success").html("");
	        		$('#myModal').modal('hide');
	        		CadastroAtividadeController.listar();
        		},2000); 
	        },
	        error: function (data) {   
	        	CadastroAtividadeController.erro(data,"alertMsgId");
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
	        url: "/atividade/findById/"+id,
	        success: function(retorno) {
	        	CadastroAtividadeController.addUser(retorno.response)
	        }, 
	        error: function (data) {   
	        	CadastroAtividadeController.erro(data,"alertMsgId");
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
	        url: "/atividade/deletar/"+id,
	        success: function(retorno) {
	        	$("#myModal").scrollTop(0);
	        	$("#alertMsgIdTable").removeClass("oculta").addClass("alert-success").find('div').append("Deletado com sucesso!");
	        	setTimeout(function(){
	        		$("#alertMsgIdTable").addClass("oculta").removeClass("alert-success").find('div').html("");
	        		CadastroAtividadeController.listar();
        		},2000); 
	        }, error: function (data) {  
	        	CadastroAtividadeController.erro(data,'alertMsgIdTable');
	        }
	    });
	},
		
	listar(){
		$('#tableAtividade').dataTable().fnClearTable();
	    $('#tableAtividade').dataTable().fnDestroy();
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "GET",
	        contentType: "application/json",
	        url: "/atividade/listar",
	        success: function(retorno) {
	        	$(retorno.response).each(function(index, data) {
	        		$("#tableAtividade").find('tbody').append(CadastroAtividadeTemplate.itemLinha(data));
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
	},
	
	addUser(atividade){
		
		$('#myModal').html(CadastroAtividadeTemplate.add()).show();
		$("#duallistboxId").bootstrapDualListbox({
			nonSelectedListLabel: 'N\u00e3o Selecionadas',
			selectedListLabel: 'Selecionadas'
		});
		
		if(atividade != undefined){
			$('#campoId').val(atividade.id);
			$('#descricaoId').val(atividade.descricao);
			$('#constanteCampoId').val(atividade.constanteCampo);
			CadastroAtividadeController.carregarDualList(atividade.subAtividadeList);
			return;
		}
		CadastroAtividadeController.carregarDualList();
	}
};

$( document ).ready(function() {
	CadastroAtividadeController.listar();
});



