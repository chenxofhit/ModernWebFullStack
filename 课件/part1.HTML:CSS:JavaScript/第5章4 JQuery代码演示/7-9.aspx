<%@ Page Language="C#" ContentType="text/html" ResponseEncoding="gb2312" %>
<%@ Import Namespace="System.Data" %>
<%
	Response.CacheControl = "no-cache";
	Response.AddHeader("Pragma","no-cache");
	
	for(int i=0;i<100000000;i++);	//为了测试返回速度慢
	if(Request["user"]=="isaac")
		Response.Write("Sorry, " + Request["user"] + " already exists.");
	else
		Response.Write(Request["user"]+" is ok.");
%>