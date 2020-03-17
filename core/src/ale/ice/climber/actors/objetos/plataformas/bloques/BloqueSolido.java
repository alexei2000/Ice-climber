/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ale.ice.climber.actors.objetos.plataformas.bloques;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author alexe
 */
public class BloqueSolido extends Bloque{

    public BloqueSolido(World world, Texture texture, Vector2 position) {
        super(world, texture, position);
    }

    @Override
    protected void setNameFixture() {
        this.fixtureInferior.setUserData("bloqueSolido") ;
        this.fixtureSuperior.setUserData("sueloBloque");
        this.fixtureDerecho.setUserData("lateral");
        this.fixtureIzquierdo.setUserData("lateral");
        
    } 
}
