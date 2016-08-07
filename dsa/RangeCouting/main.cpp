#include <iostream>
/*
 * 像计数排序一样统计元素个数，找到指定范围的lo_bound 和hi_bound，然后
 * 通过counts[lo_bound]-counts[hi_bound]得到count
 */
using namespace std;
int lower_bound(int start, int a[],int n);
int upper_bound(int end, int a[], int n);
void make_init(int b[], int a[], int n, int max);
void make_count(int count_a[], int a[], int n);
int find_max(int a[], int n);
void init_blank(int a[], int n);
int main() {
    int a[] = {1,3,7,9,11};
    int n =  sizeof(a)/ sizeof(a[0]);
    int max = find_max(a, n);
    int b[max+1] ;
    init_blank(b,max+1);
    make_init(b, a, n, max+1);

    int count_arr[max+1];
    init_blank(count_arr,max+1);
    make_count(count_arr,b,max+1);

    for (int i = 0; i < max+1; ++i) {
        cout<<b[i]<<" ";
    }
    cout<<endl;
    for (int i = 0; i < max+1; ++i) {
        cout<<count_arr[i]<<" ";
    }
    int lo,hi;
    cin>>lo>>hi;
    int low = lower_bound(lo,b,max);
    int hig = upper_bound(hi,b,max);
    cout<<low<<endl;
    cout<<hig<<endl;
    int count = count_arr[hig] - count_arr[low] + 1;
    cout<<count<<endl;
    return 0;
}
int upper_bound(int end, int a[], int n) {
    if (end > n)return n;
    while(a[end] == 0) {
        end--;
    }
    return end;
}
int lower_bound(int start, int a[],int n) {
    while (a[start]==0) {
        start++;
        if (start == n) return -1;
    }
    return start;
}
void init_blank(int a[], int n) {
    for (int i = 0; i < n; ++i) {
        a[i] = 0;
    }
}

void make_init(int b[],int a[], int n, int max) {
    for (int i = 0; i < n; ++i) {
        b[a[i]]++;
    }
}
void make_count(int count_a[],int b[], int n) {
    for (int i = 1; i < n; ++i) {
        count_a[i] += count_a[i-1] + b[i];
    }
}
int find_max(int a[], int n) {
    int max = a[0];
    for (int i = 0; i < n; ++i) {
        if (a[i] >= max) max = a[i];
    }
    return max;
}


