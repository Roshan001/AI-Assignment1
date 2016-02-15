package Assignment1;

import java.util.*;

public class Astar {

	 public int Address,Parent_address,Zero_X_pos,Zero_Y_pos,ManhattanDistance;
	 public char Direction='\0';
	 public int State[][]=new int[5][5];
	 
	 public Astar(){
	 }
	 
	 public Astar(int[][] values,int X, int Y,int Index, int Parent,char Dir) 
	 {
		 for(int i=0;i<5;i++)
			 for(int j=0;j<5;j++)
				 State[i][j]=values[i][j]; 
			
		 Zero_X_pos=X;
		 Zero_Y_pos=Y;
		 Parent_address=Parent;
	     Direction=Dir;		
	 }
	
int Next_Node_Index=1;	

	
//***************************************3*3 FUNCTIONS**********************************************************************************		
 public static int Find_Manhattan_Distance(Astar Obj,Astar Goal_Obj)//Function to calculate Manhattan distance
	{	
	    int Manhattan_Count = 0;
	    for (int i=1; i<4; i++) {
	        for (int j=1; j<4; j++) 
	        {
	            if(Obj.State[i][j]!=Goal_Obj.State[i][j])
	                         Manhattan_Count++;            
	        }    
	    }
	   return Manhattan_Count;	    
    }	
	
 public static boolean Compare_Node(Astar Obj1,Astar Obj2) 
	{
		for (int i=1; i<4; i++) 
		{
	        for (int j=1; j<4; j++) 
	        {
	            if(Obj1.State[i][j]!=Obj2.State[i][j])
	            {
	                return true; //returns true if two nodes are not equal
	             }
	        }   
	
		}
	
	return false;
	}
	
	public static boolean Up_Direction(Astar Obj)
	{
		if (Obj.State[Obj.Zero_X_pos-1][Obj.Zero_Y_pos]==-1) 
		{
	        return false;//if false, blank space cannot be swapped with upper element
	    }
		else
		{
	        return true;//if true, blank space cann be swapped with upper element
	    }

	}
	
	public static boolean Down_DirectionDirection(Astar Obj)
	{
		if (Obj.State[Obj.Zero_X_pos+1][Obj.Zero_Y_pos]==-1) 
		{
	        return false;
	    }
		else
		{
	        return true;
	    }
	
	}
	
	public static boolean Right_Direction(Astar Obj)
	{
		if (Obj.State[Obj.Zero_X_pos][Obj.Zero_Y_pos+1]==-1) 
		{
	        return false;
	    }
		else
		{
	        return true;
	    }
	
	}
	
	public static boolean Left_Direction(Astar Obj)
	{
		if (Obj.State[Obj.Zero_X_pos][Obj.Zero_Y_pos-1]==-1) 
		{
	        return false;
	    }
		else
		{
	        return true;
	    }
	
	}
	
	public static void Swap_Up(Astar Obj)
	{
	    Obj.State[Obj.Zero_X_pos][Obj.Zero_Y_pos]=Obj.State[Obj.Zero_X_pos-1][Obj.Zero_Y_pos];
	    Obj.State[Obj.Zero_X_pos-1][Obj.Zero_Y_pos]=0;    

	}
	
	public static void Swap_Down(Astar Obj)
	{
	    Obj.State[Obj.Zero_X_pos][Obj.Zero_Y_pos]=Obj.State[Obj.Zero_X_pos+1][Obj.Zero_Y_pos];
	    Obj.State[Obj.Zero_X_pos+1][Obj.Zero_Y_pos]=0;    

	}
	
	public static void Swap_Right(Astar Obj)
	{
	    Obj.State[Obj.Zero_X_pos][Obj.Zero_Y_pos]=Obj.State[Obj.Zero_X_pos][Obj.Zero_Y_pos+1];
	    Obj.State[Obj.Zero_X_pos][Obj.Zero_Y_pos+1]=0;    

	}
	
	public static void Swap_Left(Astar Obj)
	{
	    Obj.State[Obj.Zero_X_pos][Obj.Zero_Y_pos]=Obj.State[Obj.Zero_X_pos][Obj.Zero_Y_pos-1];
	    Obj.State[Obj.Zero_X_pos][Obj.Zero_Y_pos-1]=0;    

	}
	
	public static void PrintAstar(Astar Obj)//3*3 matrix of the Astar
	{
		System.out.println("\nNavigation of space: "+Obj.Direction);
		for(int i=1;i<4;i++)
		{
			for(int j=1;j<4;j++)
		
			{
				System.out.print(Obj.State[i][j]+" ");
			}
			System.out.print("\n");
		}
	}
	
	public static int Create_Child(Astar[] Array,Astar Parent,Astar Goal,int NextIndex)//Creation of child nodes
	{
		int Index=NextIndex;
		if(Up_Direction(Parent))
		{
	        Array[Index]=new Astar(Parent.State,Parent.Zero_X_pos,Parent.Zero_Y_pos,Index,Parent.Address,'U');	        
	        Swap_Up(Array[Index]);
	        Array[Index].Zero_X_pos--;
	        Array[Index].ManhattanDistance=Find_Manhattan_Distance(Array[Index],Goal);
	       
            
	        boolean cond=Compare_Node(Array[Index],Array[Parent.Parent_address]);
	        
           if(cond==false)
	        {	
	        	Array[Index]=null;
	        
	        }
	        else
	        {  
	        	PrintAstar(Array[Index]);
	        	++Index;
		
	        }
		}
		
	if(Down_DirectionDirection(Parent))
	{
		Array[Index]=new Astar(Parent.State,Parent.Zero_X_pos,Parent.Zero_Y_pos,Index,Parent.Address,'D');	        
        Swap_Down(Array[Index]);
        Array[Index].Zero_X_pos++;
        Array[Index].ManhattanDistance=Find_Manhattan_Distance(Array[Index],Goal);
        
        
        boolean cond=Compare_Node(Array[Index],Array[Parent.Parent_address]);
        
        if(cond==false)
        {	
        	Array[Index]=null;
        
        }
        else
        {  
        	PrintAstar(Array[Index]);
        	++Index;
	
        }
      }
 	
	if(Right_Direction(Parent))
	{
		Array[Index]=new Astar(Parent.State,Parent.Zero_X_pos,Parent.Zero_Y_pos,Index,Parent.Address,'R');	        
        Swap_Right(Array[Index]);
        Array[Index].Zero_Y_pos++;
        Array[Index].ManhattanDistance=Find_Manhattan_Distance(Array[Index],Goal);
        
        boolean cond=Compare_Node(Array[Index],Array[Parent.Parent_address]);
        
        if(cond==false)
        {	
        	Array[Index]=null;
        
        }
        else
        {  
        	PrintAstar(Array[Index]);
        	++Index;
	
        }
       }

	if(Left_Direction(Parent))
	{
		Array[Index]=new Astar(Parent.State,Parent.Zero_X_pos,Parent.Zero_Y_pos,Index,Parent.Address,'L');	        
        Swap_Left(Array[Index]);
        Array[Index].Zero_Y_pos--;
        Array[Index].ManhattanDistance=Find_Manhattan_Distance(Array[Index],Goal);
      
        
        boolean cond=Compare_Node(Array[Index],Array[Parent.Parent_address]);
        
        if(cond==false)
        {	
        	Array[Index]=null;
        
        }
        else
        {  
        	PrintAstar(Array[Index]);
        	++Index;
	
        }
	}
	
	return Index;
	}
	
	//***********************************************************3*3 END***************************************************************
	//***********************************************************4*4 START***************************************************************

	public static int Find_Manhattan_Distance1(Node_15_puzzle Obj,Node_15_puzzle Goal_Obj)
		{
			
			    int Manhattan_Count = 0;
			    for (int i=1; i<5; i++) {
			        for (int j=1; j<5; j++) 
			        {
			            if(Obj.State[i][j]!=Goal_Obj.State[i][j])
			                         Manhattan_Count++;            
			        }    
			    }
		   return Manhattan_Count;	    
	    }	
		
		public static boolean Compare_Node1(Node_15_puzzle Obj1,Node_15_puzzle Obj2)
		{
			for (int i=1; i<5; i++) 
			{
		        for (int j=1; j<5; j++) 
		        {
		            if(Obj1.State[i][j]!=Obj2.State[i][j])
		            {
		                return true; 
		             }
		        }   
		
			}
		
		return false;
		}
		
		public static boolean Up_Direction1(Node_15_puzzle Obj)
		{
			if (Obj.State[Obj.Zero_X_pos-1][Obj.Zero_Y_pos]==-1) 
			{
		        return false;
		    }
			else
			{
		        return true;
		    }

		}
		
		public static boolean Down_Direction1(Node_15_puzzle Obj)
		{
			if (Obj.State[Obj.Zero_X_pos+1][Obj.Zero_Y_pos]==-1) 
			{
		        return false;
		    }
			else
			{
		        return true;
		    }
		
		}
		
		public static boolean Right_Direction1(Node_15_puzzle Obj)
		{
			if (Obj.State[Obj.Zero_X_pos][Obj.Zero_Y_pos+1]==-1) 
			{
		        return false;
		    }
			else
			{
		        return true;
		    }
		
		}
		
		public static boolean Left_Direction1(Node_15_puzzle Obj)
		{
			if (Obj.State[Obj.Zero_X_pos][Obj.Zero_Y_pos-1]==-1) 
			{
		        return false;
		    }
			else
			{
		        return true;
		    }
		
		}
		
		public static void ApplyAstar(int c,Astar []Array)
		{
			
			while(Array[c].Parent_address!=0)
	          {
	        	  
	        	  PrintAstar(Array[Array[c].Parent_address]);
	        	  c=Array[c].Parent_address;
	          }	 
			
			
		}
		
		public static void ApplyAstar1(int c,Node_15_puzzle Array[])
		{
			while(Array[c].Parent_address!=0)
	          {
	        	  
	        	  PrintNode_15_puzzle(Array[Array[c].Parent_address]);
	        	  c=Array[c].Parent_address;
	          }	 
		}
		
		public static void Swap_Up1(Node_15_puzzle Obj)
		{
		    Obj.State[Obj.Zero_X_pos][Obj.Zero_Y_pos]=Obj.State[Obj.Zero_X_pos-1][Obj.Zero_Y_pos];
		    Obj.State[Obj.Zero_X_pos-1][Obj.Zero_Y_pos]=0;    

		}
		
		public static void Swap_Down1(Node_15_puzzle Obj)
		{
		    Obj.State[Obj.Zero_X_pos][Obj.Zero_Y_pos]=Obj.State[Obj.Zero_X_pos+1][Obj.Zero_Y_pos];
		    Obj.State[Obj.Zero_X_pos+1][Obj.Zero_Y_pos]=0;    

		}
		
		public static void Swap_Right1(Node_15_puzzle Obj)
		{
		    Obj.State[Obj.Zero_X_pos][Obj.Zero_Y_pos]=Obj.State[Obj.Zero_X_pos][Obj.Zero_Y_pos+1];
		    Obj.State[Obj.Zero_X_pos][Obj.Zero_Y_pos+1]=0;    

		}
		
		public static void Swap_Left1(Node_15_puzzle Obj)
		{
		    Obj.State[Obj.Zero_X_pos][Obj.Zero_Y_pos]=Obj.State[Obj.Zero_X_pos][Obj.Zero_Y_pos-1];
		    Obj.State[Obj.Zero_X_pos][Obj.Zero_Y_pos-1]=0;    

		}
		
		public static void PrintNode_15_puzzle(Node_15_puzzle Obj)
		{
			System.out.println("\nNavigation of space: "+Obj.Direction);
			for(int i=1;i<5;i++)
			{
				for(int j=1;j<5;j++)
			
				{
					System.out.print(Obj.State[i][j]+" ");
				}
				System.out.print("\n");
			}
		}
		
		public static int Create_Child1(Node_15_puzzle[] Array,Node_15_puzzle Parent,Node_15_puzzle Goal,int NextIndex)
		{
			int Index=NextIndex;
			if(Up_Direction1(Parent))
			{
		        Array[Index]=new Node_15_puzzle(Parent.State,Parent.Zero_X_pos,Parent.Zero_Y_pos,Index,Parent.Address,'U');	        
		        Swap_Up1(Array[Index]);
		        Array[Index].Zero_X_pos--;
		        Array[Index].ManhattanDistance=Find_Manhattan_Distance1(Array[Index],Goal);
		       
	            
		        boolean cond=Compare_Node1(Array[Index],Array[Parent.Parent_address]);
		        
	           if(cond==false)
		        {	
		        	Array[Index]=null;
		        
		        }
		        else
		        {  
		        	PrintNode_15_puzzle(Array[Index]);
		        	++Index;
			
		        }
			}
			
		if(Down_Direction1(Parent))
		{
			Array[Index]=new Node_15_puzzle(Parent.State,Parent.Zero_X_pos,Parent.Zero_Y_pos,Index,Parent.Address,'D');	        
	        Swap_Down1(Array[Index]);
	        Array[Index].Zero_X_pos++;
	        Array[Index].ManhattanDistance=Find_Manhattan_Distance1(Array[Index],Goal);
	        
	        
	        boolean cond=Compare_Node1(Array[Index],Array[Parent.Parent_address]);
	        
	        if(cond==false)
	        {	
	        	Array[Index]=null;
	        
	        }
	        else
	        {  
	        	PrintNode_15_puzzle(Array[Index]);
	        	++Index;
		
	        }
	      }
	 	
		if(Right_Direction1(Parent))
		{
			Array[Index]=new Node_15_puzzle(Parent.State,Parent.Zero_X_pos,Parent.Zero_Y_pos,Index,Parent.Address,'R');	        
	        Swap_Right1(Array[Index]);
	        Array[Index].Zero_Y_pos++;
	        Array[Index].ManhattanDistance=Find_Manhattan_Distance1(Array[Index],Goal);
	        
	        boolean cond=Compare_Node1(Array[Index],Array[Parent.Parent_address]);
	        
	        if(cond==false)
	        {	
	        	Array[Index]=null;//--Index;
	        
	        }
	        else
	        {  
	        	PrintNode_15_puzzle(Array[Index]);
	        	++Index;
		
	        }
	       }

		if(Left_Direction1(Parent))
		{
			Array[Index]=new Node_15_puzzle(Parent.State,Parent.Zero_X_pos,Parent.Zero_Y_pos,Index,Parent.Address,'L');	        
	        Swap_Left1(Array[Index]);
	        Array[Index].Zero_Y_pos--;
	        Array[Index].ManhattanDistance=Find_Manhattan_Distance1(Array[Index],Goal);
	      
	        
	        boolean cond=Compare_Node1(Array[Index],Array[Parent.Parent_address]);
	        
	        if(cond==false)
	        {	
	        	Array[Index]=null;
	        
	        }
	        else
	        {  
	        	PrintNode_15_puzzle(Array[Index]);
	        	++Index;
		
	        }
		}
		
		return Index;
		}
		//***********************************************************4*4 END***************************************************************	
	
	
	
	
public static void main(String[] args)
	{
     
      int blankXstate=0;
      int blankYstate=0;
      
      
      System.out.println("A Star implementation for Tile Sliding puzzle-----\n\n");
      
      System.out.println("Enter your option"+"\n 1.3*3 \n 2.4*4\n");
      Scanner scanner = new Scanner(System.in);
      int option=scanner.nextInt();
      if(option==1)
      {
    	  Astar[] ObjectArray=new Astar[400000];
    	  Astar Goal=new Astar();
    	  Goal.State[0][0]=Goal.State[0][1]=Goal.State[0][2]=Goal.State[0][3]=Goal.State[0][4]=Goal.State[1][0]=Goal.State[1][4]=Goal.State[2][0]=Goal.State[2][4]=Goal.State[3][0]=Goal.State[3][4]=Goal.State[4][0]=Goal.State[4][1]=Goal.State[4][2]=Goal.State[4][3]=Goal.State[4][4]=-1;
          Goal.State[1][1]=1;
          Goal.State[1][2]=2;
          Goal.State[1][3]=3;
          Goal.State[2][1]=4;
          Goal.State[2][2]=5;
          Goal.State[2][3]=6;
          Goal.State[3][1]=7;
          Goal.State[3][2]=8;
          Goal.State[3][3]=0;
          int InitialList[][]=new int[5][5];
          
          InitialList[0][0]=InitialList[0][1]=InitialList[0][2]=InitialList[0][3]=InitialList[0][4]=InitialList[1][0]=InitialList[1][4]=InitialList[2][0]=InitialList[2][4]=InitialList[3][0]=InitialList[3][4]=InitialList[4][0]=InitialList[4][1]=InitialList[4][2]=InitialList[4][3]=InitialList[4][4]=-1;
          
          System.out.println("Enter the Initial List as a sequence of integers");
          for(int i=1;i<4;i++)
        	  for(int j=1;j<4;j++)
                 InitialList[i][j]=scanner.nextInt();
          
          for (int i=1; i<4; i++)
          {
              for (int j=1; j<4; j++) 
              {
                  if(InitialList[i][j]==0)
                   {
                      blankXstate = i;
                      blankYstate = j;
                   }
              }
          }
          int count=0;
          ObjectArray[0]=new Astar(InitialList,blankXstate,blankYstate,0,0,'\0');
          ObjectArray[0].ManhattanDistance=Find_Manhattan_Distance(ObjectArray[0],Goal);
          System.out.println("The Root Node is:");
          PrintAstar(ObjectArray[0]);
          
          System.out.println("The New Child nodes created are:"+"\n\n");
          
          int Next_Node_Index=1;
          int i=0;
          for(i=0;Compare_Node(ObjectArray[i],Goal); i++)       
          {
            
        	  Next_Node_Index=Create_Child(ObjectArray,ObjectArray[i],Goal,Next_Node_Index);
        	  
          }
          count=i;
          System.out.println("\nThe solution path is:");
          PrintAstar(ObjectArray[count]);
          
          ApplyAstar(count,ObjectArray);
          
           
      }
      else if(option == 2)
      {
    	  Node_15_puzzle[] ObjectArray=new Node_15_puzzle[300000];
    	  Node_15_puzzle Goal=new Node_15_puzzle();
    	  //initialize the goal state matrix
    	  Goal.State[0][0]=Goal.State[0][1]=Goal.State[0][2]=Goal.State[0][3]=Goal.State[0][4]=Goal.State[0][5]=Goal.State[1][0]=Goal.State[1][5]=Goal.State[2][0]=Goal.State[2][5]=Goal.State[3][0]=Goal.State[3][5]=Goal.State[4][0]=Goal.State[4][5]=Goal.State[5][0]=Goal.State[5][1]=Goal.State[5][2]=Goal.State[5][3]=Goal.State[5][4]=Goal.State[5][5]=-1;
          Goal.State[1][1]=1;
          Goal.State[1][2]=2;
          Goal.State[1][3]=3;
          Goal.State[1][4]=4;
          Goal.State[2][1]=5;
          Goal.State[2][2]=6;
          Goal.State[2][3]=7;
          Goal.State[2][4]=8;
          Goal.State[3][1]=9;
          Goal.State[3][2]=10;
          Goal.State[3][3]=11;
   		  Goal.State[3][4]=12;
   		  
          Goal.State[4][1]=13;
          Goal.State[4][2]=14;
          Goal.State[4][3]=15;
          Goal.State[4][4]=0;    
          int InitialList[][]=new int[6][6];
          
          InitialList[0][0]=InitialList[0][1]=InitialList[0][2]=InitialList[0][3]=InitialList[0][4]=InitialList[0][5]=InitialList[1][0]=InitialList[1][5]=InitialList[2][0]=InitialList[2][5]=InitialList[3][0]=InitialList[3][5]=InitialList[4][0]=InitialList[4][5]=InitialList[5][0]=InitialList[5][1]=InitialList[5][2]=InitialList[5][3]=InitialList[5][4]=InitialList[5][5]=-1;
          
          System.out.println("Enter the Initial List as a sequence of integers");
          for(int i=1;i<5;i++)
        	  for(int j=1;j<5;j++)
                 InitialList[i][j]=scanner.nextInt();
          
          for (int i=1; i<5; i++)
          {
              for (int j=1; j<5; j++) 
              {
                  if(InitialList[i][j]==0)
                   {
                      blankXstate = i;
                      blankYstate = j;
                   }
              }
          }
          int count=0;
          ObjectArray[0]=new Node_15_puzzle(InitialList,blankXstate,blankYstate,0,0,'\0');
          ObjectArray[0].ManhattanDistance=Find_Manhattan_Distance1(ObjectArray[0],Goal);
          System.out.println("The root Astar is:");
          PrintNode_15_puzzle(ObjectArray[0]);
          
          System.out.println("The child Astars created are:"+"\n\n");
          
          int Next_Node_Index=1;
          int i=0;
          for(i=0;Compare_Node1(ObjectArray[i],Goal); i++)       
          {
            
        	  Next_Node_Index=Create_Child1(ObjectArray,ObjectArray[i],Goal,Next_Node_Index);
        	  
          }
          count=i;
          System.out.println("\nThe solution path is:");
          PrintNode_15_puzzle(ObjectArray[count]);
          
          ApplyAstar1(count,ObjectArray); 
      }
      else if(option!= 1 && option!=2)
      {	  System.out.println("Invalid Option Selected"); 
          System.exit(0);
	  }
      
      scanner.close();
	}
      
}


