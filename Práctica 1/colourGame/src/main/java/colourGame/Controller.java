package colourGame;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "controladorJuego", eager = true)
@SessionScoped

/**
 * Clase del controlador que se encarga de dar valor y modificar los diversos
 * elementos que se pueden visualizar en las tres p�ginas xhtml del proyecto:
 * home, game y ranking. 
 * 
 * @author Lidia S�nchez M�rida
 *
 */
public class Controller implements Serializable {
	private static final long serialVersionUID = 1L;
	// Diccionario de colores para conformar las respuestas y sus colores
	private static Map<String, String> colores;
	// Almacena el color correcto de la pregunta actual
	private static String colorCorrecto;
	// Constantes para definir los colores de los mensajes feedback, 
		// preguntas y respuestas.
	private final String COLOR_ROJO = "red";
	private final String COLOR_VERDE = "green";
	private final String COLOR_BLANCO = "white";
	private final String COLOR_NEGRO = "black";
	private final String COLOR_AMARILLO = "yellow";
	// N� de aciertos de la partida actual
	private int nAciertos;
	
    ///////////////////////////////////////////////////
	// Texto para el t�tulo del juego
	@ManagedProperty(value = "#{textoTituloJuego}")
	private String textoTituloJuego;
	
	// Texto para las instrucciones del juego
	@ManagedProperty(value = "#{textoInstrucciones}")
	private String textoInstrucciones;
	
	// Texto para la respuesta de ejemplo
	@ManagedProperty(value = "#{textoRespuestaEjemplo}")
	private String textoRespuestaEjemplo;
	
	// Color y texto para el bot�n de comenzar
	@ManagedProperty(value = "#{textoComenzar}")
	private String textoComenzar;
	
	@ManagedProperty(value = "#{colorBtnComenzar}")
	private String colorBtnComenzar;
	
	@ManagedProperty(value = "#{colorTextoComenzar}")
	private String colorTextoComenzar;
	
	// Texto del bot�n de pregunta anterior
	@ManagedProperty(value = "#{textoPreguntaAnterior}")
	private String textoPreguntaAnterior;
	
	// Texto del bot�n de pregunta anterior
	@ManagedProperty(value = "#{textoPreguntaSiguiente}")
	private String textoPreguntaSiguiente;
	
	// Para inyectar tanto las preguntas como las respuestas
	@ManagedProperty(value="#{preguntasRespuestas}")
	private QuestionsAndAnswers preguntasRespuestas;
	
	// Texto y el color de la pregunta
	@ManagedProperty(value = "#{textoPregunta}")
	private String textoPregunta = "No hay m�s preguntas.";

	@ManagedProperty(value = "#{colorPregunta}")
	private String colorPregunta;
	
	// N�mero de la pregunta actual para mostrar
	private int nPregunta = 0;
	@ManagedProperty(value = "#{numeroPregunta}")
	private String numeroPregunta;
	
	// Color del texto de todas las respuestas
	@ManagedProperty(value = "#{colorRespuesta}")
	private String colorRespuesta;
	
	// Propiedades de la respuesta 1
	@ManagedProperty(value = "#{colorBtnRespuesta1}")
	private String colorBtnRespuesta1;
	
	@ManagedProperty(value = "#{textoRespuesta1}")
	private String textoRespuesta1;
	
	// Propiedades de la respuesta 2
	@ManagedProperty(value = "#{colorBtnRespuesta2}")
	private String colorBtnRespuesta2;
	
	@ManagedProperty(value = "#{textoRespuesta2}")
	private String textoRespuesta2;
	
	// Propiedades de la respuesta 3
	@ManagedProperty(value = "#{colorBtnRespuesta3}")
	private String colorBtnRespuesta3;
	
	@ManagedProperty(value = "#{textoRespuesta3}")
	private String textoRespuesta3;
		
	// Propiedades de la respuesta 4
	@ManagedProperty(value = "#{colorBtnRespuesta4}")
	private String colorBtnRespuesta4;
	
	@ManagedProperty(value = "#{textoRespuesta4}")
	private String textoRespuesta4;
	
	// Texto del resultado de escoger una respuesta
	@ManagedProperty(value = "#{textoResultado}")
	private String textoResultado;
	
	// Color del texto resultado
	@ManagedProperty(value = "#{colorTextoResultado}")
	private String colorTextoResultado;
	
	// Texto del t�tulo del ranking
	@ManagedProperty(value = "#{textoTituloRanking}")
	private String textoTituloRanking;
	
	// Texto del ranking
	@ManagedProperty(value = "#{textoRanking}")
	private String textoRanking;
	
	// Texto del n�mero de aciertos actual
	@ManagedProperty(value = "#{numeroAciertos}")
	private String numeroAciertos;
	
	// Texto del n�mero de preguntas actual
	@ManagedProperty(value = "#{numeroPreguntas}")
	private String numeroPreguntas;
	
	// Color del n�mero de aciertos y de preguntas actual
	@ManagedProperty(value = "#{colorNumeroAciertos}")
	private String colorNumeroAciertos;
	
	////////////////////////////////////////////////////////////////////////
	// INICIALIZA EL DICCIONARIO DE COLORES
	private void inicializarDiccionarioColores() {
		colores = new HashMap<String, String>();
		colores.put("ROJO", "red");
		colores.put("VERDE", "green");
		colores.put("AZUL", "blue");
		colores.put("CI�N", "cyan");
		colores.put("AMARILLO", "yellow");
		colores.put("NARANJA", "orange");
		colores.put("ROSA", "pink");
		colores.put("MAGENTA", "magenta");
		colores.put("MARR�N", "brown");
		colores.put("MORADO", "purple");
		colores.put("GRIS", "gray");
		colores.put("NEGRO", "black");
	}
	
	// TEXTO DEL TITULO DEL JUEGO
	public String getTextoTituloJuego() {
		// Inicializamos el diccionario de colores
		inicializarDiccionarioColores();
		// Texto del t�tulo del juego
		if (textoTituloJuego == null) textoTituloJuego = "Colour Quizz Game";
		return textoTituloJuego;
	}
	
	public void setTextoTituloJuego(String t) {
		textoTituloJuego = t;
	}
	
	// TEXTO DE LAS INSTRUCCIONES
	public String getTextoInstrucciones() {
		if (textoInstrucciones == null) textoInstrucciones = "�Bienvenido al juego Colour Quizz Game!"
				+ " Si desea pasar un rato divertido ha llegado al lugar correcto. Las instrucciones son"
				+ " muy sencillas. Tras pulsar sobre el bot�n Comenzar aparecer�n una serie de preguntas"
				+ "cuyas respuestas son colores. Deber� elegir la respuesta correcta para cada pregunta. "
				+ "���Pero cuidado!!! Preste atenci�n al texto de las respuestas, no a su color.";
		return textoInstrucciones;
	}
	
	// TEXTO DE LA RESPUESTA DE EJEMPLO
	public String getTextoRespuestaEjemplo() {
		if (textoRespuestaEjemplo == null) textoRespuestaEjemplo = "La respuesta correcta a esta pregunta"
				+ " ser�a NARANJA por lo que habr�a que pulsar sobre el bot�n cuyo texto representase este color. "
				+ "��Cuidado con pulsar sobre un bot�n de color naranja!! No todo es lo que parece....";
		return textoRespuestaEjemplo;
	}
	
	public void setTextoRespuestaEjemplo(String t) {
		textoRespuestaEjemplo = t;
	}
	
	public void setTextoInstrucciones(String t) {
		textoInstrucciones = t;
	}
	
	// PREGUNTAS Y RESPUESTAS
	public QuestionsAndAnswers getPreguntasRespuestas() {
		return preguntasRespuestas;
	}
	
	public void setPreguntasRespuestas(QuestionsAndAnswers pr) {
		preguntasRespuestas = pr;
	}
	
	// N� DE LA PREGUNTA ACTUAL
	public String getNumeroPregunta() {
		numeroPregunta = Integer.toString(nPregunta+1);
		return numeroPregunta;
	}
	
	public void setNumeroPregunta(String np) {
		numeroPregunta = np;
	}
	
	// TEXTO DE LA PREGUNTA
	public String getTextoPregunta() {
		if (preguntasRespuestas != null && !preguntasRespuestas.getPreguntas().isEmpty()) 
			textoPregunta = (preguntasRespuestas.getPreguntas()).get(nPregunta);
		return textoPregunta;
	}
	
	public void setTextoPregunta(String p) {
		textoPregunta = p;
	}
	
	// COLOR DEL TEXTO DE LA PREGUNTA
	public String getColorPregunta() {
		colorPregunta = COLOR_NEGRO;
		return colorPregunta;
	}
	
	public void setColorPregunta(String c) {
		colorPregunta = c;
	}
	
	// TEXTO CON EL N�MERO DE PREGUNTAS ACTUAL
	public String getNumeroPreguntas() {
		numeroPreguntas = Integer.toString(preguntasRespuestas.getNumeroPreguntas());
		return numeroPreguntas;
	}
	
	public void setNumeroPreguntas(String np) {
		numeroPreguntas = np;
	}
	
	// TEXTO DEL BOT�N COMENZAR
	public String getTextoComenzar() {
		if (textoComenzar == null) textoComenzar = "COMENZAR JUEGO";
		colorBtnRespuesta1 = colorBtnRespuesta2 = colorBtnRespuesta3 = colorBtnRespuesta4 = null;
		textoRespuesta2 = textoRespuesta3 = textoRespuesta4 = null;
		return textoComenzar;
	}
	
	public void setTextoComenzar(String t) {
		textoComenzar = t;
	}
	
	// COLOR DEL TEXTO DEL BOT�N COMENZAR
	public String getColorTextoComenzar() {
		if (colorTextoComenzar == null) colorTextoComenzar = COLOR_NEGRO;
		return colorTextoComenzar;
	}
	
	public void setColorTextoComenzar(String c) {
		colorTextoComenzar = c;
	}
	
	// COLOR DE FONDO DEL BOT�N COMENZAR
	public String getColorBtnComenzar() {
		if (colorBtnComenzar == null) colorBtnComenzar = COLOR_AMARILLO;
		return colorBtnComenzar;
	}
	
	public void setColorBtnComenzar(String c) {
		colorBtnComenzar = c;
	}
	
	// TEXTO DEL BOT�N PREGUNTA ANTERIOR
	public String getTextoPreguntaAnterior() {
		if (textoPreguntaAnterior == null) textoPreguntaAnterior = "ANTERIOR";
		return textoPreguntaAnterior;
	}
	
	public void setTextoPreguntaAnterior(String pa) {
		textoPreguntaAnterior = pa;
	}
	
	// TEXTO DEL BOT�N PREGUNTA ANTERIOR
	public String getTextoPreguntaSiguiente() {
		if (textoPreguntaSiguiente == null) textoPreguntaSiguiente = "SIGUIENTE";
		return textoPreguntaSiguiente;
	}
	
	public void setTextoPreguntaSiguiente(String ps) {
		textoPreguntaSiguiente = ps;
	}
	
	// COLOR DEL TEXTO DE LAS RESPUESTAS
	public String getColorRespuesta() {
		if (colorRespuesta == null) colorRespuesta = COLOR_BLANCO;
		return colorRespuesta;
	}
	
	public void setColorRespuesta(String c) {
		colorRespuesta = c;
	}
	
	// M�TODO PARA ELEGIR UN COLOR DE FONDO AL AZAR
	public String colorAleatorio() {
		String key = "";
		Random r = new Random();
		int n_color = r.nextInt(colores.size()); // [0, tama�o de colores - 1]
		int indice = 0;
		for (Map.Entry<String,String> color: colores.entrySet()) {
			if (indice == n_color) {
				key = color.getKey();
				break;
			}
			indice++;
		}
		return key;
	}
	
	// TEXTO DE LA RESPUESTA 1
	public String getTextoRespuesta1() {
		if (preguntasRespuestas != null && !preguntasRespuestas.getRespuestas().isEmpty()) {
			textoRespuesta1 = preguntasRespuestas.getRespuestas().get(nPregunta);
		}
		return textoRespuesta1;
	}
	
	public void setTextoRespuesta1(String r1) {
		textoRespuesta1 = r1;
	}
	
	// COLOR DE FONDO DEL BOT�N DE LA RESPUESTA 1
	public String getColorBtnRespuesta1() {
		if (colorBtnRespuesta1 == null) {
			// Respuesta correcta
			String color = colorAleatorio();
			colorBtnRespuesta1 = colores.get(color);
			colores.remove(color);
		}
		return colorBtnRespuesta1;
	}
	
	public void setColorBtnRespuesta1(String c) {
		colorBtnRespuesta1 = c;
	}
	
	// TEXTO DE LA RESPUESTA 2
	public String getTextoRespuesta2() {
		if (textoRespuesta2 == null) {
			// Establecemos el color correcto
			colorCorrecto = colores.get(textoRespuesta1);
			// Eliminamos la respuesta correcta para no ser elegida de nuevo
			colores.remove(textoRespuesta1);
			String color = colorAleatorio();
			textoRespuesta2 = color;
			colores.remove(color);
		}
		return textoRespuesta2;
	}
	
	public void setTextoRespuesta2(String r1) {
		textoRespuesta2 = r1;
	}
	
	// COLOR DE FONDO DEL BOT�N DE LA RESPUESTA 2
	public String getColorBtnRespuesta2() {
		if (colorBtnRespuesta2 == null) {
			String color = colorAleatorio();
			colorBtnRespuesta2 = colores.get(color);
			colores.remove(color);
		}
		return colorBtnRespuesta2;
	}
	
	public void setColorBtnRespuesta2(String c) {
		colorBtnRespuesta2 = c;
	}
	
	// TEXTO DE LA RESPUESTA 3
	public String getTextoRespuesta3() {
		if (textoRespuesta3 == null) {
			String color = colorAleatorio();
			textoRespuesta3 = color;
			colores.remove(color);
		}
		return textoRespuesta3;
	}
	
	public void setTextoRespuesta3(String r1) {
		textoRespuesta3 = r1;
	}
	
	// COLOR DE FONDO DEL BOT�N DE LA RESPUESTA 3
	public String getColorBtnRespuesta3() {
		if (colorBtnRespuesta3 == null) {
			colorBtnRespuesta3 = colorCorrecto;
		}
		return colorBtnRespuesta3;
	}
	
	public void setColorBtnRespuesta3(String c) {
		colorBtnRespuesta3 = c;
	}
	
	// TEXTO DE LA RESPUESTA 4
	public String getTextoRespuesta4() {
		if (textoRespuesta4 == null) {
			String color = colorAleatorio();
			textoRespuesta4 = color;
			colores.remove(color);
		}
		return textoRespuesta4;
	}
	
	public void setTextoRespuesta4(String r1) {
		textoRespuesta4 = r1;
	}
	
	// COLOR DE FONDO DEL BOT�N DE LA RESPUESTA 4
	public String getColorBtnRespuesta4() {
		if (colorBtnRespuesta4 == null) {
			String color = colorAleatorio();
			colorBtnRespuesta4 = colores.get(color);
			colores.remove(color);
		}
		return colorBtnRespuesta4;
	}
	
	public void setColorBtnRespuesta4(String c) {
		colorBtnRespuesta4 = c;
	}
	
	// TEXTO DEL RESULTADO DE PINCHAR SOBRE UNA RESPUESTA
	public String getTextoResultado() {
		if (textoResultado == null) textoResultado = "";
		return textoResultado;
	}
	
	public void setTextoResultado(String tr) {
		textoResultado = tr;
	}
	
	// COLOR PARA EL TEXTO DEL RESULTADO
	public String getColorTextoResultado() {
		return colorTextoResultado;
	}
	
	public void setColorTextoResultado(String c) {
		colorTextoResultado = c;
	}
	
	// TEXTO DEL N�MERO DE ACIERTOS ACTUAL
	public String getNumeroAciertos() {
		numeroAciertos = Integer.toString(nAciertos);
		return numeroAciertos;
	}
	
	public void setNumeroAciertos(String na) {
		numeroAciertos = na;
	}
	
	// COLOR DEL N�MERO DE ACIERTOS Y PREGUNTAS ACTUAL
	public String getColorNumeroAciertos() {
		colorNumeroAciertos = COLOR_VERDE;
		return colorNumeroAciertos;
	}
	
	public void setColorNumeroAciertos(String c) {
		colorNumeroAciertos = c;
	}
	
	// TEXTO DEL T�TULO DEL RANKING
	public String getTextoTituloRanking() {
		textoTituloRanking = "RANKING GLOBAL DE LAS PARTIDAS";
		return textoTituloRanking;
	}
	
	public void setTextoTituloRanking(String t) {
		textoTituloRanking = t;
	}

	// TEXTO DEL RANKING
	public String getTextoRanking() {
		textoRanking = "";
		int n_registro = 0;
		for (Integer registro: preguntasRespuestas.getRanking()) {
			textoRanking += "\nPartida n�  " + (n_registro+1) + " : " +
				registro + " aciertos de " + preguntasRespuestas.getNumeroPreguntas() + " preguntas. / ";
			n_registro ++;
		}
		return textoRanking;
	}
	
	public void setTextoRanking(String t) {
		textoRanking = t;
	}
	
	// M�TODO PARA DECIDIR SI LA RESPUESTA ESCOGIDA ES LA CORRECTA
	public void comprobarRespuesta(String textoNRespuesta) {
		if (!preguntasRespuestas.getRespuestasCorrectas().get(nPregunta)) {
			colorTextoResultado = COLOR_ROJO;
			if (textoNRespuesta.equals(preguntasRespuestas.getRespuestas().get(nPregunta))) {
				textoResultado = "���ACERTASTE!!!";
				colorTextoResultado = COLOR_VERDE;
				preguntasRespuestas.setRespuestaCorrecta(nPregunta);
				nAciertos ++;
			}
			else textoResultado = "OOOHH... Has fallado. Preste m�s atenci�n.";
		}
	}
	
	// MANEJADOR DE EVENTOS PARA EL BOT�N DE LA RESPUESTA 1
	public void clickBtnRespuesta1(ActionEvent e) {
		comprobarRespuesta(textoRespuesta1);
	}
	
	// MANEJADOR DE EVENTOS PARA EL BOT�N DE LA RESPUESTA 2
	public void clickBtnRespuesta2(ActionEvent e) {
		comprobarRespuesta(textoRespuesta2);
	}
	
	// MANEJADOR DE EVENTOS PARA EL BOT�N DE LA RESPUESTA 3
	public void clickBtnRespuesta3(ActionEvent e) {
		comprobarRespuesta(textoRespuesta3);
	}
	
	// MANEJADOR DE EVENTOS PARA EL BOT�N DE LA RESPUESTA 4
	public void clickBtnRespuesta4(ActionEvent e) {
		comprobarRespuesta(textoRespuesta4);
	}
	
	// M�TODO QUE INCLUYE LAS ACCIONES COMUNES A "ANTERIOR PREGUNTA"
	//	Y A "SIGUIENTE PREGUNTA"
	public void pasarAOtraPregunta() {
		textoResultado = "";
		// Comprobar si ya ha sido correctamente contestada
		if (preguntasRespuestas.getRespuestasCorrectas().get(nPregunta)) {
			textoResultado = "Esta pregunta ya la has acertado.";
			colorTextoResultado = COLOR_VERDE;
		}
		this.setTextoPregunta(preguntasRespuestas.getPreguntas().get(nPregunta));
		this.setTextoRespuesta1(preguntasRespuestas.getRespuestas().get(nPregunta));
		colorBtnRespuesta1 = colorBtnRespuesta2 = colorBtnRespuesta3 = colorBtnRespuesta4 = null;
		textoRespuesta2 = textoRespuesta3 = textoRespuesta4 = null;
	}
	
	// MANEJADOR DE EVENTOS PARA EL BOT�N ANTERIOR
	public void preguntaAnterior(ActionEvent e) {
		if (nPregunta > 0) {
			nPregunta --;
			pasarAOtraPregunta();
		}
	}
	
	// MANEJADOR DE EVENTOS PARA EL BOT�N SIGUIENTE
	public void preguntaSiguiente(ActionEvent e) {
		if (nPregunta < preguntasRespuestas.getRespuestas().size()-1) {
			nPregunta ++;
			pasarAOtraPregunta();
		}
	}
	
	// M�TODO QUE USA REGLAS DE NAVEGACI�N PARA REDIRIGIR A LA P�GINA DEL RANKING
	public String terminarJuego() {
		return "ranking";
	}
	
	// MANEJADOR DE EVENTOS PARA CALCULAR EL N� DE ACIERTOS DE LA PARTIDA 
	public void calcularPuntuacion(ActionEvent e) {
		nPregunta = 0;
		textoResultado = "";
		preguntasRespuestas.setRegistroRanking(nAciertos);
		nAciertos = 0;
		preguntasRespuestas.iniciarNuevaPartida();
	}
}