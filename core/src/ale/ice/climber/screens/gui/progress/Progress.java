package ale.ice.climber.screens.gui.progress;


import ale.ice.climber.Main;
import ale.ice.climber.screens.BaseScreen;
import ale.ice.climber.screens.gui.guiInMenu.Menu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Progress extends BaseScreen {

    private final ProgressBar bar;
    private final Stage stage;


    public Progress(Main mainGame) {
        super(mainGame);

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));

        Label titulo = new Label("Ice Climber", mainGame.getSkinUI(), "title");
        titulo.setScale(2);
        bar = new ProgressBar(0,1,0.01f,false, mainGame.getSkinUI());


        Table table = new Table();
        table.setFillParent(true);
        table.add(titulo).padBottom(10);
        table.row();
        table.add(bar).minWidth(400).padTop(10);

        stage.addActor(table);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(30/255f, 23/255f, 23/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        bar.setValue(mainGame.getManager().getProgress());
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        if(mainGame.getManager().update()){
            mainGame.setScreen(new Menu(mainGame)) ;
        }
    }
}
