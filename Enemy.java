import processing.core.*;

public class Enemy extends GameObject {
    private PApplet p;

    private Animation a,b,c;

    private PVector position        = new PVector(0, 0);
    private PVector velocity        = new PVector(0, 0);
    private PVector acceleration    = new PVector(0, 0);

    private boolean moveUp, moveLeft, moveRight;
    private boolean onGround;
    private int     groundPositionY;
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

            boolean hitBottom = position.x > x && position.x < x + w && position.y - 50 > y && position.y - 50 < y + h;

            if (hitBottom){
                velocity.y = 0;
            }
        }
        
        return isCollided;   
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
