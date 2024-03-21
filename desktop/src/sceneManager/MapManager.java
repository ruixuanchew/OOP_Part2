package sceneManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MapManager {
    private OrthographicCamera camera;
    private TiledMap map;
    private MapLayer objectLayer;
    private MapLayer lavaLayer;
    private OrthogonalTiledMapRenderer renderer; // renders map to the screen

    public MapManager() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();

    }

    public void loadMap(String mapFilePath) {
        map = new TmxMapLoader().load(mapFilePath);
        renderer = new OrthogonalTiledMapRenderer(map);
        objectLayer = map.getLayers().get("object");
        lavaLayer = map.getLayers().get("lava");
    }

    public MapObjects getObject() {
        return objectLayer.getObjects();
    }

    public MapObjects getLavaObject() {
        return lavaLayer.getObjects();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public OrthogonalTiledMapRenderer getRenderer() {
        return renderer;
    }
}