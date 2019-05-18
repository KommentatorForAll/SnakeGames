
/**
 * Beschreiben Sie hier die Klasse Maptile.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Maptile
{
    String color;
    char tilekind;
    boolean collected = true;
    
    public Maptile(char tilekind) {
        this.tilekind = tilekind;
        switch (tilekind) {
            case '#':
                color = "gray";
                break;
                
            case '.':
                color = "black";
                break;
                
            case 'a':
                color = "red";
                collected = false;
                break;
                
            case 's':
                color = "black";
                break;
                
            case 'f':
                color = "blue";
                break;
        }
    }
}
