package game;

import java.util.Objects;

public class MoveImpl implements Move{
    private int row,col;

    public MoveImpl(int x,int y){
        this.row = x; this.col = y;    
    }

    @Override
    public int getRow(){
        return this.row;
    }

    @Override
    public int getCol(){
        return this.col;
    }

    @Override
    public String toString(){
        return "(" + this.row + "," + this.col + ")";
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        
        Move move = (Move) obj;
        return this.row == move.getRow() && this.col == move.getCol();
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
