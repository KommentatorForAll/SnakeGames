import java.util.*;
import java.awt.*;
/**
 * Beschreiben Sie hier die Klasse Snake.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Snake
{
    ArrayList<SnakeTile> tiles;
    Head head;
    byte dir; // 
    
    public Snake(int posx, int posy) {
        tiles = new ArrayList<>();
        head = new Head(posx, posy);
        tiles.add(new SnakeTile(posx,posy));
        tiles.add(new SnakeTile(posx,posy));
    }
    
    public void eat() {
        tiles.add(new SnakeTile(tiles.get(tiles.size()-1).lastposx, tiles.get(tiles.size()-1).lastposy));
    }
    
    public boolean isTile(int posx, int posy) {
        for (SnakeTile tile : tiles) {
            if (tile.posx == posx && tile.posy == posy) {
                return true;
            }
        }
        return false;
    }
    
    public void move(Field map) {
        head.move(dir,map);
        int posx = head.lastposx;
        int posy = head.lastposy;
        for (SnakeTile tile : tiles){
            //System.out.println(tile.posx + " " + tile.posy);
            tile.move(posx, posy);
            posx = tile.lastposx;
            posy = tile.lastposy;
        }
    }
    
    public void draw(Frame_work frame) {
        frame.zeichne(head, "green", new Rectangle(head.posx*50, head.posy*50, 50, 50));
        for (SnakeTile tile : tiles) {
            frame.zeichne(tile, "green", new Rectangle(tile.posx*50, tile.posy*50, 50, 50));
        }
       // move();
    }
}
