/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ale.ice.climber.screens.gui.guiInMenu.fondo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author alexe
 */
public class FondoMenu extends Actor {
    
    private final TextureRegion texture;
    
    public FondoMenu(Texture texture){
        this.texture = new TextureRegion(texture,1024,600);
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha){
        batch.draw(texture,0,0);
    }
}
