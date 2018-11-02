package TestClass;

import java.io.Serializable;

/**
 * 
 * @author kingreebes
 *
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
		public InventoryItem(String desc, String cat, double wholePrice, double retailPrice, int qty) 
		{
			setProdNum();
			setDescription(desc);
			setWholesalePrice(wholePrice);
			setRetailPrice(retailPrice);
			setInitialQuantity(qty);
			setProfitMargin(wholePrice, retailPrice);
			setAssetValue(wholePrice, qty);
		}

		public void setProdNum()
		{
			//TODO This needs to check against current values residing in the data files
			//If a value is already taken, it needs to have a different one.
		}
		public void setDescription(String desc)
		{
			this.description = desc;
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
		public void setProfitMargin(double whole, double retail)
		{
			this.profitMargin = retail / whole;
		}
		public void setAssetValue(double whole, int qty)
		{
			this.assetValue = whole * qty;
		}
		public int getProdNum() 
		{
			return this.prodNum;
		}
		public String getDescription()
		{
			return this.description;
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
