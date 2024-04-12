import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * StatisticsGUI provides the GUI for the statistics when button 'Statistics' is clicked. 
 *
 * @author Dilara Mukasheva K23008889
 * @version (a version number or a date)
 */
public class StatisticsGUI
{
    private JFrame frame;
    private JPanel propertyPanel;
    private JTextField numberProperty;
    private JTextField averagePrice;
    
    private PropertyViewer viewer;
    private boolean fixedSize;


    public StatisticsGUI(PropertyViewer viewer)
    {
        //currentProperty = null;
        this.viewer = viewer;
        fixedSize = false;
        makeFrame();
        this.setPropertyViewSize(400, 250);
    }

    public void setPropertyViewSize(int width, int height)
    {
        propertyPanel.setPreferredSize(new Dimension(width, height));
        frame.pack();
        fixedSize = true;
    }
    
    private void makeFrame()
    {     
        frame = new JFrame("Statistics");
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));

        propertyPanel = new JPanel();
        propertyPanel.setLayout(new GridLayout(6,2));
        
        // Specify the layout manager with nice spacing
        contentPane.setLayout(new BorderLayout(6, 6));
        contentPane.add(propertyPanel, BorderLayout.CENTER);
        
        //dipslays both the properties for the challenge task
        propertyPanel.add(new JLabel("Number of properties viewed: "));
        numberProperty = new JTextField(String.valueOf(viewer.getNumberOfPropertiesViewed()));
        numberProperty.setEditable(false);
        propertyPanel.add(numberProperty);
        
        propertyPanel.add(new JLabel("Average price of all properties viewed: "));
        averagePrice = new JTextField("£"+ viewer.averagePropertyPrice());
        averagePrice.setEditable(false);
        propertyPanel.add(averagePrice);
        
        // building is done - arrange the components     
        frame.pack();
        
        // place the frame at the center of the screen and show
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
        frame.setVisible(true);
    }
    
}
