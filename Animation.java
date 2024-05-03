import processing.core.*;

public class Animation{
    private PApplet p;

    private PImage spritesheet; 

    private int wFrame;
    private int hFrame;
    private int row, column; 
    private int xFrame = 0;
    private int yFrame = 0;
    private int xRow;

    private int reverseFactor = 1;

    public Animation(PApplet p, String filename){
        p.imageMode(p.CENTER);
        spritesheet = p.loadImage(filename);
        wFrame = spritesheet.width/4;
        hFrame = spritesheet.height/2;
        this.p = p;
    }

    public void display(int posX, int posY){
        if(p.frameCount%10==0){

            if(column<4) {column++;} else {column = 0; row++;}
            if(row==2) {row=0;}
            if(row==1 && column == 2) {row=0; column=0;}

            xFrame = (column)*wFrame-25;
            yFrame = (row)*hFrame;
        }

        PImage sprite = spritesheet.get(xFrame, yFrame, wFrame, hFrame);

        p.pushMatrix();
        p.scale(reverseFactor, 1);

        p.image(sprite, reverseFactor*posX, posY, 100, 100);
        p.popMatrix();
    }

    public void reverseFactor(){
        reverseFactor = -reverseFactor;
    }

}