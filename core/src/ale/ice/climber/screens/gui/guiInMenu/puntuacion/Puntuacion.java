package ale.ice.climber.screens.gui.guiInMenu.puntuacion;


import ale.ice.climber.Main;
import ale.ice.climber.Registro.RegistroHandler;
import ale.ice.climber.screens.gui.guiInMenu.Menu;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;


public class Puntuacion {
    private Skin skin;
    private Menu menu;
    private Main mainGame;
    private Table table;
    private Window ventana;
    private List <String> lista;
    private TextButton continuar;


    public Puntuacion(Menu menu, Main mainGame) {
        this.skin = mainGame.getSkinUI();
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

        generateList();

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

    private void generateList(){

        RegistroHandler registroHandler = new RegistroHandler();

        SortedMap<Integer, String> map;
        map = registroHandler.getMap();

        String[] listaRegistros = new String[map.size()>10 ? 10 : map.size()];
        int i = 0;

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            listaRegistros[i] = value + ": " + key;
            i++;
            if(i==10){
                break;
            }
        }

        lista.setItems(listaRegistros);

    }


    public Table getTable() {
        return table;
    }

}