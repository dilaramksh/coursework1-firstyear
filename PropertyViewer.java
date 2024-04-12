import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;


/**
 * This project implements a simple application. Properties from a fixed
 * file can be displayed. 
 * 
 * @author Dilara Mukasheva K23008889
 * @version (a version number or a date)
 * 
 * @author Michael Kölling and Josh Murphy
 * @version 1.0
 */
public class PropertyViewer
{    
    private PropertyViewerGUI gui;     // the Graphical User Interface
    private StatisticsGUI stats;
    private Portfolio portfolio;
    //this is the index of the current property dipslayed
    int currentIndex = 0; 
    //array of all the prices of properties viewed
    ArrayList propertiesViewed = new ArrayList<Integer>(); 
    //is the total sum of all the prices in the propertiesViewed ArrayList 
    int sum = 0; 

    /**
     * Create a PropertyViewer and display its GUI on screen.
     */
    public PropertyViewer()
    {
        gui = new PropertyViewerGUI(this);
        portfolio = new Portfolio("airbnb-london.csv");

        showPropertyInfo();
    }

    /**
     * This method makes the 'next' button more interactive via displaying properties that 
     * are listed after the current property displayed, or if it is the last property it 
     * loops it to the beginning of the list of properties to the first.
     */
    public void nextProperty()
    {
        if (currentIndex >= 0 && currentIndex < (portfolio.numberOfProperties()-1)){
           currentIndex ++;
           showPropertyInfo(); 
        }
        else{
            currentIndex = 0; 
            //resets the index back to 0, to create a carousel effect
            showPropertyInfo(); 
        }
        
    }

    /**
     * This method makes the 'previous' button more interactive via displaying properties that 
     * are listed before the current property displayed, or if it is the first property it 
     * loops it to the end of the list of properties to the last.
     */
    public void previousProperty()
    {
        if (currentIndex > 0 && currentIndex < portfolio.numberOfProperties()){
           currentIndex --;
           showPropertyInfo();  
        }
        else{
            currentIndex = portfolio.numberOfProperties()-1; 
            //makes the index to the biggest number, to create a carousel effect
            showPropertyInfo(); 
        }
    }

    /**
     * I realised that I reused the same bit of code over and over again, 
     * so I decided to create this method 
     */
    public void showPropertyInfo(){
        propertiesViewed.add(portfolio.getProperty(currentIndex).getPrice());
        sum = sum + portfolio.getProperty(currentIndex).getPrice();
        gui.showProperty(portfolio.getProperty(currentIndex));
        gui.showID(portfolio.getProperty(currentIndex));
        gui.showFavourite(portfolio.getProperty(currentIndex)); 
    }
    
    /**
     * This method favourites the properties and displays it in the GUI, if clicked again it 
     * removes them from favourites
     */
    public void toggleFavourite()
    {
        portfolio.getProperty(currentIndex).toggleFavourite();
        gui.showFavourite(portfolio.getProperty(currentIndex));
    }
    //----- methods for challenge tasks -----
    /**
     * This method opens the system's default internet browser
     * The Google maps page should show the current properties location on the map.
     */
    public void viewMap() throws Exception
    {
       double latitude = portfolio.getProperty(currentIndex).getLatitude();
       double longitude = portfolio.getProperty(currentIndex).getLongitude();
       
       URI uri = new URI("https://www.google.com/maps/place/" + latitude + "," + longitude);
       java.awt.Desktop.getDesktop().browse(uri); 
    }
    
    /**
     * This method calculates how many properties the user has seen, this includes the same 
     * property that was viewed multiple times.
     */
    public int getNumberOfPropertiesViewed()
    {
        return propertiesViewed.size();
    }
    
    /**
     * This method calculates the average property prices of all properties that were viewed. 
     * This inludes the same property that was viewed multiple times. All properties' prices
     * that are viewed are stored in an array declared at the start.
     */
    public int averagePropertyPrice()
    {
        int average = sum/propertiesViewed.size();
        return average; 
    }
    
    /**
     * This method is called in the propertyViewerGUI which opens a new, additional JFrame 
     * displaying the statistics
     */
    public void toggleStats(){
        stats = new StatisticsGUI(this);
    }
}
