import processing.core.*;

public class Player{

    private PApplet p;

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

    public boolean handlePlatformCollision(int x, int y, int w, int h){
        boolean isCollided =  position.x > x && position.x < x+w && position.y > y && position.y < y+h;
        this.groundPositionY = y+1;
        this.onGround = isCollided;
        return isCollided;
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