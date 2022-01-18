package AudioSettings;

import Audio.AudioPlayer;
import DrawingMethods.DrawingText;
import Menu.MenuModel;
import Structures.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class AudioSettings {
    int sliderSelected;

    public void sliderUp(AudioPlayer audio) {
        if (sliderSelected == 0)
            audio.setFxVolume(audio.getFxVolume() + 0.05f, true);
        else
            audio.setMusicVolume(audio.getMusicVolume() + 0.05f);
    }

    public void sliderDown(AudioPlayer audio) {
        if (sliderSelected == 0)
            audio.setFxVolume(audio.getFxVolume() - 0.05f, true);
        else
            audio.setMusicVolume(audio.getMusicVolume() - 0.05f);
    }

    public boolean getInput(Screen screen, AudioPlayer audioPlayer, MenuModel model) throws IOException {
        KeyStroke key = screen.pollInput();
        if (key == null) return true;
        switch (key.getKeyType()) {
            case EOF:
                return false;
            case Escape:
                if (model != null)
                    model.setState(MenuModel.STATE.MAIN_MENU);
            case ArrowDown:
            case ArrowUp:
                changeSlider();
                break;
            case ArrowRight:
                sliderUp(audioPlayer);
                break;
            case ArrowLeft:
                sliderDown(audioPlayer);
                break;
        }
        return true;
    }

    public void changeSlider() {
        if (sliderSelected == 1)
            sliderSelected = 0;
        else
            sliderSelected = 1;
    }


    public void drawAudioSlider(TextGraphics graphics_, Position pos_, AudioPlayer audio_, String backgroundColor) {
        TextColor ogColor = graphics_.getBackgroundColor();
        graphics_.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));
        graphics_.fillRectangle(new TerminalPosition(pos_.getX() - 10, pos_.getY() - 5), new TerminalSize(39, 26), ' ');
        graphics_.setBackgroundColor(TextColor.Factory.fromString("#909090"));
        //musicSlider
        graphics_.fillRectangle(new TerminalPosition(pos_.getX(), pos_.getY()), new TerminalSize(22, 2), ' ');
        //fxSlider
        graphics_.fillRectangle(new TerminalPosition(pos_.getX(), pos_.getY() + 10), new TerminalSize(22, 2), ' ');
        graphics_.setBackgroundColor(TextColor.Factory.fromString("#404040"));
        graphics_.fillRectangle(new TerminalPosition(pos_.getX() + Math.round(audio_.getFxVolume() * 20), pos_.getY()-2), new TerminalSize(2, 6), ' ');
        graphics_.fillRectangle(new TerminalPosition(pos_.getX() + Math.round(audio_.getMusicVolume() * 20), pos_.getY()+8), new TerminalSize(2, 6), ' ');

        //Arrow
        graphics_.setBackgroundColor(TextColor.Factory.fromString("#db7b0f"));
        graphics_.fillRectangle(new TerminalPosition(pos_.getX()-9, pos_.getY() + 10*sliderSelected), new TerminalSize(8, 2), ' ');
        graphics_.fillRectangle(new TerminalPosition(pos_.getX()-4, pos_.getY()-2 + 10*sliderSelected), new TerminalSize(1, 6), ' ');
        graphics_.fillRectangle(new TerminalPosition(pos_.getX()-3, pos_.getY()-1 + 10*sliderSelected), new TerminalSize(1, 4), ' ');

        new DrawingText(new Position(pos_.getX()+4, pos_.getY()-4), "Sound Effects", backgroundColor, "#000000").draw(graphics_);
        new DrawingText(new Position(pos_.getX()+7, pos_.getY()+6), "Music", backgroundColor, "#000000").draw(graphics_);
        new DrawingText(new Position(pos_.getX()-1, pos_.getY()-2), "0", backgroundColor, "#000000").draw(graphics_);
        new DrawingText(new Position(pos_.getX()+22, pos_.getY()-2), "1", backgroundColor, "#000000").draw(graphics_);
        new DrawingText(new Position(pos_.getX()-1, pos_.getY()+8), "0", backgroundColor, "#000000").draw(graphics_);
        new DrawingText(new Position(pos_.getX()+22, pos_.getY()+8), "1", backgroundColor, "#000000").draw(graphics_);

        //RULES
        new DrawingText(new Position(pos_.getX()-4, pos_.getY()+15), "Press < and > to change volume", backgroundColor, "#000000").draw(graphics_);
        new DrawingText(new Position(pos_.getX()-4, pos_.getY()+16), "Press v and ^ to switch between", backgroundColor, "#000000").draw(graphics_);
        new DrawingText(new Position(pos_.getX(), pos_.getY()+17),"music and sound effects", backgroundColor, "#000000").draw(graphics_);
        new DrawingText(new Position(pos_.getX()-4, pos_.getY()+19),"Press escape to exit this menu", backgroundColor, "#000000").draw(graphics_);

        graphics_.setBackgroundColor(ogColor);
    }
}
