Player player;
Platform Platform;

ArrayList<Platform> platforms = new ArrayList<Platform>();

// data for tre forskellige platforme
int groundX = 200, groundY = 400, groundW = 1000, groundH = 100;  
int groundX2 = 200, groundY2 = 300, groundW2 = 300, groundH2 = 10;
int groundX3 = 800, groundY3 = 250, groundW3 = 300, groundH3 = 10;

void setup() {
    size(1400,500);
    player = new Player(this,400,groundY+1);

    platforms.add(new Platform(groundX,groundY,groundW,groundH,this));
    platforms.add(new Platform(groundX2,groundY2,groundW2,groundH2,this)); 
    platforms.add(new Platform(groundX3,groundY3,groundW3,groundH3,this));  
}

void draw() {    
    background(100);

    player.displayAndUpdatePhysics();

    //collision detection for de tre platforme
    for (int i=0; i<platforms.size(); i++){
        Platform FoundPlatform = platforms.get(i);
        FoundPlatform.displayPlatform();    
    }  

    for (int f=0; f<platforms.size(); f++){
        Platform FoundPlatformInfo = platforms.get(f);
        if(player.handlePlatformCollision(FoundPlatformInfo.getPlatformInfo())) return;
    }  
}

void keyPressed() {
    player.keyPressed();
}

void keyReleased() {
    player.keyReleased();
}

