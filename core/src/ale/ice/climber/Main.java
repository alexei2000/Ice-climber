package ale.ice.climber;


import ale.ice.climber.screens.gui.progress.Progress;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class Main extends Game {


    //music
    private String musicMenu;
    private String musicGame;

    //sfx
    private String jumpSound;
    private String breakSound;
    private String clickSound;
    private String playSound;
    private String deathSound;

    // skins
    private String skinUI;

    //textures
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

    private float sfxVolumen;

    
    public AssetManager getManager(){
        return manager;
    }

    public Sound getJumpSound(){
        return manager.get(jumpSound);
    }

    public Sound getClickSound(){
        return manager.get(clickSound);
    }

    public Sound getBreakSound(){
        return manager.get(breakSound);
    }

    public Sound getPlaySound(){
        return manager.get(playSound);
    }

    public Sound getDeathSound(){
        return manager.get(deathSound);
    }

    public Music getMusicMenu(){
        return manager.get(musicMenu);
    }

    public Music getMusicGame(){
        return manager.get(musicGame);
    }

    public Skin getSkinUI(){
        return manager.get(skinUI);
    }

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

    public Texture getFondoMenu(){
        return manager.get(fondoMenu);
    }

    

    @Override
    public void create () {

        sfxVolumen = 0.5f;

        //rutas
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
        musicMenu = "sonidos/music/Caketown 1.mp3";
        musicGame = "sonidos/music/TownTheme.mp3";
        breakSound = "sonidos/sfx/break.mp3";
        clickSound = "sonidos/sfx/click.wav";
        jumpSound = "sonidos/sfx/jump.mp3";
        playSound = "sonidos/sfx/play.wav";
        deathSound = "sonidos/sfx/death.wav";
        
        manager = new AssetManager();

        //skin
        manager.load(skinUI,Skin.class);
        manager.finishLoadingAsset(skinUI);

        //music
        manager.load(musicMenu, Music.class);
        manager.load(musicGame, Music.class);

        //sfx
        manager.load(breakSound,Sound.class);
        manager.load(jumpSound,Sound.class);
        manager.load(clickSound, Sound.class);
        manager.load(playSound, Sound.class);
        manager.load(deathSound, Sound.class);


        //textures
        manager.load(mapa1Texture, Texture.class);
        manager.load(jugadorTexture, Texture.class);
        manager.load(bloqueHieloSolido, Texture.class);
        manager.load(bloqueHieloRoto, Texture.class);
        manager.load(nieveBorde, Texture.class);
        manager.load(yetiTexture, Texture.class);
        manager.load(cabezaDePersonaje, Texture.class);
        manager.load(osoTexture, Texture.class);
        manager.load(fondoMenu, Texture.class);
        manager.load(puertaTexture, Texture.class);
        manager.load(mapa2Texture, Texture.class);
        manager.load(mapa3Texture, Texture.class);

        setScreen(new Progress(this));
    }


    @Override
    public void dispose(){
        manager.dispose();
    }

    public float getSfxVolumen(){
        return sfxVolumen;
    }

    public void setSfxVolumen(float sfxVolumen) {
        this.sfxVolumen = sfxVolumen;
    }
}