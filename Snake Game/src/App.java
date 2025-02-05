import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.io.IOException;

public class App {
    public static void main(String[] args)  throws IOException {
//        Define altura e largura do campo do jogo
        int boardWidth = 600;
        int boardHeight = 600;

//        Cria a janela do jogo
        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        Comando para não permitir o redimensionamento da tela
        frame.setResizable(false);

//        Cria o objeto Board
        Board board = new Board(boardHeight, boardWidth);
        frame.add(board);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

