/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ale.ice.climber.actors.objetos.animados.enemigos;

import ale.ice.climber.actors.objetos.animados.ObjetoAnimado;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author alexe
 */
public abstract class Enemigo extends ObjetoAnimado {
    
    protected boolean direccion;

    public Enemigo(World world, Texture texture,Vector2 position, float width, float height) {
        super(world, texture, width, height, position);
        numeroDeAnimaciones = 1;
        velocidadDeAnimacion = 0.17f;
        
    }

    @Override
    protected void animationUpdate() {
        frame = (TextureRegion)animation.getKeyFrame(duracion,true);

    }
    
    @Override
    protected void moverse(float delta) {
        duracion+=delta;
        if(body.getLinearVelocity().x==0){
            cambiarDireccion();
        }
        if(direccion && !estaEnElAire()){
            moveLeft();
        }
        else if(!estaEnElAire()){
            moveRight();
        }
    }

    @Override
    protected void moveRight() {
       body.setLinearVelocity(2,0);
    }

    @Override
    protected void moveLeft() {
       body.setLinearVelocity(-2,0);
    }
    
    public Body getBody(){
        return body;
    }
    
    public void cambiarDireccion(){
        direccion=!direccion;
        voltear();
    }
}
