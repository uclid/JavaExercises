import java.io.File;
import java.util.Scanner;

/**
 * This program will list all the files in a directory 
 * and all its sub-directories, to any level of nesting
 */
public class RecursiveDirectory {


   public static void main(String[] args) {

      String directoryName;  // Directory name entered by the user.
      File directory;        // File object referring to the directory.
      Scanner scanner;       // For reading a line of input from the user.

      scanner = new Scanner(System.in);  // scanner reads from standard input.

      System.out.print("Enter a directory name: ");
      directoryName = scanner.nextLine().trim();
      directory = new File(directoryName);

      if (directory.isDirectory() == false) {
         if (directory.exists() == false)
            System.out.println("There is no such directory!");
         else 
            System.out.println("That file is not a directory.");
      }
      else {
            // Display the files of the directory recursively
         listDirectoryContents(directory,"");
      }
      scanner.close();

   } // end main()


   /**
    * It is a recursive subroutine that lists
    * contents of a directory and its sub-directories 
    * to any level of nesting.
    * @param dir the directory whose contents should be listed  
    * @param indent a string for indentation for each new level
    * of directory
    */
   private static void listDirectoryContents(File dir, String indent) {
      String[] files;  // names of files in the directory.
      System.out.println(indent + "Directory \"" + dir.getName() + "\":");
      indent += "   ";  // Increase the indentation for new level of recursion
      files = dir.list();
      for (int i = 0; i < files.length; i++) {
            // If it is a  directory, recursively list its contents
         File f = new File(dir, files[i]);
         if (f.isDirectory())
            listDirectoryContents(f, indent);
         else
            System.out.println(indent + files[i]);
      }
   } // end listContents()


} // end class RecursiveDirectory
