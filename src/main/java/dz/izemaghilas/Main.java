package dz.izemaghilas;

import dz.izemaghilas.cmd.Cmd;
import dz.izemaghilas.cmd.exceptions.*;

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