package utils;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * 
 * Permite leer carácteres, cadenas y numeros por teclado.
 * @author Fco Javier Florín Cárdenas

 */
public class Teclado {
	
	
	public static enum Comparacion {
		MAYORIGUAL, MENORIGUAL, MAYOR, MENOR
	}
	
	public static enum Incluido {
		TODOS, NINGUNO, MINIMO, MAXIMO
	}
	public static enum Tipo {
		BYTE, SHORT, INT, LONG, FLOAT, DOUBLE
	}
	
//DECLARACIONES-------------------------------------------------------------------------------------------------------------------------------
	public static Scanner teclado=new Scanner (System.in);
	public static final String ERROR_NUM_IMPUT="El número introducido no es correcto. Vuelva a Introducirlo.";
		
//FUNCIÓN CERRAR TECLADO----------------------------------------------------------------------------------------------------------------------
	public static void cerrarTeclado (){
		teclado.close();
	}

//LEER CARACTERES-----------------------------------------------------------------------------------------------------------------
	public static char leerChar (){
		char caracter;
		caracter=teclado.nextLine().charAt(0);
		return caracter;
	}
	
//LEER CADENAS-----------------------------------------------------------------------------------------------------------------	
	public static String leerString (){
		String cadena;
		cadena=teclado.nextLine();
		return cadena;
	}

//LEER BOOLEAN-----------------------------------------------------------------------------------------------------------------
	public static boolean leerBoolean(String pregunta, String opciontrue, String opcionfalse){
		byte res;
		boolean resultado=true;
		do{
			System.out.printf("%s:\n\t1.- %s\n\t2.- %s\n",pregunta,opciontrue,opcionfalse);
			res=leerNumero(Tipo.BYTE);
		}while(res!=1&&res!=2);
		resultado=(res==1)?true:false;
		return resultado;
	}

//LEER NÚMEROS-----------------------------------------------------------------------------------------------------------------		
	@SuppressWarnings("unchecked")
	public static <N> N leerNumero(Tipo tipos){
		N numero=null;
		boolean valorValido=true;
		
		do{
			try {
				switch (tipos) {
				case BYTE:
					numero=(N) Byte.valueOf(teclado.nextByte());
					valorValido=true;
					break;
				case SHORT:
					numero=(N) Short.valueOf(teclado.nextShort());
					valorValido=true;
					break;
				case INT:
					numero=(N) Integer.valueOf(teclado.nextInt());
					valorValido=true;
					break;
				case LONG:
					numero=(N) Long.valueOf(teclado.nextLong());
					valorValido=true;
					break;
				case FLOAT:
					numero=(N) Float.valueOf(teclado.nextFloat());
					valorValido=true;
					break;
				case DOUBLE:
					numero=(N) Double.valueOf(teclado.nextDouble());
					valorValido=true;
					break;
				}
			} catch (InputMismatchException e){
				System.out.println(ERROR_NUM_IMPUT);
				valorValido=false;
			} finally{
				teclado.nextLine(); //Se limpia el buffer.
			}		
		}while(!valorValido);
		return numero;
	}
	

//PEDIR NÚMERO COMPARANDOLO CON OTRO------------------------------------------------------------------------------------------------
	public static <N> N leerComparacion(Comparacion comparacion, Tipo tipos, N comparado){
		N numero=null;
		boolean valorValido=true;
		do{
			try {	
				switch (comparacion) {
				case MAYORIGUAL:
					switch (tipos) {
					case BYTE:
						numero = leerNumero(Tipo.BYTE);
						if (!((byte)((Object)((Object)numero)) >= (byte)((Object)comparado))) {
							System.out.printf("El número introducido NO es mayor o igual a %d, INTENTELO DE NUEVO: ", ((Object)((Object)comparado)));
							valorValido = false;
						}else
							valorValido = true;
						break;
					case SHORT:
						numero = leerNumero(Tipo.SHORT);
						if (!((short)((Object)numero) >= (short)((Object)comparado))) {
							System.out.printf("El número introducido NO es mayor o igual a %d, INTENTELO DE NUEVO: ", ((Object)comparado));
							valorValido = false;
						}else
							valorValido = true;
						break;
					case INT:
						numero = leerNumero(Tipo.INT);
						if (!((int)((Object)numero) >= (int)((Object)comparado))) {
							System.out.printf("El número introducido NO es mayor o igual a %d, INTENTELO DE NUEVO: ", ((Object)comparado));
							valorValido = false;
						}else
							valorValido = true;
						break;
					case LONG:
						numero = leerNumero(Tipo.LONG);
						if (!((long)((Object)numero) >= (long)((Object)comparado))) {
							System.out.printf("El número introducido NO es mayor o igual a %d, INTENTELO DE NUEVO: ", ((Object)comparado));
							valorValido = false;
						}else
							valorValido = true;
						break;
					case FLOAT:
						numero = leerNumero(Tipo.FLOAT);
						if (!((float)((Object)numero) >= (float)((Object)comparado))) {
							System.out.printf("El número introducido NO es mayor o igual a %.2f, INTENTELO DE NUEVO: ", ((Object)comparado));
							valorValido = false;
						}else
							valorValido = true;
						break;
					case DOUBLE:
						numero = leerNumero(Tipo.DOUBLE);
						if (!((double)((Object)numero) >= (double)((Object)comparado))) {
							System.out.printf("El número introducido NO es mayor o igual a %.2f, INTENTELO DE NUEVO: ", ((Object)comparado));
							valorValido = false;
						}else
							valorValido = true;
						break;
					}
					break;
				case MENORIGUAL:
					switch (tipos) {
					case BYTE:
						numero = leerNumero(Tipo.BYTE);
						if (!((byte)((Object)((Object)numero)) <= (byte)((Object)comparado))) {
							System.out.printf("El número introducido NO es mayor o igual a %d, INTENTELO DE NUEVO: ", ((Object)comparado));
							valorValido = false;
						}else
							valorValido = true;
						break;
					case SHORT:
						numero = leerNumero(Tipo.SHORT);
						if (!((short)((Object)numero) <= (short)((Object)comparado))) {
							System.out.printf("El número introducido NO es mayor o igual a %d, INTENTELO DE NUEVO: ", ((Object)comparado));
							valorValido = false;
						}else
							valorValido = true;
						break;
					case INT:
						numero = leerNumero(Tipo.INT);
						if (!((int)((Object)numero) <= (int)((Object)comparado))) {
							System.out.printf("El número introducido NO es mayor o igual a %d, INTENTELO DE NUEVO: ", ((Object)comparado));
							valorValido = false;
						}else
							valorValido = true;
						break;
					case LONG:
						numero = leerNumero(Tipo.LONG);
						if (!((long)((Object)numero) <= (long)((Object)comparado))) {
							System.out.printf("El número introducido NO es mayor o igual a %d, INTENTELO DE NUEVO: ", ((Object)comparado));
							valorValido = false;
						}else
							valorValido = true;
						break;
					case FLOAT:
						numero = leerNumero(Tipo.FLOAT);
						if (!((float)((Object)numero) <= (float)((Object)comparado))) {
							System.out.printf("El número introducido NO es mayor o igual a %.2f, INTENTELO DE NUEVO: ", ((Object)comparado));
							valorValido = false;
						}else
							valorValido = true;
						break;
					case DOUBLE:
						numero = leerNumero(Tipo.DOUBLE);
						if (!((double)((Object)numero) <= (double)((Object)comparado))) {
							System.out.printf("El número introducido NO es mayor o igual a %.2f, INTENTELO DE NUEVO: ", ((Object)comparado));
							valorValido = false;
						}else
							valorValido = true;
						break;
					}
					
					break;
				case MAYOR:
					switch (tipos) {
					case BYTE:
						numero = leerNumero(Tipo.BYTE);
						if (!((byte)((Object)((Object)numero)) > (byte)((Object)comparado))) {
							System.out.printf("El número introducido NO es mayor o igual a %d, INTENTELO DE NUEVO: ", ((Object)comparado));
							valorValido = false;
						}else
							valorValido = true;
						break;
					case SHORT:
						numero = leerNumero(Tipo.SHORT);
						if (!((short)((Object)numero) > (short)((Object)comparado))) {
							System.out.printf("El número introducido NO es mayor o igual a %d, INTENTELO DE NUEVO: ", ((Object)comparado));
							valorValido = false;
						}else
							valorValido = true;
						break;
					case INT:
						numero = leerNumero(Tipo.INT);
						if (!((int)((Object)numero) > (int)((Object)comparado))) {
							System.out.printf("El número introducido NO es mayor o igual a %d, INTENTELO DE NUEVO: ", ((Object)comparado));
							valorValido = false;
						}else
							valorValido = true;
						break;
					case LONG:
						numero = leerNumero(Tipo.LONG);
						if (!((long)((Object)numero) > (long)((Object)comparado))) {
							System.out.printf("El número introducido NO es mayor o igual a %d, INTENTELO DE NUEVO: ", ((Object)comparado));
							valorValido = false;
						}else
							valorValido = true;
						break;
					case FLOAT:
						numero = leerNumero(Tipo.FLOAT);
						if (!((float)((Object)numero) > (float)((Object)comparado))) {
							System.out.printf("El número introducido NO es mayor o igual a %.2f, INTENTELO DE NUEVO: ", ((Object)comparado));
							valorValido = false;
						}else
							valorValido = true;
						break;
					case DOUBLE:
						numero = leerNumero(Tipo.DOUBLE);
						if (!((double)((Object)numero) > (double)((Object)comparado))) {
							System.out.printf("El número introducido NO es mayor o igual a %.2f, INTENTELO DE NUEVO: ", ((Object)comparado));
							valorValido = false;
						}else
							valorValido = true;
						break;
					}
					
					break;
				case MENOR:
					switch (tipos) {
					case BYTE:
						numero = leerNumero(Tipo.BYTE);
						if (!((byte)((Object)((Object)numero)) < (byte)((Object)comparado))) {
							System.out.printf("El número introducido NO es mayor o igual a %d, INTENTELO DE NUEVO: ", ((Object)comparado));
							valorValido = false;
						}else
							valorValido = true;
						break;
					case SHORT:
						numero = leerNumero(Tipo.SHORT);
						if (!((short)((Object)numero) < (short)((Object)comparado))) {
							System.out.printf("El número introducido NO es mayor o igual a %d, INTENTELO DE NUEVO: ", ((Object)comparado));
							valorValido = false;
						}else
							valorValido = true;
						break;
					case INT:
						numero = leerNumero(Tipo.INT);
						if (!((int)((Object)numero) < (int)((Object)comparado))) {
							System.out.printf("El número introducido NO es mayor o igual a %d, INTENTELO DE NUEVO: ", ((Object)comparado));
							valorValido = false;
						}else
							valorValido = true;
						break;
					case LONG:
						numero = leerNumero(Tipo.LONG);
						if (!((long)((Object)numero) < (long)((Object)comparado))) {
							System.out.printf("El número introducido NO es mayor o igual a %d, INTENTELO DE NUEVO: ", ((Object)comparado));
							valorValido = false;
						}else
							valorValido = true;
						break;
					case FLOAT:
						numero = leerNumero(Tipo.FLOAT);
						if (!((float)((Object)numero) < (float)((Object)comparado))) {
							System.out.printf("El número introducido NO es mayor o igual a %.2f, INTENTELO DE NUEVO: ", ((Object)comparado));
							valorValido = false;
						}else
							valorValido = true;
						break;
					case DOUBLE:
						numero = leerNumero(Tipo.DOUBLE);
						if (!((double)((Object)numero) < (double)((Object)comparado))) {
							System.out.printf("El número introducido NO es mayor o igual a %.2f, INTENTELO DE NUEVO: ", ((Object)comparado));
							valorValido = false;
						}else
							valorValido = true;
						break;
					}
					break;	
				}
					
			} catch (InputMismatchException e){
				System.out.println(ERROR_NUM_IMPUT);
				valorValido=false;
			} 	
		}while(!valorValido);
		return numero;
	}
		

	
//PEDIR NÚMERO EN UN RANGO DADO-----------------------------------------------------------------------------------------------------------------
	public static <N> N leerEntre( N minimo, N maximo, Incluido incluido, Tipo tipos) throws IllegalArgumentException{
		N numero=null;
		boolean valorValido=true;
		
		switch (tipos) {
		case BYTE:
			if((byte)((Object)minimo)>(byte)((Object)maximo))
				throw new IllegalArgumentException();
			do {
				switch (incluido) {
				case TODOS:
					numero=leerNumero(Tipo.BYTE);
					if (!((byte)((Object)((Object)numero)) >= (byte)((Object)minimo) && (byte)((Object)((Object)numero)) <= (byte)((Object)maximo))) {
						System.out.printf("El numero no es mayor o igual que %d y menor o igual que %d, intentelo de nuevo: ", minimo,maximo);
						valorValido = false;
					} else
						valorValido = true;
					break;
				case NINGUNO:
					numero=leerNumero(Tipo.BYTE);
					if (!((byte)((Object)((Object)numero)) > (byte)((Object)minimo) && (byte)((Object)((Object)numero)) < (byte)((Object)maximo))) {
						System.out.printf("El numero no es mayor que %d y menor que %d, intentelo de nuevo: ", minimo,maximo);
						valorValido = false;
					} else
						valorValido = true;
					break;
				case MINIMO:
					numero=leerNumero(Tipo.BYTE);
					if (!((byte)((Object)((Object)numero)) >= (byte)((Object)minimo) && (byte)((Object)((Object)numero)) < (byte)((Object)maximo))) {
						System.out.printf("El numero no es mayor o igual que %d y menor que %d, intentelo de nuevo: ",minimo, maximo);
						valorValido = false;
					} else
						valorValido = true;
					break;
				case MAXIMO:
					numero=leerNumero(Tipo.BYTE);
					if (!((byte)((Object)((Object)numero)) > (byte)((Object)minimo) && (byte)((Object)((Object)numero)) <= (byte)((Object)maximo))) {
						System.out.printf("El numero no es mayor que %d y menor o igual que %d, intentelo de nuevo: ",minimo, maximo);
						valorValido = false;
					} else
						valorValido = true;
					break;
				}
			} while (!valorValido);	
			break;
		case SHORT:
			if((short)((Object)minimo)>(short)((Object)maximo))
				throw new IllegalArgumentException();
			do {
				switch (incluido) {
				case TODOS:
					numero=leerNumero(Tipo.SHORT);
					if (!((short)((Object)numero) >= (short)((Object)minimo) && (short)((Object)numero) <= (short)((Object)maximo))) {
						System.out.printf("El numero no es mayor o igual que %d y menor o igual que %d, intentelo de nuevo: ", minimo,maximo);
						valorValido = false;
					} else
						valorValido = true;
					break;
				case NINGUNO:
					numero=leerNumero(Tipo.SHORT);
					if (!((short)((Object)numero) > (short)((Object)minimo) && (short)((Object)numero) < (short)((Object)maximo))) {
						System.out.printf("El numero no es mayor que %d y menor que %d, intentelo de nuevo: ", minimo,maximo);
						valorValido = false;
					} else
						valorValido = true;
					break;
				case MINIMO:
					numero=leerNumero(Tipo.SHORT);
					if (!((short)((Object)numero) >= (short)((Object)minimo) && (short)((Object)numero) < (short)((Object)maximo))) {
						System.out.printf("El numero no es mayor o igual que %d y menor que %d, intentelo de nuevo: ",minimo, maximo);
						valorValido = false;
					} else
						valorValido = true;
					break;
				case MAXIMO:
					numero=leerNumero(Tipo.SHORT);
					if (!((short)((Object)numero) > (short)((Object)minimo) && (short)((Object)numero) <= (short)((Object)maximo))) {
						System.out.printf("El numero no es mayor que %d y menor o igual que %d, intentelo de nuevo: ",minimo, maximo);
						valorValido = false;
					} else
						valorValido = true;
					break;
				}
			} while (!valorValido);	
			break;
		case INT:
			if((int)((Object)minimo)>(int)((Object)maximo))
				throw new IllegalArgumentException();
			do {
				switch (incluido) {
				case TODOS:
					numero=leerNumero(Tipo.INT);
					if (!((int)((Object)numero) >= (int)((Object)minimo) && (int)((Object)numero) <= (int)((Object)maximo))) {
						System.out.printf("El numero no es mayor o igual que %d y menor o igual que %d, intentelo de nuevo: ", minimo,maximo);
						valorValido = false;
					} else
						valorValido = true;
					break;
				case NINGUNO:
					numero=leerNumero(Tipo.INT);
					if (!((int)((Object)numero) > (int)((Object)minimo) && (int)((Object)numero) < (int)((Object)maximo))) {
						System.out.printf("El numero no es mayor que %d y menor que %d, intentelo de nuevo: ", minimo,maximo);
						valorValido = false;
					} else
						valorValido = true;
					break;
				case MINIMO:
					numero=leerNumero(Tipo.INT);
					if (!((int)((Object)numero) >= (int)((Object)minimo) && (int)((Object)numero) < (int)((Object)maximo))) {
						System.out.printf("El numero no es mayor o igual que %d y menor que %d, intentelo de nuevo: ",minimo, maximo);
						valorValido = false;
					} else
						valorValido = true;
					break;
				case MAXIMO:
					numero=leerNumero(Tipo.INT);
					if (!((int)((Object)numero) > (int)((Object)minimo) && (int)((Object)numero) <= (int)((Object)maximo))) {
						System.out.printf("El numero no es mayor que %d y menor o igual que %d, intentelo de nuevo: ",minimo, maximo);
						valorValido = false;
					} else
						valorValido = true;
					break;
				}
			} while (!valorValido);	
			break;
		case LONG:
			if((long)((Object)minimo)>(long)((Object)maximo))
				throw new IllegalArgumentException();
			do {
				switch (incluido) {
				case TODOS:
					numero=leerNumero(Tipo.LONG);
					if (!((long)((Object)numero) >= (long)((Object)minimo) && (long)((Object)numero) <= (long)((Object)maximo))) {
						System.out.printf("El numero no es mayor o igual que %d y menor o igual que %d, intentelo de nuevo: ", minimo,maximo);
						valorValido = false;
					} else
						valorValido = true;
					break;
				case NINGUNO:
					numero=leerNumero(Tipo.LONG);
					if (!((long)((Object)numero) > (long)((Object)minimo) && (long)((Object)numero) < (long)((Object)maximo))) {
						System.out.printf("El numero no es mayor que %d y menor que %d, intentelo de nuevo: ", minimo,maximo);
						valorValido = false;
					} else
						valorValido = true;
					break;
				case MINIMO:
					numero=leerNumero(Tipo.LONG);
					if (!((long)((Object)numero) >= (long)((Object)minimo) && (long)((Object)numero) < (long)((Object)maximo))) {
						System.out.printf("El numero no es mayor o igual que %d y menor que %d, intentelo de nuevo: ",minimo, maximo);
						valorValido = false;
					} else
						valorValido = true;
					break;
				case MAXIMO:
					numero=leerNumero(Tipo.LONG);
					if (!((long)((Object)numero) > (long)((Object)minimo) && (long)((Object)numero) <= (long)((Object)maximo))) {
						System.out.printf("El numero no es mayor que %d y menor o igual que %d, intentelo de nuevo: ",minimo, maximo);
						valorValido = false;
					} else
						valorValido = true;
					break;
				}
			} while (!valorValido);	
			break;
		case FLOAT:
			if((float)((Object)minimo)>(float)((Object)maximo))
				throw new IllegalArgumentException();
			do {
				switch (incluido) {
				case TODOS:
					numero=leerNumero(Tipo.FLOAT);
					if (!((float)((Object)numero) >= (float)((Object)minimo) && (float)((Object)numero) <= (float)((Object)maximo))) {
						System.out.printf("El numero no es mayor o igual que %.2f y menor o igual que %.2f, intentelo de nuevo: ", minimo,maximo);
						valorValido = false;
					} else
						valorValido = true;
					break;
				case NINGUNO:
					numero=leerNumero(Tipo.FLOAT);
					if (!((float)((Object)numero) > (float)((Object)minimo) && (float)((Object)numero) < (float)((Object)maximo))) {
						System.out.printf("El numero no es mayor que %.2f y menor que %.2f, intentelo de nuevo: ", minimo,maximo);
						valorValido = false;
					} else
						valorValido = true;
					break;
				case MINIMO:
					numero=leerNumero(Tipo.FLOAT);
					if (!((float)((Object)numero) >= (float)((Object)minimo) && (float)((Object)numero) < (float)((Object)maximo))) {
						System.out.printf("El numero no es mayor o igual que %.2f y menor que %.2f, intentelo de nuevo: ",minimo, maximo);
						valorValido = false;
					} else
						valorValido = true;
					break;
				case MAXIMO:
					numero=leerNumero(Tipo.FLOAT);
					if (!((float)((Object)numero) > (float)((Object)minimo) && (float)((Object)numero) <= (float)((Object)maximo))) {
						System.out.printf("El numero no es mayor que %.2f y menor o igual que %.2f, intentelo de nuevo: ",minimo, maximo);
						valorValido = false;
					} else
						valorValido = true;
					break;
				}
			} while (!valorValido);	
			break;
		case DOUBLE:
			if((double)((Object)minimo)>(double)((Object)maximo))
				throw new IllegalArgumentException();
			do {
				switch (incluido) {
				case TODOS:
					numero=leerNumero(Tipo.DOUBLE);
					if (!((double)((Object)numero) >= (double)((Object)minimo) && (double)((Object)numero) <= (double)((Object)maximo))) {
						System.out.printf("El numero no es mayor o igual que %.2f y menor o igual que %.2f, intentelo de nuevo: ", minimo,maximo);
						valorValido = false;
					} else
						valorValido = true;
					break;
				case NINGUNO:
					numero=leerNumero(Tipo.DOUBLE);
					if (!((double)((Object)numero) > (double)((Object)minimo) && (double)((Object)numero) < (double)((Object)maximo))) {
						System.out.printf("El numero no es mayor que %.2f y menor que %.2f, intentelo de nuevo: ", minimo,maximo);
						valorValido = false;
					} else
						valorValido = true;
					break;
				case MINIMO:
					numero=leerNumero(Tipo.DOUBLE);
					if (!((double)((Object)numero) >= (double)((Object)minimo) && (double)((Object)numero) < (double)((Object)maximo))) {
						System.out.printf("El numero no es mayor o igual que %.2f y menor que %.2f, intentelo de nuevo: ",minimo, maximo);
						valorValido = false;
					} else
						valorValido = true;
					break;
				case MAXIMO:
					numero=leerNumero(Tipo.DOUBLE);
					if (!((double)((Object)numero) > (double)((Object)minimo) && (double)((Object)numero) <= (double)((Object)maximo))) {
						System.out.printf("El numero no es mayor que %.2f y menor o igual que %.2f, intentelo de nuevo: ",minimo, maximo);
						valorValido = false;
					} else
						valorValido = true;
					break;
				}
			} while (!valorValido);	
			break;
		}
		
		return numero;
	}
	
}
