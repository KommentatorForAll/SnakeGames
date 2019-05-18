import java.awt.event.*;
import javax.swing.*;
/**
 * Beschreiben Sie hier die Klasse Engine.
 *
 * @author (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */

class Engine
{
    private Snake snake;
    int frames = 3;
    private TT waiter = new TT();
    private boolean go = false;
    public Engine(Field map) {
        int[] snakepos = map.getSnakePos();
        if (snakepos == null) {
            System.err.println("Invalid Map: Map has no snake");
            return;
        }
        snake = new Snake(snakepos[0], snakepos[1]);

    }

    public void key(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'w':
            if (!(snake.dir == 1)) {
                snake.dir = 0;
            }
            break;

            case 'a':
            if (!(snake.dir == 3)) {
                snake.dir = 2;
            }
            break;

            case 's':
            if (!(snake.dir == 0)) {
                snake.dir = 1;
            }
            break;

            case 'd':
            if (!(snake.dir == 2)) {
                snake.dir = 3;
            }
            break;
            
            case ' ':
            go = true;
        }
    }

    public boolean run(Field map, Frame_work frame) {
        boolean ended = false;
        boolean win = false;
        while (!go) {}
        while (!ended) {
            try{
                waiter.wait(frames);
                snake.move(map);

                if (map.getTile(snake.head.posx, snake.head.posy) == 'a' && !map.tilemap.get(snake.head.posx).get(snake.head.posy).collected){
                    snake.eat();
                    map.collectApple(snake.head.posx, snake.head.posy, frame);
                }
                else if (snake.isTile(snake.head.posx, snake.head.posy) || map.getTile(snake.head.posx, snake.head.posy) == '#') {
                    ended = true;
                }

                if ((map.hasFinish && map.appleCount() == 0 && map.getTile(snake.head.posx, snake.head.posy) == 'f') || (!map.hasFinish && map.appleCount() == 0)) {
                    win = true;
                    ended = true;
                }

                snake.draw(frame);
                frame.redraw();
            }
            catch(Exception e) {
                System.err.println("Error was catched: " + e);
            }
        }
        return win;
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
