package exemple;

import exemple.Window.MainFrame;

import javax.mail.MessagingException;

public class App {

    //

    public static void main( String[] args ) throws MessagingException {

        MainFrame mainFrame = new MainFrame();
        mainFrame.mainFrame();

        //send.send("Test", "That is a test messege from my server.", "admin@avmid.com", "vladislav.postoyan@yandex.ru");

    }
}
