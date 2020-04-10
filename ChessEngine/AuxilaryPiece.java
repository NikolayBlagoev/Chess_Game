public class AuxilaryPiece extends Pawn {
    Pawn p;
    Board b;
    public AuxilaryPiece(Board board, int x, int y, Player pl, Pawn p) {
        super(board, x, y, pl);
        b=board;
        this.p=p;

    }
    @Override
    public String toString() {
        return "〷";
    }
    @Override
    public void take(){
        b.auxY=-1;
        b.auxX=-1;
        p.take();
    }
}
