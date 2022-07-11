
const ControlTotensController = {
		
	init(){
		
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "GET",
	        contentType: "application/json",
	        url: "barco/listar",
	        success: function(retorno) {
	        	barcos = retorno.response;
	        	$(retorno.response).each(function(index, barco) {
	        		$('#painelId').append(ControlTotensTemplate.carregarPainel(barco));
	        	});
	        }
	    });
	},
		
	abrirModal(nome){
		
		let barco = barcos.find(b => b.nome === nome);
		
		$('#myModal').html(ControlTotensTemplate.modalToten(barco));
		$('#tableMonitorUser').DataTable().destroy();
		
		$(barco.monitoracao).each(function(inde, dat) {
			$("#tableMonitorUser").find('tbody').append(ControlTotensTemplate.itemLinha(dat));
		});
		
		$('#tableMonitorUser').DataTable({
		    language: {
		        url: '//cdn.datatables.net/plug-ins/1.11.3/i18n/pt_br.json'
		    }
		});
	}
	
};

$(document).ready(function() {
	ControlTotensController.init();
});