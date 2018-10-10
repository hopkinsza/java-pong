import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class PongPanel extends JPanel {
	
	private static final int width = 800, height = 600;
	
	// What?!
	private static final long serialVersionUID = 1L;
	
	private Timer timer = new Timer();
	private static Ball ball = new Ball(30, 30, 16, Color.black);
	private static Paddle p1 = new Paddle(10, 200, 10, 100, Color.red);
	private static Paddle p2 = new Paddle(width - 40, 200, 10, 100, Color.blue);
	
	public static void reset() {
		ball.setCenterX(30);
		ball.setCenterY(30);
		ball.velocity = 2;
		ball.direction = 45;
	}
	
	// constructor for canvas
	public PongPanel(Color color) {
		this.setBackground(color);
		
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				// ball movement & collision
				// side hit: 180 -, other: 360 -
				switch(ball.hit(width, height)) {
				case Ball.HitTop:
		            System.out.println("hit top");
		            ball.setCenterY(0 + ball.getRadius());
					if(ball.isGoingLeft()) {
						ball.setDirection(135);
					} else {
						ball.setDirection(45);
					}
					break;
				case Ball.HitBot:
					System.out.println("hit bot");
					ball.setCenterY(height - ball.getRadius());
					if(ball.isGoingLeft()) {
						ball.setDirection(225);
					} else {
						ball.setDirection(315);
					};
					break;
				case Ball.HitLeft:
					System.out.println("hit left");
					ball.setCenterX(0 + ball.getRadius());
					if(ball.isGoingUp()) {
						ball.setDirection(315);
					} else {
						ball.setDirection(45);
					}
					ball.velocity = 0;
					break;
				case Ball.HitRight:
					System.out.println("hit right");
					ball.setCenterX(width - ball.getRadius());
					if(ball.isGoingUp()) {
						ball.setDirection(225);
					} else {
						ball.setDirection(135);
					}
					ball.velocity = 0;
					break;
				}
				// ball.setDirection(180 - ball.direction);
				// ball.setDirection(180 + (360 - ball.direction));
				
				/*
				switch(ball.hit(width, height)) {
				case Ball.HitTop:
		            System.out.println("hit top");
		            ball.turn(180 - 2 * (360 - (ball.direction)));
					break;
				case Ball.HitBot:
					System.out.println("hit bot");
					ball.turn(180 - 2 * (360 - (ball.direction)));
					break;
				case Ball.HitLeft:
					System.out.println("hit left");
					ball.turn(180 + 2 * (360 - (ball.direction)));
					break;
				case Ball.HitRight:
					System.out.println("hit right");
					ball.turn(180 + 2 * (360 - (ball.direction)));
					break;
				}*/
				
				ball.move();
				
				// paddle collision
				if(ball.getCenterX() - ball.getRadius() == p1.getX() + p1.getWidth()) {
					if(ball.getCenterY() < p1.getCenterY()) {
						ball.setDirection(315);
					} else {
						ball.setDirection(45);
					}
					ball.setCenterX(20 + ball.getRadius());
				}
				if(ball.getCenterX() + ball.getRadius() == p2.getX()) {
					if(ball.getCenterY() < p2.getCenterY()) {
						ball.setDirection(225);
					} else {
						ball.setDirection(135);
					}
					ball.setCenterX(width - 40 - ball.getRadius());
				}
				
				repaint();
			}
		}, 0, 10); // time delay in millis: 20 mils = 50 fps
	}
	
	// CHANGE THE FOLLOWING TO CHANGE RUNTIME:
	// painting
	public void paintComponent(Graphics g) {
		// get Graphics g and setcolor
		super.paintComponent(g);
		g.setColor(new Color(0, 200, 100));
		
		// draw
		ball.fill(g);
		p1.fill(g);
		p2.fill(g);
	}
	
	// main
	public static void main(String[] args) {
		// create frame
		JFrame gui = new JFrame();
		gui.setTitle("Ping pong my dinga linga long dong");
		gui.setSize(width, height);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gui.setFocusable(true);
		
		gui.addKeyListener(new KeyAdapter() {
		      @Override
		      public void keyPressed(KeyEvent e) {
		          switch (e.getKeyCode()) {
		              case KeyEvent.VK_W:
		            	  System.out.print("w");
		            	  p1.move(-10);
		                  break;
		              case KeyEvent.VK_S:
		            	  System.out.print("s");
		            	  p1.move(10);
		                  break;
		              case KeyEvent.VK_UP:
		            	  System.out.print("up");
		            	  p2.move(-10);
		            	  break;
		                case KeyEvent.VK_DOWN:
		                	System.out.print("down");
		                	p2.move(10);
		                	break;
		                case KeyEvent.VK_R:
		                	System.out.print("reset");
		                	reset();
		                	break;
		            }
		        }
		    });
		
		// create panel
		Color c = new Color(0, 200, 100);
		PongPanel panel = new PongPanel(c);
		Container pane = gui.getContentPane();
		pane.add(panel);
		
		pane.setFocusable(false);
		
		gui.setVisible(true);
		gui.requestFocus();
	}
}  