/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ale.ice.climber.screens.gui.guiInMenu;

import ale.ice.climber.Main;
import ale.ice.climber.screens.BaseScreen;
import ale.ice.climber.screens.gui.guiInMenu.dialogo.Dialogo;
import ale.ice.climber.screens.gui.guiInMenu.fondo.FondoMenu;
import ale.ice.climber.screens.gui.guiInMenu.options.Options;
import ale.ice.climber.screens.gui.guiInMenu.puntuacion.Puntuacion;
import ale.ice.climber.screens.worlds.mundo1.Mundo1;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 *
 * @author alexe
 */
public class Menu extends BaseScreen{
    
    private final Stage stage;

    private final Skin skin;

    private Table mainTable;
    private Window panelJugador;
    private Table opciones;
    private Table puntuaciones;
    private Table ayuda;
    private Table info;
    private Table alerta;

    private FondoMenu fondo;
    private TextButton botonOpciones;
    private TextButton botonJugar;
    private TextButton botonPuntuacion;
    private TextButton botonAyuda;
    private TextButton botonInfo;
    private TextField  nombreCaja;

    private boolean ventanaAbierta;

    public Menu(final Main mainGame) {
        super(mainGame);

        ventanaAbierta = false;
        
        skin = mainGame.getSkinUI();
        
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
	    Gdx.input.setInputProcessor(stage);

	    createWidgets();

	    addToPanelJugador();
	    addToTable();


        stage.addActor(fondo);
        stage.addActor(mainTable);
        stage.addActor(opciones);
        stage.addActor(puntuaciones);
        stage.addActor(ayuda);
        stage.addActor(info);
        stage.addActor(alerta);

        mainGame.getMusicGame().setLooping(true);
        mainGame.getMusicGame().setVolume(0.5f);
        mainGame.getMusicMenu().setLooping(true);
        mainGame.getMusicMenu().setVolume(0.5f);
        

    }
    
    @Override
    public void show(){

        mainGame.getMusicGame().stop();
        mainGame.getMusicMenu().play();

    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
	    stage.draw();
    }
    
    @Override 
    public void hide(){
        mainGame.getMusicMenu().stop();
        mainGame.getMusicGame().play();

    }
    
    @Override
    public void dispose(){
        stage.dispose();
    }

    private void createWidgets(){

        fondo = new FondoMenu(mainGame.getFondoMenu());

        mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.left();

        //ventanas
        Options ventanaOpciones = new Options(this ,mainGame);
        opciones = ventanaOpciones.getTable();
        opciones.setVisible(false);

        Puntuacion ventanaPuntuacion = new Puntuacion(this, mainGame);
        puntuaciones = ventanaPuntuacion.getTable();
        puntuaciones.setVisible(false);

        Dialogo ventanaAyuda = new Dialogo(this, mainGame, "Ayuda","sfjsadnfjasdfsdfhsajdfjhasdf\nasdfasdfsfd");
        ayuda = ventanaAyuda.getTable();
        ayuda.setVisible(false);

        Dialogo ventanaInfo = new Dialogo(this, mainGame,"Info" ,"sfjsadnfjasdfsdfhsajdfjhasdf\nasdfasdfsfd");
        info = ventanaInfo.getTable();
        info.setVisible(false);

        Dialogo alerta = new Dialogo(this, mainGame,"Alerta" ,"Introduzca un nombre\npara continuar");
        this.alerta = alerta.getTable();
        this.alerta.setVisible(false);

        //botones
        botonJugar = new TextButton("Jugar",skin);
        botonOpciones = new TextButton("opciones",skin);
        botonPuntuacion = new TextButton("Puntuacion",skin);
        botonInfo = new TextButton("Info",skin);
        botonAyuda = new TextButton("ayuda",skin);

        botonJugar.addListener(botonJugarHandler());
        botonOpciones.addListener(botonOpcionHandler());
        botonPuntuacion.addListener(botonPuntuacionHandler());
        botonAyuda.addListener(botonAyudaHandler());
        botonInfo.addListener(botonInfoHandler());

        botonInfo.getLabel().setFontScale(0.5f);
        botonAyuda.getLabel().setFontScale(0.5f);

        //panel nombre del jugador
        panelJugador = new Window("",skin);
        panelJugador.setPosition(500, 300);
        panelJugador.setMovable(false);

        nombreCaja = new TextField("",skin);

    }

    private void addToPanelJugador(){
        Label nombre = new Label("Nombre:",skin);

        panelJugador.add(nombre).pad(10, 0, 5, 0);
        panelJugador.row();
        panelJugador.add(nombreCaja).pad(5, 0, 10, 0);
    }

    private void addToTable(){

        mainTable.add(botonJugar).pad(120,170,10,0);
        mainTable.row();
        mainTable.add(botonOpciones).pad(10,170,10,0);
        mainTable.row();
        mainTable.add(botonPuntuacion).pad(10,170,50,0);
        mainTable.row();
        mainTable.add(panelJugador).pad(20,170,0,0);


        mainTable.add(botonInfo).pad(0, 430, 0, -60).maxHeight(40).maxWidth(60).bottom();
        mainTable.add(botonAyuda).maxHeight(40).maxWidth(80).bottom().padRight(-30);

    }

    private ChangeListener botonJugarHandler(){
        return new ChangeListener() {
                @Override
                public void changed (ChangeEvent event, Actor actor) {
                if(!nombreCaja.getText().equals("")){
                    mainGame.setScreen(new Mundo1(mainGame, nombreCaja.getText()));
                    mainGame.getPlaySound().play(mainGame.getSfxVolumen());
                }
                else{
                    alerta.setVisible(true);
                }
                }
            };
    }

    private ChangeListener botonOpcionHandler(){

        return new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(!ventanaAbierta){
                    opciones.setVisible(true);
                    ventanaAbierta = true;
                    mainGame.getClickSound().play(mainGame.getSfxVolumen());
                }
            }
        };
    }

    private ChangeListener botonPuntuacionHandler(){

        return new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(!ventanaAbierta){
                    puntuaciones.setVisible(true);
                    ventanaAbierta = true;
                    mainGame.getClickSound().play(mainGame.getSfxVolumen());
                }
            }
        };
    }
    private ChangeListener botonAyudaHandler(){

        return new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(!ventanaAbierta){
                    ayuda.setVisible(true);
                    ventanaAbierta = true;
                    mainGame.getClickSound().play(mainGame.getSfxVolumen());
                }
            }
        };
    }


    private ChangeListener botonInfoHandler(){

        return new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(!ventanaAbierta){
                    info.setVisible(true);
                    ventanaAbierta = true;
                    mainGame.getClickSound().play(mainGame.getSfxVolumen());
                }
            }
        };
    }


    public void setVentanaAbierta(boolean b){
        ventanaAbierta = b;
    }




    
}
