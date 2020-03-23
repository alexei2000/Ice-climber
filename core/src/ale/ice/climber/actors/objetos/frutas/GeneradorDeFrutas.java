package ale.ice.climber.actors.objetos.frutas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.List;

public abstract class  GeneradorDeFrutas {

    public List<Fruta> listaDeFrutas;
    protected World world;
    protected final float alturaNivel = 4f;
    protected int countNiveles;
    protected int countEspacios;
    protected Vector2 position;
    protected Texture texture;
    protected TextureRegion[][] texturas;

    public GeneradorDeFrutas(World world, Texture texture){
        listaDeFrutas = new ArrayList<>();
        countNiveles = 9;
        countEspacios = 0;
        this.texture = texture;
        this.world = world;

        position = new Vector2(countEspacios,countNiveles*alturaNivel);

        TextureRegion texturasCompleta = new TextureRegion(texture,256,256);
        texturas = texturasCompleta.split(64,64);
    }

    public void generarFrutassEnLinea(int num, TextureRegion textureRecortada){

        if(countNiveles<16){
            for(int i=0; i<num; i++){
                listaDeFrutas.add(new Fruta(world,texture, textureRecortada,position));
                incrementarEspacio();
            }
        }

    }

    protected void incrementarEspacio(){
        if(countEspacios<15){
            countEspacios++;
        }
        else{
            countEspacios=0;
            countNiveles++;
        }
        position.x=countEspacios;
        position.y=countNiveles*alturaNivel;

    }

    protected void incrementarVariosEspacios(int veces){

        for(int i=0; i<veces; i++){
            incrementarEspacio();
        }
    }

    public void disposeList(){
        for (Fruta listaDeBloque : listaDeFrutas) {
            listaDeBloque.detach();
            listaDeBloque.remove();
        }
    }
}
