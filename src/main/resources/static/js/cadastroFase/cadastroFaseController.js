
const CadastroFaseController = {
		
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
    		CadastroFaseController.tempoEspera(alertComponent);
    		return;
    	}
    	if(data.responseJSON.statusCode === 401){
    		$("#"+alertComponent).removeClass("oculta").addClass("alert-danger").find('div').append(data.responseJSON.response.message+"<br>");
    		CadastroFaseController.tempoEspera(alertComponent);
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
    		CadastroFaseController.tempoEspera(alertComponent);
		}
	},
	
	carregarDualList(localList){
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "GET",
	        contentType: "application/json",
	        url: "/local/listar",
	        success: function(retorno) {
	        	$(retorno.response).each(function(index, data) {
	        		if (localList != undefined){
        				if(localList.some(local => local.id === data.id)){
	        				$("#duallistboxId").append(`<option value="${data.id}" selected="selected" >${data.nome}</option>`);
        				}else{
        					$("#duallistboxId").append(`<option value="${data.id}">${data.nome}</option>`);
        				}
	        		}else{
	        			$("#duallistboxId").append(`<option value="${data.id}">${data.nome}</option>`);
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
		formControl.localList = $('#duallistboxId').val();
		let myJsonData = JSON.stringify(formControl);
		
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "POST",
	        url: "/fase/salvar",
	        dataType: "json",
	        cache: false,
	        data : myJsonData,
	        success: function(retorno) {
	        	$("#myModal").scrollTop(0);
	        	$("#alertMsgId").removeClass("oculta").addClass("alert-success").find('div').append("Salvo com sucesso!");
	        	setTimeout(function(){
	        		$("#alertMsgId").addClass("oculta").find('div').removeClass("alert-success").html("");
	        		$('#myModal').modal('hide');
	        		CadastroFaseController.listar();
        		},2000); 
	        },
	        error: function (data) {   
	        	CadastroFaseController.erro(data,"alertMsgId");
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
	        url: "/fase/findById/"+id,
	        success: function(retorno) {
	        	CadastroFaseController.addUser(retorno.response)
	        }, error: function (data) {   
	        	CadastroFaseController.erro(data,"alertMsgId");
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
	        url: "/fase/deletar/"+id,
	        success: function(retorno) {
	        	$("#myModal").scrollTop(0);
	        	$("#alertMsgIdTable").removeClass("oculta").addClass("alert-success").find('div').append("Deletado com sucesso!");
	        	setTimeout(function(){
	        		$("#alertMsgIdTable").addClass("oculta").removeClass("alert-success").find('div').html("");
	        		CadastroFaseController.listar();
        		},2000); 
	        }, error: function (data) {  
	        	CadastroFaseController.erro(data,"alertMsgIdTable");
	        }
	    });
	},
		
	listar(){
		$('#tableFase').dataTable().fnClearTable();
	    $('#tableFase').dataTable().fnDestroy();
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "GET",
	        contentType: "application/json",
	        url: "/fase/listar",
	        success: function(retorno) {
	        	$(retorno.response).each(function(index, data) {
	        		$("#tableFase").find('tbody').append(CadastroFaseTemplate.itemLinha(data));
        		});
	        },error: function (data) {   
	        	CadastroFaseController.erro(data,"alertMsgIdTable");
	        },
	        complete: function(data) { 
	        	$('#tableFase').DataTable( {
	        	    language: {
	        	        url: '//cdn.datatables.net/plug-ins/1.11.3/i18n/pt_br.json'
	        	    }
	        	});
	        }
	    });
	},
	
	addUser(fase){
		
		$('#myModal').html(CadastroFaseTemplate.add()).show();
		
		$("#duallistboxId").bootstrapDualListbox({
			nonSelectedListLabel: 'N\u00e3o Selecionadas',
			selectedListLabel: 'Selecionadas'
		});
		
		if(fase != undefined){
			$('#campoId').val(fase.id);
			$('#nomeId').val(fase.nome);
			$('#nomeId').val(fase.nome);
			$('#constanteCampoId').val(fase.constanteCampo);
			CadastroFaseController.carregarDualList(fase.localList);
			return;
		}
		CadastroFaseController.carregarDualList();
	}
};

$( document ).ready(function() {
	CadastroFaseController.listar();
});



