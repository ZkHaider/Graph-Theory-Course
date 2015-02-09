package Graph;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class BasicStrokes extends JFrame{
	
	public BasicStrokes() {

        initUI();
	}
    
    private void initUI() {
        
        setTitle("Basic strokes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new GraphicalProgram());

        setSize(280, 270);
        setLocationRelativeTo(null);        
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                BasicStrokes bs = new BasicStrokes();
                bs.setVisible(true);
            }
        });
    }
	
}

