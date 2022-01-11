package DrawingMethods;

import Structures.Position;
import com.googlecode.lanterna.graphics.TextGraphics;

public class DrawingAnimation {
    private int frameIndex = 0;
    private int currentFrameTick = 0;
    private DrawingImage[] frames = null;
    private int[] frameDuration = null;
    private boolean paused = false;
    private boolean pauseOnEnding;
    private int size = 0;

    public DrawingAnimation(DrawingImage[] images_, int[] durations_, boolean pauseOnEnding_) {
        this.frames = images_;
        this.frameDuration = durations_;
        this.pauseOnEnding = pauseOnEnding_;
        if (!(frameDuration.length == frames.length))
            if (frameDuration.length < frames.length) {
                int[] newDurations = new int[frames.length];
                for (int i = 0; i < frames.length; i++){
                    if (i < frameDuration.length)
                        newDurations[i] = frameDuration[i];
                    else
                        newDurations[i] = frameDuration[frameDuration.length-1];
                }
                frameDuration = newDurations;
            }
        this.size = frameDuration.length-1;
    }

    public void draw(TextGraphics graphics_, Position pos_, boolean boardOffset_) {
        System.out.println(paused);
        System.out.println(currentFrameTick);
        if (frameDuration[size] == currentFrameTick && frameIndex == size && pauseOnEnding)
            paused = true;
        if (!paused)
            currentFrameTick++;
        if (currentFrameTick > frameDuration[frameIndex]) {
            currentFrameTick = 1;
            frameIndex++;
            if (frameIndex >= frames.length) {
                frameIndex = 0;
            }
        }
        frames[frameIndex].draw(graphics_, pos_, boardOffset_);
    }

    public int getCurrentIndex() {
        return this.frameIndex;
    }

    public DrawingImage getCurrentFrame() {
        return this.frames[frameIndex];
    }

    public void setPaused(boolean paused_) {
        this.paused = paused_;
    }

    public void restart() {
        frameIndex = 0;
        currentFrameTick = 0;
        paused = false;
    }
}
