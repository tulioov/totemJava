
const LoginController = {
		
	login(){
		
		let formControl = new Object();
		formControl  = $('#formId').serializeJSON();
		let myJsonData = JSON.stringify(formControl);
		$.ajax({
			headers: {
	            'Authorization':'1',
	            'Content-Type':'application/json'
	        },
	        type: "POST",
	        url: "/login",
	        dataType: "json",
	        cache: false,
	        data : myJsonData,
	        success: function(retorno) {
	        },
	        error: function (data) {   
	        },
	    });
	},
};
