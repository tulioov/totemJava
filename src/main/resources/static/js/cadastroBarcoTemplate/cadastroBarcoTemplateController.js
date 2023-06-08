
const CadastroBarcoTemplateController = {
		
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
    		CadastroBarcoTemplateController.tempoEspera(alertComponent);
    		return;
    	}
    	if(data.responseJSON.statusCode === 401){
    		$("#"+alertComponent).removeClass("oculta").addClass("alert-danger").find('div').append(data.responseJSON.response.message+"<br>");
    		CadastroBarcoTemplateController.tempoEspera(alertComponent);
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
    		CadastroBarcoTemplateController.tempoEspera(alertComponent);
		}
	},
	
	carregarDualList(atividadeList){
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "GET",
	        contentType: "application/json",
	        url: "/atividade/listar",
	        success: function(retorno) {
	        	$(retorno.response).each(function(index, data) {
	        		if (atividadeList != undefined){
        				if(atividadeList.some(atividade => atividade.id === data.id)){
	        				$("#duallistboxId").append(`<option value="${data.id}" selected="selected" >${data.nome}</option>`);
        				}else{
        					$("#duallistboxId").append(`<option value="${data.id}">${data.nome}</option>`);
        				}
	        		}else{
	        			$("#duallistboxId").append(`<option value="${data.id}">${data.nome}</option>`);
	        		}
        		});
	        },complete: function(data) { 
	        	$("#duallistboxId").bootstrapDualListbox('refresh');
	        }
	    });
	},
		
	salvar(){
		
		let formControl = new Object();
		formControl  = $('#formId').serializeJSON();
		formControl.atividadeList = $('#duallistboxId').val();
		formControl.imagem = $('#base64image').val();
		let myJsonData = JSON.stringify(formControl);
		
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "POST",
	        url: "/barcoTemplate/salvar",
	        dataType: "json",
	        cache: false,
	        data : myJsonData,
	        success: function(retorno) {
	        	$("#myModal").scrollTop(0);
	        	$("#alertMsgId").removeClass("oculta").addClass("alert-success").find('div').append("Salvo com sucesso!");
	        	setTimeout(function(){
	        		$("#alertMsgId").addClass("oculta").find('div').removeClass("alert-success").html("");
	        		$('#myModal').modal('hide');
	        		CadastroBarcoTemplateController.listar();
        		},2000); 
	        },
	        error: function (data) {   
	        	CadastroBarcoTemplateController.erro(data,"alertMsgId");
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
	        url: "/barcoTemplate/findById/"+id,
	        success: function(retorno) {
	        	CadastroBarcoTemplateController.addUser(retorno.response)
	        }, error: function (data) {   
	        	CadastroBarcoTemplateController.erro(data,"alertMsgId");
	        }
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
	        url: "/barcoTemplate/deletar/"+id,
	        success: function(retorno) {
	        	$("#myModal").scrollTop(0);
	        	$("#alertMsgIdTable").removeClass("oculta").addClass("alert-success").find('div').append("Deletado com sucesso!");
	        	setTimeout(function(){
	        		$("#alertMsgIdTable").addClass("oculta").removeClass("alert-success").find('div').html("");
	        		CadastroBarcoTemplateController.listar();
        		},2000); 
	        }, error: function (data) {  
	        	CadastroBarcoTemplateController.erro(data,"alertMsgIdTable");
	        }
	    });
	},
		
	listar(){
		$('#tableBarcoTemplate').dataTable().fnClearTable();
	    $('#tableBarcoTemplate').dataTable().fnDestroy();
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "GET",
	        contentType: "application/json",
	        url: "/barcoTemplate/listar",
	        success: function(retorno) {
	        	$(retorno.response).each(function(index, data) {
	        		$("#tableBarcoTemplate").find('tbody').append(CadastroBarcoTemplateTemplate.itemLinha(data));
        		});
	        },error: function (data) {   
	        	CadastroBarcoTemplateController.erro(data,"alertMsgIdTable");
	        },
	        complete: function(data) { 
	        	$('#tableBarcoTemplate').DataTable( {
	        	    language: {
	        	        url: '//cdn.datatables.net/plug-ins/1.11.3/i18n/pt_br.json'
	        	    }
	        	});
	        }
	    });
	},
	
	addUser(barcoTemplate){
		
		$('#myModal').html(CadastroBarcoTemplateTemplate.add()).show();
		
		$("#nomeId").keyup(function(){
			const reg = /[^a-zA-Z0-9 ]+/g;
			let texto = $("#nomeId").val().replace(reg,'');
			$("#constanteCampoId").val(texto.replaceAll(' ','_'));
		});
		
		$("#duallistboxId").bootstrapDualListbox({
			nonSelectedListLabel: 'N\u00e3o Selecionadas',
			selectedListLabel: 'Selecionadas'
		});
		
		CadastroBarcoTemplateController.bs_input_file();
		
		if(barcoTemplate != undefined){
			$('#campoId').val(barcoTemplate.id);
			$('#base64image').attr('src', barcoTemplate.imagem); 
			$('#base64image').val(barcoTemplate.imagem);
			$('#nomeId').val(barcoTemplate.nome);
			$('#constanteCampoId').val(barcoTemplate.constanteCampo);
			CadastroBarcoTemplateController.carregarDualList(barcoTemplate.atividadeList);
			return;
		}
		CadastroBarcoTemplateController.carregarDualList();
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
	                var element = $("<input id='imageId' type='file' onchange='CadastroBarcoTemplateController.upImg();' class='input-ghost' style='visibility:hidden; height:0'>");
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
	CadastroBarcoTemplateController.listar();
});



