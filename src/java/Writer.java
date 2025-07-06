package Affichage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.Field;

import jakarta.servlet.http.*;
public class Writer {
    HttpServletRequest request;
    String className;
    String filename;
    public Writer(HttpServletRequest h, String c, String f) throws Exception
    {
        request = h;
        className = c;
        filename = f;
        insert();
    }
    public void insert() throws Exception
    {
        Class <?> clazz = Class.forName(className);
        Field [] fields = clazz.getDeclaredFields(); 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.newLine();
            for(Field f : fields)
            {
                writer.write(f.getName() + "::" + request.getParameter(f.getName())+";;");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace(); 
        }
    }
}
