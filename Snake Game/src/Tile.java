import java.awt.Color;
import java.awt.Graphics;

public class Tile {
    private int x;
    private int y;
    private final int tileSize;
    private final Color color;

    public Tile(int x, int y, int tileSize, Color color){
        this.x = x;
        this.y = y;
        this.tileSize = tileSize;
        this.color = color;
    }

    public void draw(Graphics g){
        g.setColor(this.color);
        g.fill3DRect(this.x, this.y, this.tileSize, tileSize, true);
    }

    public boolean checkCollision(Tile otherTile){
        return this.x == otherTile.x && this.y == otherTile.y;
    }

    public boolean checkCollision(int pointX, int pointY){
        return this.x == pointX && this.y == pointY;
    }

    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public void setY(int y){
        this.y = y;
    }

}
