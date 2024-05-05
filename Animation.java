import processing.core.*;

public class Animation {
    private PApplet p;

    private PImage spritesheet;
    private int wFrame;
    private int hFrame;
    private int row, column;
    private int xFrame = 0;
    private int yFrame = 0;
    private int xColumn;

    private int reverseFactor = 1;

    public Animation(PApplet p, String filename, int col) {
        p.imageMode(p.CENTER);
        spritesheet = p.loadImage(filename);
        wFrame = 32;
        hFrame = 32;
        this.p = p;
        xColumn = col;
    }

    public void display(float posX, float posY) {
        if (p.frameCount % 10 == 0) {

            if (column < xColumn) {
                column++;
            }
            //if(row==2) {row=0;}
            if (row == 0 && column == xColumn) {
                row = 0;
                column = 0;
            }

            xFrame = (column) * wFrame;
            yFrame = (row) * hFrame;
        }

        PImage sprite = spritesheet.get(xFrame, yFrame, wFrame, hFrame);

        p.pushMatrix();
        p.scale(reverseFactor, 1);

        
        p.image(sprite, reverseFactor * posX, posY, 64, 64);
        p.popMatrix();
    }

    public void reverseFactor() {
        reverseFactor = -reverseFactor;
    }

    public void left() {
        reverseFactor = -1;
    }

    public void right() {
        reverseFactor = 1;
    }
}
