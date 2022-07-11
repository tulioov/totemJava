
const ControlTotensTemplate = {
		
		
	carregarPainel(barco){
		return`
				<div class="col-md-4">
					<button onclick="ControlTotensController.abrirModal('${barco.nome}');" data-toggle="modal" data-target="#myModal" type="button" class="btn btn-primary" style="width: 30rem;">
			  			<div class="card-body">
			    		<h5 class="card-title">${barco.nome}</h5>
			    		<div class="card-text">
				    		<div>
								<div class="progress">
								  <div class="progress-bar bg-success" role="progressbar" style="width: ${(barco.hrsBarcoTrabalhadas*100/barco.hrsBarcoPrevista)}%"  aria-valuenow="25" aria-valuemin="0" aria-valuemax="${barco.hrsBarcoPrevista}">%</div>
								</div>
							</div>
						</div>
						</div>
					</button>
				</div>
			`;
	},
	
	itemLinha(data){
		return `
			<tr>
				<td>${data.id}</td>
				<td>${data.usuario.nome}</td>
				<td>${data.subAtividade.descricao}</td>
				<td>${data.dtInicioAtividade}</td>
				<td>${data.tempoTrabalho}</td>
				<td>${data.status}</td>
			</tr>
		`;
	},
		
	modalToten(barco){
		return`
			<div class="modal-dialog modal-lg ">
		        <div class="modal-content">
		            <div class="modal-header">
		                <h4 class="modal-title">Adicionar Feriado</h4>
		            </div>
		            <div class="modal-body">
			            
		                <form id="formId">

				            <h3>Processo de fabrica\u00e7\u00e3o do barco ${barco.nome}</h3>
							<div class="row">
								<div class="col-md-4 ">
									<img style='width:16em;height:9em;' src='${barco.imagem}'/>
								</div>
								<div class="col-md-6 mt3em">
									<h4>Data de inicio Previsto= ${barco.dtInicioPrevisto}</h4> 
									<h4>Data de Termino Previsto = ${barco.dtFimPrevisto}</h4> 
								</div>
							</div>
							<div class="row mt15">
								<h4 class="col-md-6">${barco.dtInicio==undefined?'Aguardando Inicio':barco.dtInicio}</h4> 
								<h4 class="col-md-6 text-right">${barco.dtFim==undefined?'Aguardando Inicio':barco.dtFim}</h4> 
							</div>
							<div class="progress mt15">
							  	<div class="progress-bar bg-success" role="progressbar" style="width: ${(barco.hrsBarcoTrabalhadas*100/barco.hrsBarcoPrevista)}%"  aria-valuenow="25" aria-valuemin="0" aria-valuemax="${barco.hrsBarcoPrevista}">%</div>
							</div>
							<hr/>
							<div class="row">
								<table id="tableMonitorUser">
									<thead>
								    <tr>
								        <th>ID</th>
								        <th>Nome</th>
								        <th>Trabalhando em</th>
								        <th>Hr. Entrada</th>
								        <th>Tempo(Minutos)</th>
								        <th>Status</th>
								    </tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
    			        </form>
		            </div>
		            <div class="modal-footer">
		            	<button type="button" class="btn btn-success" onClick="CadastroFeriadoController.salvar()" >Salvar</button>
		                <button type="button" class="btn btn-danger" onClick="$('#myModal').hide();" data-dismiss="modal">Fechar</button>
		            </div>
		        </div>
		    </div>
			`;
	}
};

