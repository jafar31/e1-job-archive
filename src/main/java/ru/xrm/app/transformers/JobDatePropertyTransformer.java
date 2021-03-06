package ru.xrm.app.transformers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class JobDatePropertyTransformer implements PropertyTransformer {

	private final static List<String> months=new ArrayList<String>(
			Arrays.asList("января",
					"февраля",
					"марта",
					"апреля",
					"мая",
					"июня",
					"июля",
					"августа",
					"сентября",
					"октября",
					"ноября",
					"декабря"));

	public Object transform(String from) {
		// 28 сентября 2012
		Calendar result;
		result=GregorianCalendar.getInstance();

		String[] parts=from.split(" ");
		
		if (parts.length==3 && months.indexOf(parts[1])!=-1){
			int day=Integer.valueOf(parts[0]).intValue();
			int month=months.indexOf(parts[1]);
			int year=Integer.valueOf(parts[2]).intValue();
			if (day<=0 || day>31 || year<1970 || year > 2099){
				result.set(1970, 0, 1, 0, 0, 0);
			}else{
				result.set(year, month, day, 0, 0, 0);
			}
		}else{
			result.set(1970, 0, 1, 0, 0, 0);
		}
		
		return result.getTime();
	}

}
