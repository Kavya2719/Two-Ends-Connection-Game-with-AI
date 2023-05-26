package game.tests;

import java.util.ArrayList;
import java.util.Collection;

import game.*;

public class GameTest extends Test {
    public static void main(String[] args) {
        boolean caught = false;
        try {
            new GameImpl(0);
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        expect(true, caught);

        caught = false;
        try {
            new GameImpl(5);
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        expect(false, caught);

        // TODO: Complete this test
        int size = 5;
        Game game = new GameImpl(size);
        
        // testing for out of bounds
        try{
            game.makeMove(new MoveImpl(size+1,size+1));
        }catch(IllegalArgumentException e){
            caught = true;
        }
        expect(true, caught);

        // testing for all variables before starting the game
        expect(false,game.isOver());
        expect(PieceColour.NONE, game.winner());

        Collection<Move> availableMoves = game.getMoves();
        Collection<Move> actualAvailable = new ArrayList<> ();

        for(int row = 0; row < size; ++row){
            for(int col = 0; col < size; ++col){
                actualAvailable.add(new MoveImpl(row,col));
            }
        }
        expect(actualAvailable, availableMoves);
        expect(PieceColour.BLACK, game.currentPlayer());

        // testing for the copy of grid
        Grid grid = new GridImpl(size);
        expect(grid.toString(), game.getGrid().toString());

        // testing for the copy of game and altering it
        Game copyGame = game.copy();
        copyGame.makeMove(new MoveImpl(1,1));
        expect(false, game == copyGame);

        game.makeMove(new MoveImpl(1,1));
        expect(true, grid.toString() != game.getGrid().toString());
        expect(game.getGrid().toString(), copyGame.getGrid().toString());

        game.makeMove(new MoveImpl(0,1));
        game.makeMove(new MoveImpl(1,2));
        game.makeMove(new MoveImpl(1,3));
        game.makeMove(new MoveImpl(1,4));

        expect(false, game.isOver());

        game.makeMove(new MoveImpl(0,0));
        expect(PieceColour.NONE, game.winner());

        game = new GameImpl(size);
        game.makeMove(new MoveImpl(0,4));
        game.makeMove(new MoveImpl(0,3));

        game.makeMove(new MoveImpl(1,4));
        game.makeMove(new MoveImpl(0,0));

        game.makeMove(new MoveImpl(2,4));
        game.makeMove(new MoveImpl(1,1));

        game.makeMove(new MoveImpl(2,3));
        game.makeMove(new MoveImpl(2,0));

        game.makeMove(new MoveImpl(3,3));
        game.makeMove(new MoveImpl(1,3));

        game.makeMove(new MoveImpl(3,2));
        game.makeMove(new MoveImpl(3,1));

        expect(false,game.isOver());
        expect(PieceColour.NONE, game.winner());
        
        actualAvailable.clear();
        actualAvailable.add(new MoveImpl(0,1));
        actualAvailable.add(new MoveImpl(0,2));
        actualAvailable.add(new MoveImpl(1,0));
        actualAvailable.add(new MoveImpl(1,2));
        actualAvailable.add(new MoveImpl(2,1));
        actualAvailable.add(new MoveImpl(2,2));
        actualAvailable.add(new MoveImpl(3,0));
        actualAvailable.add(new MoveImpl(3,4));
        actualAvailable.add(new MoveImpl(4,0));
        actualAvailable.add(new MoveImpl(4,1));
        actualAvailable.add(new MoveImpl(4,2));
        actualAvailable.add(new MoveImpl(4,3));
        actualAvailable.add(new MoveImpl(4,4));

        expect(actualAvailable, game.getMoves());
        
        game.makeMove(new MoveImpl(4,2));
        expect(true, game.isOver());
        expect(PieceColour.BLACK, game.winner());


        // checking for the case of draw
        game = new GameImpl(5);
        for(int row = 0; row < size; ++row){
            for(int col = 0; col < size; ++col){
                game.makeMove(new MoveImpl(row,col));
            }
        }
        System.out.print(game.getGrid());
        expect(true, game.isOver());
        expect(PieceColour.NONE, game.winner());

        game = new GameImpl(size);
        game.makeMove(new MoveImpl(0,3));
        game.makeMove(new MoveImpl(0,4));

        game.makeMove(new MoveImpl(0,0));
        game.makeMove(new MoveImpl(1,4));

        game.makeMove(new MoveImpl(1,1));
        game.makeMove(new MoveImpl(2,4));

        game.makeMove(new MoveImpl(2,0));
        game.makeMove(new MoveImpl(2,3));

        game.makeMove(new MoveImpl(1,3));
        game.makeMove(new MoveImpl(3,3));

        game.makeMove(new MoveImpl(3,1));
        game.makeMove(new MoveImpl(3,2));

        game.makeMove(new MoveImpl(4,3));
        game.makeMove(new MoveImpl(4,2));
        
        expect(true,game.isOver());
        expect(PieceColour.WHITE, game.winner());

        checkAllTestsPassed();
    }
}
