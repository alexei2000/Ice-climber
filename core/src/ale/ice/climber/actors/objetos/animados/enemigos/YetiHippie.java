/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ale.ice.climber.actors.objetos.animados.enemigos;

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
public class YetiHippie extends Enemigo {
    
    public YetiHippie(World world, Texture texture, Vector2 position) {
        super(world, texture, position, 2.34375f, 2f );
        
        TextureRegion skin = new TextureRegion(texture,1200,128);
        frames = skin.split(150, 128);
        animation = new Animation(0.08f,frames[0]);
        
        if(position.x>8){      //<---right
            direccion = true;            
        }
        else{                  //--->left
            voltear();
            direccion = false;
        }
    }
    
    @Override
    protected void createFixture() {
        PolygonShape bodyFixture = new PolygonShape();
        bodyFixture.setAsBox(width/4, height/4-0.05f);
        fixtureBody = body.createFixture(bodyFixture,0.3f);
        
        CircleShape circle = new CircleShape();
        circle.setRadius(width/4-0.05f);
        circle.setPosition(new Vector2(0.01f,height/2-0.1f));
        fixtureHead = body.createFixture(circle,0.3f);
        
        fixtureBody.setUserData("piesEnemigo");
        fixtureHead.setUserData("cabezaEnemigo"); 
        
        circle.dispose();
        bodyFixture.dispose();
    }

    
}
