package hW;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class  ConnectProject extends JFrame implements ActionListener{
	
	
	private JPanel Connect4;
	private JPanel window;
	private JLabel display;
	private JPanel [][] board;// = new Jbutton [3][3]
	private String player = "Black";
	private JButton [] label;
	private JPanel button;
	
	public void ConnectFrame(){
		setTitle("Connect 4 Game");
		//
		Connect4 = new JPanel();
		Connect4.setLayout(new BorderLayout());
		//window
		window = new JPanel();
		window.setLayout(new BorderLayout());
		window.setBackground(Color.BLACK);//background is black
		//display
		display = new JLabel();
		display.setHorizontalAlignment(JLabel.CENTER);//display will be in the center 
		
		
		window.add(display,BorderLayout.NORTH);
		
		button = new JPanel(); 
		button.setLayout(new GridLayout(1 , 7));
		displayButton();//calling displayButton Method
		window.add(button, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,500);
		setVisible(true);
	}
	public void displayButton(){
		label = new JButton[7];//7 buttons created to do things
		for(int i=0; i< label.length; i++){
			label[i] = new JButton();
			//characteristics of label
			label[i].setBackground(Color.RED);
			label[i].setFocusable(false);
			label[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
			label[i].addActionListener(this);
			label[i].setEnabled(true);
			label[i].setText("C"+ (i+1));
			button.add(label[i]);
		 }
			
		}
	public void displayBoard() {//creates the board to play
		board = new JPanel[6][7];
		for (int row=0; row<board.length; row++){
			for(int col=0; col<board[row].length; col++){
				board [row][col] = new JPanel();
				board [row][col].setBackground(Color.black);
				board [row][col].setFocusable(false);
				board [row][col].setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
				Connect4.add(board[row][col]);
						
			}
		}
	}
	public static void main (String[]args){
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				ConnectProject Con4 = new ConnectProject();
			}
		}
				);
	}
	

	public void anotherGame() {
		int yesNo = JOptionPane.showConfirmDialog(null, "Do you want to play another game?");
		if(yesNo == 0){
			clearBoard();
			
		}
		else{
			JOptionPane.showMessageDialog(null, "Thanks for playing");
			System.exit (EXIT_ON_CLOSE);
		}
		System.out.print(yesNo);
	}
	
	public void clearBoard() {
		for(int row=0; row<board.length; row++) {
			for(int col = 0; col< board[row].length;col++){
				board[row][col].setBackground(Color.WHITE);
			}
			for(int i = 0; i <label.length; i++){
				label[i].setEnabled(true);
			}
		}
	}
	public void updateCurrPlayer(){
		if (player.equalsIgnoreCase("Green")){
			player = "RED";
		}
		else{
			player = "Green";
		}
	}
	
	public void displayWinner(){
		if (player.equalsIgnoreCase("GREEN")){
			System.out.println("GREEN is the winner!");
		}
		else if (player.equalsIgnoreCase("RED")){
			System.out.println("Red is the winner");
		}
		else{
			System.out.println("Draw");
		}
		
	}
	public void nextD(int col){
		for(int row = 5 ; row>=0;row--){
			if((!(board[row][col].getBackground().equals(Color.BLUE)) && (board[row][col].getBackground().equals(Color.RED)) )){
				Color myColor = Color.ORANGE;
				if (player.equals("RED")){
					myColor = Color.red;
				}
			board[row][col].setBackground(myColor);
			if (row==0){
				label[col].setEnabled(false);
			}
			break;
			}
		}
	}
	public void actionPer(ActionEvent e){
		JButton bttnClicked = (JButton) e.getSource();
		
		for(int j=0; j< label.length; j++){
			if(bttnClicked.equals(label[j])){
				nextD(j);
			}
		}
		
		if (isWinner(player) || isFull()){
			displayWinner();
			anotherGame();
		}
		updateCurrPlayer();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public boolean isWinner(String player) {

        Color myColor;
        if(player.equals("RED")){
            myColor = Color.RED;}
        else {
            myColor = Color.GREEN;}


        //check rows
        for(int row=0; row<board.length; row++){
            int rowCount=0;//row match counter, resets on next row
            for(int col=0; col<board[row].length; col++){
                if(board[row][col].getBackground().equals(myColor)){

                    rowCount++;//increment counter
                } else {
                    rowCount=0;
                }
                if(rowCount == 4){
                    return true;//found 3 in same row

                }
            }
        }
        //check columns
        for(int col=0; col<7; col++){
            int colCount=0;
            for(int row=0; row<6; row++){
                if(board[row][col].getBackground().equals(myColor)){
                    colCount++;
                } else {colCount=0;	}
                if(colCount ==4){
                    return true;//found 3 in same column
                }
            }
        }



        //check main diagonal [0][0],[1][1],[2][2]

        int colInc=0;

        for(int i = 0; i < 4 ;i++){
            int col = colInc;
            int  row=0;


            int count=0;

            while (( col <= board.length) && (row < 6)){

                if(board[row][col].getBackground().equals(myColor)){
                    count++;
                } else {
                    count=0;
                }
                if (count == 4){
                    return true;}
                row++;
                col++; }
            colInc++; }


        //check main diagonal lower
        int rowInc=1;

        for(int i = 0; i < 2 ;i++){
            int col =0;
            int  row =rowInc;
            int count =0;


            while (( col <= board.length) && (row < 6)){
                if(board[row][col].getBackground().equals(myColor)){
                    count++;
                } else {
                    count=0;			}
                if (count == 4){
                    return true;

                }
                row++;
                col++;
            }

            System.out.println();

            rowInc++;

        }

        int colInc2 =0;
        for(int i =0; i < 4; i++){
            int rowCount = 5;
            int colCount = colInc2;
            int  countMatch=0;

            while ((colCount <= board.length)&& (rowCount >=0)){
                if(board[rowCount][colCount].getBackground().equals(myColor)){
                    countMatch++;
                } else {
                    countMatch=0;
                }
                if (countMatch == 4){
                    return true;

                }
                colCount++;
                rowCount--;
            }
            colInc2++;
        }

        int rowDec =4;

        for(int i =0; i < 2; i++){
            int rowCount = rowDec;// numrows -2
            int colCount = 0;
            int countMatch=0;

            while ((colCount <= board.length)&& (rowCount >=0)){
                if(board[rowCount][colCount].getBackground().equals(myColor)){
                    countMatch++;}
                else {rowCount=0;	}
                if (countMatch == 4){
                    return true;        	}

                colCount++;
                rowCount--;		}

            rowDec--;	}
        return false;
    }

    public boolean isFull() {
        for(int row=0; row<board.length; row++){
            for(int col=0; col<board[row].length; col++){
                if( !(board[row][col].getBackground().equals(Color.GREEN)) && !(board[row][col].getBackground().equals(Color.RED)) ){
                    return false;
                }

            }
        }
        return true;
    }
}

