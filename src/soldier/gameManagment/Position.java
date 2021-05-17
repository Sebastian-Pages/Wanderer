package soldier.gameManagment;

public class Position {
    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int distance(Position p){
        return (int) Math.sqrt(  (x-p.getX())*(x-p.getX()) + (y-p.getY())*(y-p.getY())  );
    }

}
