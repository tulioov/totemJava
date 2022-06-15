package com.totem.util;

import java.util.Calendar;
import java.util.Date;

public class UtilDate {
	
	public static Date somarDiasData(Date data, int dias) {

		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.add(Calendar.DATE, +dias);
		data = c.getTime();
		
		return data;
	}

}
