package ale.ice.climber.Registro;

import java.util.ArrayList;

public class RegistrosJugador {
    private ArrayList <RegistroJugador> registros;

    public RegistrosJugador(){
        registros = new ArrayList<>();
    }

    public RegistrosJugador(ArrayList<RegistroJugador> registros) {
        this.registros = registros;
    }

    public ArrayList<RegistroJugador> getRegistros() {
        return registros;
    }

    public void addRegistro(RegistroJugador registro) {
        this.registros.add(registro);
    }
}
