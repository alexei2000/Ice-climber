/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ale.ice.climber.actors.objetos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author alexe
 */
public class Puerta extends Objeto{

    private final TextureRegion texturePuerta;
    private final Vector2 position;
    private Fixture fixture;
    
    public Puerta(World world,Texture texture, Vector2 position,String name){
        super(world, texture,1.71875f,2);
        
        this.position = new Vector2(position.x+width/2f,position.y+height/2f);
        
        texturePuerta = new TextureRegion(texture,110, 128);
        createBody(this.position);
        
        fixture.setUserData(name);
        fixture.setSensor(true);
        
        setPosition((body.getPosition().x - width/2) * PIM,(body.getPosition().y -height/2) * PIM);
        
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        
        batch.draw(texturePuerta, getX(), getY());
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
}
