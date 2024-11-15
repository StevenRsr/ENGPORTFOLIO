package hw2;

/**
 * Model of a Monopoly-like game. Two players take turns rolling dice to move
 * around a board. The game ends when one of the players has at least
 * MONEY_TO_WIN money or one of the players goes bankrupt (has negative money).
 * 
 * @author StevenRagan
 */
public class CyGame {
	/**
	 * Do nothing square type.
	 */
	public static final int DO_NOTHING = 0;
	/**
	 * Pass go square type.
	 */
	public static final int PASS_GO = 1;
	/**
	 * Cyclone square type.
	 */
	public static final int CYCLONE = 2;
	/**
	 * Pay the other player square type.
	 */
	public static final int PAY_PLAYER = 3;
	/**
	 * Get an extra turn square type.
	 */
	public static final int EXTRA_TURN = 4;
	/**
	 * Jump forward square type.
	 */
	public static final int JUMP_FORWARD = 5;
	/**
	 * Stuck square type.
	 */
	public static final int STUCK = 6;
	/**
	 * Points awarded when landing on or passing over go.
	 */
	public static final int PASS_GO_PRIZE = 200;
	/**
	 * The amount payed to the other player per unit when landing on a
	 * PAY_PLAYER square.
	 */
	public static final int PAYMENT_PER_UNIT = 20;
	/**
	 * The amount of money required to win.
	 */
	public static final int MONEY_TO_WIN = 400;
	/**
	 * The cost of one unit.
	 */
	public static final int UNIT_COST = 50;
	
	
	// TODO: EVERTHING ELSE
	// Note that this code will not compile until you have put in stubs for all
	// the required methods.
	/**
	 * number of squares on board 
	 * @author StevenRagan
	 */
	private int numSquares;
	/**
	 * amount of money player 1 has 
	 * @author StevenRagan
	 */
	private int moneyPlayer1;
	/**
	 * amount of money player 2 has
	 * @author StevenRagan
	 */
	private int moneyPlayer2;
	/**
	 * square player 1 is on 
	 * @author StevenRagan
	 */
	private int squarePlayer1;
	/**
	 * square player 2 is on
	 * @author StevenRagan
	 */
	private int squarePlayer2;
	/**
	 * units player 1 has 
	 * @author StevenRagan
	 */
	private int unitsPlayer1;
	/**
	 * units player 2 has
	 * @author StevenRagan 
	 */
	private int unitsPlayer2;
	/**
	 * type of square current player is on
	 * @author StevenRagan
	 */
	private int squareType;
	/**
	 * number of current player
	 * @author StevenRagan 
	 */
	private int currentPlayerNum;
	/**
	 * CyGame 
	 * Function creates the game based on squares and money. 
	 * @param squares # of squares the board will have 
	 * @param money starting money for the players
	 * @author StevenRagan
	 */
	public CyGame(int squares, int money) {
		numSquares=squares;
		moneyPlayer1=money;
		moneyPlayer2=money;
		currentPlayerNum=1;
		unitsPlayer1=1;
		unitsPlayer2=1;
		squarePlayer1=0;
		squarePlayer2=0;
	}
	/**
	 * buyUnit 
	 * Function check to see if game has ended if it has player 
	 * can't buy unit otherwise player buys a unit for unit_cost.
	 * then ends turn.
	 * @author StevenRagan
	 */
	public void buyUnit() {
		if(!isGameEnded()){
		if(currentPlayerNum==1) {
			if(getSquareType(squarePlayer1)==DO_NOTHING) {
				if(moneyPlayer1>=UNIT_COST) {
					unitsPlayer1+=1;
					moneyPlayer1=moneyPlayer1-UNIT_COST;
					endTurn();
				}
			}
		}else if(currentPlayerNum==2) {
			if(getSquareType(squarePlayer2)==DO_NOTHING) {
				if(moneyPlayer2>=UNIT_COST) {
					unitsPlayer2+=1;
					moneyPlayer2=moneyPlayer2-UNIT_COST;
					endTurn();
				}
			}			
		}
		}
		
	}
	/**
	 * endTurn
	 * Function switches the number of current player.
	 * @author Steven Ragan
	 */
	public void endTurn() {
	if (currentPlayerNum==1) {
		currentPlayerNum = 2;
	}
	else if (currentPlayerNum==2) {
		currentPlayerNum = 1;
	}
	}
	/**
	 * getCurrentPlayer 
	 * Function returns the # of current player  
	 * @return currentPlayerNum
	 * @author Steven Ragan
	 */
	public int getCurrentPlayer() {
		return currentPlayerNum;
	}
	/**
	 * getPlayer1Money 
	 * Function returns amount of money player1 has. 
	 * @return moneyPlayer1
	 * @author Steven Ragan
	 */
	public int getPlayer1Money() {
		return moneyPlayer1;
	}
	/**
	 * getPlayer1Square 
	 * Function returns # of square player 1 is on. 
	 * @return squarePlayer1
	 * @author Steven Ragan
	 */
	public int getPlayer1Square() {
		return squarePlayer1;
	}
	/**
	 * getPlayer1Units 
	 * Function returns # of units player1 has. 
	 * @return unitsPlayer1
	 * @author Steven Ragan
	 */
	public int getPlayer1Units() {
		return unitsPlayer1;
	}
	/**
	 * getPlayer2Money 
	 * Function returns amount of money player2 has.
	 * @return moneyPlayer2
	 * @author Steven Ragan
	 */
	public int getPlayer2Money() {
		return moneyPlayer2;
	}
	/**
	 * getPlayer2Square 
	 * Function returns # of square player 2 is on. 
	 * @return squarePlayer2
	 * @author Steven Ragan
	 */
	public int getPlayer2Square() {
		return squarePlayer2;
	}
	/**
	 * getPlayer2Units 
	 * Function returns # of units player2 has. 
	 * @return unitsPlayer2
	 * @author Steven Ragan
	 */
	public int getPlayer2Units() {
		return unitsPlayer2;
	}
	/**
	 * getSquareType 
	 * Function returns the type of square 
	 * based on what the input square is divisible by.
	 * @param square # of the square you want to get type of 
	 * @return type of square 
	 * @author Steven Ragan
	 */
	public int getSquareType(int square) {
		squareType=DO_NOTHING;
		if(square%2==0) {
			squareType=JUMP_FORWARD;
		}
			
		if (square%3==0){
			squareType=STUCK;
		}
		if ((square%7==0)||(square%11==0)){
			squareType=EXTRA_TURN;
		}
		if (square%5==0){
			squareType=PAY_PLAYER;
		}
		if (square==(numSquares-1)){
			squareType=CYCLONE;
		}
		if (square==0){
			squareType=PASS_GO;
		}
		return squareType;
	}
	/**
	 * isGameEnded 
	 * function checks it see if the game has
	 * ended if current values are it it returns true if 
	 * not it will return false
	 * @return true if game ended and false if not 
	 * @author Steven Ragan
	 */
	public boolean isGameEnded() {
		if(moneyPlayer1<0||moneyPlayer2<0||moneyPlayer1>=MONEY_TO_WIN||moneyPlayer2>=MONEY_TO_WIN) {
		return true;
		}else
		return false;
	}
	/**
	 *roll 
	 *Function moves the player by given value
	 *unless the player is on a stuck square and the 
	 *value is odd then affect the player based on 
	 *what the square landed on is. Finished by ending 
	 *turn. 
	 *
	 *
	 * @param value #of spaces the player will move
	 * @author Steven Ragan
	 */
	public void roll(int value) {
		if(!isGameEnded()) {
		if (currentPlayerNum==1) {
			if(getSquareType(squarePlayer1)==STUCK) {
				if(value%2==0) {
					squarePlayer1+=value;
					if(squarePlayer1>=numSquares) {
						moneyPlayer1+=PASS_GO_PRIZE;
						squarePlayer1= squarePlayer1%numSquares;
					}
				}

			}else {
				squarePlayer1+=value;
				if(squarePlayer1>=numSquares) {
					moneyPlayer1+=PASS_GO_PRIZE;
					squarePlayer1= squarePlayer1%numSquares;
				}
				
			}
			
			if(getSquareType(squarePlayer1)==CYCLONE) {
				squarePlayer1=squarePlayer2;
				endTurn();
			}
			else if(getSquareType(squarePlayer1)==PAY_PLAYER) {
				moneyPlayer2+=(PAYMENT_PER_UNIT*unitsPlayer2);
				moneyPlayer1-=(PAYMENT_PER_UNIT*unitsPlayer2);
				endTurn();
			}
			else if(getSquareType(squarePlayer1)==EXTRA_TURN) {
				
			}
			else if(getSquareType(squarePlayer1)==STUCK) {
				endTurn();
			}
			else if(getSquareType(squarePlayer1)==JUMP_FORWARD) {
				if(!isGameEnded()) {
				squarePlayer1+=4;
				
				if(squarePlayer1>=numSquares) {
					moneyPlayer1+=PASS_GO_PRIZE;
					squarePlayer1= squarePlayer1%numSquares;
				}
				}
				endTurn();
				
			}
			else if(getSquareType(squarePlayer1)==DO_NOTHING) {
				endTurn();
			}
		}
		
		else if (currentPlayerNum==2) {
			if(getSquareType(squarePlayer2)==STUCK) {
				if(value%2==0) {
					squarePlayer2+=value;
					if(squarePlayer2>=numSquares) {
						moneyPlayer2+=PASS_GO_PRIZE;
						squarePlayer2= squarePlayer2%numSquares;
					}
				}
			}else {
				squarePlayer2+=value;
				if(squarePlayer2>=numSquares) {
					moneyPlayer2+=PASS_GO_PRIZE;
					squarePlayer2= squarePlayer2%numSquares;
				}
				
			}
			if(getSquareType(squarePlayer2)==CYCLONE) {
				squarePlayer2=squarePlayer1;
				endTurn();
			}
			else if(getSquareType(squarePlayer2)==PAY_PLAYER) {
				moneyPlayer1+=(PAYMENT_PER_UNIT*unitsPlayer1);
				moneyPlayer2-=(PAYMENT_PER_UNIT*unitsPlayer1);
				endTurn();
			}
			else if(getSquareType(squarePlayer2)==EXTRA_TURN) {
				
			}
			else if(getSquareType(squarePlayer2)==STUCK) {
				endTurn();
			}
			else if(getSquareType(squarePlayer2)==JUMP_FORWARD) {
				if(!isGameEnded()) {
				squarePlayer2+=4;
				if(squarePlayer2>=numSquares) {
					moneyPlayer2+=PASS_GO_PRIZE;
					squarePlayer2= squarePlayer2%numSquares;
				}
				}
				endTurn();
				
			}
			else if(getSquareType(squarePlayer2)==DO_NOTHING) {
				endTurn();
			}
		}
		}	
		
	}
	/** 
	 * sellUnit
	 * Function checks to see if game has ended or not.
	 * If it hasen't then the player sells 1 unit gets back 1 unit cost 
	 * then ends turn
	 * @author Steven Ragan
	 */
	public void sellUnit() {
		if(!isGameEnded()) {
		if(currentPlayerNum==1) {
				if(unitsPlayer1>=1) {
					unitsPlayer1=unitsPlayer1-1;
					moneyPlayer1=moneyPlayer1+UNIT_COST;
					endTurn();
				}
		}else if(currentPlayerNum==2) {
				if(unitsPlayer2>=1) {
					unitsPlayer2=unitsPlayer2-1;
					moneyPlayer2=moneyPlayer2+UNIT_COST;
					endTurn();
				}
		}				
		}		
	}
	
	// The method below is provided for you and you should not modify it.
	// The compile errors will go away after you have written stubs for the
	// rest of the API methods.
	

	/**
	 * Returns a one-line string representation of the current game state. The
	 * format is:
	 * <p>
	 * <tt>Player 1*: (0, 0, $0) Player 2: (0, 0, $0)</tt>
	 * <p>
	 * The asterisks next to the player's name indicates which players turn it
	 * is. The numbers (0, 0, $0) indicate which square the player is on, how
	 * many units the player has, and how much money the player has
	 * respectively.
	 * 
	 * @return one-line string representation of the game state
	 */
	public String toString() {
		String fmt = "Player 1%s: (%d, %d, $%d) Player 2%s: (%d, %d, $%d)";
		String player1Turn = "";
		String player2Turn = "";
		if (getCurrentPlayer() == 1) {
			player1Turn = "*";
		} else {
			player2Turn = "*";
		}
		return String.format(fmt,
				player1Turn, getPlayer1Square(), getPlayer1Units(), getPlayer1Money(),
				player2Turn, getPlayer2Square(), getPlayer2Units(), getPlayer2Money());
	}
}
