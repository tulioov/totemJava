
const CadastroAtividadeTemplate = {
		
	add (){
		return `
		<div class="modal-dialog modal-lg ">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h4 class="modal-title">Adicionar Atividade</h4>
	            </div>
	            <div class="modal-body">
		            
	                <form id="formId">
		                <div id="alertMsgId" class="alert fade in oculta">
		                	<a onclick="$('#alertMsgId').addClass('oculta');$('#formId').find('input').removeClass('errorInput');" class="close">&times;</a>
		                	<div></div>
						</div>
	                	<input type="text" id="campoId" name="id" class="oculta">
		                <div class="panel panel-default">
		                	<div class="panel-heading">Cadastro de Atividade</div>
		                	<div class="panel-body">
			                	<div class="row">
									<div class="col-md-4">
										<div>Descri\u00e7\u00e3o: </div>
										<input type="text" name="descricao" class="form-control uppercase" id="descricaoId">
									</div>
									<div class="col-md-6">
										<div>Constante Campo: </div>
										<input type="text" name="constanteCampo" oninput="this.value = this.value.replace(' ', '');" class="form-control uppercase" id="constanteCampoId">
									</div>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">Rela\u00e7\u00e3o Atividade e SubAtividade</div>
                			<div class="panel-body">
								<select multiple="multiple" size="10"  id="duallistboxId">
								</select>
							</div>
						</div>
			        </form>
	            </div>
	            <div class="modal-footer">
	            	<button type="button" class="btn btn-success" onClick="CadastroAtividadeController.salvar()" >Salvar</button>
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
					<span onclick="CadastroAtividadeController.editar(${data.id});" class="glyphicon glyphicon-pencil"></span>
					<span onclick="CadastroAtividadeController.deletar(${data.id});" class="glyphicon glyphicon-trash"></span>
				</td>
			</tr>
		`;
		
	}
	
};