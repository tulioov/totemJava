
const CadastroBarcoTemplate = {
		
	add (){
		return `
		<div class="modal-dialog modal-lg ">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h4 class="modal-title">Adicionar Barco</h4>
	            </div>
	            <div class="modal-body">
		            
	                <form id="formId">
		                <div id="alertMsgId" class="alert fade in oculta">
		                	<a onclick="$('#alertMsgId').addClass('oculta');$('#formId').find('input').removeClass('errorInput');" class="close">&times;</a>
		                	<div></div>
						</div>
	                	<input type="text" id="campoId" name="id" class="oculta">
		                <div class="panel panel-default">
		                	<div class="panel-heading">Cadastro de Barco</div>
		                	<div class="panel-body">
								<div class="col-8 ">
									<img style='display:block; width:100px;height:100px;' 
										id='base64image'
									/>
								    <div>Imagem: </div>
									<div class="form-group">
										<div class="input-group input-file" name="Fichier1">
								    		<input  type="text" class="form-control" placeholder='Escolha uma imagem...' />			
								            <span class="input-group-btn">
								        		<button class="btn btn-default btn-choose" type="button">Escolha</button>
								    		</span>
										</div>
									</div>
								</div>
			                	<div class="row">
									<div class="col-md-6">
										<div>Nome: </div>
										<input type="text" name="nome" class="form-control" id="nomeId">
									</div>
									<div class="col-md-6">
										<div>Descri\u00e7\u00e3o: </div>
										<input type="text" name="descricao" class="form-control" id="descricaoId">
									</div>
								</div>
							</div>
						</div>
			        </form>
	            </div>
	            <div class="modal-footer">
	            	<button type="button" class="btn btn-success" onClick="CadastroBarcoController.salvar()" >Salvar</button>
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
				<td>${data.descricao}</td>
				<td>
					<span onclick="CadastroBarcoController.editar(${data.id});" class="glyphicon glyphicon-pencil"></span>
					<span onclick="CadastroBarcoController.deletar(${data.id});" class="glyphicon glyphicon-trash"></span>
				</td>
			</tr>
		`;
		
	}
	
};