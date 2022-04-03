/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;
import java.sql.*;
/**
 *
 * @author azizk
 */
public class MaConnection {
     public final String url="jdbc:mysql://localhost:3306/gclaim";
    public final String user="root";
    public final String pwd ="";
   private Connection cnx;
   public static MaConnection ct;
   public MaConnection(){ 
        try{
            cnx=DriverManager.getConnection(url, user, pwd);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            
        }
    }
    public Connection getConnection(){
        return cnx;
    }
    public static MaConnection getInstance(){
        if(ct==null)
            ct=new MaConnection();
        return ct;
    }

    public Connection MaConnection() {
        return cnx;
    }
}
