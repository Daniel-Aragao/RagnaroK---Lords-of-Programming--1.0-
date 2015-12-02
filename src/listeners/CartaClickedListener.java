package listeners;

import entity.Carta;
import entity.Entity;

public interface CartaClickedListener {
	public void CardClicked(Entity entidade);
	public void CardHoover(Carta c, boolean b);
}
