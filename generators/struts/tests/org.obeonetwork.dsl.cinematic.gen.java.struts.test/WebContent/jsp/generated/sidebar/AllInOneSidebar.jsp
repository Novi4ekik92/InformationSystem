<%@ taglib
        uri="http://struts.apache.org/tags-html"
        prefix="html" %>
<%@ taglib
        uri="http://struts.apache.org/tags-bean"
        prefix="bean" %>

<table style="border: 0px;">
	<tr>
		<td><html:image page="/images/navig.gif"/></td>
		<td>Navigation links : </td>
	</tr>
	<tr>
		<td>-</td>
		<td><html:link action="/scenarioprincipal/circuitstart">Circuit Start</html:link> Screen</td>
	</tr>
	<tr>
		<td>-</td>
		<td><html:link action="/scenarioprincipal/loop">Loop</html:link> Screen</td>
	</tr>
	<tr>
		<td>-</td>
		<td><html:link action="/scenarioprincipal/many1">Many 1</html:link> Screen</td>
	</tr>
	<tr>
		<td>-</td>
		<td><html:link action="/scenarioprincipal/many2">Many 2</html:link> Screen</td>
	</tr>

</table>