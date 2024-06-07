package id.dojo.things;

public interface AnimalBehavior {
    Cell checkForward();
    void stepForward(Board board);
    Cell checkLeft();
    void moveLeft(Board board);
    Cell checkRight();
    void moveRight(Board board);
    void eat();
}
