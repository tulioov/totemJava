
const CadastroUsuarioTemplate = {
		
	addUser (){
		return `
		<div class="modal-dialog modal-lg ">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h4 class="modal-title">Adicionar Usu\u00e1rio</h4>
	            </div>
	            <div class="modal-body">
		            
	                <form id="formId">
		                <div id="alertMsgId" class="alert fade in oculta">
		                	<a onclick="$('#alertMsgId').addClass('oculta');$('#formId').find('input').removeClass('errorInput');" class="close">&times;</a>
		                	<div></div>
						</div>
	                	<input type="text" id="campoId" name="id" class="oculta">
		                <div class="panel panel-default">
		                	<div class="panel-heading">Cadastro de Usu\u00e1rio</div>
		                	<div class="panel-body">
			                	<div class="row">
									<div class="col-md-6">
										<div>Nome: </div>
										<input type="text" name="nome" class="form-control" id="nomeId">
									</div>
									<div class="col-md-4">
										<div>Especialidade: </div>
										<input type="text" name="especialidade" class="form-control" id="especialidadeId">
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div>Email: </div>
										<input type="text" disabled name="email" class="form-control" id="emailId">
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div>RFID: </div>
										<input type="text" name="codRfid" class="form-control" id="codRfidId">
									</div>
									<div class="col-md-6">
										<div class="material-switch mt15">
											<div>Admin: </div>
											Nao
		                            		<input id="isAdminId" name="isAdmin" type="checkbox"/>
		                            		<label for="isAdminId" class="label-success mt15"></label>
		                            		Sim
		                        		</div>
	                        		</div>
                        		</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">Rela\u00e7\u00e3o Usu\u00e1rio Etapa</div>
	                			<div class="panel-body">
								<select multiple="multiple" size="10" id="duallistboxId" name="duallistbox">
								</select>
							</div>
						</div>
			        </form>
	            </div>
	            <div class="modal-footer">
	            	<button type="button" class="btn btn-success" onClick="CadastroUsuarioController.salvar()" >Salvar</button>
	                <button type="button" class="btn btn-danger" onClick="$('#myModal').hide();" data-dismiss="modal">Fechar</button>
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
					<span onclick="CadastroUsuarioController.editar(${data.id});" class="glyphicon glyphicon-pencil"></span>
					<span onclick="CadastroUsuarioController.deletar(${data.id});" class="glyphicon glyphicon-trash"></span>
				</td>
			</tr>
		`;
		
	}
	
};