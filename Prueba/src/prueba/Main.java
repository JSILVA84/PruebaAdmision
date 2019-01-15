package prueba;

public class Main {

	public static void main(String[] args) {
		String[] rta = textoParrafo("Anita lava la tina con agua y jabòn", 10);
		for (String temp : rta) {
			System.out.println(temp);
		}
	}

	public static String[] textoParrafo(String texto, int maxLongitudLinea) {
		char[] temp = texto.toCharArray();
		String[] rta = new String[temp.length / maxLongitudLinea];

		int i = 0;
		int j = 0;
		int k = 1;
		while (i < temp.length) {
			if (rta[j] == null) {
				rta[j] = "";
			}
			rta[j] += temp[i];
			if (k >= maxLongitudLinea) {
				if (temp[i] == ' ') {
					j++;
					k = 0;
				}
			}
			i++;
			k++;
		}
		return rta;
	}

}
