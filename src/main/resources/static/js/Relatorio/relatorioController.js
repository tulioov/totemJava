
const RelatorioController = {
		
	init(){
		SelectUtil.carregarSelect("usuario/listar","selectUsuarioId");
	},
	
	pesquisa(){
		
		$('#tablePesquisa').dataTable().fnClearTable();
	    $('#tablePesquisa').dataTable().fnDestroy();
		
		let formControl  = $('#formId').serializeJSON();
		formControl.usuarioLstId = $('#selectUsuarioId').val();
		formControl.dataInidio = $('#dtInicioId').val();
		formControl.dataFim = $('#dtFimId').val();
		let myJsonData = JSON.stringify(formControl);
		
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "POST",
	        url: "/monitoracao/listarMonitoracaoByFiltro/",
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
	},
	donwload(){
			
		$("#loadingId").removeClass("oculta");
		
		let objectGet = new Object();
		objectGet.usuarioLstId = $('#selectUsuarioId').val();
		objectGet.dataInidio = $('#dtInicioId').val();
		objectGet.dataFim = $('#dtFimId').val();
		
		var xhr = new XMLHttpRequest();
		xhr.open("POST", "/monitoracaoXLS/listarMonitoracaoByFiltroXLS/");
		xhr.setRequestHeader('Authorization', email);
		xhr.responseType = 'blob';
		xhr.setRequestHeader("Content-Type", "application/json");

		xhr.onload = function(e) {
		    if (this.status == 200) {
		        var blob = new Blob([this.response], {type: 'application/vnd.ms-excel'});
		        var a = document.createElement("a");
		        a.href = URL.createObjectURL(blob);
		        a.download = "Relatorio.xls";
		        document.body.appendChild(a);
		        a.click();
		    } else {
		        alert('Unable to download excel.')
		    }
		    $("#loadingId").addClass("oculta");
		};

		xhr.send(JSON.stringify(objectGet));
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