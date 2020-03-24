/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ale.ice.climber.actors.objetos.mapa;

import ale.ice.climber.actors.objetos.Objeto;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author alexe
 */
public class Mapa extends Objeto{
    
    private Fixture sueloFixture;
    private Fixture bordeIzquierdo;
    private Fixture bordeDerecho;
    private Texture nieveSuperior;
    
    public Mapa(World world,Texture texture, Texture nieveSuperior){
        super(world, texture, 1024f/64f, 96f/64f);
        createBody(new Vector2(width/2,height/2));
        this.nieveSuperior = nieveSuperior;
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha){

        batch.draw(texture,0,0);
        batch.draw(nieveSuperior,0,4032);
    }
    
    @Override
    public void detach(){
        body.destroyFixture(sueloFixture);
        body.destroyFixture(bordeIzquierdo);
        body.destroyFixture(bordeDerecho);
        world.destroyBody(body);
    }
    
    @Override
    protected void createBody(Vector2 position){
        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(def);
        
        PolygonShape sueloShape = new PolygonShape();
        sueloShape.setAsBox(position.x,position.y);
        sueloFixture = body.createFixture(sueloShape,3f);
        sueloFixture.setUserData("suelo");
        sueloFixture.setFriction(0.8f);
        
        PolygonShape shapeIzquierdo = new PolygonShape();
        
        shapeIzquierdo.setAsBox(0,(4096/PIM)/2,new Vector2(-width/2,(4096/PIM)/2),0);
        bordeIzquierdo = body.createFixture(shapeIzquierdo,0.1f);
        
        PolygonShape shapeDerecho = new PolygonShape();
        
        shapeDerecho.setAsBox(0,(4096/PIM)/2,new Vector2(width/2,(4096/PIM)/2),0);
        bordeDerecho = body.createFixture(shapeDerecho,0.1f);
        
        bordeIzquierdo.setUserData("paredIzquierda");
        bordeDerecho.setUserData("paredDerecha");
        
        
        shapeDerecho.dispose();
        shapeIzquierdo.dispose();
        sueloShape.dispose();
    }
    
    
    
    
}
