
const CadastroEtapaTemplate = {
		
	add (){
		return `
		<div class="modal-dialog modal-lg ">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h4 class="modal-title">Adicionar Etapa</h4>
	            </div>
	            <div class="modal-body">
		            
	                <form id="formId">
		                <div id="alertMsgId" class="alert fade in oculta">
		                	<a onclick="$('#alertMsgId').addClass('oculta');$('#formId').find('input').removeClass('errorInput');" class="close">&times;</a>
		                	<div></div>
						</div>
	                	<input type="text" id="campoId" name="id" class="oculta">
		                <div class="panel panel-default">
		                	<div class="panel-heading">Cadastro de Etapa</div>
		                	<div class="panel-body">
			                	<div class="row">
									<div class="col-md-4">
										<div>Descri\u00e7\u00e3o: </div>
										<input type="text" name="descricao" class="form-control" id="descricaoId">
									</div>
									<div class="col-md-4">
										<div>Constante Campo: </div>
										<input type="text" name="constanteCampo" class="form-control" id="constanteCampoId">
									</div>
								</div>
							</div>
						</div>
			        </form>
	            </div>
	            <div class="modal-footer">
	            	<button type="button" class="btn btn-success" onClick="CadastroEtapaController.salvar()" >Salvar</button>
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
				<td>${data.descricao}</td>
				<td>${data.constanteCampo}</td>
				<td>
					<span onclick="CadastroEtapaController.editar(${data.id});" class="glyphicon glyphicon-pencil"></span>
					<span onclick="CadastroEtapaController.deletar(${data.id});" class="glyphicon glyphicon-trash"></span>
				</td>
			</tr>
		`;
		
	}
	
};