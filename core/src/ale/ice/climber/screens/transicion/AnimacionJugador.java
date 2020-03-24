package ale.ice.climber.screens.transicion;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AnimacionJugador extends Actor {

    TextureRegion[][] frames;
    Animation <TextureRegion> animation;

    public AnimacionJugador(Texture textura) {
        TextureRegion skin = new TextureRegion(textura,560,104);
        frames = skin.split(70, 104);

        animation = new Animation<>(0.08f,frames[0]);
    }
}
