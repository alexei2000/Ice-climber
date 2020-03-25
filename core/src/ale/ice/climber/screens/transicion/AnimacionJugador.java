package ale.ice.climber.screens.transicion;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AnimacionJugador extends Actor {

    private TextureRegion frame;
    private Animation <TextureRegion> animation;
    private float velocidad;

    private float duracion;
    private boolean puedeCaminar;
    private boolean band;

    public AnimacionJugador(Texture textura) {

        velocidad = 200;
        duracion = 0;
        puedeCaminar = true;
        band = true;

        TextureRegion skin = new TextureRegion(textura,560,104);
        TextureRegion[][] frames = skin.split(70, 104);

        animation = new Animation<>(0.08f,frames[0]);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        duracion += delta;
        if(puedeCaminar){
            frame = animation.getKeyFrame(duracion, true);
            setPosition(getX() + velocidad*delta, 70);
            if(getX()>=477 && band){
                puedeCaminar = false;
            }
        }
        else{
            frame = animation.getKeyFrame(0, true);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(frame,getX(),getY());
    }

    public void puedeCaminar(){
        duracion = 0;
        puedeCaminar = true;
        band = false;
    }

    public boolean getPuedeCaminar(){
        return puedeCaminar;
    }
}
