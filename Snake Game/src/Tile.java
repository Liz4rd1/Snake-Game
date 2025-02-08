import java.awt.Color;
import java.awt.Graphics;

public class Tile {
    private int x;
    private int y;
    private final int tileSize;
    private final Color color;

//    Constructor do tile
    public Tile(int x, int y, int tileSize, Color color){
        this.x = x;
        this.y = y;
        this.tileSize = tileSize;
        this.color = color;
    }

    //    Função que desenha o tile
    public void draw(Graphics g){
        g.setColor(this.color);
        g.fill3DRect(this.x, this.y, this.tileSize, tileSize, true);
    }

    //    Função que retorna verdadeiro, se um tile colidiu com outro tile
    public boolean checkCollision(Tile otherTile){
        return this.x == otherTile.x && this.y == otherTile.y;
    }

    //    Função que retorna verdadeiro, se um ponto colidiu com outro ponto.
    public boolean checkCollision(int pointX, int pointY){
        return this.x == pointX && this.y == pointY;
    }

    //    Função que retorna a coordenada X do tile
    public int getX(){
        return x;
    }

    //    Função que seta a coordenada X do tile
    public void setX(int x){
        this.x = x;
    }

    //    Função que retorna a coordenada Y do tile
    public int getY(){
        return y;
    }

    //    Função que seta a coordenada Y do tile
    public void setY(int y){
        this.y = y;
    }

}
