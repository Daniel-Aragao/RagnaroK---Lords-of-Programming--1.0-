package listeners;

import entity.Carta_Criatura;
import entity.Carta_Magica;

public interface MagicSetListener {

	void magicaSetada(Carta_Criatura carta_Criatura, Carta_Magica cm);
	void swtichAtivado(Carta_Criatura cc);
	void forAtivado(Carta_Criatura cc);
	
}
