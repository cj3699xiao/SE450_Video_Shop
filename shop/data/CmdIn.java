package shop.data;

import shop.command.UndoableCommand;

/**
 * Implementation of command to check in a video.
 * 
 * @see Data
 */
final class CmdIn implements UndoableCommand {
	private InventorySet _inventory;
	private Record _oldvalue;
	private Video _video;

	CmdIn(InventorySet inventory, Video video) {
		_inventory = inventory;
		_video = video;
	}

	public boolean run() {
		// TODO
		try {
			_oldvalue = _inventory.checkIn(_video);
			_inventory.getHistory().add(this);// push this cmd to undoStack
			return true;

		} catch (IllegalArgumentException x) { // exception from checkin

			return false;
		}catch(ClassCastException e) {
			return false;
		}
	}

	public void undo() {
		// TODO
		_inventory.replaceEntry(_video, _oldvalue); // replace the video by the old one
	}

	public void redo() {
		// TODO
		_oldvalue = _inventory.checkIn(_video); // redo it again
	}
}
