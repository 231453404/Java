import java.util.Scanner;

public class assignment1jayesha {

	public static String[][] maze = { { "#", "#", "#", "#", "#", "#", "#" },
			{ "#", "P", ".", ".", ".", ".", "#" },
			{ "#", "#", "#", "#", ".", "#", "#" },
			{ "#", ".", ".", ".", ".", ".", "#" },
			{ "#", "#", "#", ".", "#", "#", "#" },
			{ "#", ".", ".", ".", ".", "E", "#" },
			{ "#", "#", "#", "#", "#", "#", "#" }
	};

	static int current_x = 1;
	static int current_y = 1;
	static int score = 0;
	static int highscore = 0;
	static boolean result = false;

	static Scanner scan = new Scanner(System.in);

	static void initialize() {
		result = false;
		score = 0;
		current_x = 1;
		current_y = 1;
		maze[current_x][current_y] = "P";
		maze[maze.length - 2][maze[0].length - 2] = "E";
	}

	static void DisplayMatrix() {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}
	}

	static void MainMenu() {
		int choice;
		do {
			System.out.println("Main Menu");
			System.out.println("1. Play game");
			System.out.println("2. Instructions");
			System.out.println("3. Credits");
			System.out.println("4. Show High Score");
			System.out.println("5. Exit");
			System.out.print("Enter your choice of digit: ");
			choice = scan.nextInt();

			switch (choice) {
				case 1:
					MainGame();
					break;
				case 2:
					System.out.println("Game Instructions:");
					System.out.println(
							"The maze will have walls (#) that are impassable obstacles, open paths (.) that you can move through, your starting\r\n"
									+ "position (P), and the exit point (E).");
					break;
				case 3:
					System.out.println("Game Credits:");
					System.out.println("Jayesha Jamshed 231453404");
					break;
				case 4:
					highscore(score);
					break;
				case 5:
					System.out.println("Exiting game.");
					break;
				default:
					System.out.println("Wrong Answer, Kindly choose from only the options given.");
					break;

			}

		} while (choice != 4);
	}

	static boolean CalculateChanges(String move) {
		if (move.equals("W")) {
			if (wallcheck(current_x - 1, current_y)) {
				result = hasplayerwon(current_x - 1, current_y);
				maze[current_x][current_y] = ".";
				current_x--;
				maze[current_x][current_y] = "P";
				score();
				highscore(score);
				if (result) {
					System.out.println("You have won");
				}
			}

		} else if (move.equals("A")) {
			if (wallcheck(current_x, current_y - 1)) {
				result = hasplayerwon(current_x, current_y - 1);
				maze[current_x][current_y] = ".";
				current_y--;
				maze[current_x][current_y] = "P";
				score();
				highscore(score);
				if (result) {
					System.out.println("You have won");
				}
			}
		} else if (move.equals("S")) {
			if (wallcheck(current_x + 1, current_y)) {
				result = hasplayerwon(current_x + 1, current_y);
				maze[current_x][current_y] = ".";
				current_x++;
				maze[current_x][current_y] = "P";
				score();
				highscore(score);
				if (result) {
					System.out.println("You have won");
				}
			}
		} else if (move.equals("D")) {
			if (wallcheck(current_x, current_y + 1)) {
				result = hasplayerwon(current_x, current_y + 1);
				maze[current_x][current_y] = ".";
				current_y++;
				maze[current_x][current_y] = "P";
				score();
				highscore(score);
				if (result) {
					System.out.println("You have won");

				}
			}
		} else {
			System.out.println("You have entered incorrect key.");
		}
		return !result;
	}

	static boolean wallcheck(int x, int y) {
		if (maze[x][y] == "#") {
			System.out.println("You can not cross the wall.");
			score = score - 1;
			return false;
		} else {
			score = score + 1;
			return true;
		}
	}

	static void score() {
		System.out.println("Your score is: " + score);
	}

	static void highscore(int score) {
		if (score > highscore) {
			highscore = score;
		}
		System.out.println("Highscore is: " + highscore);
	}

	static boolean hasplayerwon(int x, int y) {
		if (maze[x][y] == "E") {
			System.out.println("You have won");
			score();
			highscore(score);
			return true;
		}
		return false;
	}

	public static void MainGame() {
		boolean play = true;
		String move;
		initialize();
		while (play) {

			System.out.println("---WELCOME TO THE MAZE---");
			DisplayMatrix();
			System.out.print("Enter your move (W/A/S/D to move, Q to quit): ");
			move = scan.next().toUpperCase();

			if (move.length() == 1) {
				System.out.println("You entered: " + move);
				play = CalculateChanges(move);
			} else {
				System.out.println("Invalid Input! Try again");
			}
		}
	}

	public static void main(String[] args) {
		MainMenu();

		System.out.println("Player at position: (" + current_x
				+ "," + current_y + ")");
	}
}
