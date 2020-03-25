/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ale.ice.climber.screens.worlds.mundo2;

import ale.ice.climber.actors.objetos.plataformas.bloques.GeneradorDeBloques;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author alexe
 */
public class BloquesMundo2 extends GeneradorDeBloques {
    
    public BloquesMundo2(World world, Texture textureBloqueSolido, Texture textureBloqueRoto) {
        super(world, textureBloqueSolido, textureBloqueRoto);
        
        generarBloquesEnLinea(5,0);
        generarBloquesEnLinea(9,1);
        generarBloquesEnLinea(11,0);
        generarBloquesEnLinea(5,1);
        generarBloquesEnLinea(8,0);
        generarBloquesEnLinea(5,1);
        generarBloquesEnLinea(11,0);
        generarBloquesEnLinea(7,1);
        generarBloquesEnLinea(5,0);
        generarBloquesEnLinea(5,1);
        generarBloquesEnLinea(11,0);
        generarBloquesEnLinea(7,1);
        generarBloquesEnLinea(10,0);
        generarBloquesEnLinea(10,1);
        generarBloquesEnLinea(5,0);
        generarBloquesEnLinea(5,1);
        generarBloquesEnLinea(11,0);
        generarBloquesEnLinea(12,1);
        generarBloquesEnLinea(5,0);
        generarBloquesEnLinea(5,1);
        generarBloquesEnLinea(15,0);
        generarBloquesEnLinea(5,1);
        generarBloquesEnLinea(10,0);
        generarBloquesEnLinea(8,1);
        generarBloquesEnLinea(5,0);
        generarBloquesEnLinea(5,1);
        generarBloquesEnLinea(10,0);
        generarBloquesEnLinea(8,1);
        generarBloquesEnLinea(6,0);
        
    }
    
}
