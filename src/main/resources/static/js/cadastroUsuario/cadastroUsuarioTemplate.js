
const CadastroUsuarioTemplate = {
		
	addUser (){
		return `
		<div class="modal-dialog modal-lg">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h4 class="modal-title">Adicionar Usu\u00e1rio</h4>
	            </div>
	            <div class="modal-body">
	                <form id="formId">
	                	<input type="text" id="campoId" name="id" class="oculta">
		                <div class="panel panel-default">
		                	<div class="panel-heading">Cadastro de Usu\u00e1rio</div>
		                	<div class="panel-body">
			                	<div class="row">
									<div class="col-md-6">
										<div>Nome: </div>
										<input type="text" name="nome" class="form-control" id="recipient-name">
									</div>
									<div class="col-md-4">
										<div>Especialidade: </div>
										<input type="text" name="especialidade" class="form-control" id="recipient-name">
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="material-switch mt15">
											<div>Admin: </div>
											Sim
		                            		<input id="someSwitchOptionPrimary" name="someSwitchOption001" type="checkbox"/>
		                            		<label for="someSwitchOptionPrimary" class="label-success mt15"></label>
		                            		Nao
		                        		</div>
	                        		</div>
                        		</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">Rela\u00e7\u00e3o Usu\u00e1rio Etapa</div>
	                			<div class="panel-body">
								<select multiple="multiple" size="10" name="duallistbox">
									<option value="option1">Option 1</option>
									<option value="option2">Option 2</option>
									<option value="option3" selected="selected">Option 3</option>
									<option value="option4">Option 4</option>
									<option value="option5">Option 5</option>
									<option value="option6" selected="selected">Option 6</option>
									<option value="option7">Option 7</option>
									<option value="option8">Option 8</option>
									<option value="option9">Option 9</option>
									<option value="option0">Option 10</option>
								</select>
							</div>
						</div>
			        </form>
	            </div>
	            <div class="modal-footer">
	            	<button type="button" class="btn btn-success" onClick="CadastroUsuarioController.salvar()" >Salvar</button>
	                <button type="button" class="btn btn-danger" data-dismiss="modal">Fechar</button>
	            </div>
	        </div>
	    </div>
		`;
	},
	
	itemLinha(data){
		return `
			<tr>
				<td>${data.id}</td>
				<td>${data.nome}</td>
				<td>${data.especialidade}</td>
				<td>
					<span onclick="alert('fazendo editar')" class="glyphicon glyphicon-pencil"></span>
					<span onclick="alert('fazendo deletar')" class="glyphicon glyphicon-trash"></span>
				</td>
			</tr>
		`;
		
	}
	
};