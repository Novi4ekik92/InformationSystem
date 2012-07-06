<%@ taglib
        uri="http://struts.apache.org/tags-html"
        prefix="html" %>
<%@ taglib
        uri="http://struts.apache.org/tags-bean"
        prefix="bean" %>
<%@ taglib
        uri="http://struts.apache.org/tags-logic"
        prefix="logic" %>


<logic:messagesPresent>
   <bean:message key="errors.header"/>
   <ul>
   <html:messages id="error">
      <li><bean:write name="error"/></li>
   </html:messages>
   </ul>
   <bean:message key="errors.footer"/>
   <hr />
</logic:messagesPresent>

<html:form action="/scenarioprincipal/togglebuttons" method="POST">

<html:hidden property="event" value=""/>

<fieldset>
	<legend>Informations</legend>
	
	<table>
		<tr>
			<td><label for="toggleButton1"><bean:message key="ToggleButtons.toggleButton1"/></label> :</td>
			<td><html:text property="toggleButton1" /></td>
		</tr>
		<tr>
			<td><label for="toggleButton2"><bean:message key="ToggleButtons.toggleButton2"/></label> :</td>
			<td><html:text property="toggleButton2" /></td>
		</tr>
		<tr>
			<td><label for="toggleButton3"><bean:message key="ToggleButtons.toggleButton3"/></label> :</td>
			<td><html:text property="toggleButton3" /></td>
		</tr>
	</table>
</fieldset>

<fieldset>
	<legend>Actions</legend>
	<table>
	</table>
</fieldset>

</html:form>