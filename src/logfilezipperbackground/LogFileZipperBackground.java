package logfilezipperbackground;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static logfilezipperbackground.compress.compress;


/**
 *
 * @author buddhika_j
 */
public class LogFileZipperBackground {
    public static void main(String[] args) {
      
//     Scanner sc= new Scanner(System.in); 
//     System.out.print("Enter Interval in Minutes : ");  
//     Long one= sc.nextLong(); 
//     System.out.print("Enter 0 to run now, otherwise enter 12 : "); 
//     Long two= sc.nextLong(); 
//     
       Long one=null,two=null;
      String executionPath = System.getProperty("user.dir");
      String path =executionPath.replace("\\", "/");
     
     try {
          BufferedReader br;
        
            br = new BufferedReader(new FileReader(path+"/time.txt"));

            StringBuilder sb = new StringBuilder();
            String line = null;
        
              line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
        
             line = br.readLine();
         
            }
            String everything = sb.toString();
            one = Long.parseLong(everything.split(System.lineSeparator())[0]);
            two = Long.parseLong(everything.split(System.lineSeparator())[1]);
         
             br.close();
     
        } catch (IOException | NumberFormatException ex) {
            Logger.getLogger(LogFileZipperBackground.class.getName()).log(Level.SEVERE, null, ex);
        }
        RunEveryDay.runtask(one,two);
      //  zipfile();
    }
    
    public static void  zipfile(){
       String executionPath = System.getProperty("user.dir");
      String dirname="ServerLog";
      String logpath= "";
      String zippath="";
      String path =executionPath.replace("\\", "/");
      System.out.println("Executing at =>"+path);
      
      try {
            BufferedReader br = new BufferedReader(new FileReader(path+"/path.txt"));
     
            StringBuilder sb = new StringBuilder();
            String line = null;
        
              line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
        
             line = br.readLine();
         
            }
            String everything = sb.toString();
            logpath = everything.split(System.lineSeparator())[0];
            zippath = everything.split(System.lineSeparator())[1];
            System.out.println("Logpath = : "+logpath);
            System.out.println("ZIPpath = : "+zippath);
            
             br.close();
           } catch (IOException ex) {            
                System.err.println("zipfile : IOException : "+ex.getMessage());
                Logger.getLogger(LogFileZipperBackground.class.getName()).log(Level.SEVERE, null, ex);
           }

            File files= new File(logpath);
       
            dirname =files.getAbsoluteFile().getName();
      
            System.out.println("zipfile : dirname: "+dirname);
            compress(logpath,zippath,dirname);
    
    }
   
}
