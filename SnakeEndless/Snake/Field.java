import java.util.*;
import java.io.*;
import java.awt.*;

/**
 * Beschreiben Sie hier die Klasse Field.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Field
{
    ArrayList<ArrayList<Character>> map;
    ArrayList<ArrayList<Maptile>> tilemap;
    Random rnd;
    boolean hasFinish = false;
    
    public Field() {
        map = new ArrayList<ArrayList<Character>>();
        tilemap = new ArrayList<ArrayList<Maptile>>();
        rnd = new Random();
    }
    
    public void newMap(int xsize, int ysize) {
        int i = 0;
        int j;
        while (i<=xsize) {
            j = 0;
            map.add(new ArrayList<Character>());
            while (j <= ysize) {
                map.get(i).add('.');
                j++;
            }
            i++;
        }
            
    }
    
    public ArrayList<ArrayList<Maptile>> toTileMap() {
        int i = 0;
        for(ArrayList<Character> xRow : map) {
            tilemap.add(new ArrayList<Maptile>());
            for (char c : xRow) {
                tilemap.get(i).add(new Maptile(c));
            }    
            i++;
        }
        return tilemap;
    }
    
    public char getTile(int posx, int posy) {
        return map.get(posx).get(posy);
    }
    
    public int appleCount() {
        int cnd = 0;
        int i = 0;
        int j;
        for(ArrayList<Character> xRow : map) {
            j = 0;
            for(char c : xRow) {
                if (c == 'a' && !tilemap.get(i).get(j).collected) {
                    cnd++;
                }
                j++;
            }
            i++;
        }
        return cnd;
    }
    
    public void createApple(Frame_work frame) {
        int posx = rnd.nextInt(map.size());
        int posy = rnd.nextInt(map.get(0).size());
        map.get(posx).set(posy,'a');
        frame.zeichne(new Maptile('a'), "red", new Rectangle(posx*50, posy*50, 50, 50));
        ArrayList<ArrayList<Maptile>> a = toTileMap();
        tilemap.get(posx).get(posy).collected = false;
    }
    
    public void collectApple(int posx, int posy, Frame_work frame) {
	Maptile tile = tilemap.get(posx).get(posy);
        tile.collected = true;
        tile.color = "black";
	frame.zeichne(tile, tile.color, new Rectangle(posx*50, posy*50, 50, 50));
    }
    
    public int[] getSnakePos() {
        int i = 0;
        int ind;
        int[] ret = {0,0};
        for (ArrayList<Character> xRow : map) {
            ind = xRow.indexOf('s');
            if (!(ind == -1)) {
                ret[0] = i;
                ret[1] = ind;
                return ret;
            }
            i++;
        }
        return null;
    }
}
