package com.totem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.totem.model.Interruptor;
import com.totem.model.SensorCx;

@Service
public class SensorNivelService {
	
	@Autowired
	BotaoService btnService;
	
	static int contRuido = 0;
	
	public void automatico(int nivel){
		
		if(Interruptor.botaoAuto == 1 && nivel < 20 && this.btnService.statusBombaCasa().getBody().equals("0")){
			this.btnService.ligaDesliga("1");
		}
		
		if (nivel >= 95 && this.btnService.statusBombaCasa().getBody().equals("1")) {
			contRuido++;
			if(contRuido == 2){
				contRuido = 0;
				this.btnService.ligaDesliga("0");
			}
		}
	}

	public ResponseEntity<Object> setNivelCxCasa(int nivel) {
		
		nivel = 48 - nivel;
		nivel = nivel * 100 / 28;
		SensorCx.nivelAguaCasa = nivel;
		
		automatico(nivel);

		return new ResponseEntity("OK", HttpStatus.OK);
	}

	public ResponseEntity<Object> getNivelCxCasa() {
		
		return new ResponseEntity(Integer.valueOf(SensorCx.nivelAguaCasa), HttpStatus.OK);
	}

	public ResponseEntity<Object> setNivelCxCisterna(int nivel) {
		SensorCx.nivelAguaCisterna = nivel;

		return new ResponseEntity("OK", HttpStatus.OK);
	}

	public ResponseEntity<Object> getNivelCxCisterna() {
		return new ResponseEntity(Integer.valueOf(SensorCx.nivelAguaCisterna), HttpStatus.OK);
	}
}
