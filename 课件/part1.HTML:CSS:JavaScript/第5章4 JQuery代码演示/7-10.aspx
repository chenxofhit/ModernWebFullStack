<%@ Page Language="C#" ContentType="text/html" ResponseEncoding="gb2312" %>
<%@ Import Namespace="System.Data" %>
<%
	Response.CacheControl = "no-cache";
	Response.AddHeader("Pragma","no-cache");
	string sInput = Request["sColor"].Trim();
	if(sInput.Length == 0)
		return;
	string sResult = "";
	
	string[] aColors = new string[]{"aliceblue","antiquewith","aquamarine","azure","beige","bisque","black","blanchedalmond","blue","blueviolet","brass","bronze","brown","burlywood","cadetblue","chartreuse","chocolate","copper","coral","cornfloewrblue","cornsilk","cyan","darkblue","darkcyan","darkgoldenrod","darkgray","darkgreen","darkhaki","darkmagenta","darkolivegreen","darkorchid","darkorenge","darkred","darksalmon","darkseagreen","darkslateblue","darkslategray","darkturquoise","darkviolet","deeppink","deepskyblue","dimgray","dodgerblue","feldspar","firebrick","floralwhite","forestgreen","fuchsia","gainsboro","gold","goldenrod","golenrod","gostwhite","gray","green","greenyellow","honeydew","hotpink","indianred","inen","ivory","khaki","lavender","lavenderblush","lawngreen","lemonchiffon","lightblue","lightcoral","lightcyan","lightgodenrod","lightgodenrodyellow","lightgray","lightgreen","lightpink","lightsalmon","lightseagreen","lightskyblue","lightslateblue","lightslategray","lightsteelblue","lightyellow","lime","limegreen","magenta","magenta","maroom","maroon","mediumaquamarine","mediumblue","mediumorchid","mediumpurpul","mediumseagreen","mediumslateblue","mediumspringgreen","mediumturquoise","mediumvioletred","midnightblue","mintcream","mistyrose","moccasin","navajowhite","navy","navyblue","oldlace","olivedrab","orange","orchid","orengered","palegodenrod","palegreen","paleturquoise","palevioletred","papayawhip","peachpuff","peru","pink","plum","powderblue","purple","quartz","red","rosybrown","royalblue","saddlebrown","salmon","sandybrown","scarlet","seagreen","seashell","sienna","silver","skyblue","slategray","snow","springgreen","steelblue","tan","thistle","tomato","turquoise","violet","violetred","wheat","whitesmoke","yellow","yellowgreen"};

	for(int i=0;i<aColors.Length;i++){
		if(aColors[i].IndexOf(sInput) == 0)
			sResult += aColors[i] + ",";
	}
	if(sResult.Length>0)									//如果有匹配项
		sResult = sResult.Substring(0,sResult.Length-1);	//去掉最后的“,”号
	Response.Write(sResult);
%>