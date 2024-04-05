import processing.core.*;

public class Player{

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


    public Player(PApplet p, int x, int y){
        this.p          = p;
        groundPositionY = y;
        position.set(x,y);
    }   

    public void displayAndUpdatePhysics(){
        display();
        updatePhysics();
    }

    private void display(){
        //tegning af spilleren
        p.fill(255);
        p.textSize(20);
        p.rect(position.x-25, position.y-50, 50, 50);

        //top og bund af spilleren - skal bruges til at se hvor spilleren rammer platformen fra top eller bund
        p.ellipse(position.x, position.y, 5, 5);
        p.ellipse(position.x, position.y-50, 5, 5);

        //dette er bare info-tekst
        p.text("UP: " + moveUp + "\n LEFT: " + moveLeft + "\n RIGHT: " + moveRight + "\n onGround: " + onGround, 10, 20);
        p.text("POSITION : " + (int)position.x + " : " + (int)position.y, 300, 20);
        p.text("GROUND POSITION : " + groundPositionY, 300, 40);
    }

    private void updatePhysics(){
        //Tyngdekraften
        if(onGround){ velocity.y = 0; position.y = groundPositionY; acceleration.y = 0; velocity.x*=0.9;}
        if(!onGround){ acceleration.y = 0.5f;}        

        //Bruger input til at ændre accelerationen
        if(onGround && moveLeft){   acceleration.x = -0.5f; }
        if(onGround && moveRight){  acceleration.x = 0.5f;}
        if(onGround && moveUp){     acceleration.y = -10f;}

        //Fysikken opdateres
        velocity.add(acceleration);
        position.add(velocity);
        acceleration.mult(0);
    }

    public boolean handlePlatformCollision(int[] platformInfo) {
    int x = platformInfo[0];
    int y = platformInfo[1];
    int w = platformInfo[2];
    int h = platformInfo[3];

    boolean isCollided = position.x > x && position.x < x + w && position.y > y && position.y < y + h;
    
    if (isCollided) {
        this.groundPositionY = y + 1;
        this.onGround = true;
    } else {
        this.onGround = false;
    }
    
    return isCollided;
}


    private void loadImage(String path){
        
    }

    public void stateMachine(){
        if(moveLeft == true && onGround == true){
        p.println("left");

        }else if(moveRight == true && onGround == true){   
        p.println("right");
        }else if(!onGround){  
        p.println("Air");
        }else if(onGround && !moveRight && !moveLeft){
        p.println("idle");
        }

    }

    public void keyPressed(){
        //Bruger starter input
        if(p.key == 'a'){   moveLeft = true; }
        if(p.key == 'd'){   moveRight = true;}
        if(p.key == 'w'){   moveUp = true;}
    }

    public void keyReleased(){
        //Bruger stopper input
        if(p.key == 'a'){   moveLeft = false;}
        if(p.key == 'd'){   moveRight = false;}
        if(p.key == 'w'){   moveUp = false;}
    }


}