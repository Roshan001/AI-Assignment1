import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Tictactoenxn {

	 static int n =3;
	 static int count_child = 0;
    public static void main(String[] args) {
    	
    	class Point {

    	    int x, y;

    	    public Point(int x, int y) {
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
    	    Point point;

    	    PScores(int score, Point point) {
    	        this.score = score;
    	        this.point = point;
    	    }
    	}
    	class Board {
    		 
    	    List<Point> availablePoints;
    	    Scanner scan = new Scanner(System.in);
    	 
    	    int[][] board;
    	

    	    public Board() {
    	    	   System.out.println("Enter Board dimension");
    	    	    n = scan.nextInt();
    	    	    board = new int[n][n];
    	    }

    	    public boolean isGameOver() {
    	        //Game is over is someone has won, or board is full (draw)
    	        return (hasXWon() || hasOWon() || getAvailableStates().isEmpty());
    	    }

    	    public boolean hasXWon() {

    	    	int Dia_compare = 0;
    	    	for(int i = 0;i<n;i++)
    	    	{
        	    	int row_compare = 0;
        	    	int col_compare = 0;
        	    	
    	    		for(int j = 0;j<n;j++)
    	    		{
    	    			if(board[i][j]==1)
    	    			{
    	    				row_compare++;
    	    				if(row_compare == n)
    	    				{
    	    					return true;
    	    				}
    	    			}
    	    			if(board[j][i]==1)
    	    			{
    	    				col_compare++;
    	    				if(col_compare==n)
    	    				{
    	    					return true;
    	    				}
    	    			}
    	    			
    	    			if(i==j)
    	    			{
    	    				if(board[i][j]==1)
    	    				{
    	    					Dia_compare++;
    	    					if(Dia_compare == n)
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
    	    	for(int i = 0;i<n;i++)
    	    	{
        	    	int row_compare = 0;
        	    	int col_compare = 0;
        	    	
    	    		for(int j = 0;j<n;j++)
    	    		{
    	    			if(board[i][j]==2)
    	    			{
    	    				row_compare++;
    	    				if(row_compare == n)
    	    				{
    	    					return true;
    	    				}
    	    			}
    	    			if(board[j][i]==2)
    	    			{
    	    				col_compare++;
    	    				if(col_compare==n)
    	    				{
    	    					return true;
    	    				}
    	    			}
    	    			
    	    			if(i==j)
    	    			{
    	    				if(board[i][j]==2)
    	    				{
    	    					Dia_compare++;
    	    					if(Dia_compare == n)
    	    					{
    	    						return true;
    	    					}
    	    				}
    	    			}
    	    		}
    	    	}
    	    	
    	    	return false;
    	    }

    	    public List<Point> getAvailableStates() {
    	        availablePoints = new ArrayList<>();
    	        for (int i = 0; i < n; ++i) {
    	            for (int j = 0; j < n; ++j) {
    	                if (board[i][j] == 0) {
    	                    availablePoints.add(new Point(i, j));
    	                }
    	            }
    	        }
    	        return availablePoints;
    	    }

    	    public void placeAMove(Point point, int player) {
    	        board[point.x][point.y] = player;   //player = 1 for X, 2 for O
    	    }

    	    public Point returnBestMove() {
    	        int MAX = -100000;
    	        int best = -1;

    	        for (int i = 0; i < rootsChildrenScores.size(); ++i) { 
    	            if (MAX < rootsChildrenScores.get(i).score) {
    	                MAX = rootsChildrenScores.get(i).score;
    	                best = i;
    	            }
    	        }

    	        return rootsChildrenScores.get(best).point;
    	    }

    	    void takeHumanInput() {
    	        System.out.println("Your move: ");
    	        int x = scan.nextInt();
    	        int y = scan.nextInt();
    	        Point point = new Point(x, y);
    	        placeAMove(point, 2); 
    	    }

    	    public void displayBoard() {
    	        System.out.println();

    	        for (int i = 0; i < n; ++i) 
    	        {
    	            for (int j = 0; j < n; ++j) 
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

    	    public int returnMin(List<Integer> list) {
    	        int min = Integer.MAX_VALUE;
    	        int index = -1;
    	        for (int i = 0; i < list.size(); ++i) {
    	            if (list.get(i) < min) {
    	                min = list.get(i);
    	                index = i;
    	            }
    	        }
    	        return list.get(index);
    	    }

    	    public int returnMax(List<Integer> list) {
    	        int max = Integer.MIN_VALUE;
    	        int index = -1;
    	        for (int i = 0; i < list.size(); ++i) {
    	            if (list.get(i) > max) {
    	                max = list.get(i);
    	                index = i;
    	            }
    	        }
    	        return list.get(index);
    	    }

    	    List<PScores> rootsChildrenScores;
    	 
    	    public void callMinimax(int depth, int turn){
    	        rootsChildrenScores = new ArrayList<>();
    	        minimax(depth, turn);
    	    }
    	    
    	    public int minimax(int depth, int turn) {

    	    	
    	        if (hasXWon()) return +1;
    	        if (hasOWon()) return -1;

    	        List<Point> pointsAvailable = getAvailableStates();
    	        if (pointsAvailable.isEmpty()) return 0; 

    	        List<Integer> scores = new ArrayList<>(); 

    	        for (int i = 0; i < pointsAvailable.size(); ++i) {
    	            Point point = pointsAvailable.get(i);  

    	            if (turn == 1) { //X's turn select the highest from below minimax() call
    	                placeAMove(point, 1); 
    	                count_child++;
    	               // displayBoard();
    	                int currentScore = minimax(depth + 1, 2);
    	                scores.add(currentScore);

    	                if (depth == 0) 
    	                    rootsChildrenScores.add(new PScores(currentScore, point));
    	                
    	            } else if (turn == 2) {//O's turn select the lowest from below minimax() call
    	                placeAMove(point, 2); 
    	                count_child++;
    	                //displayBoard();
    	                scores.add(minimax(depth + 1, 1));
    	            }
    	            board[point.x][point.y] = 0; //Reset this point
    	        }
    	       
    	        return turn == 1 ? returnMax(scores) : returnMin(scores);
    	    }
    	}
    	
        Board board = new Board();
        Random rand = new Random();
       
        
        board.displayBoard();

        System.out.println("Choose the player who start's the game?\n 1. Computer\n 2. User ");
        int choice = board.scan.nextInt();
        if(choice == 1){
            System.out.println("Computer move");
            board.callMinimax(0, 1);
            for (PScores pas : board.rootsChildrenScores) {
                System.out.println("Score at point" + pas.point +" is "+ pas.score);
            }
            board.placeAMove(board.returnBestMove(), 1);
            board.displayBoard();
        }
        
        while (!board.isGameOver()) {
            System.out.println("-------------------------------------------------------------------------------------------------------");
            System.out.println("Your move: ");
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter row(Starting from 1 to "+n+")");            
            int x = scan.nextInt();
            while(x<1||x>3)
            {
            	System.out.println("Enter valid row number(Starting from 1 to "+n+")"); 
            	x = scan.nextInt();
            }
            x--;
            System.out.println("Enter Column(Starting from 1 to "+n+")");  
            int y = scan.nextInt();
            while(y<1||y>3)
            {
            	System.out.println("Enter valid column number(Starting from 1 to "+n+")"); 
            	y = scan.nextInt();
            }
            y--;

            Point userMove = new Point(x, y);

            board.placeAMove(userMove, 2); //2 for O and O is the user
            board.displayBoard();
            if (board.isGameOver()) {
                break;
            } 
            System.out.println("-------------------------------------------------------------------------------------------------------");
            System.out.println("Computer move");
            board.callMinimax(0, 1);
            for (PScores pas : board.rootsChildrenScores) {
            	System.out.println("Score at point" + pas.point +" is "+ pas.score);
            }
            board.placeAMove(board.returnBestMove(), 1);
            board.displayBoard();
            System.out.println("count: "+count_child);
        }
        if (board.hasXWon()) {
            System.out.println("Computer: I Won :-)!");
        } else if (board.hasOWon()) {
            System.out.println("Congratz, Player Won.");
        } else {
            System.out.println("It's a draw!");
        }
    }
}