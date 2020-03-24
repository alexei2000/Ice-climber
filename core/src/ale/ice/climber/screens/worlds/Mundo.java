/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ale.ice.climber.screens.worlds;

import ale.ice.climber.Registro.RegistroHandler;
import ale.ice.climber.Registro.RegistroJugador;
import ale.ice.climber.Registro.RegistrosJugador;
import ale.ice.climber.actors.objetos.animados.jugador.Jugador;
import ale.ice.climber.actors.objetos.frutas.GeneradorDeFrutas;
import ale.ice.climber.actors.objetos.mapa.Mapa;
import ale.ice.climber.actors.objetos.mapa.Nieve;
import ale.ice.climber.Main;
import ale.ice.climber.actors.objetos.puerta.Puerta;
import ale.ice.climber.actors.objetos.animados.enemigos.GeneradorDeEnemigos;
import ale.ice.climber.actors.objetos.plataformas.bloques.GeneradorDeBloques;
import ale.ice.climber.colisiones.Colisiones;
import ale.ice.climber.screens.gui.guiInGame.Tiempo;
import ale.ice.climber.screens.gui.guiInGame.Vidas;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import ale.ice.climber.lights.Sun;
import ale.ice.climber.screens.BaseScreen;
import ale.ice.climber.screens.gui.guiInMenu.Menu;


/**
 *
 * @author alexe
 */
public abstract class Mundo extends BaseScreen {

    protected final World world;
    protected final Stage stage;
    protected final Sun sun;

    protected Puerta puertaInicio;
    protected Puerta puertaFinal;
    protected Jugador jugador;
    protected Mapa map;
    protected Nieve nieve;
    protected GeneradorDeBloques bloques;
    protected GeneradorDeEnemigos enemigos;
    protected GeneradorDeFrutas frutas;
    protected Vidas vidas;

    protected int camaraNivel;
    protected boolean cambiarNivel;
    protected boolean moverCamara;
    protected boolean movimientoDeCamara;
    protected boolean hayQueReiniciar;
    protected boolean hayQueDestruirFruta;

    protected Tiempo tiempo;
    protected String nombre;
    Box2DDebugRenderer boxDebug;
    
    
    public Mundo(Main mainGame, String nombre) {
        super(mainGame);

        this.nombre = nombre;

        cambiarNivel = false;
        moverCamara = false;
        movimientoDeCamara = false;
        camaraNivel=268;
        hayQueReiniciar = false;
        
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));      
        world = new World(new Vector2(0,-20), true);
        sun = new Sun(world);

        tiempo = new Tiempo("0", mainGame.getSkinUI(), stage.getCamera());
        
        boxDebug = new Box2DDebugRenderer();
        
        world.setContactListener(new Colisiones(this, mainGame));
     
    }
    
    @Override 
    public void show(){
        createItems();
    }
    
    @Override
    public void hide(){
        hayQueReiniciar = false;
        moverCamara = false;
        movimientoDeCamara = false;
        camaraNivel=268;
        deleteItems();
    }
    
    @Override
    public void render(float delta){

        Gdx.gl.glClearColor(0/255f, 0/255f, 30/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        if(cambiarNivel){
            siguienteNivel();
        }
        
        murioElJugador();

        world.step(delta, 6, 2);
        
        moverCamara(delta);
        
        updateAndDrawActors();
        
        //boxDebug.render(world,stage.getCamera().combined.cpy().scl(64f) );
        
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
        destruirBloques();
        if(hayQueDestruirFruta){
            destruirFrutas();
            hayQueDestruirFruta = false;
        }
        stage.act();
        stage.draw();
    }
 
    public void seCayoJugador(){
       if(jugador.getBody().getPosition().y < (stage.getCamera().position.y - Gdx.graphics.getHeight()/2f)/64f){
           reiniciarNivel();
        }
    }
    
    public void murioElJugador(){
        seCayoJugador();
        if(hayQueReiniciar){
            mainGame.getDeathSound().play(mainGame.getSfxVolumen());
            stage.getCamera().position.y = Gdx.graphics.getHeight()/2f;
            jugador.perderUnaVida();
            Jugador.reiniciarPuntosPorMundo();
            if(jugador.getNumeroDeVidas() != 0){
                mainGame.setScreen(mainGame.getScreen());
            }
            else{
                jugador.resetNumeroDeVidas();
                guardarPuntos();
                mainGame.setScreen(new Menu(mainGame));
                
            }
            
        }
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

    protected void destruirFrutas(){
        for(int i=0; i<frutas.listaDeFrutas.size();i++){
            if(frutas.listaDeFrutas.get(i).getDestruido()){
                frutas.listaDeFrutas.get(i).detach();
                frutas.listaDeFrutas.remove(i);
            }
        }
    }
    
    protected void recorrerBloques(){
        for(int i=0; i<bloques.listaDeBloques.size();i++){
            stage.addActor(bloques.listaDeBloques.get(i));
        }
    }
    
    protected void recorrerEnemigos(){
        for(int i=0; i<enemigos.listaDeEnemigos.size(); i++){
            stage.addActor(enemigos.listaDeEnemigos.get(i));
        }
    }

    protected void recorrerFrutas(){
        for(int i=0; i<frutas.listaDeFrutas.size(); i++){
            stage.addActor(frutas.listaDeFrutas.get(i));
        }
    }

    public void deleteItems(){

        puertaInicio.detach();
        puertaInicio.remove();

        puertaFinal.detach();
        puertaFinal.remove();

        map.detach();
        map.remove();

        jugador.detach();
        jugador.remove();

        nieve.remove();
        vidas.remove();

        bloques.disposeList();
        enemigos.disposeList();
        frutas.disposeList();

        tiempo.remove();

    }

    public void guardarPuntos(){

        RegistroJugador registro = new RegistroJugador(nombre, Jugador.getTotalPuntos());
        RegistroHandler registroHandler = new RegistroHandler();
        registroHandler.getRegistros().addRegistro(registro);
        registroHandler.guardarRegistro();

        Jugador.reiniciarPuntosPorMundo();
        Jugador.reiniciarPuntosTotales();
    }

    public void setCambiarNivel(boolean cambiarNivel) {
        this.cambiarNivel = cambiarNivel;
    }

    public void reiniciarNivel(){
        hayQueReiniciar = true;
    }

    public abstract void createItems();
    
    public abstract void siguienteNivel();
    
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

    public GeneradorDeFrutas getFrutas(){return this.frutas;}

    public void setHayQueDestruirFruta(boolean b){
        hayQueDestruirFruta = b;
    }
    
    
    
    
    
    
    
   
    
}
