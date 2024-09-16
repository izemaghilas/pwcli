package dz.izemaghilas.cmd.exceptions;

public class UnknownCmdException extends Exception {
    private final String cmdName;

    public UnknownCmdException(String cmdName) {
        super();

        this.cmdName = cmdName;
    }

    @Override
    public String toString() {
        return String.format("pwcli: unknwown%s", this.cmdName);
    }
}
