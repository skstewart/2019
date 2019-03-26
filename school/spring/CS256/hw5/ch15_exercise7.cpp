//Name: Shayna Stewart
//HW5 - CH15 programming exercise 7
#include <iostream>
using namespace std;

class PersonData{

private:
  string lastName, firstName, address, city, state, zip, phone;

public:

  void setLastName (string lastName){
    this->lastName = lastName;
  }
  
  
  string getLastName (){
    return lastName;
  }
  
  
  void setFirstName (string firstName){
    this->firstName = firstName;
  }
  
  
  string getFirstName (){
    return firstName;
  }
  
  
  void setAddress (string address){
    this->address = address;
  }
  
  
  string getAddress (){
    return address;
  }
  
  
  void setCity (string city){
    this->city = city;
  }
  
  
  string getCity (){
    return city;
  }
  
  
  void setState (string state){
    this->state = state;
  }
  
  
  string getState () {
    return state;
  }
  
  
  void setZip (string zip) {
    this->zip = zip;
  }
  
  
  string getZip () {
    return zip;
  }
  
  
  void setPhone (string phone){
    this->phone = phone;
  }
  
  
  string getPhone () {
    return phone;
  }

};

class CustomerData:public PersonData
{
private:
  int customerNumber;
  bool mailingList;

public:
//set and get methods
  void setCustomerNumber (int customerNumber)
  {
    this->customerNumber = customerNumber;
  }
  int getCustomerNumber ()
  {
    return customerNumber;
  }
  void setMailingList (bool mailingList)
  {
    this->mailingList = mailingList;
  }
  int getMailingList ()
  {
    return mailingList;
  }

};

int
main ()
{

  CustomerData customer;


  customer.setCustomerNumber (0001);
  customer.setMailingList (true);
  customer.setLastName ("Doe");
  customer.setFirstName ("John");
  customer.setAddress ("2222 Fake Street");
  customer.setCity ("Pomona");
  customer.setState ("CA");
  customer.setZip ("91765");
  customer.setPhone ("222-222-2222");

  cout << "Customer Number : " << customer.getCustomerNumber ();
  cout << "\nName : " << customer.getFirstName () << " " << customer.
    getLastName ();
  cout << "\nAddress : " << customer.getAddress () << "," << customer.
    getCity () << "," << customer.getState () << " " << customer.getZip ();
  cout << "\nPhone : " << customer.getPhone ();

  return 0;
}
