import processing.core.*;

public class Enemy extends GameObject {
    private PApplet p;

    private Animation a,b,c;

    private PVector velocity        = new PVector(0, 0);
    private PVector acceleration    = new PVector(0, 0);

    private boolean moveUp, moveLeft, moveRight;
    private boolean lastDirectionLeft;
    
    private int lastAITime;
    private int aiInterval = 100;

    public Enemy(PApplet p, int x, int y){
        this.p          = p;
        groundPositionY = y;
        position.set(x, y);
        lastAITime = p.frameCount;

        a = new Animation(p, "Animations/Enemy/Walk/Run (32x32).png", 12);
        b = new Animation(p, "Animations/Enemy/Idle/Idle (32x32).png", 11);
        c = new Animation(p, "Animations/Enemy/Fall/Fall (32x32).png", 1);
    }

    public void displayAndUpdatePhysics(){
        display();
        updatePhysics();
    }

    private void display(){
        p.fill(255);
        p.textSize(20);
        
        p.ellipse(position.x, position.y, 5, 5);
        p.ellipse(position.x, position.y-50, 5, 5);

        stateMachine();
    }

    private void updatePhysics(){
        if(onGround){
            velocity.y = 0;
            position.y = groundPositionY;
            acceleration.y = 0;
            velocity.x *= 0.9;
        }
        if(!onGround){
            acceleration.y = 0.5f;
        }        

        if(onGround && moveLeft){
            acceleration.x = -0.5f;
        }
        if(onGround && moveRight){
            acceleration.x = 0.5f;
        }
        if(onGround && moveUp){
            acceleration.y = -10f;
        }

        velocity.add(acceleration);
        position.add(velocity);
        acceleration.mult(0);

        if (p.frameCount - lastAITime >= aiInterval) {
            executeAI();
            lastAITime = p.frameCount;
        }
        if (p.millis() % 5000 < aiInterval) {
            moveUp = true;
        } else {
            moveUp = false;
        }
    }

    public void stateMachine(){
        if(moveLeft && onGround){
             a.left();
             a.display(position.x,position.y-30);   
        } else if(moveRight && onGround){   
            a.right();
            a.display(position.x,position.y-30);  
        } else if(!onGround){ 
            if(lastDirectionLeft) {
                c.left();
            } else {
                c.right();
            }
            c.display(position.x,position.y-30); 
        } else if(onGround && !moveRight && !moveLeft){             
            if(lastDirectionLeft) {
                b.left();
            } else {
                b.right();
            }
            b.display(position.x,position.y-30); 
        }
    }

    private void executeAI() {
        if (moveLeft){
            moveLeft = false;
            moveRight = true;
        } else {
            moveLeft = true;
            moveRight = false;
        }
    }
}
