import java.awt.event.*;
import javax.swing.*;
import java.util.*;
/**
 * Beschreiben Sie hier die Klasse Engine.
 *
 * @author (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */

class Engine
{
    ArrayList<Snake> snakes;
    int frames = 3;
    private TT waiter = new TT();
    private boolean go = false;
    public Engine(int xsize, int ysize) {
        snakes = new ArrayList<>();
        snakes.add(new Snake(1, 2, xsize, ysize, "green"));
        snakes.add(new Snake(2, 4, xsize, ysize, "yellow"));
    }

    public void key(KeyEvent e) {
        //System.out.println(e.getKeyChar());
        switch (e.getExtendedKeyCode()) {
            case KeyEvent.VK_W:
            if (!(snakes.get(0).dir == 1)) {
                snakes.get(0).dir = 0;
            }
            break;

            case KeyEvent.VK_A:
            if (!(snakes.get(0).dir == 3)) {
                snakes.get(0).dir = 2;
            }
            break;

            case KeyEvent.VK_S:
            if (!(snakes.get(0).dir == 0)) {
                snakes.get(0).dir = 1;
            }
            break;

            case KeyEvent.VK_D:
            if (!(snakes.get(0).dir == 2)) {
                snakes.get(0).dir = 3;
            }
            break;
            case KeyEvent.VK_SPACE:
            go = true;

            case KeyEvent.VK_UP:
            if (!(snakes.get(1).dir == 1)) {
                snakes.get(1).dir = 0;
            }
            break;
            case KeyEvent.VK_LEFT:
            if (!(snakes.get(1).dir == 3)) {
                snakes.get(1).dir = 2;
            }
            break;
            case KeyEvent.VK_DOWN:
            if (!(snakes.get(1).dir == 0)) {
                snakes.get(1).dir = 1;
            }
            break;
            case KeyEvent.VK_RIGHT:
            if (!(snakes.get(1).dir == 2)) {
                snakes.get(1).dir = 3;
            }
            break;
        }
    }

    public int run(Frame_work frame) {
        boolean ended = false;
        int winningsnake = 0;
        int i;
        while (!go) {};
        while (!ended) {
            try{
                i=0;
                for (Snake snake : snakes) {
                    if (!ended) {
                        waiter.wait(frames);
                        ended = !snake.move();
                        if (!ended) {
                            if (isTile(snake.head.posx, snake.head.posy)) {
                                ended = true;
                                winningsnake = i;
                                System.out.println(i);
                            }
                            else {
                                i++;
                            }
                            snake.draw(frame);
                            snake.eat();
                        }

                    }
                    //System.out.println(i);
                }
                frame.redraw();
            }
            catch(Exception e) {
                System.err.println("Error was catched: " + e);
            }
        }
        System.out.println(winningsnake);
        return winningsnake;
    }

    private boolean isTile(int posx, int posy) {
        for (Snake snake : snakes) {
            if (snake.isTile(posx, posy)) {
                return true;
            }
        }
        return false;
    }

    class TT extends Thread {
        public void wait(int frames) {
            try {
                Thread.sleep(1000/frames);
            }
            catch(Exception e) {
                System.err.println("Error while waiting: " + e);
            }
        }
    }
}
