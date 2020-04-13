public class Queen extends Piece {

    private String type="Queen";
    private final String reset="\u001B[0m";;
    private Board board;


    public Queen(Board board,int x, int y, Player pl){

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
        if(fromX==toX&&fromY==toY||toX<0||toY<0||toX>board.maxX||toY>board.maxY )throw new AssertionError("Invalid move1 "+fromX+" "+toX+" "+fromY+" "+toY);
        if(fromX==toX){
            move(fromX,fromY,toX,toY,0, (toY-fromY)/Math.abs(toY-fromY));
        }else if(fromY==toY){
            move(fromX,fromY,toX,toY,(toX-fromX)/Math.abs(toX-fromX), 0);
        }else{
            move(fromX,fromY,toX,toY,(toX-fromX)/Math.abs(toX-fromX), (toY-fromY)/Math.abs(toY-fromY));
        }
    }

    void move(int fromX, int fromY, int toX, int toY, int xIncrease, int yIncrease){
        fromX+=xIncrease;
        fromY+=yIncrease;
        if(fromX<0||fromY<0||fromX>board.maxX||fromY>board.maxY) throw new  AssertionError("Invalid move2 "+fromX+" "+toX+" "+fromY+" "+toY);

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
        output +=  "♛";
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