import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Daniel Bojic
 * Date: 2020-10-22
 * Time: 09:50
 * Project: Övningsuppgift 4
 * Copyright: MIT
 */
public class Text extends JFrame {

    private JPanel p = new JPanel();
    private JTextField namn = new JTextField();
    private JButton öppna = new JButton("Öppna");
    private JButton spara = new JButton("Spara");
    private JButton sluta = new JButton("Sluta");
    private JButton skriv = new JButton("Skriv");
    private JTextArea area = new JTextArea(10, 60);
    private JScrollPane sp = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


    public Text() {
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));
        p.setLayout(new GridLayout(1, 6));
        p.add(new JLabel("Filnamn: ", JLabel.RIGHT));
        p.add(namn);
        p.add(öppna);
        p.add(spara);
        p.add(skriv);
        p.add(sluta);


        class ReadFileListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent ae) {
            läsInFil(namn.getText());
            }
        }

        class SaveFileListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                sparaFil(namn.getText());
            }
        }

        class PrintFileListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
            try{
                area.print();
            } catch (PrinterException printerException) {
                printerException.printStackTrace();
            }
            }
        }

        class ExitListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }
        namn.addActionListener(new ReadFileListener());
        öppna.addActionListener(new ReadFileListener());
        spara.addActionListener(new SaveFileListener());
        skriv.addActionListener(new PrintFileListener());
        sluta.addActionListener(new ExitListener());

        add(p, BorderLayout.NORTH);
        add(sp, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


    }

    private void läsInFil(String filnamn) {
        try (FileReader r = new FileReader(filnamn)) {
            area.read(r, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sparaFil(String filnamn) {
        try (FileWriter w = new FileWriter(filnamn)) {
            area.write(w);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
