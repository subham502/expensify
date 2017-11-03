package com.ho.test_expensify;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
public class Parse {
	
	static Auth auth = new Auth();
	public static void main(String[] args)
	{
		String getData = null;
		String userid = "aa_accounts_yellowfin_bi";
		String password = "0dd250c4569a13d8359489c8d75c203baf83a59e";
		try {
			getData = auth.authenticate(userid, password);
			//System.out.println(getData);
			//CSVParser parse = new CSVParser(new StringReader(getData),CSVFormat.DEFAULT.withHeader("expense.inserted","expense.created","expense.merchant","expense.convertedAmount","expense.category"));
			CSVParser parse = CSVFormat.EXCEL.withHeader().parse(new StringReader(getData));
			List<CSVRecord> formatData = parse.getRecords();
			//System.out.println(formatData.get(0));
			//System.out.println(formatData.get(0).get(3));
			//System.out.println(parse.getHeaderMap().get("Merchant"));
			//System.out.println(formatData.get(2).get("expense.merchant"));
			//System.out.println(formatData.size());*/
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
}