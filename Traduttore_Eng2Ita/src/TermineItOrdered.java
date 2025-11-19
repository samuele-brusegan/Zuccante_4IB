public class TermineItOrdered implements Comparable<TermineItOrdered> {
	private String italian;
	private String english;

	/**
	 * Constructs a new Termine object with the specified Italian and English words.
	 *
	 * @param italian the word in Italian
	 * @param english the corresponding word in English
	 */
	public TermineItOrdered(String italian, String english) {
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
	public int compareTo(TermineItOrdered o) {
		return this.italian.compareTo(o.italian);
	}
	@Override
	public String toString() {
//		return "Termine [italian=" + italian + ", english=" + english + "]";
        return "[it=" + italian + ", en=" + english + "]";
	}

    public TermineEnOrdered reverse() {
        return new TermineEnOrdered(italian, english);
    }
}
