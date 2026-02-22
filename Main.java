

public class Main {
	
	public static void main(String[] args) {
		System.out.println("Let's play some blackjack!");
		
		int numGames = 1000; // how many games to play (adjust this while debugging if you wish)
		MyBlackjackPlayer p = new MyBlackjackPlayer();
		BlackjackDealer dealer = new BlackjackDealer(p);
		p.setDealer(dealer);
		
		for(int i=0; i<numGames; i++) {
			dealer.playHand(i+1);
		}
		
		System.out.println("_____________SUMMARY_______________");
		System.out.println("FINAL CHIP COUNT: " + p.getChips());
	}

}
