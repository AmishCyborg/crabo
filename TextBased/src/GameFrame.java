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
	static String[] bossy = { "Demon", "Przywodca Kultystów", "Demoniczny Pomiot" };

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
		System.out.println("W Twoim ekwipunku znajduje siê: \n");
		if (player.shieldOwned == 1) {
			System.out.println("\tTarcza");
		}
		if (player.ifSword == 1) {
			System.out.println("\tMiecz");
		} else {
			System.out.println("\tMaczuga");
			System.out.println("\t" + player.healthPotions + " Mikstur ¯ycia");
			System.out.println("\t" + player.Gold + " Sztuk Z³ota\n");
			System.out.println("\t " + player.torchOwned + " pochodni");
		}
	}

	public static void shop() {
		System.out.println("____________________________________");
		System.out.println("Witaj w Karczmie!");
		System.out.println("Co chcesz teraz zrobiæ?");
		System.out.println("\t1. Wyrusz do Lochu!");
		System.out.println("\t2. Kup Mikstury ¯ycia");
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

			System.out.println("\tCoœ czai siê w tym pokuju");
			// System.out.println("____________________________________");
			System.out.println("\tW lochu spotykasz : " + enemy);

			while (enemyHP > 0) {
				System.out.println("____________________________________");
				System.out.println("\n\tCo chcesz zrobiæ?");
				System.out.println("\t1. Zaatakuj");
				System.out.println("\t2. Wypij Miksture");
				System.out.println("\t3. Sprawdz swój ekwipunek");
				System.out.println("\t4. Ucieknij!");
				System.out.println("\t5. Odnow pochodnie\n");
				System.out.println("\tTwoje HP: " + player.HP);
				System.out.println("\tHP " + enemy + " to: " + enemyHP);
				System.out.println("\tZostalo Ci jeszcze " + player.torchUsed + "0% pochodni\n");
				System.out.println("\tJesteœ w " + player.room + " pokju");

				String odp1 = scan.nextLine();
				if (odp1.equals("1")) {
					int dmgDealt = rand.nextInt(player.playerDPS) + player.dmgBoost;
					int dmgTaken = rand.nextInt(Foe.enemyDPS);

					enemyHP -= dmgDealt;
					player.HP -= dmgTaken;
					player.torchUsed--;
					System.out.println("\tZada³eœ: " + enemy + " " + dmgDealt + " punktów obra¿eñ");
					System.out.println("\tW odwecie otrzyma³eœ " + dmgTaken + " punktów obra¿eñ");
					System.out.println("\tZostalo Ci jeszcze " + player.torchUsed + "0% pochodni");
					if (player.torchOwned == 0 && player.torchUsed == 0) {
						System.out.println("O nie œwiat³o zgas³o!,znikno³eœ w ciemnoœciach");
						break;
					}

					if (player.HP < 1) {
						System.out.println("\tJests za slaby by walczyc dalej");
						break;
					}

				} else if (odp1.equals("2")) {

					if (player.HP >= 99) {
						System.out.println("Nie mo¿esz miec wiecej ni¿ 100 punktów ¿ycia");
						System.out.println("Zostalo Ci " + player.healthPotions + " Mikstur ¯ycia");

					} else if (player.healthPotions > 0) {
						if (player.HP >= 76) {
							System.out.println("Nie mo¿esz miec wiecej ni¿ 100 punktów ¿ycia");
							System.out.println("Zostalo Ci " + player.healthPotions + " Mikstur ¯ycia");

						}
						player.HP += player.healthPotionHeal;
						player.healthPotions--;
						System.out.println("Wypijajac Miksture ¯ycia odzyska³eœ : " + player.healthPotionHeal);
						System.out.println("Masz teraz " + player.HP + " punktów ¯ycia");
						System.out.println("Zosta³o Ci " + player.healthPotions + " Mikstury");

					} else if (player.healthPotions == 0) {
						System.out.println("Nie masz ju¿ ¿adnych Mikstur ¯ycia!");
					}
				} else if (odp1.equals("3")) {
					eq();
				} else if (odp1.equals("4")) {
					System.out.println("### Uciek³eœ przed " + enemy + " ###");
				} else if (odp1.equals("5")) {
					System.out.println("odnowi³es p³omieñ");
					player.torchUsed = 10;

				} else {
					System.out.println("\t Spróbuj jeszcze raz!");
				}
			}
			if (player.HP < 1) {
				System.out.println("\tPrzegra³eœ walkê z: " + enemy);
				System.out.println("\tStraci³eœ wszystkie punkty ¯ycia i ledwo uda³o Ci siê uciec z lochu!");
				System.out.println("\tZacznij od pocz¹tku");
				System.out.println("\tXXXXXXXXXXXXXXXXXXXXXXXXXXX");
				death();
				continue GRA;
			}
			System.out.println("____________________________________");
			System.out.println("Pokona³eœ: " + enemy);
			System.out.println("Zosta³o Ci " + player.HP + " punktów ¯ycia");
			System.out.println("i zdobywasz 1 punkt doœwiadczenia!");
			player.EXP++;
			player.expNeeded--;
			System.out.println("Do nastêpnego poziomu brakuje Ci " + player.expNeeded + " punktów doœwiadczenia!");
			// hpDropChnce
			if (rand.nextInt(100) > player.healthPotionDropChance) {
				if (player.healthPotions >= 5) {
					System.out.println("## W truchle " + enemy + " znjdujesz Miksture ¯ycia ##");
					System.out.println("## Ale nie mo¿esz mieæ wiêcej ni¿ 5 Mikstur ¯ycia ##");
					continue GRA;
				}

				player.healthPotions++;
				System.out.println("## W truchle " + enemy + " znjdujesz Miksture ¯ycia ##");
				System.out.println("##Obecnie posiadasz " + player.healthPotions + " Mikstur ¯ycia ##");
			}
			// goldDropChance
			if (rand.nextInt(100) > player.goldDropChance) {
				player.Gold++;
				System.out.println("## w truchle " + enemy + " znajdujesz 1 sztuke z³ota ##");
				System.out.println("## Obecnie posiadasz " + player.Gold + " sztuk z³ota ##");
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
				System.out.println("Brawo awansujesz na nasêpny poziom!\nAkutalnie masz " + player.LVL + " poziom!");
				System.out.println("Twoje Punkty ¯ycia s¹ odnowione i zadajesz teraz wiecej obrazen!");
				player.HP = 100;
				player.dmgBoost += 5;
				System.out.println("Obecnie posiadasz : " + player.HP + " punktów ¯ycia!");
			}

			player.room++;
			System.out.println("____________________________________");
			System.out.println("Co chcesz dalej robiæ ?");
			System.out.println("\t1. Walcz dalej!");
			System.out.println("\t2. Przeszukaj pomieszczenie");
			System.out.println("\t3. Sprawdz swój ekwipunek");
			System.out.println("\t4. Wyjdz z Lochu!");
			if (player.room == 5) {
				System.out.println("\t5. Mozesz sprobaæ zmierzyæ siê z Bossem");

			}
			player.points = (player.room) * 100;
			String odp2 = scan.nextLine();

			AFFTER: while (!odp2.equals("1") && !odp2.equals("2") && !odp2.equals("3") && !odp2.equals("4")
					&& !odp2.equals("5")) {
				System.out.println("\t Spróbuj jeszcze raz!");
				odp2 = scan.nextLine();

			}
			if (odp2.equals("1")) {
				System.out.println("Idziesz Dalej");
				Thread();
				continue GRA;

			} else if (odp2.equals("4")) {
				System.out.println("Wychodzisz w lochu!");
				// System.out.println("Nie uda³o Ci siê powtstrzymaæ Kultystów");
				System.out.println("Zdoby³eœ: " + player.LVL + " poziom");
				System.out.println("Wracasz do miasta aby odnowiæ si³y.");
				System.out.println("Zdoby³eœ " + player.points + " punktow");

			} else if (odp2.equals("2")) {
				System.out.println("Zdecydowa³eœ siê na przeszukanie pomieszczenia");
				if (rand.nextInt(100) > player.sweapRoom) {
					System.out.println("Znalaz³eœ 1 sztukê z³ota i przechdzisz do nastêpnego pokoju");
					player.Gold++;
					System.out.println("Obecnie posiadasz : " + player.Gold + " sztuk z³ota");
					Thread();
					continue GRA;
				} else {
					System.out.println("Nic nie znalaz³eœ.");
					continue GRA;
				}
			} else if (odp2.equals("3")) {
				eq();
				continue GRA;
			} else if (odp2.equals("5")) {
				int bossHP = 100;
				String boss = bossy[rand.nextInt(bossy.length)];

				while (bossHP > 0) {
					System.out.println("W g³êbi lochu znajdujesz wielki pokoj a w nim straszliwy " + boss);
					System.out.println("Sprobuj poknaæ " + boss + " ¿eby przeszkodziæ kulystom");
					if (player.oddFigurineOwned == 1) {
						System.out.println("zdaje siê, ¿e dziwna figurka ktora dosta³eœ od karczmarza os³abia " + boss);
						bossHP -= 50;

						System.out.println("\tTwoje HP: " + player.HP);
						System.out.println("\tHP " + boss + " to: " + bossHP);
						System.out.println("\n\t Co chcesz zrobiæ?");
						System.out.println("\t1. Zaatakuj");
						System.out.println("\t2. Wypij Miksture");
						System.out.println("\t3. Sprawdz swój ekwipunek");
						System.out.println("\t4. Ucieknij!");
						String odp7 = scan.nextLine();
						if (odp7.equals("1")) {
							int dmgDealtB = rand.nextInt(player.playerDPS) + player.dmgBoost;
							int dmgTakenB = rand.nextInt(Foe.bossDPS);

							bossHP -= dmgDealtB;
							player.HP -= dmgTakenB;
							System.out.println("\t>Zada³eœ: " + boss + " " + dmgDealtB + " punktów obra¿eñ!<");
							System.out.println("\t>W Odwecie otrzyma³eœ " + dmgTakenB + " punktów obra¿eñ!<");
						}
						if (player.HP < 1) {
							System.out.println("\t" + boss + " pokonal Cie, os³abi³ Cie i wyrzuci³ z lochu!");
							death();
							System.out.println(
									"Obecnie masz " + player.LVL + " poziom oraz " + player.Gold + " sztuk z³ota");
							break;
						}
						System.out.println("____________________________________");
						System.out.println("Pokona³eœ: " + boss);
						System.out.println("Zosta³o Ci " + player.HP + " punktów ¯ycia");
						System.out.println("Z³o zosta³o pokonane");
						break;
					}

				}

			}

			SHOP: while (running) {
				shop();
				String odp0 = scan.nextLine();
				if (odp0.equals("1")) {
					System.out.println("Wyruszasz do Lochu!");
					System.out.println("Spróbuj pokonaæ Kultstów, którzy próbuj¹ przej¹c w³adze w królestwie");
					break;
				} else if (odp0.equals("2")) {
					System.out.println("> Chcesz kupiæ Mikstury ¯ycia?");
					System.out.println("> Na stanie mam " + player.healthPotionInShop + " Mikstur Zdrowia po "
							+ player.healthPotionPrice + " za sztuke");
					System.out.println("\t Zainteresowany?");
					System.out.println("\t1. Tak");
					System.out.println("\t2. Nie");
					String odp00 = scan.nextLine();

					if (odp00.equals("1")) {
						if (player.healthPotions >= 5) {
							System.out.println("Nie mo¿esz mieæ wiêcej ni¿ 5 Mikstur ¯ycia");
							continue SHOP;
						}
						if (player.Gold >= player.healthPotionPrice) {
							player.healthPotionInShop--;
							int spentHP = player.Gold - player.healthPotionPrice;
							player.healthPotions++;
							System.out.println("Kupi³eœ 1 Miksture ¿ycia");
							System.out.println("Obecnie masz :" + player.healthPotions + " Mikstur ¯ycia");
							continue SHOP;
						} else {
							System.out.println("Nie masz tyle z³otych monet");
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
						System.out.println("Na stanie mam œwietny miecz! Moge Ci go sprzedaæ za " + player.swordPrice
								+ " sztuk z³ota");
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
								System.out.println("Nie masz tyle z³otych monet");
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
