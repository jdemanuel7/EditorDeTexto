package Clases;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.text.*;

public class GuiaGrafica extends JFrame implements ActionListener {
    private MenuBar mbar;
    private Menu file, edit, format, font, font1, font2, colorMenu;
    private MenuItem item1, item2, item3, item4;
    private MenuItem item5, item6, item7, item8, item9, item10;
    private MenuItem fname1, fname2, fname3, fname4;
    private MenuItem fstyle1, fstyle2, fstyle3, fstyle4, fstyle5;
    private MenuItem fsize1, fsize2, fsize3, fsize4;
    private MenuItem colorBlack, colorRed, colorBlue, colorGreen;

    private JPanel panelPrincipal;
    private JTextPane text;

    private Font f;

    private String commando = "";
    private String str = "";
    private String str1 = " ", str2 = "", str3 = "";
    private String str4 = "";
    private String str6 = " ";
    private String str7 = " ", str8 = " ", str9 = " ";

    private int len1;
    private int i = 0;
    private int pos1;
    private int len;
    private JButton button1;
    private JButton button2;
    private JTextPane textPane1;

    public GuiaGrafica(String str) {
        super(str);
        panelPrincipal = new JPanel();
        panelPrincipal = (JPanel) getContentPane();   // devuelve el contenedor principal donde se colocan los componentes de la interfaz gráfica
        panelPrincipal.setLayout(new BorderLayout());  // organiza los componentes

        mbar = new MenuBar();          //barra de menú en una interfaz gráfica
        setMenuBar(mbar);                //establece la barra del menu

        file = new Menu("Archivo");
        edit = new Menu("Edición");
        format = new Menu("Formato");
        font = new Menu("Fuente");
        font1 = new Menu("Estilo de fuente");
        font2 = new Menu("Tamaño");
        colorMenu = new Menu("Color");

        file.add((item1 = new MenuItem("Nuevo...")));
        file.add((item2 = new MenuItem("Abrir")));
        file.add((item3 = new MenuItem("Guardar como")));
        file.addSeparator();  //separador visual
        file.add((item4 = new MenuItem("Salir")));
        mbar.add(file);

        edit.add(item5 = new MenuItem("Cortar"));
        edit.add(item6 = new MenuItem("Copiar"));
        edit.add(item7 = new MenuItem("Pegar"));
        edit.add(item8 = new MenuItem("Borrar"));
        edit.addSeparator();
        edit.add(item9 = new MenuItem("Seleccionar Todo"));
        edit.add(item10 = new MenuItem("Fecha/Hora"));

        format.add(font);
        format.add(font1);
        format.add(font2);
        format.add(colorMenu);
        //ELEMENTOS MENU LETRA
        font.add(fname1 = new MenuItem("Courier"));
        font.add(fname2 = new MenuItem("Sans Serif"));
        font.add(fname3 = new MenuItem("Monoespaciado"));
        font.add(fname4 = new MenuItem("Symbol"));
        //ELEMENTOS MENU FUENTE
        font1.add(fstyle1 = new MenuItem("Regular"));
        font1.add(fstyle2 = new MenuItem("Negrita"));
        font1.add(fstyle3 = new MenuItem("Cursiva"));
        font1.add(fstyle4 = new MenuItem("Negrita Cursiva"));
        font1.add(fstyle5 = new MenuItem("Subrayado")); // Opción de subrayado
        //ELEMENTOS MENU TAMAÑO
        font2.add(fsize1 = new MenuItem("12"));
        font2.add(fsize2 = new MenuItem("14"));
        font2.add(fsize3 = new MenuItem("18"));
        font2.add(fsize4 = new MenuItem("20"));
        //ELEMENTOS MENU COLOR
        colorMenu.add(colorBlack = new MenuItem("Negro"));
        colorMenu.add(colorRed = new MenuItem("Rojo"));
        colorMenu.add(colorBlue = new MenuItem("Azul"));
        colorMenu.add(colorGreen = new MenuItem("Verde"));

        mbar.add(edit);
        mbar.add(format);

        item1.addActionListener(this);
        item2.addActionListener(this);
        item3.addActionListener(this);
        item4.addActionListener(this);
        item5.addActionListener(this);
        item6.addActionListener(this);
        item7.addActionListener(this);
        item8.addActionListener(this);
        item9.addActionListener(this);
        item10.addActionListener(this);
        fname1.addActionListener(this);
        fname2.addActionListener(this);
        fname3.addActionListener(this);
        fname4.addActionListener(this);
        fsize1.addActionListener(this);
        fsize2.addActionListener(this);
        fsize3.addActionListener(this);
        fsize4.addActionListener(this);
        fstyle1.addActionListener(new FontStyleListener());
        fstyle2.addActionListener(new FontStyleListener());
        fstyle3.addActionListener(new FontStyleListener());
        fstyle4.addActionListener(new FontStyleListener());
        fstyle5.addActionListener(new FontStyleListener()); // ActionListener para subrayado
        colorBlack.addActionListener(this);
        colorRed.addActionListener(this);
        colorBlue.addActionListener(this);
        colorGreen.addActionListener(this);

        text = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(text);  //desplegables
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);  //centrar el panel

        f = new Font("Cursiva", Font.PLAIN, 15);  //establecer la fuente
        text.setFont(f);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        commando = e.getActionCommand();  //Asigna la accion a comando

        if (commando.equals("Nuevo...")) {
            GuiaGrafica nota1 = new GuiaGrafica("Archivo");
            nota1.setSize(500, 500);
            nota1.setVisible(true);
            nota1.text.requestFocus();  //Resaltar visualmente
        } else if (commando.equals("Abrir")) {
            str4 = "";  //Guardar la cadena de texto
            FileDialog dialogo = new FileDialog(this, "Abrir");
            dialogo.setVisible(true);

            str1 = dialogo.getDirectory();
            str2 = dialogo.getFile();
            str3 = str1 + str2;
            try {
                File f = new File(str3);
                FileInputStream fobj = new FileInputStream(f);
                len = (int) f.length();   //longitud de archivo
                for (int j = 0; j < len; j++) {  //bucle para leer el tamaño
                    char str5 = (char) fobj.read();  //se lee y se convierte en caracteres
                    str4 = str4 + str5;
                }
            } catch (IOException exception) {
                exception.printStackTrace();  //Excepcion para ver donde ocurrio el error
            }
        } else if (commando.equals("Guardar como")) {
            FileDialog dialogo1 = new FileDialog(this, "Guardar como", FileDialog.SAVE);
            dialogo1.setVisible(true);
            str7 = dialogo1.getDirectory();

            str8 = dialogo1.getFile();
            str9 = str7 + str8;

            str6 = text.getText();
            len1 = str6.length();
            byte buf[] = str6.getBytes();  //matriz de bytes que representa los caracteres en la cadena 

            try {
                File file1 = new File(str9);
                FileOutputStream fobjl1 = new FileOutputStream(file1);  //escribir bytes en el archivo representado por el objeto file1
                for (int k = 0; k < len1; k++) {   //bucle
                    fobjl1.write(buf[k]);
                }
                fobjl1.close();
                this.setTitle(str8);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } else if (commando.equals("Salir")) {
            System.exit(0);
        } else if (commando.equals("Copiar")) {
            str = text.getSelectedText();
        } else if (commando.equals("Pegar")) {
            pos1 = text.getCaretPosition();
            text.replaceSelection(str);
        } else if (commando.equals("Borrar")) {
            String msg = text.getSelectedText();
            i = text.getText().indexOf(msg);  //obtener el texto
            text.replaceSelection(str);
        } else if (commando.equals("Seleccionar Todo")) {
            String seleccionarTodo = text.getText();
            int strLen = seleccionarTodo.length();
            text.select(0, strLen);
        } else if (commando.equals("12") || commando.equals("14") || commando.equals("18") || commando.equals("20")) {
            changeFontSize(commando);
        } else if (commando.equals("Courier") || commando.equals("Sans Serif") || commando.equals("Monoespaciado") || commando.equals("Symbol")) {
            changeFont(commando);
        } else if (commando.equals("Negro") || commando.equals("Rojo") || commando.equals("Azul") || commando.equals("Verde")) {
            cambiarColor(commando);
        }
    }

    private void changeFontSize(String size) {
        int start = text.getSelectionStart();
        int end = text.getSelectionEnd();
        StyledDocument doc = text.getStyledDocument();  //acceder y manipular directamente el contenido del texto 
        Style style = doc.addStyle("CustomStyle", null);  //Aplicar formato de letra al documento
        int fontSize = Integer.parseInt(size);

        StyleConstants.setFontSize(style, fontSize);  //especificar el tamaño de la letra

        doc.setCharacterAttributes(start, end - start, style, false);  //estilos a porciones específicas de texto
    }

    private void changeFont(String fontName) {
        int start = text.getSelectionStart();
        int end = text.getSelectionEnd();
        StyledDocument doc = text.getStyledDocument();
        Style style = doc.addStyle("CustomStyle", null);

        StyleConstants.setFontFamily(style, fontName);

        doc.setCharacterAttributes(start, end - start, style, false);
    }

    private void cambiarColor(String color) {
        Color fontColor;
        switch (color) {
            case "Negro":
                fontColor = Color.BLACK;
                break;
            case "Rojo":
                fontColor = Color.RED;
                break;
            case "Azul":
                fontColor = Color.BLUE;
                break;
            case "Verde":
                fontColor = Color.GREEN;
                break;
            default:
                fontColor = Color.BLACK; // Color por defecto
        }

        int start = text.getSelectionStart();
        int end = text.getSelectionEnd();
        StyledDocument doc = text.getStyledDocument();
        Style style = doc.addStyle("CustomStyle", null);

        StyleConstants.setForeground(style, fontColor);  //establecer el color a la letra

        doc.setCharacterAttributes(start, end - start, style, false);
    }

    private void setUnderline(boolean underline) {
        int start = text.getSelectionStart();
        int end = text.getSelectionEnd();
        StyledDocument doc = text.getStyledDocument();
        Style style = doc.addStyle("CustomStyle", null);

        StyleConstants.setUnderline(style, underline);

        doc.setCharacterAttributes(start, end - start, style, false);
    }

    private class FontStyleListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int start = text.getSelectionStart();
            int end = text.getSelectionEnd();
            StyledDocument doc = text.getStyledDocument();
            Style style = doc.addStyle("CustomStyle", null);  //Crear estilo de letra
            String command = e.getActionCommand();

            if (command.equals("Negrita")) {
                StyleConstants.setBold(style, true);
            } else if (command.equals("Cursiva")) {
                StyleConstants.setItalic(style, true);
            } else if (command.equals("Regular")) {
                StyleConstants.setBold(style, false);
                StyleConstants.setItalic(style, false);
            } else if (command.equals("Subrayado")) {
                setUnderline(true);
                return;
            }

            doc.setCharacterAttributes(start, end - start, style, false);
        }
    }

    public static void main(String[] args) {
        GuiaGrafica nota = new GuiaGrafica("Nuevo Bloc");
        nota.setSize(600, 600);
        nota.setVisible(true);
    }
}

