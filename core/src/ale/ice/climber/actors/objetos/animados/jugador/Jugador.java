/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ale.ice.climber.actors.objetos.animados.jugador;

import ale.ice.climber.Main;
import ale.ice.climber.actors.objetos.animados.ObjetoAnimado;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
public class Jugador extends ObjetoAnimado {

    //datos
    private static int puntosPorMundo=0;
    private static int totalPuntos=0;


    private boolean estaEnElSuelo;
    private int estadoDeAnimacion;
    private static int numeroDeVidas = 3;
    private Main mainGame;
    
    
    public Jugador(World world, Texture texture, Main mainGame, Vector2 position)
    {
        super(world, texture, 0.640625f,1.625f,position);

        this.mainGame = mainGame;
        
        estaEnElSuelo=true;
        numeroDeAnimaciones=3;
        velocidadDeAnimacion = 0.17f;
        estadoDeAnimacion=0;
        
        TextureRegion skin = new TextureRegion(texture,560,312);
        frames = skin.split(70, 104);//falta

        animation = new Animation<>(0.08f,frames[0]);

        
    }
    
    
    public void setAnimation(int state){
        switch (state) {
            case 0:
                animation = new Animation<>(velocidadDeAnimacion,frames[0]);
                break;
            case 1:
                animation = new Animation<>(0.03f,frames[1]);
                break;
            case 2:
                animation = new Animation<>(0.04f,frames[2]);
                break;
            default:
                break;
        }
        estadoDeAnimacion=state;
    }
    
    @Override
    protected void animationUpdate(){
        if (estadoDeAnimacion == 0) {
            frame = animation.getKeyFrame(duracion, true);
        } else {
            frame = animation.getKeyFrame(duracion, false);
        }
    }
       
    @Override
    protected void createFixture(){
          
        PolygonShape figureBody = new PolygonShape();
        CircleShape circle = new CircleShape();
        Vector2[] vertices = new Vector2[5];
        
            
        if(!flipFixture){
            
            circle.setRadius(width/2+0.06f);
            circle.setPosition(new Vector2(0.06f,height/2-0.1f));
    
            vertices[0] = new Vector2(-width/4+0.15f,height/4+0.15f);
            vertices[1] = new Vector2(-width/4,0);
            vertices[2] = new Vector2(0.1f,-height/4);
            vertices[3] = new Vector2(width-0.2f,-height/4);
            vertices[4] = new Vector2(width/2+0.1f,height/4+0.15f);
            
            
        }
        else{
            circle.setRadius(width/2+0.06f);
            circle.setPosition(new Vector2(0.4f,height/2-0.1f));
            
            vertices[0] = new Vector2(-width/4+0.2f,height/4+0.15f);//1
            vertices[1] = new Vector2(-width/4+0.2f,-height/4);//2
            vertices[2] = new Vector2(width/2+0.05f,-height/4);//3
            vertices[3] = new Vector2(width,0);//4
            vertices[4] = new Vector2(width/2+0.15f,height/4+0.15f);//5
        }
        
        
        figureBody.set(vertices);
        fixtureBody = body.createFixture(figureBody,3f);
        fixtureHead = body.createFixture(circle,3f);
        
        fixtureBody.setUserData("pies");
        fixtureHead.setUserData("cabeza"); 
        
        circle.dispose();
        figureBody.dispose();
        
    }
    
    @Override
    protected void moverse(float delta){
        //moverse hacia los lados
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            moveRight();
            duracion+=delta;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            moveLeft();
            duracion+=delta;
        }
        //saltar
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                saltar();
        }
        
        //tiempo para las animaciones
        if(!Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT) && estaEnElSuelo){
            duracion=0;
        }
        else{
            duracion+=delta;
        }
    
}
    
    @Override
    protected void moveRight(){
        if(!estaEnElAire()){
            body.setLinearVelocity(5,0);
        }
        else if(body.getLinearVelocity().x < 5 ){
            body.applyLinearImpulse(new Vector2(1,0),body.getPosition(),true);
        }
        if(volteado){
            flipFixture = false;
            voltear();
        }
        volteado=false;
    }
    
    @Override
    protected void moveLeft(){
        if(!estaEnElAire()){
            body.setLinearVelocity(-5,0);
        }
        else if(body.getLinearVelocity().x > -5 ){
            body.applyLinearImpulse(new Vector2(-1,0),body.getPosition(),true);
        }
        if(!volteado){
            flipFixture = true;
            voltear();
        }
        volteado=true;
    }
    
    @Override
    protected void voltear(){
        super.voltear();
        body.destroyFixture(fixtureHead);
        body.destroyFixture(fixtureBody);
        createFixture();
    }
    
    private void saltar(){
        if(!estaEnElAire() && estaEnElSuelo){
            setAnimation(2);
            body.applyLinearImpulse(new Vector2(0,40),body.getPosition(),true);
            estaEnElSuelo=false;
            mainGame.getJumpSound().play(mainGame.getSfxVolumen()*0.7f);
        }    
    }


    public static int getPuntosPorMundo() {
        return puntosPorMundo;
    }

    public static void sumarPuntosPorMundo(int puntosPorMundo) {
        Jugador.puntosPorMundo += puntosPorMundo;
    }

    public static int getTotalPuntos() {
        return totalPuntos;
    }

    public static void sumarTotalPuntos(int totalPuntos) {
        Jugador.totalPuntos += totalPuntos;
    }
    public static void reiniciarPuntosPorMundo(){
        Jugador.puntosPorMundo = 0;
    }

    public static void reiniciarPuntosTotales(){
        Jugador.totalPuntos = 0;
    }
    
    public void resetNumeroDeVidas(){
        Jugador.numeroDeVidas = 3;
    }
    
    public int getNumeroDeVidas(){
        return Jugador.numeroDeVidas;
    }
    
    public void perderUnaVida(){
        Jugador.numeroDeVidas--;
    }
    
    public void setEstaEnElSuelo(boolean b){
        estaEnElSuelo = b;
    }
    
    
}
