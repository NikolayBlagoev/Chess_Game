public class Queen extends Piece {
    private String type="Queen";
    private int team;
    private Board board;
    public Queen(int team, Board board,int x, int y){
        this.team=team;
        this.board=board;
        this.x=x;
        this.y=y;
    }
    private int x,y;


    @Override
    String name() {
        return type;
    }

    @Override
    void move(int fromX, int fromY, int toX, int toY) {

    }

    @Override
    int team() {
        return team;
    }

    @Override
    public String toString() {
        return "Q";
    }
}