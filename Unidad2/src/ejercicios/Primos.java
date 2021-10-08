package ejercicios;

import java.util.ArrayList;

public class Primos {
	
	static ArrayList<Integer> lista = new ArrayList<Integer>();

	public static void main(String[] args) {
		
		if(lista.size() == 0) {
			lista.add(2);
		}
		
		System.out.println(calcular(60000));
		
	}
	
	public static int calcular(int pos) {
		if(lista.size() > pos) {
			return lista.get(pos - 1);
			
		} else {
			int numero = lista.get(lista.size()-1);
			int contador = lista.size();
			boolean numeroPrimo = false;
			
			while(contador != pos) {
				numeroPrimo = false;
				while(!numeroPrimo) {
					numero++;
					numeroPrimo = esPrimo(numero);
					if(numeroPrimo) {
						contador++;
						lista.add(numero);
					}
				}
			}
			return numero;
		}
	}
	
	public static boolean esPrimo(int numero) {
		boolean primo = true;
		for(int i = 2; i <= numero/2; i++) {
			if(numero%i == 0) {
				primo = false;
				break;
			}
		}
		return primo;
	}

}
