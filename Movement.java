import processing.core.PApplet;

public class Movement {
  private float x;
  private float y;
  private PApplet p;

  public Movement(float startX, float startY, PApplet pin) {
    x = startX;
    y = startY;
    p = pin;
  }
}
