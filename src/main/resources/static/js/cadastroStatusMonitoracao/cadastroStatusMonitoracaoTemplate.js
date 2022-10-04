
const CadastroStatusMonitoracaoTemplate = {
		
	add (){
		return `
		<div class="modal-dialog modal-lg ">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h4 class="modal-title">Adicionar Menu Pausa</h4>
	            </div>
	            <div class="modal-body">
		            
	                <form id="formId">
		                <div id="alertMsgId" class="alert fade in oculta">
		                	<a onclick="$('#alertMsgId').addClass('oculta');$('#formId').find('input').removeClass('errorInput');" class="close">&times;</a>
		                	<div></div>
						</div>
	                	<input type="text" id="campoId" name="id" class="oculta">
		                <div class="panel panel-default">
		                	<div class="panel-heading">Cadastro de Menu Pausa</div>
		                	<div class="panel-body">
			                	<div class="row">
									<div class="col-md-4">
										<div>Nome: </div>
										<input type="text" name="nome" class="form-control" id="nomeId">
									</div>
									<div class="col-md-4">
										<div>Constante Campo: </div>
										<span>PAUSA_</span>
										<input type="text" name="constanteCampo" oninput="this.value = this.value.replace(' ', '');" class="uppercase" id="constanteCampoId">
									</div>
								</div>
							</div>
						</div>
			        </form>
	            </div>
	            <div class="modal-footer">
	            	<button type="button" class="btn btn-success" onClick="CadastroStatusMonitoracaoController.salvar()" >Salvar</button>
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
				<td>${data.constanteCampo}</td>
				<td><span class="${data.usuarioDelete != null?'inativo':'ativo'}">${data.usuarioDelete != null?'Inativo':'Ativo'}</span></td>
				<td>
					<span onclick="CadastroStatusMonitoracaoController.editar(${data.id});" class="glyphicon glyphicon-pencil"></span>
					<span onclick="CadastroStatusMonitoracaoController.deletar(${data.id});" class="glyphicon glyphicon-trash"></span>
				</td>
			</tr>
		`;
		
	}
	
};