package org.shashank.library.util.impl;

import java.util.Calendar;
import java.util.Date;

import org.shashank.library.util.DateUtil;
import org.springframework.stereotype.Service;

@Service
public class DateUtilImpl implements DateUtil{

	public Date addDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}
}
