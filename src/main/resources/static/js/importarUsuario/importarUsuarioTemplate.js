
const ImportUsuarioTemplate= {
		
	itemLinha(data){
		
		data.status = "Ok";
		
		if(data.RFID == null ||
		data.Matricula  == null||
		data.Nome == null ||
		data.Especialidade == null){
    		$("#salvarIdButton").addClass("oculta");
			data.status = "Falha";
		}
		
		return `
			<tr>
				<td class='uppercase'>${data.RFID}</td>
				<td class='uppercase'>${data.Matricula}</td>
				<td class='uppercase'>${data.Nome}</td>
				<td class='uppercase'>${data.Especialidade}</td>
				<td><span class="${data.status}">${data.status}</span></td>
			</tr>
		`;
	}
};