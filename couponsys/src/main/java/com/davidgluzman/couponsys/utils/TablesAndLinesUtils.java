package com.davidgluzman.couponsys.utils;

import java.util.List;

import com.davidgluzman.couponsys.beans.Company;
import com.davidgluzman.couponsys.beans.Coupon;
import com.davidgluzman.couponsys.beans.Customer;

public class TablesAndLinesUtils {
	
	public static void printLine() {
		System.out.println();
		System.out.println("==========================================================================================");
		System.out.println();
	}

	public static void printCompaniesTable(List<Company> arrayList,String string) {
		printLine();
		String leftAlignFormatString = "| %-8s | %-13s | %-27s | %-17s | %-9s |%n";
		String fullLineString="+----------------------------------------------------------------------------------------+%n";
		String lineString = "+----------+---------------+-----------------------------+-------------------+-----------+%n";
		System.out.format(fullLineString);
		System.out.format("| %-86s |  %n",string);
		System.out.format(fullLineString);
		System.out.format("|                                     COMPANIES                                          |%n");
		System.out.format(lineString);
		System.out.format("|    id    |     name      |            email            |     password      |  coupons  |%n");
		System.out.format(lineString);
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.printf(leftAlignFormatString, (arrayList.get(i)).getId(), (arrayList.get(i)).getName(),
					(arrayList.get(i)).getEmail(), (arrayList.get(i)).getPassword(), (arrayList.get(i)).getCoupons());
			System.out.format(lineString);
		}
	}
	public static void printCustomersTable(List<Customer> arrayList,String string) {
		printLine();
		String leftAlignFormatString = "| %-8s | %-13s | %-27s | %-17s | %-9s |%n";
		String fullLineString="+----------------------------------------------------------------------------------------+%n";
		String lineString = "+----------+---------------+-----------------------------+-------------------+-----------+%n";
		System.out.format(fullLineString);
		System.out.format("| %-86s |  %n",string);
		System.out.format(fullLineString);
		System.out.format("|                                     CUSTOMERS                                          |%n");
		System.out.format(lineString);
		System.out.format("|    id    |     name      |            email            |     password      |  coupons  |%n");
		System.out.format(lineString);
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.printf(leftAlignFormatString, (arrayList.get(i)).getId(), (arrayList.get(i)).getFirstName()+" "+(arrayList.get(i)).getLastName(),
					(arrayList.get(i)).getEmail(), (arrayList.get(i)).getPassword(), (arrayList.get(i)).getCoupons());
			System.out.format(lineString);
		}
	}

	public static void printCouponsTable(List<Coupon> arrayList, String string) {
		printLine();
		String leftAlignFormatString = "| %-2s | %-10s | %-8s | %-5s | %-21s | %-10s | %-10s | %-6s | %-5s | %-5s |%n";
		String fullLineString="+---------------------------------------------------------------------------------------------------------------+%n";
		String lineString = "+----+------------+----------+-------+-----------------------+------------+------------+--------+-------+-------+%n";
		System.out.format(fullLineString);
		System.out.format("| %-109s |  %n",string);
		System.out.format(fullLineString);
		System.out.format("|                                    	             COUPONS  	                                                |%n");
		System.out.format(lineString);
		System.out.format("| id | company id | category | title |      description      | start date |  end date  | amount | price | image |%n");
		System.out.format(lineString);
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.printf(leftAlignFormatString,(arrayList.get(i)).getId(),(arrayList.get(i)).getCompanyID(),(arrayList.get(i)).getCategory(),(arrayList.get(i)).getTitle(),(arrayList.get(i)).getDescription(),(arrayList.get(i)).getStartDate().getDate()+"/"+(arrayList.get(i)).getStartDate().getMonth()+"/"+((arrayList.get(i)).getStartDate().getYear()+1900),(arrayList.get(i)).getEndDate().getDate()+"/"+(arrayList.get(i)).getEndDate().getMonth()+"/"+((arrayList.get(i)).getEndDate().getYear()+1900),(arrayList.get(i)).getAmount(),(arrayList.get(i)).getPrice(),(arrayList.get(i)).getImage());
			System.out.format(lineString);
		}
		
	}

}
