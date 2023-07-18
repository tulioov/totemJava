
const ImportAtividadeTemplate= {
		
	itemLinha(data){
		
		data.status = "Ok";
		
		if(data.Fase == null ||
		data.Embarca\u00e7\u00e3o == null){
    		$("#salvarIdButton").addClass("oculta");
			data.status = "Falha";
		}
		
		const reg = /[^a-zA-Z0-9 ]+/g;
		let constanteCampo = data.Fase + '-' + data.Embarca\u00e7\u00e3o.replace(reg,'');
		
		
		return `
			<tr>
				<td class='uppercase'>${data.Fase}</td>
				<td class='uppercase'>${data.Embarca\u00e7\u00e3o}</td>
				<td class='uppercase'>${constanteCampo}</td>
				<td class='uppercase'>${data.Descri\u00e7\u00e3o.replaceAll(' ','_')}</td>
				<td class='uppercase'>${data.Tempo}</td>
				<td><span class="${data.status}">${data.status}</span></td>
			</tr>
		`;
	}
};