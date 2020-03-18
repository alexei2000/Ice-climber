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
public class OsoPolarPunketo extends Enemigo{
    
    public OsoPolarPunketo(World world, Texture texture, Vector2 position) {
        super(world, texture,position,1.09375f,1.84375f);
        
        TextureRegion skin = new TextureRegion(texture,560,118);
        frames = skin.split(70, 118);
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
        bodyFixture.setAsBox(width/4 +0.1f, 3*height/16+0.08f);
        fixtureBody = body.createFixture(bodyFixture,0.3f);
        
        CircleShape circle = new CircleShape();
        circle.setRadius(width/4+0.03f);
        circle.setPosition(new Vector2(0.01f,height/2));
        fixtureHead = body.createFixture(circle,0.3f);
        
        fixtureBody.setUserData("piesEnemigo");
        fixtureHead.setUserData("cabezaEnemigo"); 
        
        circle.dispose();
        bodyFixture.dispose();
    }
    
}
