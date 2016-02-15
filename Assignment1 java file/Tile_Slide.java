package Assignment1;

import java.util.Scanner;
import java.util.Stack;


public class Tile_Slide {
	
	public static int Tree_length = 0;
	public static String[] Tree = new String[400000];
	public static String[] Node = new String[50000];
	public static int Level = 0;
	public static boolean Goal_State_Found = false;
	private static Stack<String> stack = new Stack<String>();;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("*************Tile_Slide Problem - Iterative Deepening************* ");
		System.out.println("1. 3*3");
		System.out.println("2. 4*4");
		//Tile_Slide Tree_len = new Tile_Slide();
		//System.out.println("Enter your option");
		
		Scanner Scan = new Scanner(System.in);
		int num;
		System.out.println("Enter your option");
		num = Scan.nextInt();

		while(num!=1 && num!=2)
		{
			System.out.println("Please enter either '1' or '2'");
			num = Scan.nextInt();
		}
		

		if(num == 1){
			System.out.println("**********************(3*3)**********************");
			//String[] Tree = new String[362900];
			Scanner Scan_root = new Scanner(System.in);
			
			System.out.println("Please enter the Initial State as a string");
			String Root = Scan_root.nextLine();
			
			System.out.println("Please enter the Goal State as a string");
			String Goal = Scan_root.nextLine();
			
			if(Root.contentEquals(Goal))
			{
				System.out.println("Initial state is the Goal state");
				return;
			}
		    System.out.println("Initial State");
			
			int[][] Root_matrix = new int[5][5];
			int strlen1 = 0, strlen2 = 1;
			for(int i = 0; i<5; i++){
				for(int j =0; j<5; j++){
					
					if(i>0 && i<4 && j>0 && j<4)
					{
						Root_matrix[i][j] = Integer.parseInt(Root.substring(strlen1,strlen2));
						System.out.print(Root_matrix[i][j]+"	");
						strlen1++;
						strlen2++;
					}
					else
						Root_matrix[i][j] = -1;
	
				}
				System.out.println();
			}
			
			int[][] Goal_matrix = new int[5][5];
			strlen1 = 0; strlen2 = 1;
			for(int i = 0; i<5; i++){
				for(int j =0; j<5; j++){
					
					if(i>0 && i<4 && j>0 && j<4)
					{
						Goal_matrix[i][j] = Integer.parseInt(Goal.substring(strlen1,strlen2));
						
						strlen1++;
						strlen2++;
					}
					else
						Goal_matrix[i][j] = -1;
	
				}
			}
			
			
			
			Tile_Slide.Tree[0] = "0"+"|"+Root+"|N"+"|N"+"|0";
			Tile_Slide.Tree_length = 0;
			Tile_Slide.Node[0] = Root;
			
			stack.push(Tile_Slide.Tree[0]);

			
				Solve_puzzle(Tile_Slide.Tree[0],Goal_matrix);
			
		    
			

		}
		if(num == 2){
			System.out.println("**********************(4*4)**********************");
			Scanner Scan_root = new Scanner(System.in);
			String Root = "";
			
			int[][] Root_matrix = new int[6][6];
			System.out.println("Please enter the Initial State as a sequence of integer");
			for(int i=0;i<6;i++)
			{
				for(int j=0;j<6;j++)
				{
					if(i>0 && i<5 && j>0 && j<5)
					{
						Root_matrix[i][j] = Scan.nextInt();
						if(Root_matrix[i][j] < 10)
							Root = Root+"0"+Root_matrix[i][j];
						else
							Root = Root+Root_matrix[i][j];
					}
					else
						Root_matrix[i][j] = -1;
				}
			}
			
			
			System.out.println("Please enter the Goal State as a sequence of integers");
			String Goal = "";
			int[][] Goal_matrix = new int[6][6];
			for(int i=0;i<6;i++)
			{
				for(int j=0;j<6;j++)
				{
					if(i>0 && i<5 && j>0 && j<5)
					{
						Goal_matrix[i][j] = Scan.nextInt();
						if(Goal_matrix[i][j] < 10)
							Goal = Goal+"0"+Goal_matrix[i][j];
						else
							Goal = Goal+Goal_matrix[i][j];
					}
					else
						Goal_matrix[i][j] = -1;
				}
			}
			
			if(Root.contentEquals(Goal))
			{
				System.out.println("Initial state is the Goal state");
				return;
			}
		    System.out.println("Initial State");
			
			
			int strlen1 = 0, strlen2 = 2;
			for(int i = 0; i<6; i++){
				for(int j =0; j<6; j++){
					
					if(i>0 && i<5 && j>0 && j<5)
					{
						if(Root_matrix[i][j]<10)
							System.out.print("0"+Root_matrix[i][j]+"	");
						else
						System.out.print(Root_matrix[i][j]+"	");
					}
				}
				System.out.println();
			}
			
			
			Tile_Slide.Tree[0] = "0"+"|"+Root+"|N"+"|N"+"|0";
			Tile_Slide.Tree_length = 0;
			Tile_Slide.Node[0] = Root;
			
			stack.push(Tile_Slide.Tree[0]);

			
				Solve_15puzzle(Tile_Slide.Tree[0],Goal_matrix);
			
		    
			
		}
		
	
			
		
	}
	
	public static void Solve_puzzle(String State, int[][] goal){
		

			while(!stack.isEmpty() && !Tile_Slide.Goal_State_Found)
			{
				for(int i = 0;i<=Tile_Slide.Tree_length && !Tile_Slide.Goal_State_Found;i++)
				{
					String Test = stack.peek();
					int depth = i;

					Depth_Search_limited(Tile_Slide.Tree[i],goal,depth);
				}
			}
			int Actual_state_created = 0;
			for(int i=0;i<=Tile_Slide.Tree_length;i++)
			{
				Actual_state_created = i+1;
			}
			
			String Goal_outpt = "";
		    int A_l = Actual_state_created;
		    int Address = Actual_state_created-1;
		    int Start_Parent_Address = 0;
		    int Num_pipes = 0;
		    String Action;
		    String	A_l_test = "";
		    String[] Final = new String[4000];
		    int F_i = Integer.parseInt(Tile_Slide.Tree[Actual_state_created-1].substring(Tile_Slide.Tree[Actual_state_created-1].length()-1,Tile_Slide.Tree[Actual_state_created-1].length()));
		    int k = 1;
		    
		    while(Address!=0){
		    	Num_pipes = 0;
				for(int i = 0;i<Tile_Slide.Tree[Address].length();i++){			
					if(Tile_Slide.Tree[Address].substring(i,i+1).equals("|")){
						Num_pipes++;
						if(Num_pipes == 1)
						{
							Final[F_i-k] = Tile_Slide.Tree[Address].substring(i+1,i+10);
							F_i = F_i -1;
						}
						
						if(Num_pipes == 2){
							Start_Parent_Address = i+1;
							//parent = State.substring(0,i);
							//Sequence = State.substring(i+1,i+10);
						}

						if(Num_pipes == 3){
						A_l = Integer.parseInt(Tile_Slide.Tree[Address].substring(Start_Parent_Address,i));
						//A_l_test = Tileslide.Tree[Address].substring(Start_Parent_Address,i);
							Goal_outpt = Tile_Slide.Tree[Address].substring(i+1,i+2)+Goal_outpt ;
							Address = A_l;

						}
					}
				}
		    }
		    
		    System.out.println("");
		    int[][] output_matrix = new int[3][3];
		    for(int i =0;i<Goal_outpt.length();i++)
		    {
		    	System.out.println("Next Action : "+Goal_outpt.substring(i,i+1));
		    	
				int strlen1 = 0, strlen2 = 1;
				for(int a = 0; a<3; a++){
					for(int b =0; b<3; b++){

							output_matrix[a][b] = Integer.parseInt(Final[i].substring(strlen1,strlen2));
							System.out.print(output_matrix[a][b]+"	");
							strlen1++;
							strlen2++;
					}
					System.out.println();
				}
		    }
		    System.out.println();

		    System.out.println("Sequence of Action:	"+Goal_outpt);
		    
		    System.out.println();
		    System.out.println("Goal State");
		    for(int i =1;i<=3;i++){
		    	for(int j=1;j<=3;j++){
		    		System.out.print(goal[i][j]+"	");
		    	}
		    	System.out.println();
		    }
		    
			
	}
	
	public static void Solve_15puzzle(String State, int[][] goal){
		

		while(!stack.isEmpty() && !Tile_Slide.Goal_State_Found)
		{
			for(int i = 0;i<=Tile_Slide.Tree_length && !Tile_Slide.Goal_State_Found;i++)
			{
				String Test = stack.peek();
				int depth = i;

				Depth_Search_limited_15(Tile_Slide.Tree[i],goal,depth);
			}
		}
		int Actual_state_created = 0;
		for(int i=0;i<=Tile_Slide.Tree_length;i++)
		{
			Actual_state_created = i+1;
		}
	
		String Goal_outpt = "";
	    int A_l = Actual_state_created;
	    int Address = Actual_state_created-1;
	    int Start_Parent_Address = 0;
	    int Num_pipes = 0;
	    String Action;
	    String	A_l_test = "";
	    String[] Final = new String[40000];
	    int F_i = Integer.parseInt(Tile_Slide.Tree[Actual_state_created-1].substring(Tile_Slide.Tree[Actual_state_created-1].length()-1,Tile_Slide.Tree[Actual_state_created-1].length()));
	    int k = 1;
	    
	    while(Address!=0){
	    	Num_pipes = 0;
			for(int i = 0;i<Tile_Slide.Tree[Address].length();i++){			
				if(Tile_Slide.Tree[Address].substring(i,i+1).equals("|")){
					Num_pipes++;
					if(Num_pipes == 1)
					{
						Final[F_i-k] = Tile_Slide.Tree[Address].substring(i+1,i+33);
						F_i = F_i -1;
					}
					
					if(Num_pipes == 2){
						Start_Parent_Address = i+1;
						//parent = State.substring(0,i);
						//Sequence = State.substring(i+1,i+10);
					}

					if(Num_pipes == 3){
					A_l = Integer.parseInt(Tile_Slide.Tree[Address].substring(Start_Parent_Address,i));
					//A_l_test = Tileslide.Tree[Address].substring(Start_Parent_Address,i);
						Goal_outpt = Tile_Slide.Tree[Address].substring(i+1,i+2)+Goal_outpt ;
						Address = A_l;

					}
				}
			}
	    }
	    
	    System.out.println("");
	    int[][] output_matrix = new int[4][4];
	    for(int i =0;i<Goal_outpt.length();i++)
	    {
	    	System.out.println("Next Action : "+Goal_outpt.substring(i,i+1));
	    	
			int strlen1 = 0, strlen2 = 2;
			for(int a = 0; a<4; a++){
				for(int b =0; b<4; b++){

						output_matrix[a][b] = Integer.parseInt(Final[i].substring(strlen1,strlen2));
						System.out.print(output_matrix[a][b]+"	");
						strlen1=strlen1+2;
						strlen2=strlen2+2;
				}
				System.out.println();
			}
	    }

	    System.out.println("Sequence of Action:	"+Goal_outpt);
	    
	    System.out.println("Goal State");
	    for(int i =1;i<=4;i++){
	    	for(int j=1;j<=4;j++){
	    		System.out.print(goal[i][j]+"	");
	    	}
	    	System.out.println();
	    }
	    
		

}
	
	public static void Depth_Search_limited(String State, int[][] Goal_state,int depth)
	{
		int Num_pipes = 0;
		String parent="",Action = "",Sequence = "";
		
		for(int i = 0;i<State.length();i++){			
			if(State.substring(i,i+1).equals("|")){
				Num_pipes++;

				if(Num_pipes == 1){
					parent = State.substring(0,i);
					Sequence = State.substring(i+1,i+10);
				}
				if(Num_pipes == 3){
					Action = State.substring(i+1,i+2);
				}
			}
		}
		
		int[][] temp_array = new int[5][5];
		int i,j,k=0,strlen1=0,strlen2=1,Zero_pos_row=-1,Zero_pos_col=-1;		
		
		for(i = 0; i<5; i++)
		{
			for(j =0; j<5; j++){
				
				if(i>0 && i<4 && j>0 && j<4)
				{
				temp_array[i][j] = Integer.parseInt(Sequence.substring(strlen1,strlen2));
				strlen1 = strlen1 + 1;
				strlen2 = strlen2 + 1;
				}
				else
					temp_array[i][j] = -1;
				
				if(temp_array[i][j] == 0){
					Zero_pos_row = i;
					Zero_pos_col = j;
				}
			

				
			}
		}
		
		
		if(Action.contentEquals("N"))
		{
			//**************************UP-START*********************************************************
			if(temp_array[Zero_pos_row-1][Zero_pos_col] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[5][5];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 5; i++)
				{
					for (j = 0; j < 5; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row-1][Zero_pos_col]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row-1][Zero_pos_col];
					
					for(i=1;i<4;i++)
						for(j=1;j<4;j++)
							Child_node = Child_node + child[i][j];
					
					
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
								int g_found = 1;
								for(i=0;i<5;i++)
								{
									for(j=0;j<5;j++)
									{
										if(child[i][j]!=Goal_state[i][j])
										{
											g_found = 0;
											break;
										}
									}
								}
								if(g_found == 1){
									Tile_Slide.Goal_State_Found = true;
								}
					
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|U|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################UP-END#############################################################
			//**************************DOWN-START*********************************************************
			if(temp_array[Zero_pos_row+1][Zero_pos_col] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[5][5];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 5; i++)
				{
					for (j = 0; j < 5; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row+1][Zero_pos_col]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row+1][Zero_pos_col];
					
					for(i=1;i<4;i++)
						for(j=1;j<4;j++)
							Child_node = Child_node + child[i][j];
					
					
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						int g_found = 1;
						for(i=0;i<5;i++)
						{
							for(j=0;j<5;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}	
					
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|D|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################DOWN-END###########################################################
			//**************************RIGHT-START*********************************************************
			if(temp_array[Zero_pos_row][Zero_pos_col+1] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[5][5];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 5; i++)
				{
					for (j = 0; j < 5; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row][Zero_pos_col+1]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row][Zero_pos_col+1];
					
					for(i=1;i<4;i++)
						for(j=1;j<4;j++)
							Child_node = Child_node + child[i][j];
					
					
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						
						int g_found = 1;
						for(i=0;i<5;i++)
						{
							for(j=0;j<5;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}			
					
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|R|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################RIGHT-END###########################################################
			//**************************LEFT-START*********************************************************
			if(temp_array[Zero_pos_row][Zero_pos_col-1] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[5][5];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 5; i++)
				{
					for (j = 0; j < 5; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row][Zero_pos_col-1]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row][Zero_pos_col-1];
					
					for(i=1;i<4;i++)
						for(j=1;j<4;j++)
							Child_node = Child_node + child[i][j];
					
					
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						
						int g_found = 1;
						for(i=0;i<5;i++)
						{
							for(j=0;j<5;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}			
					
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|L|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################RIGHT-END###########################################################
				
	}
		
		if(Action.contentEquals("U"))
		{
			//**************************UP-START*********************************************************
			if(temp_array[Zero_pos_row-1][Zero_pos_col] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[5][5];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 5; i++)
				{
					for (j = 0; j < 5; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row-1][Zero_pos_col]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row-1][Zero_pos_col];
					
					for(i=1;i<4;i++)
						for(j=1;j<4;j++)
							Child_node = Child_node + child[i][j];
					
					
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						
					
						int g_found = 1;
						for(i=0;i<5;i++)
						{
							for(j=0;j<5;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}				
					
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|U|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################UP-END#############################################################
			
			//**************************RIGHT-START*********************************************************
			if(temp_array[Zero_pos_row][Zero_pos_col+1] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[5][5];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 5; i++)
				{
					for (j = 0; j < 5; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row][Zero_pos_col+1]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row][Zero_pos_col+1];
					
					for(i=1;i<4;i++)
						for(j=1;j<4;j++)
							Child_node = Child_node + child[i][j];
					
					
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						
						int g_found = 1;
						for(i=0;i<5;i++)
						{
							for(j=0;j<5;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|R|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################RIGHT-END###########################################################
			//**************************LEFT-START*********************************************************
			if(temp_array[Zero_pos_row][Zero_pos_col-1] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[5][5];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 5; i++)
				{
					for (j = 0; j < 5; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row][Zero_pos_col-1]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row][Zero_pos_col-1];
					
					for(i=1;i<4;i++)
						for(j=1;j<4;j++)
							Child_node = Child_node + child[i][j];
					
					
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						int g_found = 1;
						for(i=0;i<5;i++)
						{
							for(j=0;j<5;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}				
					
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|L|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################LEFT-END###########################################################
				
	}
		
		if(Action.contentEquals("D"))
		{
			
			//**************************DOWN-START*********************************************************
			if(temp_array[Zero_pos_row+1][Zero_pos_col] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[5][5];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 5; i++)
				{
					for (j = 0; j < 5; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row+1][Zero_pos_col]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row+1][Zero_pos_col];
					
					for(i=1;i<4;i++)
						for(j=1;j<4;j++)
							Child_node = Child_node + child[i][j];
					
					
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						
						int g_found = 1;
						for(i=0;i<5;i++)
						{
							for(j=0;j<5;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}				
					
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|D|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################DOWN-END###########################################################
			//**************************RIGHT-START*********************************************************
			if(temp_array[Zero_pos_row][Zero_pos_col+1] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[5][5];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 5; i++)
				{
					for (j = 0; j < 5; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row][Zero_pos_col+1]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row][Zero_pos_col+1];
					
					for(i=1;i<4;i++)
						for(j=1;j<4;j++)
							Child_node = Child_node + child[i][j];
					
					
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						
						int g_found = 1;
						for(i=0;i<5;i++)
						{
							for(j=0;j<5;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}				
					
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|R|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################RIGHT-END###########################################################
			//**************************LEFT-START*********************************************************
			if(temp_array[Zero_pos_row][Zero_pos_col-1] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[5][5];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 5; i++)
				{
					for (j = 0; j < 5; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row][Zero_pos_col-1]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row][Zero_pos_col-1];
					
					for(i=1;i<4;i++)
						for(j=1;j<4;j++)
							Child_node = Child_node + child[i][j];
					
					
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						
						int g_found = 1;
						for(i=0;i<5;i++)
						{
							for(j=0;j<5;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}				
					
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|L|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################LEFT-END###########################################################
				
	}
		
		if(Action.contentEquals("R"))
		{
			//**************************UP-START*********************************************************
			if(temp_array[Zero_pos_row-1][Zero_pos_col] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[5][5];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 5; i++)
				{
					for (j = 0; j < 5; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row-1][Zero_pos_col]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row-1][Zero_pos_col];
					
					for(i=1;i<4;i++)
						for(j=1;j<4;j++)
							Child_node = Child_node + child[i][j];
					
					
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						
						int g_found = 1;
						for(i=0;i<5;i++)
						{
							for(j=0;j<5;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}				
					
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|U|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################UP-END#############################################################
			//**************************DOWN-START*********************************************************
			if(temp_array[Zero_pos_row+1][Zero_pos_col] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[5][5];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 5; i++)
				{
					for (j = 0; j < 5; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row+1][Zero_pos_col]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row+1][Zero_pos_col];
					
					for(i=1;i<4;i++)
						for(j=1;j<4;j++)
							Child_node = Child_node + child[i][j];
					
					
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						
						int g_found = 1;
						for(i=0;i<5;i++)
						{
							for(j=0;j<5;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}				
					
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|D|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################DOWN-END###########################################################
			//**************************RIGHT-START*********************************************************
			if(temp_array[Zero_pos_row][Zero_pos_col+1] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[5][5];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 5; i++)
				{
					for (j = 0; j < 5; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row][Zero_pos_col+1]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row][Zero_pos_col+1];
					
					for(i=1;i<4;i++)
						for(j=1;j<4;j++)
							Child_node = Child_node + child[i][j];
					
					
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						
						int g_found = 1;
						for(i=0;i<5;i++)
						{
							for(j=0;j<5;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|R|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################RIGHT-END###########################################################

				
	}
		if(Action.contentEquals("L"))
		{
			//**************************UP-START*********************************************************
			if(temp_array[Zero_pos_row-1][Zero_pos_col] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[5][5];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 5; i++)
				{
					for (j = 0; j < 5; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row-1][Zero_pos_col]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row-1][Zero_pos_col];
					
					for(i=1;i<4;i++)
						for(j=1;j<4;j++)
							Child_node = Child_node + child[i][j];
					
					
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						
						int g_found = 1;
						for(i=0;i<5;i++)
						{
							for(j=0;j<5;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}			
					
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|U|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################UP-END#############################################################
			//**************************DOWN-START*********************************************************
			if(temp_array[Zero_pos_row+1][Zero_pos_col] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[5][5];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 5; i++)
				{
					for (j = 0; j < 5; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row+1][Zero_pos_col]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row+1][Zero_pos_col];
					
					for(i=1;i<4;i++)
						for(j=1;j<4;j++)
							Child_node = Child_node + child[i][j];
					
					
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						
						int g_found = 1;
						for(i=0;i<5;i++)
						{
							for(j=0;j<5;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}		
					
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|D|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################DOWN-END###########################################################			
			//**************************LEFT-START*********************************************************
			if(temp_array[Zero_pos_row][Zero_pos_col-1] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[5][5];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 5; i++)
				{
					for (j = 0; j < 5; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row][Zero_pos_col-1]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row][Zero_pos_col-1];
					
					for(i=1;i<4;i++)
						for(j=1;j<4;j++)
							Child_node = Child_node + child[i][j];
					
					
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						
						int g_found = 1;
						for(i=0;i<5;i++)
						{
							for(j=0;j<5;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}		
					
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|L|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################LEFT-END###########################################################
				
	}
				
			
			
		
	

					
					
		
	}
	
	
	public static void Depth_Search_limited_15(String State, int[][] Goal_state,int depth)
	{
		int Num_pipes = 0;
		String parent="",Action = "",Sequence = "";
		
		for(int i = 0;i<State.length();i++){			
			if(State.substring(i,i+1).equals("|")){
				Num_pipes++;

				if(Num_pipes == 1){
					parent = State.substring(0,i);
					Sequence = State.substring(i+1,i+33);
				}
				if(Num_pipes == 3){
					Action = State.substring(i+1,i+2);
				}
			}
		}
		
		int[][] temp_array = new int[6][6];
		int i,j,k=0,strlen1=0,strlen2=2,Zero_pos_row=-1,Zero_pos_col=-1;		
		
		for(i = 0; i<6; i++)
		{
			for(j =0; j<6; j++){
				
				if(i>0 && i<5 && j>0 && j<5)
				{
				temp_array[i][j] = Integer.parseInt(Sequence.substring(strlen1,strlen2));
				strlen1=strlen1+2;
				strlen2=strlen2+2;
				}
				else
					temp_array[i][j] = -1;
				
				if(temp_array[i][j] == 0){
					Zero_pos_row = i;
					Zero_pos_col = j;
				}
			

				
			}
		}
		
		
		if(Action.contentEquals("N"))
		{
			//**************************UP-START*********************************************************
			if(temp_array[Zero_pos_row-1][Zero_pos_col] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[6][6];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 6; i++)
				{
					for (j = 0; j < 6; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row-1][Zero_pos_col]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row-1][Zero_pos_col];
					
					for(i=1;i<5;i++)
						for(j=1;j<5;j++){
							if(child[i][j]<10)
							Child_node = Child_node +"0"+ child[i][j];
							else
							Child_node = Child_node + child[i][j];	
						}
					
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
								int g_found = 1;
								for(i=0;i<6;i++)
								{
									for(j=0;j<6;j++)
									{
										if(child[i][j]!=Goal_state[i][j])
										{
											g_found = 0;
											break;
										}
									}
								}
								if(g_found == 1){
									Tile_Slide.Goal_State_Found = true;
								}
					
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|U|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################UP-END#############################################################
			//**************************DOWN-START*********************************************************
			if(temp_array[Zero_pos_row+1][Zero_pos_col] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[6][6];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 6; i++)
				{
					for (j = 0; j < 6; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row+1][Zero_pos_col]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row+1][Zero_pos_col];
					
					for(i=1;i<5;i++)
						for(j=1;j<5;j++){
							if(child[i][j]<10)
							Child_node = Child_node +"0"+ child[i][j];
							else
							Child_node = Child_node + child[i][j];	
						}
					
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						int g_found = 1;
						for(i=0;i<6;i++)
						{
							for(j=0;j<6;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}	
					
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|D|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################DOWN-END###########################################################
			//**************************RIGHT-START*********************************************************
			if(temp_array[Zero_pos_row][Zero_pos_col+1] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[6][6];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 6; i++)
				{
					for (j = 0; j < 6; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row][Zero_pos_col+1]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row][Zero_pos_col+1];
					
					for(i=1;i<5;i++)
						for(j=1;j<5;j++){
							if(child[i][j]<10)
							Child_node = Child_node +"0"+ child[i][j];
							else
							Child_node = Child_node + child[i][j];	
						}	
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						
						int g_found = 1;
						for(i=0;i<6;i++)
						{
							for(j=0;j<6;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}			
					
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|R|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################RIGHT-END###########################################################
			//**************************LEFT-START*********************************************************
			if(temp_array[Zero_pos_row][Zero_pos_col-1] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[6][6];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 6; i++)
				{
					for (j = 0; j < 6; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row][Zero_pos_col-1]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row][Zero_pos_col-1];
					
					for(i=1;i<5;i++)
						for(j=1;j<5;j++){
							if(child[i][j]<10)
							Child_node = Child_node +"0"+ child[i][j];
							else
							Child_node = Child_node + child[i][j];	
						}			
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						
						int g_found = 1;
						for(i=0;i<6;i++)
						{
							for(j=0;j<6;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}			
					
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|L|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################RIGHT-END###########################################################
				
	}
		
		if(Action.contentEquals("U"))
		{
			//**************************UP-START*********************************************************
			if(temp_array[Zero_pos_row-1][Zero_pos_col] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[6][6];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 6; i++)
				{
					for (j = 0; j < 6; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row-1][Zero_pos_col]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row-1][Zero_pos_col];
					
					for(i=1;i<5;i++)
						for(j=1;j<5;j++){
							if(child[i][j]<10)
							Child_node = Child_node +"0"+ child[i][j];
							else
							Child_node = Child_node + child[i][j];	
						}
					
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						
					
						int g_found = 1;
						for(i=0;i<6;i++)
						{
							for(j=0;j<6;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}				
					
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|U|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################UP-END#############################################################
			
			//**************************RIGHT-START*********************************************************
			if(temp_array[Zero_pos_row][Zero_pos_col+1] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[6][6];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 6; i++)
				{
					for (j = 0; j < 6; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row][Zero_pos_col+1]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row][Zero_pos_col+1];
					
					for(i=1;i<5;i++)
						for(j=1;j<5;j++){
							if(child[i][j]<10)
							Child_node = Child_node +"0"+ child[i][j];
							else
							Child_node = Child_node + child[i][j];	
						}
					
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						
						int g_found = 1;
						for(i=0;i<6;i++)
						{
							for(j=0;j<6;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|R|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################RIGHT-END###########################################################
			//**************************LEFT-START*********************************************************
			if(temp_array[Zero_pos_row][Zero_pos_col-1] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[6][6];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 6; i++)
				{
					for (j = 0; j < 6; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row][Zero_pos_col-1]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row][Zero_pos_col-1];
					
					for(i=1;i<5;i++)
						for(j=1;j<5;j++){
							if(child[i][j]<10)
							Child_node = Child_node +"0"+ child[i][j];
							else
							Child_node = Child_node + child[i][j];	
						}
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						int g_found = 1;
						for(i=0;i<6;i++)
						{
							for(j=0;j<6;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}				
					
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|L|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################LEFT-END###########################################################
				
	}
		
		if(Action.contentEquals("D"))
		{
			
			//**************************DOWN-START*********************************************************
			if(temp_array[Zero_pos_row+1][Zero_pos_col] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[6][6];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 6; i++)
				{
					for (j = 0; j < 6; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row+1][Zero_pos_col]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row+1][Zero_pos_col];
					
					for(i=1;i<5;i++)
						for(j=1;j<5;j++){
							if(child[i][j]<10)
							Child_node = Child_node +"0"+ child[i][j];
							else
							Child_node = Child_node + child[i][j];	
						}	
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						
						int g_found = 1;
						for(i=0;i<6;i++)
						{
							for(j=0;j<6;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}				
					
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|D|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################DOWN-END###########################################################
			//**************************RIGHT-START*********************************************************
			if(temp_array[Zero_pos_row][Zero_pos_col+1] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[6][6];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 6; i++)
				{
					for (j = 0; j < 6; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row][Zero_pos_col+1]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row][Zero_pos_col+1];
					
					for(i=1;i<5;i++)
						for(j=1;j<5;j++){
							if(child[i][j]<10)
							Child_node = Child_node +"0"+ child[i][j];
							else
							Child_node = Child_node + child[i][j];	
						}
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						
						int g_found = 1;
						for(i=0;i<6;i++)
						{
							for(j=0;j<6;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}				
					
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|R|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################RIGHT-END###########################################################
			//**************************LEFT-START*********************************************************
			if(temp_array[Zero_pos_row][Zero_pos_col-1] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[6][6];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 6; i++)
				{
					for (j = 0; j < 6; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row][Zero_pos_col-1]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row][Zero_pos_col-1];
					
					for(i=1;i<5;i++)
						for(j=1;j<5;j++){
							if(child[i][j]<10)
							Child_node = Child_node +"0"+ child[i][j];
							else
							Child_node = Child_node + child[i][j];	
						}
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						
						int g_found = 1;
						for(i=0;i<6;i++)
						{
							for(j=0;j<6;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}				
					
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|L|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################LEFT-END###########################################################
				
	}
		
		if(Action.contentEquals("R"))
		{
			//**************************UP-START*********************************************************
			if(temp_array[Zero_pos_row-1][Zero_pos_col] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[6][6];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 6; i++)
				{
					for (j = 0; j < 6; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row-1][Zero_pos_col]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row-1][Zero_pos_col];
					
					for(i=1;i<5;i++)
						for(j=1;j<5;j++){
							if(child[i][j]<10)
							Child_node = Child_node +"0"+ child[i][j];
							else
							Child_node = Child_node + child[i][j];	
						}	
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						
						int g_found = 1;
						for(i=0;i<6;i++)
						{
							for(j=0;j<6;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}				
					
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|U|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################UP-END#############################################################
			//**************************DOWN-START*********************************************************
			if(temp_array[Zero_pos_row+1][Zero_pos_col] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[6][6];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 6; i++)
				{
					for (j = 0; j < 6; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row+1][Zero_pos_col]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row+1][Zero_pos_col];
					
					for(i=1;i<5;i++)
						for(j=1;j<5;j++){
							if(child[i][j]<10)
							Child_node = Child_node +"0"+ child[i][j];
							else
							Child_node = Child_node + child[i][j];	
						}
					
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						
						int g_found = 1;
						for(i=0;i<6;i++)
						{
							for(j=0;j<6;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}				
					
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|D|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################DOWN-END###########################################################
			//**************************RIGHT-START*********************************************************
			if(temp_array[Zero_pos_row][Zero_pos_col+1] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[6][6];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 6; i++)
				{
					for (j = 0; j < 6; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row][Zero_pos_col+1]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row][Zero_pos_col+1];
					
					for(i=1;i<5;i++)
						for(j=1;j<5;j++){
							if(child[i][j]<10)
							Child_node = Child_node +"0"+ child[i][j];
							else
							Child_node = Child_node + child[i][j];	
						}	
					
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						
						int g_found = 1;
						for(i=0;i<6;i++)
						{
							for(j=0;j<6;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|R|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################RIGHT-END###########################################################

				
	}
		if(Action.contentEquals("L"))
		{
			//**************************UP-START*********************************************************
			if(temp_array[Zero_pos_row-1][Zero_pos_col] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[6][6];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 6; i++)
				{
					for (j = 0; j < 6; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row-1][Zero_pos_col]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row-1][Zero_pos_col];
					
					for(i=1;i<5;i++)
						for(j=1;j<5;j++){
							if(child[i][j]<10)
							Child_node = Child_node +"0"+ child[i][j];
							else
							Child_node = Child_node + child[i][j];	
						}		
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						
						int g_found = 1;
						for(i=0;i<6;i++)
						{
							for(j=0;j<6;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}			
					
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|U|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################UP-END#############################################################
			//**************************DOWN-START*********************************************************
			if(temp_array[Zero_pos_row+1][Zero_pos_col] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[6][6];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 6; i++)
				{
					for (j = 0; j < 6; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row+1][Zero_pos_col]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row+1][Zero_pos_col];
					
					for(i=1;i<5;i++)
						for(j=1;j<5;j++){
							if(child[i][j]<10)
							Child_node = Child_node +"0"+ child[i][j];
							else
							Child_node = Child_node + child[i][j];	
						}	
					
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						
						int g_found = 1;
						for(i=0;i<6;i++)
						{
							for(j=0;j<6;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}		
					
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|D|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################DOWN-END###########################################################			
			//**************************LEFT-START*********************************************************
			if(temp_array[Zero_pos_row][Zero_pos_col-1] != -1 && !Tile_Slide.Goal_State_Found)
			{
				int[][] child = new int[6][6];
				String Child_node = "";
				int Flag = 0;
				
				for (i = 0; i < 6; i++)
				{
					for (j = 0; j < 6; j++)
					{
					child[i][j] = temp_array[i][j];
					}
				}
					child[Zero_pos_row][Zero_pos_col-1]=0;
					child[Zero_pos_row][Zero_pos_col]= temp_array[Zero_pos_row][Zero_pos_col-1];
					
					for(i=1;i<5;i++)
						for(j=1;j<5;j++){
							if(child[i][j]<10)
							Child_node = Child_node +"0"+ child[i][j];
							else
							Child_node = Child_node + child[i][j];	
						}
					
					
					for(i =0;i<=Tile_Slide.Tree_length;i++)
					{
						if(Child_node.equalsIgnoreCase(Tile_Slide.Node[i]))
						{
							Flag = 1;
							break;
						}
					}
					
					
					if(Flag == 0)
					{
						
						int g_found = 1;
						for(i=0;i<6;i++)
						{
							for(j=0;j<6;j++)
							{
								if(child[i][j]!=Goal_state[i][j])
								{
									g_found = 0;
									break;
								}
							}
						}
						if(g_found == 1){
							Tile_Slide.Goal_State_Found = true;
						}		
					
					Tile_Slide.Tree_length++;
					int level = Integer.parseInt(State.substring(State.length()-1,State.length()))+1;
					Tile_Slide.Tree[Tile_Slide.Tree_length] = Tile_Slide.Tree_length+"|"+Child_node+"|"+parent+"|L|"+level;
					Tile_Slide.Node[Tile_Slide.Tree_length] = Child_node;
					stack.push(Tile_Slide.Tree[Tile_Slide.Tree_length]);
					
					}				
			}
			//##########################LEFT-END###########################################################
				
	}
				
			
			
		
	

					
					
		
	}
	
	

}
