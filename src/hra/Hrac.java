package hra;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;

public class Hrac {

	
	// velikost hrace - konstanty
	public static final int SIRKA = 40;
	public static final int VYSKA = 33;

	// velikost skoku hrace
	private static final int KOEF_ZRYCHLENI = 1;

	// rychlost padu hrace
	private static final int KOEF_RYCHLOST = 2;

	// pro nakresleni do pameti, uklada se
	private BufferedImage img = null;

	// pocatecni X-ova pozice hrace, nemeni se (hrac neskace dopredu ani dozadu)
	private int x;

	// pocatecni Y-ova pozice hrace, meni se (hrac skace nahoru a dolu)
	private int y;

	// bude ovlivnovano koeficientem zrychleni a rychlosti padu
	private int rychlost;

	public Hrac(BufferedImage img) {
		this.img = img;
		x = (HraciPlocha.SIRKA / 2) - (img.getWidth() / 2); // umisteni na stred
															// v X
		y = HraciPlocha.VYSKA / 2; // umisteni na stred v Y

		rychlost = KOEF_ZRYCHLENI; // nastaveni pocatecni rychlosti
	}

	// vola se po narazu do zdi ci kraje okna
	public void reset() {
		y = HraciPlocha.VYSKA / 2; // znovu nastaveni na stred v Y
		rychlost = KOEF_RYCHLOST; // opet nastaveni pocatecni rychlosti
	}

	// kde se hrac nachazi
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	// kdyz se klikne, vola se metoda skok
	public void skok() {
		rychlost = -17;
	}

	// zajistuje pohyb hrace
	public void posun() {
		rychlost = rychlost + KOEF_RYCHLOST;
		y = y + rychlost;
	}

	// vykresleni hrace
	public void paint(Graphics g) {
		g.drawImage(img, x, y, null);
	}

	// vyska hrace, kvuli narazum
	public int getVyskaHrace() {
		return img.getHeight();
	}

	// vraci pomyslny ctverec/obdelnik, ktery opisuje hrace - kolizni
	public Rectangle getMez() {
		return new Rectangle(x, y, img.getWidth(), img.getHeight());
	}
}
