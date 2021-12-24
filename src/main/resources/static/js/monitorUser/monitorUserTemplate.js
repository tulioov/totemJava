
const MonitorUserTemplate = {
		
		
	modal(){
		return`
				<div class="modal-dialog modal-lg">
			        <div class="modal-content">
			            <div class="modal-header">
			                <h4 class="modal-title">Escolha sua atividade</h4>
			            </div>
			            <div class="modal-body">
			            
			            	<h2>Bem vindo NOME, Estamos ta etapa 2</h2>
			            
							<div class="panel-group" id="accordion">
							    <div class="panel panel-default">
							        <div class="panel-heading">
							            <h4 class="panel-title">
							                <button type="button" class="btn btn-primary btn-lg btn-block" data-toggle="collapse" data-parent="#accordion" href="#collapse1">Etapa 1</button>
							            </h4>
							        </div>
							        <div id="collapse1" class="panel-collapse collapse in">
						            	<div class="card card-body">
									    	<div class="panel-body">
												<button type="button" class="btn btn-success btn-lg btn-block">Fibra</button>
												<button type="button" class="btn btn-success btn-lg btn-block">Eletrica</button>
												<button type="button" class="btn btn-success btn-lg btn-block">Atividade1</button>
											</div>
									    </div>
							        </div>
							    </div>
							    <div class="panel panel-default">
							        <div class="panel-heading">
							            <h4 class="panel-title">
							                <button type="button" class="btn btn-primary btn-lg btn-block" data-toggle="collapse" data-parent="#accordion" href="#collapse2">Etapa 2</button>
							            </h4>
							        </div>
							        <div id="collapse2" class="panel-collapse collapse">
						            	<div class="card card-body">
									    	<div class="panel-body">
												<button type="button" class="btn btn-success btn-lg btn-block">Atividade2</button>
												<button type="button" class="btn btn-success btn-lg btn-block">Atividade3</button>
												<button type="button" class="btn btn-success btn-lg btn-block">Atividade4</button>
											</div>
									    </div>
							        </div>
							    </div>
							    <div class="panel panel-default">
							        <div class="panel-heading">
							            <h4 class="panel-title">
							                <button type="button"class="btn btn-primary btn-lg btn-block" data-toggle="collapse" data-parent="#accordion" href="#collapse3">Etapa 3</button>
							            </h4>
							        </div>
							        <div id="collapse3" class="panel-collapse collapse">
						            	<div class="card card-body">
									    	<div class="panel-body">
												<button type="button" class="btn btn-success btn-lg btn-block">Atividade5</button>
												<button type="button" class="btn btn-success btn-lg btn-block">Atividade6</button>
												<button type="button" class="btn btn-success btn-lg btn-block">Atividade7</button>
											</div>
									    </div>
							        </div>
							    </div>
							</div>
			            </div>
			            <div class="modal-footer">
			                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
			            </div>
			        </div>
			    </div>
				`
	}
	
};

