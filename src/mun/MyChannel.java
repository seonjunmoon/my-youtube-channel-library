package mun;

import javafx.application.Application;
import munChannelData.ChannelDataGui;

/**
 * @author Seonjun Mun
     * @version 09/23/2019
 */
public class MyChannel {

    /**
     * The main method for MyChannel, based on my channel (youtube.com/seonjunmoon).
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //launch the application
        Application.launch(ChannelDataGui.class, args);
    }
}
