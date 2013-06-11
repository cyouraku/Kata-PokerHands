import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public abstract class AbstractPokerHands implements Comparable<AbstractPokerHands> {

	protected final List<Integer> cardRanks;
	protected final AbstractPokerHands next;
	private final static List<Character> CARD_RANK_SYMBOLS = Arrays.asList(new Character[]{
			'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'
	});	
	private static final int CARD_INDEX_STEP = 3;
	protected static final int CARD_COUNT = 5;

	protected enum PokerHandsType {
		HIGH_CARD(0),
		PAIR(1);
		
		private final int rank;

		PokerHandsType(int rank) {
			this.rank = rank;
		}
		
		Integer getRank() {
			return rank;
		}
	}
	
	public AbstractPokerHands(String cards, AbstractPokerHands next) {
		cardRanks = initializeCardRanks(cards);
		this.next = next;
	}
	
	abstract protected PokerHandsType getType();

	abstract protected int compare(List<Integer> cardRanks, List<Integer> anotherCardRanks);
	
	private List<Integer> initializeCardRanks(String cards) {
		List<Integer> cardRanks = new ArrayList<>();
		for (int cardIndex = 0; cardIndex < CARD_COUNT; cardIndex++)
			cardRanks.add(getCardRank(cardIndex, cards));
		Collections.sort(cardRanks, Collections.reverseOrder());
		return cardRanks;
	}

	private Integer getCardRank(int cardIndex, String cards) {
		return CARD_RANK_SYMBOLS.indexOf(cards.charAt(cardIndex * CARD_INDEX_STEP));
	}

	protected int compareHighCard(List<Integer> cardRanks, List<Integer> anotherCardRanks) {
		for (int cardIndex = 0; cardIndex < CARD_COUNT; cardIndex++)
			if (cardRanks.get(cardIndex).compareTo(anotherCardRanks.get(cardIndex)) != 0)
				return cardRanks.get(cardIndex).compareTo(anotherCardRanks.get(cardIndex));
		
		return 0;
	}

}