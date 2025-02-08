import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
public class Food extends Tile{
    private final int maxX, maxY, tileSize;
    private Color color;
    private final Random random;

//    Constructor da comida
    public Food (int maxX, int maxY, int tileSize, Color color){
        super(0,0,tileSize,color);
        this.maxX = maxX;
        this.maxY = maxY;
        this.tileSize = tileSize;
        this.random = new Random();
    }

//    Função que faz a comida nascer em um local aleatório do tabuleiro.
    public void placeFood(){
        int newX = random.nextInt(maxX) * tileSize;
        int newY = random.nextInt(maxY) * tileSize;
        setX(newX);
        setY(newY);
    }

    //    Função que verifica se a comida nasceu dentro da cobra.
    public void checkFoodOnSnake(Snake snake){
        ArrayList<Tile> body = snake.getBody();
        for (int i=1;i < body.size();i++){
            if (getX() == body.get(i).getX() && getY() == body.get(i).getY()){
                placeFood();
            }
        }
    }
}