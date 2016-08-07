//
// Created by abc84 on 2016/4/13.
//

#ifndef BUCKETSORT_BUCKETS_H
#define BUCKETSORT_BUCKETS_H

#include "iostream"
#include <cstdlib>
#include "memory"
#include "vector"
#include "list"
using namespace std;

class Bucket;
class Buckets {
private:
    vector<double> to_sort;
    shared_ptr<vector<shared_ptr<vector<double >>>> buckets;
    size_t  sz;
public:
    friend ostream &operator<<(ostream &os, Buckets &b);
    void genRandomNumber(){
        for (int i = 0; i < sz; ++i) {
            to_sort.push_back(double(rand() % 100)/100);
        }
    }
    //shared_ptr<Bucket> &operator[](size_t n);
    size_t size(){ return sz;}
    void sort();
    Buckets(size_t n = 10):buckets(new vector<shared_ptr<vector<double>>>), sz(n){
        genRandomNumber();

        /*
         * put them in the bucket then sort the bucket respectively
         */


        for (int i = 0; i < sz; ++i) {
            (*buckets).push_back(shared_ptr<vector<double>> (new vector<double>));
                    //make_shared<list<double>>();
        }
        cout<<"cons"<<endl;


        for (int j = 0; j < sz; ++j) {
            size_t index = (to_sort[j]*100)/10;
            //cout<<to_sort[j]<<endl;
            (*(*buckets)[index]).push_back(to_sort[j]);
                    //.push_back(to_sort[j]);
        }

    }
};
//ostream &operator<<(ostream &os, Buckets &b);


#endif //BUCKETSORT_BUCKETS_H
