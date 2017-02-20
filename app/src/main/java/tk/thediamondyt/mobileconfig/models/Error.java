package tk.thediamondyt.mobileconfig.models;

public class Error {
	private String title;
	private String text;
	
	public Error(String title, String text) {
		this.title = title;
		this.text = text;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getText() {
		return text;
	}
}
