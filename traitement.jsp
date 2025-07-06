<%@ page import="Affichage.*" %>
<% 
    String classe = request.getParameter("classe");
    out.println("Classe: " + classe);
    String filename = application.getRealPath("/") + classe.split("\\.")[1] + ".txt";
    out.println("Filename: " + filename);
    Writer w = new Writer(request, classe, filename);
    response.sendRedirect("index.jsp");
%>