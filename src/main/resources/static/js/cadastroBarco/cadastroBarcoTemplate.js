
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
									<div class="col-md-4">
										<div>Nome: </div>
										<input type="text" name="nome" class="form-control uppercase"  id="nomeId">
									</div>
									<div class="col-md-4">
										<div>Constante Campo: </div>
										<input type="text" name="constanteCampo" oninput="this.value = this.value.replace(' ', '');" class="form-control uppercase" id="constanteCampoId">
									</div>
								</div>
								
								<div class="row mt15">
									<div class="col-md-6">
										<div>Horas Total de Prevista: </div>
										<input type="numer" name="hrsBarcoPrevista" class="form-control" id="hrsBarcoPrevistaId">
									</div>
									<div class="col-md-6">
										<div>Status: </div>
										<select class="form-control" id="sel1">
											<option>Ativo</option>
										    <option>Em Andamento</option>
										    <option>Inativo</option>
										</select>
									</div>
								</div>
								
								
								<div class="row mt15">
									<div class="col-md-6">
										<div>Data Prevista In&iacute;cio: </div>
										<input type="date" name="dtInicioPrevisto" class="form-control" id="dtInicioPrevistoId">
									</div>
									<div class="col-md-6">
										<div>Data Prevista Fim: </div>
										<input type="date" name="dtFimPrevisto" class="form-control" id="dtFimPrevistoId">
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
				<td>${data.constanteCampo}</td>
				<td>${data.hrsBarcoPrevista/60}</td>
				<td><span class="${data.status != null?'inativo':'ativo'}">${data.status != null?'Inativo':'Ativo'}</span></td>
				<td>
					<span onclick="CadastroBarcoController.editar(${data.id});" class="glyphicon glyphicon-pencil"></span>
					<span onclick="CadastroBarcoController.deletar(${data.id});" class="glyphicon glyphicon-trash"></span>
				</td>
			</tr>
		`;
		
	}
	
};