package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

/**
 * Für ueb06 JPanel, in das der Einkaufswagen gezeichnet wird
 * 
 * @author Scheben
 * 
 */
@SuppressWarnings("serial")
public class WillkommensGrafik extends JPanel {
	public WillkommensGrafik() {
		this.setPreferredSize(new Dimension(200, 100));
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.blue);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2.setColor(Color.white);

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// Wagen ohne Räder zeichnen und Position für die Räder
		// zurückgeben
		Point2D.Double[] koordinatenRaeder = drawPath(g2);

		// Erstes (hinteres) Rad
		// Parameter: Linke obere Ecke des umschließenden Rechtecks, Breite,
		// Höhe, Winkel
		g2.fill(new Arc2D.Double(koordinatenRaeder[0].getX(),
				koordinatenRaeder[0].getY() + 10, 10, 10, 0, 360, Arc2D.OPEN));

		// Zweites (vorderes) Rad
		// Parameter: Linke obere Ecke des umschließenden Rechtecks, Breite,
		// Höhe, Winkel
		g2.fill(new Arc2D.Double(koordinatenRaeder[1].getX() - 10,
				koordinatenRaeder[1].getY() + 10, 10, 10, 0, 360, Arc2D.OPEN));

	}


	// Wagen ohne Räder zeichnen und Position für die Räder
	// zurückgeben
	private Point2D.Double[] drawPath(Graphics2D g) {
		g.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_BEVEL));
		Path2D shape = new GeneralPath();
		Double xKoord = 20.0;
		Double yKoord = 20.0;
		shape.moveTo(xKoord, yKoord);
		// Waagerechte Linie oben (Griff)
		shape.lineTo(xKoord + 20, yKoord);
		// Schräge Linie vom Griff aus nach rechts unten (hintere Wand)
		shape.lineTo(xKoord + 50, yKoord + 50);
		// Waagerechte Linie unten (Boden)
		shape.lineTo(xKoord + 100, yKoord + 50);
		// Schräge Linie vom Boden aus nach rechts oben (vordere Wand)
		shape.lineTo(xKoord + 120, yKoord + 25);
		g.draw(shape);

		Point2D.Double[] koordinatenRaeder = new Point2D.Double[2];
		koordinatenRaeder[0] = new Point2D.Double(xKoord + 50, yKoord + 50);
		koordinatenRaeder[1] = new Point2D.Double(xKoord + 100, yKoord + 50);
		return koordinatenRaeder;

	}

}
