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
import Deque.Carta_Especial;
import Deque.Carta_Magica;
import Deque.Tipo_Carta;

public class Importar {

	// IMAGENS URL EM CADA UM DOS TXT's,
	// M�todos espec�ficos para cada tipo de carta,
	// Consertar n�mero de linhas a serem lidas
	// Consertar a descri��o de TODAS as Cartas

	private Carta importarCarta(File f, Tipo_Carta tipo) {

		switch (tipo) {
		case CRIATURA:
			return criatura(f);
		case MAGICA:
		case ED:
		case OO:
			return especial_magica(f, tipo);
		}
		return null;
	}

	private Carta especial_magica(File f, Tipo_Carta tipo) {
		FileReader arquivo = null;
		BufferedReader leituraArquivo = null;

		try {
			arquivo = new FileReader(f);
			leituraArquivo = new BufferedReader(arquivo);

		} catch (FileNotFoundException e) {
			System.err.println("Arquivos n�o encontrados");
			e.printStackTrace();
		}

		String nome = null;
		String descri��o = null;
		String imagemUrl = null;
		BufferedImage imagem = null;

		try {
			nome = leituraArquivo.readLine();
			descri��o = leituraArquivo.readLine();
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
		}

		Carta a = null;
		switch (tipo) {
		case MAGICA:
			a = new Carta_Magica(nome, descri��o, imagem);
			break;
		case ED:
			a = new Carta_Especial(nome, descri��o, imagem,tipo);
			break;
		case OO:
			a = new Carta_Especial(nome, descri��o, imagem,tipo);
		}
		try {
			leituraArquivo.close();
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return a;
	}

	private Carta_Criatura criatura(File f) {
		
		FileReader arquivo = null;
		BufferedReader leituraArquivo = null;

		try {
			arquivo = new FileReader(f);
			leituraArquivo = new BufferedReader(arquivo);

		} catch (FileNotFoundException e) {
			System.err.println("Arquivos n�o encontrados");
			e.printStackTrace();
			//return false;
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
			//debug avan�ado
			System.out.printf("%s, %s, %d, %d, %d, %s\n",nome,descri��o,ataque,defesa,skill,imagemUrl);
			//
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
			//return false;
		}

		Carta_Criatura a = new Carta_Criatura(nome, descri��o, ataque, defesa, skill, imagem);
		//debug avan�ado
		System.out.println(a.getNome());
		//
		try {
			leituraArquivo.close();
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return a;
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
				
				String tipo[] = urlDescricao.split("/");
				//debug avan�ado
				System.out.println("tipo[2]: "+tipo[2]+i);
				//
				if (tipo[2].equalsIgnoreCase("Criaturas")) {
					System.out.println("Criatura");
					cartas.add(i,importarCarta(fileDescricao,Tipo_Carta.CRIATURA)); 
						System.out.println("Carta "
								+ cartas.getElemento(i).getNome()
								+ " Importada!");
					

				} else if (tipo[2].equalsIgnoreCase("Magias")) {
					System.out.println("Magia");
					cartas.add(i,importarCarta(fileDescricao,Tipo_Carta.MAGICA)); 
						System.out.println("Carta "
								+ cartas.getElemento(i).getNome()
								+ " Importada!");
					

				} else if (tipo[2].equalsIgnoreCase("ED")) {
					System.out.println("ED");
					cartas.add(i,importarCarta(fileDescricao,Tipo_Carta.ED));
						System.out.println("Carta "
								+ cartas.getElemento(i).getNome()
								+ " Importada!");
					

				} else {
					System.out.println("OO");
					cartas.add(i,importarCarta(fileDescricao,Tipo_Carta.OO));
						System.out.println("Carta "
								+ cartas.getElemento(i).getNome()
								+ " Importada!");
					

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
		System.out.println("QTD CARTAS IMPORTADAS: "+ cartas.getQtdElementos());
		return cartas;
	}

}
