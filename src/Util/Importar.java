package Util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Carta;
import entity.CartaParameters;
import entity.Carta_Criatura;
import entity.Carta_ED;
import entity.Carta_Magica;
import entity.Carta_OO;
import entity.Tipo_Carta;

public class Importar {

	//Files url's
	public static final File FILE = new File("./Cartas/All Descriptions URL's.txt");
	public static final File BACKGROUND_FILE = new File("./Background/All Background URL's1.txt");
	
	private static BufferedImage backgrounds[];
	private static Lista_de_Generics<Carta> cartas;

	// IMAGENS URL EM CADA UM DOS TXT's,
	// Métodos específicos para cada tipo de carta,
	// Consertar número de linhas a serem lidas
	// Consertar a descrição de TODAS as Cartas

	public static Carta importarCarta(File f, Tipo_Carta tipo) {

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

	public static Carta especial_magica(File f, Tipo_Carta tipo) {
		FileReader arquivo = null;
		BufferedReader leituraArquivo = null;

		try {
			arquivo = new FileReader(f);
			leituraArquivo = new BufferedReader(arquivo);

		} catch (FileNotFoundException e) {
			System.err.println("Arquivos não encontrados");
			e.printStackTrace();
		}

		String nome = null;
		String descrição = null;
		String imagemUrl = null;
		BufferedImage imagem = null;

		try {
			nome = leituraArquivo.readLine();
			descrição = leituraArquivo.readLine();
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
		CartaParameters cp;
		Carta a = null;
		
		switch (tipo) {
		case MAGICA:
			cp = new CartaParameters(Tipo_Carta.MAGICA);
			cp.descricao = descrição;
			cp.nome = nome;
			cp.imagem = imagem;
			a = new Carta_Magica(cp);
			break;
		case ED:
			cp = new CartaParameters(Tipo_Carta.ED);
			cp.descricao = descrição;
			cp.nome = nome;
			cp.imagem = imagem;
			a = new Carta_ED(cp);
			break;
		case OO:
			cp = new CartaParameters(Tipo_Carta.OO);
			cp.descricao = descrição;
			cp.nome = nome;
			cp.imagem = imagem;
			a = new Carta_OO(cp);
		}
		try {
			leituraArquivo.close();
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return a;
	}

	private static Carta_Criatura criatura(File f) {

		FileReader arquivo = null;
		BufferedReader leituraArquivo = null;

		try {
			arquivo = new FileReader(f);
			leituraArquivo = new BufferedReader(arquivo);

		} catch (FileNotFoundException e) {
			System.err.println("Arquivos não encontrados");
			e.printStackTrace();
			// return false;
		}
		String nome = null;
		String descrição = null;
		int ataque = 0, defesa = 0, skill = 0;
		String imagemUrl = null;
		BufferedImage imagem = null;
		BufferedImage defImage = null;
		try {
			nome = leituraArquivo.readLine();
			descrição = leituraArquivo.readLine();
			ataque = Integer.parseInt(leituraArquivo.readLine());
			defesa = Integer.parseInt(leituraArquivo.readLine());
			skill = Integer.parseInt(leituraArquivo.readLine());
			imagemUrl = leituraArquivo.readLine();
			// debug avançado
			System.out.printf("%s, %s, %d, %d, %d, %s\n", nome, descrição,
					ataque, defesa, skill, imagemUrl);
			//
			imagem = ImageIO.read(new File(imagemUrl));
			imagemUrl = leituraArquivo.readLine();
			defImage = ImageIO.read(new File(imagemUrl));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				leituraArquivo.close();
				arquivo.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			// return false;
		}
		CartaParameters cp = new CartaParameters(Tipo_Carta.CRIATURA);
		cp.nome = nome;
		cp.descricao = descrição;
		cp.imagem = imagem;
		Carta_Criatura a = new Carta_Criatura(ataque, defesa, skill, defImage, cp);
		// debug avançado
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

	public static Lista_de_Generics<Carta> importAllCards(File allDescriptions) {
		if(cartas == null){
			cartas = new Lista_de_Generics<Carta>(21);
	
			FileReader arquivo = null;
			BufferedReader leituraArquivo = null;
	
			try {
				arquivo = new FileReader(allDescriptions);
				leituraArquivo = new BufferedReader(arquivo);
	
			} catch (FileNotFoundException e) {
				System.err.println("Arquivos não encontrados");
				e.printStackTrace();
			}
	
			try {
				for (int i = 0; i < cartas.length(); i++) {
	
					String urlDescricao = leituraArquivo.readLine();
					File fileDescricao = new File(urlDescricao);
	
					String tipo[] = urlDescricao.split("/");
					// debug avançado
					System.out.println("tipo[2]: " + tipo[2] + i);
					//
					if (tipo[2].equalsIgnoreCase("Criaturas")) {
						System.out.println("Criatura");
						cartas.add(i,
								importarCarta(fileDescricao, Tipo_Carta.CRIATURA));
						System.out.println("Carta "
								+ cartas.getElemento(i).getNome() + " Importada!");
	
					} else if (tipo[2].equalsIgnoreCase("Magias")) {
						System.out.println("Magia");
						cartas.add(i,
								importarCarta(fileDescricao, Tipo_Carta.MAGICA));
						System.out.println("Carta "
								+ cartas.getElemento(i).getNome() + " Importada!");
	
					} else if (tipo[2].equalsIgnoreCase("ED")) {
						System.out.println("ED");
						cartas.add(i, importarCarta(fileDescricao, Tipo_Carta.ED));
						System.out.println("Carta "
								+ cartas.getElemento(i).getNome() + " Importada!");
	
					} else {
						System.out.println("OO");
						cartas.add(i, importarCarta(fileDescricao, Tipo_Carta.OO));
						System.out.println("Carta "
								+ cartas.getElemento(i).getNome() + " Importada!");
	
					}
					System.out.println();
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
			System.out
					.println("QTD CARTAS IMPORTADAS: " + cartas.getQtdElementos());
			System.out.println();
		}
		return cartas;
	}

	
	
	public static void importarBackground(File f) {
		FileReader arquivo = null;
		BufferedReader leituraArquivo = null;

		try {
			arquivo = new FileReader(f);
			leituraArquivo = new BufferedReader(arquivo);

		} catch (FileNotFoundException e) {
			System.err.println("Arquivos não encontrados");
			e.printStackTrace();
		}
		

		String imagemUrl = null;

		try {

			imagemUrl = leituraArquivo.readLine();
			if (imagemUrl.equalsIgnoreCase("Backgrounds")) {
				
				int n = Integer.parseInt(leituraArquivo.readLine());
				backgrounds = new BufferedImage[n];
				
				for (int i = 0; i < backgrounds.length; i++) {
					imagemUrl = leituraArquivo.readLine();
					backgrounds[i] = ImageIO.read(new File(imagemUrl));
					System.out.println(imagemUrl);
				}

			}

		} catch (IOException e) {
			e.printStackTrace();

			try {
				leituraArquivo.close();
				arquivo.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}

		}

		try {
			leituraArquivo.close();
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static BufferedImage getBackground(BackgroundID bg) {
		if(backgrounds == null){
			Importar.importarBackground(Importar.BACKGROUND_FILE);
		}

		return backgrounds[bg.getindex()];
	}

}
