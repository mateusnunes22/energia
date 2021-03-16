<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>WiFi Energy Control</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="LayoutNovo/CSS/login.css" rel="stylesheet" type="text/css" />
<style type="text/css">
/*
.style2 {font-size: 12pt}
*/
</style>

<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>

<body>

	<f:view>
		<div id="login-form">
			<h3>Sistema</h3>
			<fieldset>
				<h:form>
					<h:panelGrid columns="1">
						<h:inputText id="usuario" value="#{usuarioBean.usuario.nomeUsuario}"
							title="Usuário" onclick="if (this.value=='Usuário') this.value='';" onblur="if (this.value=='') this.value='Usuário'"></h:inputText>
							
						<h:inputSecret id="password" redisplay="false"
							value="#{usuarioBean.usuario.senha}" title="Senha"></h:inputSecret>
							</h:panelGrid>
							<h:panelGrid columns="3">
							<h:commandButton value="Logar" action="#{usuarioBean.logar}" id="logar"></h:commandButton> &nbsp &nbsp  &nbsp &nbsp 
								<h:commandButton value="Status" action="#{usuarioBean.telaStatus}" id="naoLogar"></h:commandButton>
					</h:panelGrid>
					
					<rich:messages layout="table" infoLabelClass="messageInfo"
						errorLabelClass="messageError">
						<f:facet name="infoMarker">
							<h:graphicImage value="imagenss/check.png" id="check"/>
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="imagenss/fail.png" id="fail"/>
						</f:facet>
					</rich:messages>
					
				</h:form>
			</fieldset>
			<br />
			</div>
	
	</f:view>

	<%@ include file = "rodape.html" %>

</body>
</html>
