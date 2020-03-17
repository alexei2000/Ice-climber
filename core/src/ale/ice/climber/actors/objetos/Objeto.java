/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ale.ice.climber.actors.objetos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author alexe
 */
public abstract class Objeto extends Actor {
    
    public static final float PIM = 64f;//pixeles por metros
    protected final float height;
    protected final float width;
    
    protected final World world;
    protected Body body;
    protected final Texture texture;
    
    public Objeto(World world,Texture texture,float width,float height){
        this.width = width;
        this.height = height;
        this.world = world;
        this.texture= texture;
        setSize(PIM*width,PIM*height);   
    }
    
    public void textureDispose(){
        texture.dispose();
    }
    
    protected abstract void createBody(Vector2 position);
    
    public abstract void detach();
}
