Player player;

// data for tre forskellige platforme
int groundX = 200, groundY = 400, groundW = 1000, groundH = 100;  
int groundX2 = 200, groundY2 = 300, groundW2 = 300, groundH2 = 10;
int groundX3 = 800, groundY3 = 250, groundW3 = 300, groundH3 = 10;

void setup() {
    size(1400,500);
    player = new Player(this,400,groundY+1);
}

void draw() {    
    background(100);

    //tegning af de tre platforme
    rect(groundX,groundY,groundW,groundH);
    rect(groundX2,groundY2,groundW2,groundH2);
    rect(groundX3,groundY3,groundW3,groundH3);

    player.displayAndUpdatePhysics();

    //collision detection for de tre platforme
    if(player.handlePlatformCollision(groundX,groundY,groundW,groundH)) return;
    if(player.handlePlatformCollision(groundX2,groundY2,groundW2,groundH2)) return;
    if(player.handlePlatformCollision(groundX3,groundY3,groundW3,groundH3)) return;
}

void keyPressed() {
    player.keyPressed();
}

void keyReleased() {
    player.keyReleased();
}

