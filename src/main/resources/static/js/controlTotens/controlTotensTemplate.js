
const ControlTotensTemplate = {
		
		
	carregarPainel(barco){
		return`
				<div class="col-md-4 ">
					<div class="cardOkean" style="width: 30rem;">
			  			<div class="card-body">
				    		<h5 class="card-title">${barco.nome}</h5>
				    		<div class="card-text">
					    		<div>
									<div class="progress">
									  <div class="progress-bar bg-success" role="progressbar" style="width: ${(barco.hrsBarcoTrabalhadas*100/barco.hrsBarcoPrevista)}%"  aria-valuenow="25" aria-valuemin="0" aria-valuemax="${barco.hrsBarcoPrevista}">%</div>
									</div>
								</div>
								<div>
									<span style="cursor: pointer;" onclick="ControlTotensController.abrirModal('${barco.nome}');"  data-toggle="modal" data-target="#myModal" class="ativo">Monitor</span>
									<span style="cursor: pointer;" onclick="ControlTotensController.abrirModalAddHoraAvulsa('${barco.id}','${barco.nome}');" data-toggle="modal" data-target="#modalAddHoraAvulsa" class="ativo">Add Hora avulsa</span>
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
				<td>${data.usuario.nome}</td>
				<td>${data.fase}</td>
				<td>${data.local.nome}</td>
				<td>${data.atividade.nome}</td>
				<td>${data.dtInicioAtividade}</td>
				<td>${data.dtFimAtividade}</td>
				<td>${data.tempoTrabalhoFimIni}</td>
				<td>${data.statusMonitoracao.nome}</td>
			</tr>
		`;
	},
	
	itemLinhaAvulsa(data){
		return `
			<tr>
				<td class="oculta">${data.id}</td>
				<td>${data.usuario.nome}</td>
				<td>${data.fase.nome}</td>
				<td>${data.local.nome}</td>
				<td>${data.atividade.nome}</td>
				<td>${data.dtInicioAtividade}</td>
				<td>${data.dtFimAtividade}</td>
				<td>
					<span onclick="ControlTotensController.editarHrAvulsa(${data.id});" class="glyphicon glyphicon-pencil"></span>
					<span onclick="ControlTotensController.deletarHrAvulsa(${data.id});" class="glyphicon glyphicon-trash"></span>
				</td>
			</tr>
		`;
	},
	
	modalAddHoraAvulsa(idBarco,nomeBarco){
		return`
		<div class="modal-dialog modal-lg">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h4 class="modal-title">Add hora avulsa na embar\u00e7\u00e3o ${nomeBarco}</h4>
	            </div>
	            <div class="modal-body">
		            
	                <form id="formId">
	                	<div id="alertMsgId" class="alert fade in oculta">
		                	<a onclick="$('#alertMsgId').addClass('oculta');$('#formId').find('input').removeClass('errorInput');" class="close">&times;</a>
		                	<div></div>
						</div>
						<input type="hidden" name="idBarco" id="barcoId" value=${idBarco}>
						<input type="hidden" name="idMonitoracao" id="monitoracaoId" value=>
			            <div class="panel panel-default">
		                	<div class="panel-heading">Add</div>
		                	<div class="panel-body">
			                	<div class="row">
			                		<div class="col-md-4" style="display: table;">
										<div>Usuario: </div>
										<select id="selectUsuarioId" name="idUsuario" class="selectpicker" data-live-search="true" title="Escolha uma opcao">
										</select>
									</div>
			                		<div class="col-md-4" style="display: table;">
										<div>Fase: </div>
										<select id="selectFaseId" name="idFase" class="selectpicker" disabled data-live-search="true" title="Escolha uma opcao">
										</select>
									</div>
								</div>
								<div class="row mt15">
			                		<div class="col-md-4" style="display: table;">
										<div>Local: </div>
										<select id="selectLocalId" name="idLocal" class="selectpicker"  disabled  data-live-search="true" title="Escolha uma opcao">
										</select>
									</div>
									<div class="col-md-4" style="display: table;">
										<div>Atividade: </div>
										<select id="selectAtividadeId" name="idAtividade" class="selectpicker" disabled data-live-search="true" title="Escolha uma opcao">
										</select>
									</div>
								</div>
								<div class="row mt15">
									<div class="col-md-6">
										<div>Data de Inicio: </div>
										<input type="datetime-local" placeholder="dd-mm-yyyy" name="dtInicioAtividade" class="form-control" id="dtInicioAtividadeId">
									</div>
									<div class="col-md-6">
										<div>Data Fim: </div>
										<input type="datetime-local" placeholder="dd-mm-yyyy" name="dtFimAtividade" class="form-control" id="dtFimAtividadeId">
									</div>
								</div>
							</div>
						</div>
			        </form>
            		<div class="panel-heading">Horas Avulsas</div>
            		<div class="panel-body">
			            <div class="row">
							<table id="tableHoraAvulsa" class="display">
								<thead>
							    <tr>
							    	<th class="oculta">Id</th>
							        <th>Nome</th>
							        <th>Fase</th>
							        <th>Local</th>
							        <th>Especialidade</th>
							        <th>Barco</th>
							        <th>Status</th>
							        <th>A&ccedil;&atilde;o</th>
							    </tr>
								</thead>
								<tbody></tbody>
							</table>
			    		</div>
		    		</div>
		    		
		            <div class="modal-footer">
		            	<button type="button" class="btn btn-success" onClick="ControlTotensController.salvarMonitoracaoAvulsa()" >Salvar</button>
		                <button type="button" class="btn btn-danger" onClick="$('#myModal').hide();" data-dismiss="modal">Fechar</button>
		            </div>
	            </div>
	        </div>
	    </div>
		`;
	},
		
	modalToten(barco){
		return`
			<div class="modal-dialog modal-lg w-100">
		        <div class="modal-content">
		            <div class="modal-header">
		                <h4 class="modal-title">Controle do Totem</h4>
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
								        <th>Usuario</th>
								        <th>Fase</th>
								        <th>Local</th>
								        <th>Atividade</th>
								        <th>Dt Inicio</th>
								        <th>Dt Fim</th>
								        <th>Tempo decorrido</th>
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
		                <button type="button" class="btn btn-danger" onClick="$('#myModal').hide();" data-dismiss="modal">Fechar</button>
		            </div>
		        </div>
		    </div>
			`;
	}
};

