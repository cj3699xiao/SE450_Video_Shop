package shop.ui;

abstract class UI_Objects {
	protected final String _heading;
	protected final Pair[] _property;

//	protected static final class Pair {
//		final String prompt;
//		final UIFormTest test;
//		final UIMenuAction action;
//
//		Pair(String thePrompt, UIFormTest theTest) {
//			prompt = thePrompt;
//			test = theTest;
//			action = null;
//		}
//		
//		Pair(String thePrompt, UIMenuAction theAction) {
//		      prompt = thePrompt;
//		      action = theAction;
//		      test = null;
//		}
//	}
	protected static final class Pair<F, S> {
		protected F first;
		protected S second;
		
		Pair(F thePrompt, S action_test) {
			this.first = thePrompt;
			this.second = action_test;
		}

	}

	UI_Objects(String heading, UI_Objects.Pair[] property) {
		_heading = heading;
		_property = property;
	}

	protected int size() {
		return _property.length;
	}

	protected String getHeading() {
		return _heading;
	}

//	public String getPrompt(int i) {
	protected Object getPrompt(int i) {
		return _property[i].first;
	}

}
