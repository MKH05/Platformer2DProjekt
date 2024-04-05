import processing.core.PApplet;

public class Animation{
    private String AnimFolder;
    private PApplet p;


    
    private int currentFrame;

    public Animation(String Path, PApplet pin) {
      AnimFolder = Path;
      p = pin;
    }

    public loadAnimation(){
      p.loadImage
    }
    

    public updateFrame(){  
      currentFrame = (currentFrame + 1) % frames.length;
      if(currentFrame = )
      p.println("hej");

   }
}

