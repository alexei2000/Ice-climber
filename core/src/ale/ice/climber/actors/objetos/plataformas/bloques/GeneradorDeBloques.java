/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ale.ice.climber.actors.objetos.plataformas.bloques;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexe
 */
public abstract class GeneradorDeBloques {
    
    public List<Bloque> listaDeBloques;
    protected World world;
    protected final float alturaNivel = 4f;
    protected int countNiveles;
    protected int countEspacios;
    protected Vector2 position;
    protected Texture textureBloqueSolido, textureBloqueRoto;
    
    public GeneradorDeBloques(World world, Texture textureBloqueSolido, Texture textureBloqueRoto){
        listaDeBloques = new ArrayList<>();
        countNiveles = 1;
        countEspacios = 0;
        
        this.textureBloqueSolido = textureBloqueSolido;
        this.textureBloqueRoto = textureBloqueRoto;
        this.world = world;
        
        position = new Vector2(countEspacios,countNiveles*alturaNivel);
    }
    
    public void generarBloquesEnLinea(int num,int type){
        
       if(countNiveles<9){
            if(type == 0){
                for(int i=0; i<num; i++){
                    listaDeBloques.add(new BloqueSolido(world,textureBloqueSolido, position));
                    incrementarEspacio();
                    
                }
            }
            else if(type == 1){
                for(int i=0; i<num; i++){
                    listaDeBloques.add(new BloqueRoto(world,textureBloqueRoto, position));
                    incrementarEspacio();
                    
                }
            }
            
       }
        
    }
    
    protected void incrementarEspacio(){
        if(countEspacios<15){
            countEspacios++;
        }
        else{
            countEspacios=0;
            countNiveles++;
        }
        if(countNiveles==1){
            position.x=countEspacios;
            position.y=countNiveles*alturaNivel;
        }
        else{
            position.x=countEspacios;
            position.y=countNiveles*alturaNivel;
        }
        
    }
    
    protected void IncrementarVariosEspacios(int veces){
        
        for(int i=0;i<veces; i++){
            incrementarEspacio();
        }
    }
    
    public void disposeList(){
        for(int i=0; i<listaDeBloques.size();i++){
            listaDeBloques.get(i).detach();
            listaDeBloques.get(i).remove();
        }
    }

}
