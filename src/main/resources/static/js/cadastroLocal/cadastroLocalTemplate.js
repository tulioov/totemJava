
const CadastroLocalTemplate = {
		
	add (){
		return `
		<div class="modal-dialog modal-lg ">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h4 class="modal-title">Adicionar Local</h4>
	            </div>
	            <div class="modal-body">
		            
	                <form id="formId">
		                <div id="alertMsgId" class="alert fade in oculta">
		                	<a onclick="$('#alertMsgId').addClass('oculta');$('#formId').find('input').removeClass('errorInput');" class="close">&times;</a>
		                	<div></div>
						</div>
	                	<input type="text" id="campoId" name="id" class="oculta">
		                <div class="panel panel-default">
		                	<div class="panel-heading">Cadastro de Local</div>
		                	<div class="panel-body">
			                	<div class="row">
			                		<div class="col-md-4">
										<div>C&oacute;digo: </div>
										<input type="text" name="codigo" class="form-control uppercase" id="codigoId">
									</div>
									<div class="col-md-4">
										<div>Nome: </div>
										<input type="text" name="nome" class="form-control uppercase" id="nomeId">
									</div>
									<div class="col-md-4">
										<div>Constante Campo: </div>
										<input type="text" name="constanteCampo" oninput="this.value = this.value.replace(' ', '');" class="form-control uppercase" id="constanteCampoId">
									</div>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">Rela\u00e7\u00e3o Local e Atividade</div>
                			<div class="panel-body">
								<select multiple="multiple" size="10"  id="duallistboxId">
								</select>
							</div>
						</div>
			        </form>
	            </div>
	            <div class="modal-footer">
	            	<button type="button" class="btn btn-success" onClick="CadastroLocalController.salvar()" >Salvar</button>
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
					<span onclick="CadastroLocalController.editar(${data.id});" class="glyphicon glyphicon-pencil"></span>
					<span onclick="CadastroLocalController.deletar(${data.id});" class="glyphicon glyphicon-trash"></span>
				</td>
			</tr>
		`;
		
	}
	
};