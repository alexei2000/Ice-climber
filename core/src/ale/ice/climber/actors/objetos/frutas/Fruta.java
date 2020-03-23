package ale.ice.climber.actors.objetos.frutas;

import ale.ice.climber.actors.objetos.Objeto;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Fruta extends Objeto {

    private Fixture fixture;
    private TextureRegion texturaRecortada;
    private boolean destruido;

    public Fruta(World world, Texture texture, TextureRegion texturaRecortada, Vector2 position){
        super(world, texture,0.5f,0.5f);
        this.texturaRecortada = texturaRecortada;
        destruido = false;

        Vector2 posicionReal = new Vector2(position.x+0.5f, position.y+1);
        createBody(posicionReal);

        fixture.setUserData("fruta");
        fixture.setSensor(true);

        setPosition((body.getPosition().x - width) * PIM,(body.getPosition().y - height) * PIM);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.draw(texturaRecortada, getX(), getY());
    }

    @Override
    protected void createBody(Vector2 position) {
        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2,height/2);

        fixture = body.createFixture(shape,0.3f);

        shape.dispose();
    }

    @Override
    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }

    public void destruir(){
        destruido=true;
    }
    public boolean getDestruido(){
        return destruido;
    }

}
