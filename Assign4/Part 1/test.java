import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class test extends Applet implements ActionListener {

  private int unit = 1;
  private Button ZoomIn = new Button("ZoomIn");
  private Button ZoomOut = new Button("ZoomOut");
  private int gwidth = 0;

  public double sin_fun(int degree) {
    return Math.sin(Math.toRadians(degree));
  }

  public double cos_fun(int degree) {
    return Math.cos(Math.toRadians(degree));
  }

  public void plotPoint(int x, int y, Color c) {
    int ox = (getX() + getWidth()) / 2;
    int oy = (getY() + getHeight()) / 2;
    Graphics g = getGraphics();
    g.setColor(c);
    g.fillOval(ox + x * gwidth - (4 * unit), oy - y * gwidth - (4 * unit), 8 * unit, 8 * unit);
  }
  /* --- Features --- */

  public void plot_head(int rad, int xc, int yc) {
    drawEllipse(rad, rad, xc, yc, 0);
  }

  public void plot_eye(int rad, int xc, int yc) {
    drawEllipse(rad, rad, xc, yc, 0);
  }

  void plot_beak_small_toothless(int xc, int yc) {
    plotLineDDA(xc - 4, yc + 2, xc, yc + 2);
    plotLineDDA(xc - 4, yc + 2, xc, yc + 4);
    plotLineDDA(xc, yc + 2, xc, yc + 4);

    plotLineDDA(xc - 4, yc - 2, xc, yc - 2);
    plotLineDDA(xc - 4, yc - 2, xc, yc - 4);
    plotLineDDA(xc, yc - 2, xc, yc - 4);
  }

  void plot_beak_big_toothless(int xc, int yc) {
    plotLineDDA(xc - 6, yc + 2, xc, yc + 2);
    plotLineDDA(xc - 6, yc + 2, xc, yc + 6);
    plotLineDDA(xc, yc + 2, xc, yc + 6);

    plotLineDDA(xc - 6, yc - 2, xc, yc - 2);
    plotLineDDA(xc - 6, yc - 2, xc, yc - 6);
    plotLineDDA(xc, yc - 2, xc, yc - 6);
  }

  void plot_beak_small_toothy(int xc, int yc) {
    plot_beak_small_toothless(xc, yc);
    plotLineDDA(xc - 3, yc + 1, xc - 1, yc + 1);
    plotLineDDA(xc - 3, yc - 1, xc - 1, yc - 1);
  }

  void plot_beak_big_toothy(int xc, int yc) {
    plot_beak_big_toothless(xc, yc);
    plotLineDDA(xc - 5, yc + 1, xc - 1, yc + 1);
    plotLineDDA(xc - 5, yc - 1, xc - 1, yc - 1);
  }

  public void plot_ear1(int rad, int xc, int yc) {
    drawEllipse(rad, rad, xc, yc, 0);
  }

  public void plot_ear2(int xc, int yc) {
    plotLineDDA(xc - 2, yc, xc + 2, yc);
    plotLineDDA(xc - 2, yc, xc, yc + 2);
    plotLineDDA(xc, yc + 2, xc + 2, yc);
  }

  public void plot_body_spotless(int xc, int yc) {
    drawEllipse(20, 15, xc, yc, -45);

  }

  public void plot_body_spotted(int xc, int yc) {
    drawEllipse(20, 15, xc, yc, -45);
    plotPoint(xc + 2, yc + 2, Color.black);
    plotPoint(xc - 2, yc + 3, Color.black);
    plotPoint(xc - 4, yc + 6, Color.black);
    plotPoint(xc - 5, yc + 1, Color.black);
    plotPoint(xc + 5, yc + 1, Color.black);
    plotPoint(xc + 3, yc + 7, Color.black);
    plotPoint(xc + 9, yc + 8, Color.black);
    plotPoint(xc + 3, yc - 1, Color.black);
    plotPoint(xc + 7, yc - 7, Color.black);
    plotPoint(xc + 5, yc - 8, Color.black);
    plotPoint(xc - 2, yc - 3, Color.black);
    plotPoint(xc - 4, yc - 5, Color.black);
    plotPoint(xc - 5, yc - 8, Color.black);
  }

  public void plot_body_hairy(int xc, int yc) {
    plot_body_spotless(xc, yc);
    plotLineDDA(xc + 8, yc + 10, xc + 14, yc + 16);
    plotLineDDA(xc + 14, yc + 9, xc + 20, yc + 16);
    plotLineDDA(xc - 15, yc - 15, xc - 10, yc - 10);
    plotLineDDA(xc - 5, yc + 17, xc - 6, yc + 27);
    plotLineDDA(xc, yc + 17, xc + 2, yc + 22);
  }

  public void plot_Arm_long(int xc, int yc) {
    drawEllipse(8, 2, xc, yc, 200);
    drawEllipse(6, 2, xc - 12, yc, 160);
    drawEllipse(2, 2, xc - 20, yc, 0);
    plotLineDDA(xc - 25, yc + 3, xc - 21, yc);
    plotLineDDA(xc - 25, yc, xc - 21, yc);
    plotLineDDA(xc - 25, yc - 3, xc - 21, yc);
  }

  public void plot_Arm_medium(int xc, int yc) {
    drawEllipse(7, 2, xc, yc, 200);
    drawEllipse(5, 2, xc - 11, yc, 160);
    drawEllipse(2, 2, xc - 17, yc, 0);
    plotLineDDA(xc - 22, yc + 3, xc - 18, yc);
    plotLineDDA(xc - 22, yc, xc - 18, yc);
    plotLineDDA(xc - 22, yc - 3, xc - 18, yc);
  }

  public void plot_Arm_short(int xc, int yc) {
    drawEllipse(5, 2, xc, yc, 200);
    drawEllipse(3, 2, xc - 7, yc, 160);
    drawEllipse(2, 2, xc - 11, yc, 0);
    plotLineDDA(xc - 15, yc + 3, xc - 12, yc);
    plotLineDDA(xc - 15, yc, xc - 12, yc);
    plotLineDDA(xc - 15, yc - 3, xc - 12, yc);
  }

  public void plot_Arm_spotted(int xc, int yc) {
    plotPoint(xc - 1, yc + 1, Color.black);
    plotPoint(xc - 2, yc, Color.black);
    plotPoint(xc + 2, yc - 1, Color.black);
  }

  public void plot_arm_hairy(int xc, int yc) {
    plotLineDDA(xc, yc, xc + 2, yc + 6);
    plotLineDDA(xc - 1, yc, xc + 1, yc + 5);
    plotLineDDA(xc + 1, yc - 5, xc + 2, yc - 1);
  }

  public void plot_leg_long(int xc, int yc) {
    drawEllipse(8, 2, xc + 1, yc - 5, 120);
    drawEllipse(6, 2, xc + 1, yc - 16, 70);
    drawEllipse(2, 2, xc, yc - 22, 0);
    plotLineDDA(xc - 4, yc - 22, xc - 1, yc - 22);
    plotLineDDA(xc - 4, yc - 20, xc - 1, yc - 22);
    plotLineDDA(xc - 4, yc - 24, xc - 1, yc - 22);

  }

  public void plot_leg_medium(int xc, int yc) {
    drawEllipse(7, 2, xc + 1, yc - 5, 120);
    drawEllipse(5, 2, xc + 2, yc - 15, 70);
    drawEllipse(2, 2, xc, yc - 22, 0);
    plotLineDDA(xc - 4, yc - 22, xc - 1, yc - 22);
    plotLineDDA(xc - 4, yc - 20, xc - 1, yc - 22);
    plotLineDDA(xc - 4, yc - 24, xc - 1, yc - 22);

  }

  public void plot_leg_short(int xc, int yc) {
    drawEllipse(5, 2, xc + 1, yc - 5, 120);
    drawEllipse(3, 2, xc + 1, yc - 12, 70);
    drawEllipse(2, 2, xc, yc - 17, 0);
    plotLineDDA(xc - 4, yc - 21, xc - 1, yc - 21);
    plotLineDDA(xc - 4, yc - 19, xc - 1, yc - 21);
    plotLineDDA(xc - 4, yc - 23, xc - 1, yc - 21);

  }

  public void plot_tail_1(int xc, int yc) {
    drawEllipse(3, 3, xc, yc, 0);
  }

  public void plot_tail_2(int xc, int yc) {
    plotLineDDA(xc + 19, yc - 3, xc + 23, yc - 3);
    plotLineDDA(xc + 19, yc - 3, xc + 20, yc);
    plotLineDDA(xc + 19, yc, xc + 23, yc - 3);
  }

  public void plot_tail_3(int xc, int yc) {
    plot_tail_1(xc, yc);
    plotLineDDA(xc + 2, yc, xc + 5, yc + 3);
    plotLineDDA(xc + 2, yc, xc + 5, yc);
    plotLineDDA(xc + 2, yc, xc + 5, yc - 3);
  }
  /* ---- END --- */

  /* Plot Animal */

  public void plot_Animal(int fV[], int xc, int yc) {
    plot_head(9, xc - 21, yc + 21);
    plot_eye(2, xc - 22, yc + 24);

    switch (fV[2]) {
      case 1:
        plot_beak_big_toothy(xc - 29, yc + 21);
        break;
      case 2:
        plot_beak_big_toothless(xc - 29, yc + 21);
        break;
      case 3:
        plot_beak_small_toothy(xc - 29, yc + 21);
        break;
      case 4:
        plot_beak_small_toothless(xc - 29, yc + 21);
    }
    switch (fV[3]) {
      case 1:
        plot_ear1(3, xc - 17, yc + 31);
        plot_ear1(3, xc - 14, yc + 31);
        break;
      case 2:
        plot_ear2(xc - 19, yc + 31);
        plot_ear2(xc - 18, yc + 31);
        break;

    }
    switch (fV[4]) {
      case 1:
        plot_body_hairy(xc, yc);
        plot_body_spotted(xc, yc);
        break;
      case 2:
        plot_body_spotted(xc, yc);
        break;
      case 3:
        plot_body_hairy(xc, yc);
        break;
      case 4:
        plot_body_spotless(xc, yc);
        break;
    }
    switch (fV[5]) {
      case 1:
        plot_Arm_long(xc - 21, yc - 3);
        plot_Arm_long(xc - 20, yc + 3);
        plot_leg_long(xc - 3, yc - 17);
        plot_leg_long(xc + 5, yc - 19);
        break;
      case 2:
        plot_Arm_medium(xc - 21, yc - 3);
        plot_Arm_medium(xc - 20, yc + 3);
        plot_leg_medium(xc - 3, yc - 17);
        plot_leg_medium(xc + 5, yc - 19);
        break;
      case 3:
        plot_Arm_medium(xc - 21, yc - 3);
        plot_Arm_medium(xc - 20, yc + 3);
        plot_leg_medium(xc - 3, yc - 17);
        plot_leg_medium(xc + 5, yc - 19);
        break;
    }
    switch (fV[6]) {
      case 1:
        plot_tail_1(xc + 19, yc - 4);
        break;
      case 2:
        plot_tail_2(xc - 2, yc - 3);
        break;
      case 3:
        plot_tail_3(xc + 19, yc - 4);
        break;
    }

    // plot_tail_3(xc + 19, yc - 5);

  }

  public double abs(double inp) {
    if (inp < 0) {
      return -inp;
    }
    return inp;
  }

  public void drawEllipse(int rx, int ry, int xc, int yc, int angle) {

    Color c = Color.black;
    double dx, dy, p1, p2, x, y;
    x = 0;
    y = ry;

    // decision parameter of region 1
    p1 = (ry * ry) - (rx * rx * ry) + (0.25 * rx * rx);
    dx = 2 * ry * ry * x;
    dy = 2 * rx * rx * y;

    // region 1 Plotting
    while (dx <= dy) {

      plotPoint((int) (x * cos_fun(angle) - y * sin_fun(angle)) + xc,
          (int) (x * sin_fun(angle) + y * cos_fun(angle)) + yc, c);

      // plotPoint((int) -x + xc, (int) y + yc, c);
      plotPoint((int) (-x * cos_fun(angle) - y * sin_fun(angle)) + xc,
          (int) (-x * sin_fun(angle) + y * cos_fun(angle)) + yc, c);

      // plotPoint((int) x + xc, (int) -y + yc, c);
      plotPoint((int) (x * cos_fun(angle) + y * sin_fun(angle)) + xc,
          (int) (x * sin_fun(angle) - y * cos_fun(angle)) + yc, c);

      // plotPoint((int) -x + xc, (int) -y + yc, c);
      plotPoint((int) (-x * cos_fun(angle) + y * sin_fun(angle)) + xc,
          (int) (-x * sin_fun(angle) - y * cos_fun(angle)) + yc, c);

      if (p1 < 0) {
        x++;
        dx += (2 * ry * ry);
        p1 += dx + (ry * ry);
      } else {
        x++;
        y--;
        dx += (2 * ry * ry);
        dy -= (2 * rx * rx);
        p1 += dx - dy + (ry * ry);
      }
    }

    // region 2 decision making parameter
    p2 = ((ry * ry) * ((x + 0.5) * (x + 0.5))) +
        ((rx * rx) * ((y - 1) * (y - 1))) -
        (rx * rx * ry * ry);

    // region 2 plotting
    while (y >= 0) {

      // plotPoint((int) x + xc, (int) y + yc, c);

      // plotPoint((int) -x + xc, (int) y + yc, c);

      // plotPoint((int) x + xc, (int) -y + yc, c);

      // plotPoint((int) -x + xc, (int) -y + yc, c);

      plotPoint((int) (x * cos_fun(angle) - y * sin_fun(angle)) + xc,
          (int) (x * sin_fun(angle) + y * cos_fun(angle)) + yc, c);

      // plotPoint((int) -x + xc, (int) y + yc, c);
      plotPoint((int) (-x * cos_fun(angle) - y * sin_fun(angle)) + xc,
          (int) (-x * sin_fun(angle) + y * cos_fun(angle)) + yc, c);

      // plotPoint((int) x + xc, (int) -y + yc, c);
      plotPoint((int) (x * cos_fun(angle) + y * sin_fun(angle)) + xc,
          (int) (x * sin_fun(angle) - y * cos_fun(angle)) + yc, c);

      // plotPoint((int) -x + xc, (int) -y + yc, c);
      plotPoint((int) (-x * cos_fun(angle) + y * sin_fun(angle)) + xc,
          (int) (-x * sin_fun(angle) - y * cos_fun(angle)) + yc, c);

      if (p2 > 0) {
        y--;
        dy -= (2 * rx * rx);
        p2 += (rx * rx) - dy;
      } else {
        y--;
        x++;
        dx += (2 * ry * ry);
        dy -= (2 * rx * rx);
        p2 += dx - dy + (rx * rx);
      }
    }
  }

  public void plotLineDDA(double initialX, double initialY, double finalX, double finalY) {
    double dx = finalX - initialX, dy = finalY - initialY, step;
    if (abs(dy) > abs(dx)) {
      step = dy;
    } else {
      step = dx;
    }

    plotPoint((int) initialX, (int) initialY, Color.black);
    double xinc = dx / step, yinc = dy / step;
    if (initialX < finalX) {
      while ((initialX < finalX)) {
        initialX += xinc;
        initialY += yinc;
        plotPoint((int) initialX, (int) initialY, Color.black);
      }
    } else {
      while ((initialX > finalX)) {
        initialX += xinc;
        initialY += yinc;
        plotPoint((int) initialX, (int) initialY, Color.black);
      }
    }

  }

  public void init() {
    this.setSize(new Dimension(1000, 900));
    int a = 255, b = 255, c = 255;
    Color backColor = new Color(a, b, c);
    this.setBackground(backColor);
    add(ZoomIn);
    add(ZoomOut);

    ZoomIn.addActionListener(this);
    ZoomOut.addActionListener(this);
  }

  public void paint(Graphics g) {
    /*----- Grid Plottiong */
    int length = 800 * unit;
    int width = 5 * unit;
    gwidth = width;
    int looplen = (length / width) * 2;

    int ox = (getX() + getWidth()) / 2;
    int oy = (getY() + getHeight()) / 2;

    int m = 10, n = 255, p = 20;

    Color gridColor = new Color(m, n, p);
    g.setColor(gridColor);

    int plotnum = -(length / width);

    for (int i = 0; i <= looplen; i++) {
      /*------ Plotting Grid Lines ------*/
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

    /*
     * ------ Plooting co-ordinates ------
     * for (int i = 0; i <= looplen; i++) {
     * g.setColor(Color.black);
     * g.drawString(String.valueOf(plotnum), ox - length + width * i, oy);
     * g.drawString(String.valueOf(-plotnum), ox, oy - length + width * i);
     * plotnum++;
     * }
     */

    /*------ Plooting Axes Lines ------*/
    g.setColor(Color.black);
    g.drawLine(ox, oy + length + width, ox, oy - length - width);
    g.drawLine(ox - length - width, oy, ox + length + width, oy);

    /* ------ Origin Plot ------ */
    g.setColor(Color.blue);
    g.fillOval(ox - (2 * unit), oy - (2 * unit), 4 * unit, 4 * unit);
    /* ------- Animal Plot ------ */
    int[] fv1 = { 1, 1, 1, 1, 1, 1, 1 };
    plot_Animal(fv1, 0, 0);
    //{1,1,4,2,4,3,3}
    // int[] fv2 = { 1, 1, 4, 2, 3, 2, 3 };
    // int[] fv3 = { 1, 1, 1, 2, 1, 2, 3 };
    // plot_Animal(fv2, 0, 0);
    // plot_Animal(fv3, 110, 0);
  }

  public void actionPerformed(ActionEvent e) {
    Graphics g = getGraphics();
    g.clearRect(0, 0, getWidth(), getHeight());
    if (e.getSource() == ZoomIn)
      unit *= 2;
    else if (unit != 1)
      unit /= 2;
    paint(g);
  }
}
