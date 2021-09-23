import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author jason
 */
public class JimmyFrame extends JFrame implements KeyListener {

    JimmyPanel gamePanel = new JimmyPanel();

    public JimmyFrame() {

        this.setTitle("°{¤è¶ô");
        this.setSize(500, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(gamePanel);
        this.setFocusable(true);
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("type"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            gamePanel.moveR();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            gamePanel.moveL();//To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("release"); //To change body of generated methods, choose Tools | Templates.
    }

}

