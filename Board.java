public class Board {
    private Piece[][] board;
    private int x,y;
    private String frame=" ";
    private int maxPlayers;
    private int turn=0;
    //Used for classic chess
    public Board(){
        x=8;
        y=8;
        maxPlayers=2;
        board = new Piece[8][8];
        char c = 'A';
        for (int i =0;i<y;i++) {
            frame += (char) (c + i) + " ";
            board[1][i] = new Pawn(1, this, i, 1);
            board[6][i] = new Pawn(2, this, i, 6);
        }
        //White
        board[0][0] = new Rook(1, this, 0, 0);
        board[0][1] = new Knight(1, this,1,0);
        board[0][2] = new Bishop(1, this,2,0);
        board[0][3] = new Queen(1, this,3,0);
        board[0][4] = new King(1, this,4,0);
        board[0][5] = new Bishop(1, this, 5, 0);
        board[0][6] = new Knight(1, this,6, 0);
        board[0][7] = new Rook(1, this, 7, 0);

        //Black
        board[7][0] = new Rook(2, this, 0, 7);
        board[7][1] = new Knight(2, this, 1, 7);
        board[7][2] = new Bishop(2, this, 2, 7);
        board[7][3] = new Queen(2, this, 3, 7);
        board[7][4] = new King(2, this, 4, 7);
        board[7][5] = new Bishop(2, this,5, 7);
        board[7][6] = new Knight(2, this, 6, 7);
        board[7][7] = new Rook(2, this,7,7);
    }
    private String get(int x, int y){
        if(x>=this.x||y>=this.y) return "Tile outside of board";
        if(board[y][x]==null) return "Empty tile";
        return board[y][x].name() + " of team: "+board[y][x].team();
    }
    public Piece getAt(int x, int y){
        if(x>=this.x||y>=this.y||x<0||y<0) return null;
        return board[y][x];
    }
    public void setAt(int x, int y, Piece p){
        if(x>=this.x||y>=this.y||x<0||y<0) return;
        board[y][x]=p;
    }
    private int strToInt(String input){
        int output = 0;
        for(char c:input.toCharArray()){
            output=output*26;
            output+=(c-'A');
        }
        return output;

    }
    private void get(String[] command){
        try {
            if (command.length != 2) {
                System.out.println("Get requires 2 parameters. Syntax: get [Tile Name]");
                return;
            }
            command[1].toUpperCase();
            String[] interp = command[1].split("[^A-Z0-9]+|(?<=[A-Z])(?=[0-9])|(?<=[0-9])(?=[A-Z])");
            if (interp.length != 2) {
                System.out.println("Invalid tile name");
                return;
            }
            int x = strToInt(interp[0]);
            int y = Integer.parseInt(interp[1]);
            y--;
            System.out.println(get(x, y));
        }catch (Exception e){
            System.out.println("Invalid get syntax. Syntax: get [Tile Name]. Tile name must be characters followed by numbers");
        }
    }
    public void interpet (String command){
        if(command==null) System.out.println("Invalid command. Please type ? to see list of acceptable commands");
        String[] interp = command.split(" ");
        switch (interp[0]){
            case "get":
                get(interp);
                break;
            case "board":
                System.out.println(toString());
                break;
            case "move":
                move(interp);
                break;
            default:
                System.out.println("Invalid command. Please type ? to see list of acceptable commands");
                break;
        }
    }
    private void move(String[] command){
        try {
            if (command.length != 3) {
                System.out.println("Get requires 3 parameters. Syntax: move [Tile Name] [TileName]");
                return;
            }
            command[1].toUpperCase();
            String[] interp = command[1].split("[^A-Z0-9]+|(?<=[A-Z])(?=[0-9])|(?<=[0-9])(?=[A-Z])");
            if (interp.length != 2) {
                System.out.println("Invalid tile name at position 1 "+command[1]);
                return;
            }
            int x = strToInt(interp[0]);
            int y = Integer.parseInt(interp[1]);
            y--;
            if(x>this.x||y>this.y||x<0||y<0) {
                System.out.println("Invalid tile name at position 1 "+command[1]);
                return;
            }
            if(board[y][x]==null){
                System.out.println("No piece at tile "+command[1]);
                return;
            }
            if(board[y][x].team()-1!=turn){
                System.out.println("Not your turn");
                return;
            }
            interp = command[2].split("[^A-Z0-9]+|(?<=[A-Z])(?=[0-9])|(?<=[0-9])(?=[A-Z])");
            if (interp.length != 2) {
                System.out.println("Invalid tile name at position 2 "+command[2]);
                return;
            }
            int tox = strToInt(interp[0]);
            int toy = Integer.parseInt(interp[1]);
            toy--;
            if(tox>this.x||toy>this.y||tox<0||toy<0) {
                System.out.println("Invalid tile name at position 2 "+command[2]);
                return;
            }
            board[y][x].move(x,y,tox,toy);
            System.out.println("Successfully moved piece from "+command[1]+" to "+command[2]);
            turn+=1;
            turn=turn%maxPlayers;
        }catch (AssertionError e){
            System.out.println(e.getMessage());
            return;
        } catch (Exception e){
            System.out.println("Invalid move syntax. Syntax: move [Tile Name] [TileName]. Tile name must be characters followed by numbers");
            return;
        }
    }
    @Override
    public String toString(){
        String output="";
        for(int y=0;y<this.y;y++){
            output+="|";
            for(int x=0;x<this.x;x++){
                if(board[y][x]==null) {
                    output+="_";
                }else{
                    output+=board[y][x].toString();
                }
                output+="|";
            }

            output+=" "+(y+1)+"\n";
        }
        output+=frame;
        return output;
    }

}
