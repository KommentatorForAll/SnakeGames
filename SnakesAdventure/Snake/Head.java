    
/**
 * Beschreiben Sie hier die Klasse Head.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Head
{
    int posx, posy;
    int lastposx, lastposy;
    
    public Head(int posx, int posy) {
        this.posx = posx;
        this.posy = posy;
        this.lastposx = posx;
        this.lastposy = posy;
    }
    
    public void move(byte dir, Field map) {
        switch (dir) {
            case 0:
                lastposy = posy;
                lastposx = posx;
                posy -= 1;
                break;
                
            case 1:
                lastposy = posy;
                lastposx = posx;
                posy += 1;
                break;
                
            case 2:
                lastposx = posx;
                lastposy = posy;
                posx -= 1;
                break;
                
            case 3:
                lastposx = posx;
                lastposy = posy;
                posx += 1;
        }

    if (posx == map.map.size()) {
        posx = 0;
    }
    else if (posy == map.map.get(0).size()) {
        posy = 0;
    }
    else if (posx == -1) {
        posx = map.map.size()-1;
    }
    else if (posy == -1) {
        posy = map.map.get(0).size()-1;
    }
    }
}
