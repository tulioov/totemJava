
const CadastroEtapaController = {
		
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
    	retorno = data.responseJSON.response;
    	for (const property in retorno) {
    		if(property == 'stackTrace'){
    			return;
    		}
    		$("#"+property+"Id").addClass("errorInput");
    		$("#"+alertComponent).removeClass("oculta").addClass("alert-danger").find('div').append(retorno[property]+"<br>");
		}
	},
		
	salvar(){
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "POST",
	        url: "/etapa/salvar",
	        dataType: "json",
	        cache: false,
	        data : JSON.stringify($('#formId').serializeJSON()),
	        success: function(retorno) {
	        	$("#myModal").scrollTop(0);
	        	$("#alertMsgId").removeClass("oculta").addClass("alert-success").find('div').append("Salvo com sucesso!");
	        	setTimeout(function(){
	        		$("#alertMsgId").addClass("oculta").find('div').removeClass("alert-success").html("");
	        		$('#myModal').modal('hide');
	        		CadastroEtapaController.listar();
        		},2000); 
	        },
	        error: function (data) {   
	        	CadastroEtapaController.erro(data,"alertMsgId");
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
	        url: "/etapa/findById/"+id,
	        success: function(retorno) {
	        	CadastroEtapaController.addUser(retorno.response)
	        }, error: function (data) {   
	        	CadastroEtapaController.erro(data,"alertMsgId");
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
	        url: "/etapa/deletar/"+id,
	        success: function(retorno) {
	        	$("#myModal").scrollTop(0);
	        	$("#alertMsgIdTable").removeClass("oculta").addClass("alert-success").find('div').append("Deletado com sucesso!");
	        	setTimeout(function(){
	        		$("#alertMsgIdTable").addClass("oculta").removeClass("alert-success").find('div').html("");
	        		CadastroEtapaController.listar();
        		},2000); 
	        }, error: function (data) {  
	        	CadastroEtapaController.erro(data,"alertMsgIdTable");
	        }
	    });
	},
		
	listar(){
		$('#tableEtapa').dataTable().fnClearTable();
	    $('#tableEtapa').dataTable().fnDestroy();
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "GET",
	        contentType: "application/json",
	        url: "/etapa/listar",
	        success: function(retorno) {
	        	$(retorno.response).each(function(index, data) {
	        		$("#tableEtapa").find('tbody').append(CadastroEtapaTemplate.itemLinha(data));
        		});
	        },error: function (data) {   
	        	CadastroEtapaController.erro(data,"alertMsgIdTable");
	        },
	        complete: function(data) { 
	        	$('#tableEtapa').DataTable( {
	        	    language: {
	        	        url: '//cdn.datatables.net/plug-ins/1.11.3/i18n/pt_br.json'
	        	    }
	        	});
	        }
	    });
	},
	
	addUser(etapa){
		
		$('#myModal').html(CadastroEtapaTemplate.add()).show();
		
		if(etapa != undefined){
			$('#campoId').val(etapa.id);
			$('#nomeId').val(etapa.nome);
			$('#descricaoId').val(etapa.descricao);
			$('#constanteCampoId').val(etapa.constanteCampo);
		}
	}
};

$( document ).ready(function() {
	CadastroEtapaController.listar();
});



