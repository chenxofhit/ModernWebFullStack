<%@ Page Language="C#" ContentType="text/html" ResponseEncoding="gb2312" %>
<%@ Import Namespace="System.Data" %>
<%
	if(Request.HttpMethod == "POST")
		Response.Write("POST: " + Request["firstName"] + ", your birthday is " + Request["birthday"]);
	else if(Request.HttpMethod == "GET")
		Response.Write("GET: " + Request["firstName"] + ", your birthday is " + Request["birthday"]);
%>