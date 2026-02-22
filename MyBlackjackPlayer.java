// Name: Daniel Paek
// Computing ID: <your computing ID>
// Homework Name: HW4 - Blackjack
// Resources used: N/A
public class MyBlackjackPlayer extends BlackjackPlayer {
    public MyBlackjackPlayer() {
        super();
    }

    /**
     * Bets a modest amount that won’t instantly bankrupt you.
     * (Reasonable improvement over always betting 10: bet slightly more when ahead.)
     */
    @Override
    public int getBet() {
        int chips = getChips();
        if (chips <= 0) return 1;

        // Small scaling with bankroll (still conservative)
        int bet = 10;
        if (chips > 1500) bet = 20;
        if (chips > 2500) bet = 30;

        if (bet > chips) bet = chips;
        return bet;
    }

    /**
     * Basic strategy-ish (simplified):
     * - Hit on 11 or less
     * - Stand on 17+
     * - For 12–16: stand if dealer shows 2–6, otherwise hit
     * - Some reasonable doubles
     */
    @Override
    public Move getMove() {
        int myScore = handScore();

        // If already 21 or bust, stop
        if (myScore >= 21) return Move.STAY;

        Card dealerUp = this.dealer.getVisibleCard();
        int dealerVal = cardValueForDealerUpcard(dealerUp);

        // Double-down (simple, reasonable)
        // (Only do this if DOUBLE is allowed by the simulator rules)
        if (myScore == 11) return Move.DOUBLE;
        if (myScore == 10 && dealerVal <= 9) return Move.DOUBLE;
        if (myScore == 9 && dealerVal >= 3 && dealerVal <= 6) return Move.DOUBLE;

        // Hit/Stand rules
        if (myScore <= 11) return Move.HIT;
        if (myScore >= 17) return Move.STAY;

        // 12–16
        if (dealerVal >= 2 && dealerVal <= 6) return Move.STAY;
        return Move.HIT;
    }

    /**
     * Called after the hand ends; you can ignore it or use it for tracking.
     * We'll keep it simple (no state needed).
     */
    @Override
    public void handOver(Card[] dealerHand) {
        // No tracking needed for this reasonable strategy.
    }

    /* ---------------- Helper ---------------- */

    /**
     * Convert dealer visible card to a blackjack "upcard value":
     * rank: 1=Ace, 11=Jack, 12=Queen, 13=King (typical)
     * If your project uses different ranks, adjust here.
     */
    private int cardValueForDealerUpcard(Card c) {
        if (c == null) return 0;

        int r = c.getRank();
        if (r == 1) return 11;      // Ace
        if (r >= 11 && r <= 13) return 10;  // Face cards
        return r;                   // 2..10
    }
}