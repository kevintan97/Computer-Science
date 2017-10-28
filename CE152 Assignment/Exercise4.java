
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author ervin c.
 */
public class Exercise4 {

    private static String[][] maze;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the name of the file: ");

        String fileName = sc.next();

        try {
            parseMaze(fileName);
            printMaze();
            drawMaze();
        } catch (IOException ex) {
            System.out.println("File " + fileName + " was not found!\r\n");
        }
    }

    private static void parseMaze(String fileName) throws IOException {
        try (Scanner file = new Scanner(new File(fileName))) {
            List<String[]> mazeLines = new ArrayList<String[]>();
            while (file.hasNextLine()) {
                String[] mazeLine = file.nextLine().split("");
                mazeLines.add(mazeLine);
            }

            maze = new String[mazeLines.size()][];

            for (int i = 0; i < mazeLines.size(); i++) {
                maze[i] = mazeLines.get(i);
            }
        }
    }

    private static void printMaze() {
        int lines = maze.length;

        for (int i = 0; i < lines; i++) {
            for (String str : maze[i]) {
                System.out.print(str);
            }
            System.out.print(System.getProperty("line.separator"));
        }
    }

    private static void drawMaze() {
        JFrame frame = new JFrame();
        Surface surface = new Surface();
        JButton resetButton = new JButton("Reset");

        surface.setLayout(null);

        resetButton.addActionListener(surface);
        resetButton.setBounds(0, maze.length * 10, maze.length * 10, 30);
        surface.add(resetButton);

        Dimension size = new Dimension(maze.length * 10, (maze.length * 10) + 30);

        surface.setPreferredSize(size);
        surface.requestFocus();

        frame.getContentPane().add(surface);
        frame.setResizable(false);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        surface.requestFocus();
    }

    public static String[][] getMaze() {
        return maze;
    }
}

class Surface extends JPanel implements KeyListener, ActionListener {

    private String[][] maze;
    private int mazeSize;

    // position of blue circle
    private int circlePosX = 1;
    private int circlePosY = 1;

    public Surface() {
        this.addKeyListener(this);
        maze = Exercise4.getMaze();
        mazeSize = maze.length;
    }

    @Override
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    private void draw(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;

        for (int y = 0; y < mazeSize; y++) {
            for (int x = 0; x < maze[y].length; x++) {
                if (maze[y][x].equals("#")) {
                    graphics.fillRect(x * 10, y * 10, 10, 10);
                }
            }
        }

        graphics.setColor(Color.blue);
        graphics.fillOval((mazeSize - 2) * 10, (mazeSize - 2) * 10, 10, 10);

        graphics.setColor(Color.red);
        graphics.fillOval(circlePosX * 10, circlePosY * 10, 10, 10);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int addX = 0, addY = 0;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                addX = 1;
                break;
            case KeyEvent.VK_LEFT:
                addX = -1;
                break;
            case KeyEvent.VK_UP:
                addY = -1;
                break;
            case KeyEvent.VK_DOWN:
                addY = 1;
                break;
        }

        if (validBounds(circlePosX + addX, circlePosY + addY)) {
            circlePosX += addX;
            circlePosY += addY;
        }

        repaint();

        if ((circlePosX == (mazeSize - 2)) && (circlePosY == (mazeSize - 2))) {
            JOptionPane.showMessageDialog(this, "Congratulations!");
            actionPerformed(null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        circlePosX = 1;
        circlePosY = 1;

        repaint();
    }

    private boolean validBounds(int posX, int posY) {
        String[][] maze = Exercise4.getMaze();

        return !(maze[posY][posX].equals("#"));
    }

}
