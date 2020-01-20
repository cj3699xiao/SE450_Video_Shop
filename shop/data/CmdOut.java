package shop.data;

import shop.command.UndoableCommand;

/**
 * Implementation of command to check out a video.
 * 
 * @see Data
 */
final class CmdOut implements UndoableCommand {
	private InventorySet _inventory;
	private Record _oldvalue;
	private Video _video;

	CmdOut(InventorySet inventory, Video video) {
		_inventory = inventory;
		_video = video;
	}

	public boolean run() {
		// TODO
		try {
			_oldvalue = _inventory.checkOut(_video);// save old value for undo
			_inventory.getHistory().add(this);// add this to undoStack
			return true;
		} catch (IllegalArgumentException x) {// from cmdOut exceptions

			return false;
		}catch(ClassCastException e) {
			return false;
		}
	}

	public void undo() {
		// TODO
		_inventory.replaceEntry(_video, _oldvalue);// replace this one by old one

	}

	public void redo() {
		// TODO
		_oldvalue = _inventory.checkOut(_video);// do it again.
	}
}
