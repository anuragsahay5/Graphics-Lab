import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.math.*;

public class Ellipse extends Applet implements ActionListener {

    private int unit = 1;
    private Button ZoomIn = new Button("ZoomIn");
    private Button ZoomOut = new Button("ZoomOut");
    private Button PointInc = new Button("Point+");
    private Button PointDec = new Button("Point-");
    private int gwidth = 0;
    private int point_size = 1;

    public void plotPoint(int x, int y, Color c) {
        int ox = (getX() + getWidth()) / 2;
        int oy = (getY() + getHeight()) / 2;
        Graphics g = getGraphics();
        g.setColor(c);
        g.fillOval(ox + x * gwidth - (point_size * unit), oy - y * gwidth - (point_size * unit), 2 * point_size * unit,
                2 * point_size * unit);
    }

    public void init() {
        this.setSize(new Dimension(1000, 900));
        int a = 255, b = 255, c = 255;
        Color backColor = new Color(a, b, c);
        this.setBackground(backColor);
        add(ZoomIn);
        add(ZoomOut);
        add(PointInc);
        add(PointDec);

        ZoomIn.addActionListener(this);
        ZoomOut.addActionListener(this);
        PointInc.addActionListener(this);
        PointDec.addActionListener(this);
    }

    public void drawEllipse(int rx, int ry,
            int xc, int yc) {
        Color c = Color.black;
        double dx, dy, d1, d2, x, y;
        x = 0;
        y = ry;

        // Initial decision parameter of region 1
        d1 = (ry * ry) - (rx * rx * ry) +
                (0.25 * rx * rx);
        dx = 2 * ry * ry * x;
        dy = 2 * rx * rx * y;

        // For region 1
        while (dx < dy) {

            
            plotPoint((int) x + xc, (int) y + yc, c);
            
            plotPoint((int) -x + xc, (int) y + yc, c);
            
            plotPoint((int) x + xc, (int) -y + yc, c);
            
            plotPoint((int) -x + xc, (int) -y + yc, c);

            // Checking and updating value of
            // decision parameter based on algorithm
            if (d1 < 0) {
                x++;
                dx = dx + (2 * ry * ry);
                d1 = d1 + dx + (ry * ry);
            } else {
                x++;
                y--;
                dx = dx + (2 * ry * ry);
                dy = dy - (2 * rx * rx);
                d1 = d1 + dx - dy + (ry * ry);
            }
        }

        // Decision parameter of region 2
        d2 = ((ry * ry) * ((x + 0.5) * (x + 0.5))) +
                ((rx * rx) * ((y - 1) * (y - 1))) -
                (rx * rx * ry * ry);

        // Plotting points of region 2
        while (y >= 0) {

            
            plotPoint((int) x + xc, (int) y + yc, c);
            
            plotPoint((int) -x + xc, (int) y + yc, c);
            
            plotPoint((int) x + xc, (int) -y + yc, c);
            
            plotPoint((int) -x + xc, (int) -y + yc, c);

            // Checking and updating parameter
            // value based on algorithm
            if (d2 > 0) {
                y--;
                dy = dy - (2 * rx * rx);
                d2 = d2 + (rx * rx) - dy;
            } else {
                y--;
                x++;
                dx = dx + (2 * ry * ry);
                dy = dy - (2 * rx * rx);
                d2 = d2 + dx - dy + (rx * rx);
            }
        }
    }

    public void paint(Graphics g) {
        /*----- Grid Plottiong */
        int length = 600 * unit;
        int width = 4 * unit;
        gwidth = width;
        int looplen = (length / width) * 2;

        int ox = (getX() + getWidth()) / 2;
        int oy = (getY() + getHeight()) / 2;

        int m = 10, n = 255, p = 20;

        Color gridColor = new Color(m, n, p);
        g.setColor(gridColor);

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
        g.setColor(Color.BLACK);
        g.drawLine(ox, oy + length + width, ox, oy - length - width);
        g.drawLine(ox - length - width, oy, ox + length + width, oy);
        drawEllipse(50, 30, 0, 0);
    }

    public void actionPerformed(ActionEvent e) {
        Graphics g = getGraphics();
        g.clearRect(0, 0, getWidth(), getHeight());
        if (e.getSource() == ZoomIn) {
            unit *= 2;
        } else if(e.getSource()==ZoomOut) {
            if (unit != 1) {
                unit /= 2;
            }
        }
        if(e.getSource()==PointInc){
            point_size++;
        }
        else{
            if(point_size>1){
                point_size--;
            }
        }
        paint(g);
    }
}
