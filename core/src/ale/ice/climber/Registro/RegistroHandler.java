package ale.ice.climber.Registro;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;


public class RegistroHandler {

    FileHandle file;
    RegistrosJugador registros;

    public RegistroHandler() {
        this.file = Gdx.files.local("log/log.json");
        String stringFile = this.file.readString();

        Json json = new Json();

        registros = json.fromJson(RegistrosJugador.class, stringFile);

        if(registros == null){
            registros = new RegistrosJugador();
        }
    }

    public RegistrosJugador getRegistros() {
        return registros;
    }

    public void guardarRegistro(){

        Json json = new Json();
        String registroJson = json.toJson(registros);

        file.writeString(registroJson, false);
    }

    public SortedMap<Integer,String> getMap(){
        SortedMap<Integer, String> registrosOrdenados;
        registrosOrdenados = new TreeMap(java.util.Collections.reverseOrder());

        for(int i=0; i<registros.getRegistros().size(); i++){
            registrosOrdenados.put(registros.getRegistros().get(i).getPuntos(), registros.getRegistros().get(i).getNombre());
        }

        return registrosOrdenados;

    }


}
