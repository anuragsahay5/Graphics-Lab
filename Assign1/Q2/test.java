import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class test extends Applet implements ActionListener {

  private int unit = 1;
  private Button ZoomIn = new Button("ZoomIn");
  private Button ZoomOut = new Button("ZoomOut");
  private int gwidth = 0;

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
    int length = 400 * unit;
    int width = 25 * unit;
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

    /* ------ Plooting co-ordinates ------ */
    for (int i = 0; i <= looplen; i++) {
      g.setColor(Color.black);
      g.drawString(String.valueOf(plotnum), ox - length + width * i, oy);
      g.drawString(String.valueOf(-plotnum), ox, oy - length + width * i);
      plotnum++;
    }

    /*------ Plooting Axes Lines ------*/
    g.setColor(Color.black);
    g.drawLine(ox, oy + length + width, ox, oy - length - width);
    g.drawLine(ox - length - width, oy, ox + length + width, oy);

    /* ------ Origin Plot ------ */
    g.setColor(Color.blue);
    g.fillOval(ox - (4 * unit), oy - (4 * unit), 8 * unit, 8 * unit);

    /*------ Plotting point ------*/
    g.setColor(Color.red);
    g.fillOval(ox + 4 * gwidth - (8*unit), oy - 4 * gwidth - (8*unit), 16*unit, 16*unit);

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
