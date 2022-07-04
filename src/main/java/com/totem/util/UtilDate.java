package com.totem.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.totem.entity.Feriado;

@Service
public class UtilDate {

	static HashMap<Integer, String> daysOfWeek = null;

	static {
		daysOfWeek = new HashMap<>();
		daysOfWeek.put(1, "Sun");
		daysOfWeek.put(2, "Mon");
		daysOfWeek.put(3, "Tue");
		daysOfWeek.put(4, "Wed");
		daysOfWeek.put(5, "Thu");
		daysOfWeek.put(6, "Fri");
		daysOfWeek.put(7, "Sat");
	}

	public Date somarDiasData(Date data, int dias) {

		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.add(Calendar.DATE, +dias);
		data = c.getTime();

		return data;
	}

	public Long getHorasData(Date dataInicial, Date dataFim) {
		if (dataInicial == null || dataFim == null) {
			return null;
		}
		long dt = (dataFim.getTime() - dataInicial.getTime());
		return 24 * (dt / 86400000L);
	}

	public Long getDiasData(Date dataInicial, Date dataFim) {
		if (dataInicial == null || dataFim == null) {
			return null;
		}
		long dt = (dataFim.getTime() - dataInicial.getTime());
		return dt / 86400000L;
	}

	public List<Date> calcularFDSDentroIntervalo(Date dataInicial, Date dataFim) {
		List<Date> listOfWeekends = new ArrayList<>();
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		from.setTime(dataInicial);
		to.setTime(dataFim);
		while (from.getTimeInMillis() < to.getTimeInMillis()) {
			if (daysOfWeek.get(from.get(Calendar.DAY_OF_WEEK)).equalsIgnoreCase("Sat")) {
				Date sat = from.getTime();
				listOfWeekends.add(sat);
			} else if (daysOfWeek.get(from.get(Calendar.DAY_OF_WEEK)).equalsIgnoreCase("Sun")) {
				Date sun = from.getTime();
				listOfWeekends.add(sun);
			}
			from.add(Calendar.DAY_OF_MONTH, 1);
		}
		return listOfWeekends;
	}

	public boolean isDataDentroRange(Date dataTeste, Date dataInicial, Date dataFim) {
		return !(dataTeste.before(dataInicial) || dataTeste.after(dataFim));
	}

	public Long getDiasUteisPrevistos(Date dtInicio, Date dtFim, List<Feriado> lstFeriado) {

		return getDiasUteis(dtInicio, dtFim, lstFeriado);
	}

	private long getDiasUteis(Date dtInicio, Date dtFim, List<Feriado> lstFeriado) {

		int diasFeriado = 0;

		for (Feriado feriado : lstFeriado) {
			if (isDataDentroRange(feriado.getDtFeriado(), dtInicio, dtFim)) {
				diasFeriado++;
			}
		}

		Long dias = getDiasData(dtInicio, dtFim);
		List<Date> fds = calcularFDSDentroIntervalo(dtInicio, dtFim);

		return dias - (fds.size() + diasFeriado);
	}

}
