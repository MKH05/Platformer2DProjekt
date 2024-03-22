import processing.core.PApplet;

public class Player{
    private float x;
    private float y;
    private PApplet p;

    public Player(float startX, float startY, PApplet pin) {
      x = startX;
      y = startY;
      p = pin;
    }

    Movement move = new Movement(x,y,50,40,p);

    public void KeyPress(){
      System.out.println("KeyPressed");
    }

    public void DisplayAndUpdate(){
      move.update();
    }
}
