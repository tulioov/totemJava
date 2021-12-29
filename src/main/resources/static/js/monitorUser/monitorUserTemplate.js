
const MonitorUserTemplate = {
		
		
	modal(){
		return`
				<div class="modal-dialog modal-lg">
			        <div class="modal-content">
			            <div class="modal-header">
			                <h4 class="modal-title">Escolha sua atividade</h4>
			            </div>
			            <div class="modal-body">
			            <h2>Bem vindo NOME, Estamos ta etapa 0</h2>
			            
			            <div class="col-md-12">
				            <div class="panel with-nav-tabs panel-primary">
				                <div class="panel-heading">
			                        <ul class="nav nav-tabs">
			                            <li class="active"><a href="#tab0primary" data-toggle="tab">Etapa 0</a></li>
			                            <li><a href="#tab1primary" data-toggle="tab">Etapa 1</a></li>
			                            <li><a href="#tab2primary" data-toggle="tab">Etapa 2</a></li>
			                            <li><a href="#tab3primary" data-toggle="tab">Etapa 3</a></li>
			                            <li><a href="#tab4primary" data-toggle="tab">Etapa 4</a></li>
			                        </ul>
				                </div>
				                <div class="panel-body">
				                    <div class="tab-content">
				                        <div class="tab-pane fade in active" id="tab0primary">
											<div class="panel-group" id="accordion">
										    	<div class="panel panel-default">
											        <div class="panel-heading">
											            <h4 class="panel-title">
											                <button type="button" class="btn btn-primary btn-lg btn-block" data-toggle="collapse" data-parent="#accordion" href="#collapse1">Fibra</button>
											            </h4>
													</div>
											        <div id="collapse1" class="panel-collapse collapse">
										            	<div class="card card-body">
													    	<div class="panel-body">
																<button type="button" class="btn btn-success btn-lg btn-block">Sub atividade fibra 1</button>
																<button type="button" class="btn btn-success btn-lg btn-block">Sub atividade fibra 2</button>
																<button type="button" class="btn btn-success btn-lg btn-block">Sub atividade fibra 3</button>
															</div>
													    </div>
											        </div>
												</div>
												<div class="panel panel-default">
											        <div class="panel-heading">
											            <h4 class="panel-title">
											                <button type="button" class="btn btn-primary btn-lg btn-block" data-toggle="collapse" data-parent="#accordion" href="#collapse2">Eletrica</button>
											            </h4>
											        </div>
											        <div id="collapse2" class="panel-collapse collapse">
										            	<div class="card card-body">
													    	<div class="panel-body">
																<button type="button" class="btn btn-success btn-lg btn-block">Sub atividade eletrica 1</button>
																<button type="button" class="btn btn-success btn-lg btn-block">Sub atividade eletrica 2</button>
																<button type="button" class="btn btn-success btn-lg btn-block">Sub atividade eletrica 3</button>
															</div>
													    </div>
											        </div>
											    </div>
											</div>
										</div>
				                        <div class="tab-pane fade" id="tab1primary">Primary 2</div>
				                        <div class="tab-pane fade" id="tab2primary">Primary 3</div>
				                        <div class="tab-pane fade" id="tab3primary">Primary 4</div>
				                        <div class="tab-pane fade" id="tab4primary">Primary 5</div>
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
	},
	
	modalContinuar(){
		return`
				<div class="modal-dialog modal-lg">
			        <div class="modal-content">
			            <div class="modal-header">
			                <h4 class="modal-title">Escolha sua atividade</h4>
			            </div>
			            <div class="modal-body">
			            
			            	<h2>Bem vindo NOME.</h2>
			            
							<div class="panel-group" id="accordion">
							    <div class="panel panel-default">
							        <div class="panel-heading">
							            <h4 class="panel-title">
							                <button type="button" class="btn btn-danger btn-lg btn-block" data-toggle="collapse" data-parent="#accordion" href="#collapse1">Continuar?</button>
							            </h4>
							        </div>
							        <div id="collapse1" class="panel-collapse collapse">
						            	<div class="card card-body">
									    	<div class="panel-body">
												<button type="button" class="btn btn-success btn-lg btn-block">Sim</button>
												<button type="button" class="btn btn-warning btn-lg btn-block">Conclusao Parcial</button>
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
	},
	
	modalSaida(){
		return`
				<div class="modal-dialog modal-lg">
			        <div class="modal-content">
			            <div class="modal-header">
			                <h4 class="modal-title">Escolha sua atividade</h4>
			            </div>
			            <div class="modal-body">
			            
			            	<h2>Bem vindo NOME.</h2>
			            
							<div class="panel-group" id="accordion">
							    <div class="panel panel-default">
							        <div class="panel-heading">
							            <h4 class="panel-title">
							                <button type="button" class="btn btn-danger btn-lg btn-block" data-toggle="collapse" data-parent="#accordion" href="#collapse1">Saida</button>
							            </h4>
							        </div>
							        <div id="collapse1" class="panel-collapse collapse">
						            	<div class="card card-body">
									    	<div class="panel-body">
												<button type="button" class="btn btn-primary btn-lg btn-block">Pausa</button>
												<button type="button" class="btn btn-warning btn-lg btn-block">Conclusao Parcial</button>
												<button type="button" class="btn btn-success btn-lg btn-block">Conclusao</button>
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

