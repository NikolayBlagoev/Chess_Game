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
        if(fromX!=x||fromY!=y) throw new AssertionError("Piece not at this coordinates");
        if(fromX!=toX&&fromY!=toY||toX<0||toY<0||toX>board.maxX||toY>board.maxY )throw new AssertionError("Invalid move1 "+fromX+" "+toX+" "+fromY+" "+toY);
        if(fromX==toX){
            move(fromX,fromY,toX,toY,0, (toY-fromY)/Math.abs(toY-fromY));
        }else{
            move(fromX,fromY,toX,toY,(toX-fromX)/Math.abs(toX-fromX), 0);
        }
    }

    void move(int fromX, int fromY, int toX, int toY, int xIncrease, int yIncrease){
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
        output +=  "R";
        output+=reset;
        return output;
    }
}