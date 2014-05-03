public class DListNode {

	public DListNode prev;
	public DListNode next;
	public int[] item;
	public short redIntensity;
	public short greenIntensity;
	public short blueIntensity;
	public short lengthOfRun;

	public DListNode() {
		redIntensity = 0;
		greenIntensity = 0;
		blueIntensity = 0;
		prev = null;
		next = null;
		lengthOfRun = 0;
	}

	public DListNode(int[] a) {
		item = a;
		redIntensity = (short) (a[0]);
		greenIntensity = (short) (a[1]);
		blueIntensity = (short) (a[2]);
		prev = null;
		next = null;
		lengthOfRun = (short) (a[3]);
	}

	public void removeSelf() {      //---seems unnecessary....
		this.prev.next = this.next;
		this.next.prev = this.prev;
	}

	public String toString() {
		String result = "{ ";
		for (int i: item) {
			result = result + i;
		}
		result = result + " }";
		return result;
	}

	public boolean equals(DListNode a, DListNode b) {
		if (a.redIntensity == b.redIntensity &&
			a.greenIntensity == b.greenIntensity &&
			a.blueIntensity == b.blueIntensity) {
			return true;
		}else {
			return false;
		}
	}
}