package movement;

import hlavnybalicek.BoardFrame;

public class Move {
    private BoardFrame frameFrom;
    private BoardFrame frameTo;
    public Move(BoardFrame frameFrom, BoardFrame frameTo) {
        this.frameFrom = frameFrom;
        this.frameTo = frameTo;
    }

    /*public BoardFrame getFromFrame() {
        return this.frameFrom;
    }*/

    public BoardFrame getToFrame() {
        return this.frameTo;
    }

    public void setFrameFrom(BoardFrame frameFrom) {
        this.frameFrom = frameFrom;
    }

    public void setFrameTo(BoardFrame frameTo) {
        this.frameTo = frameTo;
    }
}
