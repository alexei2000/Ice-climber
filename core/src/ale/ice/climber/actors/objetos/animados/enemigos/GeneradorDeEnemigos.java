/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ale.ice.climber.actors.objetos.animados.enemigos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexe
 */
public class GeneradorDeEnemigos {
   
    
    public List<Enemigo> listaDeEnemigos;
    protected World world;
    protected final float alturaNivel = 4f;
    protected int countNiveles;
    protected Vector2 position;
    protected Texture textureEnemigo1, textureEnemigo2;
    
    public GeneradorDeEnemigos(World world, Texture textureEnemigo1, Texture textureEnemigo2){
        listaDeEnemigos = new ArrayList<>();
        countNiveles = 1;
        
        this.textureEnemigo1 = textureEnemigo1;
        this.textureEnemigo2 = textureEnemigo2;
        this.world = world;
        
        position = new Vector2(1,countNiveles*alturaNivel);
    }
    
    public void generarEnemigos(int num,float pos,int type){
        
       if(countNiveles<9){
            if(type == 0){
                for(int i=0; i<num; i++){
                    listaDeEnemigos.add(new YetiHippie(world,textureEnemigo1, new Vector2(pos,position.y)));
                    incrementarNivel();
                    
                }
            }
            else if(type == 1){
                for(int i=0; i<num; i++){
                    listaDeEnemigos.add(new OsoPolarPunketo(world,textureEnemigo2, new Vector2(pos,position.y)));
                    incrementarNivel();
                    
                }
            }
            
       }
        
    }
    
    protected void incrementarNivel(){
     
        countNiveles++;
        position.y=countNiveles*alturaNivel;   
    }
    
    public void disposeList(){
        for (Enemigo listaDeEnemigo : listaDeEnemigos) {
            listaDeEnemigo.detach();
            listaDeEnemigo.remove();
        }
    }

    
}


