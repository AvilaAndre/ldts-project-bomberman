package DrawingMethods;

import Structures.Position;
import com.googlecode.lanterna.graphics.TextGraphics;

public class DrawingAnimation {
    int frameIndex = 0;
    int currentFrameTick = 0;
    DrawingImage[] frames = null;
    int[] frameDuration = null;

    DrawingAnimation(DrawingImage[] images_) {
        this.frames = images_;
    }

    public void draw(TextGraphics graphics_, Position pos_, boolean boardOffset_) {
        currentFrameTick++;
        if (currentFrameTick > frameDuration[frameIndex]) {
            currentFrameTick = 0;
            frameIndex++;
            if (frameIndex >= frames.length)
                frameIndex = 0;
        }
        frames[frameIndex].draw(graphics_, pos_, boardOffset_);
    }
}
