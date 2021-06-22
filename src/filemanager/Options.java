/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filemanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;


/**
 *
 * @author denis
 */
public class Options {

    private void getRootDirectories()
    {
        File[] roots = File.listRoots();
        for (File root : roots) {
            System.out.println(root);
        }
     }
    
    private void getFolders(String filePath)
    {
        File path = new File(filePath);
        if(path.exists() && path.isDirectory())
        {
        String[] strings = path.list();
        for(int i = 0; i < strings.length; i++)
            {
                System.out.println(strings[i]);
            }
        }
    }
    
    private void getFiles(String filePath)
    {
        File path = new File(filePath);
        if(path.exists() && path.isDirectory())
        {
        File[] files = path.listFiles();
        for(int i = 0; i < files.length; i++)
            {
                System.out.println(files[i].getName());
            }
        }
    }
      
    private static FileTime getCreationTime(File file) throws IOException {
    Path p = Paths.get(file.getAbsolutePath());
    BasicFileAttributes view
        = Files.getFileAttributeView(p, BasicFileAttributeView.class)
                    .readAttributes();
    FileTime fileTime=view.creationTime();
    
    return fileTime;
  }
    
    private static FileTime getLastModifiedTime(File file) throws IOException {
    Path p = Paths.get(file.getAbsolutePath());
    BasicFileAttributes view
        = Files.getFileAttributeView(p, BasicFileAttributeView.class)
                    .readAttributes();
    FileTime fileTime=view.lastModifiedTime();
    
    return fileTime;
  }
    private void fileInfo(String filePath) throws IOException
    {
        File file = new File(filePath);
        
        System.out.println("File name: " + file.getName());
        System.out.println("Path: " + file.getAbsolutePath());
        System.out.println("File size: " + file.length() + " bytes");
        System.out.println("Creation time: "
        +  new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                   .format(getCreationTime(file).toMillis()));
        System.out.println("Last modified time: "
        + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                   .format(getLastModifiedTime(file).toMillis()));
        
    }
    
    private void createDir(String filePath)
    {
        File newDirectory = new File(filePath);
        try {
        if(!newDirectory.exists())
        {
        newDirectory.mkdir();
        System.out.println("New directory created, name: " + newDirectory.getName());
        }
        else
        {
        System.out.println("Directory called " + newDirectory.getName() + " already exists.");
        }

        } catch (Exception e) {
        System.out.println("Couldn't create a directory called "
                            + newDirectory.getName());
        } 
    }
    
    private void rename(String filePath, String changedNameFilePath)
    {
        File oldfile = new File(filePath);
        File newfile = new File(changedNameFilePath);
        if(!oldfile.exists())
        {
        System.out.println("File with that name doesn't exist.");
        return;
        }
        if(newfile.exists())
        {
        System.out.println("File with the same name already exists on path: " + changedNameFilePath);
        return;
        }
        if(oldfile.renameTo(newfile))
        {
        System.out.println("Rename succesful.");
        }
        else
        {
        System.out.println("Rename failed.");
        }
    }
    
    private void copy(String filePath, String copyFilePath)
    {
        File a = new File(filePath);
        File b = new File(copyFilePath);

        try (FileInputStream inStream = new FileInputStream(a);
                FileOutputStream outStream = new FileOutputStream(b);) {

            byte[] buffer = new byte[1024];

            int length;
            
            while ((length = inStream.read(buffer)) > 0) {

                outStream.write(buffer, 0, length);
            }

            System.out.println("File is copied successfuly.");

        } catch (IOException exc) {
            System.out.println(exc);
        }
    }
    
    private void move(String filePath, String FileCopyPath)
    {
        File a = new File(filePath);
        File b = new File(FileCopyPath);

        try (FileInputStream inStream = new FileInputStream(a);
                FileOutputStream outStream = new FileOutputStream(b);) {

            byte[] buffer = new byte[1024];

            int length;
            
            while ((length = inStream.read(buffer)) > 0) {

                outStream.write(buffer, 0, length);
            }
                      
            System.out.println("File is copied successfuly!");

        } catch (IOException exc) {
            System.out.println(exc);
        }
        a.delete();
    }
    
    private void delete(String filePath)
    {
        File a = new File(filePath);
        if(!a.exists())
        {
            System.out.println("The file with that name doesn't exist.");
            return;
        }
        if(a.delete())
        {
        System.out.println("File deleted.");
        }
        else
        {
        System.out.println("Dele failed.");
        }
    }
    
    private void commands()
    {
        System.out.println("LIST: Displays the list of root directories on the system.\n"
                + "INFO: Displays the details about selected file.\n"
                + "CREATE_DIR: Creates a new directory.\n"
                + "RENAME: Starts the process of renaming a file or folder at a specified location.\n"
                + "COPY: Creates a copy of a selected file or folder and places it to a specified location.\n"
                + "MOVE: Moves a file or folder from one location to another.\n"
                + "DELETE: Deletes the selected file or folder."); 
    }
    
    
    
    public void fileManager()
    {
        System.out.println("To see the list of commands, type 'COMMANDS' and press Enter.\n Command:\n ");
                
        try(BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));) {
            
            String command = bufferRead.readLine();
            
            switch(command)
            {
                case "COMMANDS":
                    commands();
                    break;
                case "LIST":
                    System.out.println("Choose an option:\nRoot Directories = roots\nFile list = files");                   
                    String option = bufferRead.readLine();
                    switch(option)
                    {
                        case "roots":
                            getRootDirectories();
                            break;
                        case "files":
                            System.out.print("Enter the file Path:\n");
                            String path2 = bufferRead.readLine();
                            getFiles(path2);
                            break;
                        default:
                            System.out.println("Unrecognised command"); 
                    }
                    break;
                case "INFO":
                    System.out.println("Enter the file path:\n");
                    String path3 = bufferRead.readLine();
                    fileInfo(path3);
                    break;
                case "CREATE_DIR":
                    System.out.println("Enter an absolute path for the new directory:\n");
                    String path4 = bufferRead.readLine();
                    if(!path4.isEmpty() && path4.startsWith("D:\\") || path4.startsWith("C:\\"))
                        {
                         createDir(path4);
                        }
                        else
                        {
                            System.out.println("Unrecognised file path.");
                        }
                    break;
                case "RENAME":                   
                    System.out.println("Enter the path to the target directory or file:\n");
                    String path5 = bufferRead.readLine();
                    System.out.println("Enter the path with the new name included:\n");
                    String path6 = bufferRead.readLine();
                    rename(path5, path6);
                    break;
                case "COPY":
                    System.out.println("Enter the path to the target file of folder:\n");
                    String path7 = bufferRead.readLine();
                    System.out.println("Enter the destination path:\n");
                    String path8 = bufferRead.readLine();
                    copy(path7, path8);
                    break;
                case "MOVE":
                    System.out.println("Enter the path to the target file of folder:\n");
                    String path9 = bufferRead.readLine();
                    System.out.println("Enter the destination path:\n");
                    String path10 = bufferRead.readLine();
                    move(path9, path10);
                    break;
                case "DELETE":
                    System.out.println("Enter the path to the target file or folder:\n");
                    String path11 = bufferRead.readLine();
                    delete(path11);
                    break;
                default:
                    System.out.println("Unrecognised command");
            }
            
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
