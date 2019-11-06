<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>menu</title>
<link rel="stylesheet" href="skin/css/base.css" type="text/css" />
<link rel="stylesheet" href="skin/css/menu.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script language='javascript'>
	var curopenItem = '1';
</script>
<script language="javascript" type="text/javascript"
	src="skin/js/frame/menu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
<base target="main" />
</head>
<body target="main">
	<table  width='99%' height="100%" border='0' cellspacing='0' cellpadding='0' >
        <tr><td style='padding-left:3px;padding-top:8px' valign='top' id="menuss">

			<c:forEach items="${sessionScope.sourcesList}" var="secondSources">
				<dl class='bitem'>
					<dt onclick=showHide("items1_1")><b>项目管理</b></dt>

					<dd style='display:block' class='sitem' id=items1_1>
						<ul class='sitemu' id=0>
							<li><a href='project-base.jsp' target='main'>基本信息管理</a> </li>
							<li><a href='project-need.jsp' target='main'>需求分析管理</a> </li>
							<li><a href='project-model.jsp' target='main'>模块管理</a> </li>
							<li><a href='project-function.jsp' target='main'>功能管理</a> </li>
							<li><a href='project-file.jsp' target='main'>附件管理</a> </li>
						</ul>
					</dd>
				</dl>
			</c:forEach>


		</td>
		</tr>
	</table>
</body>
</html>