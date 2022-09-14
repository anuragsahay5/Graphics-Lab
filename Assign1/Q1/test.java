import java.applet.*;
import java.awt.*;

public class test extends Applet {

  public void init() {
    this.setSize(new Dimension(1000, 900));
  }

  public void paint(Graphics g) {

    int ox = (getX() + getWidth()) / 2;
    int oy = (getY() + getHeight()) / 2;
    g.setColor(Color.black);
    g.drawLine(ox, oy + 525, ox, oy - 475);
    g.drawLine(ox - 475, oy, ox + 525, oy);

  }
}
