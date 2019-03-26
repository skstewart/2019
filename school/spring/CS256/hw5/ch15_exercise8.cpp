class PreferredCustomer : public CustomerData{
  private:
    double discountLevel, purchasesAmount;
    
  public:

    PreferredCustomer (){
    
    }

    void setDiscountLevel (double discountLevel){
        this->discountLevel = discountLevel;
    }

    double getDiscountLevel ()const{
        return discountLevel;
    }

    void setPurchasesAmount (double purchasesAmount){

      if (purchasesAmount < 0){
        purchasesAmount = 0.0;
     }
      this->purchasesAmount = purchasesAmount;

    if (purchasesAmount < 500){
        setDiscountLevel (0.0);

    }
    else if ((500 <= purchasesAmount) && (purchasesAmount < 1000)){
      setDiscountLevel (0.05);

    }
  else if ((1000 <= purchasesAmount) && (purchasesAmount < 1500)){
      setDiscountLevel (0.06);

    }
  else if ((1500 <= purchasesAmount) && (purchasesAmount < 2000)){
      setDiscountLevel (0.07);

    }
  else{
      setDiscountLevel (0.1);
    }

}

double getPurchasesAmount ()const{
       return purchasesAmount;
     }
};

int main (){
  PreferredCustomer customer;
  
  customer.setCustomerNumber (0001);
  customer.setMailingList (true);
  customer.setLastName ("Doe");
  customer.setFirstName ("John");
  customer.setAddress ("2222 Fake Street");
  customer.setCity ("Pomona");
  customer.setState ("CA");
  customer.setZip ("91765");
  customer.setPhone ("222-222-2222");
  customer.setPurchasesAmount(1550);

  cout << "Customer Number : " << customer.getCustomerNumber ();
  cout << "\nName : " << customer.getFirstName () << " " << customer.getLastName ();
  cout << "\nAddress : " << customer.getAddress () << "," << customer.getCity () << "," << customer.getState () << " " << customer.getZip ();
  cout << "\nPhone : " << customer.getPhone ();
  cout << "\nPurchase amount: " << customer.getPurchasesAmount();
  cout << "\nDiscount Level: %" << 100*customer.getDiscountLevel();


  return 0;
};
