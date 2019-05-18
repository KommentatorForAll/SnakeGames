
/**
 * Beschreiben Sie hier die Klasse Tile.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class SnakeTile
{
    int posx, posy;
    int lastposx, lastposy;
    
    public SnakeTile(int posx, int posy) {
        this.posx = posx;
        this.posy = posy;
        this.lastposx = posx;
        this.lastposx = posx;
    }
    
    public void move(int posx, int posy) {
        this.lastposx = this.posx;
        this.lastposy = this.posy;
        this.posx = posx;
        this.posy = posy;
    }
}
