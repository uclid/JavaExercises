import java.awt.*;
import javax.swing.*;

/**
 * An applet to test the StopWatchLabel Class
 */

public class StopWatch extends JApplet {

   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public void init() {

      StopWatchLabel watch = new StopWatchLabel();
      watch.setFont( new Font("SansSerif", Font.BOLD, 24) );
      watch.setBackground(Color.BLUE);
      watch.setForeground(Color.RED);
      watch.setOpaque(true);
      getContentPane().add(watch, BorderLayout.CENTER);

   }

}
