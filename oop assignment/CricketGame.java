import java.util.Scanner;

public class CricketGame {
    private static Team team1;
    private static Team team2;
    public static void main (String args[]) {
        Game game = new Game();

        //take team names
        System.out.print("Enter name of team 1: ");
        Scanner sc = new Scanner(System.in);
        String name1 = sc.next();
        System.out.print("Enter name of team 2: ");
        String name2 = sc.next();

        //create teams, create players and add players to team
        System.out.println("Creating teams...");
        game.createTeams(name1, name2);

        //toss
        System.out.println("Calling on the Toss...");
        int teamNum = game.toss();
        if (teamNum==1) {
            team1 = game.getTeam1();
            team2 = game.getTeam2();
        } else {
            team1 = game.getTeam2();
            team2 = game.getTeam1();
        }
        System.out.println("first batting goes to: " + team1.getName());

        //first team batting
        game.play(team1, false, null);

        //second team batting
        System.out.println("Now playing: " + team2.getName());
        game.play(team2, true, team1);

        //scoreboard
        game.scoreboard();
    }
}
