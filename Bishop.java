public class Bishop extends Piece {
    private String type="Bishop";

    private final String reset="\u001B[0m";;
    private Board board;
    public Bishop(Board board,int x, int y, Player pl){
        this.board=board;
        this.x=x;
        this.y=y;
        this.pl=pl;
    }
    private Player pl;
    private int x,y;


    @Override
    String name() {
        return type + " of team "+pl.getName();
    }

    @Override
    void move(int fromX, int fromY, int toX, int toY) {
        if(Math.abs(fromX-toX)!=Math.abs(fromY-toY)||fromX!=x||fromY!=y||toX>board.maxX||toY>board.maxY) throw new AssertionError("illegal move");

    }

    @Override
    Player player() {
        return pl;
    }


    @Override
    public String toString() {
        String output =  pl.getColour();
        output +=  "B";
        output+=reset;
        return output;
    }
}