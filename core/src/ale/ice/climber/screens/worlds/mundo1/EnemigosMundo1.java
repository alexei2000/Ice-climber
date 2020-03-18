/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ale.ice.climber.screens.worlds.mundo1;

import ale.ice.climber.actors.objetos.animados.enemigos.GeneradorDeEnemigos;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author alexe
 */
public class EnemigosMundo1 extends GeneradorDeEnemigos {
    
    public EnemigosMundo1(World world, Texture textureEnemigo1, Texture textureEnemigo2) {
        super(world, textureEnemigo1, textureEnemigo2);
        
        generarEnemigos(1,0,0);
        generarEnemigos(2,14,2);
        generarEnemigos(1,0,1);
        generarEnemigos(2,14,1);
        generarEnemigos(1,0,2);
        generarEnemigos(1,14,2);
        
    }
    
}
