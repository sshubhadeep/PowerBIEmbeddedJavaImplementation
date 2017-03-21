package com.sshubhadeep.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sshubhadeep.helper.AppTokenCreator;

/**
 * Servlet implementation class MyReport
 */
@WebServlet("/MyReport")
public class MyReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyReport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String workspaceid="Your Workspace Id";
		String reportId="Your Report Id";
		String workspaceCollectionName="Your Workspace Collection Name";
		String accessToken="Your Access Token From Azure Portal";
		String apptoken=AppTokenCreator.createAppToken(workspaceid, reportId, workspaceCollectionName, accessToken);
		
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		out.println("<html-code>");
		out.println("<!DOCTYPE html><html><head><meta charset=\"utf-8\" /><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"+
					"<title>PowerBI Report</title><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"+
					"</head><body><button id=\"btnView\">View Report !</button><div id=\"divView\">"+
					"<iframe id=\"ifrTile\" width=\"100%\" height=\"90%\"></iframe></div>"+
					"<script>(function () { document.getElementById(\'btnView\').onclick = function() {"+
					"var iframe = document.getElementById(\'ifrTile\');"+
					"iframe.src = \'https://embedded.powerbi.com/appTokenReportEmbed?reportId="+reportId+"\';"+
					"iframe.onload = function() { var msgJson = { action: \"loadReport\","+
					"accessToken: \""+apptoken+"\", height: 500, width: 722 };"+
					"var msgTxt = JSON.stringify(msgJson); iframe.contentWindow.postMessage(msgTxt, \"*\");};};"+
					"}());</script></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
