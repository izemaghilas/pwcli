package dz.izemaghilas;

import dz.izemaghilas.cmd.Cmd;
import dz.izemaghilas.cmd.exceptions.*;
import dz.izemaghilas.storage.Storage;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Storage.init();
            Cmd.process(args);
        } catch (IOException e) {
            System.out.printf("failed to init pwcli. '%s'", e.getMessage());
        } catch (EmptyCmdException | UnknownCmdException | MissingCmdOptionException | UnknownCmdOptionException |
                 DuplicateCmdOptionException | NoCmdOptionValue e) {
            System.out.printf("%s\n", e);
        }
    }
}