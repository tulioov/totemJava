
const CadastroUsuarioController = {
	
	listar (){
		$.ajax({
	        type: "GET",
	        contentType: "application/json",
	        url: "/usuario/listar",
	        success: function(retorno) {
	        	console.log("retorno:" + retorno);
	        },

	        error: function(e) {
	            console.log("ERROR : ", e);
	        }
	    });
	}
	
};


