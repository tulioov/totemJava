
const CadastroLocalController = {
		
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
    		CadastroLocalController.tempoEspera(alertComponent);
    		return;
    	}
    	if(data.responseJSON.statusCode === 401){
    		$("#"+alertComponent).removeClass("oculta").addClass("alert-danger").find('div').append(data.responseJSON.response.message+"<br>");
    		CadastroLocalController.tempoEspera(alertComponent);
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
    		$("#alertMsgId").removeClass("oculta").addClass("alert-danger").find('div').append(retorno[property]+"<br>");
    		CadastroLocalController.tempoEspera(alertComponent);
		}
	},
		
	
	carregarDualList(atividadeList){
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
	        		if (atividadeList != undefined){
        				if(atividadeList.some(local => local.id === data.id)){
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
		formControl.atividadeList = $('#duallistboxId').val();
		let myJsonData = JSON.stringify(formControl);
		
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "POST",
	        url: "/local/salvar",
	        dataType: "json",
	        cache: false,
	        data : myJsonData,
	        success: function(retorno) {
	        	$("#myModal").scrollTop(0);
	        	$("#alertMsgId").removeClass("oculta").addClass("alert-success").find('div').append("Salvo com sucesso!");
	        	setTimeout(function(){
	        		$("#alertMsgId").addClass("oculta").find('div').removeClass("alert-success").html("");
	        		$('#myModal').modal('hide');
	        		CadastroLocalController.listar();
        		},2000); 
	        },
	        error: function (data) {   
	        	CadastroLocalController.erro(data,"alertMsgId");
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
	        url: "/local/findById/"+id,
	        success: function(retorno) {
	        	CadastroLocalController.addUser(retorno.response)
	        }, 
	        error: function (data) {   
	        	CadastroLocalController.erro(data,"alertMsgId");
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
	        url: "/local/deletar/"+id,
	        success: function(retorno) {
	        	$("#myModal").scrollTop(0);
	        	$("#alertMsgIdTable").removeClass("oculta").addClass("alert-success").find('div').append("Deletado com sucesso!");
	        	setTimeout(function(){
	        		$("#alertMsgIdTable").addClass("oculta").removeClass("alert-success").find('div').html("");
	        		CadastroLocalController.listar();
        		},2000); 
	        }, error: function (data) {  
	        	CadastroLocalController.erro(data,'alertMsgIdTable');
	        }
	    });
	},
		
	listar(){
		
		$('#tableLocal').dataTable().fnClearTable();
	    $('#tableLocal').dataTable().fnDestroy();
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
	        		$("#tableLocal").find('tbody').append(CadastroLocalTemplate.itemLinha(data));
        		});
	        },
	        error: function (data) {  
	        	CadastroLocalController.erro(data,'alertMsgIdTable');
	        },
	        complete: function(data) { 
	        	$('#tableLocal').DataTable( {
	        	    language: {
	        	        url: '//cdn.datatables.net/plug-ins/1.11.3/i18n/pt_br.json'
	        	    }
	        	});
	        }
	    });
	},
	
	addUser(local){
		
		$('#myModal').html(CadastroLocalTemplate.add()).show();
		
		$("#nomeId").keyup(function(){
			const reg = /[^a-zA-Z0-9 ]+/g;
			let texto = $("#nomeId").val().replace(reg,'');
			$("#constanteCampoId").val(texto.replaceAll(' ','_'));
		});
		
		$("#duallistboxId").bootstrapDualListbox({
			nonSelectedListLabel: 'N\u00e3o Selecionadas',
			selectedListLabel: 'Selecionadas'
		});
		
		if(local != undefined){
			$('#campoId').val(local.id);
			$('#codigoId').val(local.codigo);
			$('#nomeId').val(local.nome);
			$('#constanteCampoId').val(local.constanteCampo);
			CadastroLocalController.carregarDualList(local.atividadeList);
			return;
		}
		CadastroLocalController.carregarDualList();
	}
};

$( document ).ready(function() {
	CadastroLocalController.listar();
});



