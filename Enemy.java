import processing.core.*;

public class Enemy extends GameObject{
    private PApplet p;

    private PVector position        = new PVector(0, 0);
    private PVector velocity        = new PVector(0, 0);
    private PVector acceleration    = new PVector(0, 0);

    private boolean moveUp, moveLeft, moveRight;
    private boolean onGround;
    private int     groundPositionY;
}