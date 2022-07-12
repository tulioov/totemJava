
const CadastroMaterialController = {
		
	tempoEspera(divId){
		setTimeout(function () {
			$('#'+divId).hide(); 
		}, 1500); 
	}
		
	erro(data, alertComponent){
		$("#myModal").scrollTop(0);
    	$("#"+alertComponent).find('div').html("");
    	if(data.responseJSON.statusCode === 404){
    		$("#"+alertComponent).removeClass("oculta").addClass("alert-danger").find('div').append(data.responseJSON.response+"<br>");
    		CadastroMaterialController.tempoEspera(alertComponent);
    		return;
    	}
    	if(data.responseJSON.statusCode === 401){
    		$("#"+alertComponent).removeClass("oculta").addClass("alert-danger").find('div').append(data.responseJSON.response.message+"<br>");
    		CadastroMaterialController.tempoEspera(alertComponent);
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
    		CadastroMaterialController.tempoEspera(alertComponent);
		}
	},
		
	salvar(){
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "POST",
	        url: "/material/salvar",
	        dataType: "json",
	        cache: false,
	        data : JSON.stringify($('#formId').serializeJSON()),
	        success: function(retorno) {
	        	$("#myModal").scrollTop(0);
	        	$("#alertMsgId").removeClass("oculta").addClass("alert-success").find('div').append("Salvo com sucesso!");
	        	setTimeout(function(){
	        		$("#alertMsgId").addClass("oculta").find('div').removeClass("alert-success").html("");
	        		$('#myModal').modal('hide');
	        		CadastroMaterialController.listar();
        		},2000); 
	        },
	        error: function (data) {   
	        	CadastroMaterialController.erro(data,"alertMsgId");
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
	        url: "/material/findById/"+id,
	        success: function(retorno) {
	        	CadastroMaterialController.addUser(retorno.response)
	        }, error: function (data) {   
	        	CadastroMaterialController.erro(data,"alertMsgId");
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
	        url: "/material/deletar/"+id,
	        success: function(retorno) {
	        	$("#myModal").scrollTop(0);
	        	$("#alertMsgIdTable").removeClass("oculta").addClass("alert-success").find('div').append("Deletado com sucesso!");
	        	setTimeout(function(){
	        		$("#alertMsgIdTable").addClass("oculta").removeClass("alert-success").find('div').html("");
	        		CadastroMaterialController.listar();
        		},2000); 
	        }, error: function (data) {  
	        	CadastroMaterialController.erro(data,"alertMsgIdTable");
	        }
	    });
	},
		
	listar(){
		$('#tableMaterial').dataTable().fnClearTable();
	    $('#tableMaterial').dataTable().fnDestroy();
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "GET",
	        contentType: "application/json",
	        url: "/material/listar",
	        success: function(retorno) {
	        	$(retorno.response).each(function(index, data) {
	        		$("#tableMaterial").find('tbody').append(CadastroMaterialTemplate.itemLinha(data));
        		});
	        },
	        error: function (data) {  
	        	CadastroMaterialController.erro(data,"alertMsgIdTable");
	        },
	        complete: function(data) { 
	        	$('#tableMaterial').DataTable( {
	        	    language: {
	        	        url: '//cdn.datatables.net/plug-ins/1.11.3/i18n/pt_br.json'
	        	    }
	        	});
	        }
	    });
	},
	
	addUser(material){
		
		$('#myModal').html(CadastroMaterialTemplate.add()).show();
		$('[name=duallistbox]').bootstrapDualListbox({
			nonSelectedListLabel: 'N\u00e3o Selecionadas',
			selectedListLabel: 'Selecionadas'
		});
		
		if(material != undefined){
			$('#campoId').val(material.id);
			$('#nomeId').val(material.nome);
			$('#descricaoId').val(material.descricao);
		}
	}
};

$( document ).ready(function() {
	CadastroMaterialController.listar();
});



