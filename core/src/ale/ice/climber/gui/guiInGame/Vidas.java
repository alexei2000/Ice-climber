/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ale.ice.climber.gui.guiInGame;

import ale.ice.climber.actors.objetos.animados.jugador.Jugador;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author alexe
 */
public class Vidas extends Actor{
    
    private final Jugador jugador;
    private final Camera camera;
    private final TextureRegion texture;
    
    public Vidas(Jugador jugador,Camera camera, Texture texture){
        this.jugador = jugador;
        this.camera = camera;
        this.texture = new TextureRegion(texture,27,31);
    }
    
    @Override 
    public void draw(Batch batch, float parentAlpha){   
        for(int i = 0; i<jugador.getNumeroDeVidas(); i++){
            batch.draw(texture,50+i*60,camera.position.y + Gdx.graphics.getHeight()/3 + 60);
        }
    }
    
}
