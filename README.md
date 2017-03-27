<!--
  Title: PowerBI Embedded Java Implementation
  Description: A PowerBI Embedded Java Implementation  to diplay a report in iFrame.
  Author: sshubhadeep
  --> 


# PowerBI-Embedded-Java-Implementation
  
 ## Steps
  
    1. Create Azure SQL in Azure Portal
    2. Use PowerBI Desktop to load data from the database created in Azure Portal.
    3. Create a report template on the loaded dataset and save it in local drive.
    4. Create a workspace collection in Azure Portal.
    5. Add an workspace using PowerBI Cli (command line interface) tool.
    6. Generate a report using the same tool using the saved file.
    7. Use the current application to create a dynamic web project (add commons-lang3-3.5.jar if needed).
    8. View your report from iFrame after deploying the application.

  ## To run the application for existing report
  
    1. populate MyReport.java (Servlet) with the following details
    	workspaceid="Your Workspace Id";
		reportId="Your Report Id";
		workspaceCollectionName="Your Workspace Collection Name";
		accessToken="Your Access Token From Azure Portal";
    2. Run As -> Run on server
    
In case of further help contact me at sshubhadeep@gmail.com
