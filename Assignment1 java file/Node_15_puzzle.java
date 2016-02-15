package Assignment1;

public class Node_15_puzzle {
	 public int Address,Parent_address,Zero_X_pos,Zero_Y_pos,ManhattanDistance;
	 public char Direction='\0';
	public int State[][]=new int[6][6];
	 public Node_15_puzzle()
	 {
	 }
	 
	public Node_15_puzzle(int[][] values,int X, int Y,int Index, int Parent,char Dir) 
	{
		 for(int i=0;i<6;i++)
		 {
			 for(int j=0;j<6;j++)
		 
			 {
				 State[i][j]=values[i][j];
			 }
		 }
			
		 Zero_X_pos=X;
		 Zero_Y_pos=Y;
		 Parent_address=Parent;
	     Direction=Dir;			 
	}
}