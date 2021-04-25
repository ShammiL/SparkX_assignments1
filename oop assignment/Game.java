
import java.util.*;

public class Game {
    private Team team1;
    private Team team2;
    private int numPlayers = 6;
    private int numOvers = 5;
    private int numBalls = 3;

    public Game() {}

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void createTeams(String name1, String  name2) {
        System.out.println("Team 1: " + name1);
        this.team1 = new Team(name1);
        team1.addPlayers(numPlayers, name1);

        System.out.println("Team 2: " + name2);
        this.team2 = new Team(name2);
        team2.addPlayers(numPlayers, name2);
    }

    public int toss() {
        List<Integer> teams = Arrays.asList(1, 2);
        return Helpers.randomGenerate(teams);
    }

    public void play(Team team, boolean isSecond, Team firstTeam) {
        List<Integer> runList = Arrays.asList(0, 1, 2, 3, 4, 6, -1, -2);
        Queue<Player> teamPlayers = team.getPlayers();
        int current = 0;
        Player currentPlayer = teamPlayers.poll();
        System.out.println("Now playing: " + currentPlayer.getName());
        for (int ov = 1; ov <= numOvers; ov++) {
            for (int bl = 1; bl <= numBalls; bl++) {
                System.out.println("over: " + String.valueOf(ov) + " | balls: " + String.valueOf(bl));
                Scanner sc = new Scanner(System.in);
                String command = sc.next();
                if (command.equals("p")) {
                    Random rand = new Random();
                    Integer run = runList.get(rand.nextInt(runList.size()));
                    if (run == -1) {
                        currentPlayer.setIsOut(IsOut.Caught);
                        teamPlayers.add(currentPlayer);
                        System.out.println(currentPlayer.getName() + " out by caught with " + currentPlayer.getRuns() + " runs");
                        if (current == 5) {
                            System.out.println("All Out!");
                            bl = 3;
                            ov = 5;
                        } else {
                            current++;
                            currentPlayer = teamPlayers.poll();
                            System.out.println("Now playing: " + currentPlayer.getName());
                        }
                    } else if (run == -2) {
                        currentPlayer.setIsOut(IsOut.Bowled);
                        teamPlayers.add(currentPlayer);
                        System.out.println(currentPlayer.getName() + " out by bowled with " + currentPlayer.getRuns() + " runs");
                        if (current == 5) {
                            System.out.println("All Out!");
                            bl = 3;
                            ov = 5;
                        } else {
                            current++;
                            currentPlayer = teamPlayers.poll();
                            System.out.println("Now playing: " + currentPlayer.getName());
                        }
                    } else {
                        System.out.println("runs: " + String.valueOf(run));
                        currentPlayer.addRuns(run);
                        team.addMarks(run);
                        if (isSecond == true) {
                            if (compareScores(team.getMarks(), firstTeam.getMarks())) {
                                bl = 3;
                                ov = 5;
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
        teamPlayers.add(currentPlayer);
        System.out.println(currentPlayer.getName() + " scored " + currentPlayer.getRuns() + " runs");
        System.out.println(team.getName() + " scored " + team.getMarks());
    }

    public static boolean compareScores(int score1, int score2) {
        return score1 > score2;
    }

    public void scoreboard() {
        Team winner;
        if (compareScores(this.team1.getMarks(), this.team2.getMarks())) {
            winner = this.team1;
        } else {
            winner = this.team2;
        }
        System.out.println("The winner is team " + winner.getName() + " !");
        System.out.println("-----SCOREBOARD-----");
        display(team1);
        display(team2);
    }

    public static void display(Team team) {
        System.out.println("Team " + team.getName());
        Queue<Player> players = team.getPlayers();
        for (Player ply: players) {
            System.out.println("Player: " + ply.getName() + " | " + "runs: " + ply.getRuns() + " | " + "out: " + ply.getIsOut());
        }
        System.out.println("Total score of team: " + team.getMarks());
    }
}