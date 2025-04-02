import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class Board {
    
    private Random random; 
    private int rows;
    private int cols;
    private int size;
    private Map<Integer, Integer> snakes;
    private Map<Integer, Integer> elevators;
    private int snakeCount = 5; // default count, can be changed
    private int elevatorCount = 5;
    
    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.size = rows * cols;
        this.random = new Random();
        this.snakes = new HashMap<>();
        this.elevators = new HashMap<>();
    
        setupBoard();
    }
    
    private int calculatePos(int row, int col) { 
        return row * cols + col + 1;
    }
    
    private void setupBoard() {
        for (int i = 0; i < snakeCount; i++) {
            int startRow = random.nextInt(rows - 1) + 1;
            int endRow = random.nextInt(startRow);
            int startCol = random.nextInt(cols);
            int endCol = random.nextInt(cols);
            
            int startPos = calculatePos(startRow, startCol);
            int endPos = calculatePos(endRow, endCol);
            
            if (!snakes.containsKey(startPos) && startPos > endPos) {
                addSnake(startPos, endPos);
            }
        }
        
        for (int i = 0; i < elevatorCount; i++) {
            int startRow = random.nextInt(rows - 1);
            int endRow = random.nextInt(rows - startRow - 1) + startRow + 1;
            int startCol = random.nextInt(cols);
            int endCol = random.nextInt(cols);
            
            int startPos = calculatePos(startRow, startCol);
            int endPos = calculatePos(endRow, endCol);
            
            if (!elevators.containsKey(startPos) && startPos < endPos) {
                addElevator(startPos, endPos);
            }
        }
    }
    
    public boolean isSnake(int position) {
        return snakes.containsKey(position);
    }
    
    public boolean isElevator(int position) {
        return elevators.containsKey(position);
    }
    
    public void addSnake(int start, int end) {
        snakes.put(start, end);
    }
    
    public void addElevator(int start, int end) {
        elevators.put(start, end);
    }
    
    public int getNewPos(int position) {
        if (snakes.containsKey(position)) {
            return snakes.get(position);
        } else if (elevators.containsKey(position)) {
            return elevators.get(position);
        }
        return position;
    }
    
    public void printBoard() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int pos = calculatePos(r, c);
                if (snakes.containsKey(pos)) {
                    System.out.print("S ");
                } else if (elevators.containsKey(pos)) {
                    System.out.print("E ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }
}
