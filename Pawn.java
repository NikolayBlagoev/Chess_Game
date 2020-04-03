public class Pawn extends Piece {
    private String type="Pawn";
    private int team;
    private boolean moved=false;
    private Board board;

    private int orientation;
    public Pawn(int team, Board board,int x, int y){
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
    void move(int fromX, int fromY, int toX, int toY) throws AssertionError{
        if(fromX!=x&&fromY!=y) throw new AssertionError("Invalid move1");

        if(toX!=x){

            if(Math.abs(toX-fromX)!=1||Math.abs(toY-fromY)!=1) {

                throw new AssertionError("Invalid move2");
            }
            if(board.getAt(toX, toY)==null||board.getAt(toX, toY).team()==team){
                throw new AssertionError("Invalid move3");
            }else{
                board.setAt(x,y, null);
                board.setAt(toX,toY, this);
                x=toX;
                y=toY;
            }
            moved=true;
            return;
        }
        if(Math.abs(fromY-toY)>2)  throw new AssertionError("Invalid move4");
        if(Math.abs(fromY-toY)==2){
            if(moved)throw new AssertionError("Invalid move");
            if(board.getAt(toX,toY)!=null||board.getAt(toX,(toY+fromY)/2)!=null) throw new AssertionError("Invalid move5");
            board.setAt(x,y, null);
            board.setAt(toX,toY, this);
            x=toX;
            y=toY;

            moved=true;
            return;
        }
        if(Math.abs(fromY-toY)==1){
            if(board.getAt(toX,toY)!=null) throw new AssertionError("Invalid move6");
            board.setAt(x,y, null);
            board.setAt(toX,toY, this);
            x=toX;
            y=toY;
            moved=true;
            return;
        }

    }

    @Override
    int team() {
        return team;
    }

    @Override
    public String toString() {
        return "P";
    }


}
