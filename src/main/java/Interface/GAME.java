package Interface;
import java.io.IOException;

public interface GAME {
    //enum STATE {MENU, SETTINGS, MAPS, GAME};

    void run() throws IOException, InterruptedException;

    void updateView() throws IOException;

    boolean getInput() throws IOException;
    //void updateView();

}
