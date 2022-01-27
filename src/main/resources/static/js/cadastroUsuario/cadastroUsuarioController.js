
const CadastroUsuarioController = {
		
	salvar(){
		
		$.ajax({
			headers: {
	            'Authorization':'1',
	            'Content-Type':'application/json'
	        },
	        type: "POST",
	        url: "/usuario/salvar",
	        dataType: "json",
	        cache: false,
	        data : JSON.stringify($('#formId').serializeJSON()),
	        success: function(retorno) {
	        	console.log("retorno:" + retorno);
	        },
	        error: function (data) {   
	        	$("#alertMsgId").find('div').html("");
	        	retorno = data.responseJSON.response;
	        	for (const property in retorno) {
	        		$("#"+property+"Id").addClass("errorInput");
	        		$("#alertMsgId").removeClass("oculta").find('div').append(retorno[property]+"<br>");
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
	        	CadastroUsuarioController.addUser(retorno.response)
	        }, error: function (data) {   
	        	console.log(data)
	        }
	    });
	},
		
	listar(){
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
		$('[name=duallistbox]').bootstrapDualListbox({
			nonSelectedListLabel: 'N\u00e3o Selecionadas',
			selectedListLabel: 'Selecionadas'
		});
		
		if(usuario != undefined){
			$('#campoId').val(usuario.id);
			$('#nomeId').val(usuario.nome);
			$('#especialidadeId').val(usuario.especialidade);
		}
	}
	
};

$( document ).ready(function() {
	CadastroUsuarioController.listar();
});



