import processing.core.PApplet;

public class Platform {
    private PApplet p;
    private int groundX, groundY, groundW, groundH; 

    public Platform(int PlatformgroundX, int PlatformgroundY, int PlatformgroundW, int PlatformgroundH, PApplet pin) {
        groundX = PlatformgroundX;
        groundY = PlatformgroundY;
        groundW = PlatformgroundW;
        groundH = PlatformgroundH;
        p = pin;
    }

    public void displayPlatform() {
        p.rect(groundX, groundY, groundW, groundH);
    }

    public int[] getPlatformInfo() {
        int[] info = {groundX, groundY, groundW, groundH};
        return info;
    }
}
