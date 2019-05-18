import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
/**
 * Beschreiben Sie hier die Klasse Frame_work.
 *
 * @author (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Frame_work
{
    private JFrame frame;
    private Zeichenflaeche zeichenflaeche;
    private Graphics2D graphic;
    private Color hintergrundfarbe;
    private Image frameImage;
    private ArrayList<Object> tiles;
    private Map<Object, ShapeMitFarbe> tileToShape;
    private Engine engine;

    /**
     * Erzeuge eine Leinwand.
     *
     * @param titel
     *            Titel, der im Rahmen der Leinwand angezeigt wird
     * @param breite
     *            die gewünschte Breite der Leinwand
     * @param hoehe
     *            die gewünschte Höhe der Leinwand
     * @param grundfarbe
     *            die Hintergrundfarbe der Leinwand
     */
    public Frame_work(int width, int hight, Engine engine) {
        frame = new JFrame();
        this.engine = engine;
        frame.addKeyListener(new Keylistener());
        tiles = new ArrayList<Object>();
        zeichenflaeche = new Zeichenflaeche();
        frame.setContentPane(zeichenflaeche);
        frame.setTitle("Snake's adventure");
        frame.setLocation(0, 0);
        zeichenflaeche.setPreferredSize(new Dimension(width, hight));
        hintergrundfarbe = Color.black;
        frame.pack();
        tileToShape = new HashMap<Object, ShapeMitFarbe>();
        setzeSichtbarkeit();
    }

    /**
     * Setze, ob diese Leinwand sichtbar sein soll oder nicht. Wenn die Leinwand
     * sichtbar gemacht wird, wird ihr frame in den Vordergrund geholt. Diese
     * Operation kann auch benutzt werden, um ein bereits sichtbares
     * Leinwandframe in den Vordergrund (vor andere frame) zu holen.
     *
     * @param sichtbar
     *            boolean für die gewünschte Sichtbarkeit: true für sichtbar,
     *            false für nicht sichtbar.
     */
    public void setzeSichtbarkeit() {
        if (graphic == null) {
            // erstmaliger Aufruf: erzeuge das Bildschirm-Image und fülle
            // es mit der Hintergrundfarbe
            Dimension size = zeichenflaeche.getSize();
            frameImage = zeichenflaeche.createImage(size.width, size.height);
            graphic = (Graphics2D) frameImage.getGraphics();
            graphic.setColor(hintergrundfarbe);
            graphic.fillRect(0, 0, size.width, size.height);
            graphic.setColor(Color.black);
        }
        frame.setVisible(true);
    }

    /**
     * Zeichne für das gegebene Figur-Objekt eine Java-Figur (einen Shape) auf
     * die Leinwand.
     *
     * @param figur
     *            das Figur-Objekt, für das ein Shape gezeichnet werden soll
     * @param farbe
     *            die Farbe der Figur
     * @param shape
     *            ein Objekt der Klasse Shape, das tatsächlich gezeichnet wird
     */
    // Achtung: dieser Weg zur Verwaltung der Figuren-Objekte ist ein wenig
    // rückwärts ausgerichtet. Dies wurde sorgfältig entwickelt, um die sichtbaren
    // Figuren-Schnittstellen in diesem Projekt zu Lehrzwecken sauber und einfach
    // zu halten.

    public void zeichne(Object figur, String farbe, Shape shape) {
        tiles.remove(figur); // entfernen, falls schon eingetragen
        tiles.add(figur); // am Ende hinzufügen
        tileToShape.put(figur, new ShapeMitFarbe(shape, farbe));
    }

    /**
     * Entferne die gegebene Figur von der Leinwand.
     *
     * @param figur
     *            die Figur, deren Shape entfernt werden soll
     */
    public void entferne(Object tile) {
        tiles.remove(tile); // entfernen, falls schon eingetragen
        tileToShape.remove(tile);
    }

    /**
     * Setze die Zeichenfarbe der Leinwand.
     *
     * @param farbname
     *            der Name der neuen Zeichenfarbe.
     */
    public void setzeZeichenfarbe(String farbname) {
        if(farbname.equals("red")) {
            graphic.setColor(new Color(235, 25, 25));
        }
        else if(farbname.equals("black")) {
            graphic.setColor(Color.black);
        }
        else if(farbname.equals("gray")) {
            graphic.setColor(new Color(100, 100, 100));
        }
        else if(farbname.equals("blue")) {
            graphic.setColor(new Color(30, 75, 220));
        }
        else if(farbname.equals("yellow")) {
            graphic.setColor(new Color(255, 230, 0));
        }
        else if(farbname.equals("green")) {
            graphic.setColor(new Color(80, 160, 60));
        }
        else if(farbname.equals("white")) {
            graphic.setColor(Color.white);
        }
        else {
            graphic.setColor(Color.black);
        }
    }


    /**
     * Zeichne erneut alle Figuren auf der Leinwand.
     */
    public void redraw() {
        delete();
        for (Object tile : tiles) {
            tileToShape.get(tile).draw(graphic);
        }
        zeichenflaeche.repaint();
    }

    /**
     * Lösche die gesamte Leinwand.
     */
    private void delete() {
        Color original = graphic.getColor();
        graphic.setColor(hintergrundfarbe);
        Dimension size = zeichenflaeche.getSize();
        graphic.fill(new Rectangle(0, 0, size.width, size.height));
        graphic.setColor(original);
    }

    public void redrawSnake() {
        for (Object tile : tiles) {
            if (tile instanceof SnakeTile || tile instanceof Head) {
		//System.out.println(tile.posx + " " + tile.posy);
                tileToShape.get(tile).draw(graphic);
            }
        }
        zeichenflaeche.repaint();
    }
    /***************************************************************************
     * Interne Klasse Zeichenflaeche - die Klasse für die GUI-Komponente, die
     * tatsächlich im Leinwand-frame angezeigt wird. Diese Klasse definiert
     * ein JPanel mit der zusätzlichen Möglichkeit, das auf ihm gezeichnet Image
     * aufzufrischen (erneut zu zeichnen).
     */
    private class Zeichenflaeche extends JPanel {
        private static final long serialVersionUID = 20190331L;

        public void paint(Graphics g) {
            g.drawImage(frameImage, 0, 0, null);
        }
    }

    /***************************************************************************
     * Interne Klasse ShapeMitFarbe - Da die Klasse Shape des JDK nicht auch
     * eine Farbe mitverwalten kann, muss mit dieser Klasse die Verknüpfung
     * modelliert werden.
     */
    private class ShapeMitFarbe {
        private Shape shape;

        private String farbe;

        public ShapeMitFarbe(Shape shape, String farbe) {
            this.shape = shape;
            this.farbe = farbe;
        }

        public void draw(Graphics2D graphic) {
            setzeZeichenfarbe(farbe);
            graphic.fill(shape);
        }
    }


    private class Keylistener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            engine.key(e);
        }
    }
}
