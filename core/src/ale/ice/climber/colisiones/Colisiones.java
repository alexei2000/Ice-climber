/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ale.ice.climber.colisiones;

import ale.ice.climber.Main;
import ale.ice.climber.screens.worlds.Mundo;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 *
 * @author alexe
 */
public class Colisiones implements ContactListener {
    private final Mundo mundo;
    private final Main mainGame;
    
    public Colisiones(Mundo mundo, Main mainGame){
        this.mundo = mundo;
        this.mainGame = mainGame;
    }

    private boolean hanColisionado(Contact contact, Object a,Object b){
        return (contact.getFixtureA().getUserData().equals(a) && contact.getFixtureB().getUserData().equals(b)||
                contact.getFixtureA().getUserData().equals(b) && contact.getFixtureB().getUserData().equals(a));
                
    }
    
    private void perderVida(Contact contact){
        if(hanColisionado(contact,"pies","piesEnemigo") || hanColisionado(contact,"pies","cabezaEnemigo")){
            mundo.reiniciarNivel();
        }
        if(hanColisionado(contact,"cabeza","piesEnemigo") || hanColisionado(contact,"cabeza","cabezaEnemigo")){
            mundo.reiniciarNivel();
        }
    }
    
    private void cambiarNivel(Contact contact){
        if(hanColisionado(contact,"cabeza","puertaFinal")){
            mundo.setCambiarNivel(true);
        }
    }

    private void romperBloques(Contact contact){
        if(hanColisionado(contact,"cabeza","bloqueRoto")){

            Body body;

            if(contact.getFixtureA().getUserData().equals("bloqueRoto")){
                body = contact.getFixtureA().getBody();
            }
            else{
                body = contact.getFixtureB().getBody();
            }
            for(int i=0; i<mundo.getBloques().listaDeBloques.size();i++){
                if(body.getPosition().equals(mundo.getBloques().listaDeBloques.get(i).getBody().getPosition())){
                    mundo.getBloques().listaDeBloques.get(i).remove();
                    mundo.getBloques().listaDeBloques.get(i).destruir();
                    break;
                }
            }
            mainGame.getBreakSound().play(mainGame.getSfxVolumen()*0.7f);
        }
    }

    private void recogerFruta(Contact contact){

        if(hanColisionado(contact,"pies","fruta")){

            Body body;

            if(contact.getFixtureA().getUserData().equals("fruta")){
                body = contact.getFixtureA().getBody();
            }
            else{
                body = contact.getFixtureB().getBody();
            }

            for(int i=0; i<mundo.getFrutas().listaDeFrutas.size();i++){
                if(body.getPosition().equals(mundo.getFrutas().listaDeFrutas.get(i).getBody().getPosition())){
                    mundo.getFrutas().listaDeFrutas.get(i).remove();
                    mundo.getFrutas().listaDeFrutas.get(i).destruir();
                    mundo.setHayQueDestruirFruta(true);
                    break;
                }
            }
            mainGame.getBreakSound().play(mainGame.getSfxVolumen()*0.7f);
        }
    }

    private void cambiarDePosicion(Contact contact){
        if(hanColisionado(contact,"pies","paredDerecha")){
            mundo.getJugador().setCambiarEntidadPosicion(1);
        }
        else if(hanColisionado(contact,"pies", "paredIzquierda")){
            mundo.getJugador().setCambiarEntidadPosicion(2);
        }
    }

    @Override
    public void beginContact(Contact contact) {
        
        perderVida(contact);
        
        cambiarNivel(contact);
        
        if(hanColisionado(contact,"pies","suelo")){
            mundo.getJugador().setEstaEnElSuelo(true);
            mundo.getJugador().setAnimation(0);
        }
        
        else if(hanColisionado(contact,"pies","sueloBloque")){
            mundo.getJugador().setEstaEnElSuelo(true);
            mundo.getJugador().setAnimation(0);
            mundo.setMoverCamara(true);
        } 

        romperBloques(contact);

        recogerFruta(contact);

        cambiarDePosicion(contact);
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }
                   
}
