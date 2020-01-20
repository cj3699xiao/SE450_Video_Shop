package shop.main;

import shop.ui.UI;
import shop.ui.UIMenu;
import shop.ui.UIMenuAction;
import shop.ui.UIMenuBuilder;
import shop.ui.UIError;
import shop.ui.UIForm;
import shop.ui.UIFormTest;
import shop.ui.UIFormBuilder;
import shop.data.Data;
import shop.data.Inventory;
import shop.data.Video;
import shop.data.Record;
import shop.command.Command;
import java.util.Iterator;
import java.util.Comparator;

final class Control {//change to final, not visible
	private static final int EXITED = 0;
	private static final int EXIT = 1;
	private static final int START = 2;
	private static final int NUMSTATES = 10;
	private UIMenu[] _menus;
	private static int _state; // have to make it static to enable the enum

	private static UIForm _getVideoForm;// have to make it static to enable the enum
	private static UIFormTest _numberTest;// have to make it static to enable the enum
	private UIFormTest _stringTest;

	private static Inventory _inventory;// have to make it static to enable the enum
	private static UI _ui; // have to make it static to enable the enum

	private enum Forms { // Forms enum
		yeartest(new UIFormTest() {
			public boolean run(String input) {
				try {
					int i = Integer.parseInt(input);
					return i > 1800 && i < 5000;
				} catch (NumberFormatException e) {
					return false;
				}
			}
		}),

		numberTest(new UIFormTest() {
			public boolean run(String input) {
				try {
					Integer.parseInt(input);
					return true;
				} catch (NumberFormatException e) {
					return false;
				}
			}
		}),

		stringTest(new UIFormTest() {
			public boolean run(String input) {
				return !"".equals(input.trim());
			}
		});

		private UIFormTest content;

		private Forms(UIFormTest cur_content) {
			this.content = cur_content;
		}
	}
	
	
	private enum Menus { // Menus enum
		Default("Default", new UIMenuAction() {
			public void run() {
				_ui.displayError("doh!");
			}
		}),
		
		add_remove("Add/Remove copies of a video", new UIMenuAction() {
			public void run() {
				String[] result1 = _ui.processForm(_getVideoForm);
				Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);

				UIFormBuilder f = new UIFormBuilder();
				f.add("Number of copies to add/remove", _numberTest);
				String[] result2 = _ui.processForm(f.toUIForm(""));

				Command c = Data.newAddCmd(_inventory, v, Integer.parseInt(result2[0]));
				if (!c.run()) {
					_ui.displayError("Command failed");
				}
			}
		}),
		
		checkin("Check in a video", new UIMenuAction() {
			public void run() {
				// TODO
				String[] result1 = _ui.processForm(_getVideoForm); // get the video string array

				if (result1[0] == null) {// if input with null,then return to menu
					_ui.displayError("Command failed");
					return;
				}
				Video cur = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]); // convert to video  class
				if (!Data.newInCmd(_inventory, cur).run()) {
					_ui.displayError("Command failed");
				}
			}
		}),
		
		checkout("Check out a video", new UIMenuAction() {
			public void run() {
				// TODO
				String[] result1 = _ui.processForm(_getVideoForm);

				if (result1[0] == null) {// if input with null,then return to menu
					_ui.displayError("Command failed");
					return;
				}

				Video cur = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);
				if (!Data.newOutCmd(_inventory, cur).run()) {
					_ui.displayError("Command failed");
				}
			}
		}),
		
		Print_inventory("Print the inventory", new UIMenuAction() {
			public void run() {
				_ui.displayMessage(_inventory.toString());
			}
		}),
		
		Clear_inventory("Clear the inventory", new UIMenuAction() {
			public void run() {
				if (!Data.newClearCmd(_inventory).run()) {
					_ui.displayError("Command failed");
				}
			}
		}),
		
		Undo("Undo", new UIMenuAction() {
			public void run() {
				if (!Data.newUndoCmd(_inventory).run()) {
					_ui.displayError("Command failed");
				}
			}
		}),
		
		Redo("Redo", new UIMenuAction() {
			public void run() {
				if (!Data.newRedoCmd(_inventory).run()) {
					_ui.displayError("Command failed");
				}
			}
		}),
		
		Print_top10("Print top ten all time rentals in order", new UIMenuAction() {
			public void run() {
				Iterator<Record> copy = _inventory.iterator(new Comparator<Record>() {
					public int compare(Record a, Record b) {
						return b.numRentals() - a.numRentals(); // this is ascending order
					}

				});

				StringBuffer output = new StringBuffer();

				try {
					for (int i = 0; i < 10; i++) {
						output.append(copy.next().toString());
						output.append("\n");
					}
				} catch (Exception e) {
					_ui.displayError("We dont have 10 videos yet!");
				}
				_ui.displayMessage(output.toString());
			}
		}),
		
		Exit("Exit", new UIMenuAction() {
			public void run() {
				_state = EXIT;
			}
		}),
		
		Initialization("Initialize with bogus contents", new UIMenuAction() {
			public void run() {
				Data.newAddCmd(_inventory, Data.newVideo("a", 2000, "m"), 1).run();
				Data.newAddCmd(_inventory, Data.newVideo("b", 2000, "m"), 2).run();
				Data.newAddCmd(_inventory, Data.newVideo("c", 2000, "m"), 3).run();
				Data.newAddCmd(_inventory, Data.newVideo("d", 2000, "m"), 4).run();
				Data.newAddCmd(_inventory, Data.newVideo("e", 2000, "m"), 5).run();
				Data.newAddCmd(_inventory, Data.newVideo("f", 2000, "m"), 6).run();
				Data.newAddCmd(_inventory, Data.newVideo("g", 2000, "m"), 7).run();
				Data.newAddCmd(_inventory, Data.newVideo("h", 2000, "m"), 8).run();
				Data.newAddCmd(_inventory, Data.newVideo("i", 2000, "m"), 9).run();
				Data.newAddCmd(_inventory, Data.newVideo("j", 2000, "m"), 10).run();
				Data.newAddCmd(_inventory, Data.newVideo("k", 2000, "m"), 11).run();
				Data.newAddCmd(_inventory, Data.newVideo("l", 2000, "m"), 12).run();
				Data.newAddCmd(_inventory, Data.newVideo("m", 2000, "m"), 13).run();
				Data.newAddCmd(_inventory, Data.newVideo("n", 2000, "m"), 14).run();
				Data.newAddCmd(_inventory, Data.newVideo("o", 2000, "m"), 15).run();
				Data.newAddCmd(_inventory, Data.newVideo("p", 2000, "m"), 16).run();
				Data.newAddCmd(_inventory, Data.newVideo("q", 2000, "m"), 17).run();
				Data.newAddCmd(_inventory, Data.newVideo("r", 2000, "m"), 18).run();
				Data.newAddCmd(_inventory, Data.newVideo("s", 2000, "m"), 19).run();
				Data.newAddCmd(_inventory, Data.newVideo("t", 2000, "m"), 20).run();
				Data.newAddCmd(_inventory, Data.newVideo("u", 2000, "m"), 21).run();
				Data.newAddCmd(_inventory, Data.newVideo("v", 2000, "m"), 22).run();
				Data.newAddCmd(_inventory, Data.newVideo("w", 2000, "m"), 23).run();
				Data.newAddCmd(_inventory, Data.newVideo("x", 2000, "m"), 24).run();
				Data.newAddCmd(_inventory, Data.newVideo("y", 2000, "m"), 25).run();
				Data.newAddCmd(_inventory, Data.newVideo("z", 2000, "m"), 26).run();
			}
		});

		private String title;
		private UIMenuAction content;

		private Menus(String cur_title, UIMenuAction cur_content) {
			this.title = cur_title;
			this.content = cur_content;
		}
	}
	
	private enum Exite_state { // Forms enum
		Default("Default", new UIMenuAction() {
			public void run() {
			}
		}),
		
		Yes("Yes", new UIMenuAction() {
			public void run() {
				_state = EXITED;
			}
		}),
		
		No("No", new UIMenuAction() {
			public void run() {
				_state = START;
			}
		});

		private String title;
		private UIMenuAction content;

		private Exite_state(String cur_title,UIMenuAction cur_content) {
			this.title = cur_title;
			this.content = cur_content;
		}
	}

	Control(Inventory inventory, UI ui) {
		_inventory = inventory;
		_ui = ui;

		_menus = new UIMenu[NUMSTATES];
		_state = START;
		addSTART(START);
		addEXIT(EXIT);

		UIFormTest yearTest = Forms.yeartest.content;

		_numberTest = Forms.numberTest.content;

		_stringTest = Forms.stringTest.content;

		UIFormBuilder f = new UIFormBuilder();
		f.add("Title", _stringTest);
		f.add("Year", yearTest);
		f.add("Director", _stringTest);
		_getVideoForm = f.toUIForm("Enter Video");
	}

	void run() {
		try {
			while (_state != EXITED) {
				_ui.processMenu(_menus[_state]);
			}
		} catch (UIError e) {
			_ui.displayError("UI closed");
		}
	}

	private void addSTART(int stateNum) {
		UIMenuBuilder m = new UIMenuBuilder();

		m.add(Menus.Default.title, Menus.Default.content);
		
		m.add(Menus.add_remove.title, Menus.add_remove.content);
		
		m.add(Menus.checkin.title,Menus.checkin.content);
		
		m.add(Menus.checkout.title, Menus.checkout.content);

		m.add(Menus.Print_inventory.title,Menus.Print_inventory.content);
		
		m.add(Menus.Clear_inventory.title,Menus.Clear_inventory.content);
		
		m.add(Menus.Undo.title,Menus.Undo.content);
		
		m.add(Menus.Redo.title,Menus.Redo.content);
		
		m.add(Menus.Print_top10.title,Menus.Print_top10.content);

		m.add(Menus.Exit.title,Menus.Exit.content);

		m.add(Menus.Initialization.title,Menus.Initialization.content);

		_menus[stateNum] = m.toUIMenu("Bob's Video");
	}

	private void addEXIT(int stateNum) {
		UIMenuBuilder m = new UIMenuBuilder();
		
		m.add(Exite_state.Default.title,Exite_state.Default.content);
		
		m.add(Exite_state.Yes.title,Exite_state.Yes.content);
		
		m.add(Exite_state.No.title,Exite_state.No.content);

		_menus[stateNum] = m.toUIMenu("Are you sure you want to exit?");
	}
}
