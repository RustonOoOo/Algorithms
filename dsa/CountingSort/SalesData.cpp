//
// Created by abc84 on 2016/4/13.
//

#include <iostream>
#include "SalesData.h"

istream &operator>>(istream &is, SalesData &data) {
    double price;
    is >> data.id >> data.units_sold >> price;
    if (is) {
        data.revenue = data.units_sold * price;
    }
    else {
        cout<<"error type"<<endl;
        data = SalesData();
    }
    return is;
}

ostream &operator<<(ostream &os, SalesData &data) {
    os << data.id << " avg price :" << data.units_sold << "sold" << data.revenue<<"yuan"<<endl;
}

SalesData &SalesData::operator+(const SalesData &that) {
    if (that.id != this->id) std::cout<<"not the same type of book can not add"<<std::endl;
    this->units_sold += that.units_sold;
    this->revenue += that.revenue;
    return *this;
}

SalesData &SalesData::operator-(const SalesData &that) {
    if (that.id != this->id) std::cout<<"not the same type of book can not substract"<<std::endl;
    this->units_sold += that.units_sold;
    this->revenue += that.revenue;
    return *this;
}

//SalesData::SalesData(const SalesData &data) {
//
//}
//
//
//SalesData &SalesData::operator=(const SalesData &data) {
//    return <#initializer#>;
//}

