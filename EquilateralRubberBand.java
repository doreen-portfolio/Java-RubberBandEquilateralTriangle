import javax.swing.JFrame;

/**
 * <p>
 * This class is called EquilateralRubberBand. It sets up the frame and the 
 * DrawTrianglePanel object is added as a panel.
 * </p>
 * 
 * @author Doreen Chan-Ying
 * @version 1.0
 */
public class EquilateralRubberBand {
    /**
    * Creates and displays the application frame.
    * @param args Unused
    */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Equilateral Triangle Rubber Band");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new DrawTrianglePanel());
        frame.pack();
        frame.setVisible(true);
    }
}
