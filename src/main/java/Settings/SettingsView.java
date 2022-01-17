package Settings;

import DrawingMethods.DrawingBlock;
import DrawingMethods.DrawingImage;
import Structures.Position;

public class SettingsView {
    int width;
    int height;
    DrawingImage viewBackground;

    public SettingsView(int width_, int height_){
        this.width = width_;
        this.height = height_;
        viewBackground = new DrawingImage(new DrawingBlock[] {new DrawingBlock(new Position(0,0), width, height, "#ffffff", null, ' ')});
}
}
