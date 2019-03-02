package punneria;

import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;

public class soundEffects {
public static boolean playLaunch() {
	Rhythm rhythem = new Rhythm()
			.addLayer("s.s.s.s.s.s.s")
			.addLayer("`.`.`.`.`.`.`");
	new Player()
	.play("I[CHOIR_AAHS] cw ew gw bq aw I A");
	new Player()
	.play(rhythem);
	new Player()
	.play("i[CHOIR_AAHS] cw ew gw aq bw");
	return true;
}
}
