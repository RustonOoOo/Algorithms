//
// Created by abc84 on 2016/4/13.
//

#include "Buckets.h"
#include "algorithm"

void Buckets::sort() {
    auto bs = *buckets;
    for (int i = 0; i < sz; i++) {
        auto buc = bs[i];
        //cout<<"sb"<<endl;
        std::sort((*buc).begin(), (*buc).end());
    }
    cout<<"woshi"<<endl;
    to_sort.clear();
    for (shared_ptr<vector<double >> l : bs) {
        auto ls = *l;
        for_each(ls.cbegin(), ls.cend(), [this](double d){this->to_sort.push_back(d);});
    }
}



ostream &operator<<(ostream &os, Buckets &b) {
    cout<<"asda"<<endl;
    for_each(b.to_sort.cbegin(), b.to_sort.cend(), [](const double &d){cout<<d<<" ";});
    cout<<endl;
    return os;
}





