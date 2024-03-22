import processing.core.PApplet;
import processing.core.PVector;

public class Player{
  private float x;
  private float y;
  private float WorldPosY;
  private boolean onGround;
  private Movement move;
  private PApplet p;

  public Player(float startX, float startY, PApplet pin) {
      x = startX;
      y = startY;
      WorldPosY = startY + 50f;
      p = pin;
      onGround = false;

      move = new Movement(x, y,5f,0.5f, pin);
  }

  public void keyPressed() {
      if (p.key == 'w' && onGround) {
            p.println("KeyPressed: w");
            jump();
      }
      if (p.key == p.CODED) {
          if (p.keyCode == p.LEFT) {
              move.applyLeftForce(0.5f);
          } else if (p.keyCode == p.RIGHT) {
              move.applyRightForce(0.5f);
          }
      }
  }

  public void jump() {
      PVector jumpForce = new PVector(0, -12);
      move.applyForce(jumpForce);
      onGround = false;
      p.println("HOP!");
  }

  public void update() {
      if(move.getPosition().y > WorldPosY){
        onGround = false;
        move.update(onGround);
      } else if (move.getPosition().y < WorldPosY) {
        onGround = true;
        move.update(onGround);
      }

      p.ellipse(move.getPosition().x,move.getPosition().y,25f,25f);   
  }
}
