
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {

    public static void main(String args[]) {
        Team firstTeam;
        Team secondTeam;
        //take team names
        System.out.print("Enter name of team 1: ");
        Scanner sc = new Scanner(System.in);
        String name1 = sc.next();
        System.out.print("Enter name of team 2: ");
        String name2 = sc.next();

        //create teams, create players and add players to team
        System.out.println("Creating teams...");
        Team team1 = createTeam(name1);
        System.out.println("Team 1: " + name1);

        Team team2 = createTeam(name2);
        System.out.println("Team 2: " + name2);

        //toss
        System.out.println("Calling on the Toss...");
        firstTeam = toss(team1, team2);
        System.out.println("first batting goes to: " + firstTeam.getName());

        //first team batting
        play(firstTeam, false, null);

        if (firstTeam.equals(team1)) {
            secondTeam = team2;
        } else {
            secondTeam = team1;
        }
        //second team batting
        System.out.println("Now playing: " + secondTeam.getName());
        play(secondTeam, true, firstTeam);

        //scoreboard
        scoreboard(team1, team2);
    }

    public static Team createTeam(String name) {
        Team team = new Team(name);
        for (int p = 1; p < 7; p++) {
            String playerName = name + String.valueOf(p);
            System.out.println("Player " + String.valueOf(p) + ":" + playerName);
            Player player = new Player(playerName);
            team.addPlayers(player, p - 1);
        }
        return team;
    }

    public static Team toss(Team team1, Team team2) {
        List<Team> teams = Arrays.asList(team1, team2);
        Random rand = new Random();
        return teams.get(rand.nextInt(teams.size()));
    }

    public static void play(Team team, boolean isSecond, Team firstTeam) {
        List<Integer> runList = Arrays.asList(0, 1, 2, 3, 4, 6, -1, -2);
        Player[] teamPlayers = team.getPlayers();
        int current = 0;
        Player currentPlayer = teamPlayers[current];
        System.out.println("Now playing: " + currentPlayer.getName());
        for (int ov = 1; ov < 6; ov++) {
            for (int bl = 1; bl < 4; bl++) {
                System.out.println("over: " + String.valueOf(ov) + " | balls: " + String.valueOf(bl));
                Scanner sc = new Scanner(System.in);
                String command = sc.next();
                if (command.equals("p")) {
                    Random rand = new Random();
                    Integer run = runList.get(rand.nextInt(runList.size()));
                    if (run == -1) {
                        currentPlayer.setOut("caught");
                        System.out.println(currentPlayer.getName() + " out by caught with " + currentPlayer.getRuns() + " runs");
                        if (current == 5) {
                            System.out.println("All Out!");
                            bl = 4;
                            ov = 6;
                        } else {
                            current++;
                            currentPlayer = teamPlayers[current];
                            System.out.println("Now playing: " + currentPlayer.getName());
                        }
                    } else if (run == -2) {
                        currentPlayer.setOut("bowled");
                        System.out.println(currentPlayer.getName() + " out by bowled with " + currentPlayer.getRuns() + " runs");
                        if (current == 5) {
                            System.out.println("All Out!");
                            bl = 4;
                            ov = 6;
                        } else {
                            current++;
                            currentPlayer = teamPlayers[current];
                            System.out.println("Now playing: " + currentPlayer.getName());
                        }
                    } else {
                        System.out.println("runs: " + String.valueOf(run));
                        currentPlayer.addRuns(run);
                        team.addMarks(run);
                        if (isSecond == true) {
                            if (compareScores(team.getMarks(), firstTeam.getMarks())) {
                                bl = 4;
                                ov = 6;
                            }
                        }
                    }
                } else {
                    System.out.println("Incorrect command, press p to play");
                    bl--;
                    continue;
                }
            }
        }
        System.out.println(team.getName() + " scored " + team.getMarks());
    }

    public static boolean compareScores(int score1, int score2) {
        return score1 > score2;
    }

    public static void scoreboard(Team team1, Team team2) {
        Team winner;
        if (compareScores(team1.getMarks(), team2.getMarks())) {
            winner = team1;
        } else {
            winner = team2;
        }
        System.out.println("The winner is team " + winner.getName() + " !");
        System.out.println("-----SCOREBOARD-----");
        display(team1);
        display(team2);
    }

    public static void display(Team team) {
        System.out.println("Team " + team.getName());
        Player[] players = team.getPlayers();
        for (Player ply: players) {
            System.out.println("Player: " + ply.getName() + " | " + "runs: " + ply.getRuns() + " | " + "out: " + ply.getOut());
        }
        System.out.println("Total score of team: " + team.getMarks());
    }
}
