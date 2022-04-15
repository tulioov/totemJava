
const MonitorUserController = {
		
	abrirEscolhaEtapa(id){
		$('#myModal').html(MonitorUserTemplate.abrirEscolhaEtapa())
	},
	
	abrirEscolhaBarco(){
		
		$('#myModal').html(MonitorUserTemplate.modalEscolhaBarco())
		
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "GET",
	        contentType: "application/json",
	        url: "/barco/listar",
	        success: function(retorno) {
	        	$(retorno.response).each(function(index, data) {
	        		$('#imgEscolhaBarco').append(
        				`<div class="col-md-4 mt15">
        					<img 
        						style='display:block; width:16em;height:9em;' 
        						id='base64image-${data.id}' 
        						src='${data.imagem}'
        						onClick="MonitorUserController.abrirEscolhaEtapa(${data.id})"
    						/>
        				</div>`);
        		});
	        }
	    });
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