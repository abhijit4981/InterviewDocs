package com.cosmos.nse;

import java.time.ZonedDateTime;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestFutureDeals {
	private static final int CENTURY = 2000;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello world");
		ZonedDateTime periodStartDate = ZonedDateTime.parse("2016-02-04T08:20:10+05:30[Asia/Kolkata]");
		String str = getSeasonSuffix(periodStartDate,false);
		String str1 = getSeasonSuffix(periodStartDate,true);
		String str2 = getPowerFutureSeasonSuffix(periodStartDate);
		StringBuilder sb = getPowerFutureSeasonSuffix1(periodStartDate);
		System.out.println(str);
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(sb.toString());
	}

	private static String getSeasonSuffix(ZonedDateTime periodStartDate, boolean whiteSpaceAfterSeasonName) {
		int month = periodStartDate.getMonthValue();
		int yearCentury = periodStartDate.getYear()-CENTURY;
		StringBuilder sb = new StringBuilder();
		if (month == 4) {
			sb.append("SUM").append(whiteSpaceAfterSeasonName ? " " : "").append(yearCentury);
		} else {
			sb.append("WIN").append(whiteSpaceAfterSeasonName ? " " : "").append(yearCentury)
					.append("-").append(yearCentury + 1);
		}
		return sb.toString();
	}
	private static String getPowerFutureSeasonSuffix(ZonedDateTime periodStartDate) {
		int month = periodStartDate.getMonthValue();
		System.out.println(month);
	      ++month;
	      System.out.println(month);
	      StringBuilder sb = new StringBuilder();
	      if (month == 4) {
	        sb.append("APR");
	      }
	      else
	        sb.append("OCT");

	      return sb.toString();
	}
	private static StringBuilder getPowerFutureSeasonSuffix1(ZonedDateTime periodStartDate) {
		int month = periodStartDate.getMonthValue();
		StringBuilder sb = new StringBuilder();
		if (month == 3) {
			sb.append("APR");
	      }
	      else
	        sb.append("OCT");
		return sb;
	}
}
