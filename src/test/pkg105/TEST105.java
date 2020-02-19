
package test.pkg105;

import java.io.*;
import java.util.ArrayList;

public class TEST105 {
    
    public static int SELECTED_ROW;
    public static ArrayList<Student> MYLIST = new ArrayList<>();
    public static File MAINFILE = new File("D:\\MAIN FILE.obj");

    public static void main(String[] args) {
        
        MainFrame mainframe = new MainFrame();
        mainframe.setVisible(true);
        mainframe.setDefaultCloseOperation(3);
    }

    
}
