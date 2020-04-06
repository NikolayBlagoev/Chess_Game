public class Rook extends Piece {
    private String type="Rook";

    private final String reset="\u001B[0m";;
    private Board board;

    public Rook(Board board,int x, int y, Player pl){

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

    }

    @Override
    Player player() {
        return pl;
    }



    @Override
    public String toString() {
        String output =  pl.getColour();
        output +=  "R";
        output+=reset;
        return output;
    }
}