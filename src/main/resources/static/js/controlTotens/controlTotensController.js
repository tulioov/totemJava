
const ControlTotensController = {
		
	abrirModal(){
		$('#myModal').html(ControlTotensTemplate.modalToten())
		$('#tableMonitorUser').DataTable( {
		    language: {
		        url: '//cdn.datatables.net/plug-ins/1.11.3/i18n/pt_br.json'
		    }
		})
	}
	
};

$( document ).ready(function() {
	
	
});



