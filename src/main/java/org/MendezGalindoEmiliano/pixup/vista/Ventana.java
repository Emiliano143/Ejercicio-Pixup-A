package org.MendezGalindoEmiliano.pixup.vista;

import javax.swing.*;
import java.util.ArrayList;

public class Ventana extends JFrame implements Ejecutable {
    private static Ventana instance;
    private JLabel[] labels;
    private JTextField[] textFields;
    private JComboBox<String> jComboBox;
    private JButton jButtonExecute;

    private String eleccion;

    // ArrayList para almacenar los datos
    private ArrayList<String[]> datos = new ArrayList<>();

    private Ventana() {
        super("Ventana de PixUp");
        init();
    }

    private void init() {
        // Lista desplegable para seleccionar entidad
        jComboBox = new JComboBox<>(new String[]{
                "Estado", "Municipio", "Colonia", "Usuario",
                "Notificación", "Tipo de Notificación", "Domicilio", "Tipo de Domicilio"
        });
        jComboBox.setBounds(20, 10, 300, 30);
        jComboBox.addActionListener(e -> actualizar());

        // Crear etiquetas y campos de texto dinámicos
        labels = new JLabel[7];
        textFields = new JTextField[7];

        for (int i = 0; i < 7; i++) {
            labels[i] = new JLabel("Campo " + (i + 1) + ":");
            labels[i].setBounds(20, 50 + i * 40, 150, 30);
            labels[i].setVisible(false); // Ocultos inicialmente
            add(labels[i]);

            textFields[i] = new JTextField();
            textFields[i].setBounds(180, 50 + i * 40, 150, 30);
            textFields[i].setVisible(false); // Ocultos inicialmente
            add(textFields[i]);
        }

        // Botón para ejecutar acción
        jButtonExecute = new JButton("Ejecutar");
        jButtonExecute.setBounds(20, 350, 100, 30);
        jButtonExecute.addActionListener(e -> ejecutarAccion());

        // Configuración del diseño y agregar componentes
        setLayout(null);
        add(jComboBox);
        add(jButtonExecute);

        // Configuración de la ventana
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 450);
    }

    private void actualizar() {
        // Obtener la entidad seleccionada
        eleccion = (String) jComboBox.getSelectedItem();

        // Limpiar todos los cuadros de texto
        for (JTextField textField : textFields) {
            textField.setText("");
        }

        // Ocultar todas las etiquetas y cuadros de texto
        for (int i = 0; i < 7; i++) {
            labels[i].setVisible(false);
            textFields[i].setVisible(false);
        }

        // Mostrar etiquetas y cuadros de texto según la entidad seleccionada
        switch (eleccion) {
            case "Estado":
                labels[0].setText("ID del Estado:");
                labels[1].setText("Nombre del Estado:");
                labels[0].setVisible(true);
                textFields[0].setVisible(true);
                labels[1].setVisible(true);
                textFields[1].setVisible(true);
                break;

            case "Municipio":
                labels[0].setText("ID del Municipio:");
                labels[1].setText("Nombre del Municipio:");
                labels[2].setText("ID del Estado relacionado:");
                labels[0].setVisible(true);
                textFields[0].setVisible(true);
                labels[1].setVisible(true);
                textFields[1].setVisible(true);
                labels[2].setVisible(true);
                textFields[2].setVisible(true);
                break;

            case "Colonia":
                labels[0].setText("ID de la Colonia:");
                labels[1].setText("Nombre de la Colonia:");
                labels[2].setText("Código Postal:");
                labels[3].setText("ID del Municipio relacionado:");
                labels[0].setVisible(true);
                textFields[0].setVisible(true);
                labels[1].setVisible(true);
                textFields[1].setVisible(true);
                labels[2].setVisible(true);
                textFields[2].setVisible(true);
                labels[3].setVisible(true);
                textFields[3].setVisible(true);
                break;

            case "Usuario":
                labels[0].setText("ID del Usuario:");
                labels[1].setText("Nombre del Usuario:");
                labels[2].setText("Primer Apellido:");
                labels[3].setText("Segundo Apellido:");
                labels[4].setText("Contraseña:");
                labels[5].setText("Email:");
                labels[6].setText("RFC:");
                for (int i = 0; i < 7; i++) {
                    labels[i].setVisible(true);
                    textFields[i].setVisible(true);
                }
                break;

            case "Notificación":
                labels[0].setText("ID de la Notificación:");
                labels[1].setText("Fecha de la Notificación:");
                labels[2].setText("Usuario relacionado:");
                labels[3].setText("Tipo de Notificación:");
                labels[0].setVisible(true);
                textFields[0].setVisible(true);
                labels[1].setVisible(true);
                textFields[1].setVisible(true);
                labels[2].setVisible(true);
                textFields[2].setVisible(true);
                labels[3].setVisible(true);
                textFields[3].setVisible(true);
                break;

            case "Tipo de Notificación":
                labels[0].setText("ID del Tipo de Notificación:");
                labels[1].setText("Descripción:");
                labels[2].setText("Ruta:");
                labels[0].setVisible(true);
                textFields[0].setVisible(true);
                labels[1].setVisible(true);
                textFields[1].setVisible(true);
                labels[2].setVisible(true);
                textFields[2].setVisible(true);
                break;

            case "Domicilio":
                labels[0].setText("ID del Domicilio:");
                labels[1].setText("Calle:");
                labels[2].setText("Número Exterior:");
                labels[3].setText("Número Interior:");
                labels[4].setText("Usuario relacionado:");
                labels[5].setText("Colonia relacionada:");
                labels[6].setText("Tipo de Domicilio:");
                for (int i = 0; i < 7; i++) {
                    labels[i].setVisible(true);
                    textFields[i].setVisible(true);
                }
                break;

            case "Tipo de Domicilio":
                labels[0].setText("ID del Tipo de Domicilio:");
                labels[1].setText("Descripción:");
                labels[0].setVisible(true);
                textFields[0].setVisible(true);
                labels[1].setVisible(true);
                textFields[1].setVisible(true);
                break;
        }
    }

    private void ejecutarAccion() {
        // Opciones CRUD disponibles
        String[] opcionesCRUD = {"Alta", "Baja", "Cambio", "Ver"};
        int seleccion = JOptionPane.showOptionDialog(this, "Seleccione una opción CRUD", "Operación CRUD",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcionesCRUD, opcionesCRUD[0]);

        switch (seleccion) {
            case 0: // Alta
                String[] datosEntidad = new String[labels.length];
                for (int i = 0; i < labels.length; i++) {
                    if (labels[i].isVisible()) {
                        datosEntidad[i] = textFields[i].getText();
                    }
                }
                datos.add(datosEntidad);
                JOptionPane.showMessageDialog(this, "Datos almacenados correctamente.");
                break;

            case 1: // Baja
                if (!datos.isEmpty()) {
                    String idEliminar = JOptionPane.showInputDialog(this, "Ingrese el ID a eliminar:");
                    boolean eliminado = datos.removeIf(dato -> dato[0].equals(idEliminar));
                    if (eliminado) {
                        JOptionPane.showMessageDialog(this, "Datos eliminados correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(this, "ID no encontrado.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No hay datos para eliminar.");
                }
                break;

            case 2: // Cambio
                if (!datos.isEmpty()) {
                    String idModificar = JOptionPane.showInputDialog(this, "Ingrese el ID a modificar:");
                    for (String[] dato : datos) {
                        if (dato[0].equals(idModificar)) {
                            for (int i = 0; i < labels.length; i++) {
                                if (labels[i].isVisible()) {
                                    String nuevoDato = JOptionPane.showInputDialog(this,
                                            "Ingrese nuevo dato para " + labels[i].getText(), dato[i]);
                                    if (nuevoDato != null && !nuevoDato.isEmpty()) {
                                        dato[i] = nuevoDato;
                                    }
                                }
                            }
                            JOptionPane.showMessageDialog(this, "Datos modificados correctamente.");
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(this, "ID no encontrado.");
                } else {
                    JOptionPane.showMessageDialog(this, "No hay datos para modificar.");
                }
                break;

            case 3: // Ver
                if (!datos.isEmpty()) {
                    StringBuilder resultado = new StringBuilder("Datos almacenados:\n");
                    for (String[] dato : datos) {
                        resultado.append(String.join(" | ", dato)).append("\n");
                    }
                    JOptionPane.showMessageDialog(this, resultado.toString());
                } else {
                    JOptionPane.showMessageDialog(this, "No hay datos almacenados.");
                }
                break;

            default:
                JOptionPane.showMessageDialog(this, "Operación cancelada.");
        }
    }

    public static Ventana getInstance() {
        if (instance == null) {
            instance = new Ventana();
        }
        return instance;
    }

    @Override
    public void run() {
        setBounds(100, 100, 400, 500);
        setVisible(true);
    }
}