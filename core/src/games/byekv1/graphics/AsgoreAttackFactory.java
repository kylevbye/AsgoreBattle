package games.byekv1.graphics;

import java.util.ArrayList;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Polygon;

import games.byekv1.asgorebattle.scenes.BattleScene;
import games.byekv1.graphics.graphicloaders.ImageLoader;
import games.byekv1.output.VolumeManager;

public class AsgoreAttackFactory {

    ///
    /// CONSTANTS
    ///
    public static final int HURT_BLICK_CONSTANT = 50;

    public static HollowBox hollowBox;

    public static HealthBar healthBar;

    public static Sound damage;

    public static boolean playerImmune;
    public static int playerImmuneCount;

    public static MobileScreenObject soul = new MobileScreenObject(ImageLoader.loadImage("soul/playerSoul.png")) {
        @Override
        public void initBoundingPolygon() {
            //	Primitive float arrays init all vals to 0.
		    float[] vertices = new float[14];

		    vertices[0] = 0;
            vertices[1] = getHeight()*.375f;

            vertices[2] = 0;
            vertices[3] = getHeight()*.8125f;

            vertices[4] = getWidth()*.25f;
            vertices[5] = getHeight();

            vertices[6] = getWidth()*.75f;
            vertices[7] = getHeight();

            vertices[8] = getWidth();
            vertices[9] = vertices[1];

            vertices[10] = getWidth();
            vertices[11] = vertices[3];

            vertices[12] = getWidth()*.5f;
            vertices[13] = 0.f;

		    boundingPolygon = new Polygon(vertices);
            System.out.println("POLY\n");
            for (float f : vertices) System.out.format("%.2f ", f);
        }
    };

    private static AsgoreAttack attack1 = new AsgoreAttack(0, 600, 100, 200, AsgoreAttack.DETERMINATION) {
        
        @Override
        public void prepare() {
            super.prepare();
            for (MobileScreenObject mo : getAttackParts()) mo.setVisible(false);
            AsgoreAttackFactory.soul.setColor(Color.RED);
        }
        
        @Override
        public void update() {
            super.update();
            if (getCounter() >= getDuration() || !boxShrinked()) return;

            for (int i = 0; i<32; ++i) {
                MobileScreenObject mo = getAttackParts().get(i);
                mo.setVisible(true);
                mo.setPosition(hollowBox.getX()+hollowBox.getWidth()*.5f-mo.getWidth()*.5f+(float)(30*Math.sin(getCounter()*.03 + i*3)), BattleScene.WORLD_HEIGHT-getCounter()*1.2f+10*i);
            }

            for (MobileScreenObject mo : getAttackParts()) {
                if (mo.overlaps(soul)) {
                    if (!playerImmune){
                        healthBar.setHealthPoints(healthBar.getHealthPoints()-5);
                        damage.play(VolumeManager.volume);
                        playerImmune = true;
                        playerImmuneCount = 50;
                    }
                }
            }
            
        }

        @Override
        protected void init() {

            setAttackParts(new ArrayList<MobileScreenObject>());

            for (int i = 0; i<32; ++i) {
                MobileScreenObject particle = new MobileScreenObject(ImageLoader.loadImage("attack/asgoreParticle2.png"));
                getAttackParts().add(particle);
            }

        }
    };

    private static AsgoreAttack attack2 = new AsgoreAttack(0, 600, 50, 50, AsgoreAttack.DETERMINATION) {

        @Override
        public void prepare() {
            super.prepare();

            float leftX = BattleScene.WORLD_WIDTH*.5f-getAttackParts().get(0).getWidth();
            float rightX = BattleScene.WORLD_WIDTH*.5f;
            float space = 38.f;

            for (int i = 0; i<getAttackParts().size(); ++i) {
                MobileScreenObject mo = getAttackParts().get(i);
                mo.setVisible(false);

                //  X-Pos
                if (Math.round(Math.random()) == 0) {
                    mo.setX(leftX);
                }
                else mo.setX(rightX);

                //  Y-Pos
                mo.setY(BattleScene.WORLD_HEIGHT+space*i+mo.getWidth()*i);
            }
            AsgoreAttackFactory.soul.setColor(Color.RED);
        }
        
        @Override
        public void update() {
            super.update();
            if (getCounter() >= getDuration() || !boxShrinked()) return;

            for (int i = 0; i<getAttackParts().size(); ++i) {
                MobileScreenObject mo = getAttackParts().get(i);
                mo.setVisible(true);
                mo.moveDown(4.f);
            }

            for (MobileScreenObject mo : getAttackParts()) {
                if (mo.overlaps(soul)) {
                    if (!playerImmune){
                        healthBar.setHealthPoints(healthBar.getHealthPoints()-5);
                        damage.play(VolumeManager.volume);
                        playerImmune = true;
                        playerImmuneCount = 50;
                    }
                }
            }
            
        }

        @Override
        protected void init() {

            setAttackParts(new ArrayList<MobileScreenObject>());

            for (int i = 0; i<30; ++i) {
                MobileScreenObject particle = new MobileScreenObject(ImageLoader.loadImage("attack/asgoreParticle2.png"));
                particle.setSize(getBoxWidth()*.5f, getBoxWidth()*.5f);
                getAttackParts().add(particle);
            }

        }
    };

    public static AsgoreAttack getAttack(int id) {

        AsgoreAttack asgoreAttack = null;

        switch (id) {
            case 1:
                asgoreAttack = attack1;
                break;
            case 2: 
                asgoreAttack = attack2;
                break;
        }

        asgoreAttack.prepare();
        return asgoreAttack;
    }

}
