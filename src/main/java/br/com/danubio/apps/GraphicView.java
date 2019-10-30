
package br.com.danubio.apps;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;

public class GraphicView {
    
    private static String RESOURCES_IMAGES = "/images/50x50/";
    private JFrame window;
    private JPanel drawPanel;
    
    private ImageIcon turtleEnabled;
    private ImageIcon turtleDisabled;
    private JToggleButton enabledButton;
    private JTextField moveField;
    
    private Controller controller;
    
    private JDialog aboutDialog;
    private JDialog preferencesDialog;
    private ArgumentsSettings arguments;
    
    
    public GraphicView(Controller controll, ArgumentsSettings arguments)
    {
        this.controller = controll;
        this.arguments = arguments;
        this.aboutDialog = null;
        this.preferencesDialog = null;
    }
    
    public void createGraphicView()
    {
        
        drawPanel = new JPanel() {
            private Dimension preferredDimension;
            
            {
                setBackground(Color.WHITE);
                preferredDimension = new Dimension(550, 500);
            }
            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D)g;
                controller.drawTurtleGraphic(g2d);
            }
            
            public Dimension getPreferredDimension()
            {
                return preferredDimension;
            }
            
        };
        drawPanel.setMinimumSize(new Dimension(200, 200));
        drawPanel.setSize(new Dimension(500, 500));
        
        JPanel controllPanel = new JPanel();
        
        JButton leftButton = new JButton();
        leftButton.setIcon(createImageIcon(RESOURCES_IMAGES + "prev.png"));
        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
                controller.setLeft();
            }
        });
        
        enabledButton = new JToggleButton();
        turtleEnabled = createImageIcon(RESOURCES_IMAGES + "turtle_enabled_north.png");
        turtleDisabled = createImageIcon(RESOURCES_IMAGES + "turtle_disabled_north.png");
        enabledButton.setIcon(turtleDisabled);
        enabledButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event)
            {
                AbstractButton button = (AbstractButton) event.getSource();
                boolean enabled = button.isSelected();
                controller.setDraw(enabled);
            }
        });
        
        JButton rightButton = new JButton();
        rightButton.setIcon(createImageIcon(RESOURCES_IMAGES + "next.png"));
        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                controller.setRight();
            }
        });
        
        Font font = new Font("Sans Serif", Font.BOLD, 25);
        JLabel moveLabel = new JLabel("Move:");
        moveLabel.setFont(font);
        moveField = new JTextField();
        moveField.setFont(font);
        moveField.setHorizontalAlignment(JTextField.CENTER);
        
        JButton drawButton = new JButton();
        drawButton.setIcon(createImageIcon(RESOURCES_IMAGES + "draw.png"));
        drawButton.setToolTipText("Desenha uma linha.");
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    int space = Integer.parseInt(moveField.getText());
                    controller.setMove(space);
                } catch (NumberFormatException exception) {
                    
                }
            }
        });
        
        
        controllPanel.setLayout(new GridLayout(2, 3, 10, 10));
        controllPanel.add(leftButton);
        controllPanel.add(enabledButton);
        controllPanel.add(rightButton);
        controllPanel.add(moveLabel);
        controllPanel.add(moveField);
        controllPanel.add(drawButton);
        
        JMenuItem exitItem = new JMenuItem("Sair");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                System.exit(0);
            }
        });
        JMenuItem preferencesItem = new JMenuItem("Configurar Tartaruga");
        preferencesItem.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event)
           {
               showPreferencesDialog();
           }
        });
        
        JMenuItem helpItem = new JMenuItem("Sobre o Gráfico de Tartaruga");
        helpItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                showAboutDialog();
            }
        });
        
        JMenu fileMenu = new JMenu("Arquivo");
        fileMenu.add(exitItem);
        
        JMenu preferencesMenu = new JMenu("Configurações");
        preferencesMenu.add(preferencesItem);
        
        JMenu helpMenu = new JMenu("Ajuda");
        helpMenu.add(helpItem);
        
        JMenuBar mainMenuBar = new JMenuBar();
        mainMenuBar.add(fileMenu);
        mainMenuBar.add(preferencesMenu);
        mainMenuBar.add(helpMenu);
        
        window = new JFrame("Gráfico de Tartaruga");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        window.add(drawPanel, BorderLayout.CENTER);
        window.setJMenuBar(mainMenuBar);
        JPanel forControllPanel = new JPanel();
        forControllPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        forControllPanel.add(controllPanel);
        window.add(forControllPanel, BorderLayout.PAGE_END);
        
        window.setSize(600, 800);
        window.setVisible(true);
    }
    
    void setTurtleDraw(boolean enabled)
    {
        if (enabled) {
            enabledButton.setIcon(turtleEnabled);
        } else {
            enabledButton.setIcon(turtleDisabled);
        }
    }
    
    void repaint()
    {
        drawPanel.repaint();
    }
    
    void showMessage(String title, String message)
    {
        JOptionPane.showMessageDialog(window, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    void showPreferencesDialog()
    {
        if (preferencesDialog == null) {
            this.preferencesDialog = new PreferencesDialog(window, controller, arguments);
            this.preferencesDialog.pack();
        }

        this.preferencesDialog.setVisible(true);
    }
    
    void showAboutDialog()
    {
        if (aboutDialog == null) {
            this.aboutDialog = new JDialog(window);
            aboutDialog.setTitle("Sobre o Gráfico de Tartaruga");
            aboutDialog.setLayout(new BorderLayout());

            JTabbedPane aboutTabbedPane = new JTabbedPane();
            JPanel aboutTurtleGraphics = new JPanel();
            JLabel aboutTurtleLabel = new JLabel();
            aboutTurtleLabel.setText("<html><b><u>Gráfico de Tartaruga</u></b><br><br>"
              + "Versão: 1.0-SNAPSHOT<br><br>"
              + "Um simples programa para desenhar retas e quadrados.<br>"
              + "Utilize o painel de controle principal para mudar a direção da tartaruga.<br>"
              + "É possível informar um número de espaço para deslocar a tartaruga de uma posição para outra.<br><br>"
              + "Contato: <a href=mailto:jaimefritschi@gmail.com>jaimefritschi@gmail.com</a></html>");

            aboutTurtleLabel.setHorizontalAlignment(JLabel.LEFT);
            aboutTurtleGraphics.setLayout(new GridLayout(1, 1));
            aboutTurtleGraphics.add(aboutTurtleLabel);

            aboutTabbedPane.addTab("Sobre", aboutTurtleGraphics);

            JPanel aboutAutorPanel = new JPanel();
            JLabel aboutAutorLabel = new JLabel();
            aboutAutorLabel.setHorizontalTextPosition(JLabel.LEFT);
            aboutAutorLabel.setVerticalTextPosition(JLabel.NORTH);
            aboutAutorLabel.setText("<html><b><u>Jaime Eli Fritschi</u></b><br><br><br>"
              + "Relatar erros, novas funcionalidades ou entrar em contato com o desenvolvedor use o email: <br><br>"
              + "<a href=mailto:jaimefritschi@gmail.com>jaimefritschi@gmail.com</a></html>");

            aboutAutorPanel.setLayout(new GridLayout(1, 1));
            aboutAutorPanel.add(aboutAutorLabel);

            aboutTabbedPane.addTab("Sobre o Autor", aboutAutorPanel);

            JButton closeButton = new JButton("Fechar");
            closeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    aboutDialog.dispose();
                }
            });

            JPanel forCloseButton = new JPanel();
            forCloseButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
            forCloseButton.add(closeButton);

            aboutDialog.add(aboutTabbedPane, BorderLayout.CENTER);
            aboutDialog.add(forCloseButton, BorderLayout.PAGE_END);

            aboutDialog.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
            aboutDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            aboutDialog.setSize(400, 300);
        }
        
        aboutDialog.setVisible(true);
    }
    
    
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = GraphicView.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}