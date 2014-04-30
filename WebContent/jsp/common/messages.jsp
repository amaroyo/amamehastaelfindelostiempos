<tr><td>
	<html:errors />
</td></tr>

<logic:messagesPresent message="true">
	<tr>
		<td>
			<html:messages id="msg" message="true" header="messages.header" footer="messages.footer"> 
 				<li><bean:write name="msg"/></li>
 			</html:messages>  
		</td>
	</tr>
</logic:messagesPresent>