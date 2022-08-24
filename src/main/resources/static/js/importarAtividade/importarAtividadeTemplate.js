
const ImportAtividadeTemplate= {
		
	itemLinha(data){
		
		data.status = "Ok";
		
		if(data.Codigo == null ||
		data.Nome == null){
    		$("#salvarIdButton").addClass("oculta");
			data.status = "Falha";
		}
		
		const reg = /[^a-zA-Z0-9 ]+/g;
		let texto = data.Nome.replace(reg,'');
		
		return `
			<tr>
				<td class='uppercase'>${data.Codigo}</td>
				<td class='uppercase'>${data.Nome}</td>
				<td class='uppercase'>${texto.replaceAll(' ','_')}</td>
				<td class='uppercase'>${data.Tempo}</td>
				<td><span class="${data.status}">${data.status}</span></td>
			</tr>
		`;
	}
};