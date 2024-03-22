import processing.core.PApplet;
import processing.core.PVector;

public class Movement {
    private PVector position;
    private PVector velocity;
    private float topSpeed;
    private float gravity;
    private PApplet p;

    public Movement(float startX, float startY, float topSpeed, float gravity, PApplet pin) {
        position = new PVector(startX, startY);
        velocity = new PVector(0, 0);
        this.topSpeed = topSpeed;
        this.gravity = gravity;
        p = pin;
    }

    public PVector getPosition() {
        return position;
    }

    public void applyForce(PVector force) {
        velocity.add(force);
    }

    public void applyLeftForce(float forceMagnitude) {
        PVector leftForce = new PVector(-forceMagnitude, 0);
        applyForce(leftForce);
    }

    public void applyRightForce(float forceMagnitude) {
        PVector rightForce = new PVector(forceMagnitude, 0);
        applyForce(rightForce);
    }

    public void update(boolean onGround) {
        if (onGround) {
            

            velocity.x *= 0.9;
        }
        else {
           PVector gravityForce = new PVector(0, gravity);
            applyForce(gravityForce);
        }
        position.add(velocity);
        velocity.limit(topSpeed);
    }
}
