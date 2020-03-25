/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ale.ice.climber.screens.worlds.mundo3;

import ale.ice.climber.actors.objetos.animados.jugador.Jugador;
import ale.ice.climber.actors.objetos.mapa.Mapa;
import ale.ice.climber.actors.objetos.mapa.Nieve;
import ale.ice.climber.Main;
import ale.ice.climber.actors.objetos.puerta.Puerta;
import ale.ice.climber.screens.gui.guiInGame.Vidas;
import ale.ice.climber.screens.gui.guiInMenu.Menu;
import ale.ice.climber.screens.transicion.Transicion;
import ale.ice.climber.screens.worlds.mundo1.FrutasMundo1;
import ale.ice.climber.screens.worlds.mundo2.Mundo2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import ale.ice.climber.screens.worlds.Mundo;

/**
 *
 * @author alexe
 */
public class Mundo3 extends Mundo{
    
    public Mundo3(Main mainGame, String nombre) {
        super(mainGame, nombre);
    }

    @Override
    public void createItems() {
        Texture textureMap = mainGame.getMapa3Texture();
        Texture skinJugador = mainGame.getJugadorTexture();
        Texture bloqueSolidoTexture = mainGame.getBloqueHieloSolidoTexture();
        Texture bloqueRotoTexture = mainGame.getBloqueHieloRoto();
        Texture bordeNieve = mainGame.getNieveBorde();
        
        bloques = new BloquesMundo3(world,bloqueSolidoTexture,bloqueRotoTexture);
        enemigos = new EnemigosMundo3(world, mainGame.getYetiTexture(), mainGame.getOsoTexture());
        
        puertaInicio = new Puerta(world, mainGame.getPuertaTexture(),new Vector2(3,1.5f),"puertaInicio");
        puertaFinal = new Puerta(world, mainGame.getPuertaTexture(),new Vector2(6.5f,62),"puertaFinal");
        frutas = new FrutasMundo1(world,mainGame.getTextureFrutas());
        
        map = new Mapa(world,textureMap, mainGame.getTextureNieveSuperior());
        nieve = new Nieve(bordeNieve);
        jugador = new Jugador(world,skinJugador, mainGame, new Vector2(5f,1f));
        
        vidas = new Vidas(jugador,stage.getCamera(),mainGame.getCabezaDePersonaje());
    
        stage.addActor(map);
        stage.addActor(puertaInicio);
        stage.addActor(puertaFinal);
        stage.addActor(jugador);
        recorrerEnemigos();
        stage.addActor(nieve);
        recorrerFrutas();;
        recorrerBloques();
        stage.addActor(vidas);
        stage.addActor(tiempo);
    }

    @Override
    public void siguienteNivel() {
        int puntosFruta = Jugador.getPuntosPorMundo();
        int puntosTotales = Jugador.getTotalPuntos();
        Jugador.sumarTotalPuntos(Jugador.getPuntosPorMundo()); //frutas
        Jugador.sumarTotalPuntos(100000/tiempo.getValue()); //tiempo
        guardarPuntos();
        mainGame.setScreen(new Transicion(mainGame, "Ganaste!!", new Menu(mainGame), puntosFruta, tiempo.getValue(), puntosTotales));
    }
    
}