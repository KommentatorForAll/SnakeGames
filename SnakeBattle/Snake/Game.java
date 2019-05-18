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
        main(2, 38, 20);
    }

    private static int pixelsize = 50;

    public static void main(int difficulty, int xsize, int ysize) {
        Engine engine = new Engine(xsize, ysize);
        Frame_work frame = new Frame_work(xsize*pixelsize, ysize*pixelsize, engine);
        setDifficulty(difficulty, engine);
        frame.redraw();
        start(engine, frame);
    }

    private static void start(Engine engine, Frame_work frame) {
        boolean winningsnake = engine.run(frame) == 1;
        if (!winningsnake) {
            System.out.println("The yellow snake (Player 2) won!");
        }
        else {
            System.out.println("The green snake (Player 1) won!");
        }
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
