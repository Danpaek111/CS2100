// Name: Daniel Paek
// Computing ID: <your computing ID>
// Homework Name: HW4 - Blackjack
// Resources used: N/A
public class DeckShoe {
    // An array of decks of cards that comprise this multi-deck.
    private Deck[] decks;

    // Index of which deck we are currently dealing from
    private int currentDeckIndex;

    /**
     * No-arg constructor (UML shows DeckShoe()).
     * Defaults to 1 deck unless your instructions say otherwise.
     */
    public DeckShoe() {
        this(1);
    }

    /**
     * Constructor: instantiates the number of decks specified and
     * adds them to the list of decks.
     */
    public DeckShoe(int numDecks) {
        if (numDecks <= 0) numDecks = 1;

        decks = new Deck[numDecks];
        for (int i = 0; i < numDecks; i++) {
            decks[i] = new Deck();
        }

        restoreDecks();
    }

    /**
     * Deals the top card from the stack of decks and returns that Card.
     * IMPORTANT: if you’ve dealt the last card in all the decks, reshuffle
     * all the decks and start over again.
     */
    public Card dealTopCard() {
        if (cardsLeft() == 0) {
            restoreDecks();
        }

        // Move currentDeckIndex forward until we find a deck with cards
        int checked = 0;
        while (checked < decks.length && decks[currentDeckIndex].cardsLeft() == 0) {
            currentDeckIndex++;
            if (currentDeckIndex >= decks.length) currentDeckIndex = 0;
            checked++;
        }

        // Safety: if still empty, restore and deal again
        if (decks[currentDeckIndex].cardsLeft() == 0) {
            restoreDecks();
        }

        return decks[currentDeckIndex].dealTopCard();
    }

    /**
     * Reshuffles all of the decks.
     * Uses Deck.restockDeck() + Deck.shuffle() per your UML.
     */
    protected void restoreDecks() {
        for (int i = 0; i < decks.length; i++) {
            decks[i].restockDeck();
            decks[i].shuffle();
        }
        currentDeckIndex = 0;
    }

    /**
     * Returns the number of cards left to be dealt in the entire stack of cards.
     */
    public int cardsLeft() {
        int total = 0;
        for (int i = 0; i < decks.length; i++) {
            total += decks[i].cardsLeft();
        }
        return total;
    }
}
