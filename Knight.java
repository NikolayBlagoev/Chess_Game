public class Knight extends Piece {
    private String type="Knight";

    private Board board;

    private final String reset="\u001B[0m";;
    public Knight(Board board,int x, int y, Player pl){
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
        output +=  "N";
        output+=reset;
        return output;
    }
}