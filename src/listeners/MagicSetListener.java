package listeners;

import entity.Carta_Criatura;
import entity.Carta_Magica;

public interface MagicSetListener {

	void magicaSetada(Carta_Criatura carta_Criatura, Carta_Magica cm);
	Carta_Criatura switchAtivado(Carta_Criatura cc);
	Carta_Criatura forAtivado(Carta_Criatura cc);
	
}
