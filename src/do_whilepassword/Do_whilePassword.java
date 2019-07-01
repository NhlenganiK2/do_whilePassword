/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package do_whilepassword;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.swing.JOptionPane;

/**
 *
 * @author Ben.Chabalala
 */
public class Do_whilePassword {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        createFile();
        showDialog();
    }
    
    public static void showDialog() throws IOException
    {
        int count = 0;
        String newPassword;
        boolean isValid = true;

        String InPassword = "";
        
        String sysPassword = createFile();

        do 
        {
            InPassword = JOptionPane.showInputDialog("Enter Password");
            if(InPassword.equals(sysPassword))
            {
                System.out.println("correct password");
            }
            if(!(InPassword.equals(sysPassword)))
            { 
              System.out.println("incorrect password. Please enter the correct password");
            }
            count++;
        }while(!InPassword.equals(sysPassword) && count<3); 

        

        if((InPassword.equals(sysPassword))== !true)
        { 
           System.out.println("The password was " + sysPassword + " Please enter a correct new password");
           newPassword=JOptionPane.showInputDialog("Enter New Password:");
           
           isValid = validateNewPassword(InPassword);
           
           if(isValid==true) 
           {
                SaveNewPasswordInFile(newPassword);
                System.out.println("Password changed Successfully");
           }
        } 
    }
    
    public static String createFile() throws FileNotFoundException, UnsupportedEncodingException, IOException
    {
        File f = new File("password.txt");
        String password = "";
        
        if(!f.exists())
        {
            PrintWriter writer = new PrintWriter("password.txt", "UTF-8");
            writer.print("John");
            writer.close();
        }
        else
        {
            PrintWriter writer = new PrintWriter(f);
            writer.print("John");
            writer.close();
        }
        
        BufferedReader br = new BufferedReader(new FileReader(f));
        password = br.readLine();

        return password;
    }
    
    public static void SaveNewPasswordInFile(String newPassword) throws FileNotFoundException
    {
        File f = new File("password.txt");
        PrintWriter writer = new PrintWriter(f);
        writer.print("");
        writer.print(newPassword);
        writer.close();
    }
    
    public static boolean validateNewPassword(String newPassword)
    {
           
        boolean isValid = true;
        String numbers = "(.*[0-9].*)";
        String inputPass="";
        String lowerCaseChars = "(.*[a-z].*)";
        String password;
        String upperCaseChars = "(.*[A-Z].*)";
        
        
        //>  Password may have any number of characters between 1 and 20.
        if((newPassword.length()>20)==true || (newPassword.length()<0)==true) 
        {
            System.out.println(" password length is not correct ");
            isValid=false;
        }
        //> Password may start with an underscore “_” or any letter of the alphabet
        else if((newPassword.charAt(0) == '_')==false || (newPassword.substring(0,1).equalsIgnoreCase("true"))==false)
        {
            isValid=true;
        }
        //> Password may be any combination of upper and lower case letters.
        else  if ((newPassword.matches(upperCaseChars ))==false || (newPassword.matches(lowerCaseChars)  )==false)
        {
            isValid=true;                 
        }
        //> Password consists only of numbers, English alphabet letters, and the underscore character.
        else if ((newPassword.matches(numbers ))==false && (newPassword.matches("[a-z A-Z]"))==false && (newPassword.matches ("[_]" ))==false)
        {
             System.out.println(
            " password should consist only of numbers, English alphabet letters, and the underscore character.");
            isValid=false;         
        }
        
        return isValid;
    }
}
