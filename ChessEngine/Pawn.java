import java.util.Scanner;

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

    public Piece promote(int x, int y){
        System.out.println("To what figure do u want to promote your pawn?");
        Scanner sc=new Scanner(System.in);
        String input = sc.nextLine();

        PieceGeneration pcg = board.pieces.get(input.toUpperCase());
        if(pcg==null){
            throw new AssertionError("Invalid piece "+input);
        }
        Piece nwp = pcg.generate(x,y, pl);
        return nwp;
    }
    @Override
    String name() {
        return type + " of team "+pl.getName();
    }

    @Override
    void move(int fromX, int fromY, int toX, int toY) throws AssertionError{
        if((fromX!=x&&fromY!=y)||toX>board.maxX||toY>board.maxY||(fromX==toX&&toY==fromY)) throw new AssertionError("Invalid move1");
        if(!pl.isOrientationAxis()) {
            if (Math.abs(fromY - toY) > 2||(pl.isOrientationDirection()&&toY<fromY)||(!pl.isOrientationDirection()&&toY>fromY)) throw new AssertionError("Invalid move4 "+toY+" "+fromY+" "+pl.isOrientationDirection());

            if (toX != x) {

                if (Math.abs(toX - fromX) != 1 || Math.abs(toY - fromY) != 1) {

                    throw new AssertionError("Invalid move2");
                }
                if (board.getAt(toX, toY) == null || board.getAt(toX, toY).player() == pl) {
                    throw new AssertionError("Invalid move3");
                } else {
                    Piece nwp=this;
                    if((pl.isOrientationDirection()&&toY==board.maxY)||(!pl.isOrientationDirection()&&toY==0)){
                        nwp = promote(toX, toY);
                    }
                    if(board.getAt(toX,toY) instanceof AuxilaryPiece){
                        ((AuxilaryPiece) board.getAt(toX,toY)).take();
                    }
                    board.setAt(x, y, null);
                    board.setAt(toX, toY, nwp);

                    x = toX;
                    y = toY;

                    moved = true;
                    return;
                }

            }
            if (Math.abs(fromY - toY) == 2) {
                if (moved) throw new AssertionError("Invalid move");
                if (board.getAt(toX, toY) != null || board.getAt(toX, (toY + fromY) / 2) != null)
                    throw new AssertionError("Invalid move5");
                Piece nwp=this;
                if((pl.isOrientationDirection()&&toY==board.maxY)||(!pl.isOrientationDirection()&&toY==0)){
                    nwp = promote(toX, toY);

                }
                board.setAt(x, y, null);

                board.setAt(toX, toY, nwp);
                board.setAuxilary(x,(toY+fromY)/2, (Pawn) nwp);
                x = toX;
                y = toY;

                moved = true;
                return;
            }
            if (Math.abs(fromY - toY) == 1) {
                if (board.getAt(toX, toY) != null) throw new AssertionError("Invalid move6");
                Piece nwp=this;
                if((pl.isOrientationDirection()&&toY==board.maxY)||(!pl.isOrientationDirection()&&toY==0)){
                    nwp = promote(toX, toY);

                }
                board.setAt(x, y, null);
                board.setAt(toX, toY, nwp);
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
        output +=  "♟";
        output+=reset;
        return output;
    }


    public void take() {
        board.setAt(x,y,null);
    }

}
