package cliente;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import modelo.Juguete;

public class ListaJuguetes extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
    private List<Juguete> listaJuguetes;
    private String[] columnNames = {"Nombre", "Descripción", "Mín. edad recomendada", "Precio"};

    public ListaJuguetes(List<Juguete> juguetes) { 
        listaJuguetes = new ArrayList<Juguete>(juguetes);
    } 
    
    public int getColumnCount() { return columnNames.length; } 
    
    public int getRowCount() { return listaJuguetes.size(); } 
    
    public String getColumnName(int col) { return columnNames[col]; } 
    
    public Juguete getJuguete(int fila) {
    	return listaJuguetes.get(fila);
    }
 
    public Object getValueAt(int row, int col) { 
        Juguete j = listaJuguetes.get(row); 
        switch(col) { 
            case 0: return j.getNombre(); 
            case 1: return j.getDescripcion(); 
            case 2: return j.getMinEdadRecomendada(); 
            case 3: return j.getPrecio(); 
            case 4: return "Borrar";
            default: return null; 
        } 
    } 
}