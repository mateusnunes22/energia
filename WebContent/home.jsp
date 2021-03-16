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
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="LayoutNovo/CSS/homeList.css" rel="stylesheet"
	type="text/css" />
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>

<body>

	<%@ include file="menu.html"%>

	<f:view>

		<div id="home">
			<fieldset>
				<h:form>

					<h:panelGrid columns="5">
						<h:outputText id="paciente" value=" UH"
							styleClass="texto"></h:outputText>
						<h:inputText value="#{clienteBean.clienteList.texto}" size="40"
							styleClass="true" maxlength="100" id="paciente2"></h:inputText>
						<h:commandButton id="buscar" value="Buscar"
							action="#{clienteBean.atualizar}"></h:commandButton>
						<!-- <h:outputText id="mac" value=" Apartamentos"
							styleClass="true"></h:outputText> -->
			&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
			&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
			
					<h:commandLink styleClass="hiperLink" value="Buscar WEC"
							action="#{clienteBean.buscarWEC}"></h:commandLink>
					</h:panelGrid>

					<br />
					<rich:dataTable id="Liberarr"
						onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
						onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
						width="800" border="2" value="#{clienteBean.listaNomeLike}"
						var="c" rendered="#{not empty clienteBean.listaNomeLike}"
						binding="#{clienteBean.objTabela }" rows="10">

						<f:facet name="header">
							<rich:columnGroup>
								<rich:column colspan="15" styleClass="listcli"
									id="listadepacientes">
									<h:outputText
										value="Lista de UHs  (#{clienteBean.clienteList.size })"
										id="listadepacientes2" />
								</rich:column>
								<rich:column breakBefore="true" id="nome">
									<h:outputText value="UH" id="nome2" />
								</rich:column>
								<rich:column id="ip">
									<h:outputText value="Mac instalado" id="ip2" />
								</rich:column>
								<rich:column id="status">
									<h:outputText value="Mac Status" id="status2" />
								</rich:column>
								<rich:column id="modo">
									<h:outputText value="Modo" id="modo2" />
								</rich:column>
								<rich:column id="releLuz">
									<h:outputText value="Rele Luz" id="releLuz2" />
								</rich:column>
								<rich:column id="releLocacao">
									<h:outputText value="Rele Locação" id="releLocacao2" />
								</rich:column>
								<rich:column id="editar">
									<h:outputText value="Editar Mac" id="editar2" />
								</rich:column>
								<rich:column>
									<h:outputText value="Remover Mac" />
								</rich:column>
							</rich:columnGroup>
						</f:facet>

						<rich:column id="nome3">
							<h:outputText styleClass="textoTabela" value="#{c.nome}" id="nome4" />
						</rich:column>
						<rich:column id="ip3">
							<h:outputText styleClass="textoTabela" value="#{c.fone}" id="ip4" />
						</rich:column>
						<rich:column id="status3">
							<h:outputText styleClass="textoTabela" value="#{c.status}" id="status4" />
						</rich:column>
						<rich:column>
							<h:commandButton action="#{clienteBean.modoOperacao}"
								image="#{c.fone2 }" />
						</rich:column>
						<rich:column>
							<h:commandButton action="#{clienteBean.alterarLuz}"
								image="#{c.retorno }" />
						</rich:column>
						<rich:column>
							<h:commandButton action="#{clienteBean.alterarLocacao}"
								image="#{c.renovacao }" />
						</rich:column>
						<rich:column>
							<h:commandButton action="#{clienteBean.editar}"
								image="LayoutNovo/Imagem/editar.png" />
						</rich:column>
						<rich:column>
							<h:commandButton action="#{clienteBean.removerMac}"
								image="LayoutNovo/Imagem/remover.png" />
						</rich:column>
						<f:facet name="footer">
							<rich:datascroller id="ds" renderIfSinglePage="true"></rich:datascroller>
						</f:facet>

					</rich:dataTable>


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
