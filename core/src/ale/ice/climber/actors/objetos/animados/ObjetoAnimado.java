/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ale.ice.climber.actors.objetos.animados;

import ale.ice.climber.actors.objetos.Objeto;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author alexe
 */
public abstract class ObjetoAnimado extends Objeto{
    
    protected boolean vivo, volteado, flipFixture;
    protected int cambiarJugadorPosion;
    protected int numeroDeAnimaciones;
    protected float velocidadDeAnimacion;
    
    protected TextureRegion[][] frames;
    protected TextureRegion frame;
    protected Fixture fixtureHead;
    protected Fixture fixtureBody;
    
    protected Animation animation;
    protected float duracion;
    
    public ObjetoAnimado(World world, Texture texture, float width, float height,Vector2 position) {
        super(world, texture, width, height); 
     
        cambiarJugadorPosion=0;
        vivo = true;
        volteado = false;
        flipFixture = false;
        duracion=0;
        
        position.x = position.x+width/2f;
        position.y = position.y+height/2f;
        createBody(position);
    }
    
    @Override
    public void act(float delta){
        moverse(delta);
        transportarEntidad();
        setPosition((body.getPosition().x - width/2) * PIM,(body.getPosition().y -height/4) * PIM);
        animationUpdate();
        
    }
    
    @Override 
    public void draw(Batch batch, float parentAlpha){
        batch.draw(frame,getX(),getY());
    }
    
    @Override
    public void detach() {
        body.destroyFixture(fixtureHead);
        body.destroyFixture(fixtureBody);
        world.destroyBody(body);  
    }
    
    @Override
    protected void createBody(Vector2 position) {
        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        def.fixedRotation = true;
        body = world.createBody(def);
        createFixture();
    }
    
    protected void voltear(){  
        for(int i=0;i<numeroDeAnimaciones;i++){
            for(int j=0; j<8;j++){
                frames[i][j].flip(true, false);
            }
        }
        
        body.destroyFixture(fixtureHead);
        body.destroyFixture(fixtureBody);
        createFixture();
    }
    
    public void transportarEntidad(){
        if(cambiarJugadorPosion==1){
            body.linVelLoc.x=body.linVelLoc.x*-1;
            body.setTransform(new Vector2(width/2,body.getPosition().y),0); 
        }
        else if(cambiarJugadorPosion==2){
            body.linVelLoc.x=body.linVelLoc.x*-1;
            body.setTransform(new Vector2(15+width/2,body.getPosition().y),0);
        }
        cambiarJugadorPosion=0;
    }
    
    public void setCambiarEntidadPosicion(int p){
        this.cambiarJugadorPosion = p;
    }
    
    protected abstract void animationUpdate();
    
    protected abstract void createFixture();
    
    protected abstract void moverse(float delta);
    
    protected abstract void moveRight();
    
    protected abstract void moveLeft();

    
    
}
