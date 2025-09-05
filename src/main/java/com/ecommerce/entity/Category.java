package com.ecommerce.entity;

public class Category {
    private int id;
    private String name;
    private int totalCategoryProduct;
    
    private byte[]image;
    private String base64Image;

    public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public Category() {

    }

    public Category(int id, String name, int totalCategoryProduct) {
        this.id = id;
        this.name = name;
        this.totalCategoryProduct = totalCategoryProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalCategoryProduct() {
        return totalCategoryProduct;
    }
    
    

    public void setTotalCategoryProduct(int totalCategoryProduct) {
        this.totalCategoryProduct = totalCategoryProduct;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", totalCategoryProduct=" + totalCategoryProduct +
                '}';
    }
}
