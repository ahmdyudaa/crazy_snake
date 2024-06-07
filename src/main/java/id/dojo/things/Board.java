package id.dojo.things;
import id.dojo.models.Point;

import java.util.ArrayList;
import java.util.List;

public class Board extends Thing {
    private final int row;
    private final int col;

    private final List<List<Cell>>board;

    public Board(String name, String appearance, int row, int col) {
        super(name, appearance);
        this.row = row;
        this.col = col;

        board = new ArrayList<>();
        for(int i = 0; i < this.row; i++){
            board.add(new ArrayList<>());
            for(int j = 0; j < this.col; j++){
                board.get(i).add(new Cell("cell", " ", i, j));
            }
        }
    }

    public List<List<Cell>> getBoard(){
        return board;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void displayBoard(){
        for(List<Cell> row : this.board){
            for(Cell cell : row){
                cell.displayCell();
            }
            //Pindah baris
            System.out.println();
        }
    }
    //meletakkan benda ke dalam cell, parameter
    public void putObject(Point point, Thing thing){
        if(thing != null){
            //mengakses row dan col -> masukan benda
            this.getBoard().get(point.getX()).get(point.getY()).addThing(thing);
        }else{
            this.getBoard().get(point.getX()).get(point.getY()).removeThing();
        }
    }
}
