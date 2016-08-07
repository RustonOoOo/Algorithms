//
// Created by abc84 on 2016/4/13.
//

#ifndef COUNTINGSORT_SALESDATA_H
#define COUNTINGSORT_SALESDATA_H

#include <string>

using  namespace std;

class SalesData {
private:

    string id;
    int units_sold;
    double revenue;

public:

    SalesData():id(std::string()),units_sold(0), revenue(0){}
    //SalesData(const SalesData &);
    //SalesData &operator=(const SalesData &);
    //SalesData &operator=(SalesData &&)noexcept;
    friend istream &operator>>(istream &,SalesData &);
    friend ostream &operator<<(ostream &,SalesData &);

    SalesData &operator+(const SalesData &);
    SalesData &operator-(const SalesData &);

};


#endif //COUNTINGSORT_SALESDATA_H
