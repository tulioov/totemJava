
const MonitorUserTemplate = {
		
		
	modalEscolhaBarco(){
		return`
			<div class="modal-dialog modal-lg">
		        <div class="modal-content">
		            <div class="modal-header">
		                <h4 class="modal-title">Escolha sua Embarca\u00e7\u00e3o</h4>
		            </div>
		            <div class="modal-body">
		            
		            	<h2>Bem vindo NOME.</h2>
		            
						<div class="panel-group" >
						    <div class="panel panel-default">
						        <div class="panel-heading">
						            <h4 class="panel-title">
										Embarca\u00e7\u00e3o					            
									</h4>
						        </div>
				            	<div class="card card-body">
					            	<div id="imgEscolhaBarco" class="row">
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
		
		
	abrirEscolhaFase(){
		return`
				<div class="modal-dialog modal-lg">
			        <div class="modal-content">
			            <div class="modal-header">
			                <h4 class="modal-title">Escolha sua atividade</h4>
			            </div>
			            <div class="modal-body">
			            <h2>Bem vindo NOME, Estamos ta fase 0</h2>
			            
			            <div class="col-md-12">
				            <div class="panel with-nav-tabs panel-primary">
				                <div class="panel-heading">
			                        <ul class="nav nav-tabs">
			                            <li class="active"><a href="#tab0primary" data-toggle="tab">Fase 0</a></li>
			                            <li><a href="#tab1primary" data-toggle="tab">Fase 1</a></li>
			                            <li><a href="#tab2primary" data-toggle="tab">Fase 2</a></li>
			                            <li><a href="#tab3primary" data-toggle="tab">Fase 3</a></li>
			                            <li><a href="#tab4primary" data-toggle="tab">Fase 4</a></li>
			                        </ul>
				                </div>
				                <div class="panel-body">
				                	<div class="panel-group" id="accordion">
					                    <div class="tab-content">
					                        <div class="tab-pane fade in active" id="tab0primary">
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
					                        <div class="tab-pane fade" id="tab1primary">
					                        	

												<div class="panel panel-default">
											        <div class="panel-heading">
											            <h4 class="panel-title">
											                <button type="button" class="btn btn-primary btn-lg btn-block" data-toggle="collapse" data-parent="#accordion" href="#collapse3">Fibra</button>
											            </h4>
													</div>
											        <div id="collapse3" class="panel-collapse collapse">
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
											                <button type="button" class="btn btn-primary btn-lg btn-block" data-toggle="collapse" data-parent="#accordion" href="#collapse4">Eletrica</button>
											            </h4>
											        </div>
											        <div id="collapse4" class="panel-collapse collapse">
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
					                        <div class="tab-pane fade" id="tab2primary">
					                        	<div class="panel panel-default">
											        <div class="panel-heading">
											            <h4 class="panel-title">
											                <button type="button" class="btn btn-primary btn-lg btn-block" data-toggle="collapse" data-parent="#accordion" href="#collapse5">Fibra</button>
											            </h4>
													</div>
											        <div id="collapse5" class="panel-collapse collapse">
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
											                <button type="button" class="btn btn-primary btn-lg btn-block" data-toggle="collapse" data-parent="#accordion" href="#collapse6">Eletrica</button>
											            </h4>
											        </div>
											        <div id="collapse6" class="panel-collapse collapse">
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
					                        <div class="tab-pane fade" id="tab3primary">
					                        	<div class="panel panel-default">
											        <div class="panel-heading">
											            <h4 class="panel-title">
											                <button type="button" class="btn btn-primary btn-lg btn-block" data-toggle="collapse" data-parent="#accordion" href="#collapse7">Fibra</button>
											            </h4>
													</div>
											        <div id="collapse7" class="panel-collapse collapse">
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
											                <button type="button" class="btn btn-primary btn-lg btn-block" data-toggle="collapse" data-parent="#accordion" href="#collapse8">Eletrica</button>
											            </h4>
											        </div>
											        <div id="collapse8" class="panel-collapse collapse">
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
					                        <div class="tab-pane fade" id="tab4primary">
					                        	<div class="panel panel-default">
											        <div class="panel-heading">
											            <h4 class="panel-title">
											                <button type="button" class="btn btn-primary btn-lg btn-block" data-toggle="collapse" data-parent="#accordion" href="#collapse9">Fibra</button>
											            </h4>
													</div>
											        <div id="collapse9" class="panel-collapse collapse">
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
											                <button type="button" class="btn btn-primary btn-lg btn-block" data-toggle="collapse" data-parent="#accordion" href="#collapse10">Eletrica</button>
											            </h4>
											        </div>
											        <div id="collapse10" class="panel-collapse collapse">
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
			            	
			            	
			            	<section>
						        <div>
						            <div class="row d-flex justify-content-center">
						                <div class="col-md-12">
						                    <div class="wizard">
						                        <div class="wizard-inner">
						                            <div class="connecting-line"></div>
						                            <ul class="nav nav-tabs" role="tablist">
						                                <li role="presentation" class="active bg-white">
						                                    <a href="#step0" data-toggle="tab" aria-controls="step0" role="tab" aria-expanded="true"><span class="round-tab">0 </span> <i>Fase 0</i></a>
						                                </li>
						                                <li role="presentation" class="disabled bg-white">
						                                    <a href="#step1" data-toggle="tab" aria-controls="step1" role="tab" aria-expanded="false"><span class="round-tab">1</span> <i>Fase 1</i></a>
						                                </li>
						                                <li role="presentation" class="disabled bg-white">
						                                    <a href="#step2" data-toggle="tab" aria-controls="step2" role="tab"><span class="round-tab">2</span> <i>Fase 2</i></a>
						                                </li>
						                                <li role="presentation" class="disabled bg-white">
						                                    <a href="#step3" data-toggle="tab" aria-controls="step3" role="tab"><span class="round-tab">3</span> <i>Fase 3</i></a>
						                                </li>
						                                <li role="presentation" class="disabled bg-white">
						                                    <a href="#step4" data-toggle="tab" aria-controls="step4" role="tab"><span class="round-tab">4</span> <i>Fase 4</i></a>
						                                </li>
						                            </ul>
						                        </div>
						        
						                        <form role="form"  class="login-box">
						                            <div class="tab-content" id="main_form">
						                            
						                                <div class="tab-pane active" role="tabpanel" id="step0">
						                                    <h4 class="text-center">Step 0</h4>
						                                    <div class="row">
						                                        <div class="col-md-6">
						                                            <div class="form-group">
						                                                <label>First and Last Name *</label> 
						                                                <input class="form-control" type="text" name="name" placeholder=""> 
						                                            </div>
						                                        </div>
						                                    </div>
						                                    <ul class="list-inline pull-right">
						                                        <li><button type="button" class="default-btn next-step">Continue to next step</button></li>
						                                    </ul>
						                                </div>
						                                <div class="tab-pane" role="tabpanel" id="step1">
						                                    <h4 class="text-center">Step 1</h4>
						                                    
						                                    <div class="col-md-6">
						                                        <div class="form-group">
						                                            <label>Address 1 *</label> 
						                                            <input class="form-control" type="text" name="name" placeholder=""> 
						                                        </div>
						                                    </div>
						                                    <ul class="list-inline pull-right">
						                                        <li><button type="button" class="default-btn prev-step">Back</button></li>
						                                        <li><button type="button" class="default-btn next-step skip-btn">Skip</button></li>
						                                        <li><button type="button" class="default-btn next-step">Continue</button></li>
						                                    </ul>
						                                </div>
						                                <div class="tab-pane" role="tabpanel" id="step2">
						                                    <h4 class="text-center">Step 2</h4>
						                                    
						                                    <div class="col-md-6">
						                                        <div class="form-group">
						                                            <label>Account Name *</label> 
						                                            <input class="form-control" type="text" name="name" placeholder=""> 
						                                        </div>
						                                    </div>
						                                    <ul class="list-inline pull-right">
						                                        <li><button type="button" class="default-btn prev-step">Back</button></li>
						                                        <li><button type="button" class="default-btn next-step skip-btn">Skip</button></li>
						                                        <li><button type="button" class="default-btn next-step">Continue</button></li>
						                                    </ul>
						                                </div>
						                                <div class="tab-pane" role="tabpanel" id="step3">
						                                    <h4 class="text-center">Step 3</h4>
						                                    
						                                    <div class="col-md-6">
						                                        <div class="form-group">
						                                            <label>Account Name *</label> 
						                                            <input class="form-control" type="text" name="name" placeholder=""> 
						                                        </div>
						                                    </div>
						                                    <ul class="list-inline pull-right">
						                                        <li><button type="button" class="default-btn prev-step">Back</button></li>
						                                        <li><button type="button" class="default-btn next-step skip-btn">Skip</button></li>
						                                        <li><button type="button" class="default-btn next-step">Continue</button></li>
						                                    </ul>
						                                </div>
						                                <div class="tab-pane" role="tabpanel" id="step4">
						                                	<div class="row">
		                                                        <div class="col-md-12">
						                                    		<h4 class="text-center">Step 4</h4>
						                                    	</div>
						                                    </div>	
						                                    <div class="all-info-container">
						                                        <div class="list-content">
						                                            <a href="#listone" data-toggle="collapse" aria-expanded="false" aria-controls="listone">Collapse 1 <i class="fa fa-chevron-down"></i></a>
						                                            <div class="collapse" id="listone">
						                                                <div class="list-box">
						                                                    <div class="row">
						                                                        <div class="col-md-6">
						                                                            <div class="form-group">
						                                                                <label>First and Last Name *</label> 
						                                                                <input class="form-control" type="text"  name="name" placeholder="" disabled="disabled"> 
						                                                            </div>
						                                                        </div>
						                                                        <div class="col-md-6">
						                                                            <div class="form-group">
						                                                                <label>Phone Number *</label> 
						                                                                <input class="form-control" type="text" name="name" placeholder="" disabled="disabled"> 
						                                                            </div>
						                                                        </div>
						                                                    </div>
						                                                </div>
						                                            </div>
						                                        </div>
						                                        <div class="list-content">
						                                            <a href="#listtwo" data-toggle="collapse" aria-expanded="false" aria-controls="listtwo">Collapse 2 <i class="fa fa-chevron-down"></i></a>
						                                            <div class="collapse" id="listtwo">
						                                                <div class="list-box">
						                                                    <div class="row">
						                                                        <div class="col-md-6">
						                                                            <div class="form-group">
						                                                                <label>Address 1 *</label> 
						                                                                <input class="form-control" type="text" name="name" placeholder="" disabled="disabled"> 
						                                                            </div>
						                                                        </div>
						                                                    </div>
						                                                </div>
						                                            </div>
						                                        </div>
						                                        <div class="list-content">
						                                            <a href="#listthree" data-toggle="collapse" aria-expanded="false" aria-controls="listthree">Collapse 3 <i class="fa fa-chevron-down"></i></a>
						                                            <div class="collapse" id="listthree">
						                                                <div class="list-box">
						                                                    <div class="row">
						                                                        <div class="col-md-6">
						                                                            <div class="form-group">
						                                                                <label>Number *</label> 
						                                                                <input class="form-control" type="text" name="name" placeholder=""> 
						                                                            </div>
						                                                        </div>
						                                                    </div>
						                                                </div>
						                                            </div>
						                                        </div>
						                                    </div>
						                                    
						                                    <ul class="list-inline pull-right">
						                                        <li><button type="button" class="default-btn prev-step">Back</button></li>
						                                        <li><button type="button" class="default-btn next-step">Finish</button></li>
						                                    </ul>
						                                </div>
						                                <div class="clearfix"></div>
						                            </div>
						                            
						                        </form>
						                    </div>
						                </div>
						            </div>
						        </div>
						    </section>
			            	
			            </div>
			            <div class="modal-footer">
			                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
			            </div>
			        </div>
			    </div>
			    <script src="../js/monitorUser/steps.js"></script>
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

