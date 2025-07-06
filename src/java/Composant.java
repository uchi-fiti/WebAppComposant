package Affichage;

import java.lang.reflect.Field;
import java.util.Vector;

public class Composant {
    private Vector<Object> data;

    public Vector<Object> getData() {
        return data;
    }
    public void setData(Vector<Object> data) {
        this.data = data;
    }

    public String construireHtmlFormComposant()throws Exception {
        String html = "";
        Field[] fields = this.getClass().getDeclaredFields();
        Class<?> th = this.getClass();
        html+= "Composant: "+th.getName();
        html += "<form action=\"traitement.jsp\" method=\"get\">";
        html+= "<input type=\"hidden\" name=\"classe\" value=\"" + th.getName() + "\"";
        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            f.setAccessible(true);
            Class<?> type = f.getType();
            html += "<label>" + f.getName() + "</label> : ";
            if(f.getType().getName().contains("Affichage.")){
                html+="</br>";
                Class<?> clazz = Class.forName(f.getType().getName());
                if (Composant.class.isAssignableFrom(clazz)) {
                    @SuppressWarnings("unchecked")
                    Class<? extends Composant> classModel = (Class<? extends Composant>) clazz;
                    Composant instance = classModel.getDeclaredConstructor().newInstance();
                    html += instance.construireHtmlFormComposant();
                }
            }
            if (type.equals(String.class)) {
                html += "<input type='text' name='" + f.getName() + "' />\n";
            }
            if (type.equals(int.class) || type.equals(Integer.class)
                    || type.equals(double.class) || type.equals(Double.class)) {
                html += "<input type='number' name='" + f.getName() + "' />\n";
            }
            html+="</br>";
        }
        html += "<input type=\"submit\" value=\"Valider\">";
        html += "</form>";

        return html;
    }
    public String construireHtmlTable(){
        if(getData()!=null && getData().size()>0) {
            String htmlTable = "";
            htmlTable += "<table border='1'>\n";
            htmlTable += "<tr>\n";

            // Ajouter les en-têtes de colonnes
            Field[] tableau_champ = getData().get(0).getClass().getDeclaredFields();
            for (int i = 0;i<tableau_champ.length;i++) {
                htmlTable += "<th>";
                htmlTable += tableau_champ[i].getName();
                htmlTable += "</th>\n";
            }
            htmlTable+="</tr>\n";

            // Ajouter les lignes de données
            for (int i = 0;i<getData().size();i++) {
                htmlTable+="<tr>\n";
                for (int j = 0;j<tableau_champ.length;j++) {
                    tableau_champ[j].setAccessible(true);
                    htmlTable+= "<td>";
                    htmlTable+=getValField(getData().get(i),tableau_champ[j]);
                    htmlTable+= "</td>\n";

                }
                htmlTable+="</tr>\n";
            }
            htmlTable+="</table>";

            return htmlTable;
        }
        return "";
    }

    public static String convertDebutMajuscule(String autre) {
        char[] c = autre.toCharArray();
        c[0] = Character.toUpperCase(c[0]);
        return new String(c);
    }

    public static Object getValField(Object classe, Field f) {
        try {
            String nomMethode = "get" + convertDebutMajuscule(f.getName());
            Object o = classe.getClass().getMethod(nomMethode, new Class<?>[0]).invoke(classe, new Object[0]);
            if (o == null)
                return "";
            return o;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }
}
