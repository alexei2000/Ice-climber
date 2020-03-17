/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ale.ice.climber.actors.objetos.animados.enemigos;

import ale.ice.climber.actors.objetos.animados.ObjetoAnimado;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author alexe
 */
public class Enemigo extends ObjetoAnimado {

    public Enemigo(World world, Texture texture,Vector2 position) {
        super(world, texture, 2.34375f, 2f,position);
        numeroDeAnimaciones = 1;
        velocidadDeAnimacion = 0.17f;
        
        TextureRegion skin = new TextureRegion(texture,1200,128);
        frames = skin.split(150, 128);
        animation = new Animation(0.08f,frames[0]);
        
    }

    @Override
    protected void animationUpdate() {
        frame = (TextureRegion)animation.getKeyFrame(duracion,true);
    }

    @Override
    protected void createFixture() {
        PolygonShape bodyFixture = new PolygonShape();
        bodyFixture.setAsBox(width/2, height/2);
        fixtureBody = body.createFixture(bodyFixture,0.3f);
        
        CircleShape circle = new CircleShape();
        circle.setRadius(width/2+0.03f);
        circle.setPosition(new Vector2(0.01f,height/2-0.1f));
        fixtureHead = body.createFixture(circle,0.3f);
        
        fixtureBody.setUserData("piesEnemigo");
        fixtureHead.setUserData("cabezaEnemigo"); 
        
        circle.dispose();
        bodyFixture.dispose();
    }

    @Override
    protected void moverse(float delta) {
        duracion+=delta;
    }

    @Override
    protected void moveRight() {
    }

    @Override
    protected void moveLeft() {
    }
    
}
