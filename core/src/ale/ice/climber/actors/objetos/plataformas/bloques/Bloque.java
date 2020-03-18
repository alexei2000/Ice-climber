/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ale.ice.climber.actors.objetos.plataformas.bloques;

import ale.ice.climber.actors.objetos.Objeto;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author alexe
 */
public abstract class Bloque extends Objeto {
    
    protected Fixture fixtureInferior;
    protected Fixture fixtureSuperior;
    protected Fixture fixtureDerecho;
    protected Fixture fixtureIzquierdo;
    protected boolean destruido;

    public Bloque(World world, Texture texture,Vector2 position) {
        super(world, texture, 1f, 0.5f);
        destruido=false;
        
        position.x = position.x+width/2;
        position.y = position.y+height/2;
        createBody(position);
        setNameFixture();
        
        
    }
    
    @Override
    public void act(float delta){
        setPosition((body.getPosition().x - width/2) * PIM,(body.getPosition().y -height/2) * PIM);
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha){
        
        batch.draw(texture,getX(),getY());
    }

    @Override
    public void detach() {
        body.destroyFixture(fixtureSuperior);
        body.destroyFixture(fixtureInferior);
        body.destroyFixture(fixtureDerecho);
        body.destroyFixture(fixtureIzquierdo);
        world.destroyBody(body);
    }
    
    @Override
    protected void createBody(Vector2 position) {
        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(def);
        
        EdgeShape sup = new EdgeShape();
        sup.set(-width/2,height/2,width/2,height/2);
        fixtureSuperior = body.createFixture(sup,0.1f);
        
        EdgeShape izquierdo = new EdgeShape();
        izquierdo.set(-width/2,height/2,-width/2,-height/2);
        fixtureIzquierdo = body.createFixture(izquierdo,0.1f);
        
        EdgeShape inf = new EdgeShape();
        inf.set(-width/2,-height/2,width/2,-height/2);
        fixtureInferior = body.createFixture(inf,0.1f);
        
        EdgeShape derecho = new EdgeShape();
        derecho.set(width/2,height/2,width/2,-height/2);
        fixtureDerecho = body.createFixture(derecho,0.1f);
        
        fixtureDerecho.setFriction(0.8f);
        fixtureInferior.setFriction(0.8f);
        fixtureIzquierdo.setFriction(0.8f);
        fixtureSuperior.setFriction(5);
                
        sup.dispose();
        inf.dispose();
        derecho.dispose();
        izquierdo.dispose();
    }
    
    public void destruir(){
        destruido=true;
    }
    public boolean getDestruido(){
        return destruido;
    }
    
    protected abstract void setNameFixture();
 
    
}
