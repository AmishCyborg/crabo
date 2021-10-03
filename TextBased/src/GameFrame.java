import java.awt.Container;
import java.util.Random;
import java.util.Scanner;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.event.*;
import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

public class GameFrame extends JFrame implements ActionListener {
	private int width;
	private int height;

	static int nr = 1;
	static int przygoda = 1;

	int HP = 100;
	static Scanner scan = new Scanner(System.in);
	static Random rand = new Random();
	private JButton atk, hp, run, submit;
	private JTextArea textArea;
	private Container contentPane, nickW;
	private JTextField textField, textFieldO;
	static String input;
	static String Nick;
	String odp;

	static boolean running = true;
	static String[] enemiesT1 = { "Chochlik", "Duzy Pajak", "Szkielet", "Zombie" };
	static String[] enemiesT2 = { "Ork", "Kultysta", "Ghul", "Wampir" };
	static String[] bossy = { "Demon", "Przywodca Kultyst�w", "Demoniczny Pomiot" };

	public GameFrame(int w, int h) {
		width = w;
		height = h;
		contentPane = this.getContentPane();
		textArea = new JTextArea();
		atk = new JButton("Atak");
		hp = new JButton("Health Potion");
		run = new JButton("run!");
		nickW = this.getContentPane();
		textField = new JTextField();
		textFieldO = new JTextField();
	}
	public  void nickWindow() {
		this.setSize(width, height);
		this.setTitle("Type your Name: ");
		submit = new JButton("Submit");
		submit.addActionListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setFocusable(true);
		this.setLocationRelativeTo(null);
		nickW.setLayout(new FlowLayout());
		nickW.add(textField);
		nickW.add(submit);
		textField.setPreferredSize(new Dimension(250, 30));
		this.setVisible(true);

	}

	public static void death() {
		player.HP=100;
		player.EXP=0;
		player.expNeeded=5;
		player.room=0;
		player.healthPotions=2;
		player.torchOwned=1;
		player.torchUsed=10;
		player.ifSword=0;
		player.oddFigurineOwned=0;
				
	}
	public static void Thread() {
		for (int i = 5; i > 0; i--) {
			System.out.println("#");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
			input = textField.getText();
			System.out.println("Welcome " + input);
			this.dispose();
		}
	}

	public static void eq() {
		System.out.println("W Twoim ekwipunku znajduje si�: \n");
		if (player.shieldOwned == 1) {
			System.out.println("\tTarcza");
		}
		if (player.ifSword == 1) {
			System.out.println("\tMiecz");
		} else {
			System.out.println("\tMaczuga");
			System.out.println("\t" + player.healthPotions + " Mikstur �ycia");
			System.out.println("\t" + player.Gold + " Sztuk Z�ota\n");
			System.out.println("\t " + player.torchOwned + " pochodni");
		}
	}

	public static void shop() {
		System.out.println("____________________________________");
		System.out.println("Witaj w Karczmie!");
		System.out.println("Co chcesz teraz zrobi�?");
		System.out.println("\t1. Wyrusz do Lochu!");
		System.out.println("\t2. Kup Mikstury �ycia");
		System.out.println("\t3. Kup Miecz");
		System.out.println("\t4. Zapisz swoja dotychczasowa przygode.");
		System.out.println("\t5. Sprawdz swoj ekwipunek");

	}

	public static void actualGame() {
		System.out.println("\tZaczynasz przygode wchodzac do Lochu! ");
		GRA: while (running) {
			System.out.println("____________________________________");
			int enemyHP = rand.nextInt(Foe.maxEnemyHPT1);
			String enemy = enemiesT1[rand.nextInt(enemiesT1.length)];
			// String enemy2 = enemiesT2[rand.nextInt(enemiesT2.length)];

			System.out.println("\tCo� czai si� w tym pokuju");
			// System.out.println("____________________________________");
			System.out.println("\tW lochu spotykasz : " + enemy);

			while (enemyHP > 0) {
				System.out.println("____________________________________");
				System.out.println("\n\tCo chcesz zrobi�?");
				System.out.println("\t1. Zaatakuj");
				System.out.println("\t2. Wypij Miksture");
				System.out.println("\t3. Sprawdz sw�j ekwipunek");
				System.out.println("\t4. Ucieknij!");
				System.out.println("\t5. Odnow pochodnie\n");
				System.out.println("\tTwoje HP: " + player.HP);
				System.out.println("\tHP " + enemy + " to: " + enemyHP);
				System.out.println("\tZostalo Ci jeszcze " + player.torchUsed + "0% pochodni\n");
				System.out.println("\tJeste� w " + player.room + " pokju");

				String odp1 = scan.nextLine();
				if (odp1.equals("1")) {
					int dmgDealt = rand.nextInt(player.playerDPS) + player.dmgBoost;
					int dmgTaken = rand.nextInt(Foe.enemyDPS);

					enemyHP -= dmgDealt;
					player.HP -= dmgTaken;
					player.torchUsed--;
					System.out.println("\tZada�e�: " + enemy + " " + dmgDealt + " punkt�w obra�e�");
					System.out.println("\tW odwecie otrzyma�e� " + dmgTaken + " punkt�w obra�e�");
					System.out.println("\tZostalo Ci jeszcze " + player.torchUsed + "0% pochodni");
					if (player.torchOwned == 0 && player.torchUsed == 0) {
						System.out.println("O nie �wiat�o zgas�o!,znikno�e� w ciemno�ciach");
						break;
					}

					if (player.HP < 1) {
						System.out.println("\tJests za slaby by walczyc dalej");
						break;
					}

				} else if (odp1.equals("2")) {

					if (player.HP >= 99) {
						System.out.println("Nie mo�esz miec wiecej ni� 100 punkt�w �ycia");
						System.out.println("Zostalo Ci " + player.healthPotions + " Mikstur �ycia");

					} else if (player.healthPotions > 0) {
						if (player.HP >= 76) {
							System.out.println("Nie mo�esz miec wiecej ni� 100 punkt�w �ycia");
							System.out.println("Zostalo Ci " + player.healthPotions + " Mikstur �ycia");

						}
						player.HP += player.healthPotionHeal;
						player.healthPotions--;
						System.out.println("Wypijajac Miksture �ycia odzyska�e� : " + player.healthPotionHeal);
						System.out.println("Masz teraz " + player.HP + " punkt�w �ycia");
						System.out.println("Zosta�o Ci " + player.healthPotions + " Mikstury");

					} else if (player.healthPotions == 0) {
						System.out.println("Nie masz ju� �adnych Mikstur �ycia!");
					}
				} else if (odp1.equals("3")) {
					eq();
				} else if (odp1.equals("4")) {
					System.out.println("### Uciek�e� przed " + enemy + " ###");
				} else if (odp1.equals("5")) {
					System.out.println("odnowi�es p�omie�");
					player.torchUsed = 10;

				} else {
					System.out.println("\t Spr�buj jeszcze raz!");
				}
			}
			if (player.HP < 1) {
				System.out.println("\tPrzegra�e� walk� z: " + enemy);
				System.out.println("\tStraci�e� wszystkie punkty �ycia i ledwo uda�o Ci si� uciec z lochu!");
				System.out.println("\tZacznij od pocz�tku");
				System.out.println("\tXXXXXXXXXXXXXXXXXXXXXXXXXXX");
				death();
				continue GRA;
			}
			System.out.println("____________________________________");
			System.out.println("Pokona�e�: " + enemy);
			System.out.println("Zosta�o Ci " + player.HP + " punkt�w �ycia");
			System.out.println("i zdobywasz 1 punkt do�wiadczenia!");
			player.EXP++;
			player.expNeeded--;
			System.out.println("Do nast�pnego poziomu brakuje Ci " + player.expNeeded + " punkt�w do�wiadczenia!");
			// hpDropChnce
			if (rand.nextInt(100) > player.healthPotionDropChance) {
				if (player.healthPotions >= 5) {
					System.out.println("## W truchle " + enemy + " znjdujesz Miksture �ycia ##");
					System.out.println("## Ale nie mo�esz mie� wi�cej ni� 5 Mikstur �ycia ##");
					continue GRA;
				}

				player.healthPotions++;
				System.out.println("## W truchle " + enemy + " znjdujesz Miksture �ycia ##");
				System.out.println("##Obecnie posiadasz " + player.healthPotions + " Mikstur �ycia ##");
			}
			// goldDropChance
			if (rand.nextInt(100) > player.goldDropChance) {
				player.Gold++;
				System.out.println("## w truchle " + enemy + " znajdujesz 1 sztuke z�ota ##");
				System.out.println("## Obecnie posiadasz " + player.Gold + " sztuk z�ota ##");
			}
			// torchDropChance
			if (rand.nextInt(100) > player.torchDropChance) {
				player.torchOwned++;
				System.out.println("## w truchle " + enemy + " znajdujesz 1 pochodnie ##");
				System.out.println("## Obecnie posiadasz " + player.torchOwned + " pochodni ##");
			}
			// LVLUP
			if (player.expNeeded == 0) {
				player.LVL++;
				player.EXP = 0;
				player.expNeeded = 5;
				System.out.println("Brawo awansujesz na nas�pny poziom!\nAkutalnie masz " + player.LVL + " poziom!");
				System.out.println("Twoje Punkty �ycia s� odnowione i zadajesz teraz wiecej obrazen!");
				player.HP = 100;
				player.dmgBoost += 5;
				System.out.println("Obecnie posiadasz : " + player.HP + " punkt�w �ycia!");
			}

			player.room++;
			System.out.println("____________________________________");
			System.out.println("Co chcesz dalej robi� ?");
			System.out.println("\t1. Walcz dalej!");
			System.out.println("\t2. Przeszukaj pomieszczenie");
			System.out.println("\t3. Sprawdz sw�j ekwipunek");
			System.out.println("\t4. Wyjdz z Lochu!");
			if (player.room == 5) {
				System.out.println("\t5. Mozesz sproba� zmierzy� si� z Bossem");

			}
			player.points = (player.room) * 100;
			String odp2 = scan.nextLine();

			AFFTER: while (!odp2.equals("1") && !odp2.equals("2") && !odp2.equals("3") && !odp2.equals("4")
					&& !odp2.equals("5")) {
				System.out.println("\t Spr�buj jeszcze raz!");
				odp2 = scan.nextLine();

			}
			if (odp2.equals("1")) {
				System.out.println("Idziesz Dalej");
				Thread();
				continue GRA;

			} else if (odp2.equals("4")) {
				System.out.println("Wychodzisz w lochu!");
				// System.out.println("Nie uda�o Ci si� powtstrzyma� Kultyst�w");
				System.out.println("Zdoby�e�: " + player.LVL + " poziom");
				System.out.println("Wracasz do miasta aby odnowi� si�y.");
				System.out.println("Zdoby�e� " + player.points + " punktow");

			} else if (odp2.equals("2")) {
				System.out.println("Zdecydowa�e� si� na przeszukanie pomieszczenia");
				if (rand.nextInt(100) > player.sweapRoom) {
					System.out.println("Znalaz�e� 1 sztuk� z�ota i przechdzisz do nast�pnego pokoju");
					player.Gold++;
					System.out.println("Obecnie posiadasz : " + player.Gold + " sztuk z�ota");
					Thread();
					continue GRA;
				} else {
					System.out.println("Nic nie znalaz�e�.");
					continue GRA;
				}
			} else if (odp2.equals("3")) {
				eq();
				continue GRA;
			} else if (odp2.equals("5")) {
				int bossHP = 100;
				String boss = bossy[rand.nextInt(bossy.length)];

				while (bossHP > 0) {
					System.out.println("W g��bi lochu znajdujesz wielki pokoj a w nim straszliwy " + boss);
					System.out.println("Sprobuj pokna� " + boss + " �eby przeszkodzi� kulystom");
					if (player.oddFigurineOwned == 1) {
						System.out.println("zdaje si�, �e dziwna figurka ktora dosta�e� od karczmarza os�abia " + boss);
						bossHP -= 50;

						System.out.println("\tTwoje HP: " + player.HP);
						System.out.println("\tHP " + boss + " to: " + bossHP);
						System.out.println("\n\t Co chcesz zrobi�?");
						System.out.println("\t1. Zaatakuj");
						System.out.println("\t2. Wypij Miksture");
						System.out.println("\t3. Sprawdz sw�j ekwipunek");
						System.out.println("\t4. Ucieknij!");
						String odp7 = scan.nextLine();
						if (odp7.equals("1")) {
							int dmgDealtB = rand.nextInt(player.playerDPS) + player.dmgBoost;
							int dmgTakenB = rand.nextInt(Foe.bossDPS);

							bossHP -= dmgDealtB;
							player.HP -= dmgTakenB;
							System.out.println("\t>Zada�e�: " + boss + " " + dmgDealtB + " punkt�w obra�e�!<");
							System.out.println("\t>W Odwecie otrzyma�e� " + dmgTakenB + " punkt�w obra�e�!<");
						}
						if (player.HP < 1) {
							System.out.println("\t" + boss + " pokonal Cie, os�abi� Cie i wyrzuci� z lochu!");
							death();
							System.out.println(
									"Obecnie masz " + player.LVL + " poziom oraz " + player.Gold + " sztuk z�ota");
							break;
						}
						System.out.println("____________________________________");
						System.out.println("Pokona�e�: " + boss);
						System.out.println("Zosta�o Ci " + player.HP + " punkt�w �ycia");
						System.out.println("Z�o zosta�o pokonane");
						break;
					}

				}

			}

			SHOP: while (running) {
				shop();
				String odp0 = scan.nextLine();
				if (odp0.equals("1")) {
					System.out.println("Wyruszasz do Lochu!");
					System.out.println("Spr�buj pokona� Kultst�w, kt�rzy pr�buj� przej�c w�adze w kr�lestwie");
					break;
				} else if (odp0.equals("2")) {
					System.out.println("> Chcesz kupi� Mikstury �ycia?");
					System.out.println("> Na stanie mam " + player.healthPotionInShop + " Mikstur Zdrowia po "
							+ player.healthPotionPrice + " za sztuke");
					System.out.println("\t Zainteresowany?");
					System.out.println("\t1. Tak");
					System.out.println("\t2. Nie");
					String odp00 = scan.nextLine();

					if (odp00.equals("1")) {
						if (player.healthPotions >= 5) {
							System.out.println("Nie mo�esz mie� wi�cej ni� 5 Mikstur �ycia");
							continue SHOP;
						}
						if (player.Gold >= player.healthPotionPrice) {
							player.healthPotionInShop--;
							int spentHP = player.Gold - player.healthPotionPrice;
							player.healthPotions++;
							System.out.println("Kupi�e� 1 Miksture �ycia");
							System.out.println("Obecnie masz :" + player.healthPotions + " Mikstur �ycia");
							continue SHOP;
						} else {
							System.out.println("Nie masz tyle z�otych monet");
							continue SHOP;
						}
					} else if (odp00.equals("2")) {
						System.out.println("Ok");
						continue SHOP;

					}
				} else if (odp0.equals("3")) {
					if (!(player.LVL == 3)) {
						System.out.println(">Zapomnij masz za malo doswiadczenia!");
					} else {
						System.out.println("Na stanie mam �wietny miecz! Moge Ci go sprzeda� za " + player.swordPrice
								+ " sztuk z�ota");
						System.out.println("\t Zainteresowany?");
						System.out.println("\t1. Tak");
						System.out.println("\t2. Nie");
						String odp01 = scan.nextLine();

						if (odp01.equals("1")) {
							if (player.Gold >= player.swordPrice) {
								player.swordInShop--;
								player.swordOwned++;
								int spentSword = player.Gold - player.swordPrice;
								continue SHOP;
							} else {
								System.out.println("Nie masz tyle z�otych monet");
								continue SHOP;
							}
						} else if (odp01.equals("2")) {
							System.out.println("Ok");
							continue SHOP;
						}
					}

				} else if (odp0.equals("4")) {
					try {
						BufferedWriter bw = new BufferedWriter(
								new FileWriter("C:\\Users\\user\\eclipse-workspace\\TextBasedRpg\\output.csv"));
						bw.write("nr"+","+"Nick"+","+"LVL"+","+"GOLD"+"Points");
						bw.write(nr+","+input+","+player.LVL+","+player.Gold+","+player.points);
						bw.close();
					} catch (Exception e) {
						e.printStackTrace();

					}

					System.out.println("Udalo Ci sie zapisac stan gry");
				} else if (odp0.equals("5")) {
					eq();
				}

			} // System.out.println("THX for Playing");

		}
	}
}
