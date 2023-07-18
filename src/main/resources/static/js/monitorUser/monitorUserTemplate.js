
const MonitorUserTemplate = {
		
	menuPausa(statusMonitoracao){
		
		
		if(statusMonitoracao.constanteCampo == 'TRABALHANDO'){
			return ``;
		}
		
		if(statusMonitoracao.constanteCampo == 'AVULSA'){
			return ``;
		}
		
		return`<button type="button" class="btn-lg btn-warning col-md-12 mt15" onclick="MonitorUserController.continuarPausarFinalizar('${statusMonitoracao.constanteCampo}');">${statusMonitoracao.nome}</button> `;
	},
	
	monitoracao(barco){
		return `
			<h3>Processo de fabrica\u00e7\u00e3o do barco ${barco.nome}</h3>
			<div class="row">
				<div class="col-md-4 ">
					<img style='width:16em;height:9em;' src='${barco.barcoTemplate.imagem}'/>
				</div>
				<div class="col-md-6 mt3em">
					<h4>Data de inicio Previsto= ${barco.dtInicioPrevisto}</h4> 
					<h4>Data de Termino Previsto = ${barco.dtFimPrevisto}</h4> 
				</div>
			</div>
			<div class="row mt15">
				<h4 class="col-md-6">${barco.dtInicio==undefined?'Aguardando Inicio':barco.dtInicio}</h4> 
				<h4 class="col-md-6 text-right">${barco.dtFim==undefined?'Aguardando Fim':barco.dtFim}</h4> 
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
		`;
	},
	
	itemLinha(data){
		
		let classStatus = '';
		
		if(data.statusMonitoracao.constanteCampo == 'TRABALHANDO'){
			classStatus = 'TRABALHANDO';
		}
		else if(data.statusMonitoracao.constanteCampo =='FINALIZADO'){
			classStatus = 'FINALIZADO';
		}else{
			classStatus = 'PAUSA';
		}
		
		return `
			<tr>
				<td>${data.id}</td>
				<td>${data.usuario.nome}</td>
				<td>${data.atividade.nome}</td>
				<td>${data.dtInicioAtividade}</td>
				<td>${data.tempoTrabalho}</td>
				<td><span class="${classStatus}">${data.statusMonitoracao.nome}</span></td>
			</tr>
		`;
	},
		
	modalEscolhaBarco(){
		return`
			<div class="modal-dialog modal-lg ">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Escolha sua A\u00e7\u00e3o</h4>
					</div>
					<div class="modal-body">
						<form id="formId">
							<div class="panel panel-default">
								<div class="panel-heading">Cadastro de Usu\u00e1rio</div>
								<div class="panel-body">
									<div class="progress">
										<div id="progressBarEscolhaBarcoId" class="progress-bar bg-success" role="progressbar" style="width: 100%" value=100 aria-valuemin="0" aria-valuemax="100">Tempo de espera</div>
									</div>
									<div class="row mt15">
										<table id="imgEscolhaBarco">
											<thead>
										    <tr>
										    	<th>Id</th>
										        <th>Nome</th>
										        <th class="text-center">Status</th>
										    </tr>
											</thead>
											<tbody></tbody>
										</table>
								    </div>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-danger" onClick="$('#myModal').hide();" data-dismiss="modal">Fechar</button>
					</div>
				</div>
			</div>
		`
	},
	
	rowFases(index, fase){
		return `
			<li role="presentation" class="${index==0?'active':'disabled'} bg-white">
		        <a href="#step${index}" data-toggle="tab" aria-controls="step${index}" role="tab">
		        	<span class="round-tab">
		        		${index}
		        	</span> 
		        	<i>
		        		${fase.nome}
		        	</i>
	        	</a>
		    </li>
			`
	},
	
	contentFases(index, fase){
		return`
			<div class="tab-pane ${index==0?'active':''}" role="tabpanel" id="step${index}">
	            <h4 class="text-center">${fase.nome}</h4>
	            <div id="contentLocalId${fase.id}" class="col-md-12">
	            </div>
	        </div>					
			`
	},
	
	htmlAtividade(idLocal, atividade){
		let html = `<button type="button" class="btn btnAtividade btn-success col-md-12 mt15" onclick="MonitorUserController.salvarAtividadeEscolhida(${atividade.id},${idLocal})">${atividade.nome}</button>`
		return html;
	},
	
	contentLocal(index,local,idUsuario,idBarcoTemplate){
		
		return` 
			<div class="list-content">
		        <a class="btn btn-info" onclick="MonitorUserController.htmlAtividade('cmb${index}${local.id}',${local.id},${idUsuario},${idBarcoTemplate})" href="#cmb${index}${local.id}"  data-toggle="collapse" aria-expanded="false" aria-controls="cmb${index}${local.id}">${local.nome}<i class="fa fa-chevron-down"></i></a>
		        <div class="collapse" id="cmb${index}${local.id}">
		            <div class="list-box">
		                <div class="row">
		                    <div class="col-md-12">
		                        <div class="form-group"></div>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
		`;
	},
	
	abrirEscolhaFase(idBarco){
		return`
				<div class="modal-dialog modal-lg">
			        <div class="modal-content">
			            <div class="modal-header">
			                <h4 class="modal-title">Escolha sua atividade</h4>
			                <div class="progress">
								<div id="progressBarFaseId" class="progress-bar bg-success" role="progressbar" style="width: 100%" value=100 aria-valuemin="0" aria-valuemax="100">Tempo de espera</div>
							</div>
			            </div>
			            <input type="hidden" value="${idBarco}" id="barcoId">
			            <div class="modal-body">
			            	<section>
						        <div>
						            <div class="row d-flex justify-content-center">
						                <div class="col-md-12">
						                    <div class="wizard">
						                        <div class="wizard-inner">
						                            <div class="connecting-line"></div>
						                            <ul class="nav nav-tabs" role="tablist" id="rowFasesId">
						                                
						                            </ul>
						                        </div>
						                        <form role="form"  class="login-box">
						                            <div class="tab-content" id="contentFasesId">
						                            </div>
						                        </form>
						                    </div>
						                </div>
						            </div>
						        </div>
						    </section>
			            	
			            </div>
			            <div class="modal-footer">
			                <button id="modalCloseId" type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
			            </div>
			        </div>
			    </div>
			    <script src="../js/monitorUser/steps.js"></script>
			`
	},
	
	itemLinhaUsuario(data){
		if(data.status == null){
			data.status = "ativo";
		}
		if(data.status == 'FINALIZADO'){
			data.status = "ativo";
		}
		
		let especialidadeNome = "Em preenchimento";
		
		$(data.especialidadeList).each(function(index, especialidade) {
			especialidadeNome = "";
			if(especialidade != undefined){
				especialidadeNome += especialidade.nome+" ";
			}
			
		});
		
		return `
			<tr>
				<td>${data.id}</td>
				<td>${data.nome}</td>
				<td>${especialidadeNome}</td>
				<td>${data.nomeBarco}</td>
				<td><span class="${data.status}">${data.status}</span></td>
				<td>
					<span onclick="MonitorUserController.abrirEscolhaBarco(${data.id},${data.codRfid});" class="glyphicon glyphicon-briefcase"></span>
				</td>
			</tr>
		`;
	},
	
	modalFuncionario(){
		return `
		<div class="modal-dialog modal-lg ">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h4 class="modal-title">Adicionar Usu\u00e1rio</h4>
	            </div>
	            <div class="modal-body">
	                <form id="formId">
		                <div class="panel panel-default">
		                	<div class="panel-heading">Cadastro de Usu\u00e1rio</div>
		                	<div class="panel-body">
			                	<div class="row">
									<table id="tableUsuario" class="display">
										<thead>
									    <tr>
									        <th>ID</th>
									        <th>Nome</th>
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
						</div>
			        </form>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-danger" onClick="$('#myModal').hide();" data-dismiss="modal">Fechar</button>
	            </div>
	        </div>
	    </div>
		`;
	},
	
};

