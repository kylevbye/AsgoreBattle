package games.byekv1.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.utils.GdxRuntimeException;

import games.byekv1.AsgoreBattle;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Undertale AU: Asgore Genocide Battle";
		//config.addIcon("data/asgoreSilhouette.png", Files.FileType.Internal);
		new LwjglApplication(new AsgoreBattle(), config);
	}
}
