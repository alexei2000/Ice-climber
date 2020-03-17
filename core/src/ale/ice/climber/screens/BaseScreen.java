/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ale.ice.climber.screens;

import ale.ice.climber.Main;
import com.badlogic.gdx.Screen;

/**
 *
 * @author alexe
 */
public abstract class BaseScreen implements Screen {
    
    protected final Main mainGame;
    
    public BaseScreen(Main mainGame){
        this.mainGame = mainGame;
    }

    @Override
    public void show() {
        
    }

    @Override
    public void render(float delta) {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
    
}
