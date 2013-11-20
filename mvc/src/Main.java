import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: rmetri
 * Date: 11/17/13
 * Time: 10:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("FrameDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window
        frame.add(new View(), BorderLayout.CENTER);

        //Display the window
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }
}
