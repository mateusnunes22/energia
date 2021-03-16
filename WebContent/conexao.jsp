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
<title>Configuração de Integração</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="LayoutNovo/CSS/homeList.css" rel="stylesheet"
	type="text/css" />
<style type="text/css">
</style>

<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>

<body>

	<%@ include file="menu.html"%>

	<f:view>

		<div id="home">
			<fieldset>
				<h:form>

 <h:panelGrid columns="2">

						<h:outputText value="Ip: " id="nome" styleClass="texto"></h:outputText>
						<h:inputText id="nomef" value="#{funcionarioBean.conexao.ip }"
							size="20" maxlength="40"></h:inputText>

						<h:outputText value="Porta: " id="especialidade"
							styleClass="texto"></h:outputText>
						<h:inputText id="especialidadef"
							value="#{funcionarioBean.conexao.porta }" size="20"
							styleClass="caixaTexto" maxlength="10"></h:inputText>

						<h:outputText value="Nome da base de dados: " id="base"
							styleClass="texto"></h:outputText>
						<h:inputText id="basef"
							value="#{funcionarioBean.conexao.baseDeDados }" size="20"
							maxlength="20"></h:inputText>

						<h:outputText value="SSID: " id="ssid" styleClass="texto"></h:outputText>
						<h:inputText id="ssidf" value="#{funcionarioBean.conexao.ssid }"
							size="20" styleClass="caixaTexto" maxlength="40"></h:inputText>

						<h:outputText value="Senha: " id="senha" styleClass="texto"></h:outputText>
						<h:inputSecret id="senhaf" value="#{funcionarioBean.conexao.senha }"
							size="20" styleClass="caixaTexto" maxlength="40"></h:inputSecret>

						<h:outputText value="" id="vazio"></h:outputText>
						<h:commandButton value="Salvar" id="salvar"
							action="#{funcionarioBean.salvarConexao}"></h:commandButton>

					</h:panelGrid>


					<rich:messages layout="table" infoLabelClass="messageInfo"
						errorLabelClass="messageError" id="message">

						<f:facet name="infoMarker">
							<h:graphicImage value="imagenss/check.png" id="check" />
						</f:facet>

						<f:facet name="errorMarker">
							<h:graphicImage value="imagenss/fail.png" id="fail" />
						</f:facet>

					</rich:messages>

				</h:form>
			</fieldset>
		</div>
	</f:view>

</body>
</html>