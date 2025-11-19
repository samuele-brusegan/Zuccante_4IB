public class TermineEnOrdered implements Comparable<TermineEnOrdered> {
	private String italian;
	private String english;
	
	/**
	 * Constructs a new Termine object with the specified Italian and English words.
	 *
	 * @param italian the word in Italian
	 * @param english the corresponding word in English
	 */
	public TermineEnOrdered(String italian, String english) {
		this.italian = italian;
		this.english = english;
	}
	
	public String getItalian() {
		return italian;
	}
	
	public String getEnglish() {
		return english;
	}
	
	@Override
	public int compareTo(TermineEnOrdered o) {
		return this.english.compareTo(o.english);
	}
	@Override
	public String toString() {
//		return "Termine [italian=" + italian + ", english=" + english + "]";
        return "[it=" + italian + ", en=" + english + "]";
	}

    public TermineItOrdered reverse() {
        return new TermineItOrdered(italian, english);
    }
}
