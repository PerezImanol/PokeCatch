package classes;

public class MyException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;    

    public MyException (String e){
        this.message = e;
    }

    public String getMessage() {
        return message;
    }

	public void setMessage(String message) {
		this.message = message;
	}
}
