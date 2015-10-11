package Util;
import java.util.Random;
import java.util.Vector;
/**
 * <h1>Generic List</h1>
 * receives a generic Object type and makes a list of it
 * with customized methods for a deck organization, but 
 * also have additional lists methods
 * <p>
 * 
 * 
 * @author Daniel Aragão
 * @version 2.6
 * @since 2015-09-14
 * @param <T>
 */
public class Lista_de_Generics<T> {
	private Vector<T> lista;
	private int qtdElementos;
	private int capacity;

	public Lista_de_Generics(int n) {
		lista = new Vector<T>();
		this.capacity = n;
		lista.setSize(capacity);
	}

	public int getQtdElementos() {
		return this.qtdElementos;
	}

	public void setQtdElementos(int qtdElementos) {
		this.qtdElementos = qtdElementos;
	}

	public boolean isEmpty() {
		if (getQtdElementos() == 0)
			return true;
		return false;
	}

	public boolean isFull() {
		if (getQtdElementos() >= this.capacity)
			return true;
		return false;
	}

	public void addInicio(T e) throws RuntimeException {
		if (isFull()) {
			throw new RuntimeException("is Full");
		} else {
			for (int i = getQtdElementos(); i > 0; i--) {
				lista.set(i, lista.get(i - 1));
			}
			lista.set(0, e);
			setQtdElementos(getQtdElementos() + 1);
		}
	}

	public void addFim(T e) throws RuntimeException {
		if (isFull()) {
			throw new RuntimeException("is Full");
		} else {
			lista.add(getQtdElementos(), e);
			setQtdElementos(getQtdElementos() + 1);
		}
	}

	public void add(int i, T e) throws RuntimeException {
		if (isFull()) {
			throw new RuntimeException();
		} else {
			if (i <= 0) {
				addInicio(e);
			} else if (i >= getQtdElementos()) {
				addFim(e);
			} else {
				for (int p = getQtdElementos(); p > i; p--) {
					lista.set(p, lista.get(p - 1));
				}
				lista.set(i, e);
				setQtdElementos(getQtdElementos() + 1);
			}
		}
	}

	public void setElemento(int n, T e){
		lista.set(n, e);
	}

	public T removerFim() throws RuntimeException {
		if (isEmpty()) {
			throw new RuntimeException("is Empty");
		} else {
			setQtdElementos(getQtdElementos() - 1);
			return lista.get(getQtdElementos());
		}
	}

	public T removerInicio() throws RuntimeException {
		if (isEmpty()) {
			throw new RuntimeException("is Empty");
		} else {
			T first = lista.get(0);
			for (int i = 0; i < getQtdElementos(); i++) {
				lista.set(i, lista.get(i + 1));
			}
			setQtdElementos(getQtdElementos() - 1);
			return first;
		}

	}

	public T remover(int index) throws RuntimeException {
		if (isEmpty()) {
			throw new RuntimeException("is Empty");
		} else {
			if (index <= 0) {
				return removerInicio();
			} else if (index >= qtdElementos) {
				return removerFim();
			} else {
				T first = lista.get(index);
				for (int i = index; i < getQtdElementos(); i++) {
					lista.set(i, lista.get(i + 1));
				}
				setQtdElementos(getQtdElementos() - 1);
				return first;
			}
		}

	}

	public void embaralhar() throws RuntimeException {
		if (isEmpty()) {
			throw new RuntimeException("is Empty");
		} else {
			Random rand = new Random();
			for (int i = 0; i < getQtdElementos(); i++) {
				int posNova = rand.nextInt(getQtdElementos());
				T aux = lista.get(i);
				lista.set(i, lista.get(posNova));
				lista.set(posNova, aux);
			}
		}

	}

	public String toString() throws RuntimeException {
		if (isEmpty()) {
			throw new RuntimeException();
		} else {
			String tot = "" + lista.get(0);
			for (int i = 1; i < getQtdElementos(); i++) {
				tot = tot + "  " + lista.get(i);
			}
			return tot;
		}
	}

	public T getElemento(int n){return lista.get(n);}
	
	
	public T getElementoRandom(){
		Random rand = new Random();
		int n = rand.nextInt(getQtdElementos());
		return lista.get(n);
	}
	
	public int length(){return this.capacity;}
	
	public Lista_de_Generics<T> getList(){
		Lista_de_Generics<T> a = new Lista_de_Generics(getQtdElementos());
		
		for(int i = 0; i < a.qtdElementos;i++){
			a.add(i, lista.get(i));
		}
		
		return a;
		
	}
	
	public void fill(){
		Random rand = new Random();
		int qtdInicial = this.qtdElementos;
		
		while(!isFull()){
			int n = rand.nextInt(qtdInicial);
			this.addFim(getElemento(n));
		}		
	}
	
	public Lista_de_Generics<T> copy(){
		Lista_de_Generics<T> copy  = new Lista_de_Generics<T>(capacity);
		
		for(int i = 0; i < qtdElementos; i++){
			copy.addFim(getElemento(i));
		}
		copy.setQtdElementos(getQtdElementos());
		
		
		return copy;
	}
	
}






















