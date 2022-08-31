package logfilezipperbackground;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author buddhika_j
 */
public class compress {
     public static void compress(String dirPath,String zippath,String dirname) {
        final Path sourceDir = Paths.get(dirPath);
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");  //yyyy/MM/dd HH:mm:ss
         LocalDateTime now = LocalDateTime.now();  
       
      
        System.out.println("compress : Zipname : " +dirname);
        String zipFileName = zippath.concat(dirname+"_"+dtf.format(now)+".zip");
        
        try {
            try (ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(zipFileName))) {
                Files.walkFileTree(sourceDir, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
                        try {
                            Path targetFile = sourceDir.relativize(file);
                            outputStream.putNextEntry(new ZipEntry(targetFile.toString()));
                            byte[] bytes = Files.readAllBytes(file);
                            outputStream.write(bytes, 0, bytes.length);
                            outputStream.closeEntry();
                        } catch (IOException e) {
                        }
                        return FileVisitResult.CONTINUE;
                    }
                });
            }
        } catch (IOException e) {
             System.err.println("compress : IOException : "+e.getMessage());
        }
         File filetodelete= new File(dirPath);
         cleanDirectory(filetodelete);
    }
     
   private static void cleanDirectory(File dir) {
        System.err.println("cleanDirectory :");
        
    for (File file: dir.listFiles()) {
        if(file.getName().equals("server.log")) {
            //do nothing
        } else {
            //delete file
            file.delete();
            System.out.println("cleanDirectory : old file deleted ");
        }

    }
    }
}
