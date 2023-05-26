package game;

import java.util.Collection;
import java.util.ArrayList;

public class GameImpl implements Game{
    private Grid grid;
    private boolean currentPlayer = true; // true = BLACK, false = WHITE

    public GameImpl(int size){
        if(size == 0) throw new IllegalArgumentException();
        this.grid = new GridImpl(size);
    }

    public GameImpl(Game game){
        this.currentPlayer = (game.currentPlayer() != PieceColour.WHITE);
        this.grid = game.getGrid();
    }

    @Override
    public boolean isOver(){
        int occupied = 0;
        int size = this.grid.getSize();

        for(int row=0; row < size; ++row)
        {
            for(int col=0; col < size; ++col)
            {
                if(grid.getPiece(row,col) != PieceColour.NONE)
                    occupied++;
            }
        }

        // checking condition for draw
        if(occupied == size*size) return true;

        // checking if there's a winner
        return (winner() != PieceColour.NONE);
    }

    @Override
    public PieceColour winner(){
        // checking if black can be the winner
        if(PathFinder.topToBottom(this.grid, PieceColour.BLACK)) return PieceColour.BLACK;
        else if(PathFinder.leftToRight(this.grid, PieceColour.BLACK)) return PieceColour.BLACK;

        // checking if white can be winner
        if(PathFinder.topToBottom(this.grid, PieceColour.WHITE)) return PieceColour.WHITE;
        else if(PathFinder.leftToRight(this.grid, PieceColour.WHITE)) return PieceColour.WHITE;
        
        // either draw or no winner
        return PieceColour.NONE;
    }

    @Override
    public PieceColour currentPlayer(){
        return (this.currentPlayer)? PieceColour.BLACK : PieceColour.WHITE;
    }

    @Override
    public Collection<Move> getMoves(){
        Collection<Move> a = new ArrayList<>();

        for(int row=0; row < this.grid.getSize(); ++row)
        {
            for(int col=0; col < this.grid.getSize(); ++col)
            {
                if(grid.getPiece(row,col) == PieceColour.NONE)
                    a.add(new MoveImpl(row,col));
            }
        }
        return a;
    }

    @Override
    public void makeMove(Move move){
        int row = move.getRow(), col = move.getCol();
        
        if(row < 0 || col < 0 || row >= this.grid.getSize() || col >= this.grid.getSize()) 
            throw new IllegalArgumentException();

        grid.setPiece(row, col, this.currentPlayer());
        currentPlayer ^= true;
    }

    @Override
    public Grid getGrid(){
        return this.grid.copy();
    }

    @Override
    public Game copy(){
        Game copy = new GameImpl(this.grid.getSize());
        return copy;
    }
}