package ale.ice.climber.screens.worlds.mundo1;

import ale.ice.climber.actors.objetos.frutas.GeneradorDeFrutas;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

public class FrutasMundo1 extends GeneradorDeFrutas {

    public FrutasMundo1(World world, Texture texture) {
        super(world, texture);

        incrementarVariosEspacios(4);
        generarFrutassEnLinea(1,texturas[0][0]);
        incrementarVariosEspacios(8);
        generarFrutassEnLinea(1,texturas[1][0]);
        incrementarVariosEspacios(4);
        generarFrutassEnLinea(1,texturas[0][2]);
        incrementarVariosEspacios(8);
        generarFrutassEnLinea(1,texturas[1][3]);
        incrementarVariosEspacios(4);
        generarFrutassEnLinea(1,texturas[0][2]);
        incrementarVariosEspacios(8);
        generarFrutassEnLinea(1,texturas[1][1]);
        incrementarVariosEspacios(4);
        generarFrutassEnLinea(1,texturas[0][2]);
        incrementarVariosEspacios(8);
        generarFrutassEnLinea(1,texturas[1][1]);








    }
}
