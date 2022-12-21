/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appkasir;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
/**
 *
 * @author USER
 */
public class Koneksi {
    Connection con;
    public Koneksi() {
        String id,pass,driver,url;
        id="root";
        pass="";
        driver="com.mysql.cj.jdbc.Driver";
        url="jdbc:mysql://localhost:3306/appkasirr";
        try {
        Class.forName(driver).newInstance();
        con=DriverManager.getConnection(url,id,pass);
        }
        catch(Exception e){
            System.out.println(""+e.getLocalizedMessage());
    }
}
    public static void main(String[] arg){
        Koneksi k = new Koneksi();
    }}
