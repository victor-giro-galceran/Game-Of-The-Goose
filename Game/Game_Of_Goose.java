import java.util.Scanner;

public class Juego_Oca {

	public static void main(String[] args) {
				
		System.out.println(
				"\ndb       .d8b.        .d88b.   .o88b.  .d8b.  \r\n"
				+ "88      d8' `8b      .8P  Y8. d8P  Y8 d8' `8b \r\n"
				+ "88      88ooo88      88    88 8P      88ooo88 \r\n"
				+ "88      88~~~88      88    88 8b      88~~~88 \r\n"
				+ "88booo. 88   88      `8b  d8' Y8b  d8 88   88 \r\n"
				+ "Y88888P YP   YP       `Y88P'   `Y88P' YP   YP \r");

		Scanner scan = new Scanner(System.in);		
			
		System.out.print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n¿Cuántos jugadores hay? ");  
		int numeroDeJugadors = scan.nextInt();
		int i;
		int x;
		int z;
		System.out.print("______________________________________\n\n");

		
		while (numeroDeJugadors < 2 || numeroDeJugadors > 4) {
			System.out.print(":::::::::::::::::::::::::::::::::::::::\n¡error, número de jugadores incorrecto!\n:::::::::::::::::::::::::::::::::::::::\n");
			System.out.println("\nIntroduce un número de jugadores: ");
			numeroDeJugadors = scan.nextInt();
			System.out.println("______________________________________\n");
		}

		String nomDelsJugadors[] = new String [numeroDeJugadors], text = "Avanzas";

		//Posar nom als jugadors.
		for (i = 0; i < nomDelsJugadors.length; i++) {
			System.out.print("Introduce el nombre para el jugador [" + (i + 1) + "]: ");
			nomDelsJugadors[i] = scan.next();
		}

		// ------------------------------------------------------------ D E F I N I R - Y - I N I C I A L I Z A R - L A S - C A S I L L A S. ------------------------------------------------------------

		//Definir array de las casillas.
		int caselles [] = new int [63];

		//Definir valors per les caselles.
		for (i = 1; i < caselles.length; i++) {
			caselles[i] = i;
		}

		//Inicializar array casillas oca i definir posición 1 amb 5.
		int casellesOca [] = new int [14];
		casellesOca[0] = 5;

		//Definir valor a las casillas del array Oca.
		for (i = 1; i < casellesOca.length; i ++) {
			if (i % 2 == 0) {
				casellesOca[i] = (casellesOca[(i - 1)] + 5);;
			}
			else {
				casellesOca[i] = (casellesOca[(i - 1)] + 4);
			}
		}

		//Inicializar i definir casillas pozo.
		int casellesPou [] = new int [2];
		casellesPou [0] = 6;
		casellesPou[1] = 12;

		//Inicializar Daus.
		int dau1;
		int dau2; 
		
		//Inicializar número aleatorio entre el 1 y 2
		int random;
		
		//Inicializar i definir variable bucle i volver a tirar.
		boolean bucle = true, tornarTirar = false;

		//Inicializar i definir variable de contador de rondas.
		int numeroDeRonda = 1;

		//Inicialitzar acción.
		String accio;

		//Inicializar i definir posición del jugador.
		int posicioDelsJugadors[] = new int [numeroDeJugadors];
		for (i = 0; i < numeroDeJugadors; i++) {
			posicioDelsJugadors[i] = 0;
		}

		//Inicializar i definir valores para la variable castigo.
		int castic [] = new int [numeroDeJugadors];

		//Definir false a todos.
		for (i = 0; i < castic.length; i ++) {
			castic[i] = 0;
		}

		//Inicializar i definir variable esticalpou.
		boolean esticAlPou [] = new boolean [numeroDeJugadors];
		for (i = 0; i < esticAlPou.length; i++) {
			esticAlPou[i] = false;
		}

		//Inicialitzar i definir variable per saber si és la primera tirada.
		int contadorTirades [] = new int [numeroDeJugadors];
		for (i = 0; i < contadorTirades.length; i ++) {
			contadorTirades[i] = 0;
		}

		//I = 0.
		i = 0;

		//------------------------------------------------------------ I N I C I A R - J U E G O ------------------------------------------------------------
		while (bucle) {

			//Filtrar final del joc.
			if (i == 0) {
				x = (numeroDeJugadors - 1);
				if (posicioDelsJugadors[x] == 63 && tornarTirar != true) {
					break;
				}
			}
			else {
				x = i;
				x -= 1;
				if (posicioDelsJugadors[x] == 63 && tornarTirar != true) {
					break;
				}
			}

			//Filtrar les rondes que te sense tirar
			if (castic[i] > 0) {
				System.out.println("\n======================================\nTe quedan [" + (castic[i] - 1) + "] rondas sin tirar, {" + nomDelsJugadors[i] + "}.\n======================================\n");
					numeroDeRonda ++;
					castic[i] --;
			}
			else {
				//Registrar quina acció vol fer el jugador.
				System.out.print("______________________________________\n\nEs el turno del jugador [" + (i + 1) + "], {" + nomDelsJugadors[i] + "}\n>> ");
				accio = scan.next();

				//Upper case.
				accio = accio.toUpperCase();
				
				//Bucle no tirar.
				while (accio.equals("TIRO")==false) {
					System.out.print(":::::::::::::::::::::::::::::::::::::::\n¡error, comando no válido!\n:::::::::::::::::::::::::::::::::::::::\n>> ");
					accio = scan.next().toUpperCase();
				}

				//Tirar daus.
				dau1 = (int) (Math.random() * 6 + 1);
				dau2 = (int) (Math.random() * 6 + 1);

				//contador tirada de daus.
				contadorTirades[i] ++;

				//Imprimir valors daus i filtrar per la casella 60.
				if (posicioDelsJugadors[i] >= 60) {
					System.out.println("--------------------------------------\nHas obtenido un [" + dau1 + "].\n--------------------------------------\n");

					//Actualitzar posici�n del jugador.  
					posicioDelsJugadors[i] += (dau1);

					//Controlador para no estar a la caselela 64 o m�s.
					if (posicioDelsJugadors[i] > caselles.length) {
						posicioDelsJugadors[i] = caselles.length + (caselles.length - posicioDelsJugadors[i]);
						text = "Retrocedo";
					}
				}
				else {
					System.out.println("--------------------------------------\nHas obtenido un [" + dau1 + "] i [" + dau2 + "] = [" + (dau1 + dau2) + "].\n--------------------------------------\n");

					//Actualizar posici�n del jugador.
					posicioDelsJugadors[i] += (dau1 + dau2);

					//Controlador per no estar a la casella 64 o más.
					if (posicioDelsJugadors[i] > caselles.length) {
						posicioDelsJugadors[i] = caselles.length + (caselles.length - posicioDelsJugadors[i]);
					}
				}

				//------------------------------------------------------------ D A D O S ------------------------------------------------------------
				//Comprobar 1r tirada si dau1 = 3 i dau2 = 6 o dau1 = 4 i dau2 = 5.
				if (contadorTirades[i] == 1 && ((dau1 == 3  && dau2 == 6) || (dau1 == 4  && dau2 == 5) || (dau1 == 6  && dau2 == 3) || (dau1 == 5  && dau2 == 4))) {
					if ((dau1 == 3) || (dau1 == 6)) {
						posicioDelsJugadors[i] = 26;
						tornarTirar = true;
						
						dados();
						
						
					} else {
						posicioDelsJugadors[i] = 53;
						tornarTirar = true;
						
						dados();
						
					}
				}

				//------------------------------------------------------------ F I L T R A R - S I - L A - C A S I L L A - E S - E S P E C I A L ------------------------------------------------------------

				//------------------------------------------------------------------------------------ O C A ------------------------------------------------------------------------------------------------
				
				for (x = 0; x < (casellesOca.length - 1); x++) {
					if (posicioDelsJugadors[i] == casellesOca[x]) {
						if (posicioDelsJugadors[i] < casellesOca[13]) {
							
							ocas();							
							
							System.out.println("Casilla nº [" + posicioDelsJugadors[i] + "]: Oca. De oca en oca y tiro porque me toca.");
							posicioDelsJugadors[i] = casellesOca[(x + 1)];

							text = "Avanzas";
							tornarTirar = true;
							break;
						} else {
							
							ocas();							
							
							System.out.println("Casilla nº [" + posicioDelsJugadors[i] + "]: Oca. De oca en oca y tiro porque me toca.");
							posicioDelsJugadors[i] = casellesOca[13];
							tornarTirar = true;
							text = "Avanzas";
							break;
						}
					}

					//------------------------------------------------------------ P U E N T E ------------------------------------------------------------
					
					if (posicioDelsJugadors[i] == 6) {
						
						puentes();
												
						System.out.println("Casilla nº [" + posicioDelsJugadors[i] + "]: Puente. De puente a puente y tiro porque me lleva la corriente.");
						posicioDelsJugadors[i] = 12;

						text = "Avanzas";
						tornarTirar = true;
						break;
					}
					else if (posicioDelsJugadors[i] == 12) {
						
						puentes();					
						
						System.out.println("Casilla nº [" + posicioDelsJugadors[i] + "]: Puente. De puente a puente y tiro porque me lleva la corriente.");
						posicioDelsJugadors[i] = 6;
						text = "Retrocedes";

						tornarTirar = true;
						break;
					}

					//------------------------------------------------------------ P O S A D A ------------------------------------------------------------
					
					else if (posicioDelsJugadors[i] == 19) {
						castic[i] ++;
						
						posadas();
						
						System.out.println("Casilla nº [19]: Posada, [" + castic[i] + "] rondas sin tirar.");
						text = "Te estancas en";
						break;
					}

					//------------------------------------------------------------ P O Z O ------------------------------------------------------------
					else if (posicioDelsJugadors[i] == 31) {
						castic[i] = 2;
						
						pozo();
						
					
						System.out.println("Casilla nº [31]: Pozo, [" + castic[i] + "] rondas sin tirar a menos que un jugador también caiga.");
						esticAlPou[i] = true;

						for (z = 0; z < esticAlPou.length; z++) {
							if (esticAlPou[z] == true && i != z) {
								esticAlPou[z] = false;
								castic[z] = 0;
								
							}
						}
						text = "Te estancas en ";
						break;

					}

					//------------------------------------------------------------ L A B E R I N T O ------------------------------------------------------------
					else if (posicioDelsJugadors[i] == 42) {
						posicioDelsJugadors[i] = 39;
						text = "Retrocedes";
						
						laberintos();						
						
						System.out.println("Casilla nº [42]: Laberinto. Vuelves a la posición [" + posicioDelsJugadors[i] + "], {" + nomDelsJugadors[i] + "}.");
						break;
					}

					//------------------------------------------------------------ P R I S I Ó N ------------------------------------------------------------
					else if (posicioDelsJugadors[i] == 52) {
						castic[i] = 3;
						
						carcel();						
						
						System.out.println("Casilla nº [52]: Prisión, estás [" + castic[i] + "] turnos sin tirar, {" + nomDelsJugadors[i] + "}.");
						text = "Te estancas en";
						break;
					}

					//------------------------------------------------------------ L A - M U E R T E ------------------------------------------------------------
					else if (posicioDelsJugadors[i] == 58) {
						posicioDelsJugadors[i] = 0;
						text = "Retrocedes";
						
						muerte();
						
						System.out.println("Casilla nº [58]: La muerte, vuelves a la casilla [" + posicioDelsJugadors[i] + "], {" + nomDelsJugadors[i] + "}.");
						break;
					}
				}

				//Imprimir posición del juador.
				System.out.println(text + " a la casilla [" + posicioDelsJugadors[i] + "].");
				text = "Avanzas";
			}

			//Reset per acabar la ronda, incrementar i o i = 0.
			if (tornarTirar != true) {
				if (i == (numeroDeJugadors - 1)) {
					i = 0;
					numeroDeRonda ++;
				} else {
					i ++;
				}
			}
			
			tornarTirar = false;

		}

		for (i = 0; i < posicioDelsJugadors.length; i++) {
			if (posicioDelsJugadors[i] == caselles.length) {
				System.out.println("\n___________________________________");

				//------------------------------------------------------------ J A R D Í N - D E - L A - O C A ------------------------------------------------------------
				
				victoria();
				
				System.out.println("Casilla nº [63]: Jardín de la oca\n");
				System.out.println("A ganado el jugador [" + (i + 1) + "] a la ronda [" + numeroDeRonda + "] con [" + contadorTirades[i] + "] tiradas, {" + nomDelsJugadors[i] + "}.\n___________________________________");
			}
			
		}		
		
	}

	public void dados() {
		
		int aleatorio = generar_numero_aleatorio(0, 10);
		
		if (aleatorio < 5) {
			
			System.out.println(
					 
					  "       .-------.    ______\r\n"						
					+ "      /   o   /|   /\\     \\\r\n"				
					+ "     /_______/o|  /o \\  o  \\\r\n"					
					+ "     | o     | | /   o\\_____\\\r\n"						
					+ "     |   o   |o/ \\o   /o    /\r\n"						
					+ "     |     o |/   \\ o/  o  /\r\n"					
					+ "     '-------'     \\/____o/\n");	
			
		} else {
			
			System.out.println(""
					
				+ "               (( _______\r\n"
				+ "     _______     /\\O    O\\\r\n"
				+ "    /O     /\\   /  \\      \\\r\n"
				+ "   /   O  /O \\ / O  \\O____O\\ ))\r\n"
				+ "((/_____O/    \\\\    /O     /\r\n"
				+ "  \\O    O\\    / \\  /   O  /\r\n"
				+ "   \\O    O\\ O/   \\/_____O/\r\n"
				+ "    \\O____O\\/ ))          ))\r\n"
				+ "  ((");
			
		}
		
	}
	
	public void ocas() {
		
		int aleatorio = generar_numero_aleatorio(0, 5);
		
		if (aleatorio == 0) {
		
			System.out.println(""
					+ "	  _      _      _\r\n"
					+ "	>(.)__ <(.)__ =(.)__\r\n"
					+ "	 (___/  (___/  (___/  \n");
			
		} else if (aleatorio == 1) {
		
			System.out.println(""
					+ "                 ,-.\r\n"
					+ "         ,      ( {o\\\r\n"
					+ "         {`\"=,___) (`~\r\n"
					+ "          \\  ,_.-   )\r\n"
					+ "  ~^~^~^`- ~^ ~^ '~^~^~^~\n");
		} else if (aleatorio == 2) {
		
			System.out.println(""
					+ "                                    _\r\n"
					+ "                                ,-\"\" \"\".\r\n"
					+ "                              ,'  ____  `.\r\n"
					+ "                            ,'  ,'    `.  `._\r\n"
					+ "   (`.         _..--.._   ,'  ,'        \\    \\\r\n"
					+ "  (`-.\\    .-\"\"        \"\"'   /          (  d _b\r\n"
					+ " (`._  `-\"\" ,._             (            `-(   \\\r\n"
					+ " <_  `     (  <`<            \\              `-._\\\r\n"
					+ "  <`-       (__< <           :\r\n"
					+ "   (__        (_<_<          ;\r\n"
					+ "    `--------------------------\n");
		} else if (aleatorio == 3) {
		
			System.out.println(""

					+ "                                   ___\r\n"						
					+ "                               ,-\"\"   `.\r\n"
					+ "                             ,'  _   e )`-._\r\n"
					+ "                            /  ,' `-._<.===-'\r\n"
					+ "                           /  /\r\n"
					+ "                          /  ;\r\n"
					+ "              _          /   ;\r\n"
					+ " (`._    _.-\"\" \"\"--..__,'    |\r\n"
					+ " <_  `-\"\"                     \\\r\n"
					+ "  <`-                          :\r\n"
					+ "   (__   <__.                  ;\r\n"
					+ "     `-.   '-.__.      _.'    /\r\n"
					+ "        \\      `-.__,-'    _,'\r\n"
					+ "         `._    ,    /__,-'\r\n"
					+ "            \"\"._\\__,'< <____\r\n"
					+ "                 | |  `----.`.\r\n"
					+ "                 | |        \\ `.\r\n"
					+ "                 ; |___      \\-``\r\n"
					+ "                 \\   --<\r\n"
					+ "                  `.`.<\r\n"
					+ "                    `-'\r\n"
					+ "");	
		} else if (aleatorio == 4) {
		
			System.out.println(""
					+ "      ,;MMMM..\r\n"
					+ "   ,;:MM\"MMMMM.\r\n"
					+ ",;.MM::M.MMMMMM:\r\n"
					+ "\"\"::.;'MMMMMMMMM\r\n"
					+ "       \"'\"\"MMMMM;\r\n"
					+ "           ':MMMM.\r\n"
					+ "            'MMMM;\r\n"
					+ "             :MMMM;.\r\n"
					+ "              MMMMMM;...\r\n"
					+ "              MMMMMMMMMMMMM;.;..\r\n"
					+ "              MMMMMMMMMMMMMMMMMMM...\r\n"
					+ "              MMMMMM:MMMMMMMMMMMMMMM;...       ..:\r\n"
					+ "              MMMMMM;MMMMMMMMMMMMM:MMMMMMM:MMMM:M\r\n"
					+ "              :MMMMMM:MMMMMMMMMMMMMMM.:::;:::;;:'\r\n"
					+ "              ':MMMMMMM:MMMM:;MM:M;.MMM:';::M:'\r\n"
					+ "               ':MMMMMM;M;;MM;::M;;::::;MM:\"\"\r\n"
					+ "                 'MMMMMMMM;M;:::MMMMMMMMMM\"\r\n"
					+ "                  ''MMMMMMMMMMMMMMMMMMMMM\"\r\n"
					+ "                     ':MMMMMMMMMMMMMMMM\"'\r\n"
					+ "                       '':MMMMMMMMMMM\"'\r\n"
					+ "                          ':MMMMMM\"\"'\r\n"
					+ "                             .\r\n"
					+ "                             :\r\n"
					+ "                            ::\r\n"
					+ "                       ,..;.M'\r\n"
					+ "                      ,;;MM:'\r\n"
					+ "                        '\"'");
		} else {
				
			System.out.println(""
					+ "                                                        _...--.\r\n"
					+ "                                        _____......----'     .'\r\n"
					+ "                                  _..-''                   .'\r\n"
					+ "                                .'                       ./\r\n"
					+ "                        _.--._.'                       .' |\r\n"
					+ "                     .-'                           .-.'  /\r\n"
					+ "                   .'   _.-.                     .  \\   '\r\n"
					+ "                 .'  .'   .'    _    .-.        / `./  :\r\n"
					+ "               .'  .'   .'  .--' `.  |  \\  |`. |     .'\r\n"
					+ "            _.'  .'   .' `.'       `-'   \\ / |.'   .'\r\n"
					+ "         _.'  .-'   .'     `-.            `      .'\r\n"
					+ "       .'   .'    .'          `-.._ _ _ _ .-.    :\r\n"
					+ "      /    /o _.-'               .--'   .'   \\   |\r\n"
					+ "    .'-.__..-'                  /..    .`    / .'\r\n"
					+ "  .'   . '                       /.'/.'     /  |\r\n"
					+ " `---'                                   _.'   '\r\n"
					+ "                                       /.'    .'\r\n"
					+ "                                        /.'/.'\r\n"
					+ "");
		}
	
	}
	
	public void puentes() {
		
		int aleatorio = generar_numero_aleatorio(0, 2);
		
		if (aleatorio == 0) {
						
			System.out.println("                           #########                      \r\n"
				+ "                        ######                            \r\n"
				+ "                   ___###___________                      \r\n"
				+ "            _,--\"\"\"_________________\"\"\"--._               \r\n"
				+ "          /',--'\\\\\"##           __  \"//'--.'\\   ,  /\\    _\r\n"
				+ "-._  /\\  //'   ##\\\\       ,-_,-'  \\_///\\,-'`\\\\-' \\/  \\,-' \r\n"
				+ "   \\/  \\//    _  _\\\\ ,-._/         //'       \\\\           \r\n"
				+ "       //'--._M_|H|\\\\___   ___   _//   ___   _\\\\   ___    \r\n"
				+ "      | |    (|   | \\\\  | |   | |// | |   | | | | |   |   \r\n"
				+ "   ___| |   /ooo=oo-o\\\\oo-oo=oo-//=oo-oo=oo-oo| |=oo=oo___\r\n"
				+ "        \\\"'\"'\"'\"'\"'\"'\"'\"'\"'\"'\"'\"'\"'\"'\"'\"'\"'\"'\"|           \r\n"
				+ "         |    \\                           .   /           \r\n"
				+ "   .      \\   /\\            .  -     '    .  /            \r\n"
				+ "           `-.  '-..     ' '          ,  ,,-'     `       \r\n"
				+ "              \\     '- '            ,/,-'/                \r\n"
				+ "    \\          \\_- ' '             --',-'                 \r\n"
				+ "              -'                    \\/            |       \r\n"
				+ "     |  _  -                          - _        .       \r\n"
				+ "                                                /     ");
				
		} else if (aleatorio == 1) {
		
			System.out.println(""
				+ "                             ___....___\r\n"
				+ "   ^^                __..-:'':__:..:__:'':-..__\r\n"
				+ "                 _.-:__:.-:'':  :  :  :'':-.:__:-._\r\n"
				+ "               .':.-:  :  :  :  :  :  :  :  :  :._:'.\r\n"
				+ "            _ :.':  :  :  :  :  :  :  :  :  :  :  :'.: _\r\n"
				+ "           [ ]:  :  :  :  :  :  :  :  :  :  :  :  :  :[ ]\r\n"
				+ "           [ ]:  :  :  :  :  :  :  :  :  :  :  :  :  :[ ]\r\n"
				+ "  :::::::::[ ]:__:__:__:__:__:__:__:__:__:__:__:__:__:[ ]:::::::::::\r\n"
				+ "  !!!!!!!!![ ]!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!![ ]!!!!!!!!!!!\r\n"
				+ "  ^^^^^^^^^[ ]^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^[ ]^^^^^^^^^^^\r\n"
				+ "           [ ]                                        [ ]\r\n"
				+ "           [ ]                                        [ ]\r\n"
				+ "           [ ]                                        [ ]\r\n"
				+ "   ~~^_~^~/   \\~^-~^~ _~^-~_^~-^~_^~~-^~_~^~-~_~-^~_^/   \\~^ ~~_ ^\n");
			
		} else {
		
			System.out.println(""
				+ "                                           ^^\r\n"
				+ "      ^^      ..                                       ..\r\n"
				+ "              []                                       []\r\n"
				+ "            .:[]:_          ^^                       ,:[]:.\r\n"
				+ "          .: :[]: :-.                             ,-: :[]: :.\r\n"
				+ "        .: : :[]: : :`._                       ,.': : :[]: : :.\r\n"
				+ "      .: : : :[]: : : : :-._               _,-: : : : :[]: : : :.\r\n"
				+ "  _..: : : : :[]: : : : : : :-._________.-: : : : : : :[]: : : : :-._\r\n"
				+ "  _:_:_:_:_:_:[]:_:_:_:_:_:_:_:_:_:_:_:_:_:_:_:_:_:_:_:[]:_:_:_:_:_:_\r\n"
				+ "  !!!!!!!!!!!![]!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!![]!!!!!!!!!!!!!\r\n"
				+ "  ^^^^^^^^^^^^[]^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^[]^^^^^^^^^^^^^\r\n"
				+ "              []                                       []\r\n"
				+ "              []                                       []\r\n"
				+ "              []                                       []\r\n"
				+ "   ~~^-~^_~^~/  \\~^-~^~_~^-~_^~-^~_^~~-^~_~^~-~_~-^~_^/  \\~^-~_~^-~~-\r\n"
				+ "  ~ _~~- ~^-^~-^~~- ^~_^-^~~_ -~^_ -~_-~~^- _~~_~-^_ ~^-^~~-_^-~ ~^\r\n"
				+ "     ~ ^- _~~_-  ~~ _ ~  ^~  - ~~^ _ -  ^~-  ~ _  ~~^  - ~_   - ~^_~\r\n"
				+ "       ~-  ^_  ~^ -  ^~ _ - ~^~ _   _~^~-  _ ~~^ - _ ~ - _ ~~^ -\r\n"
				+ "          ~^ -_ ~^^ -_ ~ _ - _ ~^~-  _~ -_   ~- _ ~^ _ -  ~ ^-\r\n"
				+ "              ~^~ - _ ^ - ~~~ _ - _ ~-^ ~ __- ~_ - ~  ~^_-\r\n"
				+ "                  ~ ~- ^~ -  ~^ -  ~ ^~ - ~~  ^~ - ~\n");
			
		}		
		
	}
	
	public void posadas() {
		
		int aleatorio = generar_numero_aleatorio(0, 2);
		
		if (aleatorio == 0) {
			
			System.out.println(
				  "                          \r\n"
				+ "                      _II_\r\n"
				+ "                      [__] \r\n"
				+ "    __________________|  |___\r\n"
				+ "   /^^^^^^,-.^^^^^^^^\\|__|^^^\\\r\n"
				+ "  /     ,',-.`.               \\\r\n"
				+ " /    ,','   `.`.     ,-\"\"\"-.  \\\r\n"
				+ "/___,','__   __`.`.__/_,\"T\"._\\__\\\r\n"
				+ " |='-'||/\\| |^^||`-`=|_|_|_|_|=|\r\n"
				+ " |= = ||)(| |__||= ==|_|_|_|_|=|\r\n"
				+ " |= ==|\"\"\"\" \"\"\"\"| = =____= =_==|\r\n"
				+ " |== =| __   __ |= =| [] | |^|=|\r\n"
				+ " |= ==||/\\| |==||== |   o|=|_| |\r\n"
				+ " |== =||)(| |  || = | == | == =|\r\n"
				+ " |= ==|\"\"\"\" \"\"\"\"|== |____|= = =| \r\n"
				+ " \"\"\"\"\"|_________|\"\"\"'====`\"\"\"\"\"\"\n");
		
		} else if (aleatorio == 1) {
			
			System.out.println(""
					+ "                           (   )\r\n"
					+ "                          (    )\r\n"
					+ "                           (    )\r\n"
					+ "                          (    )\r\n"
					+ "                            )  )\r\n"
					+ "                           (  (                  /\\\r\n"
					+ "                            (_)                 /  \\  /\\\r\n"
					+ "                    ________[_]________      /\\/    \\/  \\\r\n"
					+ "           /\\      /\\        ______    \\    /   /\\/\\  /\\/\\\r\n"
					+ "          /  \\    //_\\       \\    /\\    \\  /\\/\\/    \\/    \\\r\n"
					+ "   /\\    / /\\/\\  //___\\       \\__/  \\    \\/\r\n"
					+ "  /  \\  /\\/    \\//_____\\       \\ |[]|     \\\r\n"
					+ " /\\/\\/\\/       //_______\\       \\|__|      \\\r\n"
					+ "/      \\      /XXXXXXXXXX\\                  \\\r\n"
					+ "        \\    /_I_II  I__I_\\__________________\\\r\n"
					+ "               I_I|  I__I_____[]_|_[]_____I\r\n"
					+ "               I_II  I__I_____[]_|_[]_____I\r\n"
					+ "               I II__I  I     XXXXXXX     I\r\n"
					+ "            ~~~~~\"   \"~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		} else {
		
			System.out.println(""
					+ "    ) )        /\\\r\n"
					+ "   =====      /  \\\r\n"
					+ "  _|___|_____/ __ \\____________\r\n"
					+ " |::::::::::/ |  | \\:::::::::::|\r\n"
					+ " |:::::::::/  ====  \\::::::::::|\r\n"
					+ " |::::::::/__________\\:::::::::|\r\n"
					+ " |_________|  ____  |__________|\r\n"
					+ "  | ______ | / || \\ | _______ |\r\n"
					+ "  ||  |   || ====== ||   |   ||\r\n"
					+ "  ||--+---|| |    | ||---+---||\r\n"
					+ "  ||__|___|| |   o| ||___|___||\r\n"
					+ "  |========| |____| |=========|\r\n"
					+ " (^^-^^^^^-|________|-^^^--^^^)\r\n"
					+ " (,, , ,, ,/________\\,,,, ,, ,)\r\n"
					+ "','',,,,' /__________\\,,,',',;;\n");
		
		}	
		
	}
										
	public void pozo() {
		
		System.out.println(""
				+ "                 __\r\n"
				+ "               .'/\\'.\r\n"
				+ "             .'-/__\\-'.\r\n"
				+ "           .'--/____\\--'.\r\n"
				+ "         .'--./______\\.--'.\r\n"
				+ "       .'--../________\\..--'.\r\n"
				+ "     .'--.._/__________\\_..--'.\r\n"
				+ "   .'--..__/____________\\__..--'.\r\n"
				+ " .'--..___/______________\\___..--'.\r\n"
				+ "'========'================'========'\r\n"
				+ "      [_|__]            [_|__]\r\n"
				+ "     =[__|_]=====\"\"=====[__|_]==.\r\n"
				+ "      [_|__]     '|     [_|__]  |\r\n"
				+ "      [__|_]     |'     [__|_]  |\r\n"
				+ "      [_|__]  .--JL--.  [_|__]  '===O\r\n"
				+ "      [__|_]   \\====/   [__|_]\r\n"
				+ "      [_|__]_.-| .; |-._[_|__]\r\n"
				+ "      [__|_]'._ \\__/(_.'[__|_]\r\n"
				+ "      [.-._]            [_.-.]\r\n"
				+ "      [_.-.'--..____..--'.-._]\r\n"
				+ " (o)  [(_.'   .-.     .-.'._)\\ (o)\r\n"
				+ "(\\'/) [  .-. (_.'.-. (_.' .-.](\\'/)\r\n"
				+ "   ;: [ (_.'.-. (_.' .-. (_.'| ;:'\r\n"
				+ ";:    [ .-. '._) .-. '._).-. ]   ;:.\r\n"
				+ "      [(_.'  .-. '._) .-.'._)]\r\n"
				+ "  (o) /.-.  (_.'.-.  (_.' .-.];:(o)\r\n"
				+ " (\\'/)['._).-. (_.'   .-.(_.'] (\\'/)\r\n"
				+ "      [   (_.'.-.  .-.'._)   \\ ;:\r\n"
				+ ";:'   '-._    '._) '._)   _.-'\r\n"
				+ "          `---..____..---'   ;:`\r\n"
				+ "   ;:'      ;:'.:;     ;;\"\n");		
		
	}
	
	public void laberintos() {
		
		int aleatorio = generar_numero_aleatorio(0, 5);
		
		if (aleatorio == 0) {
		
			System.out.println(""
				+ "	.--.--.--.  .--.--.\r\n"
				+ "	|     |        |  |\r\n"
				+ "	:  :--:  :  :  :  :\r\n"
				+ "	|  |     |  |     |\r\n"
				+ "	:  :  :  :--:--:--:\r\n"
				+ "	|  |  |           |\r\n"
				+ "	:  :  :--:--:--:  :\r\n"
				+ "	|  |        |  |  |\r\n"
				+ "	:  :--:--:  :  :  :\r\n"
				+ "	|     |     |  |  |\r\n"
				+ "	:--:  :  :--:  :  :\r\n"
				+ "	|        |        |\r\n"
				+ "	:  :--:--:--:--:--:\n");
			
		} else {
		
			System.out.println(
				  "___________________________________  \r\n"
				+ "| _____ |   | ___ | ___ ___ | |   | |\r\n"
				+ "| |   | |_| |__ | |_| __|____ | | | |\r\n"
				+ "| | | |_________|__ |______ |___|_| |\r\n"
				+ "| |_|   | _______ |______ |   | ____|\r\n"
				+ "| ___ | |____ | |______ | |_| |____ |\r\n"
				+ "|___|_|____ | |   ___ | |________ | |\r\n"
				+ "|   ________| | |__ | |______ | | | |\r\n"
				+ "| | | ________| | __|____ | | | __| |\r\n"
				+ "|_| |__ |   | __|__ | ____| | |_| __|\r\n"
				+ "|   ____| | |____ | |__ |   |__ |__ |\r\n"
				+ "| |_______|_______|___|___|___|_____|  \n");
			
		}		
		
	}
				
	public void carcel() {
		
		int aleatorio = generar_numero_aleatorio(0, 5);
		
		if (aleatorio == 0) {
			
			System.out.println(""
				+ "                       _    __    __    __    __    _\r\n"
				+ "                      | |__|__|__|__|__|__|__|__|__|_|\r\n"
				+ " __    __    __       |_|___|___|___|___|___|___|___||       __    __    __\r\n"
				+ "|__|  |__|  |__|      |___|___|___|___|___|___|___|__|      |__|  |__|  |__|\r\n"
				+ "|__|__|__|__|__|       \\____________________________/       |__|__|__|__|__|\r\n"
				+ "|_|___|___|___||        |_|___|___|___|___|___|___||        |_|___|___|___||\r\n"
				+ "|___|___|___|__|        |___|___|___|___|___|___|__|        |___|___|___|__|\r\n"
				+ " \\_|__|__|___|/          \\________________________/          \\_|__|__|__|_/\r\n"
				+ "  \\__|____|__/            |___|___|___|___|___|__|            \\__|__|__|_/\r\n"
				+ "   |||_|_|_||             |_|___|___|___|___|__|_|             |_|_|_|_||\r\n"
				+ "   ||_|_|||_|__    __    _| _  __ |_ __  _ __  _ |_    __    __||_|_|_|_|\r\n"
				+ "   |_|_|_|_||__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|_|_|_|_||\r\n"
				+ "   ||_|||_|||___|___|___|___|___|___|___|___|___|___|___|___|__||_|_|_|_|\r\n"
				+ "   |_|_|_|_||_|___|___|___|___|___|___|___|___|___|___|___|___||_|_|_|_||\r\n"
				+ "   ||_|_|_|_|___|___|___|___|___|___|___|___|___|___|___|___|__||_|_|_|_|\r\n"
				+ "   |_|||_|_||_|___|___|___|___|___|___|___|___|___|___|___|___||_|_|_|_||\r\n"
				+ "   ||_|_|_|_|___|___|___|___|___|_/| | | \\__|___|___|___|___|__||_|_|_|_|\r\n"
				+ "   |_|_|_|_||_|___|___|___|___|__/ | | | |\\___|___|___|___|___||_|_|_|_||\r\n"
				+ "   ||_|_|_|||___|___|___|___|___|| | | | | |____|___|___|___|__||_|_|_|_|\r\n"
				+ "   |_|_|_|_||_|___|___|___|___|_|| | | | | |__|___|___|___|___||_|_|_|_||\r\n"
				+ "  /___|___|__\\__|___|___|___|___|| | | | | |____|___|___|___|_/_|___|__|_\\\r\n"
				+ " |_|_|_|_|_|_||___|___|___|___|_|| | | | | |__|___|___|___|__|_|__|__|__|_|\r\n"
				+ " ||_|_|_|_|_|_|_|___|___|___|___||_|_|_|_|_|____|___|___|____|___|__|__|__|\n");
		
		} else if () {
		
			System.out.println(""
				+ "                                  ]=I==II==I=[\r\n"
				+ "                                   \\\\__||__//                 ]=I==II==I=[\r\n"
				+ "              ]=I==II==I=[          |.. ` *|                   \\\\__||__//\r\n"
				+ "               \\\\__||__//           |. /\\ #|                    |-_ []#|\r\n"
				+ "                | []   |            |  ## *|                    |      |\r\n"
				+ "                |    ..|            | . , #|                  ]=I==II==I=[\r\n"
				+ "___   ____  ___ |   .. |         __ |..__.*| __                \\\\__||__//\r\n"
				+ "] I---I  I--I [ |..    |        |  ||_|  |_|| |                 |    _*|\r\n"
				+ "]_____________[ | .. []|         \\--\\-|-|--/-//                 |   _ #|\r\n"
				+ " \\_\\| |_| |/_/  |_   _ | _   _   _|      ' *|                   |`    *|\r\n"
				+ "  |  .     |'-'-` '-` '-` '-` '-` | []     #|-|--|-_-_-_-_ _ _ _|_'   #|\r\n"
				+ "  |     '  |=-=-=-=-=-=-=-=-=-=-=-|      []*|-----________` ` `   ]   *|\r\n"
				+ "  |  ` ` []|      _-_-_-_-_  '    |-       #|      ,    ' ```````['  _#|\r\n"
				+ "  | '  `  '|   [] | | | | |  []`  |  []    *|   `          . `   |'  I*|\r\n"
				+ "  |      - |    ` | | | | | `     | ;  '   #|     .  |        '  |    #|\r\n"
				+ " /_'_-_-___-\\__,__|_|_|_|_|_______|   `  , *|    _______+___,__,-/._.._.\\\r\n"
				+ "             _,--'    __,-'      /,_,_v_Y_,_v\\\\-' \n");
			
		} else {
		
			System.out.println(
				  "	____________________\r\n"
				+ "	_]|  |  |  |  |  |[_\r\n"
				+ "	_]|==|==|==|==|==|[_\r\n"
				+ "	_]|_ _  |  |  |  |[_\r\n"
				+ "	_]|_|_[ |  |  |  |[_\r\n"
				+ "	_]|_|_[ |  |  |  |[_\r\n"
				+ "	_]|  |  |  |  |  |[_\r\n"
				+ "	_]|  |  |  |  |  |[_\r\n"
				+ "	_]|==|==|==|==|==|[_\r\n"
				+ "	_]|  |  |.-|--|  |[_\r\n"
				+ "	_]|  |  | `.  |  |[_\r\n"
				+ "	_]|  |  |  |`.|  |[_\r\n"
				+ "	_]|  |  |  |  |`.|[_\r\n"
				+ "	_]|  |  |  |  |  |[_\r\n"
				+ "	_]|==|==|==|==|==|[_\r\n"
				+ "	_]|__|__|__|__|__|[_  \n");
			
		}	
		
	}
									
	public void muerte() {
				
		int aleatorio = generar_numero_aleatorio(0, 5);
		
		if (aleatorio == 0) {
			
			System.out.println(
				  "           _..--\"\"---.\r\n"
				+ "          /           \".\r\n"
				+ "          `            l\r\n"
				+ "          |'._  ,._ l/\"\\\r\n"
				+ "          |  _J<__/.v._/\r\n"
				+ "           \\( ,~._,,,,-)\r\n"
				+ "            `-\\' \\`,,j|\r\n"
				+ "               \\_,____J\r\n"
				+ "          .--.__)--(__.--.\r\n"
				+ "         /  `-----..--'. j\r\n"
				+ "         '.- '`--` `--' \\\\\r\n"
				+ "        //  '`---'`  `-' \\\\\r\n"
				+ "       //   '`----'`.-.-' \\\\\r\n"
				+ "     _//     `--- -'   \\' | \\________\r\n"
				+ "    |  |         ) (      `.__.---- -'\\\r\n"
				+ "     \\7          \\`-(               74\\\\\\\r\n"
				+ "     ||       _  /`-(               l|//7__\r\n"
				+ "     |l    ('  `-)-/_.--.          f''` -.-\"|\r\n"
				+ "     |\\     l\\_  `-'    .'         |     |  |\r\n"
				+ "     llJ   _ _)J--._.-('           |     |  l\r\n"
				+ "     |||( ( '_)_  .l   \". _    ..__I     |  L\r\n"
				+ "     ^\\\\\\||`'   \"   '\"-. \" )''`'---'     L.-'`-.._\r\n"
				+ "          \\ |           ) /.              ``'`-.._``-.\r\n"
				+ "          l l          / / |                      |''|\r\n"
				+ "           \" \\        / /   \"-..__                |  |\r\n"
				+ "           | |       / /          1       ,- t-...J_.'\r\n"
				+ "           | |      / /           |       |  |\r\n"
				+ "           J  \\  /\"  (            l       |  |\r\n"
				+ "           | ().'`-()/            |       |  |\r\n"
				+ "          _.-\"_.____/             l       l.-l\r\n"
				+ "      _.-\"_.+\"|                  /        \\.  \\\r\n"
				+ "/\"\\.-\"_.-\"  | |                 /          \\   \\\r\n"
				+ "\\_   \"      | |                1            | `'|\r\n"
				+ "  |ll       | |                |            i   |\r\n"
				+ "  \\\\\\       |-\\               \\j ..          L,,'. `/\r\n"
				+ " __\\\\\\     ( .-\\           .--'    ``--../..'      '-..\r\n"
				+ "   `'''`----`\\\\\\\\ .....--'''\r\n"
				+ "              \\\\\\\\                                   ''");
		
		} else if (aleatorio == 1) {
			
			System.out.println(""
					+ "                      :::!~!!!!!:.\r\n"
					+ "                  .xUHWH!! !!?M88WHX:.\r\n"
					+ "                .X*#M@$!!  !X!M$$$$$$WWx:.\r\n"
					+ "               :!!!!!!?H! :!$!$$$$$$$$$$8X:\r\n"
					+ "              !!~  ~:~!! :~!$!#$$$$$$$$$$8X:\r\n"
					+ "             :!~::!H!<   ~.U$X!?R$$$$$$$$MM!\r\n"
					+ "             ~!~!!!!~~ .:XW$$$U!!?$$$$$$RMM!\r\n"
					+ "               !:~~~ .:!M\"T#$$$$WX??#MRRMMM!\r\n"
					+ "               ~?WuxiW*`   `\"#$$$$8!!!!??!!!\r\n"
					+ "             :X- M$$$$       `\"T#$T~!8$WUXU~\r\n"
					+ "            :%`  ~#$$$m:        ~!~ ?$$$$$$\r\n"
					+ "          :!`.-   ~T$$$$8xx.  .xWW- ~\"\"##*\"\r\n"
					+ ".....   -~~:<` !    ~?T#$$@@W@*?$$      /`\r\n"
					+ "W$@@M!!! .!~~ !!     .:XUW$W!~ `\"~:    :\r\n"
					+ "#\"~~`.:x%`!!  !H:   !WM$$$$Ti.: .!WUn+!`\r\n"
					+ ":::~:!!`:X~ .: ?H.!u \"$$$B$$$!W:U!T$$M~\r\n"
					+ ".~~   :X@!.-~   ?@WTWo(\"*$$$W$TH$! `\r\n"
					+ "Wi.~!X$?!-~    : ?$$$B$Wu(\"**$RM!\r\n"
					+ "$R@i.~~ !     :   ~$$$$$B$$en:``\r\n"
					+ "?MXT@Wx.~    :     ~\"##*$$$$M~\n");
		
		} else if (aleatorio == 2) {
			
			System.out.println(""
					+ "                 uuuuuuu\r\n"
					+ "             uu$$$$$$$$$$$uu\r\n"
					+ "          uu$$$$$$$$$$$$$$$$$uu\r\n"
					+ "         u$$$$$$$$$$$$$$$$$$$$$u\r\n"
					+ "        u$$$$$$$$$$$$$$$$$$$$$$$u\r\n"
					+ "       u$$$$$$$$$$$$$$$$$$$$$$$$$u\r\n"
					+ "       u$$$$$$$$$$$$$$$$$$$$$$$$$u\r\n"
					+ "       u$$$$$$\"   \"$$$\"   \"$$$$$$u\r\n"
					+ "       \"$$$$\"      u$u       $$$$\"\r\n"
					+ "        $$$u       u$u       u$$$\r\n"
					+ "        $$$u      u$$$u      u$$$\r\n"
					+ "         \"$$$$uu$$$   $$$uu$$$$\"\r\n"
					+ "          \"$$$$$$$\"   \"$$$$$$$\"\r\n"
					+ "            u$$$$$$$u$$$$$$$u\r\n"
					+ "             u$\"$\"$\"$\"$\"$\"$u\r\n"
					+ "  uuu        $$u$ $ $ $ $u$$       uuu\r\n"
					+ " u$$$$        $$$$$u$u$u$$$       u$$$$\r\n"
					+ "  $$$$$uu      \"$$$$$$$$$\"     uu$$$$$$\r\n"
					+ "u$$$$$$$$$$$uu    \"\"\"\"\"    uuuu$$$$$$$$$$\r\n"
					+ "$$$$\"\"\"$$$$$$$$$$uuu   uu$$$$$$$$$\"\"\"$$$\"\r\n"
					+ " \"\"\"      \"\"$$$$$$$$$$$uu \"\"$\"\"\"\r\n"
					+ "           uuuu \"\"$$$$$$$$$$uuu\r\n"
					+ "  u$$$uuu$$$$$$$$$uu \"\"$$$$$$$$$$$uuu$$$\r\n"
					+ "  $$$$$$$$$$\"\"\"\"           \"\"$$$$$$$$$$$\"\r\n"
					+ "   \"$$$$$\"                      \"\"$$$$\"\"\r\n"
					+ "     $$$\"                         $$$$\"\n");
		
		} else {
			
			System.out.println(""
					+ "                       uuuuuuuuuuuuuuuuuuuuu.\r\n"
					+ "                   .u$$$$$$$$$$$$$$$$$$$$$$$$$$W.\r\n"
					+ "                 u$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$Wu.\r\n"
					+ "               $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$i\r\n"
					+ "              $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\r\n"
					+ "         `    $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\r\n"
					+ "           .i$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$i\r\n"
					+ "           $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$W\r\n"
					+ "          .$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$W\r\n"
					+ "         .$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$i\r\n"
					+ "         #$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$.\r\n"
					+ "         W$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\r\n"
					+ "$u       #$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$~\r\n"
					+ "$#      `\"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\r\n"
					+ "$i        $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\r\n"
					+ "$$        #$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\r\n"
					+ "$$         $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\r\n"
					+ "#$.        $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$#\r\n"
					+ " $$      $iW$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$!\r\n"
					+ " $$i      $$$$$$$#\"\" `\"\"\"#$$$$$$$$$$$$$$$$$#\"\"\"\"\"\"#$$$$$$$$$$$$$$$W\r\n"
					+ " #$$W    `$$$#\"            \"       !$$$$$`           `\"#$$$$$$$$$$#\r\n"
					+ "  $$$     ``                 ! !iuW$$$$$                 #$$$$$$$#\r\n"
					+ "  #$$    $u                  $   $$$$$$$                  $$$$$$$~\r\n"
					+ "   \"#    #$$i.               #   $$$$$$$.                 `$$$$$$\r\n"
					+ "          $$$$$i.                \"\"\"#$$$$i.               .$$$$#\r\n"
					+ "          $$$$$$$$!         .   `    $$$$$$$$$i           $$$$$\r\n"
					+ "          `$$$$$  $iWW   .uW`        #$$$$$$$$$W.       .$$$$$$#\r\n"
					+ "            \"#$$$$$$$$$$$$#`          $$$$$$$$$$$iWiuuuW$$$$$$$$W\r\n"
					+ "               !#\"\"    \"\"             `$$$$$$$##$$$$$$$$$$$$$$$$\r\n"
					+ "          i$$$$    .                   !$$$$$$ .$$$$$$$$$$$$$$$#\r\n"
					+ "         $$$$$$$$$$`                    $$$$$$$$$Wi$$$$$$#\"#$$`\r\n"
					+ "         #$$$$$$$$$W.                   $$$$$$$$$$$#   ``\r\n"
					+ "          `$$$$##$$$$!       i$u.  $. .i$$$$$$$$$#\"\"\r\n"
					+ "             \"     `#W       $$$$$$$$$$$$$$$$$$$`      u$#\r\n"
					+ "                            W$$$$$$$$$$$$$$$$$$      $$$$W\r\n"
					+ "                            $$`!$$$##$$$$``$$$$      $$$$!\r\n"
					+ "                           i$\" $$$$  $$#\"`  \"\"\"     W$$$$\r\n"
					+ "                                                   W$$$$!\r\n"
					+ "                      uW$$  uu  uu.  $$$  $$$Wu#   $$$$$$\r\n"
					+ "                     ~$$$$iu$$iu$$$uW$$! $$$$$$i .W$$$$$$\r\n"
					+ "             ..  !   \"#$$$$$$$$$$##$$$$$$$$$$$$$$$$$$$$#\"\r\n"
					+ "             $$W  $     \"#$$$$$$$iW$$$$$$$$$$$$$$$$$$$$$W\r\n"
					+ "             $#`   `       \"\"#$$$$$$$$$$$$$$$$$$$$$$$$$$$\r\n"
					+ "                              !$$$$$$$$$$$$$$$$$$$$$#`\r\n"
					+ "                              $$$$$$$$$$$$$$$$$$$$$$!\r\n"
					+ "                            $$$$$$$$$$$$$$$$$$$$$$$`\r\n"
					+ "                             $$$$$$$$$$$$$$$$$$$$\"\n");
		
		}		
		
	}
												
	public void victoria() {
		
		System.out.println(""
				+ "             ___     ___\r\n"
				+ "      ,_    / _,\\   /,_ \\    _,\r\n"
				+ "      | \\   \\( \\|   |/ )/   / |\r\n"
				+ "      |  \\_  \\\\       //  _/  |\r\n"
				+ "      (_   \\_) \\     / ( /   _)\r\n"
				+ "      (\\_   `   \\   /   `   _/)\r\n"
				+ "       ,\\   -=~  /   \\  ~=-   /,\r\n"
				+ "    ~^~^~^~^~^~^~^~^~^~^~^~^~^~^~\n");
		
	}					
	
	
	// ------------------------------------ A L E A T O R Y ------------------------------------ //
		
	public int generar_numero_aleatorio(int minimo, int maximo) {
			
		return (int) Math.floor(Math.random() * (minimo - (maximo + 1)) + (maximo + 1));
		
	}
	
}
