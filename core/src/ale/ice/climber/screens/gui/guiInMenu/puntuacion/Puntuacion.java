package ale.ice.climber.screens.gui.guiInMenu.puntuacion;


import ale.ice.climber.Main;
import ale.ice.climber.screens.gui.guiInMenu.Menu;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.ArrayList;


public class Puntuacion {
    private Skin skin;
    private Menu menu;
    private Main mainGame;
    private Table table;
    private Window ventana;
    private List <String> lista;
    private TextButton continuar;


    public Puntuacion(Skin skin, Menu menu, Main mainGame) {
        this.skin = skin;
        this.menu = menu;
        this.mainGame = mainGame;
        createWidgets();
        addToWindow();

    }

    public void createWidgets() {

        table = new Table();
        table.setFillParent(true);
        table.center();


        ventana = new Window("Puntuacion", skin);
        ventana.setMovable(false);
        lista = new List<>(skin);

        String[] palabras = new String[10];
        palabras[0] = "hola";
        palabras[1] = "hola";
        palabras[2] = "hola";
        palabras[3] = "hola";
        palabras[4] = "hola";
        palabras[5] = "hola";
        palabras[6] = "hola";
        palabras[7] = "hola";
        palabras[8] = "hola";
        palabras[9] = "hola";
        lista.setItems(palabras);


        continuar = new TextButton("Continuar", skin);
        continuar.getLabel().setFontScale(0.5f);
        continuar.addListener(botonContinuarHandler());


    }

    public void addToWindow() {
        ventana.add(lista).minWidth(200);
        ventana.row();
        ventana.add(continuar).maxHeight(40).maxWidth(80);

        table.add(ventana);
    }

    private ChangeListener botonContinuarHandler() {

        return new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                table.setVisible(false);
                menu.setVentanaAbierta(false);
                mainGame.getClickSound().play(mainGame.getSfxVolumen());
            }
        };
    }


    public Table getTable() {
        return table;
    }

}