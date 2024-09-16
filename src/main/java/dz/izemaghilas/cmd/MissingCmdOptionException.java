package dz.izemaghilas.cmd;

public class MissingCmdOptionException extends Exception {
    private final String cmdName;

    public MissingCmdOptionException(String cmdName) {
        super();

        this.cmdName = cmdName;
    }

    @Override
    public String toString() {
        return String.format("pwcli: '%s' missing option. Try 'pwcli help' for more information", this.cmdName);
    }
}
