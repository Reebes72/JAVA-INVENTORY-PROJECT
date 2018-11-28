package TestClass;

import java.io.Serializable;

/**
 * 
 * @author kingreebes
 *Most recent change added a static count variable to increment prodNum with each object creation
 */

public class InventoryItem implements Serializable
{
	    
		private int prodNum;
		private String description;
		private String category;
		private double wholesalePrice;
		private double retailPrice;
		private double profitMargin;
		private int currentQuantity;
		private double assetValue;
		
		/**
		 * This Constructor is for when we initially create an item in the program.
		 * @param prod
		 * @param desc
		 * @param cat
		 * @param wholePrice
		 * @param retailPrice
		 * @param qty
		 */
		public InventoryItem(String desc, String cat, double wholePrice, double retailPrice, int qty, int num) 
		{
			setProdNum(num);
			setDescription(desc);
			setCategory(cat);
			setWholesalePrice(wholePrice);
			setRetailPrice(retailPrice);
			setInitialQuantity(qty);
			setProfitMargin();
			setAssetValue();
		}
		public InventoryItem() {
			setProdNum(000);
			setDescription("Placeholder");
			setCategory("Text");
			setWholesalePrice(123);
			setRetailPrice(456);
			setInitialQuantity(0);
			setProfitMargin();
			setAssetValue();
		}

		public void setProdNum(int count)
		{
			this.prodNum = count;
			
			//TODO This needs to check against current values residing in the data files
			//If a value is already taken, it needs to have a different one.
		}
		public void setDescription(String desc)
		{
			this.description = desc;
		}
		public void setCategory(String cat)
		{
			this.category = cat;
		}
		public void setWholesalePrice(double wholePrice) 
		{
			if(wholePrice < 0)
			{
				this.wholesalePrice = 0.00;
			} else
			{
				this.wholesalePrice = wholePrice;
			}
		}
		public void setRetailPrice(double retail) {
			if(retail < 0)
			{
				this.retailPrice = 0.00;
			}
			if(retail < this.wholesalePrice)
			{
				this.retailPrice = this.wholesalePrice;
			} 
			else
			{
				this.retailPrice = retail;
			}
		}
		public void setInitialQuantity(int qty)
		{
			this.currentQuantity = qty;
		}
		public void setProfitMargin()
		{
			this.profitMargin = this.getRetailPrice() / this.getWholesalePrice() - 1;
		}
		public void setAssetValue()
		{
			this.assetValue = this.getWholesalePrice() * this.getQuantity();
		}
		public int getProdNum() 
		{
			return this.prodNum;
		}
		public String getDescription()
		{
			return this.description;
		}
		public String getCategory()
		{
			return this.category;
		}
		public double getWholesalePrice()
		{
			return this.wholesalePrice;
		}
		public double getRetailPrice()
		{
			return this.retailPrice;
		}
		public int getQuantity()
		{
			return this.currentQuantity;
		}
		public double getProfitMargin()
		{
			return this.profitMargin;
		}
		public double getAssetValue()
		{
			return this.assetValue;
		}
		
}
