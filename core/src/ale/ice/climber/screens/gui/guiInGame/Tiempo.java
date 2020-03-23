package ale.ice.climber.screens.gui.guiInGame;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class Tiempo extends Label {

    private float value;
    private Camera camera;

    public Tiempo(CharSequence text, Skin skin, Camera camera) {
        super(text, skin,"dark");

        this.value = 0;
        this.camera = camera;
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        value += delta;
        setPosition(850, camera.position.y + Gdx.graphics.getHeight()/3 + 60);
        setText("Tiempo: "+(int)value+"s");

    }

    public int getValue() {
        return (int)value;
    }
}
