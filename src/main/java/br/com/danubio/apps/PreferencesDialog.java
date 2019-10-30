
package br.com.danubio.apps;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class PreferencesDialog extends JDialog {
    private static final String TITLE = "Configurar Gráfico de Tartaruga";
    private ArgumentsSettings arguments;
    private final Controller controller;
    
    private JButton lineColorButton;
    SpinnerNumberModel lineWidthModel;
    private JButton backgroundColorButton;
    private JTextField heightField;
    private JTextField widthField;
    
    
    public PreferencesDialog(Frame owner, Controller controller, ArgumentsSettings arguments)
    {
        super(owner, TITLE);
        this.arguments = arguments;
        this.controller = controller;
        
        JTabbedPane preferencesTabbedPane = new JTabbedPane();
        preferencesTabbedPane.addTab("Configurar Linha", createLinePanel());
        preferencesTabbedPane.addTab("Configurar Tela", createBackgroundPanel());
        
        
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event)
            {
                saveArguments();
                PreferencesDialog.this.controller.setPreferences();
                setVisible(false);
            }
        });
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                PreferencesDialog.this.setVisible(false);
            }
        });
        
        JPanel forButtons = new JPanel();
        forButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
        forButtons.add(okButton);
        forButtons.add(cancelButton);
        
        this.add(preferencesTabbedPane);
        this.add(forButtons, BorderLayout.SOUTH);
        
        loadArguments();
    }
    
    private void loadArguments()
    {
        lineColorButton.setBackground(arguments.getLineColor());
        lineWidthModel.setValue(arguments.getLineWidth());
        backgroundColorButton.setBackground(arguments.getPanelColor());
        heightField.setText(String.valueOf(arguments.getPanelHeight()));
        widthField.setText(String.valueOf(arguments.getPanelWidth()));
    }
    
    private void saveArguments()
    {
        arguments.setLineColor(lineColorButton.getBackground());
        arguments.setLineWidth(lineWidthModel.getNumber().floatValue());
        
        arguments.setPanelColor(backgroundColorButton.getBackground());
        
        try {
            int height = Integer.parseInt(heightField.getText());
            int width = Integer.parseInt(widthField.getText());
            
            arguments.setPanelHeight(height);
            arguments.setPanelWidth(width);
        }
        catch (NumberFormatException exception)
        {
            
        }
    }
    
    private JComponent createBackgroundPanel()
    {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2, 5, 5));
        JLabel backgroundColorLabel = new JLabel("Cor de Fundo: ");
        backgroundColorButton = new JButton();
        backgroundColorButton.setBackground(arguments.getPanelColor());
        backgroundColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                JButton button = (JButton) event.getSource();
                Color color = JColorChooser.showDialog(PreferencesDialog.this.getOwner(), "Escolha uma Cor", button.getBackground());
                button.setBackground(color);
            }
        });
        
        JLabel heightLabel = new JLabel("Altura: ");
        heightField = new JTextField();
        JLabel widthLabel = new JLabel("Largura: ");
        widthField = new JTextField();
        
        mainPanel.add(backgroundColorLabel);
        mainPanel.add(backgroundColorButton);
        mainPanel.add(heightLabel);
        mainPanel.add(heightField);
        mainPanel.add(widthLabel);
        mainPanel.add(widthField);
        
        return mainPanel;
    }
    
    private JComponent createLinePanel()
    { 
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 2, 5, 5));
        JLabel lineColorLabel = new JLabel("Cor da Linha: ");
        lineColorButton = new JButton();
        lineColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                JButton button = (JButton) event.getSource();
                Color cor = JColorChooser.showDialog(PreferencesDialog.this.getOwner(), "Escolha uma Cor", button.getBackground());
                button.setBackground(cor);
            }
        });
        
        JLabel lineWidth = new JLabel("Largura da Linha: ");
        JSpinner lineSpinner = new JSpinner();
        Float value = 2.0f;
        Float min = 1.0f;
        Float max = 10.0f;
        Float step = 0.1f;
        lineWidthModel = new SpinnerNumberModel(value, min, max, step);
        lineSpinner.setModel(lineWidthModel);
        
        mainPanel.add(lineColorLabel);
        mainPanel.add(lineColorButton);
        mainPanel.add(lineWidth);
        mainPanel.add(lineSpinner);
        
        return mainPanel;
    }
}