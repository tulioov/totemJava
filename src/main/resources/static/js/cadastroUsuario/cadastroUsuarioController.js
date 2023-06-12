
const CadastroUsuarioController = {
		
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
		}
	},
		
	carregarDualList(faseList){
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
	        		if (faseList != undefined){
        				if(faseList.some(fase => fase.id === data.id)){
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
	        	$('.selectpicker').selectpicker('refresh');
	        }
	    });
	},
		
	salvar(){
		
		let formControl = new Object();
		formControl  = $('#formId').serializeJSON();
		formControl.faseList = $('#duallistboxId').val();
		formControl.especialidadeList = $('#especialidadeSelect').val();
		formControl.isAdmin = $('#isAdminId').prop('checked');
		formControl.isLider = $('#isLiderId').prop('checked');
		formControl.email = $('#emailId').val();
		
		let myJsonData = JSON.stringify(formControl);
		
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "POST",
	        url: "/usuario/salvar",
	        dataType: "json",
	        cache: false,
	        data : myJsonData,
	        success: function(retorno) {
	        	$("#myModal").scrollTop(0);
	        	$("#alertMsgId").removeClass("oculta").addClass("alert-success").find('div').append("Salvo com sucesso!");
	        	setTimeout(function(){
	        		$("#alertMsgId").addClass("oculta").find('div').removeClass("alert-success").html("");
	        		$('#myModal').modal('hide');
	        		CadastroUsuarioController.listar();
        		},2000); 
	        },
	        error: function (data) {   
	        	CadastroUsuarioController.erro(data,"alertMsgId");
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
	        url: "/usuario/findById/"+id,
	        success: function(retorno) {
	        	CadastroUsuarioController.addUser(retorno.response)
	        }, 
	        error: function (data) {   
	        	CadastroUsuarioController.erro(data,"alertMsgId");
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
	        url: "/usuario/deletar/"+id,
	        success: function(retorno) {
	        	$("#myModal").scrollTop(0);
	        	$("#alertMsgIdTable").removeClass("oculta").addClass("alert-success").find('div').append("Deletado com sucesso!");
	        	setTimeout(function(){
	        		$("#alertMsgIdTable").addClass("oculta").removeClass("alert-success").find('div').html("");
	        		CadastroUsuarioController.listar();
        		},2000); 
	        }, 
	        error: function (data) {  
	        	CadastroUsuarioController.erro(data,"alertMsgIdTable");
	        }
	    });
	},
		
	listar(){
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
	        		$("#tableUsuario").find('tbody').append(CadastroUsuarioTemplate.itemLinha(data));
        		});
	        },
	        error: function (data) {  
	        	CadastroUsuarioController.erro(data,"alertMsgIdTable");
	        },
	        complete: function(data) { 
	        	$('#tableUsuario').DataTable( {
	        	    language: {
	        	        url: '//cdn.datatables.net/plug-ins/1.11.3/i18n/pt_br.json'
	        	    }
	        	});
	        }
	    });
	},
	
	addUser(usuario){
		
		$('#myModal').html(CadastroUsuarioTemplate.addUser()).show();
		$('#duallistboxId').bootstrapDualListbox({
			nonSelectedListLabel: 'N\u00e3o Selecionadas',
			selectedListLabel: 'Selecionadas'
		});
		
		if(usuario != undefined){
			$('#campoId').val(usuario.id);
			$('#nomeId').val(usuario.nome);
			$('#especialidadeId').val(usuario.especialidade);
			$('#matriculaId').val(usuario.matricula);
			$('#emailId').val(usuario.email);
			$('#codRfidId').val(usuario.codRfid);
			$('#isAdminId').prop('checked', usuario.isAdmin);
			$('#isLiderId').prop('checked', usuario.isLider);
			
			let status = usuario.status;
			if(usuario.status == null){
				usuario.status = "ativo";
			}
			if(usuario.status == 'FINALIZADO'){
				usuario.status = "ativo";
			}
			$('#statusId').html(usuario.status);
			$('#statusId').attr("class",usuario.status);
			SelectUtil.carregarSelectByList("especialidade/listar","especialidadeSelect",usuario.especialidadeList);
			CadastroUsuarioController.carregarDualList(usuario.faseList);
			return;
		}
		SelectUtil.carregarSelect("especialidade/listar","especialidadeSelect",undefined);
		CadastroUsuarioController.carregarDualList();
	}
	
};

$( document ).ready(function() {
	CadastroUsuarioController.listar();
});



