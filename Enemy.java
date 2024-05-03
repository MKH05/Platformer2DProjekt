import processing.core.*;

public class Enemy extends GameObject{
    private PApplet p;

    private PVector position        = new PVector(0, 0);
    private PVector velocity        = new PVector(0, 0);
    private PVector acceleration    = new PVector(0, 0);

    private boolean moveUp, moveLeft, moveRight;
    private boolean onGround;
    private int     groundPositionY;

    public Enemy(PApplet p, int x, int y){
        this.p          = p;
        groundPositionY = y;
        position.set(x,y);
    }

    public void displayAndUpdatePhysics(){
        display();
        updatePhysics();
    }

    private void display(){
        p.fill(255);
        p.textSize(20);
        p.rect(position.x-25, position.y-50, 50, 50);
        p.ellipse(position.x, position.y, 5, 5);
        p.ellipse(position.x, position.y-50, 5, 5);
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

        boolean HitButtom = position.x > x && position.x < x + w && position.y-50 > y && position.y-50 < y + h;

        if (HitButtom){
            velocity.y = 0;
        }
    }
    
    return isCollided;   
    }

    Runnable AI = new Runnable() {
        private void run() {
            if (moveLeft){
                moveLeft = false;
                moveRight = true;
            }else{
                moveLeft = true;
                moveRight = false;
            }
        }
    };

    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    executor.scheduleAtFixedRate(AI, 0, 5, TimeUnit.SECONDS);
    
}