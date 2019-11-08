package cliente;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dao.*;
import modelo.*;

public class ClienteJava extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private List<Juguete> juguetes;
	private javax.swing.JButton botonIniciarSesion;
    private javax.swing.JPanel panelListadoJuguetes;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JScrollPane panelScroll;
    private javax.swing.JLabel tituloVista;
    private ListaJuguetes listaJuguetes;
    
    public ListaJuguetes getLista() {return listaJuguetes;}
	
	public ClienteJava() {
		juguetes = new ArrayList<Juguete>((JugueteDao.INSTANCE).getJuguetes());
		initComponents();
	}
	
    private void initComponents() {
    	panelScroll = new javax.swing.JScrollPane();
        panelPrincipal = new javax.swing.JPanel();
        tituloVista = new javax.swing.JLabel();
        botonIniciarSesion = new javax.swing.JButton();
        panelListadoJuguetes = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tituloVista.setFont(new java.awt.Font("Bodoni MT", 1, 20)); // NOI18N
        tituloVista.setText("Catálogo de juguetes.");

        botonIniciarSesion.setText("Iniciar sesión");

        javax.swing.GroupLayout panelListadoJuguetesLayout = new javax.swing.GroupLayout(panelListadoJuguetes);
        panelListadoJuguetes.setLayout(panelListadoJuguetesLayout);
        panelListadoJuguetesLayout.setHorizontalGroup(
            panelListadoJuguetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelListadoJuguetesLayout.setVerticalGroup(
            panelListadoJuguetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 224, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelListadoJuguetes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addComponent(tituloVista)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                        .addComponent(botonIniciarSesion)))
                .addGap(51, 51, 51))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tituloVista)
                    .addComponent(botonIniciarSesion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelListadoJuguetes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        panelScroll.setViewportView(panelPrincipal);
        // Catálogo de juguetes
        listaJuguetes = new ListaJuguetes(juguetes);
    	JTable tablaJuguetes = new JTable(listaJuguetes); 
        panelScroll.add(tablaJuguetes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelScroll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelScroll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        
        pack();
    }
	
	public static void main(String[] args) {
        // TODO code application logic here
        ClienteJava cliente = new ClienteJava();
        JTable tablaJuguetes = new JTable(cliente.getLista()); 
        cliente.getContentPane().add(new JScrollPane(tablaJuguetes), "Center");
        cliente.setSize(800,800);
        cliente.setVisible(true);
        
        /*JFrame frame = new JFrame("FileTableDemo"); 
        frame.getContentPane().add(new JScrollPane(tablaJuguetes), "Center"); 
        frame.setSize(600, 400); 
        frame.setVisible(true);
			*/
		/*JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    Object rowData[][] = { { "Row1-Column1", "Row1-Column2", "Row1-Column3" },
	        { "Row2-Column1", "Row2-Column2", "Row2-Column3" } };
	    Object columnNames[] = { "Column One", "Column Two", "Column Three" };
	    JTable table = new JTable(rowData, columnNames);

	    JScrollPane scrollPane = new JScrollPane(table);
	    frame.add(scrollPane, BorderLayout.CENTER);
	    frame.setSize(300, 150);
	    frame.setVisible(true);*/

    }
}