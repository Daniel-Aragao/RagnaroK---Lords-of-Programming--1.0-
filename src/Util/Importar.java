package Util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import Deque.Carta;
import Deque.Carta_Criatura;
import Deque.Tipo_Carta;

public class Importar {

	// IMAGENS URL EM CADA UM DOS TXT's,
	// M�todos espec�ficos para cada tipo de carta,
	// Consertar n�mero de linhas a serem lidas
	// Consertar a descri��o de TODAS as Cartas

	private boolean importarCarta(Carta a, File f, Tipo_Carta tipo) {

		switch (tipo) {
		case CRIATURA:
			return criatura(a, f);
		case MAGICA:
			return magica(a, f);
		case ED:
			return ed(a, f);
		case OO:
			return oo(a, f);
		}
		return false;
	}

	private boolean oo(Carta a, File f) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean ed(Carta a, File f) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean magica(Carta a, File f) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean criatura(Carta a, File f) {
		FileReader arquivo;
		BufferedReader leituraArquivo = null;

		try {
			arquivo = new FileReader(f);
			leituraArquivo = new BufferedReader(arquivo);

		} catch (FileNotFoundException e) {
			System.err.println("Arquivos n�o encontrados");
			e.printStackTrace();
			return false;
		}
		String nome = null;
		String descri��o = null;
		int ataque = 0, defesa = 0, skill = 0;
		String imagemUrl = null;
		BufferedImage imagem = null;
		try {
			nome = leituraArquivo.readLine();
			descri��o = leituraArquivo.readLine();
			ataque = Integer.parseInt(leituraArquivo.readLine());
			defesa = Integer.parseInt(leituraArquivo.readLine());
			skill = Integer.parseInt(leituraArquivo.readLine());
			imagemUrl = leituraArquivo.readLine();
			imagem = ImageIO.read(new File(imagemUrl));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				leituraArquivo.close();
				arquivo.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			return false;
		}

		a = new Carta_Criatura(nome, descri��o, ataque, defesa, skill, imagem);
		try {
			leituraArquivo.close();
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public Lista_de_Generics<Carta> importAllCards(File allDescriptions) {
		Lista_de_Generics<Carta> cartas = new Lista_de_Generics<Carta>(21);

		FileReader arquivo = null;
		BufferedReader leituraArquivo = null;

		try {
			arquivo = new FileReader(allDescriptions);
			leituraArquivo = new BufferedReader(arquivo);

		} catch (FileNotFoundException e) {
			System.err.println("Arquivos n�o encontrados");
			e.printStackTrace();
		}

		try {
			for (int i = 0; i < cartas.length(); i++) {

				String urlDescricao = leituraArquivo.readLine();
				File fileDescricao = new File(urlDescricao);
				String tipo[] = urlDescricao.split("\\");

				if (tipo[3].equalsIgnoreCase("Criaturas")) {

					if (importarCarta(cartas.getElemento(i), fileDescricao,
							Tipo_Carta.CRIATURA)) {
						System.out.println("Carta "
								+ cartas.getElemento(i).getNome()
								+ " Importada!");
					} else {
						System.out.println("Falha na importa��o de "
								+ cartas.getElemento(i).getNome() + "!");
					}

				} else if (tipo[3].equalsIgnoreCase("Magias")) {

					if (importarCarta(cartas.getElemento(i), fileDescricao,
							Tipo_Carta.MAGICA)) {
						System.out.println("Carta "
								+ cartas.getElemento(i).getNome()
								+ " Importada!");
					} else {
						System.out.println("Falha na importa��o de "
								+ cartas.getElemento(i).getNome() + "!");
					}

				} else if (tipo[3].equalsIgnoreCase("ED")) {

					if (importarCarta(cartas.getElemento(i), fileDescricao,
							Tipo_Carta.ED)) {
						System.out.println("Carta "
								+ cartas.getElemento(i).getNome()
								+ " Importada!");
					} else {
						System.out.println("Falha na importa��o de "
								+ cartas.getElemento(i).getNome() + "!");
					}

				} else {

					if (importarCarta(cartas.getElemento(i), fileDescricao,
							Tipo_Carta.OO)) {
						System.out.println("Carta "
								+ cartas.getElemento(i).getNome()
								+ " Importada!");
					} else {
						System.out.println("Falha na importa��o de "
								+ cartas.getElemento(i).getNome() + "!");
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			leituraArquivo.close();
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return cartas;
	}

}
