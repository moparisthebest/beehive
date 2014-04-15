<%@ page language="java"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="temp" %>
<%@ taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui" %>

<html>
<head>
<title><temp:attribute name="title"/></title>
<netui:base />
</head>
<body>
<h1><temp:attribute name="title"/></h1>
<table width="100%"><tr><td>
<table width="100%"><tr><td width="100">'left' Section</td><td><hr /></td></tr></table>
<temp:includeSection name="left"/>
<hr />
</td>
<td>
<table width="100%"><tr><td width="100">'right' Section</td><td><hr /></td></tr></table>
<temp:includeSection name="right"/>
<hr />
</td>
</tr>
</table>
</body>
</html>
