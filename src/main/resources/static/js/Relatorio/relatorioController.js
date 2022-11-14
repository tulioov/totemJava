
const RelatorioController = {
		
	init(){
		SelectUtil.carregarSelect("usuario/listar","selectUsuarioId");
	},
	
	pesquisa(){
		
		$('#tablePesquisa').dataTable().fnClearTable();
	    $('#tablePesquisa').dataTable().fnDestroy();
		
		let formControl  = $('#formId').serializeJSON();
		formControl.usuarioLstId = $('#selectUsuarioId').val();
		let myJsonData = JSON.stringify(formControl);
		
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "POST",
	        url: "/monitoracao/listarMonitoracaoByUsuarios/",
	        dataType: "json",
	        cache: false,
	        data : myJsonData,
	        success: function(retorno) {
	        	$(retorno.response).each(function(index, data) {
	        		$("#tablePesquisa").find('tbody').append(RelatorioTemplate.itemLinha(data));
        		});
	        },
	        error: function (data) {   
	        },
	        complete: function(data) { 
	        	$('#tablePesquisa').DataTable( {
	        	    language: {
	        	        url: '//cdn.datatables.net/plug-ins/1.11.3/i18n/pt_br.json'
	        	    }
	        	});
	        }
	    });
	}
};

$( document ).ready(function() {
	
	RelatorioController.init();
	
	$('#tablePesquisa').DataTable( {
	    language: {
	        url: '//cdn.datatables.net/plug-ins/1.11.3/i18n/pt_br.json'
	    }
	});
});