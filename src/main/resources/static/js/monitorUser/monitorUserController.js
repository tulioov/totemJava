
const MonitorUserController = {
		
//	listar (){
//		$.ajax({
//	        type: "GET",
//	        contentType: "application/json",
//	        url: "/usuario/listar",
//	        success: function(retorno) {
//	        	console.log("retorno:" + retorno);
//	        }
//	    });
//	}
		
	abrirModal(){
		$('#myModal').html(MonitorUserTemplate.modal())
	},
	
	abrirModalContinuidade(){
		$('#myModal').html(MonitorUserTemplate.modalContinuar())
	},
	
	abrirModalSaida(){
		$('#myModal').html(MonitorUserTemplate.modalSaida())
	}
};



$( document ).ready(function() {
	
	$('#tableMonitorUser').DataTable( {
	    language: {
	        url: '//cdn.datatables.net/plug-ins/1.11.3/i18n/pt_br.json'
	    }
	});
	
});



