package ale.ice.climber.screens.transicion;

import ale.ice.climber.Main;
import ale.ice.climber.screens.BaseScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Transicion extends BaseScreen {

    private String titulo;
    private int puntosFrutas;
    private int tiempo;


    public Transicion(Main mainGame, String titulo, int puntosFrutas, int tiempo) {
        super(mainGame);

        this.titulo = titulo;
        this.puntosFrutas = puntosFrutas;
        this.tiempo = tiempo;
        
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void hide() {
        super.hide();
    }
}
