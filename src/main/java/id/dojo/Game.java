
package id.dojo;
import id.dojo.models.Point;
import id.dojo.things.Board;
import id.dojo.things.Cell;
import id.dojo.things.Snake;
import id.dojo.things.Wall;

import java.io.IOException;
import java.util.List;

// Class untuk mengontroll jalannya game, mengatur board, snake, dll
public class Game {
    private Board board;
    private List<Wall> walls;
    private Snake snake;
    private int speed;

    public void render() throws InterruptedException, IOException {
        while(true){
            board.displayBoard();
            snake.stepForward(board);
            Thread.sleep(100);

            new ProcessBuilder("clear").inheritIO().start().waitFor();
        }
    }

    public static Builder getBuilder(){
        return new Builder();
    }
    public Game(Builder builder){
        this.board = builder.board;
        this.walls = builder.walls;
        this.snake = builder.snake;
        this.speed = builder.speed;
    }

    public static class Builder{
        Board board;
        List<Wall> walls;
        Snake snake;
        int speed;

        public Builder createBoard(int row, int col){
            board = new Board("Board", "", row, col);

            return this;
        }

        public Builder createWalls(){
            //method untuk membuat dinding area game
            int row = board.getRow();
            int col = board.getCol();

            for(int i = 0; i < row; i++){
                for(int j = 0; j< col; j++){
                    if(i == 0 || i == row - 1 || j == 0 || j == col - 1){
                        board.putObject(new Point(i,j), new Wall("Wall", " * "));
                    }
                }
            }
            return this;
        }

        public Builder createSnake(Snake snake){
            this.snake = snake;
            return this;
        }

        //dipake untuk membuat objek ular dan fruit
        public Builder generatePopulation(){
            board.putObject(snake.getHead(), snake);
            return this;
        }

        public Game build(){
            return new Game(this);
        }
    }
}
//package id.dojo;
//
//import id.dojo.models.Point;
//import id.dojo.things.Board;
//import id.dojo.things.Snake;
//import id.dojo.things.Wall;
//
//import java.io.IOException;
//import java.util.List;
//
//public class Game {
//    private Board board;
//    private List<Wall> walls;
//    private Snake snake;
//    private int speed;
//
//    public void render() throws InterruptedException, IOException {
//        while (true) {
//            board.displayBoard();
//            snake.stepForward(board);
//            Thread.sleep(100);
//            new ProcessBuilder("clear").inheritIO().start().waitFor();
//        }
//    }
//
//    public static Builder getBuilder() {
//        return new Builder();
//    }
//
//    public Game(Builder builder) {
//        this.board = builder.board;
//        this.walls = builder.walls;
//        this.snake = builder.snake;
//        this.speed = builder.speed;
//    }
//
//    public static class Builder {
//        Board board;
//        List<Wall> walls;
//        Snake snake;
//        int speed;
//
//        public Builder createBoard(int row, int col) {
//            board = new Board("Board", "", row, col);
//            return this;
//        }
//
//        public Builder createWalls() {
//            int row = board.getRow();
//            int col = board.getCol();
//
//            for (int i = 0; i < row; i++) {
//                for (int j = 0; j < col; j++) {
//                    if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
//                        board.putObject(new Point(i, j), new Wall("Wall", " * "));
//                    }
//                }
//            }
//            return this;
//        }
//
//        public Builder createSnake(Snake snake) {
//            this.snake = snake;
//            return this;
//        }
//
//        public Builder generatePopulation() {
//            board.putObject(snake.getHead(), snake);
//            for (Point segment : snake.getBody()) {
//                board.putObject(segment, snake);
//            }
//            return this;
//        }
//
//        public Game build() {
//            return new Game(this);
//        }
//    }
//}
