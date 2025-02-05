import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Board extends JPanel implements ActionListener, KeyListener{
    private final int boardWidth;
    private final int boardHeight;
    private final int tileSize = 25;
    private Timer timer;
    private Snake snake;
    private Food food;
    private boolean gameOver = false;
    private boolean pause = false;

    public Board(int boardHeight, int boardWidth){
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        this.food = null;
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(this);
        startGame();
    }

    public void startGame(){
        snake = new Snake(0, 0, tileSize, Color.GREEN);
        food = new Food(boardWidth/tileSize, boardHeight/tileSize, tileSize, Color.PINK);
        timer = new Timer(250,this);
        timer.start();
        food.placeFood();
        gameOver = false;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.darkGray);
        for(int i = 0; i< boardWidth / tileSize;i++){
            g.drawLine(i * tileSize, 0, i * tileSize, boardHeight);
        }
        for(int i = 0; i < boardHeight / tileSize; i++) {
            g.drawLine(0, i * tileSize, boardWidth, i * tileSize);
        }
        food.draw(g);
        snake.draw(g);
        drawScore(g);
    }


    public boolean isTileInsideBoard(Tile tile){
        return tile.getX() >= 0 && tile.getX() < boardWidth && tile.getY() >= 0 && tile.getY() < boardHeight;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver){
            timer.stop();
        } else{
            checkFoodOnSnake(food);
            snake.checkFoodCollision(food);
            snake.move();
            checkBoardCollsion();
            checkSelfCollsion();
            repaint();
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_R && gameOver){
            startGame();
        }
        if (e.getKeyCode() == KeyEvent.VK_P){
            if (!pause){
                pause = true;
                timer.stop();
            } else {
                pause = false;
                timer.restart();
            }}
        if (e.getKeyCode() == KeyEvent.VK_UP && snake.getVelocityY() != 1){
            snake.setVelocityY(-1);
            snake.setVelocityX(0);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && snake.getVelocityY() != -1){
            snake.setVelocityY(1);
            snake.setVelocityX(0);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && snake.getVelocityX() != 1){
            snake.setVelocityX(-1);
            snake.setVelocityY(0);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && snake.getVelocityX() != -1){
            snake.setVelocityX(1);
            snake.setVelocityY(0);}
        }


    @Override
    public void keyReleased(KeyEvent e) {
    }


    public void checkBoardCollsion(){
        if (!isTileInsideBoard(snake.getHead())){
            gameOver = true;
        }
    }


    public void checkSelfCollsion(){
        ArrayList<Tile> body = snake.getBody();
        Tile head = snake.getHead();
        for (int i=1;i<(body.size());i++){
            if (head.getX() == body.get(i).getX() && head.getY() == body.get(i).getY()) {
                gameOver = true;
            }
        }
    }


    public void drawScore(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        int score = (snake.getSize() - 4);
        if (gameOver){
            g.drawString("Game Over! Score: " + score, 10, 20);
        } else{
            g.drawString("Score: " + score, 10, 20);
        }
    }


    public void checkFoodOnSnake(Food food){
        ArrayList<Tile> body = snake.getBody();
        for (int i=1;i < body.size();i++){
            if (food.getX() == body.get(i).getX() && food.getY() == body.get(i).getY()){
                food.placeFood();
            }
        }
    }
}



