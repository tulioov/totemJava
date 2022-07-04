package com.totem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.totem.entity.Feriado;
import com.totem.service.FeriadoService;

public class DateTest {

	public static void main(String[] args) {
		try {
			
			FeriadoService feriadoService = new FeriadoService();
			String dataInicial = "7-Oct-2019";
			String dataFim = "25-Oct-2019";
			String toDateTeste = "20-Oct-2019";

			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Date dtInicio = formato.parse("01/06/2022");
			Date dtFim = formato.parse("29/06/2022");

			// System.out.println(isDataDentroRange(new Date(toDateTeste), new
			// Date(dataInicial), new Date(dataFim)));
			// System.out.println(calcularFDSDentroIntervalo(new Date(dataInicial), new
			// Date(dataFim)));
			List<Feriado> lstFeriado = feriadoService.findByDtFeriadoBetween(dtInicio, dtFim);
			System.out.println(lstFeriado.size());

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
