//Importa bibliotecas importantes
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

//Cria a classe Board
public class Board extends JPanel implements ActionListener, KeyListener{
    private final int boardWidth;
    private final int boardHeight;
//    Define o tamanho do tile
    private final int tileSize = 25;
    private Timer timer;
    private Snake snake;
    private Food food;
    private boolean gameOver = false;
    private boolean pause = false;

//    Constructor
    public Board(int boardHeight, int boardWidth){
//        Pega as dimensões desejadas para a janela e cria um objeto novo com tais dimensões
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        this.food = null;
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(this);
        startGame();
    }

//    Função que inicia o jogo
    public void startGame(){
//        Cria a cobra
        snake = new Snake(0, 0, tileSize, Color.GREEN);
//        Cria a comida
        food = new Food(boardWidth/tileSize, boardHeight/tileSize, tileSize, Color.PINK);
//        Dá o tempo em que ocorrerá as atualizações na tela
        timer = new Timer(250,this);
        timer.start();
//        Coloca a comida em um lugar aleatório da tela
        food.placeFood();
//        Coloca o game over como falso para iniciar o jogo
        gameOver = false;
    }

//    Metódo que vai desenhar os tiles na tela
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
//        Coloca a cor como cinza escuro
        g.setColor(Color.darkGray);
//        Loop para criar linhas para representar a grade na horizontal
        for(int i = 0; i< boardWidth / tileSize;i++){
            g.drawLine(i * tileSize, 0, i * tileSize, boardHeight);
        }
//        Loop para criar linhas para representar a grade na vertical
        for(int i = 0; i < boardHeight / tileSize; i++) {
            g.drawLine(0, i * tileSize, boardWidth, i * tileSize);
        }
//        Desenha a comida na tela
        food.draw(g);
//        Desenha a cobra na tela
        snake.draw(g);
//        Desenha a pontuação na tela
        drawScore(g);
    }


//    Função que verifica se um dado tile está dentro dos limites do Board e retorna true ou false
    public boolean isTileInsideBoard(Tile tile){
        return tile.getX() >= 0 && tile.getX() < boardWidth && tile.getY() >= 0 && tile.getY() < boardHeight;
    }


//    Função que irá executar a cada atualização do jogo (Timer)
    @Override
    public void actionPerformed(ActionEvent e) {
//        Verifica se o jogo acabou
        if (gameOver){
//            Caso seja verdadeiro as atualizações param
            timer.stop();
        } else{
//            Senão o jogo irá continuar normalmente
//            Função que verifica se a comida nasceu dentro da cobra
            food.checkFoodOnSnake(snake);
//            Verifica se a cobra comeu uma comida
            snake.checkFoodCollision(food);
//            Continua o movimento da cobra
            snake.move();
//            Verifica se a cobra colidiu com o tabuleiro
            checkBoardCollsion();
//            Verifica se a cobra colidiu com si mesma
            checkSelfCollsion();
//            Desenhar tudo de novo com as novas mudanças
            repaint();
        }
    }

//Função que não executa nada
    @Override
    public void keyTyped(KeyEvent e) {
    }

//Função que irá verificar as teclas apertadas
    @Override
    public void keyPressed(KeyEvent e) {
//        Verifica se a tecla apertada foi R e o gamer over é verdadeiro
        if (e.getKeyCode() == KeyEvent.VK_R && gameOver){
//            Se verdade, ele reinicia o jogo chamando a função de iniciar o jogo
            startGame();
        }
//        Verifica se a tecla pressionada foi a tecla P
        if (e.getKeyCode() == KeyEvent.VK_P){
//            Verifica se o jogo não está pausado
            if (!pause){
//                Se verdadeiro, ele seta pause como verdadeiro e para o timer, pausando o jogo
                pause = true;
                timer.stop();
            } else {
//                Se o jogo estiver pausado, ele seta o pause como false e retorna o jogo, ou seja, despausa o jogo
                pause = false;
                timer.restart();
            }}
//        Verifica se a tecla apertada foi a seta para cima e se o velocityY é diferente de 1 = se a cobra não está indo para baixo.
        if (e.getKeyCode() == KeyEvent.VK_UP && snake.getVelocityY() != 1){
//            Seleciona a direção da cobra para cima
            snake.setVelocityY(-1);
            snake.setVelocityX(0);
        }
//        Senão verifica a se a tecla pressionada foi a seta para baixo e com o velocityY diferente de -1 = indo para cima.
        else if (e.getKeyCode() == KeyEvent.VK_DOWN && snake.getVelocityY() != -1){
//            Seleciona a direção da cobra para baixo
            snake.setVelocityY(1);
            snake.setVelocityX(0);
        }
        //        Verificação da tecla seta para esquerda e velocityX diferente de 1 = indo para a direita
        else if (e.getKeyCode() == KeyEvent.VK_LEFT && snake.getVelocityX() != 1){
//            Seleciona a direção da cobra para esquerda
            snake.setVelocityX(-1);
            snake.setVelocityY(0);
        }
        //        Verificação da tecla seta para direita e velocityX diferente de -1 = indo para a esquerda
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && snake.getVelocityX() != -1){
//            Seleciona a direção da cobra para direita
            snake.setVelocityX(1);
            snake.setVelocityY(0);}
        }


    @Override
    public void keyReleased(KeyEvent e) {
    }


//    Função que verifica se a cabeça da cobra está nos limites do tabuleiro.
    public void checkBoardCollsion(){
//        Se falso, o jogo termina
        if (!isTileInsideBoard(snake.getHead())){
            gameOver = true;
        }
    }


//    Verifica se a cobra colidiu com alguma parte do corpo
    public void checkSelfCollsion(){
        ArrayList<Tile> body = snake.getBody();
        Tile head = snake.getHead();
        for (int i=1;i<(body.size());i++){
//            Se verdadeiro o jogo termina
            if (head.getX() == body.get(i).getX() && head.getY() == body.get(i).getY()) {
                gameOver = true;
            }
        }
    }


//    Função que desenha na tela a pontuação do jogador.
    public void drawScore(Graphics g){
//        Seleciona a cor e a fonte da letra.
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        int score = (snake.getSize() - 4);
//        Verifica se o jogo terminou, se verdadeiro muda o texto do score.
        if (gameOver){
            g.drawString("Game Over! Score: " + score, 10, 20);
        } else{
            g.drawString("Score: " + score, 10, 20);
        }
    }

}



