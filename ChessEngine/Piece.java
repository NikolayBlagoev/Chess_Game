public abstract class Piece {

   abstract String name();
   abstract void move(int fromX,int fromY, int toX, int toY);
   abstract Player player();
   @Override
   public abstract String toString();
   abstract void setXY(int x, int y);
   abstract void unmove(int x, int y);
}
