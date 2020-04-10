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
        if(Math.abs(fromX-toX)!=Math.abs(fromY-toY)||fromX!=x||fromY!=y||toX>board.maxX||toY>board.maxY||fromX==toX||fromY==toY) throw new AssertionError("illegal move");
        move(fromX,fromY,toX,toY,(toX-fromX)/Math.abs(toX-fromX), (toY-fromY)/Math.abs(toY-fromY));
    }


    void move(int fromX, int fromY, int toX, int toY, int xIncrease, int yIncrease){
        if(xIncrease==0||yIncrease==0)throw new AssertionError("illegal move");
        fromX+=xIncrease;
        fromY+=yIncrease;
        if(fromX==toX&&fromY==toY){
            if(board.getAt(fromX,fromY)!=null){
                if(board.getAt(fromX,fromY)instanceof AuxilaryPiece){
                    board.destroyAux();
                    board.setAt(fromX,fromY,this);
                    board.setAt(x,y,null);
                    x=fromX;
                    y=fromY;
                    return;
                }
                if(board.getAt(fromX,fromY).player()==pl){
                    throw new AssertionError("Invalid move. Piece on "+fromX+" "+toX+ " that is from your team");

                }else {

                    board.setAt(fromX,fromY,this);
                    board.setAt(x,y,null);
                    x=fromX;
                    y=fromY;
                    return;
                }
            }else{
                board.setAt(fromX,fromY,this);
                board.setAt(x,y,null);
                x=fromX;
                y=fromY;
                return;
            }
        }else{
            System.out.println("59 here "+fromY+" "+fromX+ " "+xIncrease+" "+yIncrease );
            if(board.getAt(fromX,fromY)!=null&&!(board.getAt(fromX,fromY)instanceof  AuxilaryPiece)){
                System.out.println("60 here");
                throw new AssertionError("Invalid move. Piece on "+fromX+" "+toX);
            }else{
                move(fromX, fromY, toX, toY,  xIncrease,  yIncrease);
                return;
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
        output +=  "♝";
        output+=reset;
        return output;
    }
}