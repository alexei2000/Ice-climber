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
    private Slider volumen;
    private CheckBox muteMusic;
    private CheckBox muteSfx;
    private TextButton continuar;
    private TextButton salir;

    boolean isMusicMute;


    public Options(Skin skin, Main mainGame, Menu menu) {
        this.mainGame = mainGame;
        this.skin = skin;
        this.menu = menu;
        isMusicMute = false;
        createWidgets();
        addToWindow();

    }

    public void createWidgets(){

        table = new Table();
        table.setFillParent(true);
        table.center();

        ventana = new Window("Opciones", skin);
        ventana.setMovable(false);

        volumen = new Slider(0, 1, 0.01f, false,skin);
        volumen.setValue(0.5f);
        volumen.addListener(volumenHandler());

        muteMusic = new CheckBox("Mute music",skin);
        muteMusic.addListener(checkBoxMusicnHandler());
        muteSfx = new CheckBox("Mute sfx",skin);

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
        ventana.add(new Label("Volumen General",skin, "dark")).colspan(2).pad(30,0, 10, 0);
        ventana.row();
        ventana.add(volumen).pad(10,20,50,20).colspan(2).minWidth(350);
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
    private ChangeListener volumenHandler(){

        return new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                if(!isMusicMute){
                    mainGame.getMusicGame().setVolume(volumen.getValue());
                    mainGame.getMusicMenu().setVolume(volumen.getValue());
                }
            }
        };
    }

    private ChangeListener checkBoxMusicnHandler(){

        return new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                if(muteMusic.isChecked()){
                    mainGame.getMusicGame().setVolume(0);
                    mainGame.getMusicMenu().setVolume(0);
                }else{
                    mainGame.getMusicGame().setVolume(volumen.getValue());
                    mainGame.getMusicMenu().setVolume(volumen.getValue());
                }

            }
        };
    }


    public Table getTable() {
        return table;
    }

}
