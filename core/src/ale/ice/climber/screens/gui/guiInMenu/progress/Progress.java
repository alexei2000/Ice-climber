package ale.ice.climber.screens.gui.guiInMenu.progress;


import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Progress{

    private final ProgressBar bar;
    private final Table table;
    private final Label titulo;


    public Progress(Skin skin) {

        titulo = new Label("Ice Climber",skin,"title");
        titulo.setScale(2);
        bar = new ProgressBar(0,1,0.01f,false, skin,"fancy");


        table = new Table();
        table.setFillParent(true);
        table.add(titulo).padBottom(10);
        table.row();
        table.add(bar).minWidth(400).padTop(10);

    }

    public Table getTable() {
        return table;
    }
    public void setProgress(float progress){
        bar.setValue(progress);
    }


}
