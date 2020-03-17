/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ale.ice.climber.lights;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author alexe
 */
public class Sun {
    
    private final RayHandler rayHandler;
    
    public Sun(World world){
        rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(0.1f, 0.1f, 0.1f, 1f);
        rayHandler.setBlurNum(1);
        PointLight pl = new PointLight(rayHandler, 100, new Color(247f/256f,247f/256f,58f/256f,1f),8, 8,6);

        rayHandler.setShadows(true);
        pl.setStaticLight(false);
        pl.setSoft(true);
        pl.setSoftnessLength(0);
        
    }
    
    public void dispose(){
        rayHandler.dispose();
    }
    
    public RayHandler getLight(){
        return this.rayHandler;
    }
    
    
    
}
