import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math.*;

public class test extends Applet{

  private int unit = 1;
  private int gwidth = 0;

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
    g.fillOval(ox + x * gwidth - (2 * unit), oy - y * gwidth - (2 * unit), 4 * unit, 4 * unit);
  }

  public void plotLineDDA(double initialX, double initialY, double finalX, double finalY) {
    double dx = finalX - initialX, dy = finalY - initialY, step;
    if (abs(dy) > abs(dx)) {
      step = dy;
    } else {
      step = dx;
    }


    plotPoint((int)initialX, (int)initialY, Color.red);
    double xinc = dx / step, yinc = dy / step;
    if(initialX<finalX){
      while ((initialX < finalX)) {
        initialX += xinc;
        initialY += yinc;
        plotPoint((int)initialX, (int)initialY, Color.red);
      }
    }
    else{
      while ((initialX > finalX)) {
        initialX += xinc;
        initialY += yinc;
        plotPoint((int)initialX, (int)initialY, Color.red);
      }
    }
    

  }

  public void init() {
    this.setSize(new Dimension(1000, 900));
    int a = 255, b = 255, c = 255;
    Color backColor = new Color(a, b, c);
    this.setBackground(backColor);
  }

  public void paint(Graphics g) {
    /*----- Grid Plottiong */
    int length = 600 * unit;
    int width = 20 * unit;
    gwidth = width;
    int looplen = (length / width) * 2;

    int ox = (getX() + getWidth()) / 2;
    int oy = (getY() + getHeight()) / 2;

    int m = 10, n = 255, p = 20;

    Color gridColor = new Color(m, n, p);
    g.setColor(gridColor);

    int plotnum = -(length / width);

    for (int i = 0; i <= looplen; i++) {
      g.drawLine(
          ox - length + width * i,
          oy + length + width,
          ox - length + width * i,
          oy - (length + width));
      g.drawLine(
          ox - (length + width),
          oy - length + width * i,
          ox + length + width,
          oy - length + width * i);
    }

    for (int i = 0; i <= looplen; i++) {
      g.setColor(Color.black);
      g.drawString(String.valueOf(plotnum), ox - length + width * i, oy);
      g.drawString(String.valueOf(-plotnum), ox, oy - length + width * i);
      plotnum++;
    }
    g.setColor(Color.black);
    g.drawLine(ox, oy + length + width, ox, oy - length - width);
    g.drawLine(ox - length - width, oy, ox + length + width, oy);

    g.setColor(Color.red);
    g.fillOval(ox - (2 * unit), oy - (2 * unit), 4 * unit, 4 * unit);

    /*------Point Plot----*/

    plotLineDDA(10, 10, 30, 20);
  }

}
