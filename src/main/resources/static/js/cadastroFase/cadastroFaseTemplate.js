
const CadastroFaseTemplate = {
		
	add (){
		return `
		<div class="modal-dialog modal-lg ">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h4 class="modal-title">Adicionar Fase</h4>
	            </div>
	            <div class="modal-body">
		            
	                <form id="formId">
		                <div id="alertMsgId" class="alert fade in oculta">
		                	<a onclick="$('#alertMsgId').addClass('oculta');$('#formId').find('input').removeClass('errorInput');" class="close">&times;</a>
		                	<div></div>
						</div>
	                	<input type="text" id="campoId" name="id" class="oculta">
		                <div class="panel panel-default">
		                	<div class="panel-heading">Cadastro de Fase</div>
		                	<div class="panel-body">
			                	<div class="row">
									<div class="col-md-6">
										<div>Nome: </div>
										<input type="text" name="nome" class="form-control uppercase" id="nomeId">
									</div>
									<div class="col-md-6">
										<div>Constante Campo: </div>
										<input type="text" name="constanteCampo" oninput="this.value = this.value.replace(' ', '');" class="form-control uppercase" id="constanteCampoId">
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div>Barco Template: </div>
										<select id="barcoTemplateSelect" name="barcoTemplateSelect"  class="selectpicker"  data-live-search="true" title="Escolha uma opcao">
										</select>
									</div>
									<div class="col-md-6">
										<div>Descri\u00e7\u00e3o: </div>
										<input disabled type="text" name="descricao" class="form-control uppercase" id="descricaoId">
									</div>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">Rela\u00e7\u00e3o Fase e Local</div>
                			<div class="panel-body">
								<select multiple="multiple" class="dualListClass" size="10"  id="duallistLocalId">
								</select>
							</div>
						</div>
			        </form>
	            </div>
	            <div class="modal-footer">
	            	<button type="button" class="btn btn-success" onClick="CadastroFaseController.salvar()" >Salvar</button>
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
				<td>${data.barcoTemplateList[0]==undefined?'Sem Barco':data.barcoTemplateList[0].nome}</td>
				<td><span class="${data.usuarioDelete != null?'inativo':'ativo'}">${data.usuarioDelete != null?'Inativo':'Ativo'}</span></td>
				<td>
					<span onclick="CadastroFaseController.editar(${data.id});" class="glyphicon glyphicon-pencil"></span>
					<span onclick="CadastroFaseController.deletar(${data.id});" class="glyphicon glyphicon-trash"></span>
				</td>
			</tr>
		`;
		
	}
	
};