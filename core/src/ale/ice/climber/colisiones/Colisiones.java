/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ale.ice.climber.colisiones;

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
    
    public Colisiones(Mundo mundo){
        this.mundo = mundo;
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

        if(hanColisionado(contact,"cabeza","bloqueRoto")){
            if(contact.getFixtureA().getUserData().equals("bloqueRoto")){
                 Body body = contact.getFixtureA().getBody();
                 for(int i=0; i<mundo.getBloques().listaDeBloques.size();i++){
                     if(body.getPosition().equals(mundo.getBloques().listaDeBloques.get(i).getBody().getPosition())){
                         mundo.getBloques().listaDeBloques.get(i).remove();
                         mundo.getBloques().listaDeBloques.get(i).destruir();
                         break;
                     }
                 }
            }
            else{
               Body body = contact.getFixtureB().getBody();
                 for(int i=0; i<mundo.getBloques().listaDeBloques.size();i++){
                     if(body.getPosition().equals(mundo.getBloques().listaDeBloques.get(i).getBody().getPosition())){
                         mundo.getBloques().listaDeBloques.get(i).remove();
                         mundo.getBloques().listaDeBloques.get(i).destruir();
                         break;
                     }
                 }
            }
        }
        if(hanColisionado(contact,"pies","paredDerecha")){
            mundo.getJugador().setCambiarEntidadPosicion(1);
        }
        else if(hanColisionado(contact,"pies", "paredIzquierda")){
            mundo.getJugador().setCambiarEntidadPosicion(2);
        }
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
