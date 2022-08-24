const SelectUtil = {
		
	carregarSelect(url,selectId){
		
		$.ajax({
			headers: {
	            'Authorization': email,
	            'Content-Type':'application/json'
	        },
	        type: "GET",
	        contentType: "application/json",
	        url: url,
	        success: function(retorno) {
	        	$(retorno.response).each(function(index, data) {
	        		$('#'+selectId).append('<option value='+data.id+' >'+data.nome+'</option>');
	        	});
	        	$('.selectpicker').selectpicker('refresh');
	        }
	    });
	},
	
	carregarSelectChange(url, selectId, selectChangeId){
		$('#'+selectChangeId).on('change', function() {
			$.ajax({
				headers: {
		            'Authorization': email,
		            'Content-Type':'application/json'
		        },
		        type: "GET",
		        async: false,
		        contentType: "application/json",
		        url: url+$('#'+selectChangeId).val(),
		        success: function(retorno) {
		        	$(retorno.response).each(function(index, data) {
		        		$('#'+selectId).append('<option value='+data.id+' >'+data.nome+'</option>');
		        	});
		        	$('#'+selectId).removeAttr("disabled")
		        	$('.selectpicker').selectpicker('refresh');
		        }
		    });
		});
	},
	
	resetListSelectChange(selectChangeId, arraySelect, isDisabled){
		$('#'+selectChangeId).on('change', function() {
			$(arraySelect).each(function(index,selectId ) {
				$("#"+selectId).empty().selectpicker('refresh');
				if(isDisabled){
					$('#'+selectId).attr("disabled",true);
					$('.selectpicker').selectpicker('refresh');
				}
	    	});
		});
	}
};