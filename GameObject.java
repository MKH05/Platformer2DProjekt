import processing.core.*;

public class GameObject {
    public PVector position        = new PVector(0, 0);
    public int     groundPositionY; 
    public boolean onGround;
    public PVector velocity        = new PVector(0, 0);

    public boolean handlePlatformCollision(int[] platformInfo) {
        int x = platformInfo[0];
        int y = platformInfo[1];
        int w = platformInfo[2];
        int h = platformInfo[3];

        boolean isCollided = position.x > x && position.x < x + w && position.y > y && position.y < y + h;
        
        if (isCollided) {
            groundPositionY = y + 1;
            onGround = true;
        } else {
            onGround = false;

            boolean HitButtom = position.x > x && position.x < x + w && position.y-50 > y && position.y-50 < y + h;

            if (HitButtom){
                velocity.y = 0;
            }
        }
        
        return isCollided;
    }
}
