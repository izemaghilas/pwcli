package dz.izemaghilas;

import dz.izemaghilas.cmd.*;

public class Main {
    public static void main(String[] args) {
        try {
            Cmd.process(args);
        } catch (EmptyCmdException | UnknownCmdException | MissingCmdOptionException | UnknownCmdOptionException |
                 DuplicateCmdOptionException | NoCmdOptionValue e) {
            System.out.printf("%s\n", e);
        }
    }
}