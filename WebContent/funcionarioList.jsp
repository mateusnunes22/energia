<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich" %>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Busca</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="LayoutNovo/CSS/homeList.css" rel="stylesheet" type="text/css" />
<style type="text/css">
</style>

<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>

<body>
	<%@ include file = "menu.html" %>

<f:view>

<div id="home">

<fieldset>

<h:form>
 
					
					<h:panelGrid width="750">
					<h:panelGrid columns="4">
						<h:outputText id="energia" value=" Dentista" styleClass="texto"></h:outputText>
						<h:inputText id="true" value="#{funcionarioBean.funcionarioList.texto}" size="40"
						styleClass="true"></h:inputText>
						<h:commandButton id="buscar" value="Buscar" action="#{funcionarioBean.atualizar}">
						</h:commandButton>
						<h:commandButton id="novo" action="#{funcionarioBean.novo}" value="Novo"
							styleClass="novcli" image="LayoutNovo/Imagem/novo.png" title="Novo Dentista"/>
					</h:panelGrid>
					<br />
					
<rich:dataTable 
	id="Liberarr"  
	onRowMouseOver="this.style.backgroundColor='#F1F1F1'"  
	onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'" 
	width="750" border="2" value="#{funcionarioBean.listaNomeLike}" var="c"  
	rendered="#{not empty funcionarioBean.listaNomeLike}" rows="10" binding="#{funcionarioBean.objTabela }">
   
 
 <f:facet name="header">
        <rich:columnGroup>
    	    <rich:column colspan="15" id="listadefuncionario">
        	<h:outputText value="Lista de Funcionarios (#{funcionarioBean.funcionarioList.size })" id="listadefuncionario2"/>
      		</rich:column >
      		<rich:column breakBefore="true" id="nome">
                 <h:outputText value="Nome" id="nome2"/>
            </rich:column>
            <rich:column id="especialidade">
                <h:outputText value="Especialidade" id="especialidade2"/>
            </rich:column>  
            <rich:column id="editar">
				<h:outputText value="Editar" id="editar2"/>
			</rich:column>          
         </rich:columnGroup>
    </f:facet>
    
    <rich:column id="nome3">
        <h:outputText value="#{c.nome}" id="nome4"/>  
   </rich:column>
   <rich:column id="especialidade3">
        <h:outputText value="#{c.especialidade}" id="especialidade4"/>  
   </rich:column>
   <rich:column id="editar3">
		<h:commandButton action="#{funcionarioBean.editar}" image="LayoutNovo/Imagem/editar.png" id="editar4"/>
	</rich:column>

   <f:facet name="footer">
                <rich:datascroller id="ds" renderIfSinglePage="true"></rich:datascroller>
            </f:facet>
 
</rich:dataTable> 

<rich:messages layout="table" infoLabelClass="messageInfo"
						errorLabelClass="messageError" id="message">

						<f:facet name="infoMarker">
							<h:graphicImage value="imagenss/check.png" id="check"/>
						</f:facet>

						<f:facet name="errorMarker">
							<h:graphicImage value="imagenss/fail.png" id="fail"/>
						</f:facet>

					</rich:messages>

</h:panelGrid>
    
</h:form>
</fieldset>
</div>
</f:view>

</body>
</html>
