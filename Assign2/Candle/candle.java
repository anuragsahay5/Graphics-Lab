import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class candle extends Applet implements ActionListener {

  private int unit = 1;
  public int gwidth = 10;
  public boolean glow = false;

  public double abs(double inp) {
    if (inp < 0) {
      return -inp;
    }
    return inp;
  }

  public void plotPoint(int x, int y, Color c) {
    int ox = (getX() + getWidth()) / 2;
    int oy = (getY() + getHeight()) / 2;
    Graphics g = getGraphics();
    g.setColor(c);
    g.fillOval(ox + x * gwidth - (5 * unit), oy - y * gwidth - (5 * unit), 10 * unit, 10 * unit);
  }

  public void plotLineDDA(double initialX, double initialY, double finalX, double finalY, Color c) {
    double dx = finalX - initialX, dy = finalY - initialY, step;
    if (abs(dy) > abs(dx)) {
      step = dy;
    } else {
      step = dx;
    }

    plotPoint((int) initialX, (int) initialY, c);
    double xinc = dx / step, yinc = dy / step;
    if (initialX < finalX || initialY < finalY) {
      while ((initialX < finalX) || (initialY < finalY)) {
        initialX += xinc;
        initialY += yinc;
        plotPoint((int) initialX, (int) initialY, c);
      }
    } else {
      while ((initialX > finalX)) {
        initialX += xinc;
        initialY += yinc;
        plotPoint((int) initialX, (int) initialY, c);
      }
    }

  }

public void plotmid(){
	plotLineDDA(-1, 1, 1, 1, Color.red);
	plotLineDDA(-2, 2, 2, 2, Color.red);
	plotLineDDA(-2, 3, 2, 3, Color.orange);
	plotLineDDA(-2, 4, 2, 4, Color.orange);
	plotLineDDA(-2, 5, 2, 5, Color.yellow);
	plotLineDDA(-1, 6, 1, 6, Color.yellow);
	plotLineDDA(0, 7, 0, 7, Color.yellow);
}

  public void plotlow() {
    plotLineDDA(-1, 1, 1, 1, Color.red);
    plotLineDDA(-2, 2, 2, 2, Color.red);
    plotLineDDA(-3, 3, 3, 3, Color.red);
    plotLineDDA(-2, 3, 2, 3, Color.orange);
    plotLineDDA(-3, 4, 3, 4, Color.orange);
    plotLineDDA(-3, 5, 3, 5, Color.orange);
    plotLineDDA(-3, 5, 3, 5, Color.yellow);
    plotLineDDA(-2, 6, 2, 6, Color.yellow);
    plotLineDDA(-1, 7, 1, 7, Color.yellow);
    plotLineDDA(0, 8, 0, 8, Color.yellow);

  }

  public void plothigh() {
    plotLineDDA(-1, 1, 1, 1, Color.red);
    plotLineDDA(-2, 2, 2, 2, Color.red);
    plotLineDDA(-3, 3, 3, 3, Color.red);
    plotLineDDA(-4, 4, 4, 4, Color.orange);
    plotLineDDA(-4, 5, 4, 5, Color.orange);
    plotLineDDA(-4, 6, 4, 6, Color.orange);
    plotLineDDA(-4, 7, 4, 7, Color.orange);
    plotLineDDA(-4, 7, 4, 7, Color.yellow);
    plotLineDDA(-3, 8, 3, 8, Color.yellow);
    plotLineDDA(-2, 9, 2, 9, Color.yellow);
    plotLineDDA(-2, 10, 2, 10, Color.yellow);
    plotLineDDA(-1, 11, 1, 11, Color.yellow);
    plotLineDDA(0, 12, 0, 12, Color.yellow);
  }

  public void delay() {
    int dely = 500;
    long start = System.currentTimeMillis();
    while (start >= System.currentTimeMillis() - dely) {

    }
  }

  public void drawbase() {
    plotLineDDA(-4, 0, 4, 0, Color.black);
    plotLineDDA(-4, -20, 4, -20, Color.black);
    plotLineDDA(-4, -20, -4, 0, Color.black);
    plotLineDDA(4, -20, 4, 0, Color.black);
  }

  Timer timer = new Timer();
  
  public void light_candle() {
    TimerTask task = new TimerTask() {
      public void run() {
        if(glow){
        drawbase();
        plotmid();
        delay();
        getGraphics().clearRect(0, 0, getWidth(), getHeight());
        drawbase();
        plotlow();
        delay();
        getGraphics().clearRect(0, 0, getWidth(), getHeight());
        drawbase();
        plothigh();
        delay();
        getGraphics().clearRect(0, 0, getWidth(), getHeight());
        drawbase();
        plotlow();
        delay();
        getGraphics().clearRect(0, 0, getWidth(), getHeight());
      }
      else{
        drawbase();
      }
    }
    };
    
    timer.scheduleAtFixedRate(task, 1000, 1000);

  }

  public void put_out_candle() {
    drawbase();
  }

  public void init() {
    this.setSize(new Dimension(1000, 900));
    int a = 255, b = 255, c = 255;
    Color backColor = new Color(a, b, c);
    this.setBackground(backColor);
    Button b1 = new Button("On/Off");
    add(b1);
    b1.addActionListener(this);
  }

  public void paint(Graphics g) {
    if (glow) {
      light_candle();
    } else {
      put_out_candle();
    }
  }

  public void actionPerformed(ActionEvent e) {
    Graphics g = getGraphics();
    if (glow) {
      glow = false;
      drawbase();
    } else {
      glow = true;
    }
    g.clearRect(0, 0, 1000, 900);
    paint(g);
  }
}
