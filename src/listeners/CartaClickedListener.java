package listeners;

import entity.Carta;

public interface CartaClickedListener {
	public void CardClicked(Carta c);
	public void CardHoover(Carta c, boolean b);
}
