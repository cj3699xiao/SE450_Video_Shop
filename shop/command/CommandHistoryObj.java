package shop.command;
import java.util.Stack;

final class CommandHistoryObj implements CommandHistory {
  Stack<UndoableCommand> _undoStack = new Stack<UndoableCommand>();
  Stack<UndoableCommand> _redoStack = new Stack<UndoableCommand>();
  RerunnableCommand _undoCmd = new RerunnableCommand () {
      public boolean run () {
        boolean result = !_undoStack.empty(); // if has item, then true.
        if (result) {
          // Undo
          // TODO  
        	UndoableCommand cur = _undoStack.pop();
        	cur.undo();
        	_redoStack.push(cur); // then this cmd become redoable
        }
        return result;
      }
    };
  RerunnableCommand _redoCmd = new RerunnableCommand () {
      public boolean run () {
        boolean result = !_redoStack.empty();
        if (result) {
          // Redo
          // TODO  
        	UndoableCommand cur = _redoStack.pop();
        	cur.redo();
        	_undoStack.push(cur); // then this cmd become undoable
        }
        return result;
      }
    };

  public void add(UndoableCommand cmd) {
    // TODO  
	  _undoStack.push(cmd);
	  _redoStack.clear();  // since this is a new cmd, so no more redo available
  }
  
  public RerunnableCommand getUndo() {
    return _undoCmd;
  }
  
  public RerunnableCommand getRedo() {
    return _redoCmd;
  }
  
  // For testing
  UndoableCommand topUndoCommand() {
    if (_undoStack.empty())
      return null;
    else
      return _undoStack.peek();
  }
  // For testing
  UndoableCommand topRedoCommand() {
    if (_redoStack.empty())
      return null;
    else
      return _redoStack.peek();
  }
}
