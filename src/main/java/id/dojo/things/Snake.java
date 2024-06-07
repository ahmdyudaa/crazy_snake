package id.dojo.things;

import id.dojo.models.Point;

import java.util.ArrayList;
import java.util.List;

public class Snake extends Thing implements AnimalBehavior {

    private int size;
    private Point head;
    private List<Point> body;

    public Snake(Builder builder) {
        super(builder.getName(), builder.getAppearance());
        this.head = builder.getPosition();
        this.size = builder.getSize();

        body = new ArrayList<>();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Point getHead() {
        return head;
    }

    public void setHead(Point head) {
        this.head = head;
    }

    public void generateBody(Board board, Snake snake){

//        board.putObject(body.add(head.getX()-1, head.getY()-1), snake);
        int headPosX = getHead().getX();
        int headPosY = getHead().getY();
        for(int i = 0; i < size; i++){
            body.add(new Point(--headPosX, headPosY));
        }
    }

    @Override
    public Cell checkForward() {
        return null;
    }

    @Override
    public void stepForward(Board board){

        int headXbefore = head.getX();
        int headYbefire = head.getY();

//        int posX = 0;
        int posY = head.getY();
//        int posY = 0;
        board.putObject(head, null);

        head.setY(posY+1);
        board.putObject(head, this);

//        if(head.getY() - 1 == body.get(0).getX()){
//            //arah ke bawah
//            posX = headXbefore + 1;
//            posY = headYbefire;
//
//        }
//        return new Point(posX, posY);
    }

    @Override
    public Cell checkLeft() {
        return null;
    }

    @Override
    public void moveLeft(Board board) {

    }

    @Override
    public Cell checkRight() {
        return null;
    }

    @Override
    public void moveRight(Board board) {

    }

    @Override
    public void eat() {

    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public static class Builder{
        private int size;
        private String name;
        private String appearance;
        private int posX, posY;


        public int getSize(){
            return size;
        }

        public Builder setSize(int s){
         size = s;
         return this;
        }
        public String getName(){
            return name;
        }

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public String getAppearance(){
            return appearance;
        }

        public Builder setAppearance(String appearance){
            this.appearance = appearance;
            return this;
        }

        public Point getPosition(){
            return new Point(posX, posY);
        }

        public Builder setPosition(int x, int y){
            posX = x;
            posY = y;
            return this;
        }

        public Snake build(){
            return new Snake(this);
        }



    }
}


//package id.dojo.things;
//
//import id.dojo.models.Point;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//public class Snake extends Thing implements AnimalBehavior {
//
//    private int size;
//    private Point head;
//    private List<Point> body;
//
//    public Snake(Builder builder) {
//        super(builder.getName(), builder.getAppearance());
//        this.head = builder.getPosition();
//        this.size = builder.getSize();
//        this.body = new ArrayList<>();
//        // Initialize the body behind the head
//        for (int i = 1; i <= size; i++) {
//            body.add(new Point(head.getX(), head.getY() - i));
//        }
//    }
//
//    public int getSize() {
//        return size;
//    }
//
//    public void setSize(int size) {
//        this.size = size;
//    }
//
//    public Point getHead() {
//        return head;
//    }
//
//    public void setHead(Point head) {
//        this.head = head;
//    }
//
//    public List<Point> getBody() {
//        return body;
//    }
//
//    public void setBody(List<Point> body) {
//        this.body = body;
//    }
//
//    @Override
//    public Cell checkForward() {
//        return null;
//    }
//
//    @Override
//    public void stepForward(Board board) {
//        Point nextHead = new Point(head.getX(), head.getY() + 1);
//
//        // Check for wall collision
//        if (isCollision(board, nextHead)) {
//            moveRandom(board);
//            return;
//        }
//
//        move(board, nextHead);
//    }
//
//    @Override
//    public Cell checkLeft() {
//        return null;
//    }
//
//    @Override
//    public void moveLeft(Board board) {
//
//    }
//
//    @Override
//    public Cell checkRight() {
//        return null;
//    }
//
//    @Override
//    public void moveRight(Board board) {
//
//    }
//
//    @Override
//    public void eat() {
//
//    }
//
//    private boolean isCollision(Board board, Point nextHead) {
//        return nextHead.getX() < 0 || nextHead.getX() >= board.getRow() ||
//                nextHead.getY() < 0 || nextHead.getY() >= board.getCol() ||
//                board.getBoard().get(nextHead.getX()).get(nextHead.getY()).getThing() instanceof Wall;
//    }
//
//    private void moveRandom(Board board) {
//        Random random = new Random();
//        List<Point> possibleMoves = new ArrayList<>();
//
//        Point[] directions = new Point[]{
//                new Point(head.getX() - 1, head.getY()), // up
//                new Point(head.getX() + 1, head.getY()), // down
//                new Point(head.getX(), head.getY() - 1), // left
//                new Point(head.getX(), head.getY() + 1)  // right
//        };
//
//        for (Point direction : directions) {
//            if (!isCollision(board, direction)) {
//                possibleMoves.add(direction);
//            }
//        }
//
//        if (!possibleMoves.isEmpty()) {
//            Point nextHead = possibleMoves.get(random.nextInt(possibleMoves.size()));
//            move(board, nextHead);
//        }
//    }
//
//    private void move(Board board, Point nextHead) {
//        // Remove tail from the board
//        Point tail = body.remove(body.size() - 1);
//        board.putObject(tail, null);
//
//        // Add current head to the body
//        body.add(0, new Point(head.getX(), head.getY()));
//
//        // Update the head position
//        board.putObject(head, null);
//        head = nextHead;
//        board.putObject(head, this);
//    }
//
//    public static Builder getBuilder() {
//        return new Builder();
//    }
//
//    public static class Builder {
//        private int size;
//        private String name;
//        private String appearance;
//        private int posX, posY;
//
//        public int getSize() {
//            return size;
//        }
//
//        public Builder setSize(int size) {
//            this.size = size;
//            return this;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public Builder setName(String name) {
//            this.name = name;
//            return this;
//        }
//
//        public String getAppearance() {
//            return appearance;
//        }
//
//        public Builder setAppearance(String appearance) {
//            this.appearance = appearance;
//            return this;
//        }
//
//        public Point getPosition() {
//            return new Point(posX, posY);
//        }
//
//        public Builder setPosition(int x, int y) {
//            this.posX = x;
//            this.posY = y;
//            return this;
//        }
//
//        public Snake build() {
//            return new Snake(this);
//        }
//    }
//}
