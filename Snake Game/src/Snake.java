import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
public class Snake extends Tile{
    private final int tileSize;
    private int velocityX;
    private int velocityY;
    private Tile head;
    private final Color color;
    private final ArrayList<Tile> body ;

//    Constructor da snake
    public Snake(int startX, int startY, int tileSize, Color color){
        super(startX,startY,tileSize,color);
        this.body = new ArrayList<Tile>();
        this.color = color;
        this.tileSize = tileSize;
        Tile head = new Tile(startX, startY, tileSize, color);
        this.head = head;
        this.velocityX = 1;
        this.velocityY = 0;
        body.add(head);
        for (int i=1;i<4;i++){
            body.add(new Tile(startX, startY + (i*tileSize), tileSize, color));
        }
        
    }

//    Função que retorna a velocityX
    public int getVelocityX(){
        return velocityX;
    }

    //    Função que seta a velocityX
    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    //    Função que retorna a velocityY
    public int getVelocityY(){
        return velocityY;
    }

    //    Função que seta a velocityY
    public void setVelocityY(int velocityY){
        this.velocityY = velocityY;
    }

    //    Função que retorna o corpo da cobra
    public ArrayList<Tile> getBody(){
        return body;
    }

    //    Função que retorna a cabeça da cobra
    public Tile getHead(){
        return head;
    }

    //    Função que retorna a cauda da cobra
    public Tile getTail(){
        return body.get(body.size() - 1);
    }

    //    Função que retorna o tamanho da cobra
    public int getSize(){
        return body.size();
    }

    //    Função que faz a cobra se mover
    public void move(){
        body.remove(body.size() - 1);
        Tile novaCabeca = new Tile((head.getX() + (velocityX*tileSize)), (head.getY() + (velocityY*tileSize)),tileSize,color);
        body.add(0,novaCabeca);
        head = novaCabeca;
    }

    //    Função que desenha o corpo da cobra
    public void draw(Graphics g){
        for (int i=0;i<body.size();i++){
            Tile tile = body.get(i);
            g.setColor(color);
            g.fill3DRect(tile.getX(), tile.getY(), tileSize, tileSize, true);
        }
    }

    //    Função que faz a cobra crescer
    public void grow(){
        int caudaX  = getTail().getX();
        int caudaY = getTail().getY();
        Tile newcauda = new Tile(caudaX, caudaY, tileSize, color);
        body.add((body.size() - 1), newcauda);
    }

    //    Função que checa se a comida colidiu com a cabeça da cobra, se verdadeiro ela cresce e a comida respawna.
    public void checkFoodCollision(Food food){
        if (food.checkCollision(head)){
            grow();
            food.placeFood();
        }
    }
}