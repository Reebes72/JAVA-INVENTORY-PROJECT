package TestClass;
/**
 * 
 * @author kingreebes
 *
 */

public abstract class InventoryItem {
		private String Description;
		private String ProdNum;
		private double Price;
		
		public InventoryItem() {
			this("null","----", 0.00);
		}
		public InventoryItem(String desc, String num, double prc) {
			setDescription(desc);
			setNumber(num);
			setPrice(prc);
		}
		
		public void setDescription(String desc) {
			this.Description = desc;
		}
		public void setNumber(String num) {
			this.ProdNum = num;
		}
		public void setPrice(double prc) {
			this.Price = prc;
		}
		public String getDescription() {
			return this.Description;
		}
		public String getProdNum() {
			return this.ProdNum;
		}
		public double getPrice() {
			return this.Price;
		}
		public String getSQL() {
			return "INSERT INTO Inventory " +
					"(Description, ProdNum, Price) " +
					"VALUES ('" + this.getDescription() + "', '" + this.getProdNum() + "', " + this.getPrice() + ")";
		}
}
