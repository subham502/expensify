package com.ho.test_expensify;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import org.json.JSONObject;

public class GlobalCurrency 
{
	public static String getHTML(String urlToRead){
	      /*StringBuilder result = new StringBuilder();
	      URL url = new URL(urlToRead);
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      String line;
	      while ((line = rd.readLine()) != null) {
	         result.append(line);
	      }
	      rd.close();
	      return result.toString(); */
		String postResp = null;
		try{
			//String targetURL = "http://api.fixer.io/2016-07-13?base=AUDi";
	          GetMethod get = new GetMethod(urlToRead);           
	          HttpClient httpclient = new HttpClient();
	          httpclient.executeMethod(get);
	          postResp = get.getResponseBodyAsString();
	          System.out.println("The Response from the server : "+postResp);

		}
		catch(Exception e){
			e.printStackTrace();
		}  
        return postResp;
	   }

	   public static String getExchangeRate(String globalCur, String expenseDate) throws Exception
	   {
			java.sql.Date res = null;
			try {
				res = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(expenseDate).getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		 //System.out.println(res);
	     String output = getHTML("http://api.fixer.io/"+res+"?base="+globalCur);
	     //System.out.println(output);
	     
	     JSONObject  objParse = new JSONObject(output);
	    // JSONObject rates = (JSONObject) objParse.get("rates");    
	     //System.out.println(rates.get(reportCur));
	    // return rates.get(reportCur).toString();
	     return output;
	   }
	   public static void main(String[] args) throws Exception {
		   String output = getExchangeRate("AUD","2016-07-13 10:24:02");
		   System.out.println(output);
		}
}