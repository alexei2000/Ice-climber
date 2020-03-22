package ale.ice.climber;

import ale.ice.climber.screens.gui.guiInMenu.Menu;
import ale.ice.climber.screens.gui.guiInMenu.progress.Progress;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class Main extends Game {
    
    private String skinUI;
    private String mapa1Texture;
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
    private String mapa3Texture;
    
    private AssetManager manager;
    private Stage stage;
    private Table progress;
    private Progress info;

    
    
    public Texture getMapa1Texture(){
        return manager.get(mapa1Texture);
    }
    
    public Texture getMapa2Texture(){
        return manager.get(mapa2Texture);
    }
    
    public Texture getMapa3Texture(){
       return manager.get(mapa3Texture);
    }
    
    public Texture getJugadorTexture(){
        return manager.get(jugadorTexture);
    }
    
    public Texture getYetiTexture(){
        return manager.get(yetiTexture);
    }
    public Texture getOsoTexture(){
        return manager.get(osoTexture);
    }
    
    public Texture getNieveBorde(){
        return manager.get(nieveBorde);
    }
    
    public Texture getBloqueHieloSolidoTexture(){
        return manager.get(bloqueHieloSolido);
    }
    public Texture getBloqueHieloRoto(){
        return manager.get(bloqueHieloRoto);
    }
    
    public Texture getPuertaTexture(){
        return manager.get(puertaTexture);
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
    

    @Override
    public void create () {
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);
        
        mapa1Texture = "textures/mapas/mapa1.png";
        mapa2Texture = "textures/mapas/mapa2.png";
        mapa3Texture = "textures/mapas/mapa3.png";
        jugadorTexture = "textures/personajes/jugador.png";
        yetiTexture = "textures/personajes/yeti.png";
        osoTexture = "textures/personajes/oso.png";
        bloqueHieloSolido = "textures/mapas/bloqueSolido.png";
        bloqueHieloRoto = "textures/mapas/bloqueRoto.png";
        nieveBorde = "textures/mapas/nieve.png";
        cabezaDePersonaje = "textures/personajes/vidas.png";
        skinUI = "skin/skin/freezing-ui.json";
        fondoMenu = "textures/fondoMenu.png";
        puertaTexture = "textures/mapas/puerta.png";
        
        
        manager = new AssetManager();
        manager.load(skinUI,Skin.class);
        manager.finishLoadingAsset(skinUI);
        manager.load(mapa1Texture,Texture.class);
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
        manager.load(mapa3Texture, Texture.class);
        info = new Progress(getSkinUI());
        progress = info.getTable();
        stage.addActor(progress);
    }

    @Override
    public void render() {
        super.render();

        if(manager.update()){
            setScreen(new Menu(this)) ;
        }
        else{
            Gdx.gl.glClearColor(30/255f, 23/255f, 23/255f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            System.out.println(manager.getProgress());
            info.setProgress(manager.getProgress());
            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();
        }
    }

    @Override
    public void dispose(){
        manager.dispose();
    }
    
}