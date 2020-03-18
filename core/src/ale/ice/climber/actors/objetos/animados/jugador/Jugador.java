/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ale.ice.climber.actors.objetos.animados.jugador;

import ale.ice.climber.actors.objetos.animados.ObjetoAnimado;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author alexe
 */
public class Jugador extends ObjetoAnimado {
    
    private boolean estaEnElSuelo;
    private int estadoDeAnimacion;
    
    
    public Jugador(World world, Texture texture,Vector2 position) {
        super(world, texture, 0.640625f,1.625f,position);
        
        estaEnElSuelo=true;
        numeroDeAnimaciones=3;
        velocidadDeAnimacion = 0.17f;
        estadoDeAnimacion=0;
        
        TextureRegion skin = new TextureRegion(texture,560,312);
        frames = skin.split(70, 104);//falta
        animation = new Animation(0.08f,frames[0]);
        
    }
    
    
    public void setAnimation(int state){
        switch (state) {
            case 0:
                animation = new Animation(velocidadDeAnimacion,frames[0]);
                break;
            case 1:
                animation = new Animation(0.03f,frames[1]);
                break;
            case 2:
                animation = new Animation(0.04f,frames[2]);
                break;
            default:
                break;
        }
        estadoDeAnimacion=state;
    }
    
    @Override
    protected void animationUpdate(){
        switch(estadoDeAnimacion){
            case 0: frame = (TextureRegion)animation.getKeyFrame(duracion, true); break;
            case 1: frame = (TextureRegion)animation.getKeyFrame(duracion, false); break;
            case 2: frame = (TextureRegion)animation.getKeyFrame(duracion, false);break;
        }
    }
       
    @Override
    protected void createFixture(){
          
        PolygonShape figureBody = new PolygonShape();
        CircleShape circle = new CircleShape();
        Vector2[] vertices = new Vector2[5];
        
            
        if(!flipFixture){
            
            circle.setRadius(width/2+0.03f);
            circle.setPosition(new Vector2(0.01f,height/2-0.1f));
    
            vertices[0] = new Vector2(-width/4+0.15f,height/4);
            vertices[1] = new Vector2(-width/4,0);
            vertices[2] = new Vector2(0.1f,-height/4);
            vertices[3] = new Vector2(width-0.05f,-height/4);
            vertices[4] = new Vector2(width/2+0.1f,height/4);
            
            
        }
        else{
            circle.setRadius(width/2+0.03f);
            circle.setPosition(new Vector2(0.45f,height/2-0.1f));
            
            vertices[0] = new Vector2(-(-width/4+0.15f),height/4);//1
            vertices[1] = new Vector2(-width/4,-height/4);//2
            vertices[2] = new Vector2(width/2+0.05f,-height/4);//3
            vertices[3] = new Vector2(width,0);//4
            vertices[4] = new Vector2(width/2+0.15f,height/4);//5
        }
        
        
        figureBody.set(vertices);
        fixtureBody = body.createFixture(figureBody,3f);
        fixtureHead = body.createFixture(circle,3f);
        
        fixtureBody.setUserData("pies");
        fixtureHead.setUserData("cabeza"); 
        
        circle.dispose();
        figureBody.dispose();
        
    }
    
    @Override
    protected void moverse(float delta){
        //moverse hacia los lados
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            moveRight();
            duracion+=delta;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            moveLeft();
            duracion+=delta;
        }
        //saltar
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                saltar();
        }
        
        //tiempo para las animaciones
        if(!Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT) && estaEnElSuelo){
            duracion=0;
        }
        else{
            duracion+=delta;
        }
    
}
    
    @Override
    protected void moveRight(){
        if(!estaEnElAire()){
            body.setLinearVelocity(5,0);
        }
        else{
            body.applyLinearImpulse(new Vector2(0.13f,0),body.getPosition(),true);
        }
        if(volteado){
            flipFixture = false;
            voltear();
        }
        volteado=false;
    }
    
    @Override
    protected void moveLeft(){
        if(!estaEnElAire()){
            body.setLinearVelocity(-5,0);
        }
        else{
            body.applyLinearImpulse(new Vector2(-0.13f,0),body.getPosition(),true);
        }
        if(!volteado){
            flipFixture = true;
            voltear();
        }
        volteado=true;
    }
    
    @Override
    protected void voltear(){
        super.voltear();
        body.destroyFixture(fixtureHead);
        body.destroyFixture(fixtureBody);
        createFixture();
    }
    
    private void saltar(){
        if(!estaEnElAire() && estaEnElSuelo){
            setAnimation(2);
            body.applyLinearImpulse(new Vector2(0,36),body.getPosition(),true);
            estaEnElSuelo=false;
        }    
    }
    
    public void setEstaEnElSuelo(boolean b){
        estaEnElSuelo = b;
    }
    
    
}
