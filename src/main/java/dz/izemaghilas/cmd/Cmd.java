package dz.izemaghilas.cmd;

import dz.izemaghilas.cmd.exceptions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Cmd {
    private static final String CMD_HELP = "help";
    private static final String CMD_GET = "get";
    private static final String CMD_STORE = "store";

    private static final String CMD_EMAIL_OPTION = "-e";
    private static final String CMD_WEBSITE_URL_OPTION = "-wu";
    private static final String CMD_PASSWORD_OPTION = "-p";

    public static void process(String[] cmdArgs) throws EmptyCmdException, UnknownCmdException, MissingCmdOptionException, UnknownCmdOptionException, DuplicateCmdOptionException, NoCmdOptionValue {
        if (cmdArgs.length == 0) {
            throw new EmptyCmdException();
        } else if (Stream.of(CMD_HELP, CMD_GET, CMD_STORE).noneMatch(cmd -> cmd.equals(cmdArgs[0]))) {
            throw new UnknownCmdException(cmdArgs[0]);
        } else if (cmdArgs[0].equals(CMD_GET)) {
            getCmd(cmdArgs);
        } else if (cmdArgs[0].equals(CMD_STORE)) {
            storeCmd(cmdArgs);
        } else {
            helpCmd();
        }
    }

    private static List<CmdPair> buildCmdPairs(String[] cmdArgs) {
        List<CmdPair> cmdPairs = new ArrayList<>();

        int i = 0;
        while (i < cmdArgs.length) {
            if (i + 1 < cmdArgs.length) {
                // if the next token is an option
                // the current option does not have a value
                if (cmdArgs[i + 1].charAt(0) == '-') {
                    cmdPairs.add(new CmdPair(cmdArgs[i], ""));
                    i += 1;
                    continue;
                }
                cmdPairs.add(new CmdPair(cmdArgs[i], cmdArgs[i + 1]));
            } else {
                cmdPairs.add(new CmdPair(cmdArgs[i], ""));
            }
            i += 2;
        }

        return cmdPairs;
    }

    private static void checkDuplicatedCmdOptions(String cmd, List<CmdPair> cmdPairs, List<String> options) throws UnknownCmdOptionException, DuplicateCmdOptionException {
        List<String> previousOptions = new ArrayList<>();
        for (CmdPair cmdPair : cmdPairs) {
            if (options.stream().noneMatch(option -> option.equals(cmdPair.option()))) {
                throw new UnknownCmdOptionException(cmd, cmdPair.option());
            }

            if (previousOptions.stream().anyMatch(option -> option.equals(cmdPair.option()))) {
                throw new DuplicateCmdOptionException(cmd, cmdPair.option());
            }
            previousOptions.add(cmdPair.option());
        }
    }

    private static void helpCmd() {
        System.out.println("""
                Usage: pwcli [command options ...]
                Commands:
                help        Display help message
                                
                get         Get passwords
                options:
                    -e <email address>
                    -wu <website url>
                usage:
                    get -e <email address>
                    get -wu <website url>
                    get -e <email address> -wu <website url>
                                
                store       Store password
                options:
                    -p  <password>
                    -e  <email address>
                    -wu <website url>
                usage:
                    store -p <password> -e <email address> -wu <website url>
                """);
    }

    private static void getCmd(String[] args) throws MissingCmdOptionException, UnknownCmdOptionException, DuplicateCmdOptionException, NoCmdOptionValue {
        if (args.length <= 1) {
            throw new MissingCmdOptionException(CMD_GET);
        }

        List<CmdPair> cmdPairs = buildCmdPairs(Arrays.copyOfRange(args, 1, args.length));
        checkDuplicatedCmdOptions(CMD_GET, cmdPairs, List.of(CMD_EMAIL_OPTION, CMD_WEBSITE_URL_OPTION));

        String email = "";
        String websiteUrl = "";

        for (CmdPair cmdPair : cmdPairs) {
            if (cmdPair.option().equals(CMD_EMAIL_OPTION)) {
                if (cmdPair.value().isBlank()) {
                    throw new NoCmdOptionValue(CMD_GET, CMD_EMAIL_OPTION);
                }
                email = cmdPair.value();
            } else if (cmdPair.option().equals(CMD_WEBSITE_URL_OPTION)) {
                if (cmdPair.value().isBlank()) {
                    throw new NoCmdOptionValue(CMD_GET, CMD_WEBSITE_URL_OPTION);
                }
                websiteUrl = cmdPair.value();
            }
        }

        // get password
    }

    private static void storeCmd(String[] args) throws MissingCmdOptionException, UnknownCmdOptionException, DuplicateCmdOptionException, NoCmdOptionValue {
        if (args.length <= 1) {
            throw new MissingCmdOptionException(CMD_STORE);
        }

        List<CmdPair> cmdPairs = buildCmdPairs(Arrays.copyOfRange(args, 1, args.length));
        checkDuplicatedCmdOptions(CMD_STORE, cmdPairs, List.of(CMD_PASSWORD_OPTION, CMD_EMAIL_OPTION, CMD_WEBSITE_URL_OPTION));

        String password = "";
        String email = "";
        String websiteUrl = "";

        for (CmdPair cmdPair : cmdPairs) {
            switch (cmdPair.option()) {
                case CMD_PASSWORD_OPTION -> {
                    if (cmdPair.value().isBlank()) {
                        throw new NoCmdOptionValue(CMD_STORE, CMD_PASSWORD_OPTION);
                    }
                    password = cmdPair.value();
                }
                case CMD_EMAIL_OPTION -> {
                    if (cmdPair.value().isBlank()) {
                        throw new NoCmdOptionValue(CMD_STORE, CMD_EMAIL_OPTION);
                    }
                    email = cmdPair.value();
                }
                case CMD_WEBSITE_URL_OPTION -> {
                    if (cmdPair.value().isBlank()) {
                        throw new NoCmdOptionValue(CMD_STORE, CMD_WEBSITE_URL_OPTION);
                    }
                    websiteUrl = cmdPair.value();
                }
            }
        }

        // store password
    }
}
