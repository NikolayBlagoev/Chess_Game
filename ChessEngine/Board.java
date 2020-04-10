import java.util.HashMap;

public class Board {
    private Piece[][] board;
    private int x,y;
    private String frame=" ";
    private int maxPlayers;
    private int turn=0;
    private Player[] players;
    int auxX=-1, auxY=-1;
    //Colours or display
    private boolean flagAux=false;
    public final String ANSI_RESET = "\u001B[0m";
    public final String ANSI_BLACK = "\u001B[30m";
    public final String ANSI_RED = "\u001B[31m";
    public final String ANSI_GREEN = "\u001B[32m";
    public final String ANSI_YELLOW = "\u001B[33m";
    public final String ANSI_BLUE = "\u001B[34m";
    public final String ANSI_PURPLE = "\u001B[35m";
    public final String ANSI_CYAN = "\u001B[36m";
    public final String ANSI_WHITE = "\u001B[37m";
    int maxX;
    int maxY;

    HashMap <String, PieceGeneration> pieces;

    //Used for classic chess
    public Board(){
        x=8;
        y=8;
        maxX=7;
        maxY=7;
        maxPlayers=2;
        players=new Player[2];
        players[0] = new Player(1, ANSI_RED, null,16 );
        players[1] = new Player(2, ANSI_BLUE, null,16 );
        pieces = new HashMap<>();
        PieceGeneration QUEEN = (par1,par2,par3)->{
            return new Queen(this, par1,par2,par3);
        };
        PieceGeneration KNIGHT = (par1,par2,par3)->{
            return new Knight(this, par1,par2,par3);
        };
        PieceGeneration BISHOP = (par1,par2,par3)->{
            return new Bishop(this, par1,par2,par3);
        };
        PieceGeneration ROOK = (par1,par2,par3)->{
            return new Rook(this, par1,par2,par3);
        };

        pieces.put("Q",QUEEN);
        pieces.put("QUEEN",QUEEN);
        pieces.put("N",KNIGHT);
        pieces.put("KNIGHT",KNIGHT);
        pieces.put("R",ROOK);
        pieces.put("ROOK",ROOK);
        pieces.put("B",BISHOP);
        pieces.put("BISHOP",BISHOP);
        players[0].setOrientation("+y");
        players[1].setOrientation("-y");
        board = new Piece[8][8];
        char c = 'A';
        for (int i =0;i<y;i++) {
            frame += (char) (c + i) + " ";
            board[1][i] = new Pawn(this, i, 1,players[0]);
            board[6][i] = new Pawn(this, i, 6,players[1]);
        }
        //White
        board[0][0] = new Rook(this, 0, 0,players[0]);
        board[0][1] = new Knight( this,1,0,players[0]);
        board[0][2] = new Bishop( this,2,0,players[0]);
        board[0][3] = new Queen(this,3,0,players[0]);
        board[0][4] = new King(this,4,0,players[0]);
        board[0][5] = new Bishop(this, 5, 0,players[0]);
        board[0][6] = new Knight( this,6, 0,players[0]);
        board[0][7] = new Rook(this, 7, 0,players[0]);

        //Black
        board[7][0] = new Rook(this, 0, 7,players[1]);
        board[7][1] = new Knight(this, 1, 7,players[1]);
        board[7][2] = new Bishop( this, 2, 7,players[1]);
        board[7][3] = new Queen(this, 3, 7,players[1]);
        board[7][4] = new King(this, 4, 7,players[1]);
        board[7][5] = new Bishop(this,5, 7,players[1]);
        board[7][6] = new Knight(this, 6, 7,players[1]);
        board[7][7] = new Rook( this,7,7,players[1]);
    }
    private String get(int x, int y){
        if(x>=this.x||y>=this.y) return "Tile outside of board";
        if(board[y][x]==null) return "Empty tile";
        return board[y][x].name();
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
    protected void destroyAux(){
        setAt(auxX,auxY, null);
        auxX=-1;
        auxY=-1;
    }
    protected void setAuxilary(int x, int y, Pawn p){
        if(x>=this.x||y>=this.y||x<0||y<0) return;
        setAt(auxX,auxY, null);
        flagAux=true;
        auxX=x;
        auxY=y;
        board[y][x]=new AuxilaryPiece(this, x, y,p.player(),p);
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
            case "GET":
                get(interp);
                break;
            case "board":
            case "BOARD":
                System.out.println(toString());
                break;
            case "move":
            case "MOVE":
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
                System.out.println("Move requires 3 parameters. Syntax: move [Tile Name] [TileName]");
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
            if(board[y][x].player().getTeamNumber()-1!=turn){
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
            if(flagAux){
                flagAux=!flagAux;
            }else {
                destroyAux();
            }
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