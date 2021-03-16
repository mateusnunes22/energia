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
<title>Configuração WEC</title>
<link href="LayoutNovo/CSS/homeList.css" rel="stylesheet" type="text/css" />
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>

<body>

	<%@ include file="menu.html"%>

	<f:view>
		<div id="home">
				<fieldset>
					<h:form>
						<h3>Configurações</h3>

						<rich:messages layout="table" id="message" infoLabelClass="messageInfo"
							errorLabelClass="messageError">
							<f:facet name="infoMarker">
								<h:graphicImage value="imagenss/check.png" id="check"/>
							</f:facet>
							<f:facet name="errorMarker">
								<h:graphicImage value="imagenss/fail.png" id="fail"/>
							</f:facet>
						</rich:messages>
						
						<h:panelGrid columns="5">
							<h:outputText value="" styleClass="texto" id="buscar2"></h:outputText>
							<h:outputText value="definir UH e IP:" styleClass="texto" id="buscar1"></h:outputText>
							<h:outputText styleClass="true" id="apto" value="#{clienteBean.cliente.nome}"></h:outputText>
							<h:selectOneMenu id="numeroUsuario321"  value="#{clienteBean.cliente.idAtendimento }">
       						 <f:selectItems value="#{atendimentoBean.mac }"  />
							</h:selectOneMenu> 
							<h:commandButton value="Atualizar"
								action="#{clienteBean.salvar}" id="salvar"></h:commandButton>

						</h:panelGrid>

						<br />
					</h:form>
				</fieldset>
			</div>
	</f:view>

</body>
</html>
