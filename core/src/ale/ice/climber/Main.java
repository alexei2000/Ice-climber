package ale.ice.climber;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import ale.ice.climber.screens.worlds.mundo1.Mundo1;


public class Main extends Game {
    
    private String mapaTexture;
    private String jugadorTexture;
    private String bloqueHieloSolido; 
    private String bloqueHieloRoto;
    private String nieveBorde;
    private String yetiTexture;
    
    private AssetManager manager;
    
    
    public Texture getMapaTexture(){
        return manager.get(mapaTexture);
    }
    
    public Texture getJugadorTexture(){
        return manager.get(jugadorTexture);
    }
    
    public Texture getBloqueHieloSolidoTexture(){
        return manager.get(bloqueHieloSolido);
    }
    public Texture getBloqueHieloRoto(){
        return manager.get(bloqueHieloRoto);
    }
    public Texture getNieveBorde(){
        return manager.get(nieveBorde);
    }
    public Texture getYetiTexture(){
        return manager.get(yetiTexture);
    }

    @Override
    public void create () {
        
        mapaTexture = "MAPA_Mesa de trabajo 1.png";
        jugadorTexture = "personaje de climb_Mesa de trabajo 1.png";
        bloqueHieloSolido = "BLOQUE DE HILO SOLIDO_Mesa de trabajo 1.png";
        bloqueHieloRoto = "BLOQUE TEXTURA_Mesa de trabajo 1.png";
        nieveBorde = "nieve para los bordes_Mesa de trabajo 1.png";
        yetiTexture = "YETI HIPPIE_Mesa de trabajo 1.png";
        
        manager = new AssetManager();
        manager.load(mapaTexture,Texture.class);
        manager.load(jugadorTexture,Texture.class);
        manager.load(bloqueHieloSolido,Texture.class);
        manager.load(bloqueHieloRoto,Texture.class);
        manager.load(nieveBorde,Texture.class);
        manager.load(yetiTexture, Texture.class);
        manager.finishLoading();
        setScreen(new Mundo1(this)) ; 
    }
    
    @Override
    public void dispose(){
        manager.dispose();
    }
    
}
