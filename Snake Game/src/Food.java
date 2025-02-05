import java.awt.Color;
import java.util.Random;
public class Food extends Tile{
    private final int maxX, maxY, tileSize;
    private Color color;
    private final Random random;

    public Food (int maxX, int maxY, int tileSize, Color color){
        super(0,0,tileSize,color);
        this.maxX = maxX;
        this.maxY = maxY;
        this.tileSize = tileSize;
        this.random = new Random();
    }

    public void placeFood(){
        int newX = random.nextInt(maxX) * tileSize;
        int newY = random.nextInt(maxY) * tileSize;
        setX(newX);
        setY(newY);
    }
}