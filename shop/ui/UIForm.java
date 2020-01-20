package shop.ui;

import shop.ui.UIFormTest;
/**
 * @see UIFormBuilder
 */
public class UIForm extends UI_Objects{
UIForm(String heading, Pair[] property) {
		super(heading, property);
		// TODO Auto-generated constructor stub
	}
//  private final String _heading;
//  private final Pair[] _form;
//
//  static final class Pair {
//    final String prompt;
//    final UIFormTest test;
//
//    Pair(String thePrompt, UIFormTest theTest) {
//      prompt = thePrompt;
//      test = theTest;
//    }
//  }
//  
//  UIForm(String heading, Pair[] menu) {
//    _heading = heading;
//    _form = menu;
//  }
//  public int size() {
//    return _form.length;
//  }
//  public String getHeading() {
//    return _heading;
//  }
//  public String getPrompt(int i) {
//    return _form[i].prompt;
//  }
 
  protected boolean checkInput(int i, String input) { 
    if (null == super._property[i]) {
      return true;
    }
    try {
     ((UIFormTest)super._property[i].second).run(input);
    }catch(Exception a) {
    	System.out.println("Cast error!");
    }
    return ((UIFormTest)super._property[i].second).run(input);
  }
}
