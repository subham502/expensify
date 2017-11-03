package com.ho.test_expensify;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;

import org.apache.commons.io.IOUtils;

public class Auth 
{
	public static String formatData = null; 
	public String authenticate(String user,String pass) throws IOException
    {
  	
    	HttpURLConnection httpcon = (HttpURLConnection) ((new URL("https://integrations.expensify.com/Integration-Server/ExpensifyIntegrations").openConnection()));
    	httpcon.setDoOutput(true);
    	httpcon.setRequestProperty("Expect", "");
    	
    	try {
			httpcon.setRequestMethod("POST");
			httpcon.connect();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	 Date today = new Date();
         int days = Integer.parseInt("10");
         Date before90days = new Date(today.getTime()-1000*60*60*days*24l);
	
    	JsonObject data = Json.createObjectBuilder()
    			.add("test", "true")
				.add("type","file")
				.add("credentials", Json.createObjectBuilder()
										//.add("partnerUserID", "aa_subha_aptus_gmail_com")
										//.add("partnerUserSecret", "f17a585243da6f9669cde863f032d5f8c3a6f560"))
		    							.add("partnerUserID", user)
										.add("partnerUserSecret",pass))
				.add("onReceive", Json.createObjectBuilder()
										.add("immediateResponse", Json.createArrayBuilder()
																		.add("returnRandomFileName")))
				.add("inputSettings",Json.createObjectBuilder()
										.add("type", "combinedReportData")
										.add("filters", Json.createObjectBuilder()
															.add("startDate", new SimpleDateFormat("yyyy-MM-dd").format(before90days).toString())))
				.add("outputSettings",Json.createObjectBuilder()
											.add("fileExtension","csv"))
				.add("onFinish", Json.createArrayBuilder()
									.add(Json.createObjectBuilder()
												.add("actionName", "markAsExported"))).build();
				String template ="<#if addHeader == true>"
				+ "reportID,accountID,reportName,managerID,managerEmail,accountEmail,created,total amount,status,submitted,currency,approved,<#t>"
				+ "approvers,managerPayrollID,policyName,policyID,reimbursed,submitterPayrollID,submitterUserID,<#t>"
				+ "expense.transactionID,<#t>"
				+ "expense.cardID,expense.reportID,expense.mcc,expense.tag,expense.currency,expense.billable,<#t>"
				+ "expense.amount,expense.inserted,expense.reimbursable,expense.currencyConversionRate,<#t>"
				+ "expense.created,expense.modifiedAmount,expense.bank,expense.receiptID,expense.receiptFilename,<#t>"
				+ "expense.modifiedCreated,expense.merchant,expense.convertedAmount,<#t>"
				+ "expense.category,expense.modifiedMerchant,expense.comment,<#t>"
				+ "expense.modifiedMCC,expense.receiptObject.thumbnail,<#t>"
				+ "expense.receiptObject.smallThumbnail,<#t>"
				+ "expense.receiptObject.transactionID,expense.receiptObject.type,<#t>"
				+ "expense.receiptObject.url,<#t>"
				+ "tagGlCode,taxAmount,taxName,taxRate,taxRateName,taxCode,type,units.count,units.rate,units.unit,<#t>"
				+ "categoryGlCode,categoryPayrollCode,hasTax,ntagX,ntagXGlCode,requesterEmail<#lt>"
				+ "</#if>"
				+ "<#list reports as report>\n"
					+ "<#list report.transactionList as expense>\n"
						+ "${report.reportID},<#t>\n"
						+ "${report.accountID},<#t>\n"
						+ "${report.reportName},<#t>\n"
						+ "${report.managerID},<#t>\n"
						+ "${report.managerEmail},<#t>\n"
						+ "${report.accountEmail},<#t>\n"
						+ "${report.created},<#t>\n"
						+ "${(report.total/100)?string(\"0.00\")},<#t>\n"
						+ "${report.status},<#t>\n"
						+ "${report.submitted},<#t>\n"
						+ "${report.currency},<#t>\n"
						+ "${report.approved},<#t>\n"
						+ "${report.approvers},<#t>\n"
						+ "${report.managerPayrollID},<#t>\n"
						+ "${report.policyName},<#t>\n"
						+ "${report.policyID},<#t>\n"
						+ "${report.reimbursed},<#t>\n"
						+ "${report.submitterPayrollID},<#t>\n"
						+ "${report.submitterUserID},<#t>\n"
						+ "${expense.transactionID},<#t>\n"
						+ "${expense.cardID},<#t>\n"
						+ "${expense.reportID},<#t>\n"
						+ "${expense.mcc},<#t>\n"
						+ "${expense.tag},<#t>\n"
						+ "${expense.currency},<#t>\n"
						+ "${expense.billable?string('true','false')},<#t>\n"
						+ "${(expense.amount/100)?string(\"0.00\")},<#t>\n"
						+ "${expense.inserted},<#t>\n"
						+ "${expense.reimbursable?string('true','false')},<#t>\n"
						+ "${expense.currencyConversionRate},<#t>\n"
						+ "${expense.created},<#t>\n"
						+ "${(expense.modifiedAmount/100)?string(\"0.00\")},<#t>\n"
						+ "${expense.bank},<#t>\n"
						+ "${expense.receiptID},<#t>\n"
						+ "${expense.receiptFilename},<#t>\n"
						+ "${expense.modifiedCreated},<#t>\n"
						+ "${expense.merchant},<#t>\n"
						+ "${(expense.convertedAmount/100)?string(\"0.00\")},<#t>\n"
						+ "${expense.category},<#t>\n"
						+ "${expense.modifiedMerchant},<#t>\n"
						+ "${expense.comment},<#t>\n"
						+ "${expense.modifiedMCC},<#t>\n"
						+ "${expense.tagGlCode},<#t>\n"
						+ "${expense.taxAmount},<#t>\n"
						+ "${expense.taxName},<#t>\n"
						+ "${expense.taxRate},<#t>\n"
						+ "${expense.taxRateName},<#t>\n"
						+ "${expense.taxCode},<#t>\n"
						+ "${expense.type},<#t>\n"
						+ "${expense.units.count},<#t>\n"
						+ "${expense.units.rate},<#t>\n"
						+ "${expense.units.unit},<#t>\n"
						+ "${expense.categoryGlCode},<#t>\n"
						+ "${expense.categoryPayrollCode},<#t>\n"
						+ "${expense.hasTax},<#t>\n"
						+ "${expense.ntagX},<#t>\n"
						+ "${expense.ntagXGlCode},<#t>\n"
						+ "${request.requesterEmail},<#t>\n"
						+ "<#--The receipt object can be empty sometimes -->\n"
						+ "<#if expense.receiptObject?has_content>\n"
							+ "${expense.receiptObject.thumbnail!},<#t>\n"
							+ "${expense.receiptObject.smallThumbnail!},<#t>\n"
							+ "${expense.receiptObject.transactionID!},<#t>\n"
							+ "${expense.receiptObject.type!},<#t>\n"
							+ "${expense.receiptObject.url!}<#lt>\n"
						+ "<#else>,,,,,,,,,,\n"
						+ "</#if>\n"
					+ "</#list>\n"
				+ "</#list>";
    	
    	String getdata = "requestJobDescription="+data.toString()+"&template="+template;
    	
    	OutputStreamWriter output = new OutputStreamWriter(httpcon.getOutputStream());
    	output.write(getdata);
    	output.close();
    	
    	InputStreamReader input = new InputStreamReader(httpcon.getInputStream());
    	formatData = IOUtils.toString(input);
    	//System.out.println(formatData);
    	input.close();
    	httpcon.disconnect();
    	

		httpcon = (HttpURLConnection) ((new URL("https://integrations.expensify.com/Integration-Server/ExpensifyIntegrations").openConnection()));
    	httpcon.setDoOutput(true);
    	httpcon.setRequestProperty("Expect", "");
    	
    	try {
			httpcon.setRequestMethod("POST");
			httpcon.connect();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    	String tempData = "requestJobDescription={\"type\":\"download\",\"credentials\":{\"partnerUserID\":\"aa_subham502_gmail_com\",\"partnerUserSecret\":\"c071455c6287a5c5e77920eb201ba3d39a501a45\"},\"fileName\":"+formatData+"}";
    	OutputStreamWriter outputData = new OutputStreamWriter(httpcon.getOutputStream());
    	outputData.write(tempData);
    	outputData.close();
    	
    	InputStreamReader inputData = new InputStreamReader(httpcon.getInputStream());  	
    	String outString = IOUtils.toString(inputData);
    	//System.out.println(outString);
    	inputData.close();
    	System.out.println(outString);
    	return outString;
    }
}
	