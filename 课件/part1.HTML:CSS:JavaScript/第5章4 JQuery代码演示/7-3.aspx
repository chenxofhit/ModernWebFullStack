<%@ Page Language="C#" ContentType="text/xml" ResponseEncoding="gb2312" %>
<%@ Import Namespace="System.Data" %>
<%
	Response.ContentType = "text/xml";
	Response.CacheControl = "no-cache";
	Response.AddHeader("Pragma","no-cache");
	
	string xml = "<p id='kk'>p标记<span>内套span标记</span></p><span>单独的span标记</span>";
	Response.Write(xml);
%>