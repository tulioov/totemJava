
const CadastroUsuarioController = {
		
	
	salvar(){
		
		$.ajax({
	        type: "POST",
	        url: "/usuario/salvar",
	        contentType: "application/json",
	        dataType: "json",
	        cache: false,
//	        data: JSON.stringify({"nome":"fwefwef"}),
	        data : JSON.stringify($('#formId').serializeJSON()),
	        success: function(retorno) {
	        	console.log("retorno:" + retorno);
	        }
	    });
	},
		
		
		
	listar(){
		$.ajax({
	        type: "GET",
	        contentType: "application/json",
	        url: "/usuario/listar",
	        success: function(retorno) {
	        	console.log("retorno:" + retorno);
	        }
	    });
	},
	
	addUser(){
		$('#myModal').html(CadastroUsuarioTemplate.addUser());
		$('[name=duallistbox]').bootstrapDualListbox({
			nonSelectedListLabel: 'N\u00e3o Selecionadas',
			selectedListLabel: 'Selecionadas'
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



