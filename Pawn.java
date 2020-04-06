public class Pawn extends Piece {
    private String type="Pawn";

    private boolean moved=false;


    private final String reset="\u001B[0m";;
    private Board board;

    public Pawn(Board board,int x, int y, Player pl){

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
    void move(int fromX, int fromY, int toX, int toY) throws AssertionError{
        if(fromX!=x&&fromY!=y) throw new AssertionError("Invalid move1");
        if(!pl.isOrientationAxis()) {
            if (toX != x) {

                if (Math.abs(toX - fromX) != 1 || Math.abs(toY - fromY) != 1) {

                    throw new AssertionError("Invalid move2");
                }
                if (board.getAt(toX, toY) == null || board.getAt(toX, toY).player() == pl) {
                    throw new AssertionError("Invalid move3");
                } else {
                    if(board.getAt(toX,toY) instanceof AuxilaryPiece){
                        ((AuxilaryPiece) board.getAt(toX,toY)).take();
                    }
                    board.setAt(x, y, null);
                    board.setAt(toX, toY, this);
                    x = toX;
                    y = toY;
                }
                moved = true;
                return;
            }
            if (Math.abs(fromY - toY) > 2||(pl.isOrientationDirection()&&toY<fromY)||(!pl.isOrientationDirection()&&toY>fromY)) throw new AssertionError("Invalid move4 "+toY+" "+fromY+" "+pl.isOrientationDirection());
            if (Math.abs(fromY - toY) == 2) {
                if (moved) throw new AssertionError("Invalid move");
                if (board.getAt(toX, toY) != null || board.getAt(toX, (toY + fromY) / 2) != null)
                    throw new AssertionError("Invalid move5");
                board.setAt(x, y, null);

                board.setAt(toX, toY, this);
                board.setAuxilary(x,(toY+fromY)/2, this);
                x = toX;
                y = toY;

                moved = true;
                return;
            }
            if (Math.abs(fromY - toY) == 1) {
                if (board.getAt(toX, toY) != null) throw new AssertionError("Invalid move6");
                board.setAt(x, y, null);
                board.setAt(toX, toY, this);
                x = toX;
                y = toY;
                moved = true;
                return;
            }
        }else{

        }

    }

    @Override
    Player player() {
        return pl;
    }


    @Override
    public String toString() {
        String output =  pl.getColour();
        output +=  "P";
        output+=reset;
        return output;
    }


    public void take() {
        board.setAt(x,y,null);
    }

}
