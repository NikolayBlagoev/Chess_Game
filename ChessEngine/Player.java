public class Player {
    private int teamNumber;
    private String colour;
    private boolean lost = false;
    private boolean orientationAxis, orientationDirection;
    private int pieceCount = 0;
    private String name = null;

    public Player(int teamNumber, String colour, int pieceCount) {
        this.teamNumber = teamNumber;
        this.colour = colour;
        this.pieceCount = pieceCount;
    }

    public Player(int teamNumber, String colour, String name, int pieceCount) {
        this.teamNumber = teamNumber;
        this.colour = colour;
        this.name = name;
        this.pieceCount = pieceCount;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public boolean isLost() {
        return lost;
    }

    protected void setLost(boolean lost) {
        this.lost = lost;
    }

    public boolean isOrientationAxis() {
        return orientationAxis;
    }

    public void setOrientation(String orient) throws AssertionError{

        if (orient.charAt(0) == '+') {
            orientationDirection=true;
        } else if (orient.charAt(0) == '-') {
            orientationDirection=false;
        } else {
            throw new AssertionError("Invalid direction " + orient);
        }
        if (orient.charAt(1) == 'x') {
            orientationAxis=true;
        } else if (orient.charAt(1) == 'y') {
            orientationAxis=false;
        } else {
            throw new AssertionError("Invalid axis " + orient);
        }


    }

    public boolean isOrientationDirection() {
        return orientationDirection;
    }


    public int getPieceCount() {
        return pieceCount;
    }

    protected void setPieceCount(int pieceCount) {
        this.pieceCount = pieceCount;
    }

    public String getName() {
        if (name == null) return Integer.toString(teamNumber);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
