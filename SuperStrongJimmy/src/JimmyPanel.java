import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author jason
 */
public class JimmyPanel extends JPanel {

    static int x = 200;
    static int y = 0;
    static int running = 0;
    static boolean start = false;
    static int gameScore = 0;
    static int killX = 200;
    static int killY = 0;
    static boolean dead = false;

    public static void setStart(boolean start) {
        JimmyPanel.start = start;
    }

    public JimmyPanel() {

        this.firstPaint();
    }

    public void firstPaint() {
        // super.paint(g);
        Label endGame = new Label("             你被砸死了!");
        endGame.setBounds(100, 100, 200, 80);
        endGame.setBackground(Color.red);
        endGame.setForeground(Color.WHITE);
        endGame.setVisible(false);
        this.add(endGame);
        this.setBackground(Color.BLACK);
        Button restart = new Button("再來一局");
        restart.setBounds(150, 200, 100, 50);
        restart.setVisible(false);
        Button start = new Button("start game");
        this.add(restart);
        start.setBounds(150, 500, 100, 50);
        Label title = new Label("   閃方塊");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("", Font.PLAIN, 40));
        title.setBounds(100, 100, 300, 200);
        this.add(title);
        this.add(start);
        this.setLayout(null);
        Label score = new Label("score:" + gameScore);
        JPanel outside = new JPanel();
        outside.setBounds(400, 0, 100, 600);
        outside.setBackground(Color.DARK_GRAY);
        this.add(outside);
        title.setFont(new Font("", Font.PLAIN, 40));
        score.setVisible(false);
        score.setBounds(400, 0, 100, 100);
        score.setForeground(Color.BLACK);
        score.setBackground(Color.WHITE);
        this.add(score);
        //this.addKeyListener(this);
        // this.getContentPane().setBackground(Color.BLACK);
        this.setVisible(true);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                title.setVisible(false);
                start.setVisible(false);
                score.setVisible(true);
                outside.setBounds(400, 100, 100, 500);
                running = 1;
            }
        });
        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                x = 200;
                y = 510;
                dead = false;
                killX = 200;
                killY = 0;
                running = 1;
                gameScore=0;
                restart.setVisible(false);
                endGame.setVisible(false);
            }
        });
        new Thread(() -> {
            while (true) {
                score.setText("score:" + Integer.toString(gameScore / 5));
                if (((x - killX <= 50) && (x - killX >= 0) || (killX - x <= 50) && (killX - x >= 0)) && killY >= 455) {
                    System.out.println("" + x);
                    System.out.println("" + killX);
                    endGame.setVisible(true);
                    dead = true;

                }
                if (killY >= 515) {
                    System.out.println("" + x);
                    System.out.println("" + killX);
                    //inputBrick(x);
                    killY = 0;
                    //x=200;
                    killX = (int) (Math.random() * 350);
                    //break;
                }
                if (dead == true) {

                    restart.setVisible(true);
                }

                //break;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    System.out.println("gg");
                }

                repaint();

            }

        }).start();

    }

    /* public void inputBrick(int x) {
        JPanel down = new JPanel();
        down.setBounds(x, 515, 50, 50);
        down.setBackground(Color.red);
        this.add(down);
    }*/
    public void moveR() {
        if (x < 350) {
            x += 50;
        }
    }

    public void moveL() {
        if (x > 0) {
            x -= 50;
        }
    }

    @Override

    public void paint(Graphics g) {
        super.paint(g);
        if (running == 1) {
            if (dead == false) {
                g.setColor(Color.WHITE);
                g.drawRect(killX, killY, 50, 50);
                g.setColor(Color.ORANGE);
                g.fillRect(killX, killY, 50, 50);
                killY += 5 * (gameScore / 1000) + 5;
                g.setColor(Color.WHITE);
            }
            if (dead == true) {
                g.setColor(Color.WHITE);
                g.drawOval(x, 510, 40, 40);
                g.drawLine(x + 8, 522, x + 13, 528);
                g.drawLine(x + 9, 528, x + 12, 522);
                g.drawLine(x + 24, 522, x + 29, 528);
                g.drawLine(x + 25, 528, x + 28, 522);
                g.drawOval(x + 15, 535, 10, 10);
            } else {
                g.drawOval(x, 510, 40, 40);
                g.drawOval(x + 10, 525, 5, 5);
                g.drawOval(x + 30, 525, 5, 5);
                g.drawLine(x + 15, 540, x + 25, 540);
                gameScore++;
            }

        }

    }

}
