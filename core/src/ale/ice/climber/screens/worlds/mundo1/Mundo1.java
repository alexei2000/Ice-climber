/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ale.ice.climber.screens.worlds.mundo1;

import ale.ice.climber.actors.objetos.animados.jugador.Jugador;
import ale.ice.climber.actors.objetos.mapa.Mapa;
import ale.ice.climber.actors.objetos.mapa.Nieve;
import ale.ice.climber.Main;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import ale.ice.climber.screens.worlds.Mundo;

/**
 *
 * @author alexe
 */
public class Mundo1 extends Mundo{
    
    public Mundo1(Main mainGame) {
        super(mainGame);
    }

    @Override
    public void createItems() {
        Texture textureMap = mainGame.getMapaTexture();
        Texture skinJugador = mainGame.getJugadorTexture();
        Texture bloqueSolidoTexture = mainGame.getBloqueHieloSolidoTexture();
        Texture bloqueRotoTexture = mainGame.getBloqueHieloRoto();
        Texture bordeNieve = mainGame.getNieveBorde();
        
        bloques = new BloquesMundo1(world,bloqueSolidoTexture,bloqueRotoTexture);
        enemigos = new EnemigosMundo1(world, mainGame.getYetiTexture(), mainGame.getOsoTexture());
        
        
        map = new Mapa(world,textureMap);
        nieve = new Nieve(bordeNieve);
        jugador = new Jugador(world,skinJugador,new Vector2(2f,1f));
    
        stage.addActor(map);
        stage.addActor(jugador);
        recorrerEnemigos();
        stage.addActor(nieve);
       
        recorrerBloques();
    }
    
}
