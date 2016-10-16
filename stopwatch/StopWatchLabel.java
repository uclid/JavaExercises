import java.awt.event.*;

import javax.swing.*;


/**
 * A custom component that acts as a simple stop-watch.  When the user clicks
 * on it, this componet starts timing.  When the user clicks again,
 * it displays the time between the two clicks.  Clicking a third time
 * starts another timer, etc.  While it is timing, the label just
 * displays the message "Timing....".
 */
public class StopWatchLabel extends JLabel implements MouseListener, ActionListener {

   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private long startTime;   // Start time of timer.
                             //   (Time is measured in milliseconds.)

   private boolean running;  // True when the timer is running.
   
   private Timer timer; 

   /**
    * Constructor sets initial text on the label to
    * "Click to start timer." and sets up a mouse listener
    * so the label can respond to clicks.
    */
   public StopWatchLabel() {
      super("  Click to start timer.  ", JLabel.CENTER);
      addMouseListener(this);
   }
   
   
   /**
    * Tells whether the timer is currently running.
    */
   public boolean isRunning() {
      return running;
   }

   
   /**
    * React when the user presses the mouse by starting
    * or stopping the timer and changing the text that
    * is shown on the label.
    */
   public void mousePressed(MouseEvent evt) {
      if (running == false) {
            // Record the time and start the timer.
         running = true;
         startTime = evt.getWhen();  // Time when mouse was clicked.
         setText("Timing....");
         setText("Running:  0 seconds");
         if (timer == null) {
            timer = new Timer(100, this);
            timer.start();
         }
         else
            timer.restart();
      }
      else {
            // Stop the timer.  Compute the elapsed time since the
            // timer was started and display it.
    	 timer.stop();
         running = false;
         long endTime = evt.getWhen();
         double seconds = (endTime - startTime) / 1000.0;
         setText("Time: " + seconds + " s");
      }
   }
   //It is called when an event from timer is received
   public void actionPerformed(ActionEvent evt) {
	      long time = (System.currentTimeMillis() - startTime) / 1000;
	      long micro = (System.currentTimeMillis() - startTime) / 100 - time*10;
	      setText("Time:  " + time + "."+ micro +" s");
	    }

   public void mouseReleased(MouseEvent evt) { }
   public void mouseClicked(MouseEvent evt) { }
   public void mouseEntered(MouseEvent evt) { }
   public void mouseExited(MouseEvent evt) { }

}
