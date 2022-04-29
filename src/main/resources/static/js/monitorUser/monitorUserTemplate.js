
const MonitorUserTemplate = {
	
	monitoracao(){
		return `
			<h3>Processo de fabrica\u00e7\u00e3o do barco</h3>
			<div class="progress">
			  	<div class="progress-bar bg-success" role="progressbar" style="width: 25%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">25%</div>
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
				        <th>Tempo</th>
				    </tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		`;
	},
		
	modalEscolhaBarco(){
		return`
			<div>
				<div class="panel-group" >
				    <div class="panel panel-default">
				        <div class="panel-heading">
				            <h4 class="panel-title">
								Escolha sua Embarca\u00e7\u00e3o					            
							</h4>
				        </div>
		            	<div class="card card-body">
			            	<div id="imgEscolhaBarco" class="row">
							</div>
					    </div>
				    </div>
				</div>
		    </div>
		`
	},
	
	rowEtapas(index, etapa){
		return `
			<li role="presentation" class="${index==0?'active':'disabled'} bg-white">
		        <a href="#step${index}" data-toggle="tab" aria-controls="step${index}" role="tab"><span class="round-tab">${index}</span> <i>${etapa.descricao}</i></a>
		    </li>
			`
	},
	
	contentEtapas(index, etapa){
		return`
			<div class="tab-pane ${index==0?'active':''}" role="tabpanel" id="step${index}">
	            <h4 class="text-center">${etapa.descricao}</h4>
	            <div id="contentAtividadeId${etapa.id}" class="col-md-12">
	            </div>
	        </div>					
			`
	},
	
	contentAtividade(index,etapa){
		return `
			<div class="list-content">
		        <a href="#cmb${index}${etapa.id}"  data-toggle="collapse" aria-expanded="false" aria-controls="cmb${index}${etapa.id}">Atividade 1<i class="fa fa-chevron-down"></i></a>
		        <div class="collapse" id="cmb${index}${etapa.id}">
		            <div class="list-box">
		                <div class="row">
		                    <div class="col-md-12">
		                        <div class="form-group">
		                            <button type="button" class="btn btn-success col-md-12" onclick="">SubAtividade 1</button>
		                        </div>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
		`;
	},
	
	
		
	abrirEscolhaEtapa(){
		return`
				<div class="modal-dialog modal-lg">
			        <div class="modal-content">
			            <div class="modal-header">
			                <h4 class="modal-title">Escolha sua atividade</h4>
			                <div class="progress">
								<div id="progressBarEtapaId" class="progress-bar bg-success" role="progressbar" style="width: 100%" value=100 aria-valuemin="0" aria-valuemax="100">Tempo de espera</div>
							</div>
			            </div>
			            <div class="modal-body">
			            	<section>
						        <div>
						            <div class="row d-flex justify-content-center">
						                <div class="col-md-12">
						                    <div class="wizard">
						                        <div class="wizard-inner">
						                            <div class="connecting-line"></div>
						                            <ul class="nav nav-tabs" role="tablist" id="rowEtapasId">
						                                
						                            </ul>
						                        </div>
						                        <form role="form"  class="login-box">
						                            <div class="tab-content" id="contentEtapasId">
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
	
};

