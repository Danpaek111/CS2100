


/*
 * A superclass that will be used to define a blackjack player.
 * Declared as abstract because 3 abstract methods must be defined by
 *    any subclass that extends this super class.
 */
public abstract class BlackjackPlayer {

	/* How many chips the player starts with */
	private int chips = 1000;

	/*
	 * Storage to store cards in player's hand, and a count of how many are in the hand
	 */
	protected Card[] cards = new Card[22];
	protected int numCards = 0;

	/* The dealer this player is playing with */
	public BlackjackDealer dealer;
	
	/* Three abstract methods that must be defined in a subclass */
	public abstract int getBet();
	public abstract Move getMove();
	public abstract void handOver(Card[] dealerHand);

	/* return current number of chips player has */
	public int getChips() {
		return chips;
	}

	/* reduce number of chips when player loses a hand */
	protected void takeChips(int amount) {
		this.chips -= amount;
	}

	/* add to the number of chips when player loses a hand */
	protected void giveChips(int amount) {
		this.chips += amount;
	}

	/* calculate the score of the player's hand according to blackjack rules */
	public int handScore() {
		int tot = 0;
		int numAces = 0;
		Card c;
		for (int i = 0; i < numCards; ++i) {
			c = cards[i];
			int rank = c.getRank();
			if (rank > 10)
				rank = 10;
			if (rank == 1) {
				numAces++;
				rank = 11;
			}
			tot += rank;
		}
		while (tot > 21 && numAces > 0) {
			tot -= 10;
			numAces--;
		}
		return tot;
	}

	/* associate this player with a given dealer */
	public void setDealer(BlackjackDealer dealer) {
		this.dealer = dealer;
		dealer.setPlayerChips(chips); // tell dealer how many chips player starts with
	}
}
