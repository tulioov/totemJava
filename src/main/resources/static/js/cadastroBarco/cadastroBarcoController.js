
let imageUploadBarco;

const CadastroBarcoController = {
		
	tempoEspera(divId){
		setTimeout(function () {
			$('#'+divId).hide(); 
		}, 1500); 
	},
		
	erro(data, alertComponent){
		$("#myModal").scrollTop(0);
    	$("#"+alertComponent).find('div').html("");
    	if(data.responseJSON.statusCode === 404){
    		$("#"+alertComponent).removeClass("oculta").addClass("alert-danger").find('div').append(data.responseJSON.response+"<br>");
    		CadastroBarcoController.tempoEspera(alertComponent);
    		return;
    	}
    	if(data.responseJSON.statusCode === 401){
    		$("#"+alertComponent).removeClass("oculta").addClass("alert-danger").find('div').append(data.responseJSON.response.message+"<br>");
    		CadastroBarcoController.tempoEspera(alertComponent);
    		return;
    	}
    	if(data.responseJSON.statusCode === 500){
    		$("#"+alertComponent).removeClass("oculta").addClass("alert-danger").find('div').append("Erro Interno"+"<br>");
    		return;
    	}
    	retorno = data.responseJSON.response;
    	for (const property in retorno) {
    		if(property == 'stackTrace'){
    			return;
    		}
    		$("#"+property+"Id").addClass("errorInput");
    		$("#"+alertComponent).removeClass("oculta").addClass("alert-danger").find('div').append(retorno[property]+"<br>");
    		CadastroBarcoController.tempoEspera(alertComponent);
		}
	},
		
	salvar(){
		
		let formControl = new Object();
		formControl  = $('#formId').serializeJSON();
		formControl.imagem = $('#base64image').val();
		formControl.dtInicioPrevisto = $("#dtInicioPrevistoId").val().split('-').reverse().join('/');
		formControl.dtFimPrevisto = $("#dtFimPrevistoId").val().split('-').reverse().join('/');
		formControl.hrsBarcoPrevista = $('#hrsBarcoPrevistaId').val()*60;
		let myJsonData = JSON.stringify(formControl);
		
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "POST",
	        url: "/barco/salvar",
	        dataType: "json",
	        cache: false,
	        data : myJsonData,
	        success: function(retorno) {
	        	$("#myModal").scrollTop(0);
	        	$("#alertMsgId").removeClass("oculta").addClass("alert-success").find('div').append("Salvo com sucesso!");
	        	setTimeout(function(){
	        		$("#alertMsgId").addClass("oculta").find('div').removeClass("alert-success").html("");
	        		$('#myModal').modal('hide');
	        		CadastroBarcoController.listar();
        		},2000); 
	        },
	        error: function (data) {   
	        	CadastroBarcoController.erro(data,"alertMsgId");
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
	        url: "/barco/findById/"+id,
	        success: function(retorno) {
	        	CadastroBarcoController.addUser(retorno.response)
	        }, error: function (data) {   
	        	CadastroBarcoController.erro(data,"alertMsgId");
	        },
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
	        url: "/barco/deletar/"+id,
	        success: function(retorno) {
	        	$("#myModal").scrollTop(0);
	        	$("#alertMsgIdTable").removeClass("oculta").addClass("alert-success").find('div').append("Deletado com sucesso!");
	        	setTimeout(function(){
	        		$("#alertMsgIdTable").addClass("oculta").removeClass("alert-success").find('div').html("");
	        		CadastroBarcoController.listar();
        		},2000); 
	        }, error: function (data) {   
	        	CadastroBarcoController.erro(data,"alertMsgIdTable");
	        },
	    });
	},
		
	listar(){
		$('#tableBarco').dataTable().fnClearTable();
	    $('#tableBarco').dataTable().fnDestroy();
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
	        		$("#tableBarco").find('tbody').append(CadastroBarcoTemplate.itemLinha(data));
        		});
	        },
	        error: function (data) {   
	        	CadastroBarcoController.erro(data,"alertMsgIdTable");
	        },
	        complete: function(data) { 
	        	$('#tableBarco').DataTable( {
	        	    language: {
	        	        url: '//cdn.datatables.net/plug-ins/1.11.3/i18n/pt_br.json'
	        	    }
	        	});
	        }
	    });
	},
	
	addUser(barco){
		
		$('#myModal').html(CadastroBarcoTemplate.add()).show();
		$('[name=duallistbox]').bootstrapDualListbox({
			nonSelectedListLabel: 'N\u00e3o Selecionadas',
			selectedListLabel: 'Selecionadas'
		});
		
		CadastroBarcoController.bs_input_file();
		
		if(barco != undefined){
			$('#campoId').val(barco.id);
			$('#nomeId').val(barco.nome);
			$('#hrsBarcoPrevistaId').val(barco.hrsBarcoPrevista/60);
			$('#dtInicioPrevistoId').val(barco.dtInicioPrevisto.split('/').reverse().join('-'));
			$('#dtFimPrevistoId').val(barco.dtFimPrevisto.split('/').reverse().join('-'));
			$('#descricaoId').val(barco.descricao);
			$('#base64image').attr('src', barco.imagem); 
			$('#base64image').val(barco.imagem);
		}
	},
	
	upImg(){
		let file = $('#imageId')[0].files[0];
		var reader = new FileReader();
			reader.readAsDataURL(file);
			reader.onload = function () {
			fileBase64 = reader.result;
			$('#base64image').attr('src', fileBase64); 
			$('#base64image').val(fileBase64);
		};
		   reader.onerror = function (error) {
		   console.log('Error: ', error);
	    };
	},
	
	bs_input_file() {
	    $(".input-file").before(
	        function () {
	            if (!$(this).prev().hasClass('input-ghost')) {
	                var element = $("<input id='imageId' type='file' onchange='CadastroBarcoController.upImg();' class='input-ghost' style='visibility:hidden; height:0'>");
	                element.attr("name", $(this).attr("name"));
	                element.change(function () {
	                    element.next(element).find('input').val((element.val()).split('\\').pop());
	                });
	                $(this).find("button.btn-choose").click(function () {
	                    element.click();
	                });
	                $(this).find('input').css("cursor", "pointer");
	                $(this).find('input').mousedown(function () {
	                    $(this).parents('.input-file').prev().click();
	                    return false;
	                });
	                return element;
	            }
	        }
	    );
	}
	
};

$( document ).ready(function() {
	CadastroBarcoController.listar();
});