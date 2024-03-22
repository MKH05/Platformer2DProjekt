import processing.core.PApplet;

public class Player{
  private float x;
  private float y;
  private boolean onGround;
  private PApplet p;
  private Movement move;

  Movement move;

  public Player(float startX, float startY, PApplet pin) {
      x = startX;
      y = startY;
      p = pin;
      onGround = false;   
  }

  move = new Movement(x, y, 5, 0.5, p);

  public void keyPressed() {
      if (p.key == 'w' && onGround) {
          jump();
      }
      if (p.key == CODED) {
          if (p.keyCode == LEFT) {
              move.applyLeftForce(0.5);
          } else if (p.keyCode == RIGHT) {
              move.applyRightForce(0.5);
          }
      }
  }

  public void jump() {
      PVector jumpForce = new PVector(0, -12);
      move.applyForce(jumpForce);
      onGround = false;
  }

  public void update() {
      move.update(onGround);
  }

  public void setOnGround(boolean onGround) {
      this.onGround = onGround;
  }
}
