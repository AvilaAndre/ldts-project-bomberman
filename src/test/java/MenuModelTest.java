import Menu.MenuModel;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuModelTest {
    @Test
    public void playerColorChangeTest() throws IOException {

        DefaultTerminalFactory factory = new DefaultTerminalFactory();
        //String[] playerColors = {"#58b06c", "#1c5888", "#a03c50", "#dbcd0f", "#db7b0f"};

        MenuModel model = new MenuModel(factory.createScreen(), 50, 50);
        model.playerJoin(1);
        model.playerNextColor(1);
        assertEquals("#1c5888", model.getPlayers()[0]);


        model = new MenuModel(factory.createScreen(), 50, 50);
        model.playerJoin(1);
        model.playerJoin(2);
        model.playerJoin(3);
        model.playerJoin(4);
        model.playerNextColor(1);
        assertEquals("#db7b0f", model.getPlayers()[0]);


        model = new MenuModel(factory.createScreen(), 50, 50);
        model.playerJoin(1);
        model.playerJoin(2);
        model.playerJoin(3);
        model.playerJoin(4);
        model.playerNextColor(1);
        model.playerNextColor(1);
        assertEquals("#58b06c", model.getPlayers()[0]);


        model = new MenuModel(factory.createScreen(), 50, 50);
        model.playerJoin(1);
        model.playerJoin(2);
        model.playerJoin(3);
        model.playerNextColor(1);
        assertEquals("#dbcd0f", model.getPlayers()[0]);


        model = new MenuModel(factory.createScreen(), 50, 50);
        model.playerJoin(1);
        model.playerPreviousColor(1);
        assertEquals("#db7b0f", model.getPlayers()[0]);


        model = new MenuModel(factory.createScreen(), 50, 50);
        model.playerJoin(1);
        model.playerJoin(2);
        model.playerJoin(3);
        model.playerJoin(4);
        model.playerPreviousColor(1);
        assertEquals("#db7b0f", model.getPlayers()[0]);


        model = new MenuModel(factory.createScreen(), 50, 50);
        model.playerJoin(1);
        model.playerJoin(2);
        model.playerJoin(3);
        model.playerJoin(4);
        model.playerPreviousColor(1);
        model.playerPreviousColor(1);
        assertEquals("#58b06c", model.getPlayers()[0]);


        model = new MenuModel(factory.createScreen(), 50, 50);
        model.playerJoin(1);
        model.playerJoin(2);
        model.playerJoin(3);
        model.playerPreviousColor(1);
        assertEquals("#db7b0f", model.getPlayers()[0]);
    }
}