package com.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.core136.common.utils.SysTools;


public class Sss {
	public static void main(String[] args) {
		 String str = "2019-03-13 13:54:00";
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Date date = null;
			try {
				date = simpleDateFormat.parse(str);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        long ts = date.getTime();
	        System.out.println(ts);
	        System.out.println(System.currentTimeMillis());
	}
}
