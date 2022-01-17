package Settings;

import Audio.AudioPlayer;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class SettingsController {
    Screen screen;
    SettingsModel model;
    SettingsView view;


    public SettingsController(Screen screen_, int width_, int height_){
        this.screen = screen_;
        this.view = new SettingsView(width_, height_);
        this.screen = screen_;
    }
}
