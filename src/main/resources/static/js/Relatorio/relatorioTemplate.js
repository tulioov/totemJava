
const RelatorioTemplate = {
		
	itemLinha(data){
		
		
		return `
			<tr>
				<td class='uppercase'>${data.usuario.matricula}</td>
				<td class='uppercase'>${data.usuario.nome}</td>
				<td class='uppercase'>${data.atividade.constanteCampo}</td>
				<td class='uppercase'>${data.tempoTrabalhoFimIni}</td>
				<td class='uppercase'>${data.statusMonitoracao.constanteCampo}</td>
			</tr>
		`;
	}
};