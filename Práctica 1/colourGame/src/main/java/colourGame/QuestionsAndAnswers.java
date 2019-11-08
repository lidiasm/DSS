package colourGame;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;

@ManagedBean(name = "preguntasRespuestas", eager = true)
@SessionScoped

/**
 * Clase que almacena las preguntas y respuestas del juego as� como
 * cu�les son las respuestas correctas actualmente. 
 * Asimismo tambi�n almacena un ranking con los resultados de todas las
 * partidas que se hayan jugado hasta el momento.
 * 
 * @author Lidia S�nchez M�rida.
 *
 */
public class QuestionsAndAnswers implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final int NUMERO_PREGUNTAS = 10;
	private ArrayList<String> preguntas = new ArrayList<String>(NUMERO_PREGUNTAS);
	private ArrayList<String> respuestas = new ArrayList<String>(NUMERO_PREGUNTAS);
	private ArrayList<Boolean> respuestasCorrectas = new ArrayList<Boolean>();
	private ArrayList<Integer> ranking = new ArrayList<Integer>();
	
	public QuestionsAndAnswers() {
		preguntas.add("�De qu� color es una naranja?");	//1
		respuestas.add("NARANJA");
		preguntas.add("�De qu� color es una pantera?"); //2	
		respuestas.add("NEGRO");
		preguntas.add("�De qu� es un bol�grafo azul?"); //3
		respuestas.add("AZUL");
		preguntas.add("�De qu� color es rosa roja?"); //4
		respuestas.add("ROJO");
		preguntas.add("�De qu� color es una estrella?"); //5
		respuestas.add("AMARILLO");
		preguntas.add("�De qu� color es una pera?"); //6
		respuestas.add("VERDE");
		preguntas.add("�De qu� color es una fresa?"); //7
		respuestas.add("ROJO");
		preguntas.add("�De qu� color es el cielo?"); //8
		respuestas.add("AZUL");
		preguntas.add("�De qu� color es un oso pardo?"); //9
		respuestas.add("MARR�N");
		preguntas.add("�De qu� color es un gato siam�s?"); //10
		respuestas.add("MARRON");
		iniciarNuevaPartida();
	}
	
	// GET PARA EL N�MERO DE PREGUNTAS
	public int getNumeroPreguntas() {
		return NUMERO_PREGUNTAS;
	}
	
	// GET Y SET PARA LAS PREGUNTAS
	public ArrayList<String> getPreguntas() {
		return preguntas;
	}
	
	public void setPreguntas(ArrayList<String> q) {
		preguntas = new ArrayList<String>(q);
	}
	
	// GET Y SET PARA LAS RESPUESTAS
	public ArrayList<String> getRespuestas() {
		return respuestas;
	}
	
	public void setRespuestas(ArrayList<String> a) {
		respuestas = new ArrayList<String>(a);
	}
	
	// M�TODO PARA INICIAR LA PARTIDA SIN NING�N ACIERTO
	public void iniciarNuevaPartida() {
		respuestasCorrectas = new ArrayList<Boolean>();
		for (int i = 0; i < NUMERO_PREGUNTAS; i++) respuestasCorrectas.add(false);
	}
	
	// GET Y SET PARA LAS RESPUESTAS CORRECTAS
	public ArrayList<Boolean> getRespuestasCorrectas() {
		return respuestasCorrectas;
	}
	
	public void setRespuestasCorrectas(ArrayList<Boolean> rc) {
		respuestasCorrectas = new ArrayList<Boolean>(rc);
	}
	
	public void setRespuestaCorrecta(int nPreguntaAcertada) {
		respuestasCorrectas.set(nPreguntaAcertada, true);
	}
	
	// GET Y SET PARA EL RANKING
	public ArrayList<Integer> getRanking() {
		return ranking;
	}
	
	public void setRegistroRanking(int registro) {
		ranking.add(registro);
	}
	
	public void setRanking(ArrayList<Integer> r) {
		ranking = new ArrayList<Integer>(r);
	}
}
