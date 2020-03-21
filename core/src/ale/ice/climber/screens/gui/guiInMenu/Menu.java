/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ale.ice.climber.screens.gui.guiInMenu;

import ale.ice.climber.Main;
import ale.ice.climber.screens.BaseScreen;
import ale.ice.climber.screens.gui.guiInMenu.fondo.FondoMenu;
import ale.ice.climber.screens.worlds.mundo1.Mundo1;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 *
 * @author alexe
 */
public class Menu extends BaseScreen{
    
    private final Stage stage;
    private final Table mainTable;
    //private final Table desplegable;
    private final Window window;
    private final Skin skin;
    private final FondoMenu fondo;
    private final TextButton botonOpciones;
    private final TextButton botonJugar;
    private final TextButton botonPuntuacion;
    private final TextField  nombreCaja;
    
    public Menu(final Main mainGame) {
        super(mainGame);
        
        skin = mainGame.getSkinUI();
        
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
	Gdx.input.setInputProcessor(stage);
        
        fondo = new FondoMenu(mainGame.getFondoMenu());
        stage.addActor(fondo);

	mainTable = new Table();
	mainTable.setFillParent(true);
        mainTable.left();
        
        //mainTable.setDebug(true); 
	stage.addActor(mainTable);

        
        botonJugar = new TextButton("Jugar",skin);
        botonJugar.addListener(new ChangeListener() {
        @Override
	public void changed (ChangeEvent event, Actor actor) {
		mainGame.setScreen(new Mundo1(mainGame));
	}
        });
                
        botonOpciones = new TextButton("opciones",skin);
        botonPuntuacion = new TextButton("Puntuacion",skin);
        
        window = new Window("",skin);
        window.setPosition(500, 300);
        
        nombreCaja = new TextField("",skin);
        
        window.add(new Label("Nombre:",skin)).pad(10, 0, 5, 0);
        window.row();
        window.add(nombreCaja).pad(5, 0, 10, 0);
        window.setMovable(false);
        
        mainTable.add(botonJugar).pad(120,170,10,0);
        mainTable.row();
        mainTable.add(botonOpciones).pad(10,170,10,0);
        mainTable.row();
        mainTable.add(botonPuntuacion).pad(10,170,50,0);
        mainTable.row();
        mainTable.add(window).pad(20,170,0,0);
        TextButton but = new TextButton("Info",skin);
        but.getLabel().setFontScale(0.5f);
        mainTable.add(but).pad(0, 430, 0, -60).maxHeight(40).maxWidth(60).bottom();
        TextButton but1 = new TextButton("ayuda",skin);
        but1.getLabel().setFontScale(0.5f);
        mainTable.add(but1).maxHeight(40).maxWidth(80).bottom().padRight(-30);
    }
    
    @Override
    public void show(){
        
    
        
       
        
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
        
    }
    
    @Override
    public void dispose(){
        stage.dispose();
    }
    
}
