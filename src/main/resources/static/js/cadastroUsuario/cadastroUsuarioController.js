
const CadastroUsuarioController = {
		
	listar (){
		$.ajax({
	        type: "GET",
	        contentType: "application/json",
	        url: "/usuario/listar",
	        success: function(retorno) {
	        	console.log("retorno:" + retorno);
	        }
	    });
	}
	
};

$( document ).ready(function() {
	
	$('#tableUsuario').DataTable( {
	    language: {
	        url: '//cdn.datatables.net/plug-ins/1.11.3/i18n/pt_br.json'
	    }
	});
	
});



