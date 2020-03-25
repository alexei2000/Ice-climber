package ale.ice.climber.screens.transicion;

import ale.ice.climber.Main;
import ale.ice.climber.screens.BaseScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;


public class Transicion extends BaseScreen {

    private AnimacionJugador animacionJugador;
    private Stage stage;

    private String titulo;
    private int puntosFrutas;
    private int tiempo;
    private int puntosTotal;

    private float contador;
    private float velocidad;
    private float state;
    private int puntosTiempo;


    private Table tabla;

    private Label iceClimberLabel;
    private Label titleLabel;
    private Label tiempoLabel;
    private Label numFrutasLabel;
    private Label puntosTiempoLabel;
    private Label puntosFrutasLabel;
    private Label puntosTotalLabel;
    private Label puntosTotalNivelLabel;

    private BaseScreen screen;



    public Transicion(Main mainGame, String titulo, BaseScreen screen ,int puntosFrutas, int tiempo, int puntosTotal) {
        super(mainGame);

        this.velocidad = 50;
        this.state = 0;

        if(!titulo.equals("Juego Terminado")){
            this.puntosTiempo = 100000/tiempo;
        }
        else{
            this.puntosTiempo = 0;
        }


        this.titulo = titulo;
        this.puntosFrutas = puntosFrutas;
        this.tiempo = tiempo;
        this.puntosTotal = puntosTotal;
        this.contador = 0;
        this.stage = new Stage();
        this.animacionJugador = new AnimacionJugador(mainGame.getJugadorTexture());

        this.screen = screen;

    }

    @Override
    public void show() {
        super.show();
        createWidgets();
        addToStage();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(30/255f, 23/255f, 23/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        actualizar(delta);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void hide() {
        super.hide();
        dispose();
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
    }

    private void createWidgets(){
        tabla = new Table(mainGame.getSkinUI());
        tabla.setFillParent(true);

        iceClimberLabel = new Label("Ice Climber",mainGame.getSkinUI(), "title");

        titleLabel = new Label(titulo,mainGame.getSkinUI());
        tiempoLabel = new Label("Tiempo: ",mainGame.getSkinUI());
        numFrutasLabel = new Label("Frutas: ",mainGame.getSkinUI());

        puntosTiempoLabel = new Label("Puntos: ",mainGame.getSkinUI());
        puntosFrutasLabel = new Label("Puntos: ",mainGame.getSkinUI());
        puntosTotalNivelLabel = new Label("Puntos Del Nivel: ",mainGame.getSkinUI());
        puntosTotalLabel = new Label("Puntos Acomulados:",mainGame.getSkinUI());

    }

    private void addToStage(){
        tabla.add(iceClimberLabel).colspan(2).pad(0,0,0,0);
        tabla.row();
        tabla.add(titleLabel).colspan(2).pad(50,0,25,0);
        tabla.row().pad(25,0,0,0);
        tabla.add(tiempoLabel).left().minWidth(150);
        tabla.add(puntosTiempoLabel).left().minWidth(150);
        tabla.row().pad(25,0,0,0);
        tabla.add(numFrutasLabel).left().minWidth(150);
        tabla.add(puntosFrutasLabel).left().minWidth(150);
        tabla.row().pad(25,0,0,0).minWidth(300);
        tabla.add(puntosTotalNivelLabel).colspan(2);
        tabla.row().pad(25,0,200,0).colspan(2).minWidth(300);
        tabla.add(puntosTotalLabel);

        stage.addActor(tabla);
        stage.addActor(animacionJugador);

    }

    private void actualizar(float delta){

        if(!animacionJugador.getPuedeCaminar()){
            if(state == 0){
                contador+=velocidad*delta;
                tiempoLabel.setText("Tiempo: "+(int)contador);
                if(contador>tiempo){
                    tiempoLabel.setText("Tiempo: "+tiempo);
                    state = 1;
                    contador = 0;
                }
            }
            if(state == 1){
                contador+=20*velocidad*delta;
                puntosTiempoLabel.setText("Puntos: "+(int)contador);
                if((int)contador> puntosTiempo){
                    puntosTiempoLabel.setText("Puntos: "+ puntosTiempo);
                    state = 2;
                    contador = 0;
                }
            }
            if(state == 2){
                contador+=velocidad*delta;
                numFrutasLabel.setText("Frutas: "+(int)contador);
                if((int)contador>(puntosFrutas/50)){
                    numFrutasLabel.setText("Frutas: "+puntosFrutas/50);
                    state = 3;
                    contador = 0;
                }
            }
            if(state == 3){
                contador+=10*velocidad*delta;
                puntosFrutasLabel.setText("Puntos: "+(int)contador);
                if((int)contador>puntosFrutas){
                    puntosFrutasLabel.setText("Puntos: "+puntosFrutas);
                    state = 4;
                    contador = 0;
                }
            }
            if(state == 4){
                contador+=20*velocidad*delta;
                puntosTotalNivelLabel.setText("Puntos Del Nievel: "+(int)contador);
                if((int)contador>(puntosFrutas + puntosTiempo)){
                    puntosTotalNivelLabel.setText("Puntos Del Nivel: "+(puntosFrutas + puntosTiempo));
                    state = 5;
                    contador = 0;
                }
            }
            if(state == 5){
                contador+=40*velocidad*delta;
                puntosTotalLabel.setText("Puntos Acomulados: "+(int)contador);
                if((int)contador> ((puntosFrutas + puntosTiempo)+puntosTotal)){
                    puntosTotalLabel.setText("Puntos Acomulados: "+ ((puntosFrutas + puntosTiempo)+puntosTotal));
                    state = 6;
                    contador = 0;
                    animacionJugador.puedeCaminar();
                }
            }
        }

        if(state == 6){
            if(animacionJugador.getX() > 1024){
                mainGame.setScreen(screen);
            }
        }

    }
}
