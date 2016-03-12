import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AlphaBeta { 
	static int children_count = 0;
    public static void main(String[] args) { 
    	

    	class XYCoordinate {

    	    int x, y;

    	    public XYCoordinate(int x, int y) {
    	        this.x = x;
    	        this.y = y;
    	    }

    	    @Override
    	    public String toString() {
    	        return "[" + x + ", " + y + "]";
    	    }
    	}

    	class PScores {

    	    int score;
    	    XYCoordinate point;

    	    PScores(int score, XYCoordinate point) {
    	        this.score = score;
    	        this.point = point;
    	    }
    	}
    	class Board {

    	    List<XYCoordinate> availablePoints;
    	    Scanner scan = new Scanner(System.in);
    	    int[][] board = new int[3][3]; 

    	    List<PScores> rootsChildrenScore = new ArrayList<>();

    	    public int evaluateBoard() {
    	        int score = 0;

    	        //Check all rows
    	        for (int i = 0; i < 3; ++i) {
    	            int blank = 0;
    	            int X = 0;
    	            int O = 0;
    	            for (int j = 0; j < 3; ++j) {
    	                if (board[i][j] == 0) {
    	                    blank++;
    	                } else if (board[i][j] == 1) {
    	                    X++;
    	                } else {
    	                    O++;
    	                }

    	            } 
    	            score+=changeInScore(X, O); 
    	        }

    	        //Check all columns
    	        for (int j = 0; j < 3; ++j) {
    	            int blank = 0;
    	            int X = 0;
    	            int O = 0;
    	            for (int i = 0; i < 3; ++i) {
    	                if (board[i][j] == 0) {
    	                    blank++;
    	                } else if (board[i][j] == 1) {
    	                    X++;
    	                } else {
    	                    O++;
    	                } 
    	            }
    	            score+=changeInScore(X, O);
    	        }

    	        int blank = 0;
    	        int X = 0;
    	        int O = 0;

    	        //Check diagonal (first)
    	        for (int i = 0, j = 0; i < 3; ++i, ++j) {
    	            if (board[i][j] == 1) {
    	                X++;
    	            } else if (board[i][j] == 2) {
    	                O++;
    	            } else {
    	                blank++;
    	            }
    	        }

    	        score+=changeInScore(X, O);

    	        blank = 0;
    	        X = 0;
    	        O = 0;

    	        //Check Diagonal (Second)
    	        for (int i = 2, j = 0; i > -1; --i, ++j) {
    	            if (board[i][j] == 1) {
    	                X++;
    	            } else if (board[i][j] == 2) {
    	                O++;
    	            } else {
    	                blank++;
    	            }
    	        }

    	        score+=changeInScore(X, O);

    	        return score;
    	    }
    	    private int changeInScore(int X, int O){
    	        int change;
    	        if (X == 3) {
    	            change = 100;
    	        } else if (X == 2 && O == 0) {
    	            change = 10;
    	        } else if (X == 1 && O == 0) {
    	            change = 1;
    	        } else if (O == 3) {
    	            change = -100;
    	        } else if (O == 2 && X == 0) {
    	            change = -10;
    	        } else if (O == 1 && X == 0) {
    	            change = -1;
    	        } else {
    	            change = 0;
    	        } 
    	        return change;
    	    }
    	    
    	    //Set this to some value if you want to have some specified depth limit for search
    	    int uptoDepth = -1;
    	    
    	    public int alphaBetaMinimax(int alpha, int beta, int depth, int turn){
    	        
    	        if(beta<=alpha){ 
    	        	System.out.println("Pruning at depth = "+depth);
    	        if(turn == 1) 
    	        	return Integer.MAX_VALUE; 
    	        else 
    	        	return Integer.MIN_VALUE; }
    	        
    	        if(depth == uptoDepth || isGameOver()) 
    	        	return evaluateBoard();
    	        
    	        List<XYCoordinate> pointsAvailable = getAvailableStates();
    	        
    	        if(pointsAvailable.isEmpty()) return 0;
    	        
    	        if(depth==0) rootsChildrenScore.clear(); 
    	        
    	        int maxValue = Integer.MIN_VALUE, minValue = Integer.MAX_VALUE;
    	        
    	        for(int i=0;i<pointsAvailable.size(); ++i){
    	            XYCoordinate point = pointsAvailable.get(i);
    	            
    	            int currentScore = 0;
    	            
    	            if(turn == 1){
    	                placeAMove(point, 1); 
    	                children_count++;
    	                currentScore = alphaBetaMinimax(alpha, beta, depth+1, 2);
    	                maxValue = Math.max(maxValue, currentScore); 
    	                
    	                //Set alpha
    	                alpha = Math.max(currentScore, alpha);
    	                
    	                if(depth == 0)
    	                    rootsChildrenScore.add(new PScores(currentScore, point));
    	            }else if(turn == 2){
    	                placeAMove(point, 2);
    	                children_count++;
    	                currentScore = alphaBetaMinimax(alpha, beta, depth+1, 1); 
    	                minValue = Math.min(minValue, currentScore);
    	                
    	                //Set beta
    	                beta = Math.min(currentScore, beta);
    	            }
    	            //reset board
    	            board[point.x][point.y] = 0; 
    	            
    	            //If a pruning has been done, don't evaluate the rest of the sibling states
    	            if(currentScore == Integer.MAX_VALUE || currentScore == Integer.MIN_VALUE) break;
    	        }
    	        return turn == 1 ? maxValue : minValue;
    	    }  

    	    public boolean isGameOver() {
    	        //Game is over if someone has won, or Available states will be empty when board is full (draw)
    	        return (hasXWon() || hasOWon() || getAvailableStates().isEmpty());
    	    }

    	    public boolean hasXWon() {

    	    	int Dia_compare = 0;
    	    	for(int i = 0;i<3;i++)
    	    	{
        	    	int row_compare = 0;
        	    	int col_compare = 0;
        	    	
    	    		for(int j = 0;j<3;j++)
    	    		{
    	    			if(board[i][j]==1)
    	    			{
    	    				row_compare++;
    	    				if(row_compare == 3)
    	    				{
    	    					return true;
    	    				}
    	    			}
    	    			if(board[j][i]==1)
    	    			{
    	    				col_compare++;
    	    				if(col_compare==3)
    	    				{
    	    					return true;
    	    				}
    	    			}
    	    			
    	    			if(i==j)
    	    			{
    	    				if(board[i][j]==1)
    	    				{
    	    					Dia_compare++;
    	    					if(Dia_compare == 3)
    	    					{
    	    						return true;
    	    					}
    	    				}
    	    			}
    	    		}
    	    	}
    	    	
    	    	return false;
    	    }

    	    public boolean hasOWon() {
    	    	int Dia_compare = 0;
    	    	for(int i = 0;i<3;i++)
    	    	{
        	    	int row_compare = 0;
        	    	int col_compare = 0;
        	    	
    	    		for(int j = 0;j<3;j++)
    	    		{
    	    			if(board[i][j]==2)
    	    			{
    	    				row_compare++;
    	    				if(row_compare == 3)
    	    				{
    	    					return true;
    	    				}
    	    			}
    	    			if(board[j][i]==2)
    	    			{
    	    				col_compare++;
    	    				if(col_compare==3)
    	    				{
    	    					return true;
    	    				}
    	    			}
    	    			
    	    			if(i==j)
    	    			{
    	    				if(board[i][j]==2)
    	    				{
    	    					Dia_compare++;
    	    					if(Dia_compare == 3)
    	    					{
    	    						return true;
    	    					}
    	    				}
    	    			}
    	    		}
    	    	}
    	    	
    	    	return false;
    	    }

    	    public List<XYCoordinate> getAvailableStates() {
    	        availablePoints = new ArrayList<>();
    	        for (int i = 0; i < 3; ++i) {
    	            for (int j = 0; j < 3; ++j) {
    	                if (board[i][j] == 0) {
    	                    availablePoints.add(new XYCoordinate(i, j));
    	                }
    	            }
    	        }
    	        return availablePoints;
    	    }

    	    public void placeAMove(XYCoordinate point, int player) {
    	        board[point.x][point.y] = player;   //player = 1 for X, 2 for O
    	    }

    	    public XYCoordinate returnBestMove() {
    	        int MAX = -100000;
    	        int best = -1;

    	        for (int i = 0; i < rootsChildrenScore.size(); ++i) {
    	            if (MAX < rootsChildrenScore.get(i).score) {
    	                MAX = rootsChildrenScore.get(i).score;
    	                best = i;
    	            }
    	        }

    	        return rootsChildrenScore.get(best).point;
    	    }

    	    void takeHumanInput() {
    	        System.out.println("Your move: ");
    	        int x = scan.nextInt();
    	        int y = scan.nextInt();
    	        XYCoordinate point = new XYCoordinate(x, y);
    	        placeAMove(point, 2);
    	    }

    	    public void displayBoard() {
    	        System.out.println();

    	        for (int i = 0; i < 3; ++i) 
    	        {
    	            for (int j = 0; j < 3; ++j) 
    	            {
    	            	if(board[i][j] == 1)
    	            	{
    	            		 System.out.print(" X |");
    	            	}
    	            	else if(board[i][j] == 2)
    	            	{
    	            		 System.out.print(" O |");
    	            	}
    	            	else
    	            	{
    	            		 System.out.print("   |");
    	            	}
    	            	
    	               
    	            }
    	            System.out.println();

    	        }
    	    } 
    	    
    	    public void resetBoard() {
    	        for (int i = 0; i < 3; ++i) {
    	            for (int j = 0; j < 3; ++j) {
    	                board[i][j] = 0;
    	            }
    	        }
    	    } 
    	}
        Board b = new Board();
        System.out.print("*****Board*****");

        b.displayBoard();

        System.out.println("Choose the player who start's the game?\n 1. Computer\n 2. User ");
        int choice = b.scan.nextInt();
        if (choice == 1) {
            b.alphaBetaMinimax(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1);
            for (PScores pas : b.rootsChildrenScore) 
                System.out.println("Point: " + pas.point + " Score: " + pas.score);
            
            b.placeAMove(b.returnBestMove(), 1);
            System.out.println("Children count: "+ children_count);
            children_count = 0;
            b.displayBoard();
        }

        while (!b.isGameOver()) {

            System.out.println("-------------------------------------------------------------------------------------------------------");
            System.out.println("Your move: ");
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter row(1 or 2 or 3)");            
            int x = scan.nextInt();
            while(x<1||x>3)
            {
            	System.out.println("Enter valid row number(1 or 2 or 3)");
            	x = scan.nextInt();
            }
            x--;
            System.out.println("Enter Column(1 or 2 or 3)");          
            int y = scan.nextInt();
            while(y<1||y>3)
            {
            	System.out.println("Enter valid column number(1 or 2 or 3)");
            	y = scan.nextInt();
            }
            y--;

            XYCoordinate userMove = new XYCoordinate(x, y);

            b.placeAMove(userMove, 2); //2 for O and O is the user
            b.displayBoard();
            if (b.isGameOver()) break;
            
            b.alphaBetaMinimax(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1);
            for (PScores pas : b.rootsChildrenScore) 
                System.out.println("Point: " + pas.point + " Score: " + pas.score);
            
            b.placeAMove(b.returnBestMove(), 1);
            System.out.println("Children count: "+ children_count);
            children_count = 0;
            b.displayBoard();
        }
        if (b.hasXWon()) {
            System.out.println("Computer: I Won :-)!");
        } else if (b.hasOWon()) {
            System.out.println("Player Won.");
        } else {
            System.out.println("It's a draw!");
        }
    }
}