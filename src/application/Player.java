package application;

public class Player
{
	private Card card_info;
	
	public Player()
	{
		card_info = new Card();
		
		card_info.no = 0;
		card_info.suit = 0;
	}
	
	public void Draw(Deck deck)
	{
		card_info = deck.GetCard();
		return;
	}
	
	public int GetNo()
	{
		return card_info.no;
	}
	
	public int GetSuit()
	{
		return card_info.suit;
	}
}