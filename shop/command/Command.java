package shop.command;

/**
 * A simple command interface. from source 
 */
public interface Command {
  /**
   * The command body.
   * @return true if command succeeds, false otherwise
   */
  public boolean run ();
}