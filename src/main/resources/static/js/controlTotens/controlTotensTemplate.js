
const ControlTotensTemplate = {
		
	modalToten(){
		return`
				<div class="modal-dialog modal-lg">
			        <div class="modal-content">
			            <div class="modal-header">
			                <h4 class="modal-title">Apontamento de horas</h4>
			                <button type="button" class="close" data-dismiss="modal">&times;</button>
			            </div>
			            <div class="modal-body">
			                <div class="">
								<table id="tableMonitorUser">
									<thead>
								    <tr>
								        <th>ID</th>
								        <th>Nome</th>
								        <th>Trabalhando em</th>
								        <th>Hr. Entrada</th>
								        <th>Tempo</th>
								    </tr>
									</thead>
									<tbody>
										<tr>
											<td>01</td>
											<td>Fernand</td>
											<td>Fibra</td>
											<td>08:00</td>
											<td>
												<div class="progress">
												  <div class="progress-bar bg-success" role="progressbar" style="width: 25%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">03:00</div>
												</div>
											</td>
										</tr>
										<tr>
											<td>01</td>
											<td>maria</td>
											<td>Eletrica</td>
											<td>08:00</td>
											<td>
												<div class="progress">
												  <div class="progress-bar bg-warning" role="progressbar" style="width: 75%" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100">5:00</div>
												</div>
											</td>
										</tr>
										<tr>
											<td>01</td>
											<td>Geraldo</td>
											<td>Motor</td>
											<td>08:00</td>
											<td>
												<div class="progress">
												  <div class="progress-bar bg-danger" role="progressbar" style="width: 100%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100">13:00</div>
												</div>
											</td>
										</tr>
										<tr>
											<td>01</td>
											<td>Jose</td>
											<td>Fibra</td>
											<td>08:00</td>
											<td>
												<div class="progress">
												  <div class="progress-bar bg-warning" role="progressbar" style="width: 75%" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100">5:00</div>
												</div>
											</td>
										</tr>
										<tr>
											<td>01</td>
											<td>Fulano</td>
											<td>Fibra</td>
											<td>08:00</td>
											<td>
												<div class="progress">
												  <div class="progress-bar bg-success" role="progressbar" style="width: 25%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">03:00</div>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
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

