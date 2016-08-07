#include <iostream>
#include "Buckets.h"

using namespace std;

int main() {
//    for (int i = 0; i < 1000 ; ++i) {
//        size_t x = (double(rand() % 100)/100)*100/10;
//        cout << x << "  ";
//    }
    Buckets b;
    cout<<b;
    b.sort();
    cout<<b;
    shared_ptr<vector<shared_ptr<list<double>>>> vl(new vector<shared_ptr<list<double>>>);
//    auto vl = make_shared<vector<shared_ptr<list<double>>>>
//            (new vector<shared_ptr<list<double>>>);
    //(*vl).push_back(make_shared<list<double >>());
    //(*vl)
    return 0;
}