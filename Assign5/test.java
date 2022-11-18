import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math.*;

import javax.sound.sampled.Clip;

public class test extends Applet implements ActionListener {

  private int unit = 1;
  private int gwidth = 0;
  private int showclip = 0;
  private Button Clip = new Button("Clip Selection");
  static int x_min = -100, x_max = 100, y_min = -100, y_max = 100;

  public int abs(int inp) {
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

  public void plotLineDDA(int x0, int y0, int x1, int y1, Color c) {
    int dx = x1 - x0;
    int dy = y1 - y0;

    int step;
    if (abs(dx) > abs(dy))
      step = abs(dx);
    else
      step = abs(dy);

    float x_incr = (float) dx / step;
    float y_incr = (float) dy / step;

    float x = x0;
    float y = y0;

    for (int i = 0; i < step; i++) {

      plotPoint((int) x, (int) y, c);
      x += x_incr;
      y += y_incr;
    }

  }

  public int computeCode(double x, double y) {
    // initialized as being inside
    int code = 0;

    if (x < x_min) // to the left of rectangle
      code |= 1;
    else if (x > x_max) // to the right of rectangle
      code |= 2;
    if (y < y_min) // below the rectangle
      code |= 4;
    else if (y > y_max) // above the rectangle
      code |= 8;

    return code;
  }

  public void drawClip(double x1, double y1,
      double x2, double y2) {
    // Compute region codes for P1, P2
    double ix1 = x1, iy1 = y1, ix2 = x2, iy2 = y2;
    int code1 = computeCode(x1, y1);
    int code2 = computeCode(x2, y2);

    // Initialize line as outside the rectangular window
    boolean accept = false;

    while (true) {
      if ((code1 == 0) && (code2 == 0)) {
        // If both endpoints lie within rectangle
        accept = true;
        break;
      } else if ((code1 & code2) != 0) {
        // If both endpoints are outside rectangle,
        // in same region
        break;
      } else {

        int code_out;
        double x = 0, y = 0;

        if (code1 != 0)
          code_out = code1;
        else
          code_out = code2;

        if ((code_out & 8) != 0) {
          // point is above the clip rectangle
          x = x1
              + (x2 - x1) * (y_max - y1)
                  / (y2 - y1);
          y = y_max;
        } else if ((code_out & 4) != 0) {
          // point is below the rectangle
          x = x1
              + (x2 - x1) * (y_min - y1)
                  / (y2 - y1);
          y = y_min;
        } else if ((code_out & 2) != 0) {
          // point is to the right of rectangle
          y = y1
              + (y2 - y1) * (x_max - x1)
                  / (x2 - x1);
          x = x_max;
        } else if ((code_out & 1) != 0) {
          // point is to the left of rectangle
          y = y1
              + (y2 - y1) * (x_min - x1)
                  / (x2 - x1);
          x = x_min;
        }

        // Now intersection point x, y is found
        // We replace point outside rectangle
        // by intersection point
        if (code_out == code1) {
          x1 = x;
          y1 = y;
          code1 = computeCode(x1, y1);
        } else {
          x2 = x;
          y2 = y;
          code2 = computeCode(x2, y2);
        }
      }
    }
    if (accept) {
      if (showclip == 1) {
        plotLineDDA((int) x1, (int) y1, (int) x2, (int) y2, Color.blue);
      } else {
        plotLineDDA((int) ix1, (int) iy1, (int) ix2, (int) iy2, Color.red);
      }
    } else {
      if (showclip == 0) {
        plotLineDDA((int) ix1, (int) iy1, (int) ix2, (int) iy2, Color.red);
      }
    }
  }

  public void init() {
    this.setSize(new Dimension(1000, 900));
    int a = 255, b = 255, c = 255;
    Color backColor = new Color(a, b, c);
    this.setBackground(backColor);
    add(Clip);
    Clip.addActionListener(this);
  }

  public void paint(Graphics g) {
    /*----- Grid Plottiong */
    int length = 1000 * unit;
    int width = 2 * unit;
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

    // for (int i = 0; i <= looplen; i++) {
    // g.setColor(Color.black);
    // g.drawString(String.valueOf(plotnum), ox - length + width * i, oy);
    // g.drawString(String.valueOf(-plotnum), ox, oy - length + width * i);
    // plotnum++;
    // }
    g.setColor(Color.black);
    g.drawLine(ox, oy + length + width, ox, oy - length - width);
    g.drawLine(ox - length - width, oy, ox + length + width, oy);

    g.setColor(Color.red);
    g.fillOval(ox - (2 * unit), oy - (2 * unit), 4 * unit, 4 * unit);

    /* Window Drawing */
    int xmin = -100, xmax = 100, ymin = -100, ymax = 100;
    for (int i = ymin - 2; i <= ymax + 2; i++) {
      plotPoint(xmin, i, Color.black);
      plotPoint(xmax, i, Color.black);
    }
    plotLineDDA(xmin - 2, ymin, xmax + 2, ymin, Color.black);
    plotLineDDA(xmin - 2, ymax, xmax + 2, ymax, Color.black);

    
    // drawClip(-70, -40, 130, 90);
    // drawClip(0, 0, 30, 190);
    drawClip(0, 120, -120, 0);
    drawClip(xmin, xmax, ymin, ymax);
    drawClip(0, 120, 120, 0);
    drawClip(-120, 0, 0, -120);
    drawClip(0, -120, 120, 0);

  }

  public void actionPerformed(ActionEvent e) {
    Graphics g = getGraphics();
    g.clearRect(0, 0, getWidth(), getHeight());
    if (e.getSource() == Clip)
      if (showclip == 1) {
        showclip = 0;
      } else {
        showclip = 1;
      }
    paint(g);
  }

}