package social_network.command_line;

import social_network.SocialNetwork;
import social_network.commands.CommandFactory;
import social_network.follows.FollowRepository;
import social_network.date.DateTime;
import social_network.date.TimeDifferenceFormatter;
import social_network.follows.InMemoryFollowRepository;
import social_network.posts.InMemoryPostRepository;
import social_network.posts.PostFormatter;
import social_network.posts.PostRepository;

import java.io.PrintStream;
import java.util.Scanner;

public class CommandLineReader {

    private static CommandLineInputParser parser;
    private static DateTime dateTime;
    private static PostRepository postRepository;
    private static PrintStream output;
    private static TimeDifferenceFormatter time;
    private static PostFormatter postFormatter;
    private static CommandFactory commandFactory;
    private static SocialNetwork socialNetwork;
    private static SocialNetworkCommandLineClient client;
    private static FollowRepository followRepository;

    public static void main(String[] args) {
        initialiseApplication();

        Scanner scanner = new Scanner(System.in);
        output.println("welcome!");

        while (true) {
            output.print("> ");
            String input = scanner.nextLine();
            client.executeCommand(input);
        }
    }

    private static void initialiseApplication() {
        parser = new CommandLineInputParser();
        dateTime = new DateTime();
        output = System.out;
        time = new TimeDifferenceFormatter();
        postRepository = new InMemoryPostRepository(dateTime);
        postFormatter = new PostFormatter(System.out, time);
        followRepository = new InMemoryFollowRepository();
        commandFactory = new CommandFactory(postRepository, followRepository, postFormatter);
        socialNetwork = new SocialNetwork(commandFactory);
        client = new SocialNetworkCommandLineClient(parser, socialNetwork);
    }
}
