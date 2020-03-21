package ale.ice.climber;

import ale.ice.climber.screens.gui.guiInMenu.Menu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class Main extends Game {
    
    private String skinUI;
    
    private String mapaTexture;
    private String jugadorTexture;
    private String bloqueHieloSolido; 
    private String bloqueHieloRoto;
    private String nieveBorde;
    private String yetiTexture;
    private String osoTexture;
    private String cabezaDePersonaje;
    private String fondoMenu;
    private String puertaTexture;
    private String mapa2Texture;
    
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
    public Texture getOsoTexture(){
        return manager.get(osoTexture);
    }
    
    public Texture getCabezaDePersonaje(){
        return manager.get(cabezaDePersonaje);
    }
    
    public Skin getSkinUI(){
        return manager.get(skinUI);
    }
    
    public Texture getFondoMenu(){
        return manager.get(fondoMenu);
    }
    
    public Texture getPuertaTexture(){
        return manager.get(puertaTexture);
    }
    
    public Texture getMapa2Texture(){
        return manager.get(mapa2Texture);
    }

    @Override
    public void create () {
        
        mapaTexture = "MAPA_Mesa de trabajo 1.png";
        jugadorTexture = "personaje de climb_Mesa de trabajo 1.png";
        bloqueHieloSolido = "BLOQUE DE HILO SOLIDO_Mesa de trabajo 1.png";
        bloqueHieloRoto = "BLOQUE TEXTURA_Mesa de trabajo 1.png";
        nieveBorde = "nieve para los bordes_Mesa de trabajo 1.png";
        yetiTexture = "YETI HIPPIE_Mesa de trabajo 1.png";
        osoTexture = "oso ice climber_Mesa de trabajo 1.png";
        cabezaDePersonaje = "cabeza_Mesa de trabajo 1.png";
        skinUI = "skin/skin/freezing-ui.json";
        fondoMenu = "fondo menu_Mesa de trabajo 1.png";
        puertaTexture = "puerta.png";
        mapa2Texture = "mapa2_Mesa de trabajo 1.png";
        
        manager = new AssetManager();
        manager.load(skinUI,Skin.class);
        manager.load(mapaTexture,Texture.class);
        manager.load(jugadorTexture,Texture.class);
        manager.load(bloqueHieloSolido,Texture.class);
        manager.load(bloqueHieloRoto,Texture.class);
        manager.load(nieveBorde,Texture.class);
        manager.load(yetiTexture, Texture.class);
        manager.load(cabezaDePersonaje, Texture.class);
        manager.load(osoTexture, Texture.class);
        manager.load(fondoMenu, Texture.class);
        manager.load(puertaTexture, Texture.class);
        manager.load(mapa2Texture,Texture.class);
        manager.finishLoading();
        setScreen(new Menu(this)) ; 
    }
    
    @Override
    public void dispose(){
        manager.dispose();
    }
    
}
