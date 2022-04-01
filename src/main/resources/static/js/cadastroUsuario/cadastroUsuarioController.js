
const CadastroUsuarioController = {
		
	carregarDualList(etapaList){
		$.ajax({
			headers: {
	            'Authorization':'1',
	            'Content-Type':'application/json'
	        },
	        type: "GET",
	        contentType: "application/json",
	        url: "/etapa/listar",
	        success: function(retorno) {
	        	$(retorno.response).each(function(index, data) {
	        		if (etapaList != undefined){
        				if(etapaList.some(etapa => etapa.id === data.id)){
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
		formControl.etapaList = $('#duallistboxId').val();
		formControl.isAdmin = $('#isAdminId').prop('checked');
		let myJsonData = JSON.stringify(formControl);
		
		$.ajax({
			headers: {
	            'Authorization':'1',
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
	        	$("#myModal").scrollTop(0);
	        	$("#alertMsgId").find('div').html("");
	        	retorno = data.responseJSON.response;
	        	for (const property in retorno) {
	        		$("#"+property+"Id").addClass("errorInput");
	        		$("#alertMsgId").removeClass("oculta").addClass("alert-danger").find('div').append(retorno[property]+"<br>");
        		}
	        },
	    });
	},
	
	editar(id){
		$.ajax({
			headers: {
	            'Authorization':'1',
	            'Content-Type':'application/json'
	        },
	        type: "GET",
	        contentType: "application/json",
	        url: "/usuario/findById/"+id,
	        success: function(retorno) {
	        	CadastroUsuarioController.addUser(retorno.response)
	        }, error: function (data) {   
	        	console.log(data)
	        }
	    });
	},
	
	deletar(id){
		$.ajax({
			headers: {
	            'Authorization':'1',
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
	        }, error: function (data) {  
	        	$("#myModal").scrollTop(0);
	        	console.log(data)
	        }
	    });
	},
		
	listar(){
		$('#tableUsuario').dataTable().fnClearTable();
	    $('#tableUsuario').dataTable().fnDestroy();
		$.ajax({
			headers: {
	            'Authorization':'1',
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
			$('#codRfidId').val(usuario.codRfid);
			$('#isAdminId').prop('checked', usuario.isAdmin);
			CadastroUsuarioController.carregarDualList(usuario.etapaList);
			return;
		}
		CadastroUsuarioController.carregarDualList();
	}
	
};

$( document ).ready(function() {
	CadastroUsuarioController.listar();
});



