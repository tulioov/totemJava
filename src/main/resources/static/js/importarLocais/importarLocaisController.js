
const ImportLocaisController = {
		
	salvar(){
		alert("Desenvolvimento em andamento pelo suporte de TI");
	},
		
	downloadTemplateExcel(){
		
		// criando XLS
		var wb = XLSX.utils.book_new();
		wb.Props = {
                Title: "SheetJS java",
                Subject: "Locais",
                Author: "JavaAPPTOTEM",
                CreatedDate: new Date(2017,12,19)
        };
		wb.SheetNames.push("Locais");
		let colunas = [ 'Codigo' , 'Nome'];
		var ws_data = [colunas];
		var ws = XLSX.utils.aoa_to_sheet(ws_data);
		wb.Sheets["Locais"] = ws;
		
		// Dowload XLS
		var wbout = XLSX.write(wb, {bookType:'xlsx',  type: 'binary'});
        var buf = new ArrayBuffer(wbout.length); // convert s to arrayBuffer
        var view = new Uint8Array(buf);  // create uint8array as viewer
       
        for (var i=0; i<wbout.length; i++) view[i] = wbout.charCodeAt(i) & 0xFF;
        saveAs(new Blob([buf],{type:"application/octet-stream"}), 'templateLocais.xlsx');
		
	},
	
	importandoArquivo(evt) {
		var files = evt.target.files;
		var xl2json = new ExcelToJSON();
		xl2json.parseExcel(files[0]);
	}
	
};



$( document ).ready(function() {
	
	$('#tableLocais').DataTable( {
	    language: {
	        url: '//cdn.datatables.net/plug-ins/1.11.3/i18n/pt_br.json'
	    }
	});
	
	document.getElementById('fileupload').addEventListener('change', ImportLocaisController.importandoArquivo, false);
	
	ExcelToJSON = function() {
		this.parseExcel = function(file) {
			var reader = new FileReader();
		    reader.onload = function(e) {
		    	var data = e.target.result;
		    	var workbook = XLSX.read(data, { type: 'binary' });
		    	workbook.SheetNames.forEach(function(sheetName) {
		    		var XL_row_object = XLSX.utils.sheet_to_row_object_array(workbook.Sheets[sheetName]);
		    		var rowsLst = JSON.parse(JSON.stringify(XL_row_object));
		    		$("#tableLocais").find('tbody').html('');
		    		$(rowsLst).each(function(linha, data) {
		        		$("#tableLocais").find('tbody').append(ImportLocaisTemplate.itemLinha(data));
	        		});
		    	})
		    };
		    reader.onerror = function(ex) {
		      console.log(ex);
		    };
		    reader.readAsBinaryString(file);
		};
	};
});