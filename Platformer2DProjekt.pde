
Player player;

void setup() {
    size(1280, 512);

    player = new Player(width/2, height/2, this);
}

void draw() {
    background(220);
}

void keyPressed() {
    player.KeyPress();
}

void keyReleased() {
    
}