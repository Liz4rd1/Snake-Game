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

//        Define que a janela irá fechar com base no sistema operacional
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        Comando para não permitir o redimensionamento da tela
        frame.setResizable(false);

//        Cria o objeto Board
        Board board = new Board(boardHeight, boardWidth);
//        Adiciona Board ao Frame
        frame.add(board);

        frame.pack();
        frame.setLocationRelativeTo(null);
//        Faz com que a janela seja visível
        frame.setVisible(true);
    }
}

