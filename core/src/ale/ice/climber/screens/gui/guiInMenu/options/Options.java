package ale.ice.climber.screens.gui.guiInMenu.options;


import ale.ice.climber.Main;
import ale.ice.climber.screens.gui.guiInMenu.Menu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;


public class Options  {
    private Main mainGame;
    private Skin skin;
    private Menu menu;
    private Table table;
    private Window ventana;
    private Slider volumenMusic;
    private Slider volumenSfx;
    private CheckBox muteMusic;
    private CheckBox muteSfx;
    private TextButton continuar;
    private TextButton salir;

    boolean isMusicMute;
    boolean isSfxMute;


    public Options(Menu menu,  Main mainGame) {
        this.mainGame = mainGame;
        this.skin = mainGame.getSkinUI();
        this.menu = menu;
        isMusicMute = false;
        isSfxMute = false;
        createWidgets();
        addToWindow();

    }

    public void createWidgets(){

        table = new Table();
        table.setFillParent(true);
        table.center();

        ventana = new Window("Opciones", skin);
        ventana.setMovable(false);

        volumenMusic = new Slider(0, 1, 0.01f, false,skin);
        volumenMusic.setValue(0.5f);
        volumenMusic.addListener(volumenMusicHandler());

        volumenSfx = new Slider(0, 1, 0.01f, false,skin);
        volumenSfx.setValue(0.5f);
        volumenSfx.addListener(volumenSfxHandler());

        muteMusic = new CheckBox("Mute music",skin);
        muteMusic.addListener(checkBoxMusicHandler());

        muteSfx = new CheckBox("Mute sfx",skin);
        muteMusic.addListener(checkBoxSfxHandler());

        continuar = new TextButton("Continuar",skin);
        continuar.getLabel().setFontScale(0.5f);
        continuar.addListener(botonContinuarHandler());

        salir = new TextButton("salir",skin);
        salir.getLabel().setFontScale(0.5f);
        salir.addListener(botonSalirHandler());

    }

    public void  addToWindow(){
        ventana.add(new Label("Sonido",skin, "dark")).colspan(2).padTop(20);
        ventana.row();
        ventana.add(muteMusic).left().pad(10,20, 0, 0);
        ventana.row();
        ventana.add(muteSfx).left().pad(10,20, 0, 0);
        ventana.row();
        ventana.add(new Label("Musica",skin, "dark")).colspan(2).pad(30,0, 10, 0);
        ventana.row();
        ventana.add(volumenMusic).pad(10,20,20,20).colspan(2).minWidth(350);
        ventana.row();
        ventana.add(new Label("Sfx",skin, "dark")).colspan(2).pad(30,0, 10, 0);
        ventana.row();
        ventana.add(volumenSfx).pad(10,20,50,20).colspan(2).minWidth(350);
        ventana.row();
        ventana.add(continuar).maxHeight(40).maxWidth(80);
        ventana.add(salir).maxHeight(40).maxWidth(80);

        table.add(ventana);
    }

    private ChangeListener botonContinuarHandler(){

        return new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                table.setVisible(false);
                menu.setVentanaAbierta(false);
                mainGame.getClickSound().play(mainGame.getSfxVolumen());
            }
        };
    }

    private ChangeListener botonSalirHandler(){

        return new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        };
    }
    private ChangeListener volumenMusicHandler(){

        return new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                if(!isMusicMute){
                    mainGame.getMusicGame().setVolume(volumenMusic.getValue());
                    mainGame.getMusicMenu().setVolume(volumenMusic.getValue());
                }
            }
        };
    }

    private ChangeListener volumenSfxHandler(){

        return new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                if(!isSfxMute){
                    mainGame.setSfxVolumen(volumenSfx.getValue());
                }
            }
        };
    }

    private ChangeListener checkBoxMusicHandler(){

        return new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                if(muteMusic.isChecked()){
                    mainGame.getMusicGame().setVolume(0);
                    mainGame.getMusicMenu().setVolume(0);
                    isMusicMute = true;
                }else{
                    mainGame.getMusicGame().setVolume(volumenMusic.getValue());
                    mainGame.getMusicMenu().setVolume(volumenMusic.getValue());
                    isMusicMute = false;
                }

            }
        };
    }

    private ChangeListener checkBoxSfxHandler(){

        return new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                if(muteSfx.isChecked()){
                    mainGame.setSfxVolumen(0);
                    isSfxMute = true;
                }else{
                    mainGame.setSfxVolumen(volumenSfx.getValue());
                    isSfxMute = false;
                }

            }
        };
    }


    public Table getTable() {
        return table;
    }

}
