package br.com.danubio.apps;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class App 
{
    public static void main( String[] args )
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GraphicController controller = new GraphicController();
            }
        });
    }
}
