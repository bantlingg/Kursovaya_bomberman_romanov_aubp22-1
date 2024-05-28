package father;
import main.Arena;

import java.awt.*;

public class Father {
    int x,y;
    int speed;
    boolean hp=true;
    public void update(){}
    public void draw( Graphics2D g2){}
    public boolean isAlive(){
        if(hp){return true;}
        else {return false;}
    }
}
