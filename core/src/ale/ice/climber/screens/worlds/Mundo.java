/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ale.ice.climber.screens.worlds;

import ale.ice.climber.actors.objetos.animados.jugador.Jugador;
import ale.ice.climber.actors.objetos.mapa.Mapa;
import ale.ice.climber.actors.objetos.mapa.Nieve;
import ale.ice.climber.Main;
import ale.ice.climber.actors.objetos.animados.enemigos.GeneradorDeEnemigos;
import ale.ice.climber.actors.objetos.plataformas.bloques.GeneradorDeBloques;
import ale.ice.climber.colisiones.Colisiones;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import ale.ice.climber.lights.Sun;
import ale.ice.climber.screens.BaseScreen;

/**
 *
 * @author alexe
 */
public abstract class Mundo extends BaseScreen {
    
    protected Jugador jugador;
    protected Mapa map;
    protected Nieve nieve;
    protected final World world;
    protected final Stage stage;
    protected final Sun sun;
    protected GeneradorDeBloques bloques;
    protected GeneradorDeEnemigos enemigos;
    protected boolean moverCamara;
    protected boolean movimientoDeCamara;
    protected int camaraNivel;
    Box2DDebugRenderer boxDebug;
    
    
    public Mundo(Main mainGame) {
        super(mainGame);
        
        moverCamara = false;
        movimientoDeCamara = false;
        camaraNivel=268;
        
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));      
        world = new World(new Vector2(0,-20), true);
        sun = new Sun(world);
        
       boxDebug = new Box2DDebugRenderer();
        
       world.setContactListener(new Colisiones(this));   
    }
    
    @Override
    public void hide(){
        map.detach();
        map.remove();
        jugador.detach();
        jugador.remove();
    }
    
    @Override
    public void render(float delta){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(delta, 6, 2);
        
        moverCamara(delta);
        
        updateAndDrawActors();
        
        boxDebug.render(world,stage.getCamera().combined.cpy().scl(64f) );
        
        updateAndDrawLights();
          
    }
    
    @Override 
     public void dispose(){
        stage.dispose();
        world.dispose();
        boxDebug.dispose();
        sun.dispose(); 
    }
     
    protected void updateAndDrawLights(){
        sun.getLight().setCombinedMatrix(stage.getCamera().combined.cpy().scl(64f));
        sun.getLight().updateAndRender();
    }
     
    protected void updateAndDrawActors(){
        stage.act();
        destruirBloques();
        stage.draw();
    }
     
    protected void moverCamara(float delta){
        if(jugador.getY()>camaraNivel+256 && moverCamara){
            movimientoDeCamara=true;
            camaraNivel+=512;
        }
        if(movimientoDeCamara && (stage.getCamera().position.y < camaraNivel)){
           stage.getCamera().position.y+=700*delta;
        }
        else{
            movimientoDeCamara=false;
        }
        moverCamara=false;
    }
    
    protected void destruirBloques(){
        for(int i=0; i<bloques.listaDeBloques.size();i++){
            if(bloques.listaDeBloques.get(i).getDestruido()){
                bloques.listaDeBloques.get(i).detach();
                bloques.listaDeBloques.remove(i);
            }
         }
    }
    
    public void setMoverCamara(boolean b){
        this.moverCamara = b;
    }
    
    public Jugador getJugador(){
        return this.jugador;
    }
    
    public GeneradorDeEnemigos getEnemigos(){
        return this.enemigos;
    }
    
    public GeneradorDeBloques getBloques(){
        return this.bloques;
    }
    
    
    
    
    
   
    
}
