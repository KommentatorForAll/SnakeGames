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
        main(2);
    }

    private static int pixelsize = 50;

    public static void main(int difficulty) {
        Field map = new Field();
        try {
            map.readMap("../maps/level" + getCurrentLevel() +".txt");
        }
        catch (Exception e) {
            System.out.println("Congratulations, you beated the game!");
            return;
        }
        Engine engine = new Engine(map);
        ArrayList<ArrayList<Maptile>> tilemap = map.toTileMap();
        int ysize = tilemap.get(0).size();
        int xsize = tilemap.size();
        Frame_work frame = new Frame_work(xsize*pixelsize, ysize*pixelsize, engine);
        setDifficulty(difficulty, engine);
        drawMap(map, frame);
        frame.redraw();
        start(map, engine, frame);
    }

    private static void start(Field map, Engine engine, Frame_work frame) {
        boolean win;
        win = engine.run(map, frame);
        if (win) {
            System.out.println("You win!");
            try {
                writeCurrentLevel(Integer.parseInt(getCurrentLevel())+1 + "");
            } catch(Exception e) {}
        }
        else {
            System.out.println("You lost!");
        }
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

    private static String getCurrentLevel() throws FileNotFoundException{
        Scanner sc = new Scanner(new File("../stats/currentlevel.txt"));
        return sc.nextLine();
    }

    private static void writeCurrentLevel(String level) throws IOException{
        Writer wr = new FileWriter("../stats/currentlevel.txt");
        wr.write(level);
        wr.close();
    }
    
    public static void resetStats() throws IOException {
        Writer wr = new FileWriter("../stats/currentlevel.txt");
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
