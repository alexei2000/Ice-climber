/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ale.ice.climber.actors.objetos.mapa;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author alexe
 */
public class Nieve extends Actor {
    
    private final TextureRegion nieveTexture;
    
    public Nieve(Texture texture){
        this.nieveTexture = new TextureRegion(texture);
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha){
        drawNieve(batch);
    }
    
    public void drawNieve(Batch batch){
        for(int i=0;i<8;i++){

            if(i==0){
                nieveTexture.flip(true, false);
                batch.draw(nieveTexture,896,i*240+96);
                nieveTexture.flip(true, false);
                batch.draw(nieveTexture,0,i*240+96);
            }
            else{
                nieveTexture.flip(true, false);
                batch.draw(nieveTexture,896,i*256);
                nieveTexture.flip(true, false);
                batch.draw(nieveTexture,0,i*256);
            }

        }
        
    }
}
