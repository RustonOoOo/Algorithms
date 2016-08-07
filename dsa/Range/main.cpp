#include <iostream>
#include <cstdio>
#include <search.h>
#include <cstdlib>
/*
 * 快排与二分搜索上下界限
 */
using namespace std;
int comp(double i,double j);
int count_(double lo, double hi,double a[], int n);
int find_lo(double lo, double a[], int n);
int find_hi(double hi, double a[], int n);
int partition(double a[], int lo, int hi);
void exch(double a[], int i, int pre);
void qsort_(double a[], int lo, int hi, int(* com)(double i, double j)) ;
int main() {
    int length,query;
    //cin>>length>>query;
    scanf("%d %d", &length, &query);
    double in[length];
    for (int i = 0; i < length; ++i) {//init array
        //scanf("%d ", &in[i]);
        cin >> in[i] ;
    }
    //cout << "init finish" << endl;
    //qsort(in, length, sizeof(in[0]), comp);
    qsort_(in, 0, length - 1, comp);
//    for (int j = 0; j < length; ++j) {
//        cout<< in[j] << " ";
//    }
    //cout<<endl;
    double low,hi;
    for (int i = 0; i < query; i++) {
        scanf("%lf %lf", &low, &hi);
        int count = count_(low, hi, in, length);
        printf("%d\n", count);
    }
    //test case
//    int a[] = {1,2,7,9,11};
//    int c = find_hi(10,a,5);
//    cout << c;
    return 0;
}
void qsort_(double a[], int lo, int hi, int(* com)(double i, double j)) {
    if (lo >= hi)
        return;
    int p = partition(a, lo, hi);
    //cout<<p<<endl;
    qsort_(a, lo, p - 1, com);
    qsort_(a, p + 1, hi, com);
}
int partition(double a[], int lo, int hi) {
    //(rand() % (b-a+1))+ a
    int ran = rand() % (hi - lo + 1) + lo;
    exch(a, ran, hi);
    int pre = lo - 1;
    for (int i = lo; i < hi ; ++i) {
        if (a[i] <= a[hi]) {
            pre ++;
            exch(a, i, pre);
        }
    }
    exch(a, pre + 1, hi);
    return pre + 1;
}

void exch(double a[], int i, int j) {
    double temp = a[i];
    a[i] = a[j];
    a[j] = temp;
}

int comp(double i, double j) {
    return i - j;
}
int find_hi(double t, double a[], int n) {
    if (t >= a[n-1])
        return n-1;
    int hi = n-1;
    int lo = 0;
    int mid;
    while(lo<hi) {
        mid = lo + (hi - lo)/2;
        if (a[mid] > t) {
            hi = mid;
        }
        else
            lo = mid + 1;
    }
    //if (t>)
    return --lo;
}
int find_lo(double t, double a[], int n) {
    int hi = n-1;
    int lo = 0;
    while((hi - lo) > 1) {
        int mid = lo + (hi-lo)/2;
        if(a[mid] < t) {
            lo = mid;
        }
        else
            hi = mid;
    }
    return hi;
}
int count_(double lo, double hi, double a[], int n) {
    int lo_bound = find_lo(lo, a, n);
    cout<<"low" << lo_bound<<endl;
    int hi_bound = find_hi(hi, a, n);
    cout<<"hi" << hi_bound<<endl;
    //cout<<"count " << count<<endl;
    //if(hi_bound == lo_bound && hi_bound != lo)
    return hi_bound - lo_bound + 1;
}

