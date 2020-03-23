package ale.ice.climber.screens.gui.guiInMenu.dialogo;


import ale.ice.climber.Main;
import ale.ice.climber.screens.gui.guiInMenu.Menu;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;



public class Dialogo {
    private Skin skin;
    private Menu menu;
    private Main mainGame;
    private Table table;
    private Window ventana;
    private TextButton aceptar;
    private Label content;
    String contentStr;
    String title;


    public Dialogo(Menu menu, Main mainGame,String title ,String contentStr) {
        this.skin = mainGame.getSkinUI();
        this.menu = menu;
        this.mainGame = mainGame;
        this.contentStr = contentStr;
        this.title = title;

        createWidgets();
        addToWindow();

    }

    public void createWidgets() {

        table = new Table();
        table.setFillParent(true);
        table.center();


        ventana = new Window(title, skin);
        ventana.setMovable(false);

        content = new Label(contentStr,skin, "dark");

        aceptar = new TextButton("Continuar", skin);
        aceptar.getLabel().setFontScale(0.5f);
        aceptar.addListener(botonContinuarHandler());


    }

    public void addToWindow() {
        ventana.add(content);
        ventana.row();
        ventana.add(aceptar).maxHeight(40).maxWidth(80);

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