public abstract class Piece {
   abstract String name();
   abstract void move(int fromX,int fromY, int toX, int toY);
   abstract int team();
   @Override
   public abstract String toString();

}
