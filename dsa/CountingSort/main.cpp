#include <iostream>
#include "SalesData.h"
using namespace std;
/*
 * n should less than or equal to the max number in the sorting array
 */
void countingSort(int[],int n);

int main() {
//    int a[] = {7,7,7,6,5,4,3,2,1};
//    countingSort(a, sizeof(a)/ sizeof(int));
//    for (int i : a) {
//        cout<<i<<" ";
//    }
    SalesData sd;
    SalesData s1;
    SalesData s2;
    cin >> s1;
    cin >> s2;
    sd = s1 + s2;
    cout << sd;
    return 0;
}

void countingSort(int a[], int n) {
    int c[n] ;
    int b[n];
    for (int i = 0; i < n; ++i) {
        c[i] = 0;
    }
    //cout<<c[7]<<endl;

    /*
     * count how many
     */
    for (int i = 0; i < n; ++i) {
        c[a[i]]++;
    }
   // cout<<c[7]<<endl;

    /*
     * count how many less than
     */
    for (int i = 1; i < n; ++i) {
        c[i] += c[i-1];
    }
    /*
     * sort
     */
    //cout<<"sb"<<"";

    for (int i = n - 1; i >= 0 ; --i) {
        b[--c[a[i]]] = a[i];
        //c[a[i]];
        //cout<<"sb"<<"";
    }
    //cout<<"ex"<<endl;

    /*
     * copy back
     */
    for (int i = n - 1; i >=0 ; i--) {
        a[i] = b[i];
    }
    for (int i = 0; i < n ; i++) {
        cout<<b[i]<<" ";
    }
}