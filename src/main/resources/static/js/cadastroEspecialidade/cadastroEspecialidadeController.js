
const CadastroEspecialidadeController = {
		
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
    		CadastroEspecialidadeController.tempoEspera(alertComponent);
    		return;
    	}
    	if(data.responseJSON.statusCode === 401){
    		$("#"+alertComponent).removeClass("oculta").addClass("alert-danger").find('div').append(data.responseJSON.response.message+"<br>");
    		CadastroEspecialidadeController.tempoEspera(alertComponent);
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
    		CadastroEspecialidadeController.tempoEspera(alertComponent);
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
        				if(atividadeList.some(especialidade => especialidade.id === data.id)){
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
	        url: "/especialidade/salvar",
	        dataType: "json",
	        cache: false,
	        data : myJsonData,
	        success: function(retorno) {
	        	$("#myModal").scrollTop(0);
	        	$("#alertMsgId").removeClass("oculta").addClass("alert-success").find('div').append("Salvo com sucesso!");
	        	setTimeout(function(){
	        		$("#alertMsgId").addClass("oculta").find('div').removeClass("alert-success").html("");
	        		$('#myModal').modal('hide');
	        		CadastroEspecialidadeController.listar();
        		},2000); 
	        },
	        error: function (data) {   
	        	CadastroEspecialidadeController.erro(data,"alertMsgId");
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
	        url: "/especialidade/findById/"+id,
	        success: function(retorno) {
	        	CadastroEspecialidadeController.addUser(retorno.response)
	        }, 
	        error: function (data) {   
	        	CadastroEspecialidadeController.erro(data,"alertMsgId");
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
	        url: "/especialidade/deletar/"+id,
	        success: function(retorno) {
	        	$("#myModal").scrollTop(0);
	        	$("#alertMsgIdTable").removeClass("oculta").addClass("alert-success").find('div').append("Deletado com sucesso!");
	        	setTimeout(function(){
	        		$("#alertMsgIdTable").addClass("oculta").removeClass("alert-success").find('div').html("");
	        		CadastroEspecialidadeController.listar();
        		},2000); 
	        }, error: function (data) {  
	        	CadastroEspecialidadeController.erro(data,'alertMsgIdTable');
	        }
	    });
	},
		
	listar(){
		
		$('#tableEspecialidade').dataTable().fnClearTable();
	    $('#tableEspecialidade').dataTable().fnDestroy();
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "GET",
	        contentType: "application/json",
	        url: "/especialidade/listar",
	        success: function(retorno) {
	        	$(retorno.response).each(function(index, data) {
	        		$("#tableEspecialidade").find('tbody').append(CadastroEspecialidadeTemplate.itemLinha(data));
        		});
	        },
	        error: function (data) {  
	        	CadastroEspecialidadeController.erro(data,'alertMsgIdTable');
	        },
	        complete: function(data) { 
	        	$('#tableEspecialidade').DataTable( {
	        	    language: {
	        	        url: '//cdn.datatables.net/plug-ins/1.11.3/i18n/pt_br.json'
	        	    }
	        	});
	        }
	    });
	},
	
	addUser(especialidade){
		
		$('#myModal').html(CadastroEspecialidadeTemplate.add()).show();
		
		$("#nomeId").keyup(function(){
			const reg = /[^a-zA-Z0-9 ]+/g;
			let texto = $("#nomeId").val().replace(reg,'');
			$("#constanteCampoId").val(texto.replaceAll(' ','_'));
		});
		
		$("#duallistboxId").bootstrapDualListbox({
			nonSelectedListLabel: 'N\u00e3o Selecionadas',
			selectedListLabel: 'Selecionadas'
		});
		
		if(especialidade != undefined){
			$('#campoId').val(especialidade.id);
			$('#nomeId').val(especialidade.nome);
			$('#constanteCampoId').val(especialidade.constanteCampo);
			CadastroEspecialidadeController.carregarDualList(especialidade.atividadeList);
			return;
		}
		CadastroEspecialidadeController.carregarDualList();
	}
};

$( document ).ready(function() {
	CadastroEspecialidadeController.listar();
});



