import java.util.*;
import java.awt.*;
import java.io.*;
/**
 * Beschreiben Sie hier die Klasse Game.
 *
 * @author (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Game
{
    public static void main(String[] args) {
        main(3, 15, 15);
    }

    private static int pixelsize = 50;

    public static void main(int difficulty, int xsize, int ysize) {
        Field map = new Field();
        map.newMap(xsize, ysize);
        Engine engine = new Engine(map);
        Frame_work frame = new Frame_work((xsize+1)*pixelsize, (ysize+1)*pixelsize, engine);
        setDifficulty(difficulty, engine);
        drawMap(map, frame);
        frame.redraw();
        start(map, engine, frame);
    }

    private static void start(Field map, Engine engine, Frame_work frame) {
        int score = engine.run(map, frame);
        System.out.println("You died!");
        System.out.println("Your score: " + score);
        try {
            int highscore = Integer.parseInt(gethighscore());
            if (score > highscore) {
                writeHighscore(score + "");
                System.out.println("You got a new highscore!");
            }
            else {
                System.out.println("Highscore: " + highscore);
            }
        } catch (Exception e) {}
    }

    private static void drawMap(Field map, Frame_work frame) {
        int i = 0;
        int j;
        for(ArrayList<Maptile> yRow : map.toTileMap()) {
            j = 0;
            for (Maptile tile : yRow) {
                frame.zeichne(tile, tile.color, new Rectangle(i*pixelsize, j*pixelsize, pixelsize, pixelsize));
                j++;
            }
            i++;
        }
    }

    private static String gethighscore() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("../stats/highscore.txt"));
        return sc.nextLine();
    }

    private static void writeHighscore(String level) throws IOException{
        Writer wr = new FileWriter("../stats/highscore.txt");
        wr.write(level);
        wr.close();
    }
    
    public static void resetStats() throws IOException {
        Writer wr = new FileWriter("../stats/highscore.txt");
        wr.write("0");
        wr.close();
    }
    
    private static void setDifficulty(int difficulty, Engine engine) {
        switch (difficulty) {
            case 0:
                engine.frames = 3;
                break;
            case 1:
                engine.frames = 5;
                break;
            case 2:
                engine.frames = 7;
                break;
            case 3:
                engine.frames = 9;
                break;
            default:
                engine.frames = 5;
                break;
        }
    }
}
