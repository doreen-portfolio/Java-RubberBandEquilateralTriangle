import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
/**
 * <p>
 * This class, called DrawTrianglePanel, responds to mouse action events.
 * It gets the coordinates of the centre point of an equilateral triangle when
 * the mouse is pressed. At the centre point, a red dot is drawn. As the mouse
 * is dragged, blue dashed lines for the triangle shape are redrawn. Blue
 * squares are drawn at the three vertices of the triangle. As the mouse is
 * dragged, the MouseListener gets the coordinates point at which the mouse
 * cursor is at. The coordinates of that point and the centre point are used
 * to calculate the angle, magnitude, and vector to find the coordinates of the
 * other two vertices. When the mouse is being pressed and dragged, a message
 * is shown at the top left of the window indicating the actions and the 
 * coordinates of the point that the mouse is being dragged to. When the mouse 
 * is released, it shows the message that indicates that it has been released
 * and the coordinates of the point that it has been released at.
 * </p>
 * 
 * @author Doreen Chan-Ying
 * @version 1.0
 */
public class DrawTrianglePanel extends JPanel {
    /** Panel height and width size. */
    private static final int SIZE = 400;
    /** Centre point for the equilateral triangle. */
    private Point c;
    /** Point of the first corner of the triangle that the mouse is dragged 
     * to. */
    private Point p1;
    /** Message displaying mouse drag and release events on top left corner of
     *  the JPanel. */
    private String message; 
    /** Message displaying mouse press event on top left corner of the 
     * JPanel. */
    private String message1;
    /**
    * Constructor: Sets up this panel to listen for mouse events.
    */
    public DrawTrianglePanel() {
        LineListener listener = new LineListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
        setBackground(Color.white);
        setPreferredSize(new Dimension(SIZE, SIZE));
    }
    /**
    * Draws a red circle for the centre point, blue squares for each of the 
    * three points on the triangle, the lines of the triangle as dashed and 
    * blue in colour. 
    * @param page Graphics component to draw on
    */
    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        final int offset = 4;
        final int size = 7;
        final int position = 20;
        if (c != null) { //draws centre red circle 
            page.setColor(Color.red); 
            page.fillOval(c.x - offset, c.y - offset, size, size);
            page.setColor(Color.black);
            page.drawString(message1, position, position);
            page.drawString(message, position, position * 2);
        }
        if (c != null && p1 != null) {
            double m = Math.sqrt(Math.pow((p1.x - c.x), 2) + Math.pow((p1.y 
                - c.y), 2));
            double a1 = Math.atan2((p1.y - c.y), (p1.x - c.x));
            double a2 = a1 + 2 * Math.PI / Integer.parseInt("3");
            double a3 = a2 + 2 * Math.PI / Integer.parseInt("3");
            Point p2 = new Point(c.x + (int) (m * Math.cos(a2)), c.y 
                + (int) (m * Math.sin(a2)));
            Point p3 = new Point(c.x + (int) (m * Math.cos(a3)), c.y  
                + (int) (m * Math.sin(a3)));
            page.setColor(Color.blue);
            page.drawRect(p1.x - offset, p1.y - offset, size, size);
            page.drawRect(p2.x - offset, p2.y - offset, size, size);
            page.drawRect(p3.x - offset, p3.y - offset, size, size);
            Graphics2D g = (Graphics2D) page;
            final float[] dash = {5.0f};
            g.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, 
                BasicStroke.JOIN_MITER, (float) Double.parseDouble("3.0f"), 
                dash, 0.0f));
            page.setColor(Color.blue);
            page.drawLine(p1.x, p1.y, p2.x, p2.y);
            page.drawLine(p2.x, p2.y, p3.x, p3.y);
            page.drawLine(p3.x, p3.y, p1.x, p1.y);
        }
    }
    /**
    * Represents the listener for all mouse events.
    */
    private class LineListener implements MouseListener, MouseMotionListener {
        /**
        * Captures the centre point position at which the mouse button is
        * pressed.
        * @param event Contains position of mouse press
        */
        public void mousePressed(MouseEvent event) {
            c = event.getPoint();
            message1 = "Pressed";
        }

        /**
        * Gets the current position of the mouse as it is dragged and
        * redraws the triangle and vertices.
        * @param event Contains position where mouse is released
        */
        public void mouseDragged(MouseEvent event) {
            p1 = event.getPoint();
            repaint();
            message = "Dragged " + p1.x + ", " + p1.y;
        }
        /** {@inheritDoc} */
        public void mouseClicked(MouseEvent event) { }
        /** {@inheritDoc} */
        public void mouseReleased(MouseEvent event) {
            message = "Released " + p1.x + ", " + p1.y;
            message1 = "";
        }
        /** {@inheritDoc} */
        public void mouseEntered(MouseEvent event) { }
        /** {@inheritDoc} */
        public void mouseExited(MouseEvent event) { }
        /** {@inheritDoc} */
        public void mouseMoved(MouseEvent event) { }
    }
}
