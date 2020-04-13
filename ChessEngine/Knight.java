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
        if(fromX!=x||fromY!=y) throw new AssertionError("Piece not at this coordinates");
            if(toX<0||toY<0||toX>board.maxX||toY>board.maxY||(fromX==toX&&toY==fromY)||!(((Math.abs(fromX-toX)==2&&Math.abs(fromY-toY)==1))||(Math.abs(fromX-toX)==1&&Math.abs(fromY-toY)==2)) )throw new AssertionError("Invalid move1 "+fromX+" "+toX+" "+fromY+" "+toY);
            if(board.getAt(toX,toY)==null){
                board.setAt(x, y, null);
                board.setAt(toX, toY, this);
                x = toX;
                y = toY;

                return;
            }else{
                if(board.getAt(toX,toY).player()==player()||board.getAt(toX,toY) instanceof AuxilaryPiece){
                    throw new AssertionError("Invalid move");
                }else{
                    board.setAt(x, y, null);
                    board.setAt(toX, toY, this);
                    x = toX;
                    y = toY;
                }
            }
    }

    @Override
    Player player() {
        return pl;
    }


    @Override
    public String toString() {
        String output =  pl.getColour();
        output +=  "♞";
        output+=reset;
        return output;
    }

    @Override
    void setXY(int x, int y) {
        this.x=x;
        this.y=y;
    }

    @Override
    void unmove(int x, int y) {
        this.x=x;
        this.y=y;
    }
}