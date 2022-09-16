import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Circle extends Applet implements ActionListener {

    private int unit = 1;
    private Button ZoomIn = new Button("ZoomIn");
    private Button ZoomOut = new Button("ZoomOut");
    private int gwidth = 0;

    public void plotPoint(int x, int y, Color c) {
        int ox = (getX() + getWidth()) / 2;
        int oy = (getY() + getHeight()) / 2;
        Graphics g = getGraphics();
        g.setColor(c);
        g.fillOval(ox + x * gwidth - (4 * unit), oy - y * gwidth - (4 * unit), 8 * unit, 8 * unit);
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

    public void drawCircle(int x_centre, int y_centre, int r, Color c) {
        int x = r, y = 0;
        if (r > 0) {

            plotPoint(r + x_centre, 0 + y_centre, c);
            plotPoint(-r + x_centre, 0 + y_centre, c);
            plotPoint(0 + x_centre, r + y_centre, c);
            plotPoint(0 + x_centre, -r + y_centre, c);
        }
        int P = 1 - r;
        while (x > y) {
            y++;

            if (P <= 0)
                P = P + 2 * y + 1;

            else {
                x--;
                P = P + 2 * y - 2 * x + 1;
            }

            if (x < y)
                break;

            plotPoint(x + x_centre, y + y_centre, c);
            plotPoint(-x + x_centre, y + y_centre, c);
            plotPoint(x + x_centre, -y + y_centre, c);
            plotPoint(-x + x_centre, -y + y_centre, c);
            if (x != y) {
                plotPoint(y + x_centre, x + y_centre, c);
                plotPoint(-y + x_centre, x + y_centre, c);
                plotPoint(y + x_centre, -x + y_centre, c);
                plotPoint(-y + x_centre, -x + y_centre, c);
            }
        }
    }

    public void paint(Graphics g) {
        /*----- Grid Plottiong */
        int length = 600 * unit;
        int width = 10 * unit;
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

        /* Cirlce Draw */
        drawCircle(0, 6, 21, Color.black);
        drawCircle(10, 0, 10, Color.black);
        drawCircle(-10, 0, 10, Color.black);
        drawCircle(0, 17, 10, Color.black);
        drawCircle(14, 14, 4, Color.black);
        drawCircle(-14, 14, 4, Color.black);
        drawCircle(0, -11, 4, Color.black);

        drawCircle(-12, 21, 2, Color.black);
        drawCircle(12, 21, 2, Color.black);
        drawCircle(-19, 9, 2, Color.black);
        drawCircle(19, 9, 2, Color.black);
        drawCircle(-7, -12, 2, Color.black);
        drawCircle(7, -12, 2, Color.black);

        drawCircle(0, 6, 1, Color.black);

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