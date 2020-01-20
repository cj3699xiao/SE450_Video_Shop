package shop.ui;

import shop.ui.UIMenuAction;

/**
 * @see UIMenuBuilder
 */
public class UIMenu extends UI_Objects {

	UIMenu(String heading, Pair[] property) {
		super(heading, property);
		// TODO Auto-generated constructor stub
	}

////private final String _heading;
//  private final Pair[] _menu;

//  static final class Pair {
//    final String prompt;
//    final UIMenuAction action;
//
//    Pair(String thePrompt, UIMenuAction theAction) {
//      prompt = thePrompt;
//      action = theAction;
//    }
//  }

//  UIMenu(String heading, Pair[] menu) {
//    _heading = heading;
//    _menu = menu;
//  }
//  public int size() {
//    return super._property.length;
//  }
//  public String getHeading() {
//    return super._heading;
//  }
//  public String getPrompt(int i) {
//    return super._property[i].prompt;
//  }

//public void runAction(int i) {
//  _menu[i].action.run();
//}

	protected void runAction(int i) {
		try {
			((UIMenuAction) super._property[i].second).run();
		} catch (Exception a) {
			System.out.println("Cast error!");
		}
	}
}
