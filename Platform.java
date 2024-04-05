import processing.core.PApplet;

public class Platform{
    private PApplet p;
    private int groundX, groundY, groundW, groundH; 

    public Platform(int PlatformgroundX, int PlatformgroundY, int PlatformgroundW, int PlatformgroundH, PApplet pin) {
      groundX = PlatformgroundX;
      groundY = PlatformgroundY;
      groundW = PlatformgroundW;
      groundH = PlatformgroundH;
      p = pin;
    }

    public displayPlatform(){
        rect(groundX,groundY,groundW,groundH);
    }

    int getPlatformInfo(){
        return groundX, groundY, groundW, groundH;
    }
}