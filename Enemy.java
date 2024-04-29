import processing.core.*;

public class Enemy{
    private PApplet p;
    
    PImage[] walk = new PImage[12];
    PImage[] fall = new PImage[12];
    PImage[] idle = new PImage[12];

    private PVector position        = new PVector(0, 0);
    private PVector velocity        = new PVector(0, 0);
    private PVector acceleration    = new PVector(0, 0);

    private boolean moveUp, moveLeft, moveRight;
    private boolean onGround;
    private int     groundPositionY;
}