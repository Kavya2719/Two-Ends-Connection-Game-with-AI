package game;

import java.util.ArrayList;

public class GridImpl implements Grid {
    private ArrayList<StringBuilder> grid;

    public GridImpl(int size){
        if(size == 0) throw new IllegalArgumentException();
        
        grid = new ArrayList<>();
        for(int i=0; i<size; i++) this.grid.add(new StringBuilder(".".repeat(size)));
    }

    @Override
    public int getSize(){
        return this.grid.size();
    }

    @Override
    public PieceColour getPiece(int row, int col){
        if(row < 0 || col < 0 || row >= this.grid.size() || col >= this.grid.size()) 
                throw new IllegalArgumentException();

        char ch = this.grid.get(row).charAt(col);
        if(ch == 'W') return PieceColour.WHITE;
        else if(ch == 'B') return PieceColour.BLACK;
        else return PieceColour.NONE;
    }

    @Override
    public void setPiece(int row, int col, PieceColour piece){
        if(row < 0 || col < 0 || row >= this.grid.size() || col >= this.grid.size()) 
                throw new IllegalArgumentException();
        
        char ch;
        if(piece == PieceColour.NONE) ch = '.';
        else if(piece == PieceColour.WHITE) ch = 'W';
        else ch = 'B';
        
        this.grid.get(row).setCharAt(col, ch);;
    }

    @Override
    public Grid copy(){
        Grid copy = new GridImpl(this.grid.size());
        
        for(int i=0; i<this.grid.size(); i++){
            StringBuilder s = this.grid.get(i);

            for(int j=0; j<s.length(); j++){
                char ch = s.charAt(j);
                PieceColour piece;

                if(ch == 'W') piece = PieceColour.WHITE;
                else if(ch == 'B') piece = PieceColour.BLACK;
                else piece = PieceColour.NONE;
                
                copy.setPiece(i, j, piece);
            }
        }
        return copy;
    }

    @Override
    public String toString(){
        String str = "";
        for(StringBuilder s: this.grid) str += s.toString() + "\n";
        return str;
    }
}